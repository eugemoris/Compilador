

import java.util.ArrayList;
import java.util.Map;


public class ASE2 extends AS4{

    public ASE2(Lexico lexico) {
        super(lexico);
    }

    
    @Override
    public void ejecutar(Map<String,ArrayList<Object>> tablaSimbolos) {
       String token = lexico.getToken();
       token = token.substring(0,token.length()-1);
       lexico.setToken(token);
       super.ejecutar(tablaSimbolos);
       lexico.addError("Linea "+ lexico.getNroLinea() + ": WARNING. Declaracion de float incorrecta, se elimino ultimo caracter");
       //System.out.println("Warning en linea "+ lexico.getNroLinea() + ": declaracion de float incorrecta, se elimino ultimo caracter");
    }
    
}