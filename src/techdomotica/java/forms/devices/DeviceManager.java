package techdomotica.java.forms.devices;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import techdomotica.java.forms.screens.progressDialog;
import techdomotica.objs.Ambiente;
import techdomotica.objs.Config;
import techdomotica.objs.Util;
import techdomotica.objs.comps.ACondicionado;
import techdomotica.objs.comps.Camara;
import techdomotica.objs.comps.Componente;
import techdomotica.objs.comps.Sensor;
import techdomotica.objs.comps.Televisor;

/**
 *
 * @author Andres
 */
public class DeviceManager extends javax.swing.JFrame {

    public Ambiente ambiente;
   
    private Thread changeUI;
    private Thread changeUI2;
    private boolean continueOnThread = true;
    
    public DeviceManager(Ambiente amb) {
        initComponents();
        ambiente = amb;
        removeAdminStuff();
        setIconImage(new ImageIcon(getClass().getResource("/resources/media/L4.png")).getImage());
        setLocationRelativeTo(null);
        
        checkComponentUse();
        
        changeUI = new Thread(new Runnable() {
            @Override
            public void run() {
                int rate = Integer.parseInt(new Config().getConfigKey("deteriorationprogress"));
                if(rate == 0) rate = 1;
                while(continueOnThread) {
                    try {
                        Thread.sleep(600000 / rate);
                        JProgressBar progressDevices[][] = {{deviceprogressac1, deviceprogressac2}, {deviceprogresscam1, deviceprogresscam2, deviceprogresscam3, deviceprogresscam4}, {deviceprogresssensor1, deviceprogresssensor2}, {deviceprogressprojector}};
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                for(int i = 0 ; i < 2 ; i++) {
                                    if(ambiente.getACondicionado(i) != null) {
                                        if(ambiente.getACondicionado(i).getComponenteEncendidoState()) progressDevices[0][i].setValue((int)ambiente.getACondicionado(i).getUsoComponente());
                                    }
                                }

                                for(int i = 0 ; i < 4 ; i++) {
                                    if(ambiente.getCamara(i) != null) {
                                        if(ambiente.getCamara(i).getComponenteEncendidoState()) progressDevices[1][i].setValue((int)ambiente.getCamara(i).getUsoComponente());
                                    }
                                }

                                for(int i = 0 ; i < 2 ; i++) {
                                    if(ambiente.getSensor(i) != null) {
                                        if(ambiente.getSensor(i).getComponenteEncendidoState()) progressDevices[2][i].setValue((int)ambiente.getSensor(i).getUsoComponente());
                                    }
                                }

                                if(ambiente.getTelevisor() != null) {
                                    if(ambiente.getTelevisor().getComponenteEncendidoState()) progressDevices[3][0].setValue((int)ambiente.getTelevisor().getUsoComponente());
                                }
                            }
                        });
                    }    
                    catch(InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        });
        changeUI2 = new Thread(new Runnable() {
            @Override
            public void run() {
                JRadioButton allButtons[][][] = {{{rdbtnonac1, rdbtnoffac1}, {rdbtnonac2, rdbtnoffac2}}, {{rdbtnoncam1, rdbtnoffcam1}, {rdbtnoncam2, rdbtnoffcam2}, {rdbtnoncam3, rdbtnoffcam3}, {rdbtnoncam4, rdbtnoffcam4}}, {{rdbtnonsensor1, rdbtnoffsensor1}, {rdbtnonsensor2, rdbtnoffsensor2}}, {{rdbtnprojectoron, rdbtnprojectoroff}}};
                ACondicionado[] acondicionados = ambiente.getACondicionadoAsArray();
                Sensor[] sensores = ambiente.getSensorAsArray();
                Camara[] camaras = ambiente.getCamaraAsArray();
                Televisor proyector = ambiente.getTelevisor();
                
                while(continueOnThread) {
                    try {
                        Thread.sleep(2000);
                        System.out.println("bruh");
                        for(int i = 0 ; i < allButtons.length ; i++) {
                            switch(i) {
                                case 0:
                                    for(int j = 0 ; j < allButtons[i].length ; j++) {
                                        if(acondicionados[j] != null) {
                                            if(acondicionados[j].getComponenteEncendidoState()) allButtons[i][j][0].setSelected(true);
                                            else allButtons[i][j][1].setSelected(true);
                                        }
                                    }
                                    break;
                                case 1:
                                    for(int j = 0 ; j < allButtons[i].length ; j++) {
                                        if(camaras[j] != null) {
                                            if(camaras[j].getComponenteEncendidoState()) allButtons[i][j][0].setSelected(true);
                                            else allButtons[i][j][1].setSelected(true);
                                        }
                                    }
                                    break;
                                case 2:
                                    for(int j = 0 ; j < allButtons[i].length ; j++) {
                                        if(sensores[j] != null) {
                                            if(sensores[j].getComponenteEncendidoState()) allButtons[i][j][0].setSelected(true);
                                            else allButtons[i][j][1].setSelected(true);
                                        }
                                    }
                                    break;
                                case 3:
                                    for(int j = 0 ; j < allButtons[i].length ; j++) {
                                        if(proyector != null) {
                                            if(proyector.getComponenteEncendidoState()) allButtons[i][j][0].setSelected(true);
                                            else allButtons[i][j][1].setSelected(true);
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                    catch(InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        });
        changeUI.start();
        changeUI2.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btndeviceac1 = new javax.swing.ButtonGroup();
        btndeviceac2 = new javax.swing.ButtonGroup();
        btndevicecamera1 = new javax.swing.ButtonGroup();
        btndevicecamera2 = new javax.swing.ButtonGroup();
        btndevicecamera3 = new javax.swing.ButtonGroup();
        btndevicecamera4 = new javax.swing.ButtonGroup();
        btnsensor1 = new javax.swing.ButtonGroup();
        btnsensor2 = new javax.swing.ButtonGroup();
        btnprojector = new javax.swing.ButtonGroup();
        btnlights = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        displaydeviceac1 = new javax.swing.JLabel();
        deviceprogressac1 = new javax.swing.JProgressBar();
        btndeleteac1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnrepairac1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        rdbtnonac1 = new javax.swing.JRadioButton();
        rdbtnoffac1 = new javax.swing.JRadioButton();
        displaydeviceac2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        deviceprogressac2 = new javax.swing.JProgressBar();
        rdbtnoffac2 = new javax.swing.JRadioButton();
        rdbtnonac2 = new javax.swing.JRadioButton();
        btndeleteac2 = new javax.swing.JButton();
        btnrepairac2 = new javax.swing.JButton();
        btntemperaturaac1 = new javax.swing.JButton();
        btntemperaturaac2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        displaydevicecam1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btndeletecam1 = new javax.swing.JButton();
        rdbtnoncam1 = new javax.swing.JRadioButton();
        rdbtnoffcam1 = new javax.swing.JRadioButton();
        deviceprogresscam1 = new javax.swing.JProgressBar();
        btnrepaircam1 = new javax.swing.JButton();
        btnvideofeedcam1 = new javax.swing.JButton();
        btnvideofeedcam2 = new javax.swing.JButton();
        rdbtnoncam2 = new javax.swing.JRadioButton();
        rdbtnoffcam2 = new javax.swing.JRadioButton();
        deviceprogresscam2 = new javax.swing.JProgressBar();
        btnrepaircam2 = new javax.swing.JButton();
        btndeletecam2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        displaydevicecam2 = new javax.swing.JLabel();
        displaydevicecam3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btndeletecam3 = new javax.swing.JButton();
        rdbtnoncam3 = new javax.swing.JRadioButton();
        rdbtnoffcam3 = new javax.swing.JRadioButton();
        deviceprogresscam3 = new javax.swing.JProgressBar();
        btnrepaircam3 = new javax.swing.JButton();
        btnvideofeedcam3 = new javax.swing.JButton();
        btndeletecam4 = new javax.swing.JButton();
        displaydevicecam4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnrepaircam4 = new javax.swing.JButton();
        rdbtnoncam4 = new javax.swing.JRadioButton();
        deviceprogresscam4 = new javax.swing.JProgressBar();
        btnvideofeedcam4 = new javax.swing.JButton();
        rdbtnoffcam4 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        displaysensor1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btndeletesensor1 = new javax.swing.JButton();
        rdbtnonsensor1 = new javax.swing.JRadioButton();
        rdbtnoffsensor1 = new javax.swing.JRadioButton();
        deviceprogresssensor1 = new javax.swing.JProgressBar();
        btnrepairsensor1 = new javax.swing.JButton();
        sensorstats1 = new javax.swing.JButton();
        displaysensor2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btndeletesensor2 = new javax.swing.JButton();
        rdbtnonsensor2 = new javax.swing.JRadioButton();
        rdbtnoffsensor2 = new javax.swing.JRadioButton();
        deviceprogresssensor2 = new javax.swing.JProgressBar();
        btnrepairsensor2 = new javax.swing.JButton();
        sensorstats2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        displayprojector = new javax.swing.JLabel();
        displayprojectoricon = new javax.swing.JLabel();
        rdbtnprojectoron = new javax.swing.JRadioButton();
        rdbtnprojectoroff = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        deviceprogressprojector = new javax.swing.JProgressBar();
        btnrepairprojector = new javax.swing.JButton();
        btndeleteprojector = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnlightson = new javax.swing.JRadioButton();
        btnlightsoff = new javax.swing.JRadioButton();
        jSlider1 = new javax.swing.JSlider();
        labelLucesImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de dispositivos - Tech Domótica");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gestión de dispositivos");

        displaydeviceac1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        displaydeviceac1.setText("DISPLAY_DEVICE");

        deviceprogressac1.setForeground(new java.awt.Color(0, 153, 0));
        deviceprogressac1.setToolTipText("");
        deviceprogressac1.setValue(100);
        deviceprogressac1.setStringPainted(true);

        btndeleteac1.setText("Eliminar");
        btndeleteac1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteac1ActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tiempo de vida");

        btnrepairac1.setText("Reparar");
        btnrepairac1.setEnabled(false);
        btnrepairac1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepairac1ActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Dispositivos que requieran mantenimiento aparecerán señalados en rojo.");

        btndeviceac1.add(rdbtnonac1);
        rdbtnonac1.setText("Encendido");
        rdbtnonac1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnonac1ActionPerformed(evt);
            }
        });

        btndeviceac1.add(rdbtnoffac1);
        rdbtnoffac1.setText("Apagado");
        rdbtnoffac1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnoffac1ActionPerformed(evt);
            }
        });

        displaydeviceac2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        displaydeviceac2.setText("DISPLAY_DEVICE");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Tiempo de vida");

        deviceprogressac2.setForeground(new java.awt.Color(0, 153, 0));
        deviceprogressac2.setToolTipText("");
        deviceprogressac2.setValue(100);
        deviceprogressac2.setStringPainted(true);

        btndeviceac2.add(rdbtnoffac2);
        rdbtnoffac2.setText("Apagado");
        rdbtnoffac2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnoffac2ActionPerformed(evt);
            }
        });

        btndeviceac2.add(rdbtnonac2);
        rdbtnonac2.setText("Encendido");
        rdbtnonac2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnonac2ActionPerformed(evt);
            }
        });

        btndeleteac2.setText("Eliminar");
        btndeleteac2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteac2ActionPerformed(evt);
            }
        });

        btnrepairac2.setText("Reparar");
        btnrepairac2.setEnabled(false);
        btnrepairac2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepairac2ActionPerformed(evt);
            }
        });

        btntemperaturaac1.setText("Establecer temperatura");
        btntemperaturaac1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntemperaturaac1ActionPerformed(evt);
            }
        });

        btntemperaturaac2.setText("Establecer temperatura");
        btntemperaturaac2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntemperaturaac2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdbtnonac1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdbtnoffac1))
                            .addComponent(displaydeviceac1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deviceprogressac1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btndeleteac1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnrepairac1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdbtnonac2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdbtnoffac2)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(displaydeviceac2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(5, 5, 5)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(deviceprogressac2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btndeleteac2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnrepairac2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btntemperaturaac1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btntemperaturaac2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(btndeleteac1))
                    .addComponent(displaydeviceac1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deviceprogressac1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnrepairac1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdbtnonac1)
                        .addComponent(rdbtnoffac1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btntemperaturaac1)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(btndeleteac2))
                    .addComponent(displaydeviceac2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deviceprogressac2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnrepairac2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdbtnonac2)
                        .addComponent(rdbtnoffac2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btntemperaturaac2)
                .addGap(8, 8, 8)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Aires acondicionados", jPanel1);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Dispositivos que requieran mantenimiento aparecerán señalados en rojo.");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        displaydevicecam1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        displaydevicecam1.setText("DISPLAY_DEVICE");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tiempo de vida");

        btndeletecam1.setText("Eliminar");
        btndeletecam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletecam1ActionPerformed(evt);
            }
        });

        btndevicecamera1.add(rdbtnoncam1);
        rdbtnoncam1.setText("Encendido");
        rdbtnoncam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnoncam1ActionPerformed(evt);
            }
        });

        btndevicecamera1.add(rdbtnoffcam1);
        rdbtnoffcam1.setText("Apagado");
        rdbtnoffcam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnoffcam1ActionPerformed(evt);
            }
        });

        deviceprogresscam1.setForeground(new java.awt.Color(0, 153, 0));
        deviceprogresscam1.setToolTipText("");
        deviceprogresscam1.setValue(100);
        deviceprogresscam1.setStringPainted(true);

        btnrepaircam1.setText("Reparar");
        btnrepaircam1.setEnabled(false);
        btnrepaircam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepaircam1ActionPerformed(evt);
            }
        });

        btnvideofeedcam1.setText("Ver señal de vídeo");
        btnvideofeedcam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvideofeedcam1ActionPerformed(evt);
            }
        });

        btnvideofeedcam2.setText("Ver señal de vídeo");
        btnvideofeedcam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvideofeedcam2ActionPerformed(evt);
            }
        });

        btndevicecamera2.add(rdbtnoncam2);
        rdbtnoncam2.setText("Encendido");
        rdbtnoncam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnoncam2ActionPerformed(evt);
            }
        });

        btndevicecamera2.add(rdbtnoffcam2);
        rdbtnoffcam2.setText("Apagado");
        rdbtnoffcam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnoffcam2ActionPerformed(evt);
            }
        });

        deviceprogresscam2.setForeground(new java.awt.Color(0, 153, 0));
        deviceprogresscam2.setToolTipText("");
        deviceprogresscam2.setValue(100);
        deviceprogresscam2.setStringPainted(true);

        btnrepaircam2.setText("Reparar");
        btnrepaircam2.setEnabled(false);
        btnrepaircam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepaircam2ActionPerformed(evt);
            }
        });

        btndeletecam2.setText("Eliminar");
        btndeletecam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletecam2ActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Tiempo de vida");

        displaydevicecam2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        displaydevicecam2.setText("DISPLAY_DEVICE");

        displaydevicecam3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        displaydevicecam3.setText("DISPLAY_DEVICE");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Tiempo de vida");

        btndeletecam3.setText("Eliminar");
        btndeletecam3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletecam3ActionPerformed(evt);
            }
        });

        btndevicecamera3.add(rdbtnoncam3);
        rdbtnoncam3.setText("Encendido");
        rdbtnoncam3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnoncam3ActionPerformed(evt);
            }
        });

        btndevicecamera3.add(rdbtnoffcam3);
        rdbtnoffcam3.setText("Apagado");
        rdbtnoffcam3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnoffcam3ActionPerformed(evt);
            }
        });

        deviceprogresscam3.setForeground(new java.awt.Color(0, 153, 0));
        deviceprogresscam3.setToolTipText("");
        deviceprogresscam3.setValue(100);
        deviceprogresscam3.setStringPainted(true);

        btnrepaircam3.setText("Reparar");
        btnrepaircam3.setEnabled(false);
        btnrepaircam3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepaircam3ActionPerformed(evt);
            }
        });

        btnvideofeedcam3.setText("Ver señal de vídeo");
        btnvideofeedcam3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvideofeedcam3ActionPerformed(evt);
            }
        });

        btndeletecam4.setText("Eliminar");
        btndeletecam4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletecam4ActionPerformed(evt);
            }
        });

        displaydevicecam4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        displaydevicecam4.setText("DISPLAY_DEVICE");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Tiempo de vida");

        btnrepaircam4.setText("Reparar");
        btnrepaircam4.setEnabled(false);
        btnrepaircam4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepaircam4ActionPerformed(evt);
            }
        });

        btndevicecamera4.add(rdbtnoncam4);
        rdbtnoncam4.setText("Encendido");
        rdbtnoncam4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnoncam4ActionPerformed(evt);
            }
        });

        deviceprogresscam4.setForeground(new java.awt.Color(0, 153, 0));
        deviceprogresscam4.setToolTipText("");
        deviceprogresscam4.setValue(100);
        deviceprogresscam4.setStringPainted(true);

        btnvideofeedcam4.setText("Ver señal de vídeo");
        btnvideofeedcam4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvideofeedcam4ActionPerformed(evt);
            }
        });

        btndevicecamera4.add(rdbtnoffcam4);
        rdbtnoffcam4.setText("Apagado");
        rdbtnoffcam4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnoffcam4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(displaydevicecam1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnvideofeedcam1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(rdbtnoncam1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdbtnoffcam1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deviceprogresscam1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btndeletecam1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnrepaircam1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(displaydevicecam2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnvideofeedcam2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(rdbtnoncam2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rdbtnoffcam2)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(deviceprogresscam2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btndeletecam2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnrepaircam2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(displaydevicecam3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnvideofeedcam3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(rdbtnoncam3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rdbtnoffcam3)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(deviceprogresscam3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btndeletecam3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnrepaircam3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(displaydevicecam4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnvideofeedcam4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(rdbtnoncam4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rdbtnoffcam4)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(deviceprogresscam4, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btndeletecam4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnrepaircam4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displaydevicecam1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btndeletecam1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbtnoncam1)
                    .addComponent(rdbtnoffcam1)
                    .addComponent(deviceprogresscam1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnrepaircam1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnvideofeedcam1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displaydevicecam2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btndeletecam2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbtnoncam2)
                    .addComponent(rdbtnoffcam2)
                    .addComponent(deviceprogresscam2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnrepaircam2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnvideofeedcam2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displaydevicecam3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(btndeletecam3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbtnoncam3)
                    .addComponent(rdbtnoffcam3)
                    .addComponent(deviceprogresscam3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnrepaircam3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnvideofeedcam3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displaydevicecam4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(btndeletecam4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbtnoncam4)
                    .addComponent(rdbtnoffcam4)
                    .addComponent(deviceprogresscam4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnrepaircam4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnvideofeedcam4)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Cámaras", jPanel2);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Dispositivos que requieran mantenimiento aparecerán señalados en rojo.");

        displaysensor1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        displaysensor1.setText("DISPLAY_DEVICE");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Tiempo de vida");

        btndeletesensor1.setText("Eliminar");
        btndeletesensor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletesensor1ActionPerformed(evt);
            }
        });

        btnsensor1.add(rdbtnonsensor1);
        rdbtnonsensor1.setText("Encendido");
        rdbtnonsensor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnonsensor1ActionPerformed(evt);
            }
        });

        btnsensor1.add(rdbtnoffsensor1);
        rdbtnoffsensor1.setText("Apagado");
        rdbtnoffsensor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnoffsensor1ActionPerformed(evt);
            }
        });

        deviceprogresssensor1.setForeground(new java.awt.Color(0, 153, 0));
        deviceprogresssensor1.setToolTipText("");
        deviceprogresssensor1.setValue(100);
        deviceprogresssensor1.setStringPainted(true);

        btnrepairsensor1.setText("Reparar");
        btnrepairsensor1.setEnabled(false);
        btnrepairsensor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepairsensor1ActionPerformed(evt);
            }
        });

        sensorstats1.setText("Estadísticas del dispositivo");
        sensorstats1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sensorstats1ActionPerformed(evt);
            }
        });

        displaysensor2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        displaysensor2.setText("DISPLAY_DEVICE");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Tiempo de vida");

        btndeletesensor2.setText("Eliminar");
        btndeletesensor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletesensor2ActionPerformed(evt);
            }
        });

        btnsensor2.add(rdbtnonsensor2);
        rdbtnonsensor2.setText("Encendido");
        rdbtnonsensor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnonsensor2ActionPerformed(evt);
            }
        });

        btnsensor2.add(rdbtnoffsensor2);
        rdbtnoffsensor2.setText("Apagado");
        rdbtnoffsensor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnoffsensor2ActionPerformed(evt);
            }
        });

        deviceprogresssensor2.setForeground(new java.awt.Color(0, 153, 0));
        deviceprogresssensor2.setToolTipText("");
        deviceprogresssensor2.setValue(100);
        deviceprogresssensor2.setStringPainted(true);

        btnrepairsensor2.setText("Reparar");
        btnrepairsensor2.setEnabled(false);
        btnrepairsensor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepairsensor2ActionPerformed(evt);
            }
        });

        sensorstats2.setText("Estadísticas del dispositivo");
        sensorstats2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sensorstats2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(displaysensor1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sensorstats1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdbtnonsensor1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdbtnoffsensor1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deviceprogresssensor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btndeletesensor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnrepairsensor1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(displaysensor2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sensorstats2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdbtnonsensor2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdbtnoffsensor2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deviceprogresssensor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btndeletesensor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnrepairsensor2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displaysensor1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(btndeletesensor1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbtnonsensor1)
                    .addComponent(rdbtnoffsensor1)
                    .addComponent(deviceprogresssensor1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnrepairsensor1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sensorstats1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displaysensor2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(btndeletesensor2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbtnonsensor2)
                    .addComponent(rdbtnoffsensor2)
                    .addComponent(deviceprogresssensor2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnrepairsensor2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sensorstats2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Sensores", jPanel3);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Dispositivos que requieran mantenimiento aparecerán señalados en rojo.");

        displayprojector.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        displayprojector.setText("DISPLAY_DEVICE");

        btnprojector.add(rdbtnprojectoron);
        rdbtnprojectoron.setText("Activado");
        rdbtnprojectoron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnprojectoronActionPerformed(evt);
            }
        });

        btnprojector.add(rdbtnprojectoroff);
        rdbtnprojectoroff.setText("Desactivado");
        rdbtnprojectoroff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnprojectoroffActionPerformed(evt);
            }
        });

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Tiempo de vida");

        deviceprogressprojector.setForeground(new java.awt.Color(0, 153, 0));
        deviceprogressprojector.setToolTipText("");
        deviceprogressprojector.setValue(100);
        deviceprogressprojector.setStringPainted(true);

        btnrepairprojector.setText("Reparar");
        btnrepairprojector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepairprojectorActionPerformed(evt);
            }
        });

        btndeleteprojector.setText("Eliminar");
        btndeleteprojector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteprojectorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(displayprojector, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(displayprojectoricon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(rdbtnprojectoron, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(rdbtnprojectoroff))
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deviceprogressprojector, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnrepairprojector, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btndeleteprojector, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbtnprojectoron)
                    .addComponent(rdbtnprojectoroff)
                    .addComponent(displayprojector, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deviceprogressprojector, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnrepairprojector)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndeleteprojector)
                        .addGap(58, 58, 58))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(displayprojectoricon, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Proyector", jPanel4);

        btnlights.add(btnlightson);
        btnlightson.setText("Encendidas");

        btnlights.add(btnlightsoff);
        btnlightsoff.setText("Apagadas");

        jSlider1.setMajorTickSpacing(1);
        jSlider1.setMaximum(5);
        jSlider1.setPaintLabels(true);
        jSlider1.setValue(3);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnlightson)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnlightsoff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(labelLucesImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(labelLucesImg, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnlightson)
                            .addComponent(btnlightsoff)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Luces", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkComponentUse() {
        checkACUse();
        checkCamUse();
        checkSensorUse();
        checkProjector();
    }
    
    private void checkACUse() {
        JButton btnRep[] = {btnrepairac1, btnrepairac2};
        JButton btnDel[] = {btndeleteac1, btndeleteac2};
        JButton btnAiresSet[] = {btntemperaturaac1, btntemperaturaac2};
        JLabel deviceLabel[] = {displaydeviceac1, displaydeviceac2};
        JProgressBar progressDevice[] = {deviceprogressac1, deviceprogressac2};
        JRadioButton radiosDevice[][] = {{rdbtnonac1, rdbtnoffac1}, {rdbtnonac2, rdbtnoffac2}};
        
        for(int i = 0 ; i < 2 ; i++) {
            btnRep[i].setToolTipText(null);
            radiosDevice[i][0].setToolTipText(null);
            radiosDevice[i][1].setToolTipText(null);
            btnAiresSet[i].setToolTipText(null);
            if(ambiente.getACondicionado(i) != null) {
                btnDel[i].setText("Eliminar");
                radiosDevice[i][0].setEnabled(true);
                radiosDevice[i][1].setEnabled(true);
                deviceLabel[i].setText(ambiente.getACondicionado(i).getComponenteFullName());
                progressDevice[i].setValue((int)ambiente.getACondicionado(i).getUsoComponente());
                if(ambiente.getACondicionado(i).getComponenteEncendidoState()) radiosDevice[i][0].setSelected(true);
                else radiosDevice[i][1].setSelected(true);
                
                if(ambiente.getACondicionado(i).getUsoComponente() >= 80 && ambiente.getACondicionado(i).getUsoComponente() <= 100) {
                    btnRep[i].setEnabled(false);
                    btnRep[i].setToolTipText("Este aire acondicionado está en condiciones óptimas de uso.");
                    deviceLabel[i].setForeground(Color.black);
                }
                if(ambiente.getACondicionado(i).getUsoComponente() <= 60) btnRep[i].setEnabled(true);
                if(ambiente.getACondicionado(i).getUsoComponente() <= 40) deviceLabel[i].setForeground(Color.red);
                if(ambiente.getACondicionado(i).getUsoComponente() == 0) {
                    btnRep[i].setEnabled(false);
                    btnRep[i].setToolTipText("Este aire acondicionado ya está demasiado dañado para ser reparado.\nEliminalo y reemplazalo por uno nuevo.");
                }
            }
            else {
                String no = "Agrega un dispositivo para interactuar con el.";
                btnDel[i].setText("Agregar");
                deviceLabel[i].setText("Dispositivo no agregado.");
                progressDevice[i].setValue(0);
                btnRep[i].setEnabled(false);
                radiosDevice[i][0].setEnabled(false);
                radiosDevice[i][1].setEnabled(false);
                btnAiresSet[i].setEnabled(false);
                
                btnRep[i].setToolTipText(no);
                radiosDevice[i][0].setToolTipText(no);
                radiosDevice[i][1].setToolTipText(no);
                btnAiresSet[i].setToolTipText(no);
            }
        }
    }
    
    private void checkCamUse() {
        JButton btnRep[] = {btnrepaircam1, btnrepaircam2, btnrepaircam3, btnrepaircam4};
        JButton btnDel[] = {btndeletecam1, btndeletecam2, btndeletecam3, btndeletecam4};
        JButton btnCamCheck[] = {btnvideofeedcam1, btnvideofeedcam2, btnvideofeedcam3, btnvideofeedcam4};
        JLabel deviceLabel[] = {displaydevicecam1, displaydevicecam2, displaydevicecam3, displaydevicecam4};
        JProgressBar progressDevice[] = {deviceprogresscam1, deviceprogresscam2, deviceprogresscam3, deviceprogresscam4};
        JRadioButton radiosDevice[][] = {{rdbtnoncam1, rdbtnoffcam1}, {rdbtnoncam2, rdbtnoffcam2}, {rdbtnoncam3, rdbtnoffcam3}, {rdbtnoncam4, rdbtnoffcam4}};//, {rdbtnonac2, rdbtnoffac2}};
        
        for(int i = 0 ; i < 4 ; i++) {
            btnRep[i].setToolTipText(null);
            radiosDevice[i][0].setToolTipText(null);
            radiosDevice[i][1].setToolTipText(null);
            btnCamCheck[i].setToolTipText(null);
            if(ambiente.getCamara(i) != null) {
                radiosDevice[i][0].setEnabled(true);
                radiosDevice[i][1].setEnabled(true);
                deviceLabel[i].setText(ambiente.getCamara(i).getComponenteFullName());
                progressDevice[i].setValue((int)ambiente.getCamara(i).getUsoComponente());
                if(ambiente.getCamara(i).getComponenteEncendidoState()) radiosDevice[i][0].setSelected(true);
                else radiosDevice[i][1].setSelected(true);
                
                if(ambiente.getCamara(i).getUsoComponente() >= 80 && ambiente.getCamara(i).getUsoComponente() <= 100) {
                    btnRep[i].setEnabled(false);
                    btnRep[i].setToolTipText("Esta cámara está en condiciones óptimas de uso.");
                    deviceLabel[i].setForeground(Color.black);
                }
                if(ambiente.getCamara(i).getUsoComponente() <= 60) btnRep[i].setEnabled(true);
                if(ambiente.getCamara(i).getUsoComponente() <= 40) deviceLabel[i].setForeground(Color.red);
                if(ambiente.getCamara(i).getUsoComponente() == 0) {
                    btnRep[i].setEnabled(false);
                    btnRep[i].setToolTipText("Esta cámara ya está demasiado dañada para ser reparada.\nEliminala y reemplazala por una nueva.");
                }
            }
            else {
                String no = "Agrega un dispositivo para interactuar con el.";
                btnDel[i].setText("Agregar");
                deviceLabel[i].setText("Dispositivo no agregado.");
                progressDevice[i].setValue(0);
                btnRep[i].setEnabled(false);
                radiosDevice[i][0].setEnabled(false);
                radiosDevice[i][1].setEnabled(false);
                btnCamCheck[i].setEnabled(false);
                
                btnRep[i].setToolTipText(no);
                radiosDevice[i][0].setToolTipText(no);
                radiosDevice[i][1].setToolTipText(no);
                btnCamCheck[i].setToolTipText(no);
            }
        }
    }
    
    private void checkSensorUse() {
        JButton btnRep[] = {btnrepairsensor1, btnrepairsensor2};
        JButton btnDel[] = {btndeletesensor1, btndeletesensor2};
        JButton btnSensorCheck[] = {sensorstats1, sensorstats2};
        JLabel deviceLabel[] = {displaysensor1, displaysensor2};
        JProgressBar progressDevice[] = {deviceprogresssensor1, deviceprogresssensor2};
        JRadioButton radiosDevice[][] = {{rdbtnonsensor1, rdbtnoffsensor1}, {rdbtnonsensor2, rdbtnoffsensor2}};
        
        for(int i = 0 ; i < 2 ; i++) {
            btnRep[i].setToolTipText(null);
            radiosDevice[i][0].setToolTipText(null);
            radiosDevice[i][1].setToolTipText(null);
            btnSensorCheck[i].setToolTipText(null);
            if(ambiente.getSensor(i) != null) {
                radiosDevice[i][0].setEnabled(true);
                radiosDevice[i][1].setEnabled(true);
                deviceLabel[i].setText(ambiente.getSensor(i).getComponenteFullName());
                progressDevice[i].setValue((int)ambiente.getSensor(i).getUsoComponente());
                if(ambiente.getSensor(i).getComponenteEncendidoState()) radiosDevice[i][0].setSelected(true);
                else radiosDevice[i][1].setSelected(true);
                
                if(ambiente.getSensor(i).getUsoComponente() >= 80 && ambiente.getSensor(i).getUsoComponente() <= 100) {
                    btnRep[i].setEnabled(false);
                    btnRep[i].setToolTipText("Este sensor está en condiciones óptimas de uso.");
                    deviceLabel[i].setForeground(Color.black);
                }
                if(ambiente.getSensor(i).getUsoComponente() <= 60) btnRep[i].setEnabled(true);
                if(ambiente.getSensor(i).getUsoComponente() <= 40) deviceLabel[i].setForeground(Color.red);
                if(ambiente.getSensor(i).getUsoComponente() == 0) {
                    btnRep[i].setEnabled(false);
                    btnRep[i].setToolTipText("Este sensor ya está demasiado dañado para ser reparado.\nEliminalo y reemplazalo por una nuevo.");
                }
            }
            else {
                String no = "Agrega un dispositivo para interactuar con el.";
                btnDel[i].setText("Agregar");
                deviceLabel[i].setText("Dispositivo no agregado.");
                progressDevice[i].setValue(0);
                btnRep[i].setEnabled(false);
                radiosDevice[i][0].setEnabled(false);
                radiosDevice[i][1].setEnabled(false);
                btnSensorCheck[i].setEnabled(false);
                
                btnRep[i].setToolTipText(no);
                radiosDevice[i][0].setToolTipText(no);
                radiosDevice[i][1].setToolTipText(no);
                btnSensorCheck[i].setToolTipText(no);
            }
        }
    }
    
    public void checkProjector() {
        btnrepairprojector.setToolTipText(null);
        rdbtnprojectoron.setToolTipText(null);
        rdbtnprojectoroff.setToolTipText(null);
        if(ambiente.getTelevisor() != null) {
            rdbtnprojectoron.setEnabled(true);
            rdbtnprojectoroff.setEnabled(true);
            if(ambiente.getTelevisor().getComponenteEncendidoState()) displayprojectoricon.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/media/simulator/projectoron.png")).getImage().getScaledInstance(206, 166, Image.SCALE_SMOOTH)));
            else displayprojectoricon.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/media/simulator/projectoroff.png")).getImage().getScaledInstance(206, 166, Image.SCALE_SMOOTH)));
            
            
            displayprojector.setText(ambiente.getTelevisor().getComponenteFullName());
            deviceprogressprojector.setValue((int)ambiente.getTelevisor().getUsoComponente());
            if(ambiente.getTelevisor().getComponenteEncendidoState()) rdbtnprojectoron.setSelected(true);
            else rdbtnprojectoroff.setSelected(true);
            
            if(ambiente.getTelevisor().getUsoComponente() >= 80 && ambiente.getTelevisor().getUsoComponente() <= 100) {
                btnrepairprojector.setEnabled(false);
                btnrepairprojector.setToolTipText("El proyector está en condiciones óptimas de uso.");
                displayprojector.setForeground(Color.black);
            }
            if(ambiente.getTelevisor().getUsoComponente() <= 60) btnrepairprojector.setEnabled(true);
            if(ambiente.getTelevisor().getUsoComponente() <= 40) displayprojector.setForeground(Color.red);
            if(ambiente.getTelevisor().getUsoComponente() == 0) {
                btnrepairprojector.setEnabled(false);
                btnrepairprojector.setToolTipText("Este proyector ya está demasiado dañado para ser reparado.\nEliminalo y reemplazalo por uno nuevo.");
            }
        }
        else {
            displayprojectoricon.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/media/simulator/nosignal2.png")).getImage().getScaledInstance(206, 166, Image.SCALE_SMOOTH)));
            
            btndeleteprojector.setText("Agregar");
            displayprojector.setText("Dispositivo no agregado");
            deviceprogressprojector.setValue(0);
            btnrepairprojector.setEnabled(false);
            rdbtnprojectoron.setEnabled(false);
            rdbtnprojectoroff.setEnabled(false);
            
            String no = "Agrega un dispositivo para interactuar con el.";
            btnrepairprojector.setToolTipText(no);
            rdbtnprojectoron.setToolTipText(no);
            rdbtnprojectoroff.setToolTipText(no);
        }
    }
    
    private void btnrepairac1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepairac1ActionPerformed
        // TODO add your handling code here:
        makeMaintance(ambiente.getACondicionado(0), new progressDialog(this, true) {
            @Override
            public void progressBarFilled() {
                super.progressBarFilled();
                if(ambiente.getConnection().execute("UPDATE componente SET uso = 100 WHERE id_componente = " + ambiente.getACondicionado(0).getDeviceID() + ";") == 1) {
                    ambiente.getACondicionado(0).setUsoComponente(100.0);
                    deviceprogressac1.setValue(100);
                    btnrepairac1.setEnabled(false);
                    checkComponentUse();
                }
            }
        });
        
    }//GEN-LAST:event_btnrepairac1ActionPerformed

    private void btntemperaturaac1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntemperaturaac1ActionPerformed
        changeTemp(0);
    }//GEN-LAST:event_btntemperaturaac1ActionPerformed

    private void btntemperaturaac2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntemperaturaac2ActionPerformed
        changeTemp(1);
    }//GEN-LAST:event_btntemperaturaac2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        continueOnThread = false;
        changeUI.interrupt();
        //saveChangesToMain();
    }//GEN-LAST:event_formWindowClosing

    private void btnrepairac2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepairac2ActionPerformed
        makeMaintance(ambiente.getACondicionado(1), new progressDialog(this, true) {
            @Override
            public void progressBarFilled() {
                super.progressBarFilled();
                if(ambiente.getConnection().execute("UPDATE componente SET uso = 100 WHERE id_componente = " + ambiente.getACondicionado(1).getDeviceID() + ";") == 1) {
                    ambiente.getACondicionado(1).setUsoComponente(100.0);
                    deviceprogressac2.setValue(100);
                    btnrepairac2.setEnabled(false);
                    checkComponentUse();
                }
            }
                
        });
    }//GEN-LAST:event_btnrepairac2ActionPerformed

    private void btndeleteac1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteac1ActionPerformed
        if(ambiente.getACondicionado(0) != null) {
            int conf = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar el dispositivo " + ambiente.getACondicionado(0).getComponenteFullName() + "?\nEsta acción no podrá deshacerse.");
            if(conf == JOptionPane.YES_OPTION) ambiente.destroyACondicionado(0);
            checkComponentUse();
        }
        else {
            registerNewAC();
        }
    }//GEN-LAST:event_btndeleteac1ActionPerformed

    private void registerNewAC() {
        String marca = JOptionPane.showInputDialog(null, "Inserta la marca del dispositivo.", "Agregando nuevo aire acondicionado", JOptionPane.INFORMATION_MESSAGE);
        if(!marca.isEmpty()) {
            String modelo = JOptionPane.showInputDialog(null, "Inserta el modelo del dispositivo.", "Agregando nuevo aire acondicionado", JOptionPane.INFORMATION_MESSAGE);
            if(!modelo.isEmpty()) {
                String[] disponibilidad = new String[2];
                if(ambiente.getACondicionado(0) == null) disponibilidad[0] = "1 - Disponible.";
                else disponibilidad[0] = "1 - No disponible.";
                if(ambiente.getACondicionado(1) == null) disponibilidad[1] = "2 - Disponible.";    
                else disponibilidad[1] = "2 - No disponible.";
                String posicion = JOptionPane.showInputDialog(null, "Escribe la ubicación en el dispositivo:\n\nPosiciones disponibles:\n\n" + disponibilidad[0] + "\n" + disponibilidad[1], "Agregando nuevo aire acondicionado", JOptionPane.INFORMATION_MESSAGE);
                if(!posicion.isEmpty()) {
                    if(Util.esNumerico(posicion)) {
                        switch(Integer.parseInt(posicion)) {
                            case 1: {
                                ambiente.insertACIntoDB(modelo, marca);
                                //ambiente.createACondicionado(0, modelo, marca);
                                JOptionPane.showMessageDialog(null, "Dispositivo añadido correctamente. Refrescando lista de componentes.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            case 2: {
                                ambiente.insertACIntoDB(modelo, marca);
                                //ambiente.createACondicionado(1, modelo, marca);
                                JOptionPane.showMessageDialog(null, "Dispositivo añadido correctamente. Refrescando lista de componentes.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            default: {
                                JOptionPane.showMessageDialog(null, "No se reconoce la posición especificada. Intentelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        ambiente.loadACondicionados();
                        checkComponentUse();
                    }
                    else JOptionPane.showMessageDialog(null, "La posición especificada contiene caracteres no numéricos. Intentelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else JOptionPane.showMessageDialog(null, "El modelo del producto no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(null, "No se reconoce la posición especificada. Intentelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void rdbtnonac1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnonac1ActionPerformed
        ACondicionado ac = ambiente.getACondicionado(0);
        if(ac.getComponenteEncendidoState() == false) {
            ambiente.getACondicionado(0).toggleComponenteEncendido(true);
            ambiente.getConnection().execute(String.format("UPDATE `componente` SET `uso`= %d, `componente_on`= %d WHERE id_componente = %d;", Math.round(ac.getUsoComponente()), (ac.getComponenteEncendidoState() ? 1 : 0), ac.getDeviceID()));
            ambiente.getConnection().execute(String.format("UPDATE `acondicionado` SET `temperatura`= %d WHERE id_componente = %d;", Math.round(ac.getTemperatura()), ac.getDeviceID()));
        }
    }//GEN-LAST:event_rdbtnonac1ActionPerformed

    private void rdbtnoffac1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnoffac1ActionPerformed
        ACondicionado ac = ambiente.getACondicionado(0);
        if(ambiente.getACondicionado(0).getComponenteEncendidoState() == true) {
            ambiente.getACondicionado(0).toggleComponenteEncendido(false);
            ambiente.getConnection().execute(String.format("UPDATE `componente` SET `uso`= %d, `componente_on`= %d WHERE id_componente = %d;", Math.round(ac.getUsoComponente()), (ac.getComponenteEncendidoState() ? 1 : 0), ac.getDeviceID()));
            ambiente.getConnection().execute(String.format("UPDATE `acondicionado` SET `temperatura`= %d WHERE id_componente = %d;", Math.round(ac.getTemperatura()), ac.getDeviceID()));
        }
    }//GEN-LAST:event_rdbtnoffac1ActionPerformed

    private void rdbtnonac2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnonac2ActionPerformed
        ACondicionado ac = ambiente.getACondicionado(1);
        if(ambiente.getACondicionado(1).getComponenteEncendidoState() == false) {
            ambiente.getACondicionado(1).toggleComponenteEncendido(true);
            ambiente.getConnection().execute(String.format("UPDATE `componente` SET `uso`= %d, `componente_on`= %d WHERE id_componente = %d;", Math.round(ac.getUsoComponente()), (ac.getComponenteEncendidoState() ? 1 : 0), ac.getDeviceID()));
            ambiente.getConnection().execute(String.format("UPDATE `acondicionado` SET `temperatura`= %d WHERE id_componente = %d;", Math.round(ac.getTemperatura()), ac.getDeviceID()));
        }
    }//GEN-LAST:event_rdbtnonac2ActionPerformed

    private void rdbtnoffac2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnoffac2ActionPerformed
        ACondicionado ac = ambiente.getACondicionado(1);
        if(ambiente.getACondicionado(1).getComponenteEncendidoState() == true) {
            ambiente.getACondicionado(1).toggleComponenteEncendido(false);
            ambiente.getConnection().execute(String.format("UPDATE `componente` SET `uso`= %d, `componente_on`= %d WHERE id_componente = %d;", Math.round(ac.getUsoComponente()), (ac.getComponenteEncendidoState() ? 1 : 0), ac.getDeviceID()));
            ambiente.getConnection().execute(String.format("UPDATE `acondicionado` SET `temperatura`= %d WHERE id_componente = %d;", Math.round(ac.getTemperatura()), ac.getDeviceID()));
        }
    }//GEN-LAST:event_rdbtnoffac2ActionPerformed

    private void btndeleteac2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteac2ActionPerformed
        if(ambiente.getACondicionado(1) != null) {
            int conf = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar el dispositivo " + ambiente.getACondicionado(1).getComponenteFullName() + "?\nEsta acción no podrá deshacerse.");
            if(conf == JOptionPane.YES_OPTION) ambiente.destroyACondicionado(1);
            checkComponentUse();
        }
        else {
            registerNewAC();
        }
    }//GEN-LAST:event_btndeleteac2ActionPerformed

    private void btndeletecam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletecam1ActionPerformed
        if(ambiente.getCamara(0) != null) {
            int conf = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar el dispositivo " + ambiente.getCamara(0).getComponenteFullName() + "?\nEsta acción no podrá deshacerse.");
            if(conf == JOptionPane.YES_OPTION) ambiente.destroyCamara(0);
            checkComponentUse();
        }
        else {
            registerNewCam();
        }
    }//GEN-LAST:event_btndeletecam1ActionPerformed

    private void registerNewCam() {
        String marca = JOptionPane.showInputDialog(null, "Inserta la marca del dispositivo.", "Agregando nueva cámara", JOptionPane.INFORMATION_MESSAGE);
        if(!marca.isEmpty()) {
            String modelo = JOptionPane.showInputDialog(null, "Inserta el modelo del dispositivo.", "Agregando nueva cámara", JOptionPane.INFORMATION_MESSAGE);
            if(!modelo.isEmpty()) {
                String[] disponibilidad = new String[4];
                if(ambiente.getCamara(0) == null) disponibilidad[0] = "1 - Disponible.";
                else disponibilidad[0] = "1 - No disponible.";
                if(ambiente.getCamara(1) == null) disponibilidad[1] = "2 - Disponible.";    
                else disponibilidad[1] = "2 - No disponible.";
                if(ambiente.getCamara(2) == null) disponibilidad[2] = "3 - Disponible.";
                else disponibilidad[2] = "3 - No disponible.";
                if(ambiente.getCamara(3) == null) disponibilidad[3] = "4 - Disponible.";    
                else disponibilidad[3] = "4 - No disponible.";
                String posicion = JOptionPane.showInputDialog(null, "Escribe la ubicación en el dispositivo:\n\nPosiciones disponibles:\n\n" + disponibilidad[0] + "\n" + disponibilidad[1] + "\n" + disponibilidad[2] + "\n" + disponibilidad[3], "Agregando nueva cámara", JOptionPane.INFORMATION_MESSAGE);
                if(!posicion.isEmpty()) {
                    if(Util.esNumerico(posicion)) {
                        switch(Integer.parseInt(posicion)) {
                            case 1: {
                                ambiente.insertCamaraIntoDB(0, modelo, marca);
                                //ambiente.createCamara(0, modelo, marca);
                                JOptionPane.showMessageDialog(null, "Dispositivo añadido correctamente. Refrescando lista de componentes.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            case 2: {
                                ambiente.insertCamaraIntoDB(1, modelo, marca);
                                //ambiente.createCamara(1, modelo, marca);
                                JOptionPane.showMessageDialog(null, "Dispositivo añadido correctamente. Refrescando lista de componentes.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            case 3: {
                                ambiente.insertCamaraIntoDB(2, modelo, marca);
                                //ambiente.createCamara(2, modelo, marca);
                                JOptionPane.showMessageDialog(null, "Dispositivo añadido correctamente. Refrescando lista de componentes.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            case 4: {
                                ambiente.insertCamaraIntoDB(3, modelo, marca);
                                //ambiente.createCamara(3, modelo, marca);
                                JOptionPane.showMessageDialog(null, "Dispositivo añadido correctamente. Refrescando lista de componentes.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            default: {
                                JOptionPane.showMessageDialog(null, "Posición definida erronea. Por favor, intentelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                        }
                        ambiente.loadCamaras();
                        checkComponentUse();
                    }
                    else JOptionPane.showMessageDialog(null, "La posición especificada contiene caracteres no numéricos. Intentelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else JOptionPane.showMessageDialog(null, "El modelo del producto no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(null, "No se reconoce la posición especificada. Intentelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
    private void rdbtnoncam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnoncam1ActionPerformed
        // TODO add your handling code here:
        toggleCamera(0, true);
    }//GEN-LAST:event_rdbtnoncam1ActionPerformed

    private void rdbtnoffcam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnoffcam1ActionPerformed
        // TODO add your handling code here:
        toggleCamera(0, false);
    }//GEN-LAST:event_rdbtnoffcam1ActionPerformed

    private void btnrepaircam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepaircam1ActionPerformed
        makeMaintance(ambiente.getCamara(0), new progressDialog(this, true) {
            @Override
            public void progressBarFilled() {
                super.progressBarFilled();
                if(ambiente.getConnection().execute("UPDATE componente SET uso = 100 WHERE id_componente = " + ambiente.getCamara(0).getDeviceID() + ";") == 1) {
                    ambiente.getCamara(0).setUsoComponente(100.0);
                    deviceprogresscam1.setValue(100);
                    btnrepaircam1.setEnabled(false);
                    checkComponentUse();
                }
            }
        });
    }//GEN-LAST:event_btnrepaircam1ActionPerformed

    private void btnvideofeedcam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvideofeedcam1ActionPerformed
        cameraView("camara1", "Vista de cámara 1", ambiente.getCamara(0).getComponenteEncendidoState());
    }//GEN-LAST:event_btnvideofeedcam1ActionPerformed

    private void btnvideofeedcam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvideofeedcam2ActionPerformed
        // TODO add your handling code here:
        cameraView("camara2", "Vista de cámara 2", ambiente.getCamara(1).getComponenteEncendidoState());
    }//GEN-LAST:event_btnvideofeedcam2ActionPerformed

    private void rdbtnoncam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnoncam2ActionPerformed
        // TODO add your handling code here:
        toggleCamera(1, true);
    }//GEN-LAST:event_rdbtnoncam2ActionPerformed

    private void rdbtnoffcam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnoffcam2ActionPerformed
        // TODO add your handling code here:
        toggleCamera(1, false);
    }//GEN-LAST:event_rdbtnoffcam2ActionPerformed

    private void btnrepaircam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepaircam2ActionPerformed
        makeMaintance(ambiente.getCamara(1), new progressDialog(this, true) {
            @Override
            public void progressBarFilled() {
                super.progressBarFilled();
                if(ambiente.getConnection().execute("UPDATE componente SET uso = 100 WHERE id_componente = " + ambiente.getCamara(1).getDeviceID() + ";") == 1) {
                    ambiente.getCamara(1).setUsoComponente(100.0);
                    deviceprogresscam2.setValue(100);
                    btnrepaircam2.setEnabled(false);
                    checkComponentUse();
                }
            }
        });
    }//GEN-LAST:event_btnrepaircam2ActionPerformed

    private void btndeletecam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletecam2ActionPerformed
        if(ambiente.getCamara(1) != null) {
            int conf = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar el dispositivo " + ambiente.getCamara(1).getComponenteFullName() + "?\nEsta acción no podrá deshacerse.");
            if(conf == JOptionPane.YES_OPTION) ambiente.destroyCamara(1);
            checkComponentUse();
        }
        else {
            registerNewCam();
        }
    }//GEN-LAST:event_btndeletecam2ActionPerformed

    private void btndeletecam3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletecam3ActionPerformed
        if(ambiente.getCamara(2) != null) {
            int conf = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar el dispositivo " + ambiente.getCamara(2).getComponenteFullName() + "?\nEsta acción no podrá deshacerse.");
            if(conf == JOptionPane.YES_OPTION) ambiente.destroyCamara(2);
            checkComponentUse();
        }
        else {
            registerNewCam();
        }
    }//GEN-LAST:event_btndeletecam3ActionPerformed

    private void rdbtnoncam3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnoncam3ActionPerformed
        // TODO add your handling code here:
        toggleCamera(2, true);
    }//GEN-LAST:event_rdbtnoncam3ActionPerformed

    private void rdbtnoffcam3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnoffcam3ActionPerformed
        // TODO add your handling code here:
        toggleCamera(2, false);
    }//GEN-LAST:event_rdbtnoffcam3ActionPerformed

    private void btnrepaircam3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepaircam3ActionPerformed
        makeMaintance(ambiente.getCamara(2), new progressDialog(this, true) {
            @Override
            public void progressBarFilled() {
                super.progressBarFilled();
                if(ambiente.getConnection().execute("UPDATE componente SET uso = 100 WHERE id_componente = " + ambiente.getCamara(2).getDeviceID() + ";") == 1) {
                    ambiente.getCamara(2).setUsoComponente(100.0);
                    deviceprogresscam3.setValue(100);
                    btnrepaircam3.setEnabled(false);
                    checkComponentUse();
                }
            }
        });
    }//GEN-LAST:event_btnrepaircam3ActionPerformed

    private void btnvideofeedcam3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvideofeedcam3ActionPerformed
        cameraView("camara3", "Vista de cámara 3", ambiente.getCamara(2).getComponenteEncendidoState());
    }//GEN-LAST:event_btnvideofeedcam3ActionPerformed

    private void btndeletecam4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletecam4ActionPerformed
        if(ambiente.getCamara(3) != null) {
            int conf = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar el dispositivo " + ambiente.getCamara(3).getComponenteFullName() + "?\nEsta acción no podrá deshacerse.");
            if(conf == JOptionPane.YES_OPTION) ambiente.destroyCamara(3);
        }
        else {
            registerNewCam();
        }
        checkComponentUse();
    }//GEN-LAST:event_btndeletecam4ActionPerformed

    private void btnrepaircam4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepaircam4ActionPerformed
        makeMaintance(ambiente.getCamara(3), new progressDialog(this, true) {
            @Override
            public void progressBarFilled() {
                super.progressBarFilled();
                if(ambiente.getConnection().execute("UPDATE componente SET uso = 100 WHERE id_componente = " + ambiente.getCamara(3).getDeviceID() + ";") == 1) {
                    ambiente.getCamara(3).setUsoComponente(100.0);
                    deviceprogresscam4.setValue(100);
                    btnrepaircam4.setEnabled(false);
                    checkComponentUse();
                }
            }
        });
    }//GEN-LAST:event_btnrepaircam4ActionPerformed

    private void rdbtnoncam4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnoncam4ActionPerformed
        // TODO add your handling code here:
        toggleCamera(3, true);
    }//GEN-LAST:event_rdbtnoncam4ActionPerformed

    private void btnvideofeedcam4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvideofeedcam4ActionPerformed
        cameraView("camara4", "Vista de cámara 4", ambiente.getCamara(3).getComponenteEncendidoState());
    }//GEN-LAST:event_btnvideofeedcam4ActionPerformed

    private void rdbtnoffcam4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnoffcam4ActionPerformed
        // TODO add your handling code here:
        toggleCamera(3, false);
    }//GEN-LAST:event_rdbtnoffcam4ActionPerformed

    private void btndeletesensor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletesensor1ActionPerformed
        if(ambiente.getSensor(0) != null) {
            int conf = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar el dispositivo " + ambiente.getSensor(0).getComponenteFullName() + "?\nEsta acción no podrá deshacerse.");
            if(conf == JOptionPane.YES_OPTION) ambiente.destroySensor(0);
            checkComponentUse();
        }
        else {
            registerNewSensor();
        }
    }//GEN-LAST:event_btndeletesensor1ActionPerformed

    private void registerNewSensor() {
        String marca = JOptionPane.showInputDialog(null, "Inserta la marca del dispositivo.", "Agregando nueva cámara", JOptionPane.INFORMATION_MESSAGE);
        if(!marca.isEmpty()) {
            String modelo = JOptionPane.showInputDialog(null, "Inserta el modelo del dispositivo.", "Agregando nueva cámara", JOptionPane.INFORMATION_MESSAGE);
            if(!modelo.isEmpty()) {
                String[] disponibilidad = new String[2];
                if(ambiente.getSensor(0) == null) disponibilidad[0] = "1 - Disponible. (sensor de puerta)";
                else disponibilidad[0] = "1 - No disponible. (sensor de puerta)";
                if(ambiente.getSensor(1) == null) disponibilidad[1] = "2 - Disponible. (sensor de movimiento)";    
                else disponibilidad[1] = "2 - No disponible. (sensor de movimiento)";
                String posicion = JOptionPane.showInputDialog(null, "Escribe la ubicación en el dispositivo:\n\nPosiciones disponibles:\n\n" + disponibilidad[0] + "\n" + disponibilidad[1], "Agregando nuevo sensor", JOptionPane.INFORMATION_MESSAGE);
                if(!posicion.isEmpty()) {
                    if(Util.esNumerico(posicion)) {
                        switch(Integer.parseInt(posicion)) {
                            case 1: {
                                ambiente.insertSensorIntoDB("puerta", modelo, marca);
                                //ambiente.createSensor(0, modelo, marca);
                                JOptionPane.showMessageDialog(null, "Dispositivo añadido correctamente. Refrescando lista de componentes.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            case 2: {
                                ambiente.insertSensorIntoDB("movimiento", modelo, marca);
                                //ambiente.createSensor(1, modelo, marca);
                                JOptionPane.showMessageDialog(null, "Dispositivo añadido correctamente. Refrescando lista de componentes.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                        }
                        ambiente.loadSensores();
                        checkComponentUse();
                    }
                    else JOptionPane.showMessageDialog(null, "La posición especificada contiene caracteres no numéricos. Intentelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else JOptionPane.showMessageDialog(null, "El modelo del producto no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(null, "No se reconoce la posición especificada. Intentelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void rdbtnonsensor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnonsensor1ActionPerformed
        toggleDevice(ambiente.getSensor(0), 3, true);
        rdbtnonsensor1.setSelected(true);
    }//GEN-LAST:event_rdbtnonsensor1ActionPerformed

    private void rdbtnoffsensor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnoffsensor1ActionPerformed
        toggleDevice(ambiente.getSensor(0), 3, false);
        rdbtnoffsensor2.setSelected(true);
    }//GEN-LAST:event_rdbtnoffsensor1ActionPerformed

    private void btnrepairsensor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepairsensor1ActionPerformed
        makeMaintance(ambiente.getSensor(0), new progressDialog(this, true) {
            @Override
            public void progressBarFilled() {
                super.progressBarFilled();
                if(ambiente.getConnection().execute("UPDATE componente SET uso = 100 WHERE id_componente = " + ambiente.getSensor(0).getDeviceID() + ";") == 1) {
                    ambiente.getSensor(0).setUsoComponente(100.0);
                    deviceprogresssensor1.setValue(100);
                    btnrepairsensor1.setEnabled(false);
                    checkComponentUse();
                }
            }
        });
    }//GEN-LAST:event_btnrepairsensor1ActionPerformed

    private void sensorstats1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sensorstats1ActionPerformed
        SensorView sensor = new SensorView(ambiente);
        sensor.setVisible(true);
    }//GEN-LAST:event_sensorstats1ActionPerformed

    private void btndeletesensor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletesensor2ActionPerformed
        if(ambiente.getSensor(1) != null) {
            int conf = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar el dispositivo " + ambiente.getSensor(1).getComponenteFullName() + "?\nEsta acción no podrá deshacerse.");
            if(conf == JOptionPane.YES_OPTION) ambiente.destroySensor(1);
            checkComponentUse();
        }
        else {
            registerNewSensor();
        }
    }//GEN-LAST:event_btndeletesensor2ActionPerformed

    private void rdbtnonsensor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnonsensor2ActionPerformed
        toggleDevice(ambiente.getSensor(1), 3, true);
        rdbtnonsensor2.setSelected(true);
    }//GEN-LAST:event_rdbtnonsensor2ActionPerformed

    private void rdbtnoffsensor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnoffsensor2ActionPerformed
        // TODO add your handling code here:
        toggleDevice(ambiente.getSensor(1), 3, false);
        rdbtnoffsensor2.setSelected(true);
    }//GEN-LAST:event_rdbtnoffsensor2ActionPerformed

    private void btnrepairsensor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepairsensor2ActionPerformed
        // TODO add your handling code here:
        makeMaintance(ambiente.getSensor(1), new progressDialog(this, true) {
            @Override
            public void progressBarFilled() {
                super.progressBarFilled();
                if(ambiente.getConnection().execute("UPDATE componente SET uso = 100 WHERE id_componente = " + ambiente.getSensor(1).getDeviceID() + ";") == 1) {
                    ambiente.getSensor(1).setUsoComponente(100.0);
                    deviceprogresssensor2.setValue(100);
                    btnrepairsensor2.setEnabled(false);
                    checkComponentUse();
                }
            }
        });
    }//GEN-LAST:event_btnrepairsensor2ActionPerformed

    private void sensorstats2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sensorstats2ActionPerformed
        SensorView sensor = new SensorView(ambiente);
        sensor.setVisible(true);
    }//GEN-LAST:event_sensorstats2ActionPerformed

    private void btnrepairprojectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepairprojectorActionPerformed
        makeMaintance(ambiente.getTelevisor(), new progressDialog(this, true) {
            @Override
            public void progressBarFilled() {
                super.progressBarFilled();
                if(ambiente.getConnection().execute("UPDATE componente SET uso = 100 WHERE id_componente = " + ambiente.getTelevisor().getDeviceID() + ";") == 1) {
                    ambiente.getTelevisor().setUsoComponente(100.0);
                    deviceprogressprojector.setValue(100);
                    btnrepairprojector.setEnabled(false);
                    checkComponentUse();
                }
            }
        });
    }//GEN-LAST:event_btnrepairprojectorActionPerformed

    private void registerNewProjector() {
        String marca = JOptionPane.showInputDialog(null, "Inserta la marca del dispositivo.", "Agregando nueva cámara", JOptionPane.INFORMATION_MESSAGE);
        if(!marca.isEmpty()) {
            String modelo = JOptionPane.showInputDialog(null, "Inserta el modelo del dispositivo.", "Agregando nueva cámara", JOptionPane.INFORMATION_MESSAGE);
            if(!modelo.isEmpty()) {
                ambiente.insertTVIntoDB(modelo, marca);
                //ambiente.createTelevisor(modelo, marca);
                JOptionPane.showMessageDialog(null, "Dispositivo añadido correctamente. Refrescando lista de componentes.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                ambiente.loadProyector();
                checkComponentUse();
            }
            else JOptionPane.showMessageDialog(null, "El modelo del producto no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(null, "No se reconoce la posición especificada. Intentelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void btndeleteprojectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteprojectorActionPerformed
        if(ambiente.getTelevisor() != null) {
            int conf = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar el dispositivo " + ambiente.getTelevisor().getComponenteFullName() + "?\nEsta acción no podrá deshacerse.");
            if(conf == JOptionPane.YES_OPTION) ambiente.destroyTelevisor();
            checkComponentUse();
        }
        else registerNewProjector();
    }//GEN-LAST:event_btndeleteprojectorActionPerformed

    private void rdbtnprojectoronActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnprojectoronActionPerformed
        progressDialog dialogo = new progressDialog(this, true, 25) {
            @Override
            public void progressBarFilled() {
                ambiente.getTelevisor().toggleComponenteEncendido(true);
                rdbtnprojectoron.setSelected(true);
                checkComponentUse();
            }
        };
        dialogo.setTitle("Encendiendo dispositivo");
        dialogo.textVar.setText("<html>Modificando valores del dispositivo " + ambiente.getTelevisor().getComponenteFullName() + ", puede tardar unos segundos...</html>");
        dialogo.setVisible(true);
    }//GEN-LAST:event_rdbtnprojectoronActionPerformed

    private void rdbtnprojectoroffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnprojectoroffActionPerformed
        progressDialog dialogo = new progressDialog(this, true, 25) {
            @Override
            public void progressBarFilled() {
                ambiente.getTelevisor().toggleComponenteEncendido(false);
                rdbtnprojectoroff.setSelected(true);
                checkComponentUse();
            }
        };
        dialogo.setTitle("Apagando dispositivo");
        dialogo.textVar.setText("<html>Modificando valores del dispositivo " + ambiente.getTelevisor().getComponenteFullName() + ", puede tardar unos segundos...</html>");
        dialogo.setVisible(true);
    }//GEN-LAST:event_rdbtnprojectoroffActionPerformed

    public void saveChangesToMain() {}
    
    private void toggleCamera(int id, boolean onoff) {
        Camara cam = ambiente.getCamara(id);
        if(cam.getComponenteEncendidoState() == !onoff) {
            onoffCamera(cam, new progressDialog(this, true, 10) {
                @Override
                public void progressBarFilled() {
                    cam.toggleComponenteEncendido(onoff);
                    ambiente.getConnection().execute(String.format("UPDATE `componente` SET `uso`= %d, `componente_on`= %d WHERE id_componente = %d;", Math.round(cam.getUsoComponente()), (cam.getComponenteEncendidoState() ? 1 : 0), cam.getDeviceID()));
                }
            }, "Encendiendo componente", "Modificando valores de la cámara " + cam.getComponenteFullName() + ", puede tardar unos segundos.");
        }
    }
    
    private void toggleDevice(Componente comp, int timevalue, boolean onoff) {
        if(comp.getComponenteEncendidoState() == !onoff) {
            progressDialog dialogo = new progressDialog(this, true, timevalue) {
                @Override
                public void progressBarFilled() {
                    comp.toggleComponenteEncendido(onoff);
                    ambiente.getConnection().execute(String.format("UPDATE `componente` SET `uso`= %d, `componente_on`= %d WHERE id_componente = %d;", Math.round(comp.getUsoComponente()), (comp.getComponenteEncendidoState() ? 1 : 0), comp.getDeviceID()));
                }
            };
            if(onoff) dialogo.setTitle("Encendiendo dispositivo");
            else dialogo.setTitle("Apagando dispositivo");
            dialogo.textVar.setText("<html>Modificando valores del dispositivo " + comp.getComponenteFullName() + ", puede tardar unos segundos...</html>");
            dialogo.setVisible(true);
        }
    }
    
    private void cameraView(String campath, String title, boolean ison) {
        CameraView camView = new CameraView(ambiente, campath, ison) {
            @Override
            public void handleClose() {
                super.handleClose();
                checkComponentUse();
            }
        };
        int camaraid = Character.getNumericValue(campath.charAt(campath.length() - 1)) - 1;
        camView.cameraViewNum.setText(title);
        camView.setTitle(title + " - Tech Domótica");
        camView.setVisible(true);
    }
    
    public void changeTemp(int id) {
        String newtempStr = JOptionPane.showInputDialog(null, "Ingrese la nueva temperatura a insertar entre 17°C y 30°C.\nPuede ser un número entero o decimal.\n\nLa temperatura actual asignada a este aire acondicionado es de " + ambiente.getACondicionado(id).getTemperatura() + "°C.", "Ingreso de nueva temperatura", JOptionPane.QUESTION_MESSAGE);
        if(!newtempStr.isEmpty()) {
            if(Util.esNumerico(newtempStr)) {
                double temperatura = Double.parseDouble(newtempStr);
                if(temperatura >= 17.0 && temperatura <= 30.0) {
                    ambiente.getACondicionado(id).changeTemperatura(temperatura);
                    ambiente.getConnection().execute(String.format("UPDATE `acondicionado` SET `temperatura`= %d WHERE id_componente = %d;", Math.round(ambiente.getACondicionado(id).getTemperatura()), ambiente.getACondicionado(id).getDeviceID()));
                    JOptionPane.showMessageDialog(null, "Se ha cambiado la temperatura del aire acondicionado", "Cambio realizado", JOptionPane.INFORMATION_MESSAGE);
                }
                else JOptionPane.showMessageDialog(null, "La temperatura debe de estar entre los 17°C y 30°C.", "Temperatura inválida", JOptionPane.ERROR_MESSAGE);
            }
            else JOptionPane.showMessageDialog(null, "La temperatura específicada no es numérica. Intentelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void makeMaintance(Componente comp, progressDialog dialogo) {
        dialogo.setTitle("Reparando " + comp.getComponenteFullName());
        dialogo.textVar.setText("<html>Realizando una operación de mantenimiento al componente " + comp.getComponenteFullName() +", por favor espere...</html>");
        dialogo.setVisible(true);
    }
    
    public void onoffCamera(Componente comp, progressDialog dialogo, String title, String textvar) {
        dialogo.setTitle(title);
        dialogo.textVar.setText("<html>" + textvar + ". Por favor espere...</html>");
        dialogo.setVisible(true);
    }
    
    private void removeAdminStuff() {
        if(ambiente.getAdminEncargado() == null) {
            btndeleteac1.setVisible(false);
            btndeleteac2.setVisible(false);
            
            jTabbedPane1.remove(jPanel2);
            jTabbedPane1.remove(jPanel3);
            
            jPanel2.setVisible(false);
            jPanel3.setVisible(false);
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
            java.util.logging.Logger.getLogger(DeviceManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeviceManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeviceManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeviceManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeviceManager(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndeleteac1;
    private javax.swing.JButton btndeleteac2;
    private javax.swing.JButton btndeletecam1;
    private javax.swing.JButton btndeletecam2;
    private javax.swing.JButton btndeletecam3;
    private javax.swing.JButton btndeletecam4;
    private javax.swing.JButton btndeleteprojector;
    private javax.swing.JButton btndeletesensor1;
    private javax.swing.JButton btndeletesensor2;
    private javax.swing.ButtonGroup btndeviceac1;
    private javax.swing.ButtonGroup btndeviceac2;
    private javax.swing.ButtonGroup btndevicecamera1;
    private javax.swing.ButtonGroup btndevicecamera2;
    private javax.swing.ButtonGroup btndevicecamera3;
    private javax.swing.ButtonGroup btndevicecamera4;
    private javax.swing.ButtonGroup btnlights;
    private javax.swing.JRadioButton btnlightsoff;
    private javax.swing.JRadioButton btnlightson;
    private javax.swing.ButtonGroup btnprojector;
    private javax.swing.JButton btnrepairac1;
    private javax.swing.JButton btnrepairac2;
    private javax.swing.JButton btnrepaircam1;
    private javax.swing.JButton btnrepaircam2;
    private javax.swing.JButton btnrepaircam3;
    private javax.swing.JButton btnrepaircam4;
    private javax.swing.JButton btnrepairprojector;
    private javax.swing.JButton btnrepairsensor1;
    private javax.swing.JButton btnrepairsensor2;
    private javax.swing.ButtonGroup btnsensor1;
    private javax.swing.ButtonGroup btnsensor2;
    private javax.swing.JButton btntemperaturaac1;
    private javax.swing.JButton btntemperaturaac2;
    private javax.swing.JButton btnvideofeedcam1;
    private javax.swing.JButton btnvideofeedcam2;
    private javax.swing.JButton btnvideofeedcam3;
    private javax.swing.JButton btnvideofeedcam4;
    private javax.swing.JProgressBar deviceprogressac1;
    private javax.swing.JProgressBar deviceprogressac2;
    private javax.swing.JProgressBar deviceprogresscam1;
    private javax.swing.JProgressBar deviceprogresscam2;
    private javax.swing.JProgressBar deviceprogresscam3;
    private javax.swing.JProgressBar deviceprogresscam4;
    private javax.swing.JProgressBar deviceprogressprojector;
    private javax.swing.JProgressBar deviceprogresssensor1;
    private javax.swing.JProgressBar deviceprogresssensor2;
    private javax.swing.JLabel displaydeviceac1;
    private javax.swing.JLabel displaydeviceac2;
    private javax.swing.JLabel displaydevicecam1;
    private javax.swing.JLabel displaydevicecam2;
    private javax.swing.JLabel displaydevicecam3;
    private javax.swing.JLabel displaydevicecam4;
    private javax.swing.JLabel displayprojector;
    private javax.swing.JLabel displayprojectoricon;
    private javax.swing.JLabel displaysensor1;
    private javax.swing.JLabel displaysensor2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelLucesImg;
    private javax.swing.JRadioButton rdbtnoffac1;
    private javax.swing.JRadioButton rdbtnoffac2;
    private javax.swing.JRadioButton rdbtnoffcam1;
    private javax.swing.JRadioButton rdbtnoffcam2;
    private javax.swing.JRadioButton rdbtnoffcam3;
    private javax.swing.JRadioButton rdbtnoffcam4;
    private javax.swing.JRadioButton rdbtnoffsensor1;
    private javax.swing.JRadioButton rdbtnoffsensor2;
    private javax.swing.JRadioButton rdbtnonac1;
    private javax.swing.JRadioButton rdbtnonac2;
    private javax.swing.JRadioButton rdbtnoncam1;
    private javax.swing.JRadioButton rdbtnoncam2;
    private javax.swing.JRadioButton rdbtnoncam3;
    private javax.swing.JRadioButton rdbtnoncam4;
    private javax.swing.JRadioButton rdbtnonsensor1;
    private javax.swing.JRadioButton rdbtnonsensor2;
    private javax.swing.JRadioButton rdbtnprojectoroff;
    private javax.swing.JRadioButton rdbtnprojectoron;
    private javax.swing.JButton sensorstats1;
    private javax.swing.JButton sensorstats2;
    // End of variables declaration//GEN-END:variables
}
