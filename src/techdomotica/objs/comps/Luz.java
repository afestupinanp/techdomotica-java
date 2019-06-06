package techdomotica.objs.comps;

public class Luz {
    
    private int idConfig = 0;
    private boolean encendido = false;
    private double intensidad = 5;
    
    public Luz(boolean encen, double inten, int id) {
        idConfig = id;
        encendido = encen;
        intensidad = inten;
    }
    
    public void toggleLuz() {
        encendido = !encendido;
    }
    
    public void toggleLuz(boolean onoff) {
        encendido = onoff;
    }
    
    public boolean getLuzState() {
        return encendido;
    }
    
    public void cambiarIntensidad(double intensity) {
        intensidad = intensity;
    }
    
    public double getIntensidad() {
        return intensidad;
    }
    
    public int getID() {
        return idConfig;
    }
    
}