package techdomotica.objs;
/**
 * Clase de funciones o métodos útiles. No es necesario instanciar esta clase, es una clase estática.
 * @author Andres
 */
public class Util {
    
    public static final String VERSION = "0.6.1-beta";
    
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
    
    /**
     * Este método permite cargar una fuente rápidamente.
     * @param clase Clase a la que se le desea cargar el elemento Font.
     * @param path Fuente a cargar
     * @param tamano Tamaño de la fuente
     * @return Retorna un objeto de tipo Font.
     */
    public static java.awt.Font cargarFuente(Object clase, String path, float tamano) {
        try {
            return java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, clase.getClass().getResourceAsStream(path)).deriveFont(tamano);
        }
        catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    /**
     * Este método permite cargar una fuente rápidamente.
     * @param clase Clase a la que se le desea cargar el elemento Font.
     * @param path Fuente a cargar
     * @return Retorna un objeto de tipo Font.
     */
    public static java.awt.Font cargarFuente(Object clase, String path) {
        try {
            return java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, clase.getClass().getResourceAsStream(path));
        }
        catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    /**
     * Método para encriptar un String a SHA-256 usando el MessageDigest proporcionado por Apache Commons.
     * @param str Cadena a encriptar.
     * @return Array con caracteres encriptados de SHA-256.
     */
    public static String SHA256(String str) {
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(str);
    }
    
    /**
     * Este método sirve para convertir un objeto a un int, pasando por chequeos de nulidad.
     * @param obj Objeto a convertir
     * @return Número entero. Retorna 0 si no se puede convertir.
     */
    public static int parseInteger(Object obj) {
        if(obj != null) {
            String str = String.valueOf(obj);
            if(str != null) return Integer.parseInt(str);
        }
        return 0;
    }
    
//    /**
//     * Método para reemplazar los caracteres especiales de saltos de línea de un string en caracteres que pueda interpretar
//     * Java.
//     * Windows usa \r\n
//     * Mac usa \n
//     * Linux usa \n
//     * @param str Cadena a cambiar los saltos de línea.
//     * @return Retorna una cadena para guardar en una base de datos con el CRLF propio y más compatible de Java \n.
//     */
//    public static String cambiarSaltosLinea(String str) {
//        return str.replace("\\r|\\n", "\n");
//    }
}