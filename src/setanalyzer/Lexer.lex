package setanalyzer;
import static setanalyzer.Token.*;
%%

%class Lexer
%type Token

DEFINICION = "DEFINICION"


CONJUNTO_UNIVERSO = "U"
LLAVE_ABIERTA = "{"
LLAVE_CERRADA = "}"
COMA = ","
IGUAL = "="

LETRA_MINUSCULA = [a-z]
CONJUNTO = [A-Z]
NUMERO = [0-9]


OPERACION = "OPERACION"
PARENTESIS_ABIERTO = "("
PARENTESIS_CERRADO = ")"


UNION = "&"
INTERSECCION = "$"
DIFERENCIA = "/"
PRODUCTO_CRUZ = "*"
COMPLEMENTO = "^c"




WHITE  =[\t]
SPACES = " "
NUEVA_LINEA = [\n\r]
ENE = "ñ"
ENE_MAY = "Ñ"

%{
public String lexeme;
%}

%%



{DEFINICION}                {return DEFINICION;}
{CONJUNTO_UNIVERSO}         {return CONJUNTO_UNIVERSO;}
{LLAVE_ABIERTA}             {return LLAVE_ABIERTA;}
{LLAVE_CERRADA}             {return LLAVE_CERRADA;}
{COMA}                      {return COMA;}
{IGUAL}                     {return IGUAL;}
{ENE_MAY}|{CONJUNTO}                  {return CONJUNTO;}
{ENE}|{LETRA_MINUSCULA}|{NUMERO}+ {return ELEMENTO;}

{OPERACION}                 {return OPERACION;}
{PARENTESIS_ABIERTO}        {return PARENTESIS_ABIERTO;}
{PARENTESIS_CERRADO}        {return PARENTESIS_CERRADO;}
{UNION}                     {return UNION;}
{INTERSECCION}              {return INTERSECCION;}
{DIFERENCIA}                {return DIFERENCIA;}
{PRODUCTO_CRUZ}             {return PRODUCTO_CRUZ;}
{COMPLEMENTO}               {return COMPLEMENTO;}


{WHITE}                     {/*Ignore*/}
{SPACES}                    {return SPACES;}
{NUEVA_LINEA}               {return NUEVA_LINEA;}


. {return ERROR;}