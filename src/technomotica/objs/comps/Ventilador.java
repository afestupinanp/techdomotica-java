package technomotica.objs.comps;

public class Ventilador extends Componente {
    
    private boolean encendido = false;
    private int rpm = 1500;
    
    private String posicion = "Techo";
    
    public Ventilador(String nombre, String marca, double uso) {
        super(nombre, marca, uso);
        encendido = false;
    }
    
    public void toggleEncendido() {
        encendido = !encendido;
    }
    
    public boolean getEncendidoState() {
        return encendido;
    }
    
    public void setRPM(int revs) {
        rpm = revs;
    }
    
    public int getRPM() {
        return rpm;
    }
    
    public void setPosicion(String pos) {
        posicion = pos;
    }
    
    public String getPosicion() {
        return posicion;
    }
    
}
