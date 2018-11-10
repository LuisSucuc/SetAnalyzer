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
import java.util.Set;
import javax.swing.JOptionPane;
import static setanalyzer.Token.*;
import utilidades.mensajeError;
import utilidades.TokenEsperado;
import utilidades.utils;

public class Analisis {

    ArrayList<Linea> listaLineas = new ArrayList<Linea>();
    ArrayList<Conjunto> conjuntos = new ArrayList<Conjunto>();
    ArrayList<Operacion> operaciones = new ArrayList<Operacion>();
    Conjunto universo;
    ArrayList<Linea> lineasResultado = new ArrayList<Linea>();
    ArrayList<String> conjuntosLeidos = new ArrayList<String>();
        
    Linea lineaActual;
    String resultado;

    public Analisis() {
        lineaActual = new Linea();
        resultado = "";
        universo = new Conjunto();
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
                Semantico();
                //generarResultado();
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
                        //lineaActual.setToken(token);
                        listaLineas.add(lineaActual);
                        lineaActual = new Linea();
                    }

                    break;

                case ERROR:
                    error(mensajeError.DESCONOCIDO, lexer.yytext(), lexer.line_count, lexer.column_count);
                    return resultado;
                    
                case VOCABULARY:
                    error(mensajeError.VOCABULARIO_TOKEN, lexer.yytext(), lexer.line_count, lexer.column_count);
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
                    //Si se intentas sobreescribir el token se muestra mensajeError
                    else {
                        error(mensajeError.DUPLICADO, lexer.yytext(), lexer.line_count, lexer.column_count);
                        generarResultado();
                        return resultado;
                    }
            }
        }

    }
    
    
    
    
    
    
    public void Semantico(){
        System.out.println("Nombre" + universo.getNombre());
        System.out.println("Nombre" + universo.tieneElementos());
        //Validaciones iniciales
        if("".equals(universo.getNombre()) || universo.tieneElementos() == false){
            generarResultado();
            errorSintactico(mensajeError.UNIVERSO_VACIO);
            return;
        }
        
        
        int controlConjuntos = 0;
        int controlOperacion = 0;
        for (Linea lineaReconocida: listaLineas) {
            
            
            if (lineaReconocida.isLineaVacia() || lineaReconocida.token == CONJUNTO_UNIVERSO ||  lineaReconocida.token == OPERACION) {
                lineasResultado.add(lineaReconocida);
                continue;
            }
            //if (linea.getToken() == TokenEsperado.token || linea.getToken() == TokenEsperado.token) {
            if (lineaReconocida.token == DEFINICION) {
                lineasResultado.add(lineaReconocida);
                TokenEsperado.token = CONJUNTO;
                TokenEsperado.tokenSiguiente = CONJUNTO_UNIVERSO;
                
            } 
            
            else if(lineaReconocida.token == CONJUNTO) {
                
                System.out.println(lineaReconocida.getTextoOriginal());
                Conjunto conjuntoActual = conjuntos.get(controlConjuntos);
                System.out.println(conjuntoActual.getElementos());
                System.out.println("\n\n *************** \n \n");
                
                //Si el conjunto aún no existe
                if (noLeido(conjuntoActual.getNombre()) ){
                    String elemento = existenElementos(conjuntoActual.getElementos());
                    if ("".equals(elemento)) {
                        lineasResultado.add(lineaReconocida);
                    }
                    else{
                        String error = mensajeError.valorNoEnUniverso(elemento,conjuntoActual.getNombre());
                        generarErrorSintactico(error, lineaReconocida);
                        return;
                    }
                   
                }
                //El conjunto ya fue leído
                else{
                    String error = mensajeError.CONJUNTO_DUPLICADO + conjuntoActual.getNombre();
                    generarErrorSintactico(error, lineaReconocida);           
                    return;
                }
                controlConjuntos++;
                //Añadir a ya leidos
                conjuntosLeidos.add(conjuntoActual.getNombre());
                
            }
            
            else if(lineaReconocida.token == OPERACION) {
                Operacion operacionActual = operaciones.get(controlOperacion);
                
                if (existenConjunto(operacionActual.getConjunto1())) {
                    
                    if (existenConjunto(operacionActual.getConjunto2())) {
                        
                    }
                    else{
                        String error = mensajeError.CONJUNTO_NO_DEFINIDO + operacionActual.getConjunto2();
                        generarErrorSintactico(error, lineaReconocida);
                    }
                    
                } 
                else {
                    String error = mensajeError.CONJUNTO_NO_DEFINIDO + operacionActual.getConjunto1();
                    generarErrorSintactico(error, lineaReconocida);
                }
                
                controlOperacion++;
            }
            
        
            
            //}
            /*else{
                errorSintactico(mensajeError.valorNoEsperado(linea.getToken()));
                return;
            }*/
            
        }
    } 

  
    
    public void generarErrorSintactico(String error,Linea lineaReconocida) {
        errorSintactico(error);
        lineasResultado.add(lineaReconocida);
        lineaReconocida.sumarTextoResultado(error);
        generarResultadoSemantico();
    }
    
    
    public boolean noLeido(String nombreConjunto){
        for (String nombre: conjuntosLeidos) {
            if (nombreConjunto.equals(nombre)) {
                return false;
            }

        }
        return true;
    }
    
    public String existenElementos(Set<String> elementos){
        for (String elementoConjunto: elementos){
            Boolean existe = false;
            for (String elementoUniverso: universo.getElementos()) {
                if (elementoConjunto.equals(elementoUniverso)) {
                    existe = true;
                }
            }
            if(!existe){
                return elementoConjunto;
            }
        }
        return "";
    }
    
    
    
    
    
    
    public void errorSintactico(String error){
        JOptionPane.showMessageDialog(new JOptionPane(), error, "ERROR Semántico", JOptionPane.ERROR_MESSAGE);
    }
    
    
    
    
    
    public boolean existenConjunto(String conjunto){
         for (String nombre: conjuntosLeidos) {
            if (conjunto.equals(nombre)) {
                return true;
            }
        }
        return false;
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
    
    
    public void generarResultadoSemantico() {
        resultado = utilidades.utils.finalText(lineasResultado);
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
        JOptionPane.showMessageDialog(new JOptionPane(), mensajeError.LINEA_ERROR, "ERROR encontrado", JOptionPane.ERROR_MESSAGE);
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
