

import java.util.ArrayList;
import java.util.Map;


public class AS6 extends AccionSemantica {

    public AS6(Lexico lexico) {
        super(lexico);
    }

    @Override
    public void ejecutar(Map<String, ArrayList<Object>> tablaSimbolos) {
        String token = lexico.getToken();
        token = token.substring(1, token.length()-1);
        lexico.setToken(token);
        ArrayList<Object> atrID = new ArrayList<>(5);
        atrID.add(0,"CAD");
        atrID.add(1,272);
        tablaSimbolos.put(token, atrID);
    }
    
}
