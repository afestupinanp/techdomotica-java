package techdomotica.objs;

import java.sql.*;

public class Conectar {
    private static Connection conx;
    private static final String driver = "com.mysql.jdbc.Driver",
                                user = "root",
                                pswd = "",
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
                rs.beforeFirst();
                return true;
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
            return rs.getString(columnLabel);
        }
        catch(SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public void destroyResultSet() {
        try {
            if(rs != null) rs.close();
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
