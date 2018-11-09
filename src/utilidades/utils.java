package utilidades;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import Objects.Linea;
import Objects.Operacion;
import setanalyzer.Token;
import static setanalyzer.Token.*;


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
    
    public static char getNombre(String set){
        set = set.replaceAll("\\s+","");
        return set.charAt(0);
    }
    
    public static String finalText(List<Linea> listaLineas){
        String resultado = "";
         for (Linea linea : listaLineas) {
             if (!linea.isLineaVacia()) {
                resultado = resultado + linea.getTextoOriginal() + "  --->  " + linea.getTextoResultado() + "\n";
             }
             else{
                resultado = resultado + "\n"; 
             }          
        }
        return resultado;
    }
    
    public static Operacion newOperacion(String nuevaOperacion){
        Operacion  operacion = new Operacion();
        nuevaOperacion = nuevaOperacion.replaceAll("\\s+","");
        String token = nuevaOperacion.replaceAll("[A-Z]", "");
        Token tokenOperacion = getTokenOperation(token);
        
        
        if (tokenOperacion != COMPLEMENTO) {
            operacion.setConjunto2(nuevaOperacion.charAt(2));
        }
        operacion.setConjunto1(nuevaOperacion.charAt(0));
        operacion.setOperacion(tokenOperacion);
        return operacion;
    }
    
    public static Token getTokenOperation(String token){
        switch(token){
            case "&":
                return UNION;
            case "$":
                return INTERSECCION;
            case "/":
                return DIFERENCIA;
            case "*":
                return PRODUCTO_CRUZ;
            case "^c":
                return COMPLEMENTO;
            default:
                return NO_RECONOCIDO;
        }
        
    }
}
