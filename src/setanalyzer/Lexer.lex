package setanalyzer;
import static setanalyzer.Token.*;
%%

%class Lexer
%type Token
L = [a-zA-Z_]
D = [0-9]
WHITE=[ \r\t]
SPACE = [\n]

%{
public String lexeme;
%}


%%

{WHITE} {/*Ignore*/}
{SPACE} {return SPACES;}
{L}+ { return PALABRA;}
{D}+ { return INT;}

. {return ERROR;}