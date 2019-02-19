package technomotica.objs.comps;

public class Persianas extends Componente {
    
    private boolean persianasAbiertas = false;
    
    public Persianas(String nombre, String marca, double uso) {
        super(nombre, marca, uso);
        persianasAbiertas = false;
    }
    
    public void togglePersianas() {
        persianasAbiertas = !persianasAbiertas;
    }
    
    public boolean getPersianasState() {
        return persianasAbiertas;
    }
    
}
