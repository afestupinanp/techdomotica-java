package technomotica.objs;

/**
 * Clase de funciones o métodos útiles. No es necesario instanciar esta clase, es una clase estática.
 * @author Andres
 */
public class Util {
    /**
     * Chequea si el String está vacío.
     * @param str String a verificar.
     * @return Retorna true si está vacío. False si no lo está.
     */
    public static boolean stringVacio(String str) {
        return str.isEmpty();
    }
    
    /**
     * Chequea si todos los argumentos Strings N están vacios. Todos los campos son obligatorios.
     * @param str Argumentos str N a verificar.
     * @return Retorna verdadero/falso dependiendo si todos los strings están vacios.
     */
    public static boolean stringsVacios(String... str) {
        for(String s : str) {
            if(s.isEmpty()) return true;
        }
        return false;
    }
    
    /**
     * Chequea si todos los argumentos Strings N están vacíos. Se necesita al menos un campo lleno.
     * @param str
     * @return 
     */
    public static boolean alMenosUnString(String... str) {
        boolean hayUno = false;
        for(String s : str) {
            if(!s.isEmpty()) {
                hayUno = true;
            }
        }
        return hayUno;
    }
    
    /**
     * Este método chequea si el String contiene un email.
     * @param str Cadena de texto a comprobar si es correo.
     * @return Retorna verdadero/falso dependiendo de si se obtuvo el correo correctamente.
     */
    public static boolean esCorreo(String str) {
        //Expresión regular/Regular expression sacado de emailregex.com
        return str.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }
    
    /**
     * Este método chequea si el String es numérico.
     * @param str Cadena de texto a verificar si es numérica.
     * @return Retorna verdadero/falso dependiendo de si es numérico.
     */
    public static boolean esNumerico(String str) {
        //Expresión regular sacada de StackOverflow: https://stackoverflow.com/a/1102916
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    
}