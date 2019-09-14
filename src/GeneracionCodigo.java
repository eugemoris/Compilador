
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
//import sun.nio.ch.FileKey;

public class GeneracionCodigo {
    private Nodo arbol;
    private Map<String, ArrayList<Object>> tablaSimbolos;
    private ArrayList<String> codigo;
    //private ArrayList<String> declaraciones;
    private ArrayList<String> tipoAux;
    private Stack<Integer> labels= new Stack<>();
    private boolean[] registros= {false,false,false,false};
    private int cont = -1;//REVISAR
    private int contLabel = 1;
    private int contMensaje = 0;
    private int contFloat = 0;
     
    public GeneracionCodigo(Nodo arbol, Map<String, ArrayList<Object>> tabla,int contFloat) {
        this.arbol = arbol;
        //System.out.println("GeneracionCodigo.<init>()");
        tablaSimbolos = tabla;
        //declaraciones = new ArrayList<>();
        codigo = new ArrayList<>();
        tipoAux = new ArrayList<>();
        this.contFloat = contFloat; 
        labels.push(1);
    }
    
    public void generarCodigo(){
        Nodo padre = arbol;
        
        while ((padre.esHoja() == false)){
            Nodo subArbol = padre.subArbolIzquierdo(padre);
            cont++;
            
           // System.out.println("---------------------"); System.out.println("Subarbol generado: " + "@aux" + cont); subArbol.imprimirArbol(subArbol); System.out.println("---------------------");
            
            generarSubCodigo(subArbol);
                  
            subArbol.setNombre("@aux" + cont);
            tipoAux.add(subArbol.getTipo());
            subArbol.setDer(null);
            subArbol.setIzq(null);

            //subArbol.setNombre("@aux" + cont); //como setear el tipo
        
        }
         
       
        FileWriter fileC;
        try {
            fileC = new FileWriter("Assembler.asm");
            PrintWriter writeC = new PrintWriter(fileC);
            writeC.println(".386");
            writeC.println(".model flat, stdcall");
            writeC.println("option casemap :none");
            writeC.println("include \\masm32\\include\\windows.inc");
            writeC.println("include \\masm32\\include\\kernel32.inc");
            writeC.println("include \\masm32\\include\\user32.inc");
            writeC.println("includelib \\masm32\\lib\\kernel32.lib");
            writeC.println("includelib \\masm32\\lib\\user32.lib");
            //writeC.println(".MODEL small");
            writeC.println(".STACK 200h");
            writeC.println(".DATA");
            
            
            writeC.println("mensajeError DB \"ERROR\",0");
            writeC.println("mensajeDivError DB \"ERROR: DIVISION POR CERO\",0");
            writeC.println("mensajeOverflowSuma DB \"ERROR: OVERFLOW EN SUMA\",0");
            writeC.println("mensajeOverflowMultiplicacion DB \"ERROR: OVERFLOW EN MULTIPLICACION\",0");
            
            //CARGA LOS VALORES
            for (String s : tablaSimbolos.keySet())
            {
                if(tablaSimbolos.get(s).get(0).equals("CAD"))
                {
                    writeC.println((tablaSimbolos.get(s)).get(2)+ " DB " + "\"" + s + "\"" + ", 0");
                }
                else
                {
                    if (tablaSimbolos.get(s).get(0).equals("ID"))
                        /*if (tablaSimbolos.get(s).size()>3)
                            writeC.println("_" + s + " DD ?");
                        else*/
                        {
                           if (tablaSimbolos.get(s).get(2).equals("CE"))
                                writeC.println("_" + s + " DW ?");
                            else 
                                writeC.println("_" + s + " DD ?"); 
                        }
                        
                    else 
                        if (tablaSimbolos.get(s).get(0).equals("CE"))
                            writeC.println("_" + s + " DW " + s);
                        else 
                            if (tablaSimbolos.get(s).get(0).equals("CF"))
                                writeC.println("_" + tablaSimbolos.get(s).get(3) + " DD " + Float.parseFloat(s.replace(",", ".")));
                    }
                
            }
            //mensajes de error
            for (int i = 0; i< cont; i++)
                if (tipoAux.get(i)!= null)
                {
                    if (tipoAux.get(i).equals("CE"))
                        writeC.println("_@aux" + i + " DW ?" );
                    else
                        if (tipoAux.get(i).equals("CF"))
                            writeC.println("_@aux" + i + " DD ?" );
                }
            writeC.println("_@auxcomp DW ?");
            writeC.println("_@auxcero DD 0.0");

            writeC.println("_@maxValor DQ 3.40282347E38");
            //writeC.println("_@minValor DQ 1.17549435E-38");
            writeC.println("_@minValor DQ -3.40282347E38");
            //writeC.println("_@maxNegativo DQ -1.17549435E-38");
            
            writeC.println(".CODE");
            
            writeC.println("divError:");
            writeC.println("invoke MessageBox, NULL, addr mensajeDivError, addr mensajeError, MB_OK");
            writeC.println("invoke ExitProcess, 0");
            
            writeC.println("errorOverflowSuma:");
            writeC.println("invoke MessageBox, NULL, addr mensajeOverflowSuma, addr mensajeError, MB_OK");
            writeC.println("invoke ExitProcess, 0");
            
            writeC.println("errorOverflowMultiplicacion:");
            writeC.println("invoke MessageBox, NULL, addr mensajeOverflowMultiplicacion, addr mensajeError, MB_OK");
            writeC.println("invoke ExitProcess, 0");
            
            
            writeC.println("START:");
            for (String s: codigo)
            {
                writeC.println(s);
            }
            
            writeC.println("invoke ExitProcess,0");
            writeC.println("END START");
            fileC.close();
        } catch (IOException ex) {
            Logger.getLogger(GeneracionCodigo.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }
    
    public void generarSubCodigo(Nodo arb){
        String metodo = arb.getNombre();
        switch (metodo) {
            
            case "WHILE": {
                contLabel++;
                labels.push(contLabel);
                codigo.add("Label" + contLabel + ":");
                
                //CREA LABEL Y AGREGA LABEL:
                Nodo izq = arb.getNodoIzq();
                Nodo der = arb.getNodoDer();
                //System.out.println("--------WHILE--------");
                //System.out.println("------Condicion------");
                while (!(izq.esHoja())) {
                    Nodo subArbol = arb.subArbolIzquierdo(izq);
                    
                    generarSubCodigo(subArbol);
                    //System.out.println("---------------------"); System.out.println("Subarbol generado: " + "@aux" + cont); subArbol.imprimirArbol(subArbol); System.out.println("---------------------");
                    subArbol.setNombre("@aux" + cont);
                    tipoAux.add(subArbol.getTipo());
                    cont++;
                    subArbol.setDer(null);
                    subArbol.setIzq(null);
                }
                //System.out.println("-------Cuerpo--------");
                while (!(der.esHoja())) {
                    Nodo subArbol = arb.subArbolIzquierdo(der);
                    generarSubCodigo(subArbol);
                    
                  //System.out.println("---------------------"); System.out.println("Subarbol generado: " + "@aux" + cont); subArbol.imprimirArbol(subArbol); System.out.println("---------------------");
                    subArbol.setNombre("@aux" + cont);
                    cont++;
                    tipoAux.add(subArbol.getTipo());
                    subArbol.setDer(null);
                    subArbol.setIzq(null);
                }
                int menor = labels.pop();
                int primero = labels.pop();
                codigo.add("JMP Label" + primero);
                codigo.add("Label" + menor + ":");
                //System.out.println("---------------------"); System.out.println("Subarbol generado: "); arb.imprimirArbol(arb); System.out.println("---------------------");
                   
                break;
            }
           
            case "+": {
                
                if (arb.getTipo().equals("CE")) {
                    String nombreDer = arb.getNodoDer().getNombre();
                    String nombreIzq = arb.getNodoIzq().getNombre();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable(nombreDer,"CE");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable(nombreIzq,"CE");
                    codigo.add("MOV AX, _" + nombreIzq);
                    codigo.add("ADD AX, _" + nombreDer);
                    codigo.add("JC errorOverflowSuma");
                    codigo.add("MOV " + "_@aux" + cont + ", AX");
                } else {
                   
                    String nombreDer = arb.getNodoDer().getNombreAlternativo();
                    String nombreIzq = arb.getNodoIzq().getNombreAlternativo();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable(nombreDer,"CF");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable(nombreIzq,"CF");
                    codigo.add("FLD _"+ nombreIzq);
                    codigo.add("FLD _"+ nombreDer);
                    codigo.add("FADD");
                    codigo.add("FST _@aux" + cont);
                    codigo.add("FLD _@aux" + cont);
                    codigo.add("FLD _@maxValor");
                    codigo.add("FCOMPP");
                    
                    //codigo.add("FSTSW _@auxcomp");
                    codigo.add("FSTSW AX");
                    
                    codigo.add("FFREE ST (0)");
                    codigo.add("FFREE ST (1)");
                    codigo.add("FWAIT");
                    
                    //codigo.add("MOV AX, _@auxcomp" );
                    codigo.add("SAHF");  
                    codigo.add("JB errorOverflowSuma");
                    
                    //----------------------------------------------------------
                    
                    codigo.add("FLD _@aux" + cont);
                    codigo.add("FLD _@minValor");
                    codigo.add("FCOMPP");
                    
                    //codigo.add("FSTSW _@auxcomp");
                    codigo.add("FSTSW AX");
                    
                    codigo.add("FFREE ST (0)");
                    codigo.add("FFREE ST (1)");
                    codigo.add("FWAIT");
                    
                    //codigo.add("MOV AX, _@auxcomp" );
                    codigo.add("SAHF");  
                    codigo.add("JA errorOverflowSuma");
                    
                    
                }
                
                break;
            }
            case "-": {
                if (arb.getTipo().equals("CE")) {
                    String nombreDer = arb.getNodoDer().getNombre();
                    String nombreIzq = arb.getNodoIzq().getNombre();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable( nombreDer,"CE");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable( nombreIzq,"CE");
                    codigo.add("MOV AX, _" + nombreIzq);
                    codigo.add("SUB AX, _" + nombreDer);
                    codigo.add("MOV " + "_@aux" + cont + ", AX");
                } else {
                    String nombreDer = arb.getNodoDer().getNombreAlternativo();
                    String nombreIzq = arb.getNodoIzq().getNombreAlternativo();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable(nombreDer,"CF");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable( nombreIzq,"CF");
                    codigo.add("FLD _" + nombreIzq);
                    codigo.add("FLD _" + nombreDer);
                    codigo.add("FSUB");
                    codigo.add("FSTP " + "_@aux" + cont ); 
                }
                //tipoAux.add(cont,arb.getTipo());
                break;
            }
            case "*": {
                if (arb.getTipo().equals("CE")) {
                    String nombreDer = arb.getNodoDer().getNombre();
                    String nombreIzq = arb.getNodoIzq().getNombre();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable( nombreDer,"CE");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable( nombreIzq,"CE");
                    codigo.add("MOV AX, _" + nombreIzq);
                    codigo.add("MUL AX, _" + nombreDer);
                    codigo.add("JC errorOverflowMultiplicacion");
                    codigo.add("MOV " + "_@aux" + cont + ", AX");
                } else {
                    String nombreDer = arb.getNodoDer().getNombreAlternativo();
                    String nombreIzq = arb.getNodoIzq().getNombreAlternativo();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable( nombreDer,"CF");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable( nombreIzq,"CF");
                    codigo.add("FLD _" + nombreIzq);
                    codigo.add("FLD _" + nombreDer);
                    codigo.add("FMUL");
                    codigo.add("FST _@aux" + cont);
                    codigo.add("FLD _@aux" + cont);
                    codigo.add("FLD _@maxValor");
                    codigo.add("FCOMPP");
                    
                    //codigo.add("FSTSW _@auxcomp");
                    codigo.add("FSTSW AX");
                    
                    codigo.add("FFREE ST (0)");
                    codigo.add("FFREE ST (1)");
                    codigo.add("FWAIT");
                    
                    //codigo.add("MOV AX, _@auxcomp" );
                    codigo.add("SAHF");  
                    codigo.add("JB errorOverflowMultiplicacion");
                    
                    //----------------------------------------------------------
                    
                    codigo.add("FLD _@aux" + cont);
                    codigo.add("FLD _@minValor");
                    codigo.add("FCOMPP");
                    
                    //codigo.add("FSTSW _@auxcomp");
                    codigo.add("FSTSW AX");
                    
                    codigo.add("FFREE ST (0)");
                    codigo.add("FFREE ST (1)");
                    codigo.add("FWAIT");
                    
                    //codigo.add("MOV AX, _@auxcomp" );
                    codigo.add("SAHF");  
                    codigo.add("JA errorOverflowMultiplicacion");
                    
                                     
                }
                //tipoAux.add(cont,arb.getTipo());
                break;
            }
            case "/": {
                if (arb.getTipo().equals("CE")) {
                    String nombreDer = arb.getNodoDer().getNombre();
                    String nombreIzq = arb.getNodoIzq().getNombre();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable(nombreDer,"CE");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable( nombreIzq,"CE");
                    codigo.add("MOV DX, 0");
                    codigo.add("MOV AX, _" + nombreIzq);
                    codigo.add("MOV BX, _" + nombreDer);
                    codigo.add("CMP BX, 0");
                    codigo.add("JZ divError");
                    codigo.add("DIV BX"); //se le borro el AX
                    codigo.add("MOV " + "_@aux" + cont + ", AX");
                } else {
                   String nombreDer = arb.getNodoDer().getNombreAlternativo();
                    String nombreIzq = arb.getNodoIzq().getNombreAlternativo();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable( nombreDer,"CF");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable( nombreIzq,"CF");
                    codigo.add("FLD _" + nombreIzq);
                    codigo.add("FLD _" + nombreDer);
                    codigo.add("FLD _@auxcero");
                    codigo.add("FCOMP");
                    codigo.add("FSTSW _@auxcomp");
                    codigo.add("MOV AX, _@auxcomp" );
                    codigo.add("SAHF");
                    codigo.add("JZ divError");
                    codigo.add("FDIV");
                    codigo.add("FSTP " + "_@aux" + cont ); 
                    
        
                }
                //tipoAux.add(cont,arb.getTipo());
                break;
            }
            case "==": {
                contLabel++;
                if (arb.getTipo().equals("CE")) {
                    String nombreDer = arb.getNodoDer().getNombre();
                    String nombreIzq = arb.getNodoIzq().getNombre();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable( nombreDer,"CE");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable( nombreIzq,"CE");
                    codigo.add("MOV AX, _" + nombreIzq);
                    codigo.add("MOV BX, _" + nombreDer);
                    codigo.add("CMP AX, BX");
                    labels.push(contLabel);
                    codigo.add("JNE Label" + contLabel); //PENSAR //AGREGAR LABEL ACA
                } else {
                    comparacionFlotante(arb);
                    labels.push(contLabel);
                    codigo.add("JNE Label" + contLabel);
                    
                }
                
                break;
            }
            case ">=": {
                contLabel++;
                if (arb.getTipo().equals("CE")) {
                    String nombreDer = arb.getNodoDer().getNombre();
                    String nombreIzq = arb.getNodoIzq().getNombre();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable( nombreDer,"CE");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable(nombreIzq,"CE");
                    codigo.add("MOV AX, _" + nombreIzq);
                    codigo.add("MOV BX, _" + nombreDer);
                    codigo.add("CMP AX, BX");
                    labels.push(contLabel);
                    codigo.add("JB Label" + contLabel);
                } else {
                    comparacionFlotante(arb);
                    labels.push(contLabel);
                    codigo.add("JB Label" + contLabel);
                }
                
                break;
            }
            case "<=": {
                contLabel++;
                if (arb.getTipo().equals("CE")) {
                    
                    String nombreDer = arb.getNodoDer().getNombre();
                    String nombreIzq = arb.getNodoIzq().getNombre();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable( nombreDer,"CE");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable( nombreIzq,"CE");
                    codigo.add("MOV AX, _" + nombreIzq);
                    codigo.add("MOV BX, _" + nombreDer);
                    codigo.add("CMP AX, BX");
                    labels.push(contLabel);
                    codigo.add("JA Label" + contLabel); 
                } else {
                     comparacionFlotante(arb);
                     labels.push(contLabel);
                    codigo.add("JA Label" + contLabel);
                }
                
                break;
            }
            case "<>": {
                contLabel++;
                if (arb.getTipo().equals("CE")) {
                    
                    String nombreDer = arb.getNodoDer().getNombre();
                    String nombreIzq = arb.getNodoIzq().getNombre();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable( nombreDer,"CE");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable(nombreIzq,"CE");
                    codigo.add("MOV AX, _" + nombreIzq);
                    codigo.add("MOV BX, _" + nombreDer);
                    codigo.add("CMP AX, BX");
                    labels.push(contLabel);
                    codigo.add("JE Label" + contLabel); 
                } else {
                     comparacionFlotante(arb);
                     labels.push(contLabel);
                    codigo.add("JE Label" + contLabel);
                }
               
                break;
            }
            case ">": {
                contLabel++;
                if (arb.getTipo().equals("CE")) {
                    
                    String nombreDer = arb.getNodoDer().getNombre();
                    String nombreIzq = arb.getNodoIzq().getNombre();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable(nombreDer,"CE");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable(nombreIzq,"CE");
                    codigo.add("MOV AX, _" + nombreIzq);
                    codigo.add("MOV BX, _" + nombreDer);
                    codigo.add("CMP AX, BX");
                    labels.push(contLabel);
                    codigo.add("JBE Label" + contLabel); 
                } else {
                     comparacionFlotante(arb);
                    labels.push(contLabel);
                    codigo.add("JBE Label" + contLabel);
                }
                
                break;
            }
            case "<": {
                contLabel++;
                if (arb.getTipo().equals("CE")) {
                    
                    String nombreDer = arb.getNodoDer().getNombre();
                    String nombreIzq = arb.getNodoIzq().getNombre();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable(nombreDer,"CE");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable(nombreIzq,"CE");
                    codigo.add("MOV AX, _" + nombreIzq);
                    codigo.add("MOV BX, _" + nombreDer);
                    codigo.add("CMP AX, BX");
                    labels.push(contLabel);
                    codigo.add("JAE Label" + contLabel); 
                } else {
                     comparacionFlotante(arb);
                     labels.push(contLabel);
                    codigo.add("JAE Label" + contLabel);
                }
                
                break;
            }
            case "=": {
                if (arb.getTipo().equals("CE"))
                {
                    String nombreDer = arb.getNodoDer().getNombre();
                    String nombreIzq = arb.getNodoIzq().getNombre();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable(nombreDer,"CE");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable(nombreIzq,"CE");
                    codigo.add("MOV AX, _" + nombreDer);
                    codigo.add("MOV _" + nombreIzq + ", AX");
                }
                else 
                {
                    String nombreDer = arb.getNodoDer().getNombreAlternativo();
                    String nombreIzq = arb.getNodoIzq().getNombreAlternativo();
                    if (duplicado(nombreDer))
                        nombreDer= getVariable(nombreDer,"CF");
                    if (duplicado(nombreIzq))
                        nombreIzq= getVariable( nombreIzq,"CF");
                    codigo.add("MOV EAX, _" + nombreDer);
                    codigo.add("MOV _" + nombreIzq+ ", EAX");
                }
                
                break;
            }
            case "OUT": {
                String msg = arb.getNodoIzq().getNombre();
                tablaSimbolos.get(msg).add("mensaje" + contMensaje);
                codigo.add("invoke MessageBox, NULL, addr " + "mensaje" + contMensaje + ", addr"  + " mensaje" + contMensaje + ", MB_OK"); //VER
                contMensaje++;
                break;
            }
            case "IF_SIMPLE": {
                
                codigo.add("Label" + labels.pop() +":");
                //Label actual:
                break;
            }
            case "IF": {
                //posible nada
                break;
            }
            case "THEN": {
                contLabel++;
                int menor = labels.pop();
                labels.push(contLabel);
                codigo.add("JMP Label" + contLabel);
                
                codigo.add("Label" + menor + ":");
                
                //AGREGAR JMP LABEL
                // AGREGA LABEL ANTERIOR 
                break;
            }
            case "ELSE": {
                codigo.add("Label" + labels.pop() + ":");
                // AGREGA LABEL ANTERIOR ACTUAL 
                break;
            }
            case "UI_F": {
                String variable = arb.getNodoIzq().getNombre();
                if (duplicado(arb.getNodoIzq().getNombre()))
                {
                    variable= getVariable(arb.getNodoIzq().getNombre(),"CE"); 
                } 
                codigo.add("FILD _" + variable);
                codigo.add("FSTP _@aux" + cont);
                // AGREGA LABEL ANTERIOR ACTUAL 
                break;
            }
            case ":": {
                
                verificarTipo(arb.getNodoIzq(), arb.getNodoDer().getNombre());
                break;
            }
            case "LET": {
                String tipo = arb.getTipo();
                if (!duplicado(arb.getNodoIzq().getNombre()))
                {     
                   String tipoId = getTipo(arb.getNodoIzq().getNombre());
                   if (tipo.equals(tipoId))
                   {
                       arb.setNombre("=");
                       generarSubCodigo(arb);
                       arb.setNombre("@aux" + cont);
                       tipoAux.add(arb.getTipo());
                       cont++;
                       arb.setDer(null);
                       arb.setIzq(null);
                   }
                   else
                   {
                        if (tipoId.equals("CF")) {
                            ArrayList<Object> atrDig = new ArrayList<>(5);
                            atrDig.add(0,"ID");
                            atrDig.add(1,270);
                            atrDig.add(2,"CE");
                            tablaSimbolos.put(arb.getNodoIzq().getNodoIzq().getNombre() + "$", atrDig);
                        }                                      
                        else {
                            ArrayList<Object> atrDig = new ArrayList<>(5);
                            atrDig.add(0,"ID");
                            atrDig.add(1,270);
                            atrDig.add(2,"CF");
                            tablaSimbolos.put(arb.getNodoIzq().getNodoIzq().getNombre() + "$", atrDig);
                        }
                        arb.setNombre("=");
                        generarSubCodigo(arb);
                        arb.setNombre("@aux" + cont);
                        tipoAux.add(arb.getTipo());
                        cont++;
                        arb.setDer(null);
                        arb.setIzq(null);
                   }  
                    
                }
                else
                {
                    String variable = getVariable(arb.getNodoIzq().getNombre(), tipo);
                    arb.getNodoIzq().setNombre(variable);
                    arb.setNombre("=");
                    generarSubCodigo(arb);
                    arb.setNombre("@aux" + cont);
                    tipoAux.add(arb.getTipo());
                    cont++;
                    arb.setDer(null);
                    arb.setIzq(null);
                }   
                break;
            }
            default: {
               // cont--;
                break;
            }

        }
    }
    
    public String getVariable ( String name,String tipo){
        if (getTipo(name).equals(tipo))
            return name;
        else 
            if (name.contains("$"))
                return name.substring(0, name.length()-1);
            else
                return name + "$";
    }
    
    public void verificarTipo(Nodo variable, String tipo) {
        if (variable != null) {
            verificarTipo(variable.getNodoIzq(), tipo);
            if (variable.esHoja()) {
                if (!duplicado(variable.getNombre()))
                {
                    String t = getTipo(variable.getNombre());
                    if (tipo.equals("UINT")) {
                        if (t.equals("CF")) {
                            ArrayList<Object> atrDig = new ArrayList<>(5);
                            atrDig.add(0,"ID");
                            atrDig.add(1,270);
                            atrDig.add(2,"CE");
                            tablaSimbolos.put(variable.getNombre() + "$", atrDig);
                        }
                    }
                    else {
                        if (t.equals("CE")) {
                            ArrayList<Object> atrDig = new ArrayList<>(5);
                            atrDig.add(0,"ID");
                            atrDig.add(1,270);
                            atrDig.add(2,"CF");
                            //atrDig.add(3,"@float" + contFloat);
                            //contFloat++;
                            tablaSimbolos.put(variable.getNombre() + "$", atrDig);
                        }
                    }
                }
            }
            verificarTipo(variable.getNodoDer(), tipo);
        }

    }
    
    public String getTipo (String key){
    ArrayList<Object> atributos = tablaSimbolos.get(key);
    if ((atributos.get(0).equals("ID")))
    {
        return (String)atributos.get(2);
    }  
        return (String) atributos.get(0);
    }
    
    public boolean duplicado (String key){
        if ((tablaSimbolos.containsKey(key))&& (tablaSimbolos.containsKey(key + "$")))
            return true;
        return false;
    }
    
    private void comparacionFlotante(Nodo arb){
        String nombreDer = arb.getNodoDer().getNombreAlternativo();
        String nombreIzq = arb.getNodoIzq().getNombreAlternativo();
        if (duplicado(nombreDer))
            nombreDer= getVariable(nombreDer,"CF");
        if (duplicado(nombreIzq))
            nombreIzq= getVariable(nombreIzq,"CF");
        codigo.add("FLD _" + nombreIzq);
        codigo.add("FLD _" + nombreDer);
        codigo.add("FCOM");
        codigo.add("FSTSW _@auxcomp");
        codigo.add("MOV AX, _@auxcomp" );
        codigo.add("SAHF");    
    }
    
    
}
