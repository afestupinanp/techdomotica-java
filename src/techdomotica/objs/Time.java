package techdomotica.objs;

public class Time extends Thread {
    
    private boolean continueThread = true;
    private int seconds, minutes, hours;
    @Override
    public void run() {
        seconds = java.time.LocalDateTime.now().getSecond();
        minutes = java.time.LocalDateTime.now().getMinute();
        hours = java.time.LocalDateTime.now().getHour();
        
        seconds = 50;
        minutes = 59;
        hours = 6;
        while(continueThread) {
            try {
                Thread.sleep(1000);
                seconds++;
                if(seconds == 60) {
                    seconds = 0;
                    minutes++;
                    if(minutes == 60) {
                        minutes = 0;
                        hours++;
                        if(hours == 24) {
                            hours = 0;
                        }
                    }
                }
                //currentDateFormatted = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern());
            } 
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void disableTimeThread() {
        continueThread = false;
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