package techdomotica.objs.comps;

public class Televisor extends Componente {
    
    private String calidadTV = "HD";
    private String resolucion = "1280x720";
    
    public Televisor(String nombre, String marca, double uso) {
        super(nombre, marca, uso);
    }
    
    public Televisor(String nombre, String marca) {
        super(nombre, marca);
    }

    public String getCalidadTV() {
        return calidadTV;
    }

    public void setCalidadTV(String calidadTV) {
        this.calidadTV = calidadTV;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }
    
    
    
}