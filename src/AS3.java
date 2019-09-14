

import java.util.ArrayList;
import java.util.Map;

public class AS3 extends AccionSemantica {

    public AS3(Lexico lexico) {
        super(lexico);
    }
    
    @Override
    public void ejecutar(Map<String,ArrayList<Object>> tablaSimbolos) {
        String token = lexico.getToken();
        token = token.substring(0,token.length()-1);
        lexico.setToken(token);
        lexico.setLeer(false);
    }
}
