

import java.util.ArrayList;
import java.util.Map;


public class AS5 extends AccionSemantica {

    public AS5(Lexico lexico) {
        super(lexico);
    }

    @Override
    public void ejecutar(Map<String,ArrayList<Object>> tablaSimbolos) {
        if (lexico.getToken().endsWith("\n")){
            lexico.setNroLinea(lexico.getNroLinea()+1);
        }
        lexico.setToken("");
    }
    
}
