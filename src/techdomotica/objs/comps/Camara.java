package techdomotica.objs.comps;

public class Camara extends Componente {
    
    private String resolucion = "HD",
                   ubicacion = "";
    
    public Camara(String nombre, String marca, double uso) {
        super(nombre, marca, uso);
    }
    
    public Camara(String nombre, String marca) {
        super(nombre, marca, 100.0);
    }
    
    public void setResolucion(String res) {
        resolucion = res;
    }
    
    public String getResolucion() {
        return resolucion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    
    
}
