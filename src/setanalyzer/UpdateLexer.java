package setanalyzer;

import java.io.File;


public class UpdateLexer {

  
    public static void main(String[] args) {
        String ubicacion = "/home/luis/Dropbox/UMG/Automatas/Projects/Set Analyzer/src/setanalyzer/Lexer.lex";
        generarLexer(ubicacion);
    }
    
   public static void generarLexer(String ubicacion){
       File file = new File(ubicacion);
       jflex.Main.generate(file);   
   }
    
}
