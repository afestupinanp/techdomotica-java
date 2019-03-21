package technomotica.objs;

public class Time extends Thread {
    
    public long currentTime = System.currentTimeMillis();

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                increaseTime();
            } 
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void increaseTime() {
        //System.out.println("plus plus from" + this.getName());
        currentTime++;
    }
    
}