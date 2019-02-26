package technomotica.objs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Image;


public class JPanelImage extends JPanel{
    // if you wish to initialize it with greenBlock...
    private Image myImage = null;

    public JPanelImage() {
    }

    @Override
    protected void paintComponent(Graphics g){ 
        super.paintComponent(g);    
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(myImage, 0, 0, null);
    } 

    public void setBgImg(Image img) {
        this.myImage = img;
        repaint();
    }
}