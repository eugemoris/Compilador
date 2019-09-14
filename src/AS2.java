

import java.util.ArrayList;
import java.util.Map;


public class AS2 extends AccionSemantica{
    private Integer minValue = new Integer(0);
    private Integer maxValue = new Integer(65535);

    public AS2(Lexico lexico) {
        super(lexico);
    }
        
    @Override
    public void ejecutar(Map<String,ArrayList<Object>> tablaSimbolos) {
        String token = lexico.getToken();
        token = token.substring(0,token.length()-1);
        Integer entero = Integer.parseInt(token);
        if ((entero < minValue) || (entero > maxValue)) {
            lexico.addError("Linea " + lexico.getNroLinea() + ": ERROR. La constante entera " + token + " se encuentra fuera de rango");
            //System.out.println("ERROR en Linea " + lexico.getNroLinea() + ": La constante " + token + " se encuentra fuera de rango");
            lexico.setToken("");
            lexico.setEstadoActual(0);
        }
        else
        {
            lexico.setLeer(false);
            ArrayList<Object> atributosDig = tablaSimbolos.get(token);// ya fue cargado el id
            if (atributosDig == null) {
            ArrayList<Object> atrDig = new ArrayList<>(5);
            atrDig.add(0,"CE");
            atrDig.add(1,271);
            tablaSimbolos.put(token, atrDig);
            //obtenemos el numero de la tabla de simbolos
            }
            lexico.setToken(token);
        }    
    }
    
}
