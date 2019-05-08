package techdomotica.objs;

public class Reporte {
    
    private int idReport = 0,
            idUser = 0,
            typeReport = 0;
    private String reportText,
            typeReportString;
    
    private String date,
            hour;
    
    public Reporte(int id, int user, int report, String txt) {
        idReport = id;
        idUser = user;
        typeReport = report;
        reportText = txt;
        typeReportString = null;
        
        date = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
        hour = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("YYYY-mm-dd"));
    }
    
    public Reporte(int id, int user, int report, String txt, String typeReportStr, String dateReport, String hourReport) {
        idReport = id;
        idUser = user;
        typeReport = report;
        reportText = txt;
        typeReportString = typeReportStr;
        date = dateReport;
        hour = hourReport;
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
    
    public void setReportTypeString(String txt) {
        typeReportString = txt;
    }
    
    public String getReportTypeString() {
        return typeReportString;
    }
    
    public String getReportDate() {
        return date;
    }
    
    public String getReportHour() {
        return hour;
    }
    
    public static int insertReport(int user, int reportTyp, String txt) {
        Conectar conx = new Conectar();
        java.time.LocalDateTime time = java.time.LocalDateTime.now();
        String fecha = time.format(java.time.format.DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        String hora = time.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
        int value = conx.execute(String.format("INSERT INTO reporte VALUES (null, %d, %d, '%s', '%s', '%s');", user, reportTyp, fecha, hora, txt));
        conx.destroyResultSet();
        conx.closeConnection();
        return value;
    }
    
    
}