
package Formularios;


import static Formularios.emergencias.cantidad;
import static Formularios.emergencias.concentracion;
import static Formularios.emergencias.forma;
import static Formularios.emergencias.id_medicamento;
import static Formularios.emergencias.nombre_medicamento;
import static Formularios.login.idEmpleado;
import clases.Conectar;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class internos extends javax.swing.JFrame {
   Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
public static int datotipo=0;
public static int datoid=0;
public static String datonombre="";

    public internos() {
          
        initComponents(); 
           setExtendedState(JFrame.MAXIMIZED_BOTH);
        IDSALAS.setVisible(false);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            LocalDateTime fechaactual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            lblFechaActual.setText(fechaactual.format(formato));
        },0,1,TimeUnit.SECONDS);
        cargar_datos_empleados();
       id_internos();
       cargartabla.setVisible(false);
       cargarID.setVisible(false);
       internos.btn_actualizar_interno.setEnabled(false);
    }
    void id_internos() {
    try {
       
        String query = "SELECT MAX(id_internos) AS ultimo_id FROM internos";
        Statement statement = cn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            int ultimoId = resultSet.getInt("ultimo_id");
            int siguienteId = ultimoId + 1;
            ID_INTERNOS.setText(String.valueOf(siguienteId));
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "ERROR\n" + ex.getMessage());
    }
}
    void cargar_datos_empleados(){
    datotipo=login.idrolusuario;
    datonombre=login.empleado;
    datoid=login.idEmpleado;
    if(datotipo==1){
        txtnombre_enfermera.setText(datonombre);
      //txtid_enfermera.setText(datoid);
    }
}
    
    void guardar_datos() {
    try {
        String idSalas = IDSALAS.getText();
        String query = "INSERT INTO internos(id_salas, id_pacientes, id_empleados, motivo_interno, observaciones) " +
                       "VALUES ('" + idSalas + "','" + id_paciente.getText() + "','" + txtid_medico.getText() + "','" + diagnostico.getText() + "','" + observaciones.getText() + "')";

        PreparedStatement psz = cn.prepareStatement(query);
        psz.executeUpdate();
        JOptionPane.showMessageDialog(null, "Interno agregado");
    } catch (Exception error) {
        JOptionPane.showMessageDialog(null, "Error: " + error);
    }
 
}
    void actualizar_estado_sala() {
    try {
        String idSala = IDSALAS.getText();
        String query = "UPDATE salas SET estado = 2 WHERE id_salas = ?";
        
        try (PreparedStatement pst = cn.prepareStatement(query)) {
            pst.setString(1, idSala);
            int n = pst.executeUpdate();
            if (n > 0) {
            } 
        }
    } catch (Exception error) {
        JOptionPane.showMessageDialog(null, "Error al actualizar el estado de la sala: " + error.getMessage());
    }
}
    void actualizar_estado_sala2() {
    try {
        String idSala = IDSALAS.getText();
        String query = "UPDATE salas SET estado = 1 WHERE id_salas = ?";
        
        try (PreparedStatement pst = cn.prepareStatement(query)) {
            pst.setString(1, idSala);
            int n = pst.executeUpdate();
            if (n > 0) {
            } 
        }
    } catch (Exception error) {
        JOptionPane.showMessageDialog(null, "Error al actualizar el estado de la sala: " + error.getMessage());
    }
}
    void guardar_detalle_internos() {
    try {
        String Sqldelete = "DELETE FROM detalles_internos WHERE id_internos = ?";
        try (PreparedStatement deleteStatement = cn.prepareStatement(Sqldelete)) {
            deleteStatement.setString(1, ID_INTERNOS.getText());
            int deleteResult = deleteStatement.executeUpdate();
            if (deleteResult > 0) {
            }
        }

        String insertSql = "INSERT INTO detalles_internos (id_internos, id_productos, id_empleados, cantidad_suministrada, fecha) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement insertStatement = cn.prepareStatement(insertSql)) {
            for (int i = 0; i < tabla_medicamentos.getRowCount(); i++) {
                insertStatement.setString(1, ID_INTERNOS.getText());
                insertStatement.setString(2, tabla_medicamentos.getValueAt(i, 0).toString());
                insertStatement.setString(3, txtid_enfermera.getText());
                insertStatement.setString(4, tabla_medicamentos.getValueAt(i, 4).toString());
                insertStatement.setString(5, tabla_medicamentos.getValueAt(i, 5).toString());

                int insertResult = insertStatement.executeUpdate();
                if (insertResult > 0) {
                }
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}
    void actualizar_producto(){
        if (cantidad.getText().equals("") || id_medicamento.getText().equals("")) {
    JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos");
} else {
    try {
       
        String queryStockActual = "SELECT stock FROM productos WHERE id_productos = ?";
        try (PreparedStatement pstStockActual = cn.prepareStatement(queryStockActual)) {
            pstStockActual.setString(1, id_medicamento.getText());
            ResultSet rsStockActual = pstStockActual.executeQuery();

            if (rsStockActual.next()) {
                int stockActual = rsStockActual.getInt("stock");

                
                int cantidadIngresada = Integer.parseInt(cantidad.getText());
                int nuevoStock = stockActual - cantidadIngresada;

             
                String queryUpdateStock = "UPDATE productos SET stock = ? WHERE id_productos = ?";
                try (PreparedStatement pstUpdateStock = cn.prepareStatement(queryUpdateStock)) {
                    pstUpdateStock.setInt(1, nuevoStock);
                    pstUpdateStock.setString(2, id_medicamento.getText());
                    pstUpdateStock.executeUpdate();

                }
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado");
            }
        }
    } catch (Exception error) {
        JOptionPane.showMessageDialog(null, "Error: " + error);
    }
}

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
    registros[6] = txtnombre_enfermera.getText();
    modelo2.addRow(registros);
    tabla_medicamentos.setModel(modelo2);
}
    void limpiar_suministrar() {
        id_medicamento.setText("");
        nombre_medicamento.setText("");
        forma.setText("");
        concentracion.setText("");
        cantidad.setText("");
    }
    void limpiar(){
    sala.setText("");
    id_paciente.setText("");
    nombre.setText("");
    edad.setText("");
    diagnostico.setText("");
    fecha_ingreso.setText("");
    observaciones.setText("");
    IDSALAS.setText("");
    cargarID.doClick();
    id_medicamento.setText("");
    nombre_medicamento.setText("");
    concentracion.setText("");
    forma.setText("");
    cantidad.setText("");
    sexo.setSelectedIndex(0);
    cargartabla.doClick();
    }
    void cargar_detalles() {
    DefaultTableModel modeloMedicamentos = (DefaultTableModel) tabla_medicamentos.getModel();
    modeloMedicamentos.setRowCount(0);
    int idInterno = Integer.parseInt(ID_INTERNOS.getText());

    String sql = "SELECT di.id_productos, p.nombre_comercial, p.tipo_presentacion, p.concentracion, " +
                 "di.cantidad_suministrada, di.fecha, e.id_empleados, per.nombre, per.apellido " +
                 "FROM detalles_internos di " +
                 "INNER JOIN productos p ON di.id_productos = p.id_productos " +
                 "INNER JOIN empleados e ON di.id_empleados = e.id_empleados " +
                 "INNER JOIN personas per ON e.id_personas = per.id_personas " +
                 "WHERE di.id_internos = " + idInterno;

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String[] registros = new String[7];

            registros[0] = rs.getString("id_productos");
            registros[1] = rs.getString("nombre_comercial");
            registros[2] = rs.getString("tipo_presentacion");
            registros[3] = rs.getString("concentracion");
            registros[4] = rs.getString("cantidad_suministrada");
            registros[5] = rs.getString("fecha");
            registros[6] = rs.getString("nombre") + " " + rs.getString("apellido");

            modeloMedicamentos.addRow(registros);
        }
        tabla_medicamentos.setModel(modeloMedicamentos);

        if (modeloMedicamentos.getRowCount() > 0) {
            txtid_enfermera.setText(rs.getString("id_empleados"));
        } 
        if (modeloMedicamentos.getRowCount() > 0) {
            txtnombre_enfermera.setText(modeloMedicamentos.getValueAt(0, 6).toString());
        } 
    } catch (SQLException ex) {
       // JOptionPane.showMessageDialog(null, ex);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane1 = new java.awt.ScrollPane();
        jPanel1 = new javax.swing.JPanel();
        BARRA_TITULO = new javax.swing.JPanel();
        botonsalir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblFechaActual = new javax.swing.JLabel();
        ID_INTERNOS = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        sala = new javax.swing.JTextField();
        IDSALAS = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtnombre_enfermera = new javax.swing.JTextField();
        txtid_enfermera = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        DATOS_PACIENTE = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        id_paciente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        edad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        sexo = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        fecha_ingreso = new javax.swing.JTextField();
        diagnostico = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        observaciones = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtnombre_medico = new javax.swing.JTextField();
        txtid_medico = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_medicamentos = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        nombre_medicamento = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        concentracion = new javax.swing.JTextField();
        forma = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        id_medicamento = new javax.swing.JTextField();
        btn_actualizar_interno = new javax.swing.JButton();
        btn_agregar_interno = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        cargartabla = new javax.swing.JButton();
        btn_agregar_interno1 = new javax.swing.JButton();
        cargarID = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 153), null));

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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("INTERNOS");

        javax.swing.GroupLayout BARRA_TITULOLayout = new javax.swing.GroupLayout(BARRA_TITULO);
        BARRA_TITULO.setLayout(BARRA_TITULOLayout);
        BARRA_TITULOLayout.setHorizontalGroup(
            BARRA_TITULOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BARRA_TITULOLayout.createSequentialGroup()
                .addGap(543, 543, 543)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonsalir)
                .addContainerGap())
        );
        BARRA_TITULOLayout.setVerticalGroup(
            BARRA_TITULOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BARRA_TITULOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BARRA_TITULOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonsalir)
                    .addComponent(jLabel8))
                .addGap(14, 14, 14))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DE INTERNACION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(12, 93, 172));
        jLabel2.setText("ID INTERNO");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(12, 93, 172));
        jLabel3.setText("FECHA");

        lblFechaActual.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFechaActual.setForeground(new java.awt.Color(12, 93, 172));
        lblFechaActual.setText("00/00/0000   00:00:00");

        ID_INTERNOS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ID_INTERNOS.setForeground(new java.awt.Color(12, 93, 172));
        ID_INTERNOS.setText("#00000000000");

        jButton7.setBackground(new java.awt.Color(12, 93, 172));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("BUSCAR SALA");
        jButton7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        sala.setEditable(false);

        IDSALAS.setText("ID SALAS");

        jButton4.setBackground(new java.awt.Color(12, 93, 172));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("BUSCAR INTERNO");
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(ID_INTERNOS)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(sala, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(IDSALAS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFechaActual)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblFechaActual))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(ID_INTERNOS)
                            .addComponent(jButton7)
                            .addComponent(sala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IDSALAS)
                            .addComponent(jButton4))))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DE ENFERMERA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(12, 93, 172));
        jLabel9.setText("ID ENFERMERA");

        txtnombre_enfermera.setEditable(false);
        txtnombre_enfermera.setText("Isabel Sanchez");
        txtnombre_enfermera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombre_enfermeraActionPerformed(evt);
            }
        });

        txtid_enfermera.setEditable(false);
        txtid_enfermera.setText("20");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(12, 93, 172));
        jLabel12.setText("NOMBRE");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtnombre_enfermera, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(txtid_enfermera, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtid_enfermera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtnombre_enfermera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DATOS_PACIENTE.setBackground(new java.awt.Color(255, 255, 255));
        DATOS_PACIENTE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DEL PACIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(12, 93, 172));
        jLabel6.setText("ID PACIENTE");

        id_paciente.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(12, 93, 172));
        jLabel7.setText("NOMBRE");

        nombre.setEditable(false);

        edad.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(12, 93, 172));
        jLabel10.setText("EDAD");

        sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "MASCULINO", "FEMENINO" }));
        sexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(12, 93, 172));
        jLabel11.setText("SEXO");

        jButton1.setBackground(new java.awt.Color(12, 93, 172));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("AGREGAR PACIENTE");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DATOS_PACIENTELayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DATOS_PACIENTELayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(id_paciente, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DATOS_PACIENTELayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edad, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
            .addGroup(DATOS_PACIENTELayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DATOS_PACIENTELayout.setVerticalGroup(
            DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DATOS_PACIENTELayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(DATOS_PACIENTELayout.createSequentialGroup()
                        .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(id_paciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(6, 6, 6))
                    .addComponent(edad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DIAGNOSTICO MEDICO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(12, 93, 172));
        jLabel13.setText("DIAGNOSTICO");

        fecha_ingreso.setEditable(false);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(12, 93, 172));
        jLabel14.setText("FECHA DE INGRESO");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(12, 93, 172));
        jLabel17.setText("OBSERVACIONES");

        observaciones.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(observaciones)
                    .addComponent(diagnostico)
                    .addComponent(fecha_ingreso))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(diagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(fecha_ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(observaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DEL MEDICO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(12, 93, 172));
        jLabel15.setText("ID MEDICO");

        txtnombre_medico.setEditable(false);
        txtnombre_medico.setText("Pascasio Nunez");

        txtid_medico.setEditable(false);
        txtid_medico.setText("5");
        txtid_medico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtid_medicoActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(12, 93, 172));
        jLabel16.setText("NOMBRE");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtnombre_medico, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(txtid_medico, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtid_medico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtnombre_medico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MEDICAMENTOS SUMINISTRADOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

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
        if (tabla_medicamentos.getColumnModel().getColumnCount() > 0) {
            tabla_medicamentos.getColumnModel().getColumn(0).setMinWidth(100);
            tabla_medicamentos.getColumnModel().getColumn(0).setMaxWidth(100);
            tabla_medicamentos.getColumnModel().getColumn(1).setMinWidth(200);
            tabla_medicamentos.getColumnModel().getColumn(1).setMaxWidth(200);
            tabla_medicamentos.getColumnModel().getColumn(4).setMinWidth(175);
            tabla_medicamentos.getColumnModel().getColumn(4).setMaxWidth(175);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SUMINISTRAR MEDICAMENTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        jButton2.setBackground(new java.awt.Color(12, 93, 172));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("BUSCAR");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(12, 93, 172));
        jLabel18.setText("NOMBRE");

        nombre_medicamento.setEditable(false);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(12, 93, 172));
        jLabel19.setText("CONCENTRACION");

        concentracion.setEditable(false);

        forma.setEditable(false);
        forma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formaActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(12, 93, 172));
        jLabel20.setText("FORMA");

        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(12, 93, 172));
        jLabel21.setText("CANTIDAD");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(12, 93, 172));
        jLabel22.setText("CODIGO");

        id_medicamento.setEditable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(id_medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(nombre_medicamento, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(concentracion, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(forma, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jLabel18)
                        .addComponent(nombre_medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19)
                        .addComponent(concentracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(forma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)
                        .addComponent(jLabel22)
                        .addComponent(id_medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_actualizar_interno.setBackground(new java.awt.Color(12, 93, 172));
        btn_actualizar_interno.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_actualizar_interno.setForeground(new java.awt.Color(255, 255, 255));
        btn_actualizar_interno.setText("ACTUALIZAR INTERNO");
        btn_actualizar_interno.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_actualizar_interno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizar_internoActionPerformed(evt);
            }
        });

        btn_agregar_interno.setBackground(new java.awt.Color(12, 93, 172));
        btn_agregar_interno.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_agregar_interno.setForeground(new java.awt.Color(255, 255, 255));
        btn_agregar_interno.setText("AGREGAR INTERNO");
        btn_agregar_interno.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_agregar_interno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_internoActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(12, 93, 172));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("DE ALTA");
        jButton6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

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

        btn_agregar_interno1.setBackground(new java.awt.Color(12, 93, 172));
        btn_agregar_interno1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_agregar_interno1.setForeground(new java.awt.Color(255, 255, 255));
        btn_agregar_interno1.setText("NUEVO INTERNO");
        btn_agregar_interno1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_agregar_interno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_interno1ActionPerformed(evt);
            }
        });

        cargarID.setBackground(new java.awt.Color(12, 93, 172));
        cargarID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cargarID.setForeground(new java.awt.Color(255, 255, 255));
        cargarID.setText("CARGAR");
        cargarID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cargarID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BARRA_TITULO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_actualizar_interno)
                        .addGap(18, 18, 18)
                        .addComponent(btn_agregar_interno)
                        .addGap(18, 18, 18)
                        .addComponent(btn_agregar_interno1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cargartabla, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cargarID, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(DATOS_PACIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(BARRA_TITULO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(DATOS_PACIENTE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_actualizar_interno)
                            .addComponent(btn_agregar_interno)
                            .addComponent(btn_agregar_interno1))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cargartabla)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cargarID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonsalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonsalirActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new lista_medicamentos().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_formaActionPerformed

    private void sexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sexoActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        if (cantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LA CANTIDAD NO PUEDE ESTAR EN BLANCO");
            cantidad.requestFocus(true);
            return;
        }
        actualizar_producto();
        llenar_datos();
        limpiar_suministrar();
    }//GEN-LAST:event_cantidadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            new lista_pacientes_internos().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(internos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtnombre_enfermeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombre_enfermeraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombre_enfermeraActionPerformed

    private void btn_actualizar_internoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizar_internoActionPerformed
    guardar_detalle_internos();
       if (id_paciente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "NO HAZ SELECCIONADO NINGUN INTERNO");
            sala.requestFocus(true);
            return;
        } else {
             JOptionPane.showMessageDialog(null, "INTERNO ACTUALIZADO");
        }
   
    
    }//GEN-LAST:event_btn_actualizar_internoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       try {
           new lista_internos().setVisible(true);
       } catch (SQLException ex) {
           Logger.getLogger(internos.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btn_agregar_internoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_internoActionPerformed
guardar_datos();
guardar_detalle_internos();
actualizar_estado_sala();
 limpiar();
 menu.cargarDATOS.doClick();
    }//GEN-LAST:event_btn_agregar_internoActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
            String query = "UPDATE internos SET estado = 2 WHERE id_internos = '" + ID_INTERNOS.getText() + "'";
            try{
                PreparedStatement psz = cn.prepareStatement(query);
                psz.executeUpdate();
                JOptionPane.showMessageDialog(null, "PACIENTE DADO DE ALTA");
            } catch(Exception error){
                JOptionPane.showMessageDialog(null, "Error: " + error);
            }
            actualizar_estado_sala2();
            limpiar();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtid_medicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtid_medicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtid_medicoActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
     new listado_salas().setVisible(true); 
    }//GEN-LAST:event_jButton7ActionPerformed

    private void cargartablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargartablaActionPerformed
        cargar_detalles();
    }//GEN-LAST:event_cargartablaActionPerformed

    private void btn_agregar_interno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_interno1ActionPerformed
      
       btn_agregar_interno.setEnabled(true);
       limpiar();
    }//GEN-LAST:event_btn_agregar_interno1ActionPerformed

    private void cargarIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarIDActionPerformed
        id_internos();       
    }//GEN-LAST:event_cargarIDActionPerformed

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
            java.util.logging.Logger.getLogger(internos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(internos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(internos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(internos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new internos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BARRA_TITULO;
    private javax.swing.JPanel DATOS_PACIENTE;
    public static javax.swing.JLabel IDSALAS;
    public static javax.swing.JLabel ID_INTERNOS;
    private javax.swing.JButton botonsalir;
    public static javax.swing.JButton btn_actualizar_interno;
    public static javax.swing.JButton btn_agregar_interno;
    public static javax.swing.JButton btn_agregar_interno1;
    public static javax.swing.JTextField cantidad;
    public static javax.swing.JButton cargarID;
    public static javax.swing.JButton cargartabla;
    public static javax.swing.JTextField concentracion;
    public static javax.swing.JTextField diagnostico;
    public static javax.swing.JTextField edad;
    public static javax.swing.JTextField fecha_ingreso;
    public static javax.swing.JTextField forma;
    public static javax.swing.JTextField id_medicamento;
    public static javax.swing.JTextField id_paciente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblFechaActual;
    public static javax.swing.JTextField nombre;
    public static javax.swing.JTextField nombre_medicamento;
    public static javax.swing.JTextField observaciones;
    public static javax.swing.JTextField sala;
    private java.awt.ScrollPane scrollPane1;
    public static javax.swing.JComboBox<String> sexo;
    private javax.swing.JTable tabla_medicamentos;
    private javax.swing.JTextField txtid_enfermera;
    public static javax.swing.JTextField txtid_medico;
    private javax.swing.JTextField txtnombre_enfermera;
    public static javax.swing.JTextField txtnombre_medico;
    // End of variables declaration//GEN-END:variables


}

