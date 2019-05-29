/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techdomotica.java.forms.events;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import techdomotica.objs.Ambiente;
import techdomotica.objs.Conectar;
import techdomotica.objs.Event;
import techdomotica.objs.Perfil;
import techdomotica.objs.Util;

/**
 *
 * @author Andres
 */
public class EventScreen extends javax.swing.JFrame {

    /**
     * Creates new form EventScreen
     */
    private ArrayList<Event> eventosListT = new ArrayList();
    private ArrayList<Event> eventosList = new ArrayList();
    private ArrayList<Perfil> perfilList = new ArrayList();
    private Ambiente ambiente;
    private Conectar conx = null;
    
    private int selectedRowF = 0;
    private int selectedRowS = 0;
    
    public EventScreen(Ambiente amb) {
        initComponents();
        ambiente = amb;
        conx = amb.getConnection();
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/resources/media/L4.png")).getImage());
        getTodayEventList();
        loadTable();
        loadTodayTable();
        loadPerfiles(false);
    }
    
    private void getTodayEventList() {
        eventosListT = ambiente.getEventList();
    }
    
    private void loadTodayTable() {
        selectedRowS = 0;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("#");
        model.addColumn("Perfil para el evento");
        model.addColumn("Hora del evento");
        for(int i = 0 ; i < eventosListT.size() ; i++) {
            Object[] fila = {eventosListT.get(i).getEventID(), eventosListT.get(i).getPerfilEvento().getPerfilID(), eventosListT.get(i).getHora()};
            model.addRow(fila);
        }
        tableTodayEvents.getTableHeader().setReorderingAllowed(false);
        tableTodayEvents.setModel(model);
        tableEvents.setRowSelectionInterval(0, 0);
    }

    private void loadPerfiles(boolean showMineOnly) {
        String query = "";
        perfilList.clear();
        if(showMineOnly) query = "SELECT * FROM perfil WHERE id_usuario = " + ambiente.getAdminEncargado().getID() + " AND habilitado >= 1;";
        else query = "SELECT perfil.*, usuario.nom1, usuario.apellido1, usuario.dni FROM perfil INNER JOIN usuario ON perfil.id_usuario = usuario.id_usuario WHERE perfil.habilitado >= 1 AND usuario.habilitado = 1;";
        if(conx.executeRS(query)) {
            DefaultComboBoxModel modelo = new DefaultComboBoxModel();
            while(conx.nextRow()) {
                if(showMineOnly) modelo.addElement("ID " + techdomotica.objs.Util.parseInteger(conx.getResultSetRow("id_perfil")));
                else modelo.addElement("ID " + techdomotica.objs.Util.parseInteger(conx.getResultSetRow("id_perfil")) + " - de " + conx.getResultSetRow("nom1") + " " + conx.getResultSetRow("apellido1") + " (" + conx.getResultSetRow("dni") + ")");
                Perfil perfil = new Perfil(techdomotica.objs.Util.parseInteger(conx.getResultSetRow("id_perfil")), techdomotica.objs.Util.parseInteger(conx.getResultSetRow("temp1")), techdomotica.objs.Util.parseInteger(conx.getResultSetRow("temp2")), (techdomotica.objs.Util.parseInteger(conx.getResultSetRow("temp1_on")) == 1), (techdomotica.objs.Util.parseInteger(conx.getResultSetRow("temp2_on")) == 1), (techdomotica.objs.Util.parseInteger(conx.getResultSetRow("proyector_on")) == 1), (techdomotica.objs.Util.parseInteger(conx.getResultSetRow("sensor1_on")) == 1), (techdomotica.objs.Util.parseInteger(conx.getResultSetRow("sensor2_on")) == 1));
                perfilList.add(perfil);
            }
            comboPerfiles.setModel(modelo);
            comboPerfiles.setEnabled(true);
            comboPerfiles.setToolTipText(null);
            jButton3.setEnabled(true);
            jButton3.setToolTipText(null);
        }
        else {
            comboPerfiles.setEnabled(false);
            comboPerfiles.setToolTipText("No se encontraron perfiles disponibles.");
            jButton3.setEnabled(false);
            jButton3.setToolTipText("No puedes ver los valores de un perfil no seleccionado.");
        }
    }
    
    private void loadTable() {
        selectedRowF = 0;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("#");
        model.addColumn("Perfil para el evento");
        model.addColumn("Fecha del evento");
        model.addColumn("Hora del evento");
        String query = "SELECT * FROM evento INNER JOIN perfil ON perfil.id_perfil = evento.id_perfil WHERE fecha >= CURRENT_DATE AND perfil.habilitado >= 1 AND evento.habilitado = 1;";
        if(conx.executeRS(query)) {
            tableEvents.getTableHeader().setReorderingAllowed(false);
            conx.setBeforeFirst();
            eventosList.clear();
            while(conx.nextRow()) {
                Perfil perfil = new Perfil(techdomotica.objs.Util.parseInteger(conx.getResultSetRow("id_perfil")), techdomotica.objs.Util.parseInteger(conx.getResultSetRow("temp1")), techdomotica.objs.Util.parseInteger(conx.getResultSetRow("temp2")), (techdomotica.objs.Util.parseInteger(conx.getResultSetRow("temp1_on")) == 1), (techdomotica.objs.Util.parseInteger(conx.getResultSetRow("temp2_on")) == 1), (techdomotica.objs.Util.parseInteger(conx.getResultSetRow("proyector_on")) == 1), (techdomotica.objs.Util.parseInteger(conx.getResultSetRow("sensor1_on")) == 1), (techdomotica.objs.Util.parseInteger(conx.getResultSetRow("sensor2_on")) == 1));
                Event evento = new Event(Util.parseInteger(conx.getResultSetRow("id_evento")), LocalDate.parse(String.valueOf(conx.getResultSetRow("fecha"))), LocalTime.parse(String.valueOf(conx.getResultSetRow("hora"))), perfil);
                eventosList.add(evento);
                //perfilList.add(perfil);
                Object[] fila = {conx.getResultSetRow("id_evento"), conx.getResultSetRow("id_perfil"), conx.getResultSetRow("fecha"), conx.getResultSetRow("hora")};
                model.addRow(fila);
            }
        }
        else {
            Object[] lol = {"No hay datos"};
            model.addRow(lol);
        }
        if(conx.getResultSet() != null) conx.destroyResultSet();
        if(eventosList.isEmpty()) {
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
        }
        else {
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
        }
        tableEvents.setModel(model);
        tableEvents.setRowSelectionInterval(0, 0);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEvents = new javax.swing.JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jScrollPane2 = new javax.swing.JScrollPane();
        tableEvents1 = new javax.swing.JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        comboPerfiles = new javax.swing.JComboBox<>();
        dateText = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboHora = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        comboMin = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableTodayEvents = new javax.swing.JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eventos - Tech Domótica");
        setResizable(false);

        tableEvents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableEvents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEventsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableEvents);

        tableEvents1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableEvents1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEventsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableEvents1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Eventos del sistema");

        jButton1.setText("Eliminar evento");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Modificar evento");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("<html>Para ver los detalles del perfil seleccionado para el evento, has doble click en la fila escogida.</html>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(153, 153, 153)
                                .addComponent(jButton1)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Eventos del día");

        jLabel4.setText("Los eventos son perfiles que se activan durante ciertas horas.");

        jLabel5.setText("Perfil seleccionado:");

        jButton3.setText("Ver los valores del perfil seleccionado");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        dateText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jLabel6.setText("Fecha del evento (d/mm/yyyy):");

        jLabel7.setText("Hora:");

        comboHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jLabel8.setText(":");

        comboMin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jButton4.setText("Crear evento");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Solo mis perfiles.");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Añadir un evento");

        tableTodayEvents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tableTodayEvents);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboPerfiles, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCheckBox1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboPerfiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel7)
                    .addComponent(comboHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableEventsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEventsMouseClicked
        if(evt.getClickCount() == 2) {
            Perfil perfil = eventosList.get(selectedRowF).getPerfilEvento();
            String msg = String.format("Temperatura del aire 1: %d°C.\nTemperatura del aire 2: %d°C.\n¿Aire acondicionado 1 encendido?: %s.\n¿Aire acondicionado 2 encendido?: %s.\n¿Proyector encendido?: %s.\n¿Sensor de puerta encendido?: %s.\n¿Sensor de movimiento encendido?: %s.\n", perfil.getTempAire1(), perfil.getTempAire2(), (perfil.isAire1On() ? "Si" : "No"), (perfil.isAire2On() ? "Si" : "No"),  (perfil.isProyectorOn() ? "Si" : "No"), (perfil.isSensor1On() ? "Si" : "No"), (perfil.isSensor2On() ? "Si" : "No"));
            JOptionPane.showMessageDialog(null, msg, "Perfil del evento #" + eventosList.get(selectedRowF).getEventID(), JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            selectedRowF = tableEvents.getSelectedRow();
        }
    }//GEN-LAST:event_tableEventsMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int conf = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este evento? Esta acción no se puede revertir.", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(conf == JOptionPane.YES_OPTION) {
            if(conx.execute("UPDATE evento SET habilitado = 0 WHERE id_evento = " + eventosList.get(selectedRowF).getEventID() +";") == 1) {
                JOptionPane.showMessageDialog(null, "Se ha quitado el evento de la lista.", "Evento modificado", JOptionPane.INFORMATION_MESSAGE);
                loadTable();
                loadTodayTable();
            }
            else JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar modificar este evento. Intentalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Event evento = eventosList.get(selectedRowF);
        String hour = evento.getHora().format(DateTimeFormatter.ofPattern("HH"));
        String minute = evento.getHora().format(DateTimeFormatter.ofPattern("mm"));
        String date = evento.getFecha().format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
        
        modifyEvent eventomod = new modifyEvent(ambiente) {
            @Override
            public void onClose() {
                loadTable();
            }
        };
        eventomod.comboHora.setSelectedItem(hour);
        eventomod.comboMin.setSelectedItem(minute);
        eventomod.dateText.setText(date);
        eventomod.setEditingID(eventosList.get(selectedRowF).getEventID());
        
        eventomod.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Perfil perfil = perfilList.get(comboPerfiles.getSelectedIndex());
        String msg = String.format("Temperatura del aire 1: %d°C.\nTemperatura del aire 2: %d°C.\n¿Aire acondicionado 1 encendido?: %s.\n¿Aire acondicionado 2 encendido?: %s.\n¿Proyector encendido?: %s.\n¿Sensor de puerta encendido?: %s.\n¿Sensor de movimiento encendido?: %s.\n", perfil.getTempAire1(), perfil.getTempAire2(), (perfil.isAire1On() ? "Si" : "No"), (perfil.isAire2On() ? "Si" : "No"),  (perfil.isProyectorOn() ? "Si" : "No"), (perfil.isSensor1On() ? "Si" : "No"), (perfil.isSensor2On() ? "Si" : "No"));
        JOptionPane.showMessageDialog(null, msg, "Información del perfil seleccionado", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        loadPerfiles(jCheckBox1.isSelected());
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String msg = String.format("Perfil seleccionado: %s.\nFecha del evento: %s.\nHora del evento: %s:%s.\n\n¿Estás seguro de agregar este nuevo evento?", comboPerfiles.getSelectedItem(), dateText.getText(), comboHora.getSelectedItem(), comboMin.getSelectedItem());
        int conf = JOptionPane.showConfirmDialog(null, "Por favor, verifica los siguientes datos:\n" + msg, "Creación de nuevo evento", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(conf == JOptionPane.YES_OPTION) {
            if(dateText.getText().isEmpty()) JOptionPane.showMessageDialog(null, "El campo de la fecha está vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            else {
                try {
                    java.util.Date utilDate = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(dateText.getText());
                    java.sql.Date date = new java.sql.Date(utilDate.getTime());
                    String convertedTime = comboHora.getSelectedItem() + ":" + comboMin.getSelectedItem() + ":00";
                    if(conx.executeWithObjects("INSERT INTO evento(`id_perfil`, `habilitado`, `fecha`, `hora`) VALUES(?, 1, ?, ?);", perfilList.get(comboPerfiles.getSelectedIndex()).getPerfilID(), date, convertedTime) == 1) {
                    //if(conx.execute(String.format("INSERT INTO evento VALUES(null, %d, 1, '%s', '%s:%s:00');", perfilList.get(comboPerfiles.getSelectedIndex()).getPerfilID(), convertedDate, comboHora.getSelectedItem(), comboMin.getSelectedItem())) == 1) {
                        JOptionPane.showMessageDialog(null, "Se ha agregado un nuevo evento.", "¡Nuevo evento creado!", JOptionPane.INFORMATION_MESSAGE);
                        loadTable();
                        loadPerfiles(jCheckBox1.isSelected());
                        ambiente.loadTodayEvents();
                        ambiente.getTimeThread().getAmbienteEventos();
                        ambiente.getTimeThread().checkEvents();
                        loadTodayTable();
                    }
                    else JOptionPane.showMessageDialog(null, "No se ha podido agregar este evento. Por favor, intentelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch(ParseException e) {
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EventScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EventScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EventScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EventScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EventScreen(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboHora;
    private javax.swing.JComboBox<String> comboMin;
    private javax.swing.JComboBox<String> comboPerfiles;
    private javax.swing.JFormattedTextField dateText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableEvents;
    private javax.swing.JTable tableEvents1;
    private javax.swing.JTable tableTodayEvents;
    // End of variables declaration//GEN-END:variables
}
