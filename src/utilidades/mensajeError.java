
package utilidades;

import setanalyzer.Token;


public class mensajeError {
    public static final String DESCONOCIDO = " Caracter desconocido : '";
    public static final String DUPLICADO = " No se permite mas de una definicion por linea : '";
    public static final String VOCABULARIO = " Caracter reconocido pero linea no pertenece a lenguaje: '";
    public static final String LINEA_ERROR = " Expresión no reconocida";
    public static final String VOCABULARIO_TOKEN = " Posición de caracter inválido: '";
    public static final String NO_UNIVERSO = " No se definió Conjunto Universo";
    public static final String UNIVERSO_VACIO = " Conjunto universo vacio o inexistente";
    public static final String CONJUNTO_DUPLICADO = " Conjunto duplicado : ";
    public static final String CONJUNTO_NO_DEFINIDO = " Conjunto no ha sido definido : ";
    public static final String VALOR_NO_ESPERADO = " TOKEN no esperado, se esperaba: ";
    public static final String VALOR_NO_EN_UNIVERSO = " Valor no en universo: ";
    
    public static String valorNoEsperado(Token tokenEnviado){
        return "Token " + tokenEnviado + " no esperado, se esperaba: " + TokenEsperado.tokenSiguiente;
    }
    
    public static String valorNoEnUniverso(String valor, String conjunto){
        return "El valor '" + valor  + "' en el conjunto " + conjunto + " no definido en universo." ;
    }
}
