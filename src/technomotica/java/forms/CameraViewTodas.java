/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package technomotica.java.forms;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 *
 * @author Andres
 */
public class CameraViewTodas extends javax.swing.JFrame {

    /**
     * Creates new form CameraView
     */
    
    
    public CameraViewTodas() {
        initComponents();
        setIconImage(new ImageIcon("src/technomotica/media/L4.png").getImage());
        
        camera1.setIcon(new ImageIcon(new ImageIcon("src/technomotica/media/simulator/camara1.png").getImage().getScaledInstance(340, 250, Image.SCALE_SMOOTH)));
        camera2.setIcon(new ImageIcon(new ImageIcon("src/technomotica/media/simulator/camara2.png").getImage().getScaledInstance(340, 250, Image.SCALE_SMOOTH)));
        camera3.setIcon(new ImageIcon(new ImageIcon("src/technomotica/media/simulator/camara3.png").getImage().getScaledInstance(340, 250, Image.SCALE_SMOOTH)));
        camera4.setIcon(new ImageIcon(new ImageIcon("src/technomotica/media/simulator/camara4.png").getImage().getScaledInstance(340, 250, Image.SCALE_SMOOTH)));
        
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup = new javax.swing.ButtonGroup();
        dateTime = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        camera1 = new javax.swing.JLabel();
        camera2 = new javax.swing.JLabel();
        camera3 = new javax.swing.JLabel();
        camera4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        comboCamara = new javax.swing.JComboBox<>();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vista de todas las cámaras - Tech Domótica");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dateTime.setText("00:00 - 01/01/1970");
        getContentPane().add(dateTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 510, 150, 20));

        jLabel3.setText("Hora y fecha:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 510, 90, 20));
        getContentPane().add(camera1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 340, 250));
        getContentPane().add(camera2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 250));
        getContentPane().add(camera3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 340, 250));
        getContentPane().add(camera4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 340, 250));

        jLabel1.setText("Seleccionar cámara:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, -1, 20));

        comboCamara.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cámara 1", "Cámara 2", "Cámara 3", "Cámara 4" }));
        getContentPane().add(comboCamara, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 510, 100, -1));

        btnGroup.add(jRadioButton1);
        jRadioButton1.setText("Desactivado");
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 510, -1, -1));

        btnGroup.add(jRadioButton2);
        jRadioButton2.setText("Activado");
        getContentPane().add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 510, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CameraViewTodas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CameraViewTodas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CameraViewTodas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CameraViewTodas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CameraViewTodas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JLabel camera1;
    private javax.swing.JLabel camera2;
    private javax.swing.JLabel camera3;
    private javax.swing.JLabel camera4;
    private javax.swing.JComboBox<String> comboCamara;
    private javax.swing.JLabel dateTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    // End of variables declaration//GEN-END:variables
}
