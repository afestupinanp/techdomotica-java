package techdomotica.objs;

import java.sql.*;

public class Conectar {
    private static Connection conx;
    private static final String driver = "com.mysql.jdbc.Driver",
                                user = "root",//andres
                                pswd = "",//andres123
                                url = String.format("jdbc:mysql://%s:%s/techdomotica", new Config().getConfigKey("hostname"), new Config().getConfigKey("port"));
    private Statement ps;
    private ResultSet rs;
    
    public Conectar() {
        try {
            System.out.println("Initializing connection to " + url);
            Class.forName(driver);
            conx = DriverManager.getConnection(url, user, pswd);
            if(conx != null) {
                System.out.println("Connection established");
                ps = conx.createStatement();
            }
            else System.out.println("There was an error while trying to make a connection.");
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public boolean ping() {
        try {
            return conx.isValid(2);
        }
        catch(SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public int execute(String query) {
        System.out.println("Query: " + query);
        try {
            return ps.executeUpdate(query);
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int executeWithObjects(String query, Object... stuff) {
        System.out.println("Unparsed query: " + query);
        if(query.contains("?")) {
            System.out.print("Stuff parameters: ");
            for(int i = 0 ; i < stuff.length ; i++) {
                System.out.print(stuff[i].toString() + " ");
            }
            System.out.println(" - " + stuff.length);
            try {
                int count = 0;
                for(int i = 0 ; i < query.length() ; i++) {
                    if(query.charAt(i) == '?') {
                        count++;
                    }
                }
                //System.out.println("? Contados: " + count);
                if(count == stuff.length) {
                    PreparedStatement prepared = conx.prepareStatement(query);
                    for(int i = 0; i < stuff.length ; i++) {
                        prepared.setObject(i + 1, stuff[i]);
                    }
                    System.out.println("Query: " + prepared.toString());
                    return prepared.executeUpdate();
                }
                else System.out.println("Error: La cantidad de ? en query no coincide con la de los objetos pasados");
            }
            catch(SQLException e) {
                System.out.println(e);
            }
        }
        else System.out.println("Error: No se encontraron parametros (?) para cambiar.");
        return 0;
    }
    
    public boolean executeRSOne(String query) {
        System.out.println("Query: " + query);
        try {
            rs = ps.executeQuery(query);
            if(rs.next()) {
                return rs.first();
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean executeRS(String query) {
        System.out.println("Query: " + query);
        try {
            rs = ps.executeQuery(query);
            if(rs.next()) {
                if(rs != null) {
                    rs.beforeFirst();
                    return true;
                }
                else return false;
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public ResultSet getResultSet() {
        return rs;
    }
    
    public boolean nextRow() {
        try {
            return rs.next();
        }
        catch(SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public void setBeforeFirst() {
        try {
            rs.beforeFirst();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
    
    public Object getResultSetRow(String columnLabel) {
        try {
            if(!rs.isClosed() || rs != null) {
                return rs.getString(columnLabel);   
            }
            else return new Exception("se cerro uwu");
        }
        catch(SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public void destroyResultSet() {
        try {
            if(rs != null) {
                rs.close();
                System.out.println("ResultSet closed");
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
    
    public void closeConnection() {
        try {
            if(ping()) conx.close();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
    
}
