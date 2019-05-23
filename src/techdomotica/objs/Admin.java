package techdomotica.objs;

public class Admin extends Usuario {
    
    private Perfil perfilActual = null;
    
    public Admin(String id_usu, String name1, String name2, String ape1, String ape2, String corre, String doc, String con) {
        super(id_usu, name1, name2, ape1, ape2, corre, doc, con);
        loadDefaultPerfil();
    }

    public void loadDefaultPerfil() {
        Conectar conx = new Conectar();
        if(conx.executeRSOne("SELECT * FROM perfil WHERE id_usuario = " + getID() + " AND habilitado = 2 LIMIT 1;")) {
//            System.out.println("id_perfil " + conx.getResultSetRow("id_perfil"));
//            System.out.println("temp1 " + conx.getResultSetRow("temp1"));
//            System.out.println("temp2 " + conx.getResultSetRow("temp2"));
            perfilActual = new Perfil(Util.parseInteger(conx.getResultSetRow("id_perfil")), Util.parseInteger(conx.getResultSetRow("temp1")), Util.parseInteger(conx.getResultSetRow("temp2")), (Util.parseInteger(conx.getResultSetRow("temp1_on")) == 1), (Util.parseInteger(conx.getResultSetRow("temp2_on")) == 1), (Util.parseInteger(conx.getResultSetRow("proyector_on")) == 1), (Util.parseInteger(conx.getResultSetRow("sensor1_on")) == 1), (Util.parseInteger(conx.getResultSetRow("sensor2_on")) == 1));
        }
        if(conx.getResultSet() != null) conx.destroyResultSet();
        conx.closeConnection();
    }
    
    public Perfil getPerfilActual() {
        return perfilActual;
    }
    
}