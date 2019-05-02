package techdomotica.objs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reporte {
    
    private int idReport = 0,
            idUser = 0,
            typeReport = 0;
    private String reportText;
    
    public Reporte(int id, int user, int report, String txt) {
        idReport = id;
        idUser = user;
        typeReport = report;
        reportText = txt;
    }
    
    public void setIDReport(int id) {
        idReport = id;
    }
    
    public int getIDReport() {
        return idReport;
    }
    
    public void setUserID(int id) {
        idUser = id;
    }
    
    public int getUserID(int id) {
        return idUser;
    }
    
    public void setReportType(int type) {
        typeReport = type;
    }
    
    public int getReportType() {
        return typeReport;
    }
    
    public void setReportText(String txt) {
        reportText = txt;
    }
    
    public String getReportText() {
        return reportText;
    }
    
    public static int insertReport(int user, int reportTyp, String txt) {
        Conectar conx = new Conectar();
        LocalDateTime time = LocalDateTime.now();
        String fecha = time.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        String hora = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return conx.execute(String.format("INSERT INTO reporte VALUES (null, %d, %d, '%s', '%s', '%s');", user, reportTyp, fecha, hora, txt));
    }
    
    
}