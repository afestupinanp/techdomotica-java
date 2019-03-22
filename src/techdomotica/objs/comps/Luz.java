package techdomotica.objs.comps;

public class Luz extends Componente {
    
    private boolean encendido = false;
    private double intensidad = 100.0;
    
    public Luz(String nombre, String marca, double uso) {
        super(nombre, marca, uso);
        encendido = false;
    }
    
    public Luz(String nombre, String marca) {
        super(nombre, marca);
    }
    
    
    public void toggleLuz() {
        encendido = !encendido;
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
    
}