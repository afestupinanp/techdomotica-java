package technomotica.objs.comps;

public class Camara extends Componente {
    
    private String resolucion = "HD",
                   ubicacion = "";
    
    public Camara(String nombre, String marca, double uso) {
        super(nombre, marca, uso);
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
