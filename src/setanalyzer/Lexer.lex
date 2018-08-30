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


WHITE=[ \r\t]
SPACE = [\n]

%{
public String lexeme;
%}


%%



{WHITE} {/*Ignore*/}
{DEFINICION} {return DEFINICION;}
{CONJUNTO_UNIVERSO} {return CONJUNTO_UNIVERSO;}
{LLAVE_ABIERTA} {return LLAVE_ABIERTA;}
{LLAVE_CERRADA} {return LLAVE_CERRADA;}
{COMA} {return COMA;}
{IGUAL} {return IGUAL;}
{CONJUNTO} {return CONJUNTO;}
{LETRA_MINUSCULA}|{NUMERO}+ {return ELEMENTO;}


{SPACE} {return SPACES;}

. {return ERROR;}