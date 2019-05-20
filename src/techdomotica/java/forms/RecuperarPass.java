package techdomotica.java.forms;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import techdomotica.objs.Conectar;

public class RecuperarPass extends javax.swing.JFrame {

    private Conectar conx = new Conectar();
    
    public RecuperarPass() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/resources/media/L4.png")).getImage());
        ImageIcon img = new ImageIcon(new ImageIcon(getClass().getResource("/resources/media/L1.png")).getImage().getScaledInstance(200, 110, Image.SCALE_SMOOTH));
        imgPlace.setIcon(img);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMail = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        imgPlace = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Recuperar contraseña - Tech Domótica");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Recuperación de contraseña");

        jLabel2.setText("<html>Para realizar un cambio de contraseña, debes de dar los siguientes campos.</html>");

        jLabel3.setText("Correo electrónico:");

        txtMail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMailKeyPressed(evt);
            }
        });

        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniKeyPressed(evt);
            }
        });

        jLabel4.setText("Cédula:");

        jButton1.setText("Cambiar contraseña");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMail)
                                    .addComponent(txtDni)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(imgPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(132, 132, 132))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(imgPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        validateStuff();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void validateStuff() {
        String obtainedCorreo = txtMail.getText();
        String obtainedDni = txtDni.getText();
        if(obtainedCorreo.isEmpty() || obtainedDni.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ninguno de los campos puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            if(conx.executeRS(String.format("SELECT correo, dni, id_usuario FROM usuario WHERE correo = '%s' AND dni = '%s' AND habilitado = 1;", obtainedCorreo, obtainedDni))) {
                if(conx.nextRow()) {
                    String correo = String.valueOf(conx.getResultSetRow("correo"));
                    String dni = String.valueOf(conx.getResultSetRow("dni"));
                    int usu = Integer.parseInt(String.valueOf(conx.getResultSetRow("id_usuario")));
                    conx.destroyResultSet();
                    if(correo.equals(obtainedCorreo) && dni.equals(obtainedDni)) {
                        String newContra = JOptionPane.showInputDialog(null, "Escribe tu nueva contraseña", "Cambio de contraseña aceptado", JOptionPane.INFORMATION_MESSAGE);
                        if(!newContra.isEmpty()) {
                            if(conx.execute(String.format("UPDATE usuario SET password = '%s' WHERE id_usuario = '%s';", techdomotica.objs.Util.SHA256(newContra), usu)) == 1) {
                                conx.closeConnection();
                                JOptionPane.showMessageDialog(null, "Contraseña cambiada. Se te redirigirá al inicio de sesión.", "Contraseña modificada", JOptionPane.INFORMATION_MESSAGE);
                                techdomotica.objs.Reporte.insertReport(usu, 9, "Se ha realizado un cambio de contraseña exitoso para este usuario desde la versión de Java en " + System.getProperty("os.name") + ".");
                                closeThis();
                            }
                            else JOptionPane.showMessageDialog(null, "Ha ocurrido un error durante el intento de cambio de contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else JOptionPane.showMessageDialog(null, "No puedes cambiar una contraseña por una contraseña vacía.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Las credenciales que has ingresado no son correctas.", "Error", JOptionPane.ERROR_MESSAGE);
                        techdomotica.objs.Reporte.insertReport(Integer.parseInt(String.valueOf(conx.getResultSetRow("id_usuario"))), 9, "Alguien ha intentado realizar un cambio de contraseña en la versión de Java en " + System.getProperty("os.name") + ".");
                     }
                }
            }
            JOptionPane.showMessageDialog(null, "Dicho usuario que has especificado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void txtMailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMailKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) validateStuff();
    }//GEN-LAST:event_txtMailKeyPressed

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) validateStuff();
    }//GEN-LAST:event_txtDniKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        closeThis();
    }//GEN-LAST:event_formWindowClosing

    public void closeThis() {
        this.dispose();
        onClose();
    }
    
    public void onClose() { }
    
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
            java.util.logging.Logger.getLogger(RecuperarPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecuperarPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecuperarPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecuperarPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecuperarPass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgPlace;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtMail;
    // End of variables declaration//GEN-END:variables
}
