package technomotica.objs;

import technomotica.objs.comps.*;

public class Ambiente {
    
    public Thread ambienteThread;
    
    private String perfilActual = "";
    private Admin adminEncargado = null;
    
    private double temperaturaSala = 0.0;

    private ACondicionado[] acondicionado = new ACondicionado[2];
    private Camara[] camaras = new Camara[4];
    private Luz[] luces = new Luz[12];
    private Sensor[] sensores = new Sensor[4];
    private Televisor proyector = null;
    
    public Ambiente(Admin encargado) {
        adminEncargado = encargado;
        for(int i = 0 ; i < 2 ; i++) {
            acondicionado[i] = new ACondicionado("Watercool 2", "LG", 0);
        }
        
        ambienteThread = new Thread(new Runnable() {
            
            double increment = acondicionado[0].getTemperatura();
            double increment2 = acondicionado[1].getTemperatura();
            
            @Override
            public void run() {
                
                while(true) {
                    try {
                        Thread.sleep(2500);
                        if(Math.round(Math.random()) == 1) {
                            increment += 0.02;
                            increment2 += 0.02;
                            System.out.println("Subio");
                        }
                        else {
                            increment -= 0.02;
                            increment2 -= 0.02;
                            System.out.println("Bajo");
                        }
                        
                        acondicionado[0].changeTemperatura(increment);
                        acondicionado[1].changeTemperatura(increment2);
                        temperaturaSala = (increment + increment2) / 2;
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            
        });
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
    
    public double getTemperaturaSala() {
        return temperaturaSala;
    }
}