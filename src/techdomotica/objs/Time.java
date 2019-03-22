package techdomotica.objs;

public class Time extends Thread {
    
    
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } 
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}