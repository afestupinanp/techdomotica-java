/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techdomotica.java.forms;

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
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import techdomotica.objs.Admin;
import techdomotica.objs.Config;
import techdomotica.objs.Ambiente;
import techdomotica.objs.Time;
import techdomotica.java.forms.gestorusuarios.Registrar;

/**
 * @author Andres
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Dispositivos
     */
    public boolean onSystemTray = false;
    
    private Thread mainChanger;
    
    private Admin adminEncargado = null;
    private Ambiente ambiente;
    
    
    private TrayIcon appSystemTray = null;
    
    private boolean[] warningDisplayed = new boolean[4];
    
    private Config cfg = new Config();
    
    public Main(Admin admin) {
        adminEncargado = admin;
        ambiente = new Ambiente(adminEncargado);
        initComponents();
        
        mainChanger = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(ambiente != null) {
                        Thread.sleep(2500);
                        changeAmbientWeather();
                        System.out.println("Personas in sala: " + ambiente.getPersonasInAmbiente());
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                tempAire.setText(String.format("Temperatura ajustada: %.1f°C", ambiente.getTemperaturaSala()));
                                tempAmbience.setText(String.format("Temperatura ambiente: %.1f°C", ambiente.getTemperaturaAmbiente()));
                                txPersonas.setText(String.format("Cantidad de personas: %d", ambiente.getPersonasInAmbiente()));
                            }
                        });
                    }
                } 
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            
            //Cambio de temperatura ambiente por medio del hilo del tiempo.
            public void changeAmbientWeather() {
                Time runTime = ambiente.getTimeThread();
                System.out.println(String.format("%02d:%02d:%02d", runTime.getHours(), runTime.getMinutes(), runTime.getSeconds()));
                if(runTime.getHours() >= 0 && runTime.getHours() <= 5) ambiente.setTemperaturaAmbiente(23);
                else if(runTime.getHours() >= 6 && runTime.getHours() <= 12) ambiente.setTemperaturaAmbiente(25);
                else if(runTime.getHours() >= 13 && runTime.getHours() <= 17) ambiente.setTemperaturaAmbiente(26);
                else if(runTime.getHours() >= 18 && runTime.getHours() <= 20) ambiente.setTemperaturaAmbiente(25);
                else if(runTime.getHours() >= 21 && runTime.getHours() <= 23) ambiente.setTemperaturaAmbiente(24);
                
                if(ambiente.getACondicionado(0) != null && ambiente.getACondicionado(1) != null) {
                    if(!ambiente.getACondicionado(0).getComponenteEncendidoState() || !ambiente.getACondicionado(1).getComponenteEncendidoState()) {
                        if(!warningDisplayed[0]) {
                            JOptionPane.showMessageDialog(null, "Atención: uno de los aires acondicionados del ambiente está apagado. Puede provocar problemas en la temperatura\nde la sala y provocar daños.", "Advertencia de temperatura", JOptionPane.WARNING_MESSAGE);
                            warningDisplayed[0] = true;
                        }
                    }
                    else if(ambiente.getACondicionado(0).getUsoComponente() <= 30.0 || ambiente.getACondicionado(1).getUsoComponente() <= 30.0) {
                        if(!warningDisplayed[1]) {
                            JOptionPane.showMessageDialog(null, "Atención: uno de los aires acondicionado del ambiente necesita mantenimiento. Si se daña, puede provocar problemas de temperatura y causar daños.", "Advertencia de temperatura", JOptionPane.WARNING_MESSAGE);
                            warningDisplayed[1] = true;
                        }
                    }
                }
                else {
                    if(!warningDisplayed[2]) {
                        JOptionPane.showMessageDialog(null, "Atención: falta la instalación de al menos un aire acondicionado de la sala. Entra en el gestor de dispositivos\npara agregar un dispositivo rápidamente.", "Advertencia por falta de aires acondicionados", JOptionPane.WARNING_MESSAGE);
                        warningDisplayed[2] = true;
                    }
                }
                if(ambiente.getTemperaturaAmbiente() >= 28.0) {
                    if(!warningDisplayed[3]) {
                        JOptionPane.showMessageDialog(null, "ATENCIÓN: La temperatura ambiental está sobre 28°C. Esta temperatura, con los equipos encendidos,\npodría ocasionar daños graves en los equipos que generen calor.", "Alerta crítica de temperatura", JOptionPane.WARNING_MESSAGE);
                        warningDisplayed[3] = true;
                    }
                }
                
                /*if((ambiente.getACondicionado(0) == null || ambiente.getACondicionado(0).getUsoComponente() <= 10.0) || ambiente.getACondicionado(1) == null) {
                    ambiente.setTemperaturaAmbiente(ambiente.getTemperaturaAmbiente());
                    if(!warningDisplayed[0]) {
                        JOptionPane.showMessageDialog(null, "Atención. no se dispone de uno o más aires acondicionados en el ambiente debido a que no\nhan sido asignados o presentan problemas y requieren mantenimiento. La temperatura\npodría incrementar y ocasionar daños en los equipos.", "Advertencia de temperatura", JOptionPane.WARNING_MESSAGE);
                        warningDisplayed[0] = true;
                    }
                    if(!warningDisplayed[1] && ambiente.getTemperaturaAmbiente() >= 28.0) {
                        JOptionPane.showMessageDialog(null, "ATENCIÓN: La temperatura ambiental está sobre 28°C. Esta temperatura, con los equipos encendidos,\npodría ocasionar daños graves en los equipos que generen calor.", "Alerta crítica de temperatura", JOptionPane.WARNING_MESSAGE);
                        warningDisplayed[1] = true;
                    }
                }
                else {
                    if(ambiente.getACondicionado(0).)
                }
                else ambiente.setTemperaturaAmbiente((ambiente.getTemperaturaAmbiente() + ambiente.getTemperaturaSala()) / 2);*/
            }
        });
        mainChanger.start();
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/resources/media/L4.png")).getImage());
        
        checkDeviceAvailability();
        /*camera1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/media/simulator/camera-r.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        camera2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/media/simulator/camera.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        camera3.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/media/simulator/camera-r.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        camera4.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/media/simulator/camera.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        */
        
        //sensor1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/media/simulator/sensor.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
    }
    
    public void checkDeviceAvailability() {
        checkCameraAvailability();
        checkSensorAvailability();
    }

    public void checkCameraAvailability() {
        JLabel cameras[] = {camera1, camera2, camera3, camera4};
        JMenuItem items[] = {itemCam1, itemCam2, itemCam3, itemCam4};
        for(int i = 0 ; i < 4 ; i++) {
            if(ambiente.getCamara(i) != null) {
                items[i].setEnabled(true);
                if(i % 2 == 0) cameras[i].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/media/simulator/camera-r.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                else cameras[i].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/media/simulator/camera.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            }
            else {
                cameras[i].setIcon(null);
                items[i].setEnabled(false);
                items[i].setToolTipText("Cámara no disponible. No está instalada.");
            }
        }
    }
    
    public void checkSensorAvailability() {
        if(ambiente.getSensor(0) != null) {
            sensor1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/media/simulator/sensor.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
            doorItem.setEnabled(true);
        }
        else {
            sensor1.setIcon(null);
            doorItem.setEnabled(false);
            doorItem.setToolTipText("Sensor no disponible: no está instalado.");
        }
        if(ambiente.getSensor(1) != null) {
            sensor2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/media/simulator/sensor.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
            sensorItem.setEnabled(true);
        }
        else {
            sensor2.setIcon(null);
            sensorItem.setEnabled(false);
            sensorItem.setToolTipText("Sensor no disponible: no está instalado.");
        }
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
        sensor1 = new javax.swing.JLabel();
        sensor2 = new javax.swing.JLabel();
        mapaSala = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tempAire = new javax.swing.JLabel();
        tempAmbience = new javax.swing.JLabel();
        txPersonas = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        itemCam1 = new javax.swing.JMenuItem();
        itemCam2 = new javax.swing.JMenuItem();
        itemCam3 = new javax.swing.JMenuItem();
        itemCam4 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        sensorItem = new javax.swing.JMenuItem();
        doorItem = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
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

        camera1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/media/simulator/camera.png"))); // NOI18N
        camera1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                camera1MouseClicked(evt);
            }
        });
        getContentPane().add(camera1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 50, 50));

        camera2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/media/simulator/camera.png"))); // NOI18N
        camera2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                camera2MouseClicked(evt);
            }
        });
        getContentPane().add(camera2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 50, 50));

        camera3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/media/simulator/camera.png"))); // NOI18N
        camera3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                camera3MouseClicked(evt);
            }
        });
        getContentPane().add(camera3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 50, 50));

        camera4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/media/simulator/camera.png"))); // NOI18N
        camera4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                camera4MouseClicked(evt);
            }
        });
        getContentPane().add(camera4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, 50, 50));

        sensor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sensor1MouseClicked(evt);
            }
        });
        getContentPane().add(sensor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, 30, 30));

        sensor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sensor2MouseClicked(evt);
            }
        });
        getContentPane().add(sensor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, 30, 30));

        mapaSala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/media/simulator/sala.png"))); // NOI18N
        getContentPane().add(mapaSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 380));

        tempAire.setText("Temperatura ajustada: 23,0°C");

        tempAmbience.setText("Temperatura ambiente: 26,0°C");

        txPersonas.setText("Cantidad de personas: 0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(tempAire, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(tempAmbience, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tempAmbience)
                    .addComponent(txPersonas)
                    .addComponent(tempAire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem7.setText("Menú de usuarios");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Gestión de dispositivos");

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem16.setText("Mostrar todos los dispositivos");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem16);

        jMenu7.setText("Cámaras");

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_0, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem8.setText("Todas");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem8);

        itemCam1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.ALT_MASK));
        itemCam1.setText("Cámara 1");
        itemCam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCam1ActionPerformed(evt);
            }
        });
        jMenu7.add(itemCam1);

        itemCam2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.ALT_MASK));
        itemCam2.setText("Cámara 2");
        itemCam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCam2ActionPerformed(evt);
            }
        });
        jMenu7.add(itemCam2);

        itemCam3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.ALT_MASK));
        itemCam3.setText("Cámara 3");
        itemCam3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCam3ActionPerformed(evt);
            }
        });
        jMenu7.add(itemCam3);

        itemCam4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.ALT_MASK));
        itemCam4.setText("Cámara 4");
        itemCam4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCam4ActionPerformed(evt);
            }
        });
        jMenu7.add(itemCam4);

        jMenu4.add(jMenu7);

        jMenu8.setText("Sensores");

        sensorItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        sensorItem.setText("Sensor de movimiento");
        jMenu8.add(sensorItem);

        doorItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        doorItem.setText("Seguro de puerta");
        doorItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doorItemActionPerformed(evt);
            }
        });
        jMenu8.add(doorItem);

        jMenu4.add(jMenu8);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Aires acondicionados");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Gestión de ambiente");

        jMenuItem17.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem17.setText("Configurar eventos");
        jMenu5.add(jMenuItem17);

        jMenuItem20.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem20.setText("Estadísticas de la sala");
        jMenu5.add(jMenuItem20);

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
        
    }//GEN-LAST:event_formComponentResized

    private void camera1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_camera1MouseClicked
        if(ambiente.getCamara(0) != null) cameraView("camara1", "Vista de cámara 1", ambiente.getCamara(0).getComponenteEncendidoState());
    }//GEN-LAST:event_camera1MouseClicked

    private void camera4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_camera4MouseClicked
        if(ambiente.getCamara(3) != null) cameraView("camara4", "Vista de cámara 4", ambiente.getCamara(3).getComponenteEncendidoState());
    }//GEN-LAST:event_camera4MouseClicked

    private void camera2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_camera2MouseClicked
        if(ambiente.getCamara(1) != null) cameraView("camara2", "Vista de cámara 2", ambiente.getCamara(1).getComponenteEncendidoState());
    }//GEN-LAST:event_camera2MouseClicked

    private void camera3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_camera3MouseClicked
        if(ambiente.getCamara(2) != null) cameraView("camara3", "Vista de cámara 3", ambiente.getCamara(2).getComponenteEncendidoState());
    }//GEN-LAST:event_camera3MouseClicked

    private void itemCam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCam1ActionPerformed
        cameraView("camara1", "Vista de cámara 1", ambiente.getCamara(0).getComponenteEncendidoState());
    }//GEN-LAST:event_itemCam1ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        CameraViewTodas todas = new CameraViewTodas(ambiente);
        todas.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void itemCam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCam2ActionPerformed
        cameraView("camara2", "Vista de cámara 2", ambiente.getCamara(1).getComponenteEncendidoState());
    }//GEN-LAST:event_itemCam2ActionPerformed

    private void itemCam3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCam3ActionPerformed
        cameraView("camara3", "Vista de cámara 3", ambiente.getCamara(2).getComponenteEncendidoState());
    }//GEN-LAST:event_itemCam3ActionPerformed

    private void itemCam4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCam4ActionPerformed
        cameraView("camara4", "Vista de cámara 4", ambiente.getCamara(3).getComponenteEncendidoState());
    }//GEN-LAST:event_itemCam4ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        ambiente.toggleAmbienteThread(false);
        //ambiente.toggleDeviceThread(false);
        DeviceManager dmanager = new DeviceManager(ambiente) {
            @Override
            public void saveChangesToMain() {
                super.saveChangesToMain();
                //System.out.println("We're out of here!");
                ambiente.toggleAmbienteThread(true);
                ambiente.toggleDeviceThread(true);
                
                ambiente.startAmbienteThread();
                //ambiente.startDeviceThread();
                checkDeviceAvailability();
                
            }
        };
        dmanager.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void doorItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doorItemActionPerformed
        SensorView view = new SensorView(ambiente);
        view.setVisible(true);
    }//GEN-LAST:event_doorItemActionPerformed

    private void sensor1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sensor1MouseClicked
        if(ambiente.getSensor(0) != null) {
            SensorView view = new SensorView(ambiente);
            view.setVisible(true);
        }
    }//GEN-LAST:event_sensor1MouseClicked

    private void sensor2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sensor2MouseClicked
        // TODO add your handling code here:
        if(ambiente.getSensor(1) != null) {
            SensorView view = new SensorView(ambiente);
            view.setVisible(true);
        }
    }//GEN-LAST:event_sensor2MouseClicked

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        ACView ac = new ACView(ambiente);
        ac.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void cameraView(String campath, String title, boolean ison) {
        CameraView camView = new CameraView(ambiente, campath, ison) {
            @Override
            public void handleClose() {
                super.handleClose();
                System.out.println("Llamado desde Main!");
            }
        };
        int camaraid = Character.getNumericValue(campath.charAt(campath.length() - 1)) - 1;
        camView.cameraViewNum.setText(title);
        camView.setTitle(title + " - Tech Domótica");
        camView.setVisible(true);
    }
    
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        Registrar res = new Registrar(ambiente);
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
            Image img = new ImageIcon(getClass().getResource("/resources/media/L4.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            
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
            
            appSystemTray = new TrayIcon(img, "Tech Dómotica", popmenu);
            
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
        adminEncargado = null;
        mainChanger.interrupt();
        ambiente.toggleTimeThread(false);
        ambiente.toggleAmbienteThread(false);
        ambiente = null;
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
                new Main(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel camera1;
    private javax.swing.JLabel camera2;
    private javax.swing.JLabel camera3;
    private javax.swing.JLabel camera4;
    private javax.swing.JMenuItem doorItem;
    private javax.swing.JMenuItem itemCam1;
    private javax.swing.JMenuItem itemCam2;
    private javax.swing.JMenuItem itemCam3;
    private javax.swing.JMenuItem itemCam4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel mapaSala;
    private javax.swing.JLabel sensor1;
    private javax.swing.JLabel sensor2;
    private javax.swing.JMenuItem sensorItem;
    private javax.swing.JLabel tempAire;
    private javax.swing.JLabel tempAmbience;
    private javax.swing.JLabel txPersonas;
    // End of variables declaration//GEN-END:variables
}