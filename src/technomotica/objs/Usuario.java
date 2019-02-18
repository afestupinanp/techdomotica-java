package technomotica.objs;

public class Usuario {
    
    private String nombre = "",
                   apellido = "",
                   correo = "";
    
    public Usuario(String name, String ape, String corre) {
        nombre = name;
        apellido = ape;
        correo = corre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
}