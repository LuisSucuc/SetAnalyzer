
package setanalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import static setanalyzer.Token.*;


public class Logica {
   ArrayList<Linea> listaLineas = new ArrayList<Linea>();
   String resultado = "";
   
   
   public String generarLectura(String ubicacionArchivo )  throws FileNotFoundException, IOException{
        //ubicacionArchivo = "/home/luis/Dropbox/UMG/Automatas/Projects/Set Analyzer/Entrada.txt";
        Reader leerArchivo = new BufferedReader(new FileReader(ubicacionArchivo));
        //Se crea la instancia del analizador léxico (JFlex) y se le envía el archivo a analizar
        Lexer lexer        = new Lexer(leerArchivo);
        
        //Objeto que contendrá la información 
        Linea lineaActual = new Linea();
        while (true){
            
            //Objeto de la clase token, que retornará el token que encontró para su posterior evaluación
            Token token = lexer.yylex();

            //Si se legó el final del archivo
            if (token == null){
                resultado = utilidades.utils.finalText(listaLineas);
                escribirArchivo(resultado);
                return resultado;
            }
            
            //Se evalúa el token encontrado
            switch (token){
                
                //Si se encuentra una nueva línea
                case NUEVA_LINEA:
                        lineaActual.generarResultado();
                        listaLineas.add(lineaActual);
                        lineaActual = new Linea();
                    
                    break;

                case ERROR:
                    lineaActual.sumarTextoOriginal(lexer.yytext());
                    lineaActual.errorTextoResultado(lexer.yytext(), lexer.line_count, lexer.column_count);
                    

                    break;

                case SPACES:
                    lineaActual.sumarEspacioTextoOriginal();
                    break;

                 
                //Para todos los lexemas reconocidos
                default:
                    lineaActual.sumarTextoOriginal(lexer.yytext());
                    if (token == CONJUNTO_UNIVERSO || token == DEFINICION || token == CONJUNTO || token == OPERACION_CONJUNTO || token == OPERACION) {
                        if (token == CONJUNTO_UNIVERSO) {
                            System.out.println(utilidades.utils.getElements(lexer.yytext()));
                        }
                        lineaActual.sumarTextoResultado(token.name());
                        lineaActual.setToken(token);
                    }               
            }
        }
        
   }
   
   public void escribirArchivo( String resultado){
       try ( // Se crea el objeto que generará el reporte
               PrintWriter archivoReporte = new PrintWriter("Salida.txt", "UTF-8")) {
           //Se guarda en el archivo
           archivoReporte.println(resultado);
           //Se cierra el archivo
       }
       catch(Exception e){
           System.out.println("ERROR ESCRIBIENDO ARCHIVO");
       }
   }
    
}
