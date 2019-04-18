package techdomotica.objs.comps;

public class Componente {
    
    private int deviceID = 0;
    
    private String nombreComponente = "";
    private String marcaComponente = "";
    
    private double usoComponente = 100.0;
    private double gastoEnergetico = 0.0;
    
    private boolean componenteEncendido = false;
    
    public Componente(String nombre, String marca, double uso) {
        nombreComponente = nombre;
        marcaComponente = marca;
        usoComponente = uso;
    }
    
    public Componente(String nombre, String marca) {
        nombreComponente = nombre;
        marcaComponente = marca;
        usoComponente = 100.0;
    }

    public String getNombreComponente() {
        return nombreComponente;
    }

    public void setNombreComponente(String nombreComponente) {
        this.nombreComponente = nombreComponente;
    }

    public String getMarcaComponente() {
        return marcaComponente;
    }

    public void setMarcaComponente(String marcaComponente) {
        this.marcaComponente = marcaComponente;
    }

    public double getUsoComponente() {
        return usoComponente;
    }

    public void setUsoComponente(double usoComponente) {
        this.usoComponente = usoComponente;
    }
    
    public void decrementarUsoComponente() {
        if(usoComponente > 0) usoComponente--;
    }
    
    public void setGastoEnergetico(double gastoEnergetico) {
        this.gastoEnergetico = gastoEnergetico;
    }
    
    public double getGastoEnergetico() {
        return gastoEnergetico;
    }

    public String getComponenteFullName() {
        return marcaComponente + " " + nombreComponente;
    }
    
    public void toggleComponenteEncendido() {
        componenteEncendido = !componenteEncendido;
    }
    
    public void toggleComponenteEncendido(boolean en) {
        componenteEncendido = en;
    }
    
    public boolean getComponenteEncendidoState() {
        return componenteEncendido;
    }
    
    public void setDeviceID(int id) {
        deviceID = id;
    }
    
    public int getDeviceID() {
        return deviceID;
    }
}