package technomotica.objs;

public class Time extends Thread {
    
    public long currentTime = System.currentTimeMillis();
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                currentTime++;
            } 
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}