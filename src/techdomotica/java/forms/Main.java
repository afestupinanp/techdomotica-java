/**
 * 08/05/2019 ----
 * Querido Andrés del futuro,
 * quizás leas este código, o quizás no. Se sincero contigo mismo, ¿realmente has mejorado desde
 * este momento?
 * Aquí tiene es el putisimo código del proyecto del SENA de Tech Domótica.
 * Y a los que vienen de GitHub, alistense para un spanglish, porque no me defino que idioma quiero realmente usar para el
 * proyecto xd.
 * No me jodas.
 *  ----
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
import techdomotica.objs.TimeChecker;
import techdomotica.objs.Reporte;

import techdomotica.java.forms.devices.ACView;
import techdomotica.java.forms.devices.SensorView;
import techdomotica.java.forms.devices.CameraView;
import techdomotica.java.forms.devices.CameraViewTodas;
import techdomotica.java.forms.devices.DeviceHistory;
import techdomotica.java.forms.devices.DeviceManager;
import techdomotica.java.forms.events.EventScreen;
import techdomotica.java.forms.gestorusuarios.PerfilCreation;
import techdomotica.java.forms.gestorusuarios.PerfilesScreen;

import techdomotica.java.forms.gestorusuarios.Registrar;
import techdomotica.java.forms.gestorusuarios.Usuarios;
import techdomotica.java.forms.security.SecurityAddRep;

import techdomotica.java.forms.security.SecurityHistory;
import techdomotica.objs.Usuario;

public class Main extends javax.swing.JFrame {

    public boolean onSystemTray = false;

    private Thread mainChanger;
    private Thread[] appSync = new Thread[2];
    private Thread autosaveTimer;
    
    private Admin adminEncargado = null;
    private Usuario usuarioEncargado = null;
    
    private Ambiente ambiente;
    
    private boolean continueAutosaving = true;
    
    private TrayIcon appSystemTray = null;
    
    private boolean[] warningDisplayed = new boolean[4];
    
    private Config cfg;
    
    public Main(Admin admin) {
        adminEncargado = admin;
        addAppToSystemTray();
        ambiente = new Ambiente(adminEncargado, appSystemTray);
        cfg = ambiente.getConfig();
        startAutosaveTimer();
        initComponents();
        startMainThread();
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/resources/media/L4.png")).getImage());
        
        //startAppSyncThreads();
        checkDeviceAvailability();
    }
    
    public Main(Usuario usuario) {
        usuarioEncargado = usuario;
        addAppToSystemTray();
        ambiente = new Ambiente(usuarioEncargado, appSystemTray);
        cfg = ambiente.getConfig();
        startAutosaveTimer();
        initComponents();
        startMainThread();
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/resources/media/L4.png")).getImage());
        disableAdminOptions();
        
        //startAppSyncThreads();
        checkDeviceAvailability();        
    }
    
    private void startMainThread() {
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
                                tempAire.setText(String.format("Temperatura de sala: %.1f°C", ambiente.getTemperaturaSala()));
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
            
            //That's how an AI works my brave soul
            public void changeAmbientWeather() {
                TimeChecker runTime = ambiente.getTimeThread();
                System.out.println(String.format("%02d:%02d:%02d", runTime.getHours(), runTime.getMinutes(), runTime.getSeconds()));
                if(runTime.getHours() >= 0 && runTime.getHours() <= 5) ambiente.setTemperaturaAmbiente(23);
                else if(runTime.getHours() >= 6 && runTime.getHours() <= 12) ambiente.setTemperaturaAmbiente(25);
                else if(runTime.getHours() >= 13 && runTime.getHours() <= 17) ambiente.setTemperaturaAmbiente(26);
                else if(runTime.getHours() >= 18 && runTime.getHours() <= 20) ambiente.setTemperaturaAmbiente(25);
                else if(runTime.getHours() >= 21 && runTime.getHours() <= 23) ambiente.setTemperaturaAmbiente(24);
                
                if(ambiente.getACondicionado(0) != null && ambiente.getACondicionado(1) != null) {
                    if(!ambiente.getACondicionado(0).getComponenteEncendidoState() || !ambiente.getACondicionado(1).getComponenteEncendidoState()) {
                        if(!warningDisplayed[0]) {
                            appSystemTray.displayMessage("Alerta de temperatura", "Atención: uno de los aires acondicionados del ambiente está apagado. Puede provocar problemas en la temperatura\nde la sala y provocar daños.", TrayIcon.MessageType.WARNING);
                            warningDisplayed[0] = true;
                        }
                    }
                    else if(ambiente.getACondicionado(0).getUsoComponente() <= 30.0 || ambiente.getACondicionado(1).getUsoComponente() <= 30.0) {
                        if(!warningDisplayed[1]) {
                            appSystemTray.displayMessage("Aires acondicionados en mal estado", "Atención: uno de los aires acondicionado del ambiente necesita mantenimiento. Si se daña, puede provocar problemas de temperatura y causar daños.", TrayIcon.MessageType.WARNING);
                            warningDisplayed[1] = true;
                        }
                    }
                }
                else {
                    if(!warningDisplayed[2]) {
                        appSystemTray.displayMessage("Falta de aires acondicionados", "Atención: falta la instalación de al menos un aire acondicionado de la sala. Entra en el gestor de dispositivos para agregar un dispositivo rápidamente.", TrayIcon.MessageType.WARNING);
                        warningDisplayed[2] = true;
                    }
                }
                if(ambiente.getTemperaturaAmbiente() >= 28.0) {
                    if(!warningDisplayed[3]) {
                        appSystemTray.displayMessage("¡Alerta crítica de temperatura!", "ATENCIÓN: La temperatura ambiental está sobre 28°C. Esta temperatura, con los equipos encendidos,\npodría ocasionar daños graves en los equipos que generen calor.", TrayIcon.MessageType.WARNING);
                        JOptionPane.showMessageDialog(null, "ATENCIÓN: La temperatura ambiental está sobre 28°C. Esta temperatura, con los equipos encendidos,\npodría ocasionar daños graves en los equipos que generen calor.", "Alerta crítica de temperatura", JOptionPane.WARNING_MESSAGE);
                        warningDisplayed[3] = true;
                    }
                }
            }
        });
        mainChanger.start();
    }
    
    private void startAutosaveTimer() {
        System.out.println(String.format("Timer starting with the following: %d s (%d ms)", (Integer.parseInt(cfg.getConfigKey("autosavetimer")) * 60), (Integer.parseInt(cfg.getConfigKey("autosavetimer")) * 60) * (1000)));
        final int confT = Integer.parseInt(cfg.getConfigKey("autosavetimer")) * 60;
        autosaveTimer = new Thread(new Runnable() {
            @Override
            public void run() {
                while(continueAutosaving) {
                    for(int i = 0 ; i < confT ; i++ ) {
                        //Redundant, I know, but trust me, it just works.
                        if(continueAutosaving) {
                            try {
                                Thread.sleep(1000);
                                System.out.println("Executing... " + confT + ":00 from 0:" + i);
                                if(i == 59) {
                                    i = 0;
                                    saveAllDevices();
                                }
                            } catch (InterruptedException ex) {
                                System.out.println(ex);
                            }
                        }
                    }
                }
            }
        });
        autosaveTimer.start();
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
    
    private void startAppSyncThreads() {
        appSync[0] = new Thread(new Runnable() {
            @Override
            public void run() {
                while(continueAutosaving) {//This one never goes into false.
                    try {
                        for(int i = 0 ; i < 60 ; i++) {
                            Thread.sleep(1000);
                        }
                    }
                    catch(Exception e) {
                        System.out.println(e);
                    }
                }
            }
            
        });
        appSync[0].start();
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
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
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
        jMenu10 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
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

        tempAire.setText("Temperatura de sala: 23,0°C");

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

        jMenu6.setText("Perfiles");

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem15.setText("Mis perfiles");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem15);

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem14.setText("Crear un perfil");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem14);

        jMenuBar1.add(jMenu6);

        jMenu3.setText("Usuarios");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem6.setText("Registro de usuario");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem7.setText("Lista de usuarios");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Dispositivos");

        jMenu9.setText("Todos los dispositivos");

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("Gestionar dispositivos");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem10);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setText("Ver historial de dispositivos");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem11);

        jMenu4.add(jMenu9);

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
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem17);

        jMenuItem20.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem20.setText("Estadísticas de la sala");
        jMenu5.add(jMenuItem20);

        jMenuBar1.add(jMenu5);

        jMenu10.setText("Seguridad");

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setText("Ver historial de reportes");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem12);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem13.setText("Realizar un reporte");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem13);

        jMenuBar1.add(jMenu10);

        jMenu2.setText("Acerca de");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        jMenuItem3.setText("Manual de usuario");
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
        try {
            int conf = JOptionPane.showConfirmDialog(null, "El manual de usuario está disponible en manera de PDF desde Google Drive.\nSe abrirá una pestaña en tu navegador.", "Confirmación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(conf == JOptionPane.OK_OPTION) {
                if(java.awt.Desktop.isDesktopSupported()) java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://drive.google.com/open?id=18SYZl7nY4pkEZOJaZZ66u_tqZpxTsady"));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        About about = new About(this, true);
        about.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //Not really a daemon but ok... Guess I'll work with this.
        if(cfg.getConfigKey("daemon").equalsIgnoreCase("true")) {            
            appSystemTray.displayMessage("Tech Domótica se ha minimizado", "Tech Domótica se seguirá ejecutando en segundo plano. Usa este icono para abrir de nuevo la aplicación.", TrayIcon.MessageType.INFO);
            this.setVisible(false);
        }
        else exit();
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de cerrar sesión?\nSerás retornado al menú de inicio. Los estados de los dispositivos se guardarán automáticamente.", "¿Cerrar sesión?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(confirm == JOptionPane.YES_OPTION) {
            ambiente.saveAllDevicesFromQuit();
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
        Usuarios usuario = new Usuarios(ambiente);
        usuario.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        DeviceManager manager = new DeviceManager(ambiente){
            @Override
            public void saveChangesToMain() {
                super.saveChangesToMain();
                saveAllDevices();
                
                ambiente.toggleAmbienteThread(true);
                ambiente.toggleDeviceThread(true);
                
                ambiente.startAmbienteThread();
                
                checkDeviceAvailability();
                
            }
        };
        manager.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        SecurityHistory historial = new SecurityHistory(ambiente);
        historial.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        SecurityAddRep modal = new SecurityAddRep(this, true, ambiente);
        modal.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        DeviceHistory devices = new DeviceHistory(ambiente);
        devices.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        PerfilesScreen screen = new PerfilesScreen(ambiente);
        screen.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        PerfilCreation creation = new PerfilCreation(this, true, ambiente);
        creation.setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        EventScreen screen = new EventScreen(ambiente);
        screen.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void saveAllDevices() {
        System.out.println("Autosaving...");
        ambiente.saveAllDevicesFromQuit();
    }
    
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
    
    public Usuario getUsuarioEncargado() {
        return usuarioEncargado;
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
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de salir de Tech Domotica? Los estados de los dispositivos se guardarán automáticamente.", "Confirmación de salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(confirm == JOptionPane.YES_OPTION) {
            if(adminEncargado != null) Reporte.insertReport(Integer.parseInt(adminEncargado.getID()), 6, "Este usuario ha cerrado sesión en la versión de Java desde " + System.getProperty("os.name") + ".");
            else Reporte.insertReport(Integer.parseInt(usuarioEncargado.getID()), 6, "Este usuario ha cerrado sesión en la versión de Java desde " + System.getProperty("os.name") + ".");
            ambiente.saveAllDevicesFromQuit();
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
                appSystemTray.displayMessage("¡Tech Domótica está aquí también!", "Cuando sea necesario, Tech Domótica usará este icono para mostrarte mensajes que necesites.", TrayIcon.MessageType.INFO);
            }
            catch(AWTException e) {
                System.out.println("Error: " + e);
            }
            
        }
    }
    
    private void openApp() {
        if(this.isVisible()) {
            this.requestFocus();
        }
        else {
            this.setVisible(true);
            this.requestFocus();
        }
    }
    
    private void logOut() {
        if(adminEncargado != null) Reporte.insertReport(Integer.parseInt(adminEncargado.getID()), 6, "Este usuario ha cerrado sesión en la versión de Java desde " + System.getProperty("os.name") + ".");
        else Reporte.insertReport(Integer.parseInt(usuarioEncargado.getID()), 6, "Este usuario ha cerrado sesión en la versión de Java desde " + System.getProperty("os.name") + ".");
        SystemTray tray = SystemTray.getSystemTray();
        tray.remove(appSystemTray);
        appSystemTray = null;
        LoginPage login = new LoginPage();
        login.setVisible(true);
        adminEncargado = null;
        usuarioEncargado = null;
        mainChanger.interrupt();
        ambiente.toggleTimeThread(false);
        ambiente.toggleAmbienteThread(false);
        ambiente.toggleDeviceThread(false);
        continueAutosaving = false;
        autosaveTimer.interrupt();
        ambiente = null;
        this.dispose();
    }
    
    private void disableAdminOptions() {
        jMenuItem5.setVisible(false);//Archivo - Configuración
        jMenu6.setVisible(false);//Perfiles
        jMenu3.setVisible(false);//Usuarios
        jMenuItem11.setVisible(false);//Dispositivos - Todos los dispositivos - Historial de dispositivos
        jMenu7.setVisible(false);//Dispositivos - Cámaras
        jMenu8.setVisible(false);//Dispositivos - Sensores
        jMenu8.setVisible(false);//Dispositivos - Aires acondicionados
        jMenu5.setVisible(false);//Seguridad
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //Nimbus is fucking awful looking.
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
