#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 2 "gramatica.y"

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

#line 12 "y.tab.c"
#define IF 257
#define THEN 258
#define ELSE 259
#define ENDIF 260
#define BEGIN 261
#define END 262
#define OUT 263
#define WHILE 264
#define DO 265
#define LET 266
#define UINT 267
#define FLOAT 268
#define UI_F 269
#define ID 270
#define CTE 271
#define cadena 272
#define DOBLEI 273
#define MAYORI 274
#define MENORI 275
#define DISTINTO 276
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    0,    2,    2,    2,    2,    2,    1,    1,    1,
    1,    3,    3,    3,    3,    3,    5,    5,    5,    6,
    6,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    7,    7,    7,    7,    7,    7,    7,    8,    8,
    8,    9,    9,    9,   10,   10,   10,   10,   10,   10,
   11,   11,   11,
};
short yylen[] = {                                         2,
    1,    1,    3,    3,    3,    1,    1,    2,    2,    1,
    1,    4,    4,    3,    4,    4,    3,    1,    2,    1,
    1,    9,    8,    8,    8,    7,    8,    9,    9,    7,
    7,    6,    6,    6,    5,    7,    6,    6,    6,    6,
    5,    5,    4,    5,    4,    3,    3,    2,    2,    2,
    1,    3,    3,    3,    3,    3,    3,    1,    4,    3,
    4,    3,    3,    1,    3,    3,    4,    3,    3,    1,
    1,    1,    2,
};
short yydefred[] = {                                      0,
    0,    0,    0,    0,    0,    0,    0,    0,   10,   11,
    0,   51,    0,    0,    0,   72,   71,    0,    0,    0,
    0,    0,   70,    0,    0,    0,    0,   50,    0,   49,
    0,    0,    0,    8,    9,   20,   21,   19,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   73,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   46,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   17,   14,   16,    0,   68,    0,   69,    0,    0,    0,
    0,    0,    0,    6,    7,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   65,   66,   45,    0,    0,    0,
   43,    0,   61,   59,   15,   13,   12,    0,    0,   67,
    0,    0,    0,    0,    0,    0,    0,   35,    0,   44,
   42,    0,    0,   41,    0,   39,    0,    0,   33,   37,
    0,    0,   32,    4,    0,    3,    0,    0,   34,   40,
   38,    0,   36,    0,    0,   31,    0,   30,    0,   26,
    0,    0,    0,   25,   23,    0,   27,   24,   28,   29,
   22,
};
short yydgoto[] = {                                       7,
    8,   83,   84,   85,   11,   41,   20,   12,   21,   22,
   23,
};
short yysindex[] = {                                    144,
  -10,  -33,  -37,  -30, -215,  -53,    0,  156,    0,    0,
  118,    0, -175,  -24,  -27,    0,    0,  121, -255,  -35,
  -43,  -14,    0,   10, -241,  -21,   -9,    0,  -53,    0,
  -24,  -24,  -10,    0,    0,    0,    0,    0, -197, -231,
   11,   35,   43,  -16,   37,   46,  -32,    0, -129, -160,
  -24,  -24,  -24,  -24,  -24,  -24,  -24,  -24,  -19,  -19,
    0,   61, -149,   -3, -129, -132,   76,   93,   98,  -42,
    0,    0,    0, -209,    0,   56,    0, -118, -129, -146,
  117,  156, -134,    0,    0, -129,   15,   15,   15,   15,
   15,   15,  -14,  -14,    0,    0,    0, -129, -129, -222,
    0, -129,    0,    0,    0,    0,    0, -129, -129,    0,
 -129, -112, -111,  -99,  -64,  -46, -129,    0,  -96,    0,
    0, -129, -129,    0,  -92,    0, -108, -129,    0,    0,
 -156, -129,    0,    0,  -10,    0, -104, -129,    0,    0,
    0, -129,    0, -129, -102,    0,  -75,    0,  -91,    0,
  -87,  -86,  -83,    0,    0,  -77,    0,    0,    0,    0,
    0,
};
short yyrindex[] = {                                      0,
  187,    0,    0,    0,    0,  126,    0,  190,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -36,    1,    0,   67,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   79,    0,    0,    0,    0,    0,   94,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   48,   82,   90,  113,
  124,  137,   23,   45,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  106,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
short yygindex[] = {                                      0,
   32,  319,   14,   27,    0,   -2,   36,  189,   21,  114,
  120,
};
#define YYTABLESIZE 466
short yytable[] = {                                      57,
   64,   58,   25,  107,   58,   50,   18,   32,   80,   26,
   42,   19,   44,    9,   19,   48,   56,   19,   55,   63,
   19,   34,   62,   19,   75,   19,   10,   59,   19,   14,
   62,   66,   60,  122,   35,   45,   70,  100,   71,   27,
   28,   64,  123,   64,   63,   64,   64,   13,  108,   43,
   61,   67,   68,   47,   29,  109,   72,   57,   69,   58,
   64,   64,   64,   62,   76,   62,   48,   62,   62,   36,
   37,   87,   88,   89,   90,   91,   92,   77,   47,   57,
   73,   58,   62,   74,   62,   63,   78,   63,   52,   63,
   63,   36,   37,   60,    9,    9,  110,   86,   57,  146,
   58,   97,  147,  148,   63,    5,   63,   10,   10,   81,
    2,  113,  115,  116,   82,   98,    3,    4,   57,    5,
   58,  103,   53,    6,  117,  118,   81,    2,   34,   34,
   54,   82,  102,    3,    4,   57,    5,   58,  104,  111,
    6,   35,   35,  105,   81,    2,  128,  129,  130,   82,
  144,    3,    4,   55,    5,  150,   14,  154,    6,  132,
  133,   40,  138,  139,   56,   19,  142,  143,  157,   18,
   93,   94,  158,  159,   13,   39,  160,   57,   95,   96,
   81,    2,  161,   18,  155,   82,    2,    3,    4,    1,
    5,   33,    2,   30,    6,    0,    0,  134,    3,    4,
    0,    5,   31,    0,    0,    6,    0,    0,    0,  135,
    2,    0,    0,  106,    0,  136,    3,    4,    0,    5,
    0,   58,   49,    6,    0,   79,    0,    0,   58,   51,
   52,   53,   54,    0,   24,   15,   16,   17,   15,   16,
   17,   15,   16,   17,   15,   16,   17,   15,   16,   17,
   16,   17,   15,   16,   17,   65,   64,   64,   64,   64,
   64,   99,   64,   64,   64,   64,   64,    0,    0,    0,
   64,    0,    0,   64,   64,   64,   64,    0,   62,   62,
   62,   62,   62,    0,   62,   62,   62,   62,   62,    0,
    0,    0,   62,    0,    0,   62,   62,   62,   62,    0,
   63,   63,   63,   63,   63,   52,   63,   63,   63,   63,
   63,    0,   52,    0,   63,    0,    0,   63,   63,   63,
   63,    0,   48,   48,    0,   48,   48,    0,   48,   48,
   48,    0,   48,    0,   47,   47,   48,   47,   47,   53,
   47,   47,   47,    0,   47,    0,   53,   54,   47,   60,
   60,    0,   60,   60,   54,   60,   60,   60,    0,   60,
    0,    5,    5,   60,    5,    5,    0,    5,    5,    5,
   55,    5,   33,    2,    0,    5,   46,   55,    0,    3,
    4,   56,    5,  101,   36,   37,    6,   38,   56,   15,
   16,   17,   18,   18,   57,   18,    0,  112,  114,    1,
    2,   57,    0,    0,  119,    0,    3,    4,    0,    5,
    0,   33,    2,    6,    0,    0,  120,  121,    3,    4,
  124,    5,    0,    0,    0,    6,  125,  126,    0,  127,
    0,  131,    0,    0,    0,  137,    0,    0,    0,    0,
  140,  141,    0,    0,    0,    0,  145,    0,    0,    0,
  149,    0,    0,    0,    0,    0,  151,    0,    0,    0,
  152,    0,  153,    0,    0,  156,
};
short yycheck[] = {                                      43,
    0,   45,   40,   46,   41,   41,   40,   61,   41,   40,
   13,   45,   40,    0,   45,  271,   60,   45,   62,   41,
   45,    8,    0,   45,   41,   45,    0,   42,   45,   40,
  272,   41,   47,  256,    8,   15,   39,   41,  270,    4,
  256,   41,  265,   43,    0,   45,   46,   58,  258,   14,
   41,   31,   32,   18,  270,  265,   46,   43,  256,   45,
   60,   26,   62,   41,   44,   43,    0,   45,   46,  267,
  268,   51,   52,   53,   54,   55,   56,   41,    0,   43,
   46,   45,   60,   41,   62,   41,   41,   43,   41,   45,
   46,  267,  268,    0,   81,   82,   41,  258,   43,  256,
   45,   41,  259,  260,   60,    0,   62,   81,   82,  256,
  257,  258,   81,   82,  261,  265,  263,  264,   43,  266,
   45,   46,   41,  270,  259,  260,  256,  257,  115,  116,
   41,  261,  265,  263,  264,   43,  266,   45,   46,  258,
  270,  115,  116,   46,  256,  257,  259,  260,  260,  261,
  259,  263,  264,   41,  266,  260,   40,  260,  270,  259,
  260,   44,  259,  260,   41,   45,  259,  260,  260,   44,
   57,   58,  260,  260,   58,   58,  260,   41,   59,   60,
  256,  257,  260,   58,  260,  261,    0,  263,  264,    0,
  266,  256,  257,    5,  270,   -1,   -1,  262,  263,  264,
   -1,  266,  256,   -1,   -1,  270,   -1,   -1,   -1,  256,
  257,   -1,   -1,  256,   -1,  262,  263,  264,   -1,  266,
   -1,  258,  258,  270,   -1,  258,   -1,   -1,  265,  273,
  274,  275,  276,   -1,  272,  269,  270,  271,  269,  270,
  271,  269,  270,  271,  269,  270,  271,  269,  270,  271,
  270,  271,  269,  270,  271,  265,  256,  257,  258,  259,
  260,  265,  262,  263,  264,  265,  266,   -1,   -1,   -1,
  270,   -1,   -1,  273,  274,  275,  276,   -1,  256,  257,
  258,  259,  260,   -1,  262,  263,  264,  265,  266,   -1,
   -1,   -1,  270,   -1,   -1,  273,  274,  275,  276,   -1,
  256,  257,  258,  259,  260,  258,  262,  263,  264,  265,
  266,   -1,  265,   -1,  270,   -1,   -1,  273,  274,  275,
  276,   -1,  256,  257,   -1,  259,  260,   -1,  262,  263,
  264,   -1,  266,   -1,  256,  257,  270,  259,  260,  258,
  262,  263,  264,   -1,  266,   -1,  265,  258,  270,  256,
  257,   -1,  259,  260,  265,  262,  263,  264,   -1,  266,
   -1,  256,  257,  270,  259,  260,   -1,  262,  263,  264,
  258,  266,  256,  257,   -1,  270,  256,  265,   -1,  263,
  264,  258,  266,   65,  267,  268,  270,  270,  265,  269,
  270,  271,  267,  268,  258,  270,   -1,   79,   80,  256,
  257,  265,   -1,   -1,   86,   -1,  263,  264,   -1,  266,
   -1,  256,  257,  270,   -1,   -1,   98,   99,  263,  264,
  102,  266,   -1,   -1,   -1,  270,  108,  109,   -1,  111,
   -1,  113,   -1,   -1,   -1,  117,   -1,   -1,   -1,   -1,
  122,  123,   -1,   -1,   -1,   -1,  128,   -1,   -1,   -1,
  132,   -1,   -1,   -1,   -1,   -1,  138,   -1,   -1,   -1,
  142,   -1,  144,   -1,   -1,  147,
};
#define YYFINAL 7
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 276
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,"'('","')'","'*'","'+'","','","'-'","'.'","'/'",0,0,0,0,0,0,0,0,0,0,
"':'",0,"'<'","'='","'>'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,"IF","THEN","ELSE","ENDIF","BEGIN","END","OUT","WHILE","DO","LET",
"UINT","FLOAT","UI_F","ID","CTE","cadena","DOBLEI","MAYORI","MENORI","DISTINTO",
};
char *yyrule[] = {
"$accept : programa",
"programa : sentencia",
"programa : error",
"bloqueSentencias : BEGIN sentencia END",
"bloqueSentencias : error sentencia END",
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
"ejecutable : OUT '(' cadena ')'",
"ejecutable : OUT cadena ')'",
"ejecutable : OUT '(' cadena",
"ejecutable : OUT cadena",
"ejecutable : LET asignacion",
"ejecutable : LET error",
"ejecutable : asignacion",
"condicion : expArit1 DOBLEI expArit1",
"condicion : expArit1 MAYORI expArit1",
"condicion : expArit1 MENORI expArit1",
"condicion : expArit1 DISTINTO expArit1",
"condicion : expArit1 '>' expArit1",
"condicion : expArit1 '<' expArit1",
"condicion : expArit1",
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
"expArit2 : UI_F expArit1 ')'",
"expArit2 : termino",
"termino : CTE",
"termino : ID",
"termino : '-' CTE",
};
#endif
#ifndef YYSTYPE
typedef int YYSTYPE;
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 128 "gramatica.y"

private Lexico lex;

public Parser(Lexico lex){
	this.lex = lex;
}

private void yyerror(String string) {
	
}

int yylex()
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
#line 364 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 2:
#line 14 "gramatica.y"
{System.out.println("ERROR en el programa en linea: "  + lex.getNroLinea());}
break;
case 3:
#line 17 "gramatica.y"
{System.out.println("bloque de sentencias con begin end en linea: "  + lex.getNroLinea());}
break;
case 4:
#line 18 "gramatica.y"
{System.out.println("ERROR en bloque de sentencias en linea: "  + lex.getNroLinea());}
break;
case 5:
#line 19 "gramatica.y"
{System.out.println("ERROR en bloque de sentencias en linea: "  + lex.getNroLinea());}
break;
case 6:
#line 20 "gramatica.y"
{System.out.println("bloques en declarativaen linea: "  + lex.getNroLinea());}
break;
case 7:
#line 21 "gramatica.y"
{System.out.println("bloques en ejecutable en linea: "  + lex.getNroLinea());}
break;
case 8:
#line 24 "gramatica.y"
{System.out.println("sentencia declarativa RECURSIVA en linea: "  + lex.getNroLinea());}
break;
case 9:
#line 25 "gramatica.y"
{System.out.println("sentencia ejecutable RECURSIVA en linea: "  + lex.getNroLinea());}
break;
case 10:
#line 26 "gramatica.y"
{System.out.println("sentencia declarativa en linea: "  + lex.getNroLinea());}
break;
case 11:
#line 27 "gramatica.y"
{System.out.println("sentencia ejecutable en linea: "  + lex.getNroLinea());}
break;
case 12:
#line 30 "gramatica.y"
{System.out.println("declaracion variables en linea: "  + lex.getNroLinea());}
break;
case 13:
#line 31 "gramatica.y"
{System.out.println("Falta '.' en declaracion de variables en linea: "  + lex.getNroLinea());}
break;
case 14:
#line 32 "gramatica.y"
{System.out.println("ERROR en declaracion. Falta ':' en linea: "  + lex.getNroLinea());}
break;
case 15:
#line 33 "gramatica.y"
{System.out.println("ERROR en declaracion. Falta el tipo en linea: "  + lex.getNroLinea());}
break;
case 16:
#line 34 "gramatica.y"
{System.out.println("ERROR en declaracion. Falta la lista de variables en linea: "  + lex.getNroLinea());}
break;
case 17:
#line 37 "gramatica.y"
{System.out.println("listvar varias  variables en linea: "  + lex.getNroLinea());}
break;
case 18:
#line 38 "gramatica.y"
{System.out.println("listvar id en linea: "  + lex.getNroLinea());}
break;
case 19:
#line 39 "gramatica.y"
{System.out.println("ERROR en lista declaractiva de variables. Falta ',' en linea: " + lex.getNroLinea());}
break;
case 22:
#line 46 "gramatica.y"
{System.out.println("ejecutable if completo en linea: "  + lex.getNroLinea());}
break;
case 23:
#line 48 "gramatica.y"
{System.out.println("ERROR en if completo. Falta bloque de sentencias en linea: "  + lex.getNroLinea());}
break;
case 24:
#line 49 "gramatica.y"
{System.out.println("ERROR en if completo. Falta '(' en linea: "  + lex.getNroLinea());}
break;
case 25:
#line 50 "gramatica.y"
{System.out.println("ERROR en if completo. Falta ')' en linea: "  + lex.getNroLinea());}
break;
case 26:
#line 51 "gramatica.y"
{System.out.println("ERROR en if completo. Faltan parentesis en linea: "  + lex.getNroLinea());}
break;
case 27:
#line 52 "gramatica.y"
{System.out.println("ERROR en if completo. Falta THEN en linea: "  + lex.getNroLinea());}
break;
case 28:
#line 53 "gramatica.y"
{System.out.println("ERROR en if completo. Falta IF en linea: "  + lex.getNroLinea());}
break;
case 29:
#line 54 "gramatica.y"
{System.out.println("ERROR en if completo. Condicion mal definida: "  + lex.getNroLinea());}
break;
case 30:
#line 57 "gramatica.y"
{System.out.println("if sin else en linea: "  + lex.getNroLinea());}
break;
case 31:
#line 59 "gramatica.y"
{System.out.println("ERROR en if sin else. Falta ENDIF en linea: "  + lex.getNroLinea());}
break;
case 32:
#line 60 "gramatica.y"
{System.out.println("ERROR en if sin else. Falta THEN en linea: "  + lex.getNroLinea());}
break;
case 33:
#line 61 "gramatica.y"
{System.out.println("ERROR en if sin else. Falta ')' en linea: "  + lex.getNroLinea());}
break;
case 34:
#line 62 "gramatica.y"
{System.out.println("ERROR en if sin else. Falta '(' en linea: "  + lex.getNroLinea());}
break;
case 35:
#line 63 "gramatica.y"
{System.out.println("ERROR en if sin else. Faltan parentesis en linea: "  + lex.getNroLinea());}
break;
case 36:
#line 64 "gramatica.y"
{System.out.println("ERROR en if sin else. Falta IF en linea: "  + lex.getNroLinea());}
break;
case 37:
#line 65 "gramatica.y"
{System.out.println("ERROR en if sin else. Falta bloque de sentencias en linea: "  + lex.getNroLinea());}
break;
case 38:
#line 68 "gramatica.y"
{System.out.println("while en linea: "  + lex.getNroLinea());}
break;
case 39:
#line 70 "gramatica.y"
{System.out.println("ERROR en while. Falta WHILE en linea: "  + lex.getNroLinea());}
break;
case 40:
#line 71 "gramatica.y"
{System.out.println("ERROR en while. Falta DO en linea: "  + lex.getNroLinea());}
break;
case 41:
#line 72 "gramatica.y"
{System.out.println("ERROR en while. Falta '(' en linea: "  + lex.getNroLinea());}
break;
case 42:
#line 73 "gramatica.y"
{System.out.println("ERROR en while. Falta ')' en linea: "  + lex.getNroLinea());}
break;
case 43:
#line 74 "gramatica.y"
{System.out.println("ERROR en while. Faltan parentesis en linea: "  + lex.getNroLinea());}
break;
case 44:
#line 75 "gramatica.y"
{System.out.println("ERROR en while. Falta condicion en linea: "  + lex.getNroLinea());}
break;
case 45:
#line 77 "gramatica.y"
{System.out.println("out en linea: "  + lex.getNroLinea());}
break;
case 46:
#line 79 "gramatica.y"
{System.out.println("ERROR en out. Falta '(' en linea: "  + lex.getNroLinea());}
break;
case 47:
#line 80 "gramatica.y"
{System.out.println("ERROR en out. Falta ')' en linea: "  + lex.getNroLinea());}
break;
case 48:
#line 81 "gramatica.y"
{System.out.println("ERROR en out. Falta '(' y ')' en linea: "  + lex.getNroLinea());}
break;
case 49:
#line 83 "gramatica.y"
{System.out.println("let en linea: "  + lex.getNroLinea());}
break;
case 50:
#line 85 "gramatica.y"
{System.out.println("ERROR en let. Se espera asignacion en linea: "  + lex.getNroLinea());}
break;
case 51:
#line 87 "gramatica.y"
{System.out.println("asignacion en linea: "  + lex.getNroLinea());}
break;
case 52:
#line 90 "gramatica.y"
{System.out.println("condicion == en linea: "  + lex.getNroLinea());}
break;
case 53:
#line 91 "gramatica.y"
{System.out.println("condicion >= en linea: "  + lex.getNroLinea());}
break;
case 54:
#line 92 "gramatica.y"
{System.out.println("condicion <= en linea: "  + lex.getNroLinea());}
break;
case 55:
#line 93 "gramatica.y"
{System.out.println("condicion <> en linea: "  + lex.getNroLinea());}
break;
case 56:
#line 94 "gramatica.y"
{System.out.println("condicion > en linea: "  + lex.getNroLinea());}
break;
case 57:
#line 95 "gramatica.y"
{System.out.println("condicion < en linea: "  + lex.getNroLinea());}
break;
case 58:
#line 96 "gramatica.y"
{System.out.println("ERROR en condicion en linea: "  + lex.getNroLinea());}
break;
case 60:
#line 100 "gramatica.y"
{System.out.println("ERROR en asignacion. Falta '.' en linea: " + lex.getNroLinea());}
break;
case 61:
#line 101 "gramatica.y"
{System.out.println("ERROR en asignacion. Falta '=' en linea: " + lex.getNroLinea());}
break;
case 62:
#line 104 "gramatica.y"
{System.out.println("exp arit + en linea: "  + lex.getNroLinea());}
break;
case 63:
#line 105 "gramatica.y"
{System.out.println("exp arit + en linea: "  + lex.getNroLinea());}
break;
case 64:
#line 106 "gramatica.y"
{System.out.println("exp arit divmult en linea: "  + lex.getNroLinea());}
break;
case 65:
#line 109 "gramatica.y"
{System.out.println("exp arit * en linea: "  + lex.getNroLinea());}
break;
case 66:
#line 110 "gramatica.y"
{System.out.println("exp arit / en linea: "  + lex.getNroLinea());}
break;
case 67:
#line 112 "gramatica.y"
{System.out.println("exp arit ui_f en linea: "  + lex.getNroLinea());}
break;
case 68:
#line 114 "gramatica.y"
{System.out.println("ERROR en conversion ui_f. Falta expresion aritmetica en linea: "  + lex.getNroLinea());}
break;
case 69:
#line 115 "gramatica.y"
{System.out.println("ERROR en conversion ui_f. Falta parentesis en linea: "  + lex.getNroLinea());}
break;
case 70:
#line 117 "gramatica.y"
{System.out.println("exp arit termino en linea: "  + lex.getNroLinea());}
break;
case 73:
#line 122 "gramatica.y"
{	lex.setNegativo(yyvsp[0].sval);
						System.out.println("cte id -cte en linea: "  + lex.getNroLinea());}
break;
#line 773 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
