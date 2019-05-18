package techdomotica.objs;

public class Perfil {
    
    private int perfilID = 0;
    private int tempAire1 = 0;
    private int tempAire2 = 0;
    
    private boolean proyectorOn = false;
    private boolean aire1On = false;
    private boolean aire2On = false;
    
    public Perfil(int pID, int ac1t, int ac2t, boolean tvOn, boolean ac1On, boolean ac2On) {
        tempAire1 = 0;
        tempAire2 = 0;
        perfilID = pID;

        proyectorOn = false;
        aire1On = false;
        aire2On = false;
    }

    public int getTempAire1() {
        return tempAire1;
    }

    public void setTempAire1(int tempAire1) {
        this.tempAire1 = tempAire1;
    }

    public int getTempAire2() {
        return tempAire2;
    }

    public void setTempAire2(int tempAire2) {
        this.tempAire2 = tempAire2;
    }

    public boolean isProyectorOn() {
        return proyectorOn;
    }

    public void setProyectorOn(boolean proyectorOn) {
        this.proyectorOn = proyectorOn;
    }

    public boolean isAire1On() {
        return aire1On;
    }

    public void setAire1On(boolean aire1On) {
        this.aire1On = aire1On;
    }

    public boolean isAire2On() {
        return aire2On;
    }

    public void setAire2On(boolean aire2On) {
        this.aire2On = aire2On;
    }

    public int getPerfilID() {
        return perfilID;
    }

    public void setPerfilID(int perfilID) {
        this.perfilID = perfilID;
    }
    
    
    
}