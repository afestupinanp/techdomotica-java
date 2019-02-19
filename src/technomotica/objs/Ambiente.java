package technomotica.objs;

import java.util.ArrayList;
import technomotica.objs.comps.Componente;

public class Ambiente {
    
    private String nombre = "",
                  centro = "";
    private Admin adminEncargado = null;
    
    private ArrayList<Componente> componentes = null;
    
    public Ambiente(String name, String centre, ArrayList<Componente> comps) {
        nombre = name;
        centro = centre;
        componentes = comps;
        adminEncargado = null;
    }
    
    public Ambiente(String name, String centre, ArrayList<Componente> comps, Admin encargado) {
        nombre = name;
        centro = centre;
        componentes = comps;
        adminEncargado = encargado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public Admin getAdminEncargado() {
        return adminEncargado;
    }

    public void setAdminEncargado(Admin adminEncargado) {
        this.adminEncargado = adminEncargado;
    }

    public ArrayList<Componente> getComponentes() {
        return componentes;
    }
    
    public Componente getComponente(int index) {
        return componentes.get(index);
    }

    public void setComponentes(ArrayList<Componente> componentes) {
        this.componentes = componentes;
    }
    
    
    
}
