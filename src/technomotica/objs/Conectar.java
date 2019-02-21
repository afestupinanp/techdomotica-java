package technomotica.objs;

import java.sql.*;

public class Conectar {

    private static Connection conn;
    
    private static final String driver = "com.mysql.jdbc.Driver",
                                usuario = "root",
                                contrasena = "",
                                url = "jdbc:mysql://" + new Config().getConfigKey("hostname") + ":" + new Config().getConfigKey("port") +"/technomotica";
    
    Statement ps;
    ResultSet rs;
    
    /**
     * Está clase realiza la conexión automáticamente una conexión a la base de daatos basado en
     * el archivo de settings.properties
     */
    public Conectar() {
        try {
            System.out.println("Intentando conexión a " + url);
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, contrasena);
            ps = conn.createStatement();
            if (conn != null) System.out.println("Conexion establecida a la base de datos.");
        } 
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error SQL: " + e);
        }
    }
    
    /**
     * Función que permite realizar una consulta de modificación.
     * Usar solamente en la clausula UPDATE.
     * @param sql: Query a enviar a la base de datos.
     * @return Retorna un booleano, mostrando si se ejecutó la base de datos.
     */
    public boolean modificar(String sql) {
        try {
            ps.executeUpdate(sql);
            return true;
        }
        catch(SQLException ex) {
            System.out.println("Error SQL: " + ex);
        }
        return false;
    }
    
    /**
     * Método especializado para realizar una consulta a la base de datos.
     * Usar solamente con la clausula de SELECT.
     * @param query: Consulta que contenga una clausula SELECT.
     * @return Retorna un ResultSet (conjunto de resultados) de la base de datos. Según la documentación, nunca retorna null.
     */
    public ResultSet consultarbd(String query) {
        //Redundante con eso de v2. Se puede verificar si se devuelven resultados.
        ResultSet rs = null;
        try {
            rs = ps.executeQuery(query);
        } 
        catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
        return rs;
    }

    /**
     * Método especializado para realizar una consulta con una clausula de 
     * DELETE.
     * @param sql: Query que contenga una clausula para DELETE.
     * @return Retorna un booleano para verificar si se ejecutó la consulta.
     */
    public boolean eliminar(String sql) {
        try {
            ps.executeUpdate(sql);
            return true;
        }
        catch (SQLException ex) {
            System.out.println("Error SQL: " + ex);
        }
        return false;
    } 
}
