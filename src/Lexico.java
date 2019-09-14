



import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Lexico {
  
    private DataInputStream lectorCodigo;
    private String ultimoCaracter;  //ultimo caracter leido
    private Boolean leer;           //leer=true avanzo leer=false leo el ultimo caracter
    private Integer nroLinea;
    private int[][] matEstados = {  { 0, 0, 0,11, 1, 2, 0,13,13,13,13, 3, 4, 5,13,13,13,13,12, 1,10, 0,13},
                                    {13,13,13,13, 1, 1, 1,13,13,13,13,13,13,13,13,13,13,13,13, 1,13,13,13},
                                    {13,13,13,13,13, 2,13,13,13,13,13,13,13,13,13,13,13,13, 6,13,13,13,13},
                                    {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                                    {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                                    {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                                    {13,13,13,13,13, 6,13,13,13,13,13,13,13,13,13,13,13,13,13, 7,13,13,13},
                                    {13,13,13,13,13, 9,13, 8, 8,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                                    { 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {13,13,13,13,13, 9,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                                    {10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10, 0, 0},
                                    {11,11,13,13,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,13},
                                    {13,13,13,13,13, 6,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13} 
                                  };
    private AccionSemantica[][] matAcciones = new AccionSemantica[13][23];
    private Map<String,Integer> indices;
    private Map<String,ArrayList<Object>> tablaSimbolos;
    private ArrayList<Object> listErrores = new ArrayList();
    private ArrayList<Object> tokens = new ArrayList<>();
    private String token;
    private char ch;
    private int c=0;
    private int estadoActual;
    
   
    

    BufferedReader reader;
    public Lexico(String ruta) throws FileNotFoundException {
        //this.lectorCodigo = new DataInputStream(new FileInputStream(ruta));
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), Charset.forName("UTF-8")));  
        ultimoCaracter = "";
        leer = true;
        nroLinea = new Integer(1);
        indices = new HashMap<>();
        tablaSimbolos = new HashMap<>();
        cargarMatriz();
        cargarIndice();
        ArrayList<Object> ifAtributos = new ArrayList<>(5);
        ifAtributos.add(0,"PR");
        ifAtributos.add(1,257);
        tablaSimbolos.put("IF", ifAtributos);
        
        ArrayList<Object> thenAtributos = new ArrayList<>(5);
        thenAtributos.add(0,"PR");
        thenAtributos.add(1,258);
        tablaSimbolos.put("THEN", thenAtributos);
        
        ArrayList<Object> elseAtributos = new ArrayList<>(5);
        elseAtributos.add(0,"PR");
        elseAtributos.add(1,259);
        tablaSimbolos.put("ELSE", elseAtributos);
        
        ArrayList<Object> endifAtributos = new ArrayList<>(5);
        endifAtributos.add(0,"PR");
        endifAtributos.add(1,260);
        tablaSimbolos.put("ENDIF", endifAtributos);
        
        ArrayList<Object> beginAtributos = new ArrayList<>(5);
        beginAtributos.add(0,"PR");
        beginAtributos.add(1,261);
        tablaSimbolos.put("BEGIN", beginAtributos);
        
        ArrayList<Object> endAtributos = new ArrayList<>(5);
        endAtributos.add(0,"PR");
        endAtributos.add(1,262);
        tablaSimbolos.put("END", endAtributos);
        
        ArrayList<Object> outAtributos = new ArrayList<>(5);
        outAtributos.add(0,"PR");
        outAtributos.add(1,263);
        tablaSimbolos.put("OUT", outAtributos);
        
        ArrayList<Object> whileAtributos = new ArrayList<>(5);
        whileAtributos.add(0,"PR");
        whileAtributos.add(1,264);
        tablaSimbolos.put("WHILE", whileAtributos);
        
        ArrayList<Object> doAtributos = new ArrayList<>(5);
        doAtributos.add(0,"PR");
        doAtributos.add(1,265);
        tablaSimbolos.put("DO", doAtributos);
        
        ArrayList<Object> letAtributos = new ArrayList<>(5);
        letAtributos.add(0,"PR");
        letAtributos.add(1,266);
        tablaSimbolos.put("LET", letAtributos);
        
        ArrayList<Object> uintAtributos = new ArrayList<>(5);
        uintAtributos.add(0,"PR");
        uintAtributos.add(1,267);
        tablaSimbolos.put("UINT", uintAtributos);
        
        ArrayList<Object> floatAtributos = new ArrayList<>(5);
        floatAtributos.add(0,"PR");
        floatAtributos.add(1,268);
        tablaSimbolos.put("FLOAT", floatAtributos);
        
        ArrayList<Object> uifAtributos = new ArrayList<>(5);
        uifAtributos.add(0,"PR");
        uifAtributos.add(1,269);
        tablaSimbolos.put("UI_F", uifAtributos);
        
        ArrayList<Object> dobleIAtributos = new ArrayList<>(5);
        dobleIAtributos.add(0,"DI");
        dobleIAtributos.add(1,273);
        tablaSimbolos.put("==", dobleIAtributos);
        
        ArrayList<Object> mayorIAtributos = new ArrayList<>(5);
        mayorIAtributos.add(0,"MaI");
        mayorIAtributos.add(1,274);
        tablaSimbolos.put(">=", mayorIAtributos);
        
        ArrayList<Object> menorIAtributos = new ArrayList<>(5);
        menorIAtributos.add(0,"MeI");
        menorIAtributos.add(1,275);
        tablaSimbolos.put("<=", menorIAtributos);
        
        ArrayList<Object> distintoAtributos = new ArrayList<>(5);
        distintoAtributos.add(0,"Dis");
        distintoAtributos.add(1,276);
        tablaSimbolos.put("<>", distintoAtributos); 
    }
    
    public void cargarMatriz(){
        AccionSemantica AS1 = new AS1(this);
        AccionSemantica AS2 = new AS2(this);
        AccionSemantica AS3 = new AS3(this);
        AccionSemantica AS4 = new AS4(this);
        AccionSemantica AS5 = new AS5(this);
        AccionSemantica AS6 = new AS6(this);
        AccionSemantica ASE1 = new ASE1(this);
        AccionSemantica ASE2 = new ASE2(this);
        AccionSemantica ASE3 = new ASE3(this);
        AccionSemantica ASE4 = new ASE4(this);
        for(int i=0; i<=22; i++) {
            matAcciones[0][i] = null;
            matAcciones[1][i] = AS1;
            matAcciones[2][i] = AS2;
            matAcciones[3][i] = AS3;
            matAcciones[4][i] = AS3;
            matAcciones[5][i] = AS3;
            matAcciones[6][i] = AS4;
            matAcciones[7][i] = ASE2;
            matAcciones[8][i] = ASE3;
            matAcciones[9][i] = AS4;
            matAcciones[10][i] = null;
            matAcciones[11][i] = null;
            matAcciones[12][i] = AS3;
        }
        matAcciones[0][0] = AS5;
        matAcciones[0][1] = AS5;
        matAcciones[0][2] = AS5;
        matAcciones[0][6] = ASE1;
        matAcciones[0][21] = ASE1;
        matAcciones[1][4] = null;
        matAcciones[1][5] = null;
        matAcciones[1][6] = null;
        matAcciones[1][19] = null;
        matAcciones[2][5] = null; 
        matAcciones[2][18] = null;
        matAcciones[3][11] = null;
        matAcciones[4][11] = null;
        matAcciones[4][13] = null;
        matAcciones[5][11] = null;
        matAcciones[6][5] = null;
        matAcciones[6][19] = null;
        matAcciones[7][5] = null;
        matAcciones[7][7] = null;
        matAcciones[7][8] = null;
        matAcciones[8][5] = null;
        matAcciones[9][5] = null;
        matAcciones[10][2] = AS5;
        matAcciones[10][22] = ASE1;
        matAcciones[11][2] = ASE4;
        matAcciones[11][3] = AS6;
        matAcciones[11][22] = ASE4;
        matAcciones[12][5] = null;            
    }
    
    public void cargarIndice(){
        indices.put(" ", 0);
        indices.put("\t", 1);
        indices.put("salto", 2);
        indices.put("'", 3);
        indices.put("letra", 4);
        indices.put("digito", 5);
        indices.put("_", 6);
        indices.put("-", 7);
        indices.put("+", 8);
        indices.put("/", 9);
        indices.put("*", 10);
        indices.put("=", 11);
        indices.put("<", 12);
        indices.put(">", 13);
        indices.put("(", 14);
        indices.put(")", 15);
        indices.put(":", 16);
        indices.put(".", 17);
        indices.put(",", 18);
        indices.put("e", 19);
        indices.put("[", 20);
        indices.put("]", 21);
        indices.put("$", 22);
    }

    public int yylex() throws IOException{
        token = "";
        estadoActual = 0;
        AccionSemantica as = null;
        
        while(estadoActual != 13)
        {
            if (leer){
                if((c = reader.read()) != -1){
                    ch = (char) c;
                    ultimoCaracter = Character.toString(ch);
                    if (c == 13){
                        c = reader.read();
                        ultimoCaracter = "salto";
                        ch = '\n';                        
                    }
                        
                }
                else {
                    ultimoCaracter = "$";
                    ch = '$';
                }
            }
            else {
                leer = true;
                if (c == 10)
                {
                    ultimoCaracter = "salto";
                    ch = '\n';
                }
                else    
                    ultimoCaracter = Character.toString(ch);
            }
            
            //if para letras o numeros
            if (((int)c >= 48) && ((int)c <= 57))
                ultimoCaracter = "digito";
            else if ((((int)c >= 65) && ((int)c <= 68)) || (((int)c >= 70) && ((int)c <= 90)) || (((int)c >= 97) && ((int)c <= 100)) || (((int)c >= 102) && ((int)c <= 122)))
                ultimoCaracter = "letra";
            else if (c == 69)
                ultimoCaracter = "e";
            if(indices.containsKey(ultimoCaracter)==false)
            {
                addError("Linea " + nroLinea + ": ERROR. Caracter no reconocido en el lenguaje");
                estadoActual = 0;
                token = "";
            }
            else
            {
                as = matAcciones[estadoActual][indices.get(ultimoCaracter)];
                estadoActual = matEstados[estadoActual][indices.get(ultimoCaracter)];
                token = token + ch;
                if (as != null)
                    as.ejecutar(tablaSimbolos);
            }
            
        }
        int valorToken = 0;
        if (tablaSimbolos.containsKey(token))
            valorToken = (int)tablaSimbolos.get(token).get(1);
        else if (token.equals("$"))
            valorToken = -1;
        else valorToken = token.charAt(0); 
        tokens.add("\r\n Linea " + getNroLinea()+ ": " + valorToken +" - "+ getToken());
        return valorToken;
    }
   
    public boolean existeEnTablaDeSimbolos(String clave) {
        if (tablaSimbolos.containsKey(clave))
            return true;
        return false;
    }
    
    public String getNombreAlternativo(String key){
        
        return (String)tablaSimbolos.get(key).get(3);
    }
    
    public void setNroLinea(Integer nroLinea) {
        this.nroLinea = nroLinea;
    }

    public Integer getNroLinea() {
        return nroLinea;
    }

    public Boolean getLeer() {
        return leer;
    }

    public void setLeer(Boolean leer) {
        this.leer = leer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, ArrayList<Object>> getTablaSimbolos() {
        return tablaSimbolos;
    }

    public void setEstadoActual(int estadoActual) {
        this.estadoActual = estadoActual;
    }
    
    public void setNegativo(String key) {
        if (!tablaSimbolos.get(key).get(0).equals("CE"))
        {
            String negativo= "-" + key;
            if (!tablaSimbolos.containsKey(negativo))
            {
                ArrayList<Object> atrib= new ArrayList<>(5);
                atrib.add(0,"CF");
                atrib.add(1,271);
                atrib.add(2,1);
                tablaSimbolos.put(negativo, atrib);
            }
            ArrayList<Object> lista = tablaSimbolos.get(key);
            int i= (int)lista.get(2);
            lista.remove(2);
            i--;
            if (i==0)
                tablaSimbolos.remove(key);
            else
            {
                lista.add(2, i);
                tablaSimbolos.put(token, lista);
            }   
        }
    }
    
    public void addError(String lineaError){
        lineaError = "\r\n"+lineaError; 
        listErrores.add(lineaError.toString());
    }
    
    public boolean hayError(){
        if (listErrores.size()>0)
            return true;
        return false;
    }
    
    public ArrayList<Object> getErrores(){
        return listErrores;
    }
    
    public ArrayList<Object> getTokens(){
        return tokens;
    }
    
    public String getTipo (String key){
        ArrayList<Object> atributos = tablaSimbolos.get(key);
        if ((atributos.get(0).equals("ID"))&& (declarada(key)))
        {
            return (String)atributos.get(2);
        }  
        return (String) atributos.get(0);
        
    }
    
    public void setTipo (String key, String nuevoTipo){
        ArrayList<Object> aux = tablaSimbolos.get(key);  //ver casos donde no se encuentra en la tabla de simbolos
        
        if (!aux.get(0).equals("ID"))
        {
            aux.remove(0);
            aux.add(0,nuevoTipo);
        }
        else
        {
            if(aux.size()>2)
            {
               aux.remove(2); 
               aux.add(2,nuevoTipo);
               //if (aux.size()==3)
                   //aux.add(true);
            }
            else
            {
                aux.add(2,nuevoTipo);
               
            }
            
            
        }        
        tablaSimbolos.put(key, aux);//REVISAR
        
    }
    
    public boolean declarada(String key){
        if (tablaSimbolos.get(key).get(0).equals("ID"))
        {
            if (tablaSimbolos.get(key).size()>2)
                return true;
            else 
                return false; 
        }
       
        return true;
    }
    
    public String getTabla(){
        String tabla= "";
        for (String s : tablaSimbolos.keySet())
        {
            tabla = tabla + s + " "+ tablaSimbolos.get(s).toString() + "\r" + "\n";
        }
        
        return tabla;
    }
    
    public void addNombreAlt(String clave,String newName){
        
        ArrayList<Object> aux = tablaSimbolos.get(clave);
        if (aux != null)
        {
            if (aux.size()==3)
         {
           aux.add(3,newName);
           tablaSimbolos.put(clave, aux); 
            }
        }
        //System.out.println("clave " + clave);
        //System.out.println("lista " + aux);
        
        
    }
    
  
        
    public static void main(String[] args) throws IOException {
        try {
            String ruta = args[0];
            Lexico lx = new Lexico(ruta);
            
           
            
            //Lexico lx = new Lexico("./UINT_neg.txt");
            int token = 0;
            
            Parser parser = new Parser(lx);
            parser.run();
            
            
            
            while (token != -1)
            {
                token = lx.yylex();
            }
            /*FileWriter fileToken = new FileWriter("Tokens.txt");
            PrintWriter writeToken = new PrintWriter(fileToken);
            writeToken.print(lx.getTokens());
            fileToken.close();*/
            FileWriter fileTS = new FileWriter("Tabla de simbolos.txt");
            PrintWriter write = new PrintWriter(fileTS);
            write.print(lx.getTabla());
            fileTS.close();

            FileWriter fileE = new FileWriter("Errores.txt");
            PrintWriter writeE = new PrintWriter(fileE);
            writeE.print(lx.getErrores().toString());
            fileE.close();
            
            /*FileWriter fileR = new FileWriter("Reglas.txt");
            PrintWriter writeR = new PrintWriter(fileR);
            writeR.print(parser.getReglasUsadas().toString());
            fileR.close();*/
            
                      
            
            
              
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
        }
        
    }

    
    
    
}
