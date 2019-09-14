

import java.util.ArrayList;
import java.util.Map;


public abstract class AccionSemantica {
    Lexico lexico;

    public AccionSemantica(Lexico lexico) {
        this.lexico = lexico;
    }

    public abstract void ejecutar(Map<String,ArrayList<Object>> tablaSimbolos);
}
