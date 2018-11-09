package setanalyzer;

import static setanalyzer.Token.*;

public class Linea {
    private String textoOriginal;
    private String textoResultado;
    //private boolean error;
    private boolean reconocimientos;
    private boolean lineaVacia;
    //private String resultado;
    public Token token;
    
    public Linea() {
        //this.error = false;
        this.reconocimientos = false;
        this.textoOriginal = "";
        this.textoResultado = "";
        this.lineaVacia = false;
        this.token  = null;
    }
    
    public String getTextoOriginal() {
        return textoOriginal;
    }

    public String getTextoResultado() {
        return textoResultado;
    }

    public boolean isLineaVacia() {
        return lineaVacia;
    }

    public Token getToken() {
        return token;
    }
    
    
    /*
    public boolean isError() {
        return error;
    }
    */

    public boolean isReconocimientos() {
        return reconocimientos;
    }

    public void setLineaVacia(boolean lineaVacia) {
        this.lineaVacia = lineaVacia;
    }

    public void setToken(Token token) {
        this.token = token;
    }
    
    
    

    public void setReconocimientos(boolean reconocimientos) {
        this.reconocimientos = reconocimientos;
    }
    
    public void sumarTextoResultado(String nuevoTexto){
        this.textoResultado = this.textoResultado  + " " + nuevoTexto;
        
    }
    
    public void sumarTextoOriginal(String nuevoTexto){
        this.textoOriginal = this.textoOriginal + " " + nuevoTexto;
    }
    
    public void sumarEspacioTextoOriginal(){
        sumarTextoResultado(" ");
    }
    
    public void errorTextoResultado(String textoError, int linea, int columna){
        this.textoResultado = this.textoResultado +  "Caracter no en lenguaje '" + textoError + "' en línea " + linea + " columna " + columna + ". ";
        //this.error = true;
    }
    
    public void generarResultado(){
       if ("".equals(textoOriginal) && "".equals(textoResultado)) {
            lineaVacia = true;
        }
        else if (token == null) {
            sumarTextoResultado("SIN RECONOCIMIENTOS");
        }
    }
   
}
