
package Objects;

import setanalyzer.Token;

public class Operacion {
    private String conjunto1;
    private String conjunto2;
    private Token operacion;

   public Operacion(String conjunto1 , String conjunto2, Token operacion){
       this.conjunto1  = conjunto1;
       this.conjunto2  = conjunto2;
       this.operacion  = operacion;
   }

    public Operacion() {
        
    }

    public String getConjunto1() {
        return conjunto1;
    }

    public String getConjunto2() {
        return conjunto2;
    }

    public Token getOperacion() {
        return operacion;
    }

    public void setConjunto1(String conjunto1) {
        this.conjunto1 = conjunto1;
    }

    public void setConjunto2(String conjunto2) {
        this.conjunto2 = conjunto2;
    }

    public void setOperacion(Token operacion) {
        this.operacion = operacion;
    }

   
    
    
    
}
