

import java.util.ArrayList;
import java.util.Map;


public class AS1 extends AccionSemantica {

    public AS1(Lexico lexico) {
        super(lexico);
    }

    @Override
    public void ejecutar(Map<String,ArrayList<Object>> tablaSimbolos) {
        String token = lexico.getToken();
        token = token.substring(0,token.length()-1);
        if (token.length() > 15) {
            lexico.addError("Linea " + lexico.getNroLinea() + ": WARNING. El Identificador " + token + " supera la longitud permitida de 15 caracteres, el identificador se truncó");
           // System.out.println("Warning en Linea " + lexico.getNroLinea() + ": el Identificador " + token + " supera la longitud permitida de 15 caracteres.");
            token = token.substring(0,15);
        }
        lexico.setLeer(false);
        
        ArrayList<Object> atributosPR = tablaSimbolos.get(token); //ver si es palabra reservada
        
        if (atributosPR == null)
        {
            String lexema = token.toLowerCase(); //minuscula
            ArrayList<Object> atributosID = tablaSimbolos.get(lexema);// ya fue cargado el id
            //preguntar por el tipo en caso de ser necesario
            if (atributosID == null) {
                ArrayList<Object> atrID = new ArrayList<>(5);
                atrID.add(0,"ID");
                atrID.add(1,270);
                tablaSimbolos.put(lexema, atrID);
                //obtenemos el numero de la tabla de simbolos
            }
            lexico.setToken(lexema);
        }
        else    
            lexico.setToken(token);
        
       
    }
    
}
