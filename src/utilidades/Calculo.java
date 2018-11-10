
package utilidades;


import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Set;
import setanalyzer.Token;
import static setanalyzer.Token.*;

public class Calculo {
    
    
    public static  Set<String> union(Set<String> C1, Set<String> C2){
        return Sets.union(C1, C2);
    }
       
        
    
    public static  Set<String> interseccion(Set<String> C1, Set<String> C2){
        return Sets.intersection(C1, C2);
    }
    
    public static  Set<String> diferencia(Set<String> C1, Set<String> C2){
        return Sets.difference(C1, C2);
    }
    
    public static  Set<String> complemento(Set<String> C1, Set<String> C2){
        return Sets.difference(C2, C1);
    }
    
    public static Set<String> calcular(Set<String> C1, Set<String> C2, Token operacion){
        if (operacion == UNION) {
            return union(C1, C2);
        }
        else if (operacion == INTERSECCION){
            return interseccion(C1, C2);
        }
        
        else if (operacion == DIFERENCIA){
            return diferencia(C1, C2);
        }
        
        else if (operacion == COMPLEMENTO){
            return complemento(C1, C2);
        }
        
        return null;
    }
    
    
    
    
}
