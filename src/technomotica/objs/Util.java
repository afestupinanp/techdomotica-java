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
    public static boolean chequearString(String str) {
        return str.isEmpty();
    }
    
    /**
     * Versión múltiple del método chequearString.
     * @param str Argumentos N Strings a chequear.
     * @return Retorna si TODOS los strings están vacíos. True si lo están, false si al menos hay un String que no está vacio.
     */
    public static boolean chequearStrings(String... str) {
        boolean allEmpty = true;
        for(String st : str) {
            if(!chequearString(st)) {
                allEmpty = false;
                break;
            }
        }
        return allEmpty;
    }
    
}