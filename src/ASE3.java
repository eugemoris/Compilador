

import java.util.ArrayList;
import java.util.Map;

public class ASE3 extends AccionSemantica{

    public ASE3(Lexico lexico) {
        super(lexico);
    }

    @Override
    public void ejecutar(Map<String,ArrayList<Object>> tablaSimbolos) {
        lexico.setToken("");
        lexico.addError("Linea "+ lexico.getNroLinea() + ": ERROR. Falto elemento para completar el tipo float");
        //System.out.println("ERROR en linea "+ lexico.getNroLinea() + ": Falto elemento para completar el tipo float");
    }
    
}