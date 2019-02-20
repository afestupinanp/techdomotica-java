package technomotica.objs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Config {
    
    private Properties configHandler = null;
    private boolean configReaden = false;
    
    private String[] configKeys = null;
    
    /**
     * Crea una instancia de este objeto para leer las propiedades del archivo de configuración.
     */
    public Config() {
        openConfigFile();
    }
    
    /**
     * Es un método que permite abrir el archivo de configuración. 
     * Debe de ejecutarse para poder leer propiedades de settings.properties.
     * Este método se ejecuta por defecto en el método constructor de Config.
     */
    public void openConfigFile() {
        if(configReaden == false) {
            try {
                FileInputStream input = new FileInputStream("settings.properties");
                configHandler = new Properties();
                configHandler.load(input);
                configReaden = true;
                input.close();
            }
            catch(Exception e) {
                System.out.println("Error: " + e);
            }
        }
        else System.out.println("Ya hay un archivo de configuración abierto.");
    }
    
    /***
     * Obtiene una propiedad de la lista de configuración.
     * @param key La propiedad que se desea obtener en forma de String.
     * @return Retorna el valor de la propiedad en forma de String.
     */
    public String getConfigKey(String key) {
        if(configReaden) return configHandler.getProperty(key);
        else {
            System.out.println("No se puede acceder a la configuración. No hay un archivo abierto.");
            return null;
        }
    }
    
    /**
     * Cambiar un valor de propiedad y lo guarda inmediatamente.
     * @param key
     * @param value 
     */
    public void cambiarConfigKey(String key, String value) {
        try {
            System.out.println("Cambiando propiedad " + key + " a " + value);
            configHandler.setProperty(key, value);
            configHandler.store(new FileOutputStream("settings.properties"), value);
        }
        catch(java.io.IOException e) {
            System.out.println("Error " + e);
        }
    }
    
    /***
     * Cierra el archivo de configuración. Usar para liberar recursos.
     */
    public void closeConfigFile() {
        configHandler = null;
        configReaden = false;
    }
    
    
    
}