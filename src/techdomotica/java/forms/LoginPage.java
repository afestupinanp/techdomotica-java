package techdomotica.java.forms;
//lol
import techdomotica.java.forms.screens.SplashScreen;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import techdomotica.objs.Admin;
import techdomotica.objs.Conectar;
import techdomotica.objs.Reporte;
import techdomotica.objs.Util;

public class LoginPage extends javax.swing.JFrame {

    private final Conectar conx;
    private int errors = 0;
    private boolean loggeable = true;
    
    public LoginPage() {
        conx = new Conectar();
        if(!conx.ping()) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar a la base de datos de Tech Domótica.\nAsegurate de que todos los campos en la configuración sean correctos.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        else {
            initComponents();

            ImageIcon img = new ImageIcon(new ImageIcon(getClass().getResource("/resources/media/L1.png")).getImage().getScaledInstance(240, 140, Image.SCALE_SMOOTH));
            //Solución por Tirz - StackOverflow: https://stackoverflow.com/a/32885963
            imagePlace.setIcon(img);

            setIconImage(new ImageIcon(getClass().getResource("/resources/media/L4.png")).getImage());

            setLocationRelativeTo(null);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        imagePlace = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Iniciar sesión - Tech Domotica");
        setBackground(new java.awt.Color(197, 208, 230));
        setIconImages(null);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Iniciar sesión en Tech Domotica");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Correo o cédula:");

        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUserKeyTyped(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Contraseña:");

        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPassKeyTyped(evt);
            }
        });

        jButton1.setText("Iniciar sesión");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Recuperar contraseña");

        jMenuBar1.setBackground(new java.awt.Color(197, 208, 230));

        jMenu1.setText("Archivo");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Acerca de");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        jMenuItem3.setText("Documentación");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        jMenuItem1.setText("Sobre Technomotica");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtUser)
                            .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(imagePlace, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(96, 96, 96))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagePlace, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        logIn();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        About about = new About(this, true);
        about.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO: Añadir código para abrir la documentación.

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void txtUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) logIn(); 
    }//GEN-LAST:event_txtUserKeyPressed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) logIn();
    }//GEN-LAST:event_txtPassKeyPressed

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserActionPerformed

    private void txtPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyTyped
     
    }//GEN-LAST:event_txtPassKeyTyped

    private void txtUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyTyped
       /*ConLogin Clogin = new ConLogin();
       Clogin.UsuarioSoloLetras(evt);
        */
    }//GEN-LAST:event_txtUserKeyTyped
   
    Main main;
    
    public void logIn() {
        if(loggeable) {
            String email = txtUser.getText().trim();
            char[] pswd = txtPass.getPassword();
            String pswdd = Util.SHA256(String.valueOf(pswd));
            if(!email.isEmpty() && pswd.length != 0) {
                if(Util.esCorreo(email)) {
                    if(conx.executeRSOne("SELECT id_usuario, dni, password, nom1, id_rol FROM usuario WHERE correo = '"+ email +"';")) {
                        String realPswd = String.valueOf(conx.getResultSetRow("password"));
                        System.out.println(String.format("%s - %s", realPswd, pswdd));
                        if(realPswd.equals(pswdd)) {
                            loggedPassed(email);
                        }
                        else {
                            errors++;
                            Reporte.insertReport(Integer.valueOf(String.valueOf(conx.getResultSetRow("id_usuario"))), 7, "Intento de inicio de sesión en la versión de Java en " + System.getProperty("os.name"));
                            if(errors == 3) {
                                loggeable = false;
                                JOptionPane.showMessageDialog(null, "La contraseña introducida es erronea.\nYa no tienes intentos disponibles. No podrás iniciar sesión por 1 minuto.", "Credencial incorrecta", JOptionPane.ERROR_MESSAGE);
                                jButton1.setEnabled(false);
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(60000);
                                            jButton1.setEnabled(true);
                                            loggeable = true;
                                            errors = 0;
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });
                                thread.start();
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "La contraseña introducida es erronea.\nIntentos disponibles: " + errors + "/3", "Credenciales incorrectas", JOptionPane.ERROR_MESSAGE);
                            }
                        }               
                    }
                    else JOptionPane.showMessageDialog(null, "El usuario ingresado no está registrado. Por favor, intentelo de nuevo.", "Credenciales incorrectas", JOptionPane.ERROR_MESSAGE);
                }
                else if(Util.esNumerico(email)) {
                    if(conx.executeRSOne("SELECT id_usuario, password, nom1, id_rol FROM usuario WHERE dni = '"+ email +"';")) {
                        String realPswd = String.valueOf(conx.getResultSetRow("password"));
                        System.out.println(String.format("%s - %s", realPswd, pswdd));
                        if(realPswd.equals(pswdd)) {
                            loggedPassed(email);
                        }
                        else {
                            errors++;
                            Reporte.insertReport(Integer.valueOf(String.valueOf(conx.getResultSetRow("id_usuario"))), 7, "Intento de inicio de sesión en la versión de Java en " + System.getProperty("os.name"));
                            if(errors == 3) {
                                loggeable = false;
                                JOptionPane.showMessageDialog(null, "La contraseña introducida es erronea.\nYa no tienes intentos disponibles. No podrás iniciar sesión por 1 minuto.", "Credencial incorrecta", JOptionPane.ERROR_MESSAGE);
                                jButton1.setEnabled(false);
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(60000);
                                            jButton1.setEnabled(true);
                                            loggeable = true;
                                            errors = 0;
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });
                                thread.start();
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "La contraseña introducida es erronea.\nIntentos disponibles: " + errors + "/3", "Credenciales incorrectas", JOptionPane.ERROR_MESSAGE);
                            }
                        }               
                    }
                    else JOptionPane.showMessageDialog(null, "Este documento de identificación no está registrado. Por favor, intentelo de nuevo.", "Credenciales incorrectas", JOptionPane.ERROR_MESSAGE);
                }
                else JOptionPane.showMessageDialog(null, "El texto ingresado en el campo de correo electrónico o cédula no corresponde a ninguno de los tipos requeridos.\nIntentelo de nuevo.", "Credenciales incorrectas", JOptionPane.ERROR_MESSAGE);               
                conx.destroyResultSet();
            }
            else JOptionPane.showMessageDialog(null, "Uno o más campos de texto están vacío. Rellenos e intentelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(null, "No puedes iniciar sesión. Has superado el número de intentos disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void loggedPassed(String mail) {
        //SHA256Str(String str)
        JOptionPane.showMessageDialog(null, "Bienvenido, " + String.valueOf(conx.getResultSetRow("nom1")) + ".", "Inicio de sesión correcto", JOptionPane.INFORMATION_MESSAGE);
        int role = Integer.parseInt(String.valueOf(conx.getResultSetRow("id_rol")));
        Reporte.insertReport(Integer.parseInt(String.valueOf(conx.getResultSetRow("id_usuario"))), 1, "Este usuario ha iniciado sesión en la versión de Java desde " + System.getProperty("os.name"));
        if(role == 1) {
            if(Util.esCorreo(mail)) conx.executeRSOne("SELECT * FROM usuario WHERE correo = '" + mail +"';");
            else if(Util.esNumerico(mail)) conx.executeRSOne("SELECT * FROM usuario WHERE dni = '" + mail +"';");
            Admin admin = new Admin(String.valueOf(conx.getResultSetRow("id_usuario")), String.valueOf(conx.getResultSetRow("nom1")), String.valueOf(conx.getResultSetRow("nom2")), String.valueOf(conx.getResultSetRow("apellido1")), String.valueOf(conx.getResultSetRow("apellido2")), String.valueOf(conx.getResultSetRow("correo")), String.valueOf(conx.getResultSetRow("dni")), String.valueOf(conx.getResultSetRow("password")));
            this.dispose();
            SplashScreen splash = new SplashScreen() {
            @Override
                public void onAlmost() {
                    super.onAlmost();
                    main = new Main(admin);
                }
                public void onComplete() {
                    super.onComplete();
                    main.setVisible(true);
                }
            };
            splash.setVisible(true);
        }
    }
    
    public void exit() {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de salir de Tech Domótica?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

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
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imagePlace;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
