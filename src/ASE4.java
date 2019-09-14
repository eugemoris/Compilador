

import java.util.ArrayList;
import java.util.Map;


public class ASE4 extends AccionSemantica{

    public ASE4(Lexico lexico) {
        super(lexico);
    }

    @Override
    public void ejecutar(Map<String,ArrayList<Object>> tablaSimbolos) {
        String token = lexico.getToken();
         if (lexico.getToken().contains("\n")){
            int cant = lexico.getNroLinea();
            lexico.setNroLinea(cant++);
        }
        lexico.addError("Linea " + lexico.getNroLinea() + ": WARNING. Cadena no cerrada, se crea cadena igual");
        //System.out.println("Warning en linea " + lexico.getNroLinea() + ": cadena no cerrada, se crea cadena igual ");
        token = token.substring(1, token.length());
        token = token.replace("$", "");
        token = token.replace("'", "");
        //System.out.println("token en ASE4 " + token);
        lexico.setToken(token);
        lexico.setLeer(false);
        ArrayList<Object> atrID = new ArrayList<>(5);
        atrID.add(0,"CAD");
        atrID.add(1,272);
        tablaSimbolos.put(token, atrID);
    }
    
}
