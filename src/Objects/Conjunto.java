
package Objects;

import java.util.HashSet;
import java.util.Set;


public class Conjunto {
    private char nombre;
    private Set<String> elementos = new HashSet<String>();

    public Conjunto(char nombre, Set<String> elementos) {
        this.nombre = nombre;
        this.elementos = elementos;
    }

    public char  getNombre() {
        return nombre;
    }

    public Set<String> getElementos() {
        return elementos;
    }

    public void setNombre(char nombre) {
        this.nombre = nombre;
    }

    public void setElementos(Set<String> elementos) {
        this.elementos = elementos;
    }
    
    
    
    
}
