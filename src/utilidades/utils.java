/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author luis
 */
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
    
}
