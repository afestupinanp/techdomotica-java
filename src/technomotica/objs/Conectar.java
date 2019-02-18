
package proyecto;

import java.sql.*;

public class Conectar {

    private static Connection conn;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String usuario = "root";
    private static final String contraseña = "";
    private static final String url = "jdbc:mysql://localhost:3306/usuarios";
    Statement ps;
    ResultSet rs;
    boolean v1 =false;
    boolean v2 =false;
    boolean v3 =false;

    public Conectar() {
        try {
            System.out.println("Conectando...");
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, contraseña);
            ps = conn.createStatement();
            if (conn != null) {
                System.out.println("Conexion establecida..");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error " + e);
        }
    }

    public void modificar(String sql) {
        try {
            ps.executeUpdate(sql);
            v1=true;
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
            v1=false;
        }
    }
    
    public ResultSet consultarbd(String query) {
    ResultSet rs= null;
        try {
            rs=ps.executeQuery(query);
            v2=true;
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
            v2=true;
        }return rs;
    }

    public void eliminar(String sql) {
        try {
            ps.executeUpdate(sql);
            v3=true;
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
            v3=true;
        }
    }

    public boolean isV1() {
        return v1;
    }

    public boolean isV2() {
        return v2;
    }

    public boolean isV3() {
        return v3;
    }
    
    
}
