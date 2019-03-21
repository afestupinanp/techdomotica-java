package technomotica.objs.comps;

public class ACondicionado extends Componente {
    
    private double temperatura = 17.0;
    
    public ACondicionado(String nombre, String marca, double uso) {
        super(nombre, marca, uso);
    }
    
    public double getTemperatura() {
        return temperatura;
    }
    
    public void changeTemperatura(double newtemp) {
        temperatura = newtemp;
    }
    
    
}