
package Objects;

import setanalyzer.Token;

public class Operacion {
    private char conjunto1;
    private char conjunto2;
    private Token operacion;

   public Operacion(char conjunto1 , char conjunto2, Token operacion){
       this.conjunto1  = conjunto1;
       this.conjunto2  = conjunto2;
       this.operacion  = operacion;
   }

    public Operacion() {
        
    }

    public char getConjunto1() {
        return conjunto1;
    }

    public char getConjunto2() {
        return conjunto2;
    }

    public Token getOperacion() {
        return operacion;
    }

    public void setConjunto1(char conjunto1) {
        this.conjunto1 = conjunto1;
    }

    public void setConjunto2(char conjunto2) {
        this.conjunto2 = conjunto2;
    }

    public void setOperacion(Token operacion) {
        this.operacion = operacion;
    }

   
    
    
    
}
