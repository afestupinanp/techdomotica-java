package techdomotica.objs.comps;

public class ACondicionado extends Componente {
    
    private double temperatura = 23.0;
    
    public ACondicionado(String nombre, String marca, double uso) {
        super(nombre, marca, uso);
    }
    
    public ACondicionado(String nombre, String marca) {
        super(nombre, marca);
    }
    
    public double getTemperatura() {
        return temperatura;
    }
    
    public void changeTemperatura(double newtemp) {
        temperatura = newtemp;
    }
    
    
}