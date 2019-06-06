package techdomotica.objs;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import javax.swing.JOptionPane;

/**
 * Clase de funciones o métodos útiles. No es necesario instanciar esta clase, es una clase estática.
 * @author Andres
 */
public class Util {
    
    public static final String VERSION = "0.9-RC-2";
    
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
     * Este método permite cargar una fuente rápidamente para todos los elementos de un JFrame.
     * @param clase Clase a la que se le desea cargar el elemento Font.
     * @param path Fuente a cargar
     * @return Retorna un boolean indicando si se logró cambiar la fuente de todos los elementos del JFrame
     */
    public static boolean cargarFuente(javax.swing.JFrame clase) {
        try {
            java.awt.Font font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, techdomotica.java.forms.LoginPage.class.getResourceAsStream("/resources/fonts/Lato-Regular.ttf")).deriveFont(11f);
            javax.swing.plaf.FontUIResource fontR = new javax.swing.plaf.FontUIResource(font);
            
            java.util.Enumeration keys = javax.swing.UIManager.getDefaults().keys();
            while(keys.hasMoreElements()) {
                Object key = keys.nextElement();
                Object value = javax.swing.UIManager.get(key);
                if(value instanceof javax.swing.plaf.FontUIResource && !key.toString().equals("PasswordField.font")) {
                    javax.swing.UIManager.put(key, fontR);
                    System.out.println("Key " + key + " cambiada.");
                }
            }
            javax.swing.SwingUtilities.updateComponentTreeUI(clase);
        }
        catch(Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
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
    
    public static void openManual() {
        try {
            int conf = JOptionPane.showConfirmDialog(null, "El manual de usuario está disponible en manera de PDF desde Google Drive.\nSe abrirá una pestaña en tu navegador.", "Confirmación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(conf == JOptionPane.OK_OPTION) {
                if(java.awt.Desktop.isDesktopSupported() && java.awt.Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://drive.google.com/open?id=18SYZl7nY4pkEZOJaZZ66u_tqZpxTsady"));
                else {
                    conf = JOptionPane.showConfirmDialog(null, "El sistema operativo actual no soporta la redirección automática.\n¿Deseas copiar el enlace?", "Reintentando", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(conf == JOptionPane.YES_OPTION) {
                        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clip.setContents(new java.awt.datatransfer.StringSelection("https://drive.google.com/open?id=18SYZl7nY4pkEZOJaZZ66u_tqZpxTsady"), null);
                        JOptionPane.showMessageDialog(null, "Enlace copiado exitosamente en el portapapeles.", "Enlace copiado", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
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