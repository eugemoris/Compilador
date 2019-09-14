%{

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

%}

%token IF THEN ELSE ENDIF BEGIN END OUT WHILE DO LET UINT FLOAT UI_F ID CTE cadena DOBLEI MAYORI MENORI DISTINTO

%%

programa : 	  sentencia		{
												 addRegla("Linea: " + lex.getNroLinea() + ". Regla 01: sentencias en programa");
												 $$ = $1;
												cargarArbol((Nodo)$$.obj);
												if (!lex.hayError())
												{
													
													GeneracionCodigo gc = new GeneracionCodigo((Nodo)$$.obj,lex.getTablaSimbolos(),contFloat);
													gc.generarCodigo();
												}
												
												}
												
						| error 			{ lex.addError("Linea " + lex.getNroLinea() + ": ERROR en el programa"); }
;

bloqueSentencias :   BEGIN sentencia END 	{
																					addRegla("Linea: " + lex.getNroLinea() + ". Regla 02: bloque de sentencia con begin y end");
																					$$ = $2; 
																				}
											
									| BEGIN sentencia error 	{ lex.addError("Linea " + lex.getNroLinea() + ": ERROR en bloque de sentencias, falta END"); }
				 
									| declarativa 					{
																					addRegla("Linea: " + lex.getNroLinea() + ". Regla 03: sentencia declarativa en bloque de sentencia");
																					$$ = $1;	
																				}	
									| ejecutable 						{
																					addRegla("Linea: " + lex.getNroLinea() + ". Regla 44: sentencia ejecutable en bloque de sentencia");
																					$$ = $1;
																				}
;

sentencia : sentencia declarativa 	{
																addRegla("Linea: " + lex.getNroLinea() + ". Regla 05: agregar sentencia declarativa a las sentencias");
																$$ = new ParserVal(new Nodo((Nodo)$1.obj, (Nodo)$2.obj, "declarativa",null));
															}
					| sentencia ejecutable {
																addRegla("Linea: " + lex.getNroLinea() + ". Regla 06: agregar sentencia ejecutable a las sentencias");
																 $$ = new ParserVal(new Nodo((Nodo)$1.obj, (Nodo)$2.obj, "ejecutable",null));
															}
					| declarativa 				{
															addRegla("Linea: " + lex.getNroLinea() + ". Regla 07: sentencia declarativa en sentencias");
															$$ = $1;
														}	
					| ejecutable 				{
															addRegla("Linea: " + lex.getNroLinea() + ". Regla 08: sentencia ejecutable en sentencias");
															$$ = $1;
														}
;

declarativa : 	 listaVariables ':' tipo '.' 	{
																				setearTipo((Nodo)$1.obj, ((Nodo)$3.obj).getNombre());
																				addRegla("Linea: " + lex.getNroLinea() + ". Regla 09: declaracion de variables");
																				$$ = new ParserVal (new Nodo((Nodo)$1.obj,(Nodo)$3.obj,":",null));
																				
																			}
						| listaVariables ':' tipo error 	{ lex.addError("Linea " + lex.getNroLinea() + ": ERROR en declaracion, falta '.'");}
						| listaVariables tipo '.' 				{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en declaracion, falta ':'");}
						| listaVariables ':' error '.' 		{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en declaracion, falta tipo");}
						| error ':' tipo '.' 						{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en declaracion, falta lista de variables");}
;

listaVariables : 	listaVariables ',' ID 	{
																	addRegla("Linea: " + lex.getNroLinea() + ". Regla 10: lista variables");
																	$$ = new ParserVal(new Nodo ((Nodo)$1.obj,((Nodo) new ParserVal(new Nodo($3.sval, "ID")).obj),",","lista"));
																	
																}
							| ID 							{
																	addRegla("Linea: " + lex.getNroLinea() + ". Regla 11: ID en listaVariables");
																	$$ = new ParserVal (new Nodo($1.sval,"ID"));
																}
										
							| listaVariables ID 	{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en lista de variables, falta ','");}
;

tipo : 	UINT 		{
								addRegla("Linea: " + lex.getNroLinea() + ". Regla 12: UINT en tipo");
								$$ = new ParserVal (new Nodo($1.sval,null));
							}
			|FLOAT	{
								addRegla("Linea: " + lex.getNroLinea() + ". Regla 13: FLOAT en tipo");
								$$ = new ParserVal (new Nodo($1.sval,null));
							}
;

ejecutable :  	IF '(' condicion ')' THEN bloqueSentencias ELSE bloqueSentencias ENDIF 	{
																																							addRegla("Linea: " + lex.getNroLinea() + ". Regla 14: If con sentencia else");
																																							Nodo nodoThen = new Nodo ((Nodo)$6.obj,null,"THEN",null);
																																							Nodo nodoElse = new Nodo((Nodo)$8.obj,null, "ELSE", null);
																																							Nodo nodoCuerpo = new Nodo (nodoThen,nodoElse,"Cuerpo",null);
																																							$$ = new ParserVal (new Nodo ((Nodo)$3.obj,nodoCuerpo,"IF",null));
																																						}
			
						| IF '(' condicion ')' THEN bloqueSentencias ELSE ENDIF 										{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if-else, falta bloque de sentencia");}
						| IF condicion ')' THEN bloqueSentencias ELSE bloqueSentencias ENDIF				{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if-else, falta '('");}
						| IF '(' condicion THEN bloqueSentencias ELSE bloqueSentencias ENDIF 			{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if-else, falta ')'");}
						| IF condicion THEN bloqueSentencias ELSE bloqueSentencias ENDIF 				{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if-else, faltan parentesis");}
						| IF '(' condicion ')' bloqueSentencias ELSE bloqueSentencias ENDIF 					{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if-else, falta THEN");}
						| error '(' condicion ')' THEN bloqueSentencias ELSE bloqueSentencias ENDIF 	{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if-else, falta IF");}
						| IF '(' error ')' THEN bloqueSentencias ELSE bloqueSentencias ENDIF 				{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if-else, condicion mal definida");}
			
			
						| IF '(' condicion ')' THEN bloqueSentencias ENDIF 		{
																														addRegla("Linea: " + lex.getNroLinea() + ". Regla 15: if sin sentencia else");
																														$$ = new ParserVal (new Nodo((Nodo)$3.obj,(Nodo)$6.obj,"IF_SIMPLE",null));
																													}
						
						| IF '(' condicion ')' THEN bloqueSentencias error			{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if, falta ENDIF");}
						| IF '(' condicion ')' bloqueSentencias ENDIF 					{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if, falta THEN");}			
						| IF '(' condicion THEN bloqueSentencias ENDIF 				{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if, falta ')'");}
						| IF condicion ')' THEN bloqueSentencias ENDIF 				{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if, falta '('");}
						| IF condicion THEN bloqueSentencias ENDIF 					{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if, faltan parentesis");}
						| error '(' condicion ')' THEN bloqueSentencias ENDIF 	{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if, falta IF");}
						| IF '(' condicion ')' THEN ENDIF											{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en if, falta bloque de sentencias");}
			
			
						| WHILE '('condicion ')' DO bloqueSentencias 	{
																											addRegla("Linea: " + lex.getNroLinea() + ". Regla 16: while");
																											//Nodo nodoCondicion = new Nodo((Nodo)$3.obj,null,"CONDICION",null);
																											$$ = new ParserVal(new Nodo((Nodo)$3.obj,(Nodo)$6.obj,"WHILE",null)); 
																										}
			
						| error '('condicion ')' DO bloqueSentencias 		{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en while, falta WHILE");}
						| WHILE '('condicion ')' error bloqueSentencias 	{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en while, falta DO");}
						| WHILE condicion ')' DO bloqueSentencias 		{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en while, falta '('");}
						| WHILE '('condicion DO bloqueSentencias 			{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en while, falta '')");}
						| WHILE condicion DO bloqueSentencias 				{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en while, faltan parentesis");}
						| WHILE '(' ')' DO bloqueSentencias 						{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en while, falta condicion");}
						
						| OUT '(' cadena ')' '.' 	{
																	addRegla("Linea: " + lex.getNroLinea() + ". Regla 17: out");
																	Nodo cadena= new Nodo($3.sval,lex.getTipo($1.sval));
																	$$ = new ParserVal(new Nodo(cadena,null,"OUT",null));
																}
			
						| OUT cadena ')' '.'		{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en out, falta '('");}
						| OUT '(' cadena '.'		{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en out, falta ')'");}
						| OUT cadena '.'			{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en out, faltan parentesis");}
						| OUT '('cadena')'		{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en out, falta '.'");;}
			
						| LET ID '=' expArit1 '.'	{
																		addRegla("Linea: " + lex.getNroLinea() + ". Regla 18: let");
																		String tipo = (String)(((Nodo)$4.obj).getTipo());
																		Nodo aux = new Nodo($2.sval,"ID");
																		if (!(lex.getTipo(aux.getNombre())).equals(tipo))
																		{																		
																			lex.setTipo($2.sval,tipo);																		
																		}
																		$$ =  new ParserVal(new Nodo((Nodo)(new ParserVal(aux)).obj, (Nodo)$4.obj, "LET",tipo));
																	}
										
						| LET error			{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en let, falta asignacion");}
			
						| asignacion	{
													addRegla("Linea: " + lex.getNroLinea() + ". Regla 19: asignacion en sentencia ejecutable ");
													$$ = $1;
												}
;


condicion :   expArit1 comparador expArit1 	{
																				addRegla("Linea: " + lex.getNroLinea() + ". Regla 20: condicion");
																				String tipo = (String)((Nodo)($3.obj)).getTipo();
																				if ((((Nodo)$1.obj).getTipo()).equals(tipo))
																				{
																					$$=  new ParserVal(new Nodo((Nodo)$1.obj, (Nodo)$3.obj, ((Nodo)$2.obj).getNombre(),tipo));
																				}
																				else
																				{
																					lex.addError("Linea " + lex.getNroLinea() + ": ERROR de tipos en comparacion");
																					$$=  new ParserVal(new Nodo((Nodo)$1.obj, (Nodo)$3.obj, ((Nodo)$2.obj).getNombre(),tipo));
																				}
																			//COMPARACION DEBE TERNER MISMO TIPO?
																			}	
;

comparador:   	DOBLEI 			{
														addRegla("Linea: " + lex.getNroLinea() + ". Regla 21: comparador ==");
														$$ = new ParserVal (new Nodo($1.sval,null));
													}
							| MAYORI  		{
														addRegla("Linea: " + lex.getNroLinea() + ". Regla 22: comparador >=");
														$$ = new ParserVal (new Nodo($1.sval,null));
													}
							| MENORI 		{
														addRegla("Linea: " + lex.getNroLinea() + ". Regla 23: comparador <=");
														$$ = new ParserVal (new Nodo($1.sval,null));
													}
							| DISTINTO	{
														addRegla("Linea: " + lex.getNroLinea() + ". Regla 24: comparador <>");
														$$ = new ParserVal (new Nodo($1.sval,null));
													}
							| '>' 				{
														addRegla("Linea: " + lex.getNroLinea() + ". Regla 25: comparador >");
														$$ = new ParserVal (new Nodo($1.sval,null));
													}
							| '<'					{
														addRegla("Linea: " + lex.getNroLinea() + ". Regla 26: comparador <");
														$$ = new ParserVal (new Nodo($1.sval,null));
													}
							| error			{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en condicion, comparador inexistente");}
;

asignacion : 	ID '=' expArit1 '.'			{
																	if (!lex.declarada($1.sval))
																		lex.addError("Linea " + lex.getNroLinea() + ": ERROR  variable no declarada");
																	addRegla("Linea: " + lex.getNroLinea() + ". Regla 27: asignacion");
																	String tipo = (String)(((Nodo)$3.obj).getTipo());
																	Nodo aux = new Nodo($1.sval,lex.getTipo($1.sval));
																	if (!(aux.getTipo()).equals(tipo))
																	{
																		lex.addError("Linea " + lex.getNroLinea() + ": ERROR de tipos en asignacion");
																	}
																	$$ =  new ParserVal(new Nodo((Nodo)(new ParserVal(aux)).obj, (Nodo)$3.obj, "=",tipo));
																}
																
						| ID '=' expArit1			{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en asignacion, falta '.'");}
						| ID error expArit1 '.'   {lex.addError("Linea " + lex.getNroLinea() + ": ERROR en asignacion, falta '='");}
;

expArit1 : 	 expArit1 '+' expArit2 	{
																addRegla("Linea: " + lex.getNroLinea() + ". Regla 28: suma");
																 String tipo = (((Nodo)$3.obj).getTipo());
																 if ((((Nodo)$1.obj).getTipo()).equals(tipo))
																 {
																	$$ =  new ParserVal(new Nodo((Nodo)$1.obj, (Nodo)$3.obj, "+",tipo));
																 }
																 else
																 {
																	lex.addError("Linea " + lex.getNroLinea() + ": ERROR de tipos en expresion aritmetica");
																	$$ =  new ParserVal(new Nodo((Nodo)$1.obj, (Nodo)$3.obj, "+",null)); 
																 }
															}
									
					| expArit1 '-' expArit2 	{
																	addRegla("Linea: " + lex.getNroLinea() + ". Regla 29: resta");
																	String tipo = (((Nodo)$3.obj).getTipo());
																	if ((((Nodo)$1.obj).getTipo()).equals(tipo))
																	{
																		$$ =  new ParserVal(new Nodo((Nodo)$1.obj, (Nodo)$3.obj, "-",tipo));
																	}
																	else
																	{
																		lex.addError("Linea " + lex.getNroLinea() + ": ERROR de tipos en expresion aritmetica");
																		$$ =  new ParserVal(new Nodo((Nodo)$1.obj, (Nodo)$3.obj, "-",null)); 
																	}
																}
					| expArit2 					{
															addRegla("Linea: " + lex.getNroLinea() + ". Regla 30: exprecion aritmetica se division y multiplicacion");
															$$ = $1;
														}  
;

expArit2 :    expArit2 '*' termino 	{
																addRegla("Linea: " + lex.getNroLinea() + ". Regla 31: multiplicacion");
																String tipo = (((Nodo)$3.obj).getTipo());
																if ((((Nodo)$1.obj).getTipo()).equals(tipo))
																{
																	$$ =  new ParserVal(new Nodo((Nodo)$1.obj, (Nodo)$3.obj, "*",tipo));
																}
																else
																{
																	lex.addError("Linea " + lex.getNroLinea() + ": ERROR de tipos en expresion aritmetica");
																	$$ =  new ParserVal(new Nodo((Nodo)$1.obj, (Nodo)$3.obj, "*",null)); 
																}
															}
					| expArit2 '/' termino 	{
																addRegla("Linea: " + lex.getNroLinea() + ". Regla 32: division");
																String tipo = (((Nodo)$3.obj).getTipo());
																if ((((Nodo)$1.obj).getTipo()).equals(tipo))
																{
																	$$ =  new ParserVal(new Nodo((Nodo)$1.obj, (Nodo)$3.obj, "/",tipo));
																}
																else
																{
																	lex.addError("Linea " + lex.getNroLinea() + ": ERROR de tipos en expresion aritmetica");
																	$$ =  new ParserVal(new Nodo((Nodo)$1.obj, (Nodo)$3.obj, "/",null)); 
																}
															}
			
					| UI_F '(' expArit1 ')'	{
																addRegla("Linea: " + lex.getNroLinea() + ". Regla 33: conversion");
																 $$ = new ParserVal(new Nodo ((Nodo)$3.obj,null,"UI_F","CF"));
															}
			
					| UI_F '('  ')' 					{lex.addError("Linea " + lex.getNroLinea() + ": ERROR en conversion, falta expresion aritmetica");}
						
					| termino 						{
																addRegla("Linea: " + lex.getNroLinea() + ". Regla 34: termino en expArit2");
																$$ = $1;
															}
;
	
termino : 	CTE 		{
										addRegla("Linea: " + lex.getNroLinea() + ". Regla 35: constante");
										if (lex.getTipo($1.sval).equals("CE"))
											$$ = new ParserVal (new Nodo($1.sval,lex.getTipo($1.sval)));
										else 
										{
											lex.addNombreAlt($1.sval,"@float"+contFloat);
											contFloat++;
											$$= new ParserVal (new Nodo($1.sval,lex.getTipo($1.sval),lex.getNombreAlternativo($1.sval)));
											
											
										}
									}
					| ID			{
										addRegla("Linea: " + lex.getNroLinea() + ". Regla 34: identificador");
										$$ = new ParserVal (new Nodo($1.sval,lex.getTipo($1.sval)));
										if (!lex.declarada($1.sval))
											lex.addError("Linea " + lex.getNroLinea() + ": ERROR  variable no declarada");
									}	
					| '-' CTE 	{ 
										addRegla("Linea: " + lex.getNroLinea() + ". Regla 34: constante negativa");
										if (lex.getTipo($2.sval).equals("CF")){
										String newName = "-"+$2.sval;
										lex.setNegativo($2.sval);
										lex.addNombreAlt(newName,"@float"+contFloat);
										contFloat++;
										$$= new ParserVal (new Nodo(newName,"CF",lex.getNombreAlternativo(newName)));
										}	
										else{
											lex.addError("Linea " + lex.getNroLinea() + ": ERROR  no se admiten enteros negativos");
											$$= new ParserVal (new Nodo($2.sval,"CE"));
										}
																			
										
									}
;


%%

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