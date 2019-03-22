/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package technomotica.java.forms;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import technomotica.objs.Admin;
import technomotica.objs.Config;
import technomotica.objs.Ambiente;
import technomotica.objs.Time;
import technomotica.java.forms.gestorusuarios.Registrar;

/**
 * @author Andres
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Dispositivos
     */
    public boolean onSystemTray = false;
    
    public Time runTime = new Time();
    public Thread mainChanger;
    
    private Admin adminEncargado = null;
    public Ambiente ambiente;
    
    
    private TrayIcon appSystemTray = null;
    
    private Config cfg = new Config();
    
    public Main() {
        initComponents();
        runTime.start();
        
        ambiente = new Ambiente(adminEncargado);
        ambiente.getAmbienteThread().start();
        
        mainChanger = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        Thread.sleep(2500);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                tempAire.setText(String.format("Temperatura de la sala: %.1f°C", ambiente.getTemperaturaSala()));
                            }
                        });
                    }
                } 
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            
        });
        mainChanger.start();
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/technomotica/media/L4.png").getImage());
        
        camera1.setIcon(new ImageIcon(new ImageIcon("src/technomotica/media/simulator/camera-r.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        camera2.setIcon(new ImageIcon(new ImageIcon("src/technomotica/media/simulator/camera.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        camera3.setIcon(new ImageIcon(new ImageIcon("src/technomotica/media/simulator/camera-r.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        camera4.setIcon(new ImageIcon(new ImageIcon("src/technomotica/media/simulator/camera.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        camera1 = new javax.swing.JLabel();
        camera2 = new javax.swing.JLabel();
        camera3 = new javax.swing.JLabel();
        camera4 = new javax.swing.JLabel();
        mapaSala = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tempAire = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Menú principal - Tech Domotica");
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        camera1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/technomotica/media/simulator/camera.png"))); // NOI18N
        camera1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                camera1MouseClicked(evt);
            }
        });
        getContentPane().add(camera1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 50, 50));

        camera2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/technomotica/media/simulator/camera.png"))); // NOI18N
        camera2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                camera2MouseClicked(evt);
            }
        });
        getContentPane().add(camera2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 50, 50));

        camera3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/technomotica/media/simulator/camera.png"))); // NOI18N
        camera3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                camera3MouseClicked(evt);
            }
        });
        getContentPane().add(camera3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 50, 50));

        camera4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/technomotica/media/simulator/camera.png"))); // NOI18N
        camera4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                camera4MouseClicked(evt);
            }
        });
        getContentPane().add(camera4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, 50, 50));

        mapaSala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/technomotica/media/simulator/sala.png"))); // NOI18N
        getContentPane().add(mapaSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 380));

        tempAire.setText("Temperatura de la sala: 23,0°C");

        jLabel2.setText("Temperatura real:");

        jLabel3.setText("Cantidad de personas: 0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tempAire, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tempAire)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 660, 30));

        jMenu1.setText("Archivo");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem4.setText("Cerrar sesión");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem5.setText("Configuración");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu6.setText("Perfil");

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem15.setText("Mi perfil");
        jMenu6.add(jMenuItem15);

        jMenuBar1.add(jMenu6);

        jMenu3.setText("Gestión de usuarios");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem6.setText("Registro de usuario");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenu10.setText("Modificar usuario");

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem13.setText("Modificar");
        jMenu10.add(jMenuItem13);

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem14.setText("Eliminar");
        jMenu10.add(jMenuItem14);

        jMenu3.add(jMenu10);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Gestión de dispostivos");

        jMenu7.setText("Vista de cámaras");

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_0, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem8.setText("Todas");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem8);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem9.setText("Cámara 1");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem9);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem10.setText("Cámara 2");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem10);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem11.setText("Cámara 3");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem11);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem12.setText("Cámara 4");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem12);

        jMenu4.add(jMenu7);

        jMenu8.setText("Sensores");
        jMenu4.add(jMenu8);

        jMenu9.setText("Aires acondicionados");

        jMenuItem7.setText("Gestionar");
        jMenu9.add(jMenuItem7);

        jMenu4.add(jMenu9);

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem16.setText("Mostrar todos los dispositivos");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem16);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Gestión de ambiente");

        jMenuItem17.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem17.setText("Configurar eventos");
        jMenu5.add(jMenuItem17);

        jMenuBar1.add(jMenu5);

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
        jMenuItem1.setText("Sobre Tech Domótica");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO: Añadir código para abrir la documentación.

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        About about = new About(this, true);
        about.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if(cfg.getConfigKey("daemon").equalsIgnoreCase("true")) {
            addAppToSystemTray();
            this.setVisible(false);
        }
        else exit();
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de cerrar sesión?\nSerás retornado al menú de inicio.", "¿Cerrar sesión?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(confirm == JOptionPane.YES_OPTION) {
            logOut();
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        openConfig();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formComponentResized

    private void camera1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_camera1MouseClicked
        cameraView("camara1", "Vista de cámara 1");
    }//GEN-LAST:event_camera1MouseClicked

    private void camera4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_camera4MouseClicked
        cameraView("camara4", "Vista de cámara 4");
    }//GEN-LAST:event_camera4MouseClicked

    private void camera2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_camera2MouseClicked
        cameraView("camara2", "Vista de cámara 2");
    }//GEN-LAST:event_camera2MouseClicked

    private void camera3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_camera3MouseClicked
        cameraView("camara3", "Vista de cámara 3");
    }//GEN-LAST:event_camera3MouseClicked

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        cameraView("camara1", "Vista de cámara 1");
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        CameraViewTodas todas = new CameraViewTodas();
        todas.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        cameraView("camara2", "Vista de cámara 2");
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        cameraView("camara3", "Vista de cámara 3");
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        cameraView("camara4", "Vista de cámara 4");
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        DeviceManager dmanager = new DeviceManager(ambiente);/* {
            @Override
            public void saveChangesToMain() {
                super.saveChangesToMain();
                ambiente = super.ambiente;
            }
        };*/
        dmanager.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void cameraView(String campath, String title) {
        CameraView camView = new CameraView() {
            @Override
            public void handleClose() {
                super.handleClose();
                System.out.println("Llamado desde Main!");
            }
        };
        camView.timeThread = runTime;
        camView.cameraView.setIcon(new ImageIcon(new ImageIcon("src/technomotica/media/simulator/" + campath + ".png").getImage().getScaledInstance(camView.cameraView.getSize().width, camView.cameraView.getSize().height, Image.SCALE_SMOOTH)));
        camView.cameraViewNum.setText(title);
        camView.setTitle(title + " - Tech Domótica");
        camView.setVisible(true);
    }
    
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        Registrar res = new Registrar();
        res.setVisible(true);
    }
    
    public Admin getAdminEncargado() {
        return adminEncargado;
    }
    
    public Ambiente getAmbiente() {
        return ambiente;
    }
    
    public void openConfig() {
        Configuration cfg = new Configuration(Main.this, true);
        cfg.addWindowListener(new WindowAdapter() {
            public void windowClosed() {
                //Main.this.cfg.refrescarFile();
                int confirm = JOptionPane.showConfirmDialog(null, "Tech Domotica debe de ser reiniciado para que los cambios surtan efecto.\n¿Deseas hacerlo ahora?", "Reinicio requerido", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        cfg.setVisible(true);
    }
    
    public void exit() {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de salir de Tech Domotica?", "Confirmación de salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    public void addAppToSystemTray() {
        if(SystemTray.isSupported() && !onSystemTray) {
            appSystemTray = null;
            SystemTray tray = SystemTray.getSystemTray();
            Image img = new ImageIcon("src/technomotica/media/L4.png").getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            //Image img = Toolkit.getDefaultToolkit().getImage("src/technomotica/media/L4.png");
            //Glitch: No hay imagen.
            
            PopupMenu popmenu = new PopupMenu();
            
            MenuItem dfItem = new MenuItem("Abrir aplicación");
            dfItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openApp();
                }
            });
            popmenu.add(dfItem);
            
            popmenu.addSeparator();
            
            MenuItem dfItem2 = new MenuItem("Cerrar sesión");
            dfItem2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de cerrar sesión?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(confirm == JOptionPane.YES_OPTION) {
                        logOut();
                    }
                }
                
            });
            popmenu.add(dfItem2);
            
            MenuItem dfItem3 = new MenuItem("Configuración");
            dfItem3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openConfig();
                }
            });
            popmenu.add(dfItem3);
            
            MenuItem dfItem4 = new MenuItem("Salir de la aplicación");
            dfItem4.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    exit();
                }
            });
            popmenu.add(dfItem4);
            
            appSystemTray = new TrayIcon(img, "Technomotica", popmenu);
            
            appSystemTray.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) {
                    if(e.getClickCount() == 2) openApp();
                }

                @Override
                public void mouseReleased(MouseEvent e) { }

                @Override
                public void mouseEntered(MouseEvent e) { }

                @Override
                public void mouseExited(MouseEvent e) { }
                
            });
            
            onSystemTray = true;
            try {
                tray.add(appSystemTray);
                appSystemTray.displayMessage("Tech Dómotica ha quedado en segundo plano.", "Tech Domótica se seguirá ejecutando en segundo plano. Puedes cerrarlo haciendo click en el icono de la barra de estado, en la barra de tareas.", TrayIcon.MessageType.INFO);
            }
            catch(AWTException e) {
                System.out.println("Error: " + e);
            }
            
        }
    }
    
    private void openApp() {
        this.setVisible(true);
        SystemTray tray = SystemTray.getSystemTray();
        tray.remove(appSystemTray);
        onSystemTray = false;
    }
    
    private void logOut() {
        LoginPage login = new LoginPage();
        login.setVisible(true);
        this.dispose();
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel camera1;
    private javax.swing.JLabel camera2;
    private javax.swing.JLabel camera3;
    private javax.swing.JLabel camera4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel mapaSala;
    private javax.swing.JLabel tempAire;
    // End of variables declaration//GEN-END:variables
}
