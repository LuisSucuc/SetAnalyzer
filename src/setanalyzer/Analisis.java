package setanalyzer;

import Objects.Conjunto;
import Objects.Linea;
import Objects.Operacion;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static setanalyzer.Token.*;
import utilidades.error;
import utilidades.utils;

public class Analisis {

    ArrayList<Linea> listaLineas = new ArrayList<Linea>();
    ArrayList<Conjunto> conjuntos = new ArrayList<Conjunto>();
    ArrayList<Operacion> operaciones = new ArrayList<Operacion>();
    Conjunto universo;
    
    Linea lineaActual;
    String resultado;

    public Analisis() {
        lineaActual = new Linea();
        resultado = "";
    }

    public String lexicoSintactico(String ubicacionArchivo) throws FileNotFoundException, IOException {
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
                                universo = new Conjunto( utils.getNombre(lexer.yytext()), 
                                                          utils.getElementos(lexer.yytext()));
                            }
                            else if (token == CONJUNTO) {
                                conjuntos.add(new Conjunto( utils.getNombre(lexer.yytext()), 
                                                            utils.getElementos(lexer.yytext())));
                            }
                            
                            else if(token == OPERACION_CONJUNTO){
                                operaciones.add(utils.newOperacion(lexer.yytext()));
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
        imprimirOperaciones();
        try ( // Se crea el objeto que generará el reporte
                PrintWriter archivoReporte = new PrintWriter("Salida.txt", "UTF-8")) {
            //Se guarda en el archivo
            archivoReporte.println(resultado);
            //Se cierra el arschivo
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
        System.out.println("\n\n\n\n\n\n");
        System.out.println("************* CONJUNTO UNIVERSO ******");
        System.out.println(universo.getNombre());
        System.out.println(universo.getElementos());
        System.out.println("\n************* CONJUNTOS ******");
        for (Conjunto c : conjuntos) {
            System.out.println(c.getNombre());
            System.out.println(c.getElementos());
        }
    }
    
    public void imprimirOperaciones(){
        System.out.println("\n************* OPERACIONES ******");
        for (Operacion op : operaciones) {
            System.out.println(op.getConjunto1() + " " +op.getOperacion() + " "+ op.getConjunto2() + "\n" );
        }
    }

}
