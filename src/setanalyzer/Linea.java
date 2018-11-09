package setanalyzer;

import static setanalyzer.Token.*;
import utilidades.error;

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
    
    public boolean noTieneToken(){
        return token == null;
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
    
 
    
    
    public void error(String descripcion, String textoError, int linea, int columna){
        this.textoResultado = this.textoResultado +  descripcion + textoError + "' en línea " + linea + " columna " + columna + ". ";
        actualizarVariables();
    }
    
    public void error(String texto){
        this.textoResultado = this.textoResultado + texto ;
        actualizarVariables();
    }
    
    
    
    public boolean actualizarVariables(){
       if ("".equals(textoOriginal) && "".equals(textoResultado)) {
            lineaVacia = true;
        }
        else if (token == null) {
            this.textoResultado = this.textoResultado + error.LINEA_ERROR;
            return true;
        }
       return false;
    }
   
}
