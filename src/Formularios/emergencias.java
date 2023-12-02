
package Formularios;

import static Formularios.internos.ID_INTERNOS;
import clases.Conectar;
import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class emergencias extends javax.swing.JFrame {

       Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
    public emergencias() {
        initComponents();
        id_emergencias();
        cargarLogo();
        bloqueos_inicios(); 
             setExtendedState(JFrame.MAXIMIZED_BOTH);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            LocalDateTime fechaactual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            lblFechaActual.setText(fechaactual.format(formato));
        },0,1,TimeUnit.SECONDS);
        IDSALAS.setVisible(false);
        IDPERSONAS.setVisible(false);
        cargartabla.setVisible(false);
    }
    public void cargarLogo() {
    try {
        String query = "SELECT logo_empresa FROM empresa WHERE id_empresa = ?";
        try (PreparedStatement pst = cn.prepareStatement(query)) {
            pst.setInt(1, 1); 

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Blob blob = rs.getBlob("logo_empresa");

                    if (blob != null) {
                        byte[] bytes = blob.getBytes(1, (int) blob.length());
                        ImageIcon imagen = new ImageIcon(bytes);
                        Image image = imagen.getImage().getScaledInstance(125, 50, Image.SCALE_SMOOTH);
                        LOGO.setIcon(new ImageIcon(image));
                    } else {
                        LOGO.setIcon(null);
                        JOptionPane.showMessageDialog(null, "No hay imagen para cargar.");
                    }
                }
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al cargar el logo: " + ex.getMessage());
    }
}
    void bloqueos_inicios(){
           noalergico.setSelected(true);
           txtalergicos.setEnabled(false); 
           norecetados.setSelected(true);
           txtrecetados.setEnabled(false);
           notoxicos.setSelected(true);
           txttoxicos.setEnabled(false);
         

    }
    void limpiar_suministrar() {
        id_medicamento.setText("");
        nombre_medicamento.setText("");
        forma.setText("");
        concentracion.setText("");
        cantidad.setText("");
    }
    void llenar_datos(){
    LocalDateTime fechaHoraActual = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DefaultTableModel modelo2 = (DefaultTableModel) tabla_medicamentos.getModel();
    String[] registros = new String[10];
    registros[0] = id_medicamento.getText();
    registros[1] = nombre_medicamento.getText();
    registros[2] = forma.getText();
    registros[3] = concentracion.getText();
    registros[4] = cantidad.getText();
    registros[5] = fechaHoraActual.format(formatter);
    registros[6] = nombre_enfermera.getText();
    modelo2.addRow(registros);
    tabla_medicamentos.setModel(modelo2);
}
    void id_emergencias() {
    try {
       
        String query = "SELECT MAX(id_emergencias) AS ultimo_id FROM emergencias";
        Statement statement = cn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            int ultimoId = resultSet.getInt("ultimo_id");
            int siguienteId = ultimoId + 1;
            id_emergencias.setText(String.valueOf(siguienteId));
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "ERROR\n" + ex.getMessage());
    }
}
    void guardar_datos() {
    try {
        String idSalas = IDSALAS.getText();
        String query = "INSERT INTO emergencias(id_salas, id_pacientes, id_empleados, motivo_emergencia, observaciones) " +
                       "VALUES ('" + idSalas + "','" + id_paciente.getText() + "','" + txtid_medico.getText() + "','" + motivo_consulta.getText() + "','" + observaciones.getText() + "')";

        PreparedStatement psz = cn.prepareStatement(query);
        psz.executeUpdate();
        JOptionPane.showMessageDialog(null, "EMERGENCIA AGREGADA");
    } catch (Exception error) {
        JOptionPane.showMessageDialog(null, "Error: " + error);
    }
}
    void guardar_detalle_emergencias(){
      
        for (int i = 0; i < tabla_medicamentos.getRowCount(); i++){
         try {
            String IDEMERGENCIAS = id_emergencias.getText();
            String sql2 = "";
            sql2 = "INSERT INTO detalles_emergencias (id_emergencias,id_productos,id_empleados,cantidad_suministrada,fecha)VALUES('" + IDEMERGENCIAS+ "','" + tabla_medicamentos.getValueAt(i,0).toString()+ "','" + txtid_enfermera.getText()+"','" + tabla_medicamentos.getValueAt(i,4).toString()+"','" + tabla_medicamentos.getValueAt(i,5).toString() +"')";
            PreparedStatement psz = cn.prepareStatement(sql2);

            int n;
            n = psz.executeUpdate();
            if (n > 0) {

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        }
    }
    void guardar_habitos(){
           String query = "UPDATE personas SET habitos_toxicos = '" + txttoxicos.getText() +"',medicamentos_alergicos = '" + txtalergicos.getText() +"',medicamentos_recetados = '" + txtrecetados.getText() +"' WHERE id_personas = '" + IDPERSONAS.getText() + "'";
            try{
                PreparedStatement psz = cn.prepareStatement(query);
                psz.executeUpdate();
            } catch(Exception error){
                JOptionPane.showMessageDialog(null, "Error: " + error);
            }
    }
    void limpiar(){
        
    }        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MEDICAMENTOS_ALERGICOS = new javax.swing.ButtonGroup();
        MEDICAMENTOS_RECETADOS2 = new javax.swing.ButtonGroup();
        HABITOS_TOXICOS2 = new javax.swing.ButtonGroup();
        ALCOHOL = new javax.swing.ButtonGroup();
        TABAQUISMO = new javax.swing.ButtonGroup();
        DROGAS = new javax.swing.ButtonGroup();
        TE = new javax.swing.ButtonGroup();
        CAFEINA = new javax.swing.ButtonGroup();
        FONDO = new javax.swing.JPanel();
        BARRA_TITULO = new javax.swing.JPanel();
        botonsalir = new javax.swing.JButton();
        LOGO = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        IDSALAS = new javax.swing.JLabel();
        IDPERSONAS = new javax.swing.JLabel();
        DATOS_PACIENTE = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        id_paciente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        apellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        edad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        sexo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        residencia = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        INFORMACION_DE_CONSULTA = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        id_emergencias = new javax.swing.JTextField();
        lblFechaActual = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtid_enfermera = new javax.swing.JTextField();
        nombre_enfermera = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtid_medico = new javax.swing.JTextField();
        txtnombre_medico = new javax.swing.JTextField();
        sala = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        MEDICAMENTOS_ALERGICOS2 = new javax.swing.JPanel();
        noalergico = new javax.swing.JCheckBox();
        alergico = new javax.swing.JCheckBox();
        txtalergicos = new javax.swing.JTextField();
        MEDICAMENTOS_RECETADOS = new javax.swing.JPanel();
        norecetados = new javax.swing.JCheckBox();
        sirecetados = new javax.swing.JCheckBox();
        txtrecetados = new javax.swing.JTextField();
        HABITOS_TOXICOS = new javax.swing.JPanel();
        notoxicos = new javax.swing.JCheckBox();
        sitoxicos = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txttoxicos = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        motivo_consulta = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        guardar_emergencias = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        id_medicamento = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        nombre_medicamento = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        concentracion = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        forma = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        observaciones = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_medicamentos = new javax.swing.JTable();
        cargartabla = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        FONDO.setBackground(new java.awt.Color(255, 255, 255));
        FONDO.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 153), null));

        BARRA_TITULO.setBackground(new java.awt.Color(12, 93, 172));
        BARRA_TITULO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        botonsalir.setBackground(new java.awt.Color(255, 51, 0));
        botonsalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botonsalir.setForeground(new java.awt.Color(255, 255, 255));
        botonsalir.setText("SALIR");
        botonsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonsalirActionPerformed(evt);
            }
        });

        LOGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/logo empresa peque.png"))); // NOI18N
        LOGO.setToolTipText("");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("SALA DE EMERGENCIAS");

        IDSALAS.setText("ID SALAS");

        IDPERSONAS.setText("ID PERSONAS");

        javax.swing.GroupLayout BARRA_TITULOLayout = new javax.swing.GroupLayout(BARRA_TITULO);
        BARRA_TITULO.setLayout(BARRA_TITULOLayout);
        BARRA_TITULOLayout.setHorizontalGroup(
            BARRA_TITULOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BARRA_TITULOLayout.createSequentialGroup()
                .addComponent(LOGO)
                .addGap(209, 209, 209)
                .addComponent(IDPERSONAS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IDSALAS)
                .addGap(73, 73, 73)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonsalir)
                .addContainerGap())
        );
        BARRA_TITULOLayout.setVerticalGroup(
            BARRA_TITULOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(BARRA_TITULOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BARRA_TITULOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonsalir)
                    .addGroup(BARRA_TITULOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(IDSALAS)
                        .addComponent(IDPERSONAS))))
        );

        DATOS_PACIENTE.setBackground(new java.awt.Color(255, 255, 255));
        DATOS_PACIENTE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DEL PACIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(12, 93, 172));
        jLabel2.setText("ID PACIENTE");

        id_paciente.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(12, 93, 172));
        jLabel3.setText("NOMBRE");

        nombre.setEditable(false);

        apellido.setEditable(false);
        apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(12, 93, 172));
        jLabel4.setText("APELLIDO");

        edad.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(12, 93, 172));
        jLabel5.setText("EDAD");

        sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "MASCULINO", "FEMENINO" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(12, 93, 172));
        jLabel6.setText("SEXO");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(12, 93, 172));
        jLabel7.setText("RESIDENCIA");

        residencia.setEditable(false);

        jButton4.setBackground(new java.awt.Color(12, 93, 172));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar.png"))); // NOI18N
        jButton4.setText("BUSCAR ");
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(12, 93, 172));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/boton-agregar.png"))); // NOI18N
        jButton5.setText("REGISTRAR");
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DATOS_PACIENTELayout = new javax.swing.GroupLayout(DATOS_PACIENTE);
        DATOS_PACIENTE.setLayout(DATOS_PACIENTELayout);
        DATOS_PACIENTELayout.setHorizontalGroup(
            DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DATOS_PACIENTELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DATOS_PACIENTELayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DATOS_PACIENTELayout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(54, 54, 54))
                    .addGroup(DATOS_PACIENTELayout.createSequentialGroup()
                        .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombre)
                            .addComponent(apellido)
                            .addComponent(sexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(edad)
                            .addComponent(id_paciente)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DATOS_PACIENTELayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(residencia, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        DATOS_PACIENTELayout.setVerticalGroup(
            DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DATOS_PACIENTELayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(id_paciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(edad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(residencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        INFORMACION_DE_CONSULTA.setBackground(new java.awt.Color(255, 255, 255));
        INFORMACION_DE_CONSULTA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INFORMACION DE CONSULTA DE EMERGENCIA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(12, 93, 172));
        jLabel9.setText("ID EMERGERNCIA");

        id_emergencias.setEditable(false);

        lblFechaActual.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFechaActual.setForeground(new java.awt.Color(12, 93, 172));
        lblFechaActual.setText("00/00/0000   00:00:00");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(12, 93, 172));
        jLabel17.setText("FECHA");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(12, 93, 172));
        jLabel21.setText("ID ENFERMERA");

        txtid_enfermera.setText("21");

        nombre_enfermera.setText("Paula Herrera Garcia");
        nombre_enfermera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_enfermeraActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(12, 93, 172));
        jLabel22.setText("ENFERMERA");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(12, 93, 172));
        jLabel23.setText("ID MEDICO");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(12, 93, 172));
        jLabel24.setText("MEDICO");

        txtid_medico.setText("12");
        txtid_medico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtid_medicoActionPerformed(evt);
            }
        });

        txtnombre_medico.setText("Carlos Hernandez Gomez");
        txtnombre_medico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombre_medicoActionPerformed(evt);
            }
        });

        sala.setEditable(false);

        jButton7.setBackground(new java.awt.Color(12, 93, 172));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar.png"))); // NOI18N
        jButton7.setText("BUSCAR SALA");
        jButton7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(12, 93, 172));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar.png"))); // NOI18N
        jButton9.setText("BUSCAR EMERGENCIA");
        jButton9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout INFORMACION_DE_CONSULTALayout = new javax.swing.GroupLayout(INFORMACION_DE_CONSULTA);
        INFORMACION_DE_CONSULTA.setLayout(INFORMACION_DE_CONSULTALayout);
        INFORMACION_DE_CONSULTALayout.setHorizontalGroup(
            INFORMACION_DE_CONSULTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(INFORMACION_DE_CONSULTALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id_emergencias, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(sala, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(INFORMACION_DE_CONSULTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(INFORMACION_DE_CONSULTALayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtid_enfermera, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(INFORMACION_DE_CONSULTALayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre_enfermera, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(INFORMACION_DE_CONSULTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(INFORMACION_DE_CONSULTALayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtid_medico, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(INFORMACION_DE_CONSULTALayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnombre_medico, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFechaActual)
                .addContainerGap())
        );
        INFORMACION_DE_CONSULTALayout.setVerticalGroup(
            INFORMACION_DE_CONSULTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(INFORMACION_DE_CONSULTALayout.createSequentialGroup()
                .addGroup(INFORMACION_DE_CONSULTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(INFORMACION_DE_CONSULTALayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(INFORMACION_DE_CONSULTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(lblFechaActual)))
                    .addGroup(INFORMACION_DE_CONSULTALayout.createSequentialGroup()
                        .addGroup(INFORMACION_DE_CONSULTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtid_enfermera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(INFORMACION_DE_CONSULTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(nombre_enfermera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(INFORMACION_DE_CONSULTALayout.createSequentialGroup()
                        .addGroup(INFORMACION_DE_CONSULTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtid_medico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(INFORMACION_DE_CONSULTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txtnombre_medico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(INFORMACION_DE_CONSULTALayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(INFORMACION_DE_CONSULTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(id_emergencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7)
                            .addComponent(sala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MEDICAMENTOS_ALERGICOS2.setBackground(new java.awt.Color(255, 255, 255));
        MEDICAMENTOS_ALERGICOS2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MEDICAMENTOS ALERGICOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        MEDICAMENTOS_ALERGICOS.add(noalergico);
        noalergico.setText(" NO ALERGICO");
        noalergico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noalergicoActionPerformed(evt);
            }
        });

        MEDICAMENTOS_ALERGICOS.add(alergico);
        alergico.setText("ALERGICO");
        alergico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alergicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MEDICAMENTOS_ALERGICOS2Layout = new javax.swing.GroupLayout(MEDICAMENTOS_ALERGICOS2);
        MEDICAMENTOS_ALERGICOS2.setLayout(MEDICAMENTOS_ALERGICOS2Layout);
        MEDICAMENTOS_ALERGICOS2Layout.setHorizontalGroup(
            MEDICAMENTOS_ALERGICOS2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MEDICAMENTOS_ALERGICOS2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MEDICAMENTOS_ALERGICOS2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtalergicos)
                    .addGroup(MEDICAMENTOS_ALERGICOS2Layout.createSequentialGroup()
                        .addComponent(alergico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(noalergico)))
                .addContainerGap())
        );
        MEDICAMENTOS_ALERGICOS2Layout.setVerticalGroup(
            MEDICAMENTOS_ALERGICOS2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MEDICAMENTOS_ALERGICOS2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MEDICAMENTOS_ALERGICOS2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noalergico)
                    .addComponent(alergico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtalergicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MEDICAMENTOS_RECETADOS.setBackground(new java.awt.Color(255, 255, 255));
        MEDICAMENTOS_RECETADOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MEDICAMENTOS RECETADOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        MEDICAMENTOS_RECETADOS2.add(norecetados);
        norecetados.setText(" NO");
        norecetados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                norecetadosActionPerformed(evt);
            }
        });

        MEDICAMENTOS_RECETADOS2.add(sirecetados);
        sirecetados.setText("SI");
        sirecetados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sirecetadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MEDICAMENTOS_RECETADOSLayout = new javax.swing.GroupLayout(MEDICAMENTOS_RECETADOS);
        MEDICAMENTOS_RECETADOS.setLayout(MEDICAMENTOS_RECETADOSLayout);
        MEDICAMENTOS_RECETADOSLayout.setHorizontalGroup(
            MEDICAMENTOS_RECETADOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MEDICAMENTOS_RECETADOSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MEDICAMENTOS_RECETADOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtrecetados, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MEDICAMENTOS_RECETADOSLayout.createSequentialGroup()
                        .addComponent(sirecetados)
                        .addGap(35, 35, 35)
                        .addComponent(norecetados)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MEDICAMENTOS_RECETADOSLayout.setVerticalGroup(
            MEDICAMENTOS_RECETADOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MEDICAMENTOS_RECETADOSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MEDICAMENTOS_RECETADOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(norecetados)
                    .addComponent(sirecetados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtrecetados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        HABITOS_TOXICOS.setBackground(new java.awt.Color(255, 255, 255));
        HABITOS_TOXICOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HABITOS TOXICOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        HABITOS_TOXICOS2.add(notoxicos);
        notoxicos.setText(" NO");
        notoxicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notoxicosActionPerformed(evt);
            }
        });

        HABITOS_TOXICOS2.add(sitoxicos);
        sitoxicos.setText("SI");
        sitoxicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sitoxicosActionPerformed(evt);
            }
        });

        txttoxicos.setColumns(20);
        txttoxicos.setRows(5);
        jScrollPane2.setViewportView(txttoxicos);

        javax.swing.GroupLayout HABITOS_TOXICOSLayout = new javax.swing.GroupLayout(HABITOS_TOXICOS);
        HABITOS_TOXICOS.setLayout(HABITOS_TOXICOSLayout);
        HABITOS_TOXICOSLayout.setHorizontalGroup(
            HABITOS_TOXICOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HABITOS_TOXICOSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HABITOS_TOXICOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addGroup(HABITOS_TOXICOSLayout.createSequentialGroup()
                        .addComponent(sitoxicos)
                        .addGap(35, 35, 35)
                        .addComponent(notoxicos)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        HABITOS_TOXICOSLayout.setVerticalGroup(
            HABITOS_TOXICOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HABITOS_TOXICOSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HABITOS_TOXICOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(notoxicos)
                    .addComponent(sitoxicos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MOTIVO DE CONSULTA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        motivo_consulta.setColumns(20);
        motivo_consulta.setRows(5);
        jScrollPane1.setViewportView(motivo_consulta);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        jButton1.setBackground(new java.awt.Color(12, 93, 172));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/receta.png"))); // NOI18N
        jButton1.setText("RECETA MEDICA");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        guardar_emergencias.setBackground(new java.awt.Color(12, 93, 172));
        guardar_emergencias.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        guardar_emergencias.setForeground(new java.awt.Color(255, 255, 255));
        guardar_emergencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/disco-flexible.png"))); // NOI18N
        guardar_emergencias.setText("GUARDAR");
        guardar_emergencias.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        guardar_emergencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_emergenciasActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SUMINISTAR MEDICAMENTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        jButton6.setBackground(new java.awt.Color(12, 93, 172));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar.png"))); // NOI18N
        jButton6.setText("BUSCAR MEDICAMENTO");
        jButton6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(12, 93, 172));
        jLabel10.setText("CODIGO");

        id_medicamento.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(12, 93, 172));
        jLabel11.setText("NOMBRE");

        nombre_medicamento.setEditable(false);
        nombre_medicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_medicamentoActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(12, 93, 172));
        jLabel18.setText("CONCENTRACION");

        concentracion.setEditable(false);
        concentracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                concentracionActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(12, 93, 172));
        jLabel19.setText("FORMA");

        forma.setEditable(false);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(12, 93, 172));
        jLabel20.setText("CANTIDAD");

        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id_medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombre_medicamento)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(concentracion, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(forma, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jLabel10)
                    .addComponent(id_medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(nombre_medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(concentracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(forma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OBSERVACIONES DE PACIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        observaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                observacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(observaciones)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(observaciones)
                .addContainerGap())
        );

        jButton8.setBackground(new java.awt.Color(12, 93, 172));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/informe-medico.png"))); // NOI18N
        jButton8.setText("DE ALTA");
        jButton8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        tabla_medicamentos.setAutoCreateRowSorter(true);
        tabla_medicamentos.setBackground(new java.awt.Color(12, 93, 173));
        tabla_medicamentos.setForeground(new java.awt.Color(255, 255, 255));
        tabla_medicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "FORMA  /  PRESENTACION", "CONCENTRACION", "CANTIDAD SUMINISTRADA", "FECHA/HORA", "ENFERMERA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabla_medicamentos);

        cargartabla.setBackground(new java.awt.Color(12, 93, 172));
        cargartabla.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cargartabla.setForeground(new java.awt.Color(255, 255, 255));
        cargartabla.setText("CARGAR");
        cargartabla.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cargartabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargartablaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FONDOLayout = new javax.swing.GroupLayout(FONDO);
        FONDO.setLayout(FONDOLayout);
        FONDOLayout.setHorizontalGroup(
            FONDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BARRA_TITULO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(INFORMACION_DE_CONSULTA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(FONDOLayout.createSequentialGroup()
                .addGap(356, 356, 356)
                .addComponent(guardar_emergencias, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(FONDOLayout.createSequentialGroup()
                .addGroup(FONDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FONDOLayout.createSequentialGroup()
                        .addComponent(DATOS_PACIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(FONDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(FONDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FONDOLayout.createSequentialGroup()
                                .addGroup(FONDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(MEDICAMENTOS_ALERGICOS2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(MEDICAMENTOS_RECETADOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HABITOS_TOXICOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cargartabla, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(FONDOLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );
        FONDOLayout.setVerticalGroup(
            FONDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FONDOLayout.createSequentialGroup()
                .addComponent(BARRA_TITULO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(INFORMACION_DE_CONSULTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FONDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DATOS_PACIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FONDOLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(FONDOLayout.createSequentialGroup()
                        .addGroup(FONDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FONDOLayout.createSequentialGroup()
                                .addComponent(MEDICAMENTOS_ALERGICOS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MEDICAMENTOS_RECETADOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(HABITOS_TOXICOS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(cargartabla)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(FONDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardar_emergencias)
                    .addComponent(jButton1)
                    .addComponent(jButton8))
                .addGap(16, 16, 16))
        );

        getContentPane().add(FONDO, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonsalirActionPerformed
  this.dispose();
    }//GEN-LAST:event_botonsalirActionPerformed

    private void alergicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alergicoActionPerformed
  txtalergicos.setEnabled(true);    
    }//GEN-LAST:event_alergicoActionPerformed

    private void sirecetadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sirecetadosActionPerformed
           txtrecetados.setEnabled(true);       
    }//GEN-LAST:event_sirecetadosActionPerformed

    private void sitoxicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sitoxicosActionPerformed
             
           txttoxicos.setEnabled(true);
    }//GEN-LAST:event_sitoxicosActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            new lista_pacientes().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(emergencias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void nombre_medicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_medicamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_medicamentoActionPerformed

    private void apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new registro_pacientes().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void noalergicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noalergicoActionPerformed
  txtalergicos.setEnabled(false);         
    }//GEN-LAST:event_noalergicoActionPerformed

    private void notoxicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notoxicosActionPerformed
 
           txttoxicos.setEnabled(false);
    }//GEN-LAST:event_notoxicosActionPerformed

    private void norecetadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_norecetadosActionPerformed
               txtrecetados.setEnabled(false);
    }//GEN-LAST:event_norecetadosActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        new lista_medicamentos2().setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void concentracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_concentracionActionPerformed
     
    }//GEN-LAST:event_concentracionActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        llenar_datos();  
        limpiar_suministrar();
    }//GEN-LAST:event_cantidadActionPerformed

    private void nombre_enfermeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_enfermeraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_enfermeraActionPerformed

    private void txtnombre_medicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombre_medicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombre_medicoActionPerformed

    private void txtid_medicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtid_medicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtid_medicoActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        new listado_salas1().setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void observacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_observacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_observacionesActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String query = "UPDATE emergencias SET estado = 2 WHERE id_emergencias = '" + id_emergencias.getText() + "'";
        try{
            PreparedStatement psz = cn.prepareStatement(query);
            psz.executeUpdate();
            JOptionPane.showMessageDialog(null, "PACIENTE DADO DE ALTA");
        } catch(Exception error){
            JOptionPane.showMessageDialog(null, "Error: " + error);
        }
        limpiar();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void guardar_emergenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_emergenciasActionPerformed
       guardar_datos();
       guardar_detalle_emergencias();
       guardar_habitos();
    }//GEN-LAST:event_guardar_emergenciasActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
           try {
               new lista_emergencias().setVisible(true);
           } catch (SQLException ex) {
               Logger.getLogger(emergencias.class.getName()).log(Level.SEVERE, null, ex);
           }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void cargartablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargartablaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cargartablaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        new orden_medica().setVisible(true);
        orden_medica.id_pacientes.setText(id_paciente.getText());
       orden_medica.id_medico.setText(txtid_medico.getText());
       orden_medica.paciente.setText(nombre.getText()+" "+apellido.getText());
       orden_medica.medico.setText(txtnombre_medico.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(emergencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(emergencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(emergencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(emergencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new emergencias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup ALCOHOL;
    private javax.swing.JPanel BARRA_TITULO;
    private javax.swing.ButtonGroup CAFEINA;
    private javax.swing.JPanel DATOS_PACIENTE;
    private javax.swing.ButtonGroup DROGAS;
    private javax.swing.JPanel FONDO;
    private javax.swing.JPanel HABITOS_TOXICOS;
    private javax.swing.ButtonGroup HABITOS_TOXICOS2;
    public static javax.swing.JLabel IDPERSONAS;
    public static javax.swing.JLabel IDSALAS;
    private javax.swing.JPanel INFORMACION_DE_CONSULTA;
    private javax.swing.JLabel LOGO;
    private javax.swing.ButtonGroup MEDICAMENTOS_ALERGICOS;
    private javax.swing.JPanel MEDICAMENTOS_ALERGICOS2;
    private javax.swing.JPanel MEDICAMENTOS_RECETADOS;
    private javax.swing.ButtonGroup MEDICAMENTOS_RECETADOS2;
    private javax.swing.ButtonGroup TABAQUISMO;
    private javax.swing.ButtonGroup TE;
    private javax.swing.JCheckBox alergico;
    public static javax.swing.JTextField apellido;
    private javax.swing.JButton botonsalir;
    public static javax.swing.JTextField cantidad;
    public static javax.swing.JButton cargartabla;
    public static javax.swing.JTextField concentracion;
    public static javax.swing.JTextField edad;
    public static javax.swing.JTextField forma;
    public static javax.swing.JButton guardar_emergencias;
    public static javax.swing.JTextField id_emergencias;
    public static javax.swing.JTextField id_medicamento;
    public static javax.swing.JTextField id_paciente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblFechaActual;
    public static javax.swing.JTextArea motivo_consulta;
    private javax.swing.JCheckBox noalergico;
    public static javax.swing.JTextField nombre;
    private javax.swing.JTextField nombre_enfermera;
    public static javax.swing.JTextField nombre_medicamento;
    private javax.swing.JCheckBox norecetados;
    private javax.swing.JCheckBox notoxicos;
    public static javax.swing.JTextField observaciones;
    public static javax.swing.JTextField residencia;
    public static javax.swing.JTextField sala;
    public static javax.swing.JComboBox<String> sexo;
    private javax.swing.JCheckBox sirecetados;
    private javax.swing.JCheckBox sitoxicos;
    private javax.swing.JTable tabla_medicamentos;
    private javax.swing.JTextField txtalergicos;
    private javax.swing.JTextField txtid_enfermera;
    public static javax.swing.JTextField txtid_medico;
    public static javax.swing.JTextField txtnombre_medico;
    private javax.swing.JTextField txtrecetados;
    private javax.swing.JTextArea txttoxicos;
    // End of variables declaration//GEN-END:variables
}
