

import java.util.ArrayList;
import java.util.Map;


public class ASE1 extends AccionSemantica{

    public ASE1(Lexico lexico) {
        super(lexico);
    }

    @Override
    public void ejecutar(Map<String,ArrayList<Object>> tablaSimbolos) {
       lexico.setToken("");     
       lexico.addError("Linea " + lexico.getNroLinea() + ": ERROR. Caracteres no validos, fueron eliminados");
      }
    
}