

package setanalyzer;

import java.io.File;


public class SetAnalyzer {

  
    public static void main(String[] args) {
        String path = "/home/luis/Dropbox/UMG/Automatas/Projects/Set Analyzer/src/setanalyzer/Lexer.lex";
        generarLexer(path);
    }
    
   public static void generarLexer(String path){
       File file = new File(path);
       jflex.Main.generate(file);
   }
    
}
