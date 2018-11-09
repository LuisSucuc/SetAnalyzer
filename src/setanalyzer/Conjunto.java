
package setanalyzer;

import java.util.HashSet;
import java.util.Set;


public class Conjunto {
    private String nombre;
    private Set<String> elementos = new HashSet<String>();

    public Conjunto(String nombre, Set<String> elementos) {
        this.nombre = nombre;
        this.elementos = elementos;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<String> getElementos() {
        return elementos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setElementos(Set<String> elementos) {
        this.elementos = elementos;
    }
    
    
    
    
}
