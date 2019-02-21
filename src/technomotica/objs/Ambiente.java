package technomotica.objs;

import technomotica.objs.comps.*;

public class Ambiente {
    
    private String perfilActual = "";
    private Admin adminEncargado = null;
    
    private ACondicionado[] acondicionado = new ACondicionado[2];
    private Camara[] camaras = new Camara[4];
    private Luz[] luces = new Luz[12];
    private Sensor[] sensores = new Sensor[4];
    private Televisor proyector = null;
    
    public Ambiente(Admin encargado) {
        adminEncargado = encargado;
    }
    
    
    
    public void cargarPerfil() {
        //Conectar con = new Conectar();
        //con.consultarbd("SELECT * FROM ...");
    }
    
    public ACondicionado getACondicionado(int index) {
        return acondicionado[index];
    }
    
    public Camara getCamara(int index) {
        return camaras[index];
    }
    
    public Luz getLuz(int index) {
        return luces[index];
    }
    
    public Sensor getSensor(int index) {
        return sensores[index];
    }
    
    public Televisor getTelevisor() {
        return proyector;
    }
}