
package utilidades;

import java.util.ArrayList;
import java.util.List;
import setanalyzer.Linea;


public class utils {
    
    /**
     *
     * @param set Es el String de conjuntos que se envía
     * @return Un listado de conjuntos limpios
     */
    public static String[] getElements(String set){
        
        set = set.replaceAll("\\s+","");
        set = set.replace("=", "");
        set = set.replace("{", "");
        set = set.replace("}", "");
        set = set.replaceAll("([A-Z])", "");
        String[] elements = set.split(",");
        for (String element: elements) {
            System.out.println(element);
            
        }
        return elements;
    }
    
    public static String finalText(List<Linea> listaLineas){
        String resultado = "";
         for (Linea linea : listaLineas) {
             if (!linea.isLineaVacia()) {
                resultado = resultado + linea.getTextoOriginal() + "--->" + linea.getResultado() + "\n";
             }
             else{
                resultado = resultado + "\n"; 
             }          
        }
        return resultado;
    }
    
}
