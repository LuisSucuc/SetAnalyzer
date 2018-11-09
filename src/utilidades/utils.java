
package utilidades;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import setanalyzer.Linea;


public class utils {
    
    /**
     *
     * @param set Es el String de conjuntos que se envía
     * @return Un listado de conjuntos limpios
     */
    public static Set<String> getElementos(String set){
        
        set = set.replaceAll("\\s+","");
        set = set.replace("=", "");
        set = set.replace("{", "");
        set = set.replace("}", "");
        set = set.replaceAll("([A-Z])", "");
        String[] elements = set.split(",");
        
        Set<String> set_elementos = new HashSet<String>();


        for (String element: elements) {
            set_elementos.add(element);
            
        }
        return set_elementos;
    }
    
    public static String getNombre(String set){
        set = set.replaceAll("\\s+","");
        return Character.toString(set.charAt(0));
    }
    
    public static String finalText(List<Linea> listaLineas){
        String resultado = "";
         for (Linea linea : listaLineas) {
             if (!linea.isLineaVacia()) {
                resultado = resultado + linea.getTextoOriginal() + "--->" + linea.getTextoResultado() + "\n";
             }
             else{
                resultado = resultado + "\n"; 
             }          
        }
        return resultado;
    }
    
}
