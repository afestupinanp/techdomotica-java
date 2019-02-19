package technomotica.objs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Config {
    
    private static Properties configHandler = null;
    private static boolean configReaden = false;
    
    private String[] configKeys = null;
    
    public static void openConfigFile() {
        if(configReaden == false) {
            try {
                FileInputStream input = new FileInputStream("config.properties");
                configHandler = new Properties();
                configHandler.load(input);
                input.close();
            }
            catch(Exception e) {
                System.out.println("Error: " + e);
            }
        }
        else System.out.println("Ya hay un archivo de configuración abierto.");
    }
    
    public String getConfigKey(String key) {
        if(configReaden) return configHandler.getProperty(key);
        else {
            System.out.println("No se puede acceder a la configuración. No hay un archivo abierto.");
            return null;
        }
    }
    
    public void closeConfigFile() {
        configHandler = null;
        configReaden = false;
    }
    
}