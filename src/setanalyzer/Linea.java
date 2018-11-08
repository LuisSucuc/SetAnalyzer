package setanalyzer;

public class Linea {
    private String textoOriginal;
    private String resultado;
    private boolean error;
    private boolean reconocimientos;
    private boolean lineaVacia;
    
    public Linea() {
        this.error = false;
        this.reconocimientos = false;
        this.textoOriginal = "";
        this.resultado = "";
        this.lineaVacia = false;
    }
    
    public String getTextoOriginal() {
        return textoOriginal;
    }

    public String getResultado() {
        return resultado;
    }

    public boolean isLineaVacia() {
        return lineaVacia;
    }
    

    public boolean isError() {
        return error;
    }

    public boolean isReconocimientos() {
        return reconocimientos;
    }

    public void setLineaVacia(boolean lineaVacia) {
        this.lineaVacia = lineaVacia;
    }

    

    public void setReconocimientos(boolean reconocimientos) {
        this.reconocimientos = reconocimientos;
    }
    
    public void sumarResultado(String nuevoTexto){
        this.resultado = this.resultado  + " " + nuevoTexto;
        
    }
    
    public void sumarTextoOriginal(String nuevoTexto){
        this.textoOriginal = this.textoOriginal + " " + nuevoTexto;
    }
    
    public void sumarEspacioTextoOriginal(){
        sumarResultado(" ");
    }
    
    public void errorResultado(String textoError, int linea, int columna){
        this.resultado = this.resultado +  "No reconocido '" + textoError + "' en línea " + linea + " columna " + columna + ". ";
        this.error = true;
    }
    
    public void evaluarReconocimientos(){
        if ("".equals(textoOriginal) && "".equals(resultado)) {
            lineaVacia = true;
        }
        else if (!reconocimientos) {
            sumarResultado("SIN RECONOCIMIENTOS");
        }
    }
   
}
