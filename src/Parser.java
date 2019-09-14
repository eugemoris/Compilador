//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "gramatica.y"

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//#line 26 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IF=257;
public final static short THEN=258;
public final static short ELSE=259;
public final static short ENDIF=260;
public final static short BEGIN=261;
public final static short END=262;
public final static short OUT=263;
public final static short WHILE=264;
public final static short DO=265;
public final static short LET=266;
public final static short UINT=267;
public final static short FLOAT=268;
public final static short UI_F=269;
public final static short ID=270;
public final static short CTE=271;
public final static short cadena=272;
public final static short DOBLEI=273;
public final static short MAYORI=274;
public final static short MENORI=275;
public final static short DISTINTO=276;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    2,    2,    2,    2,    1,    1,    1,    1,
    3,    3,    3,    3,    3,    5,    5,    5,    6,    6,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    7,   10,   10,   10,   10,   10,   10,   10,    9,
    9,    9,    8,    8,    8,   11,   11,   11,   11,   11,
   12,   12,   12,
};
final static short yylen[] = {                            2,
    1,    1,    3,    3,    1,    1,    2,    2,    1,    1,
    4,    4,    3,    4,    4,    3,    1,    2,    1,    1,
    9,    8,    8,    8,    7,    8,    9,    9,    7,    7,
    6,    6,    6,    5,    7,    6,    6,    6,    6,    5,
    5,    4,    5,    5,    4,    4,    3,    4,    5,    2,
    1,    3,    1,    1,    1,    1,    1,    1,    1,    4,
    3,    4,    3,    3,    1,    3,    3,    4,    3,    1,
    1,    1,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    9,   10,
    0,   51,    0,    0,    0,   72,   71,    0,    0,    0,
    0,    0,   70,    0,    0,    0,    0,   50,    0,    0,
    0,    0,    7,    8,   19,   20,   18,    0,    0,    0,
    0,    0,    0,    0,    0,   73,    0,    0,   59,   53,
   54,   55,   56,   57,   58,    0,    0,    0,    0,    0,
   47,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   16,   13,   15,    0,   69,    0,    0,    0,
    0,    0,    0,    5,    6,    0,    0,    0,    0,   66,
   67,   45,   46,    0,    0,    0,    0,   42,    0,    0,
   62,   60,   14,   12,   11,    0,    0,   68,    0,    0,
    0,    0,    0,    0,   34,    0,   44,   43,   41,    0,
    0,   40,   49,    0,   38,    0,    0,   32,   36,    0,
    0,   31,    0,    3,    0,    0,   33,   39,   37,    0,
   35,    0,    0,   30,    0,   29,    0,   25,    0,    0,
    0,   24,   22,    0,   26,   23,   27,   28,   21,
};
final static short yydgoto[] = {                          7,
    8,   83,   84,   85,   11,   40,   20,   21,   12,   58,
   22,   23,
};
final static short yysindex[] = {                       -46,
  -18,  -33,  -37,  -30, -245,  -53,    0,  113,    0,    0,
  -15,    0, -213,  -24,   -3,    0,    0,   92, -258,  -35,
  -43,    9,    0,   12, -215,  -27,  -14,    0,   14,  -24,
  -24,  -18,    0,    0,    0,    0,    0, -197, -190,   36,
   38,   48,  -21,   54,  -32,    0,  -76, -160,    0,    0,
    0,    0,    0,    0,    0,  -24,  -24,  -24,   20,   20,
    0,   55,   32, -161,  -11,  -76, -154,  -24,   84,  104,
   74,  -42,    0,    0,    0, -226,    0,  -10, -127,  -76,
 -142,  113, -134,    0,    0,  -76,    9,    9,   31,    0,
    0,    0,    0,   99,  -76,  -76, -184,    0,  -76,  109,
    0,    0,    0,    0,    0,  -76,  -76,    0,  -76, -125,
 -164, -100,  -65,  -76,    0,  -85,    0,    0,    0,  -76,
  -76,    0,    0,  -64,    0, -121,  -76,    0,    0, -147,
  -76,    0,  -18,    0, -120,  -76,    0,    0,    0,  -76,
    0,  -76, -107,    0,  -94,    0,  -99,    0,  -95,  -89,
  -82,    0,    0,  -74,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
  189,    0,    0,    0,    0,   98,    0,  193,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   67,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   23,   45,  -36,    0,
    0,    0,    0,   79,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   94,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  118,   37,   26,   28,    0,    3,   34,   19,    0,    0,
  127,  147,
};
final static int YYTABLESIZE=383;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         56,
   65,   57,   25,  105,   52,   48,   18,   31,   81,   26,
   28,   19,   46,   64,   19,   41,   55,   19,   54,   77,
   19,   14,   63,   19,   29,    9,   67,   10,   39,   97,
  108,  106,   56,   33,   57,   34,   43,   27,  107,   13,
   72,   65,   38,   65,   64,   65,   65,   42,   69,   70,
   59,   45,   62,   35,   36,   60,   63,   61,   71,   65,
   65,   78,   65,   63,   19,   63,   61,   63,   63,   35,
   36,  120,   94,   56,   68,   57,   89,   93,   48,   73,
  121,   74,   63,   75,   63,   64,  100,   64,   76,   64,
   64,   32,    2,    4,   79,  129,   82,   86,    3,    4,
   92,    5,   98,   95,   64,    6,   64,    9,  144,   10,
   99,  145,  146,   32,    2,  111,  110,  112,   82,  103,
    3,    4,  116,    5,  114,  115,   56,    6,   57,  101,
  109,  118,  119,  127,  128,  122,   19,  142,   33,  148,
   34,   17,  124,  125,  117,  126,   56,  130,   57,  102,
  135,   56,  152,   57,  123,   17,  138,  139,  131,  132,
  155,   32,    2,  143,  156,  153,   82,  147,    3,    4,
  157,    5,  149,  136,  137,    6,  150,  158,  151,   32,
    2,  154,   87,   88,   82,  159,    3,    4,    2,    5,
  133,    2,    1,    6,  140,  141,  134,    3,    4,  113,
    5,    0,   30,    0,    6,   90,   91,    0,    0,    1,
    2,    0,   49,  104,    0,    0,    3,    4,    0,    5,
    0,   52,   47,    6,    0,   80,    0,    0,   52,   50,
   51,   52,   53,    0,   24,   15,   16,   17,   15,   16,
   17,   15,   16,   17,   15,   16,   17,   15,   16,   17,
   66,   35,   36,   96,   37,    0,   65,   65,   65,   65,
   65,    0,   65,   65,   65,   65,   65,    0,    0,    0,
   65,    0,    0,   65,   65,   65,   65,    0,   63,   63,
   63,   63,   63,    0,   63,   63,   63,   63,   63,   16,
   17,    0,   63,    0,    0,   63,   63,   63,   63,    0,
   64,   64,   64,   64,   64,    0,   64,   64,   64,   64,
   64,    0,    0,    0,   64,    0,    0,   64,   64,   64,
   64,    0,   61,   61,    0,   61,   61,    0,   61,   61,
   61,    0,   61,    0,   48,   48,   61,   48,   48,    0,
   48,   48,   48,    0,   48,    0,    0,   44,   48,    4,
    4,    0,    4,    4,    0,    4,    4,    4,    0,    4,
   15,   16,   17,    4,   17,   17,    0,   17,   32,    2,
    0,    0,    0,    0,    0,    3,    4,    0,    5,    0,
    0,    0,    6,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         43,
    0,   45,   40,   46,   41,   41,   40,   61,   41,   40,
  256,   45,  271,   41,   45,   13,   60,   45,   62,   41,
   45,   40,    0,   45,  270,    0,   41,    0,   44,   41,
   41,  258,   43,    8,   45,    8,   40,    4,  265,   58,
   38,   41,   58,   43,    0,   45,   46,   14,   30,   31,
   42,   18,   41,  267,  268,   47,  272,   46,  256,   26,
   60,   43,   62,   41,   45,   43,    0,   45,   46,  267,
  268,  256,   41,   43,   61,   45,   58,   46,    0,  270,
  265,   46,   60,   46,   62,   41,   68,   43,   41,   45,
   46,  256,  257,    0,   41,  260,  261,  258,  263,  264,
   46,  266,   66,  265,   60,  270,   62,   82,  256,   82,
  265,  259,  260,  256,  257,  258,   80,   81,  261,   46,
  263,  264,   86,  266,  259,  260,   43,  270,   45,   46,
  258,   95,   96,  259,  260,   99,   45,  259,  113,  260,
  113,   44,  106,  107,   46,  109,   43,  111,   45,   46,
  114,   43,  260,   45,   46,   58,  120,  121,  259,  260,
  260,  256,  257,  127,  260,  260,  261,  131,  263,  264,
  260,  266,  136,  259,  260,  270,  140,  260,  142,  256,
  257,  145,   56,   57,  261,  260,  263,  264,    0,  266,
  256,  257,    0,  270,  259,  260,  262,  263,  264,   82,
  266,   -1,  256,   -1,  270,   59,   60,   -1,   -1,  256,
  257,   -1,  256,  256,   -1,   -1,  263,  264,   -1,  266,
   -1,  258,  258,  270,   -1,  258,   -1,   -1,  265,  273,
  274,  275,  276,   -1,  272,  269,  270,  271,  269,  270,
  271,  269,  270,  271,  269,  270,  271,  269,  270,  271,
  265,  267,  268,  265,  270,   -1,  256,  257,  258,  259,
  260,   -1,  262,  263,  264,  265,  266,   -1,   -1,   -1,
  270,   -1,   -1,  273,  274,  275,  276,   -1,  256,  257,
  258,  259,  260,   -1,  262,  263,  264,  265,  266,  270,
  271,   -1,  270,   -1,   -1,  273,  274,  275,  276,   -1,
  256,  257,  258,  259,  260,   -1,  262,  263,  264,  265,
  266,   -1,   -1,   -1,  270,   -1,   -1,  273,  274,  275,
  276,   -1,  256,  257,   -1,  259,  260,   -1,  262,  263,
  264,   -1,  266,   -1,  256,  257,  270,  259,  260,   -1,
  262,  263,  264,   -1,  266,   -1,   -1,  256,  270,  256,
  257,   -1,  259,  260,   -1,  262,  263,  264,   -1,  266,
  269,  270,  271,  270,  267,  268,   -1,  270,  256,  257,
   -1,   -1,   -1,   -1,   -1,  263,  264,   -1,  266,   -1,
   -1,   -1,  270,
};
}
final static short YYFINAL=7;
final static short YYMAXTOKEN=276;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",null,
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,"IF","THEN","ELSE","ENDIF","BEGIN","END","OUT",
"WHILE","DO","LET","UINT","FLOAT","UI_F","ID","CTE","cadena","DOBLEI","MAYORI",
"MENORI","DISTINTO",
};
final static String yyrule[] = {
"$accept : programa",
"programa : sentencia",
"programa : error",
"bloqueSentencias : BEGIN sentencia END",
"bloqueSentencias : BEGIN sentencia error",
"bloqueSentencias : declarativa",
"bloqueSentencias : ejecutable",
"sentencia : sentencia declarativa",
"sentencia : sentencia ejecutable",
"sentencia : declarativa",
"sentencia : ejecutable",
"declarativa : listaVariables ':' tipo '.'",
"declarativa : listaVariables ':' tipo error",
"declarativa : listaVariables tipo '.'",
"declarativa : listaVariables ':' error '.'",
"declarativa : error ':' tipo '.'",
"listaVariables : listaVariables ',' ID",
"listaVariables : ID",
"listaVariables : listaVariables ID",
"tipo : UINT",
"tipo : FLOAT",
"ejecutable : IF '(' condicion ')' THEN bloqueSentencias ELSE bloqueSentencias ENDIF",
"ejecutable : IF '(' condicion ')' THEN bloqueSentencias ELSE ENDIF",
"ejecutable : IF condicion ')' THEN bloqueSentencias ELSE bloqueSentencias ENDIF",
"ejecutable : IF '(' condicion THEN bloqueSentencias ELSE bloqueSentencias ENDIF",
"ejecutable : IF condicion THEN bloqueSentencias ELSE bloqueSentencias ENDIF",
"ejecutable : IF '(' condicion ')' bloqueSentencias ELSE bloqueSentencias ENDIF",
"ejecutable : error '(' condicion ')' THEN bloqueSentencias ELSE bloqueSentencias ENDIF",
"ejecutable : IF '(' error ')' THEN bloqueSentencias ELSE bloqueSentencias ENDIF",
"ejecutable : IF '(' condicion ')' THEN bloqueSentencias ENDIF",
"ejecutable : IF '(' condicion ')' THEN bloqueSentencias error",
"ejecutable : IF '(' condicion ')' bloqueSentencias ENDIF",
"ejecutable : IF '(' condicion THEN bloqueSentencias ENDIF",
"ejecutable : IF condicion ')' THEN bloqueSentencias ENDIF",
"ejecutable : IF condicion THEN bloqueSentencias ENDIF",
"ejecutable : error '(' condicion ')' THEN bloqueSentencias ENDIF",
"ejecutable : IF '(' condicion ')' THEN ENDIF",
"ejecutable : WHILE '(' condicion ')' DO bloqueSentencias",
"ejecutable : error '(' condicion ')' DO bloqueSentencias",
"ejecutable : WHILE '(' condicion ')' error bloqueSentencias",
"ejecutable : WHILE condicion ')' DO bloqueSentencias",
"ejecutable : WHILE '(' condicion DO bloqueSentencias",
"ejecutable : WHILE condicion DO bloqueSentencias",
"ejecutable : WHILE '(' ')' DO bloqueSentencias",
"ejecutable : OUT '(' cadena ')' '.'",
"ejecutable : OUT cadena ')' '.'",
"ejecutable : OUT '(' cadena '.'",
"ejecutable : OUT cadena '.'",
"ejecutable : OUT '(' cadena ')'",
"ejecutable : LET ID '=' expArit1 '.'",
"ejecutable : LET error",
"ejecutable : asignacion",
"condicion : expArit1 comparador expArit1",
"comparador : DOBLEI",
"comparador : MAYORI",
"comparador : MENORI",
"comparador : DISTINTO",
"comparador : '>'",
"comparador : '<'",
"comparador : error",
"asignacion : ID '=' expArit1 '.'",
"asignacion : ID '=' expArit1",
"asignacion : ID error expArit1 '.'",
"expArit1 : expArit1 '+' expArit2",
"expArit1 : expArit1 '-' expArit2",
"expArit1 : expArit2",
"expArit2 : expArit2 '*' termino",
"expArit2 : expArit2 '/' termino",
"expArit2 : UI_F '(' expArit1 ')'",
"expArit2 : UI_F '(' ')'",
"expArit2 : termino",
"termino : CTE",
"termino : ID",
"termino : '-' CTE",
};

//#line 349 "gramatica.y"

private Lexico lex;
private ArrayList<Object> listReglas = new ArrayList<>();
private int contFloat = 0;

public Parser(Lexico lex){
	this.lex = lex;
}

public void addRegla(String lineaRegla){
        lineaRegla = "\r\n"+lineaRegla; 
        listReglas.add(lineaRegla.toString());
    }
	    

public ArrayList<Object> getReglasUsadas(){
	return listReglas;
}

private void yyerror(String string) {
	
}
	public void cargarArbol(Nodo algo){
	
    try {
        FileWriter fileArbol;
        fileArbol = new FileWriter("Arbol.txt");
        PrintWriter writeArbol = new PrintWriter(fileArbol);
	imprimirArbol(writeArbol, algo,"");	
        fileArbol.close();
    } catch (IOException ex) {
        Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}


public static  void imprimirArbol(PrintWriter wr,Nodo algo, String sangria){
	if (algo != null){
		 wr.print(sangria + algo.getNombre()+ algo.getTipo()+"\r\n");
		imprimirArbol(wr,algo.getNodoIzq(),sangria + "	");
		imprimirArbol(wr, algo.getNodoDer(),sangria + "	");
	}
	
}

public void setearTipo( Nodo variable, String tipo){
	if (variable != null)
	{
		setearTipo(variable.getNodoIzq(),tipo);
		if (variable.esHoja())
		{
				if (tipo.equals("UINT"))
				{
					lex.setTipo(variable.getNombre(),"CE");
				}
				else
				{
					lex.setTipo(variable.getNombre(),"CF");
				}	
		}
		setearTipo(variable.getNodoDer(),tipo);
	}
	
}

public int yylex()
{
    int val = 0;
    try {
        val = this.lex.yylex();
		yylval = new ParserVal(lex.getToken());
        return val;
    } catch (IOException ex) {
        Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
    }
    return val;
}
//#line 468 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 16 "gramatica.y"
{
												 addRegla("Linea: " + lex.getNroLinea() + ". Regla 01: sentencias en programa");
												 yyval = val_peek(0);
												cargarArbol((Nodo)yyval.obj);
												if (!lex.hayError())
												{
													
													GeneracionCodigo gc = new GeneracionCodigo((Nodo)yyval.obj,lex.getTablaSimbolos(),contFloat);
													gc.generarCodigo();
												}
												
												}
break;
case 2:
//#line 29 "gramatica.y"
{ lex.addError("Linea " + lex.getNroLinea() + ": ERROR en el programa"); }
break;
case 3:
//#line 32 "gramatica.y"
{
																					addRegla("Linea: " + lex.getNroLinea() + ". Regla 02: bloque de sentencia con begin y end");
																					yyval = val_peek(1); 
																				}
break;
case 4:
//#line 37 "gramatica.y"
{ lex.addError("Linea " + lex.getNroLinea() + ": ERROR en bloque de sentencias, falta END"); }
break;
case 5:
//#line 39 "gramatica.y"
{
																					addRegla("Linea: " + lex.getNroLinea() + ". Regla 03: sentencia declarativa en bloque de sentencia");
																					yyval = val_peek(0);	
																				}
break;
case 6:
//#line 43 "gramatica.y"
{
																					addRegla("Linea: " + lex.getNroLinea() + ". Regla 44: sentencia ejecutable en bloque de sentencia");
																					yyval = val_peek(0);
																				}
break;
case 7:
//#line 49 "gramatica.y"
{
																addRegla("Linea: " + lex.getNroLinea() + ". Regla 05: agregar sentencia declarativa a las sentencias");
																yyval = new ParserVal(new Nodo((Nodo)val_peek(1).obj, (Nodo)val_peek(0).obj, "declarativa",null));
															}
break;
case 8:
//#line 53 "gramatica.y"
{
																addRegla("Linea: " + lex.getNroLinea() + ". Regla 06: agregar sentencia ejecutable a las sentencias");
																 yyval = new ParserVal(new Nodo((Nodo)val_peek(1).obj, (Nodo)val_peek(0).obj, "ejecutable",null));
															}
break;
case 9:
//#line 57 "gramatica.y"
{
															addRegla("Linea: " + lex.getNroLinea() + ". Regla 07: sentencia declarativa en sentencias");
															yyval = val_peek(0);
														}
break;
case 10:
//#line 61 "gramatica.y"
{
															addRegla("Linea: " + lex.getNroLinea() + ". Regla 08: sentencia ejecutable en sentencias");
															yyval = val_peek(0);
														}
break;
case 11:
//#line 67 "gramatica.y"
{
																				setearTipo((Nodo)val_peek(3).obj, ((Nodo)val_peek(1).obj).getNombre());
																				addRegla("Linea: " + lex.getNroLinea() + ". Regla 09: declaracion de variables");
																				yyval = new ParserVal (new Nodo((Nodo)val_peek(3).obj,(Nodo)val_peek(1).obj,":",null));
																				
																			}
break;
case 12:
//#line 73 "gramatica.y"
{ lex.addError("Linea " + lex.getNroLinea() + ": ERROR en declaracion, falta '.'");}
break;
case 13:
//#line 74 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en declaracion, falta ':'");}
break;
case 14:
//#line 75 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en declaracion, falta tipo");}
break;
case 15:
//#line 76 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en declaracion, falta lista de variables");}
break;
case 16:
//#line 79 "gramatica.y"
{
																	addRegla("Linea: " + lex.getNroLinea() + ". Regla 10: lista variables");
																	yyval = new ParserVal(new Nodo ((Nodo)val_peek(2).obj,((Nodo) new ParserVal(new Nodo(val_peek(0).sval, "ID")).obj),",","lista"));
																	
																}
break;
case 17:
//#line 84 "gramatica.y"
{
																	addRegla("Linea: " + lex.getNroLinea() + ". Regla 11: ID en listaVariables");
																	yyval = new ParserVal (new Nodo(val_peek(0).sval,"ID"));
																}
break;
case 18:
//#line 89 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en lista de variables, falta ','");}
break;
case 19:
//#line 92 "gramatica.y"
{
								addRegla("Linea: " + lex.getNroLinea() + ". Regla 12: UINT en tipo");
								yyval = new ParserVal (new Nodo(val_peek(0).sval,null));
							}
break;
case 20:
//#line 96 "gramatica.y"
{
								addRegla("Linea: " + lex.getNroLinea() + ". Regla 13: FLOAT en tipo");
								yyval = new ParserVal (new Nodo(val_peek(0).sval,null));
							}
break;
case 21:
//#line 102 "gramatica.y"
{
																																							addRegla("Linea: " + lex.getNroLinea() + ". Regla 14: If con sentencia else");
																																							Nodo nodoThen = new Nodo ((Nodo)val_peek(3).obj,null,"THEN",null);
																																							Nodo nodoElse = new Nodo((Nodo)val_peek(1).obj,null, "ELSE", null);
																																							Nodo nodoCuerpo = new Nodo (nodoThen,nodoElse,"Cuerpo",null);
																																							yyval = new ParserVal (new Nodo ((Nodo)val_peek(6).obj,nodoCuerpo,"IF",null));
																																						}
break;
case 22:
//#line 110 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if-else, falta bloque de sentencia");}
break;
case 23:
//#line 111 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if-else, falta '('");}
break;
case 24:
//#line 112 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if-else, falta ')'");}
break;
case 25:
//#line 113 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if-else, faltan parentesis");}
break;
case 26:
//#line 114 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if-else, falta THEN");}
break;
case 27:
//#line 115 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if-else, falta IF");}
break;
case 28:
//#line 116 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if-else, condicion mal definida");}
break;
case 29:
//#line 119 "gramatica.y"
{
																														addRegla("Linea: " + lex.getNroLinea() + ". Regla 15: if sin sentencia else");
																														yyval = new ParserVal (new Nodo((Nodo)val_peek(4).obj,(Nodo)val_peek(1).obj,"IF_SIMPLE",null));
																													}
break;
case 30:
//#line 124 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if, falta ENDIF");}
break;
case 31:
//#line 125 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if, falta THEN");}
break;
case 32:
//#line 126 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if, falta ')'");}
break;
case 33:
//#line 127 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if, falta '('");}
break;
case 34:
//#line 128 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if, faltan parentesis");}
break;
case 35:
//#line 129 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if, falta IF");}
break;
case 36:
//#line 130 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if, falta bloque de sentencias");}
break;
case 37:
//#line 133 "gramatica.y"
{
																											addRegla("Linea: " + lex.getNroLinea() + ". Regla 16: while");
																											/*Nodo nodoCondicion = new Nodo((Nodo)$3.obj,null,"CONDICION",null);*/
																											yyval = new ParserVal(new Nodo((Nodo)val_peek(3).obj,(Nodo)val_peek(0).obj,"WHILE",null)); 
																										}
break;
case 38:
//#line 139 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en while, falta WHILE");}
break;
case 39:
//#line 140 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en while, falta DO");}
break;
case 40:
//#line 141 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en while, falta '('");}
break;
case 41:
//#line 142 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en while, falta '')");}
break;
case 42:
//#line 143 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en while, faltan parentesis");}
break;
case 43:
//#line 144 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en while, falta condicion");}
break;
case 44:
//#line 146 "gramatica.y"
{
																	addRegla("Linea: " + lex.getNroLinea() + ". Regla 17: out");
																	Nodo cadena= new Nodo(val_peek(2).sval,lex.getTipo(val_peek(4).sval));
																	yyval = new ParserVal(new Nodo(cadena,null,"OUT",null));
																}
break;
case 45:
//#line 152 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en out, falta '('");}
break;
case 46:
//#line 153 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en out, falta ')'");}
break;
case 47:
//#line 154 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en out, faltan parentesis");}
break;
case 48:
//#line 155 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en out, falta '.'");;}
break;
case 49:
//#line 157 "gramatica.y"
{
																		addRegla("Linea: " + lex.getNroLinea() + ". Regla 18: let");
																		String tipo = (String)(((Nodo)val_peek(1).obj).getTipo());
																		Nodo aux = new Nodo(val_peek(3).sval,"ID");
																		if (!(lex.getTipo(aux.getNombre())).equals(tipo))
																		{																		
																			lex.setTipo(val_peek(3).sval,tipo);																		
																		}
																		yyval =  new ParserVal(new Nodo((Nodo)(new ParserVal(aux)).obj, (Nodo)val_peek(1).obj, "LET",tipo));
																	}
break;
case 50:
//#line 168 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en let, falta asignacion");}
break;
case 51:
//#line 170 "gramatica.y"
{
													addRegla("Linea: " + lex.getNroLinea() + ". Regla 19: asignacion en sentencia ejecutable ");
													yyval = val_peek(0);
												}
break;
case 52:
//#line 177 "gramatica.y"
{
																				addRegla("Linea: " + lex.getNroLinea() + ". Regla 20: condicion");
																				String tipo = (String)((Nodo)(val_peek(0).obj)).getTipo();
																				if ((((Nodo)val_peek(2).obj).getTipo()).equals(tipo))
																				{
																					yyval=  new ParserVal(new Nodo((Nodo)val_peek(2).obj, (Nodo)val_peek(0).obj, ((Nodo)val_peek(1).obj).getNombre(),tipo));
																				}
																				else
																				{
																					lex.addError("Linea " + lex.getNroLinea() + ": ERROR de tipos en comparacion");
																					yyval=  new ParserVal(new Nodo((Nodo)val_peek(2).obj, (Nodo)val_peek(0).obj, ((Nodo)val_peek(1).obj).getNombre(),tipo));
																				}
																			/*COMPARACION DEBE TERNER MISMO TIPO?*/
																			}
break;
case 53:
//#line 193 "gramatica.y"
{
														addRegla("Linea: " + lex.getNroLinea() + ". Regla 21: comparador ==");
														yyval = new ParserVal (new Nodo(val_peek(0).sval,null));
													}
break;
case 54:
//#line 197 "gramatica.y"
{
														addRegla("Linea: " + lex.getNroLinea() + ". Regla 22: comparador >=");
														yyval = new ParserVal (new Nodo(val_peek(0).sval,null));
													}
break;
case 55:
//#line 201 "gramatica.y"
{
														addRegla("Linea: " + lex.getNroLinea() + ". Regla 23: comparador <=");
														yyval = new ParserVal (new Nodo(val_peek(0).sval,null));
													}
break;
case 56:
//#line 205 "gramatica.y"
{
														addRegla("Linea: " + lex.getNroLinea() + ". Regla 24: comparador <>");
														yyval = new ParserVal (new Nodo(val_peek(0).sval,null));
													}
break;
case 57:
//#line 209 "gramatica.y"
{
														addRegla("Linea: " + lex.getNroLinea() + ". Regla 25: comparador >");
														yyval = new ParserVal (new Nodo(val_peek(0).sval,null));
													}
break;
case 58:
//#line 213 "gramatica.y"
{
														addRegla("Linea: " + lex.getNroLinea() + ". Regla 26: comparador <");
														yyval = new ParserVal (new Nodo(val_peek(0).sval,null));
													}
break;
case 59:
//#line 217 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en condicion, comparador inexistente");}
break;
case 60:
//#line 220 "gramatica.y"
{
																	if (!lex.declarada(val_peek(3).sval))
																		lex.addError("Linea " + lex.getNroLinea() + ": ERROR  variable no declarada");
																	addRegla("Linea: " + lex.getNroLinea() + ". Regla 27: asignacion");
																	String tipo = (String)(((Nodo)val_peek(1).obj).getTipo());
																	Nodo aux = new Nodo(val_peek(3).sval,lex.getTipo(val_peek(3).sval));
																	if (!(aux.getTipo()).equals(tipo))
																	{
																		lex.addError("Linea " + lex.getNroLinea() + ": ERROR de tipos en asignacion");
																	}
																	yyval =  new ParserVal(new Nodo((Nodo)(new ParserVal(aux)).obj, (Nodo)val_peek(1).obj, "=",tipo));
																}
break;
case 61:
//#line 233 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en asignacion, falta '.'");}
break;
case 62:
//#line 234 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en asignacion, falta '='");}
break;
case 63:
//#line 237 "gramatica.y"
{
																addRegla("Linea: " + lex.getNroLinea() + ". Regla 28: suma");
																 String tipo = (((Nodo)val_peek(0).obj).getTipo());
																 if ((((Nodo)val_peek(2).obj).getTipo()).equals(tipo))
																 {
																	yyval =  new ParserVal(new Nodo((Nodo)val_peek(2).obj, (Nodo)val_peek(0).obj, "+",tipo));
																 }
																 else
																 {
																	lex.addError("Linea " + lex.getNroLinea() + ": ERROR de tipos en expresion aritmetica");
																	yyval =  new ParserVal(new Nodo((Nodo)val_peek(2).obj, (Nodo)val_peek(0).obj, "+",null)); 
																 }
															}
break;
case 64:
//#line 251 "gramatica.y"
{
																	addRegla("Linea: " + lex.getNroLinea() + ". Regla 29: resta");
																	String tipo = (((Nodo)val_peek(0).obj).getTipo());
																	if ((((Nodo)val_peek(2).obj).getTipo()).equals(tipo))
																	{
																		yyval =  new ParserVal(new Nodo((Nodo)val_peek(2).obj, (Nodo)val_peek(0).obj, "-",tipo));
																	}
																	else
																	{
																		lex.addError("Linea " + lex.getNroLinea() + ": ERROR de tipos en expresion aritmetica");
																		yyval =  new ParserVal(new Nodo((Nodo)val_peek(2).obj, (Nodo)val_peek(0).obj, "-",null)); 
																	}
																}
break;
case 65:
//#line 264 "gramatica.y"
{
															addRegla("Linea: " + lex.getNroLinea() + ". Regla 30: exprecion aritmetica se division y multiplicacion");
															yyval = val_peek(0);
														}
break;
case 66:
//#line 270 "gramatica.y"
{
																addRegla("Linea: " + lex.getNroLinea() + ". Regla 31: multiplicacion");
																String tipo = (((Nodo)val_peek(0).obj).getTipo());
																if ((((Nodo)val_peek(2).obj).getTipo()).equals(tipo))
																{
																	yyval =  new ParserVal(new Nodo((Nodo)val_peek(2).obj, (Nodo)val_peek(0).obj, "*",tipo));
																}
																else
																{
																	lex.addError("Linea " + lex.getNroLinea() + ": ERROR de tipos en expresion aritmetica");
																	yyval =  new ParserVal(new Nodo((Nodo)val_peek(2).obj, (Nodo)val_peek(0).obj, "*",null)); 
																}
															}
break;
case 67:
//#line 283 "gramatica.y"
{
																addRegla("Linea: " + lex.getNroLinea() + ". Regla 32: division");
																String tipo = (((Nodo)val_peek(0).obj).getTipo());
																if ((((Nodo)val_peek(2).obj).getTipo()).equals(tipo))
																{
																	yyval =  new ParserVal(new Nodo((Nodo)val_peek(2).obj, (Nodo)val_peek(0).obj, "/",tipo));
																}
																else
																{
																	lex.addError("Linea " + lex.getNroLinea() + ": ERROR de tipos en expresion aritmetica");
																	yyval =  new ParserVal(new Nodo((Nodo)val_peek(2).obj, (Nodo)val_peek(0).obj, "/",null)); 
																}
															}
break;
case 68:
//#line 297 "gramatica.y"
{
																addRegla("Linea: " + lex.getNroLinea() + ". Regla 33: conversion");
																 yyval = new ParserVal(new Nodo ((Nodo)val_peek(1).obj,null,"UI_F","CF"));
															}
break;
case 69:
//#line 302 "gramatica.y"
{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en conversion, falta expresion aritmetica");}
break;
case 70:
//#line 304 "gramatica.y"
{
																addRegla("Linea: " + lex.getNroLinea() + ". Regla 34: termino en expArit2");
																yyval = val_peek(0);
															}
break;
case 71:
//#line 310 "gramatica.y"
{
										addRegla("Linea: " + lex.getNroLinea() + ". Regla 35: constante");
										if (lex.getTipo(val_peek(0).sval).equals("CE"))
											yyval = new ParserVal (new Nodo(val_peek(0).sval,lex.getTipo(val_peek(0).sval)));
										else 
										{
											lex.addNombreAlt(val_peek(0).sval,"@float"+contFloat);
											contFloat++;
											yyval= new ParserVal (new Nodo(val_peek(0).sval,lex.getTipo(val_peek(0).sval),lex.getNombreAlternativo(val_peek(0).sval)));
											
											
										}
									}
break;
case 72:
//#line 323 "gramatica.y"
{
										addRegla("Linea: " + lex.getNroLinea() + ". Regla 34: identificador");
										yyval = new ParserVal (new Nodo(val_peek(0).sval,lex.getTipo(val_peek(0).sval)));
										if (!lex.declarada(val_peek(0).sval))
											lex.addError("Linea " + lex.getNroLinea() + ": ERROR  variable no declarada");
									}
break;
case 73:
//#line 329 "gramatica.y"
{ 
										addRegla("Linea: " + lex.getNroLinea() + ". Regla 34: constante negativa");
										if (lex.getTipo(val_peek(0).sval).equals("CF")){
										String newName = "-"+val_peek(0).sval;
										lex.setNegativo(val_peek(0).sval);
										lex.addNombreAlt(newName,"@float"+contFloat);
										contFloat++;
										yyval= new ParserVal (new Nodo(newName,"CF",lex.getNombreAlternativo(newName)));
										}	
										else{
											lex.addError("Linea " + lex.getNroLinea() + ": ERROR  no se admiten enteros negativos");
											yyval= new ParserVal (new Nodo(val_peek(0).sval,"CE"));
										}
																			
										
									}
break;
//#line 1119 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
