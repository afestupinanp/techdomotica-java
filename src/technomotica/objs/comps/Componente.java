package technomotica.objs.comps;

public class Componente {
    
    private String nombreComponente = "",
            marcaComponente = "";
    
    private double usoComponente = 100.0,
            gastoEnergetico = 0.0;
    
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
    
    public void setGastoEnergetico(double gastoEnergetico) {
        this.gastoEnergetico = gastoEnergetico;
    }
    
    public double getGastoEnergetico() {
        return gastoEnergetico;
    }

    public String getComponenteFullName() {
        return marcaComponente + " " + nombreComponente;
    }
    
}