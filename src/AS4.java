

import java.util.ArrayList;
import java.util.Map;

public class AS4 extends AccionSemantica {
    private Float minValue = new Float(1.17549435E-38f);
    private Float maxValue = new Float(3.40282347E38f);

    public AS4(Lexico lexico) {
        super(lexico);
    }
        
    @Override
    public void ejecutar(Map<String,ArrayList<Object>> tablaSimbolos) {
        String token = lexico.getToken();
        token = token.substring(0,token.length()-1);
        token = token.replace(",", ".");
        Float flotante = Float.parseFloat(token);
        token = token.replace(".", ",");
        if (flotante != 0.0f)
            if ((flotante < minValue) || (flotante > maxValue)) {
                lexico.addError("Linea " + lexico.getNroLinea() + ": ERROR. La constante flotante " + token + " se encuentra fuera de rango");
                //System.out.println("ERROR en Linea " + lexico.getNroLinea() + ": La constante " + token + " se encuentra fuera de rango");
                lexico.setToken("");
                lexico.setEstadoActual(0);
                return;
            }
        lexico.setLeer(false);
        String lexema = token.toLowerCase(); //minuscula

        if (!tablaSimbolos.containsKey(token))
        {
            ArrayList<Object> atrFloat = new ArrayList<>(5);
            atrFloat.add(0,"CF");
            atrFloat.add(1,271);
            atrFloat.add(2,1);//CONTADOR DE REFERENCIAS
            tablaSimbolos.put(lexema, atrFloat);
        }
        else 
        {
            ArrayList<Object> atrFloat = tablaSimbolos.get(token);
            int i= (int)atrFloat.get(2);
            atrFloat.remove(2);
            i++;
            atrFloat.add(2, i);
            tablaSimbolos.put(token, atrFloat);
        }
        lexico.setToken(lexema);
    }
}
