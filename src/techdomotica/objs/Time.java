package techdomotica.objs;

public class Time extends Thread {
    
    private int seconds, minutes, hours;
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                seconds = java.time.LocalDateTime.now().getSecond();
                minutes = java.time.LocalDateTime.now().getMinute();
                hours = java.time.LocalDateTime.now().getHour();
                //currentDateFormatted = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern());
            } 
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public int getSeconds() {
        return seconds;
    }
    
    public int getMinutes() {
        return minutes;
    }
    
    public int getHours() {
        return hours;
    }
}