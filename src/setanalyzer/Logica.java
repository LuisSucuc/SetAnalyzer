package setanalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static setanalyzer.Token.*;
import utilidades.error;

public class Logica {

    ArrayList<Linea> listaLineas = new ArrayList<Linea>();
    ArrayList<Conjunto> conjuntos = new ArrayList<Conjunto>();
    Conjunto universo;
    
    Linea lineaActual;
    String resultado;

    public Logica() {
        lineaActual = new Linea();
        resultado = "";
    }

    public String generarLectura(String ubicacionArchivo) throws FileNotFoundException, IOException {
        //BufferedReader leerLinea = new BufferedReader(new FileReader(ubicacionArchivo));
        //ubicacionArchivo = "/home/luis/Dropbox/UMG/Automatas/Projects/Set Analyzer/Entrada.txt";
        BufferedReader leerArchivo = new BufferedReader(new FileReader(ubicacionArchivo));
        //Se crea la instancia del analizador léxico (JFlex) y se le envía el archivo a analizar
        Lexer lexer = new Lexer(leerArchivo);

        //Objeto que contendrá la información 
        while (true) {

            //Objeto de la clase token, que retornará el token que encontró para su posterior evaluación
            Token token = lexer.yylex();

            //Si se legó el final del archivo
            if (token == null) {
                generarResultado();
                return resultado;
            }

            //Se evalúa el token encontrado
            switch (token) {

                //Si se encuentra una nueva línea
                case NUEVA_LINEA:
                    //lineaActual.actualizarVariables();
                    if(lineaActual.actualizarVariables()){
                        errorTodaLinea();
                        return resultado;
                    }
                    else{
                        System.out.println("SET TOKEN");
                        lineaActual.setToken(token);
                        listaLineas.add(lineaActual);
                        lineaActual = new Linea();
                    }

                    break;

                case ERROR:
                    error(error.DESCONOCIDO, lexer.yytext(), lexer.line_count, lexer.column_count);
                    return resultado;
                    
                case VOCABULARY:
                    error(error.VOCABULARIO_TOKEN, lexer.yytext(), lexer.line_count, lexer.column_count);
                    return resultado;
                   

                case SPACES:
                    lineaActual.sumarEspacioTextoOriginal();
                    break;

                //Para todos los lexemas reconocidos
                default:
                    //Comprobar 2 tokens por linea
                    if (lineaActual.noTieneToken()) {
                        //Se añade el token reconocido a la linea
                        lineaActual.setToken(token);
                        
                        //if (token == CONJUNTO_UNIVERSO || token == DEFINICION || token == CONJUNTO || token == OPERACION_CONJUNTO || token == OPERACION) {

                            if (token == CONJUNTO_UNIVERSO) {
                                universo = new Conjunto( utilidades.utils.getNombre(lexer.yytext()), 
                                                            utilidades.utils.getElementos(lexer.yytext()));
                            }
                            else if (token == CONJUNTO) {
                                conjuntos.add(new Conjunto( utilidades.utils.getNombre(lexer.yytext()), 
                                                            utilidades.utils.getElementos(lexer.yytext())));
                            }
                            lineaActual.sumarTextoOriginal(lexer.yytext());
                            lineaActual.sumarTextoResultado(token.name());

                        
                    }
                    //Si se intentas sobreescribir el token se muestra error
                    else {
                        error(error.DUPLICADO, lexer.yytext(), lexer.line_count, lexer.column_count);
                        generarResultado();
                        return resultado;
                    }
            }
        }

    }

    public void escribirArchivo(String resultado) {
        imprimirConjuntos();
        try ( // Se crea el objeto que generará el reporte
                PrintWriter archivoReporte = new PrintWriter("Salida.txt", "UTF-8")) {
            //Se guarda en el archivo
            archivoReporte.println(resultado);
            //Se cierra el archivo
        } catch (Exception e) {
            System.out.println("ERROR ESCRIBIENDO ARCHIVO");
        }
    }

    public void generarResultado() {
        resultado = utilidades.utils.finalText(listaLineas);
        escribirArchivo(resultado);
    }

    public void error(String descripcion, String TextoOriginal, int line, int column) {
        String error =  descripcion + TextoOriginal + "' en línea " + line + " columna " + column + ". ";
        lineaActual.sumarTextoOriginal(TextoOriginal);
        lineaActual.error(error);
        listaLineas.add(lineaActual);
        generarResultado();
        JOptionPane.showMessageDialog(new JOptionPane(), error, "ERROR encontrado", JOptionPane.ERROR_MESSAGE);
    }
    
    public void errorTodaLinea(){
        listaLineas.add(lineaActual);
        generarResultado();
        JOptionPane.showMessageDialog(new JOptionPane(), error.LINEA_ERROR, "ERROR encontrado", JOptionPane.ERROR_MESSAGE);
    }
    
    public void imprimirConjuntos(){
        for (Conjunto c : conjuntos) {
            System.out.println(c.getNombre());
            System.out.println(c.getElementos());
        }
    }

}
