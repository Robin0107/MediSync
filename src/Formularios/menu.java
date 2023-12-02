
package Formularios;

import clases.Conectar;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JButton;




public class menu extends javax.swing.JFrame {


    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();

    
    public menu() {

        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
            cargar();
            cargarLogo();
            cargar_citas(); 
           todas.setSelected(true);
           verificar_permisos();
           cargarDATOS.setVisible(false);
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

void cargar(){
        try{
            String query = "select count(*) as cantidad_citas from citas where estado = 1";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            txtnumero_citas.setText(("#0000")+(rs.getString("cantidad_citas")));
        } catch (Exception error){
            JOptionPane.showMessageDialog(null,"Error: " + error);
        }
        try{
            String query = "select count(*) as cantidad_habitaciones from salas where estado = 1";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            numero_habitaciones.setText(("#0000")+(rs.getString("cantidad_habitaciones")));
        } catch (Exception error){
            JOptionPane.showMessageDialog(null,"Error: " + error);
        }
        try{
            String query = "select count(*) as cantidad_internos from internos where estado = 1";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            numero_internos.setText(("#0000")+(rs.getString("cantidad_internos")));
        } catch (Exception error){
            JOptionPane.showMessageDialog(null,"Error: " + error);
        }
         try{
            String query = "select count(*) as cantidad_medicos from empleados where id_cargos_empleados = 2";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            txtnumero_medicos.setText(("#0000")+(rs.getString("cantidad_medicos")));
        } catch (Exception error){
            JOptionPane.showMessageDialog(null,"Error: " + error);
        }
   }
void verificar_permisos() {
    int idRolUsuario = login.idrolusuario;
    List<Integer> permisos = obtenerPermisos(idRolUsuario);
    deshabilitarTodosBotones();
    for (int idVista : permisos) {
        habilitarBotonSegunPermiso(idVista);
    }
}
private List<Integer> obtenerPermisos(int idRolUsuario) {
    List<Integer> permisos = new ArrayList<>();
    String query = "SELECT id_vistas FROM permisos WHERE id_roles_usuarios = ?";

    try (PreparedStatement statement = cn.prepareStatement(query)) {
        statement.setInt(1, idRolUsuario);

        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int idVista = rs.getInt("id_vistas");
                permisos.add(idVista);
            }
        }
    } catch (SQLException error) {
        JOptionPane.showMessageDialog(null, "Error al obtener permisos: " + error.getMessage());
    }

    return permisos;
}
private void deshabilitarTodosBotones() {
    btn_emergencias.setEnabled(false);
    btn_citas.setEnabled(false);
    btn_empleados.setEnabled(false);
    btn_internos.setEnabled(false);
    btn_farmacia.setEnabled(false);
    btn_pacientes.setEnabled(false);
    btn_reportes.setEnabled(false);
    btn_facturacion.setEnabled(false);
    btn_laboratorio.setEnabled(false);
    btn_ajustes.setEnabled(false);
}
private void habilitarBotonSegunPermiso(int idVista) {
    String nombreBoton = obtenerNombreBoton(idVista);
    habilitarBoton(nombreBoton);
}
private String obtenerNombreBoton(int idVista) {
 
    switch (idVista) {
        case 1:
            return "btn_emergencias";
        case 2:
            return "btn_citas";
        case 3:
            return "btn_empleados";
        case 4:
            return "btn_internos";
        case 5:
            return "btn_farmacia";
        case 6:
            return "btn_ajustes";
        case 7:
            return "btn_reportes";
        case 8:
            return "btn_pacientes";
        case 9:
            return "btn_facturacion";
        case 10:
            return "btn_laboratorio";

        default:
            return null;
    }
}
private void habilitarBoton(String nombreBoton) {
    if (nombreBoton != null) {
        JButton boton = obtenerBotonPorNombre(nombreBoton);
        if (boton != null) {
            boton.setEnabled(true);
        }
    }
}
private JButton obtenerBotonPorNombre(String nombreBoton) {
    switch (nombreBoton) {
        case "btn_emergencias":
            return btn_emergencias;
        case "btn_citas":
            return btn_citas;
        case "btn_empleados":
            return btn_empleados;
        case "btn_internos":
            return btn_internos;
        case "btn_farmacia":
            return btn_farmacia;
        case "btn_pacientes":
            return btn_pacientes;
        case "btn_reportes":
            return btn_reportes;
        case "btn_facturacion":
            return btn_facturacion;
        case "btn_laboratorio":
            return btn_laboratorio;
        case "btn_ajustes":
            return btn_ajustes;
        // Agrega más casos según sea necesario
        default:
            return null;
    }
}
void cargar_citas2() {
    DefaultTableModel modelo2 = (DefaultTableModel) tabla_citas.getModel();
    modelo2.setRowCount(0);

    String filtro = buscar_citas.getText().trim().toLowerCase();
    String estadoFiltrado = "";
    if (todas.isSelected()) {
        estadoFiltrado = "";
    } else if (hoy.isSelected()) {
        estadoFiltrado = " AND DATE(c.fecha) = CURDATE()";
    } else if (canceladas.isSelected()) {
        estadoFiltrado = " AND c.estado = 2";
    } else if (retrasadas.isSelected()) {
        estadoFiltrado = " AND DATE(c.fecha) = CURDATE()";
    }

    String sql = "SELECT c.id_citas, c.id_personas, CONCAT(p.nombre, ' ', p.apellido) AS nombre_apellido, " +
            "es.nombre AS nombre_departamento, CONCAT(pe.nombre, ' ', pe.apellido) AS nombre_apellido_empleado, " +
            "c.fecha, c.estado " +
            "FROM citas c " +
            "INNER JOIN personas p ON c.id_personas = p.id_personas " +
            "INNER JOIN especialidades es ON es.id_especialidades = c.id_especialidades " +
            "LEFT JOIN empleados e ON e.id_empleados = c.id_empleados " +
            "LEFT JOIN personas pe ON e.id_personas = pe.id_personas " +
            "WHERE (LOWER(p.nombre) LIKE '%" + filtro + "%' OR LOWER(p.apellido) LIKE '%" + filtro + "%' OR LOWER(es.nombre) LIKE '%" + filtro + "%')" +
            estadoFiltrado + " LIMIT 100";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String[] registros = new String[7];

            registros[0] = rs.getString("id_citas");
            registros[1] = rs.getString("id_personas");
            registros[2] = rs.getString("nombre_apellido");
            registros[3] = rs.getString("nombre_departamento");
            registros[4] = rs.getString("nombre_apellido_empleado");
            registros[5] = rs.getString("fecha");
            registros[6] = convertirEstado(rs.getInt("estado")); 

            modelo2.addRow(registros);
        }
        tabla_citas.setModel(modelo2);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}
private String convertirEstado(int estado) {
    switch (estado) {
        case 1:
            return "PENDIENTE";
        case 2:
            return "CANCELADA";
        case 3:
            return "RETRASADA";
        case 4:
            return "ATENDIDA";
        default:
            return "DESCONOCIDA";
    }
}
void cargar_citas() {
    DefaultTableModel modelo2 = (DefaultTableModel) tabla_citas.getModel();
    modelo2.setRowCount(0);

    String filtro = buscar_citas.getText().trim().toLowerCase();
    String estadoFiltrado = "";
    
    if (todas.isSelected()) {
        estadoFiltrado = "";
    } else if (hoy.isSelected()) {
        estadoFiltrado = " AND DATE(c.fecha) = CURDATE()";
    } else if (canceladas.isSelected()) {
        estadoFiltrado = " AND c.estado = 2";
    } else if (retrasadas.isSelected()) {
        // Modificación 1: Agregamos la condición para citas retrasadas
        estadoFiltrado = " AND (DATE(c.fecha) < CURDATE() OR (DATE(c.fecha) = CURDATE() AND TIME(c.fecha) < CURTIME()))";
    }

    String sql = "SELECT c.id_citas, c.id_personas, CONCAT(p.nombre, ' ', p.apellido) AS nombre_apellido, " +
            "es.nombre AS nombre_departamento, CONCAT(pe.nombre, ' ', pe.apellido) AS nombre_apellido_empleado, " +
            "c.fecha, c.estado " +
            "FROM citas c " +
            "INNER JOIN personas p ON c.id_personas = p.id_personas " +
            "INNER JOIN especialidades es ON es.id_especialidades = c.id_especialidades " +
            "LEFT JOIN empleados e ON e.id_empleados = c.id_empleados " +
            "LEFT JOIN personas pe ON e.id_personas = pe.id_personas " +
            "WHERE (LOWER(p.nombre) LIKE '%" + filtro + "%' OR LOWER(p.apellido) LIKE '%" + filtro + "%' OR LOWER(es.nombre) LIKE '%" + filtro + "%')" +
            estadoFiltrado + " LIMIT 100";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String[] registros = new String[7];

            registros[0] = rs.getString("id_citas");
            registros[1] = rs.getString("id_personas");
            registros[2] = rs.getString("nombre_apellido");
            registros[3] = rs.getString("nombre_departamento");
            registros[4] = rs.getString("nombre_apellido_empleado");
            registros[5] = rs.getString("fecha");
            
            // Modificación 2: Cambiamos el estado según la condición
            int estado = rs.getInt("estado");
            if (estado == 1 && (rs.getDate("fecha").before(new java.util.Date()) || rs.getTime("fecha").before(new java.util.Date()))) {
                registros[6] = convertirEstado(3); // Cambiar a "RETRASADA"
            } else {
                registros[6] = convertirEstado(estado);
            }

            modelo2.addRow(registros);
        }
        tabla_citas.setModel(modelo2);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}






    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FILTRO = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        botonsalir = new javax.swing.JButton();
        LOGO = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_emergencias = new javax.swing.JButton();
        btn_citas = new javax.swing.JButton();
        btn_empleados = new javax.swing.JButton();
        btn_ajustes = new javax.swing.JButton();
        btn_internos = new javax.swing.JButton();
        btn_farmacia = new javax.swing.JButton();
        btn_pacientes = new javax.swing.JButton();
        btn_reportes = new javax.swing.JButton();
        btn_facturacion = new javax.swing.JButton();
        btn_laboratorio = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        OPCIONES = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_citas = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        buscar_citas = new javax.swing.JTextField();
        todas = new javax.swing.JRadioButton();
        hoy = new javax.swing.JRadioButton();
        retrasadas = new javax.swing.JRadioButton();
        canceladas = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtnumero_citas = new javax.swing.JLabel();
        txtnumero_citas1 = new javax.swing.JLabel();
        txtnumero_citas2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        MenuPlegable = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        numero_internos = new javax.swing.JLabel();
        txtnumero_citas8 = new javax.swing.JLabel();
        txtnumero_citas9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtnumero_medicos = new javax.swing.JLabel();
        txtnumero_citas11 = new javax.swing.JLabel();
        txtnumero_citas12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cargarDATOS = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        numero_habitaciones = new javax.swing.JLabel();
        txtnumero_citas14 = new javax.swing.JLabel();
        txtnumero_citas15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(12, 93, 172));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(LOGO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonsalir)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(botonsalir))
            .addComponent(LOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 147, 214));

        btn_emergencias.setBackground(new java.awt.Color(12, 93, 172));
        btn_emergencias.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_emergencias.setForeground(new java.awt.Color(255, 255, 255));
        btn_emergencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/libro (1).png"))); // NOI18N
        btn_emergencias.setText("EMERGENCIAS");
        btn_emergencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_emergenciasActionPerformed(evt);
            }
        });

        btn_citas.setBackground(new java.awt.Color(12, 93, 172));
        btn_citas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_citas.setForeground(new java.awt.Color(255, 255, 255));
        btn_citas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cita (2).png"))); // NOI18N
        btn_citas.setText("CITAS");
        btn_citas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_citas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_citasActionPerformed(evt);
            }
        });

        btn_empleados.setBackground(new java.awt.Color(12, 93, 172));
        btn_empleados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_empleados.setForeground(new java.awt.Color(255, 255, 255));
        btn_empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/historial-medico (1).png"))); // NOI18N
        btn_empleados.setText("EMPLEADOS");
        btn_empleados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_empleadosActionPerformed(evt);
            }
        });

        btn_ajustes.setBackground(new java.awt.Color(12, 93, 172));
        btn_ajustes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_ajustes.setForeground(new java.awt.Color(255, 255, 255));
        btn_ajustes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/simbolo-de-la-medicina (1).png"))); // NOI18N
        btn_ajustes.setText("AJUSTES");
        btn_ajustes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_ajustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ajustesActionPerformed(evt);
            }
        });

        btn_internos.setBackground(new java.awt.Color(12, 93, 172));
        btn_internos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_internos.setForeground(new java.awt.Color(255, 255, 255));
        btn_internos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/paciente-interno (1).png"))); // NOI18N
        btn_internos.setText("INTERNOS");
        btn_internos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_internos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_internosActionPerformed(evt);
            }
        });

        btn_farmacia.setBackground(new java.awt.Color(12, 93, 172));
        btn_farmacia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_farmacia.setForeground(new java.awt.Color(255, 255, 255));
        btn_farmacia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/farmacia.png"))); // NOI18N
        btn_farmacia.setText("FARMACIA");
        btn_farmacia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_farmacia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_farmaciaActionPerformed(evt);
            }
        });

        btn_pacientes.setBackground(new java.awt.Color(12, 93, 172));
        btn_pacientes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_pacientes.setForeground(new java.awt.Color(255, 255, 255));
        btn_pacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/paciente.png"))); // NOI18N
        btn_pacientes.setText("PACIENTES");
        btn_pacientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_pacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pacientesActionPerformed(evt);
            }
        });

        btn_reportes.setBackground(new java.awt.Color(12, 93, 172));
        btn_reportes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_reportes.setForeground(new java.awt.Color(255, 255, 255));
        btn_reportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/carpeta.png"))); // NOI18N
        btn_reportes.setText("REPORTES");
        btn_reportes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reportesActionPerformed(evt);
            }
        });

        btn_facturacion.setBackground(new java.awt.Color(12, 93, 172));
        btn_facturacion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_facturacion.setForeground(new java.awt.Color(255, 255, 255));
        btn_facturacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/facturacion (1).png"))); // NOI18N
        btn_facturacion.setText("FACTURACION");
        btn_facturacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_facturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_facturacionActionPerformed(evt);
            }
        });

        btn_laboratorio.setBackground(new java.awt.Color(12, 93, 172));
        btn_laboratorio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_laboratorio.setForeground(new java.awt.Color(255, 255, 255));
        btn_laboratorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/investigador.png"))); // NOI18N
        btn_laboratorio.setText("LABORATORIO");
        btn_laboratorio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_laboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_laboratorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_emergencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_citas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btn_empleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ajustes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_internos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_farmacia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_pacientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_reportes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_facturacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_laboratorio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btn_emergencias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_citas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_empleados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_internos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_farmacia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pacientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_reportes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_facturacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_laboratorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_ajustes)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        OPCIONES.setBackground(new java.awt.Color(255, 255, 255));
        OPCIONES.setPreferredSize(new java.awt.Dimension(827, 500));
        OPCIONES.setRequestFocusEnabled(false);

        tabla_citas.setBackground(new java.awt.Color(12, 93, 172));
        tabla_citas.setForeground(new java.awt.Color(255, 255, 255));
        tabla_citas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID CITAS", "ID PERSONA", "NOMBRE ", "DEPARTAMENTO", "DOCTOR", "FECHA & HORA", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_citas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_citasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_citas);
        if (tabla_citas.getColumnModel().getColumnCount() > 0) {
            tabla_citas.getColumnModel().getColumn(0).setMinWidth(80);
            tabla_citas.getColumnModel().getColumn(0).setMaxWidth(80);
            tabla_citas.getColumnModel().getColumn(1).setMinWidth(100);
            tabla_citas.getColumnModel().getColumn(1).setMaxWidth(100);
            tabla_citas.getColumnModel().getColumn(3).setMinWidth(175);
            tabla_citas.getColumnModel().getColumn(3).setMaxWidth(175);
        }

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FILTRAR DATOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(12, 93, 172));
        jLabel2.setText("BUSCAR CITA");

        buscar_citas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscar_citasKeyReleased(evt);
            }
        });

        todas.setBackground(new java.awt.Color(255, 255, 255));
        FILTRO.add(todas);
        todas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        todas.setForeground(new java.awt.Color(12, 93, 172));
        todas.setText("TODAS");
        todas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todasActionPerformed(evt);
            }
        });

        hoy.setBackground(new java.awt.Color(255, 255, 255));
        FILTRO.add(hoy);
        hoy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        hoy.setForeground(new java.awt.Color(12, 93, 172));
        hoy.setText("HOY");
        hoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoyActionPerformed(evt);
            }
        });

        retrasadas.setBackground(new java.awt.Color(255, 255, 255));
        FILTRO.add(retrasadas);
        retrasadas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        retrasadas.setForeground(new java.awt.Color(12, 93, 172));
        retrasadas.setText("RETRASADAS");
        retrasadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retrasadasActionPerformed(evt);
            }
        });

        canceladas.setBackground(new java.awt.Color(255, 255, 255));
        FILTRO.add(canceladas);
        canceladas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        canceladas.setForeground(new java.awt.Color(12, 93, 172));
        canceladas.setText("CANCELADAS");
        canceladas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canceladasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(buscar_citas, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(todas)
                .addGap(18, 18, 18)
                .addComponent(hoy)
                .addGap(18, 18, 18)
                .addComponent(retrasadas)
                .addGap(18, 18, 18)
                .addComponent(canceladas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(buscar_citas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(todas)
                    .addComponent(hoy)
                    .addComponent(retrasadas)
                    .addComponent(canceladas))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout OPCIONESLayout = new javax.swing.GroupLayout(OPCIONES);
        OPCIONES.setLayout(OPCIONESLayout);
        OPCIONESLayout.setHorizontalGroup(
            OPCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OPCIONESLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OPCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1313, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        OPCIONESLayout.setVerticalGroup(
            OPCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OPCIONESLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 93, 172), null));

        jPanel5.setBackground(new java.awt.Color(12, 93, 172));

        txtnumero_citas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtnumero_citas.setForeground(new java.awt.Color(255, 255, 255));
        txtnumero_citas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtnumero_citas.setText("#00000");

        txtnumero_citas1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtnumero_citas1.setForeground(new java.awt.Color(255, 255, 255));
        txtnumero_citas1.setText("CITAS");

        txtnumero_citas2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtnumero_citas2.setForeground(new java.awt.Color(255, 255, 255));
        txtnumero_citas2.setText("PENDIENTES");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/calendario.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtnumero_citas2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtnumero_citas1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(txtnumero_citas)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnumero_citas1)
                    .addComponent(txtnumero_citas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnumero_citas2)
                .addContainerGap())
        );

        MenuPlegable.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout MenuPlegableLayout = new javax.swing.GroupLayout(MenuPlegable);
        MenuPlegable.setLayout(MenuPlegableLayout);
        MenuPlegableLayout.setHorizontalGroup(
            MenuPlegableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
        );
        MenuPlegableLayout.setVerticalGroup(
            MenuPlegableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 93, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(12, 93, 172));

        numero_internos.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        numero_internos.setForeground(new java.awt.Color(255, 255, 255));
        numero_internos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        numero_internos.setText("#00000");

        txtnumero_citas8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtnumero_citas8.setForeground(new java.awt.Color(255, 255, 255));
        txtnumero_citas8.setText("INTERNOS");

        txtnumero_citas9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtnumero_citas9.setForeground(new java.awt.Color(255, 255, 255));
        txtnumero_citas9.setText("INGRESADOS");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/paciente-interno.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtnumero_citas8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(numero_internos))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtnumero_citas9))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jLabel5)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnumero_citas8)
                    .addComponent(numero_internos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnumero_citas9)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(12, 93, 172));

        txtnumero_medicos.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtnumero_medicos.setForeground(new java.awt.Color(255, 255, 255));
        txtnumero_medicos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtnumero_medicos.setText("#00000");

        txtnumero_citas11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtnumero_citas11.setForeground(new java.awt.Color(255, 255, 255));
        txtnumero_citas11.setText("MEDICOS");

        txtnumero_citas12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtnumero_citas12.setForeground(new java.awt.Color(255, 255, 255));
        txtnumero_citas12.setText("ACTIVOS");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/doctor.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addComponent(txtnumero_citas11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(txtnumero_medicos))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jLabel3))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtnumero_citas12)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnumero_citas11)
                    .addComponent(txtnumero_medicos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnumero_citas12)
                .addContainerGap())
        );

        cargarDATOS.setBackground(new java.awt.Color(12, 93, 172));
        cargarDATOS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cargarDATOS.setForeground(new java.awt.Color(255, 255, 255));
        cargarDATOS.setText("CARGAR");
        cargarDATOS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cargarDATOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarDATOSActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(12, 93, 172));

        numero_habitaciones.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        numero_habitaciones.setForeground(new java.awt.Color(255, 255, 255));
        numero_habitaciones.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        numero_habitaciones.setText("#00000");

        txtnumero_citas14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtnumero_citas14.setForeground(new java.awt.Color(255, 255, 255));
        txtnumero_citas14.setText("HABITACIONES");

        txtnumero_citas15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtnumero_citas15.setForeground(new java.awt.Color(255, 255, 255));
        txtnumero_citas15.setText("DISPONIBLES");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cama-de-hospital (2).png"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtnumero_citas14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(numero_habitaciones))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtnumero_citas15))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jLabel7)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnumero_citas14)
                    .addComponent(numero_habitaciones))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnumero_citas15)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MenuPlegable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cargarDATOS, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(MenuPlegable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(cargarDATOS))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(OPCIONES, javax.swing.GroupLayout.DEFAULT_SIZE, 1319, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(OPCIONES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonsalirActionPerformed
    menusalir p2 = new menusalir();
    p2.setSize(153, 90);
    p2.setLocation(0, 0);
    MenuPlegable.removeAll();
    MenuPlegable.add(p2,BorderLayout.CENTER);
    MenuPlegable.revalidate();
    MenuPlegable.repaint();
    }//GEN-LAST:event_botonsalirActionPerformed

    private void btn_citasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_citasActionPerformed
    new citas().setVisible(true);
    }//GEN-LAST:event_btn_citasActionPerformed

    private void btn_empleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_empleadosActionPerformed
    new menu_empleados().setVisible(true);
    }//GEN-LAST:event_btn_empleadosActionPerformed

    private void btn_ajustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ajustesActionPerformed
      new menu_ajustes().setVisible(true);
    }//GEN-LAST:event_btn_ajustesActionPerformed

    private void btn_emergenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_emergenciasActionPerformed
     new emergencias().setVisible(true);
    }//GEN-LAST:event_btn_emergenciasActionPerformed

    private void btn_internosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_internosActionPerformed
      new internos().setVisible(true);
    }//GEN-LAST:event_btn_internosActionPerformed

    private void btn_farmaciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_farmaciaActionPerformed
     new menu_farmacia().setVisible(true);
    }//GEN-LAST:event_btn_farmaciaActionPerformed

    private void btn_pacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pacientesActionPerformed
        new menu_pacientes().setVisible(true);
    }//GEN-LAST:event_btn_pacientesActionPerformed

    private void btn_reportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reportesActionPerformed
        new menu_reportes().setVisible(true);
    }//GEN-LAST:event_btn_reportesActionPerformed

    private void btn_facturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_facturacionActionPerformed
        new facturar().setVisible(true);
    }//GEN-LAST:event_btn_facturacionActionPerformed

    private void btn_laboratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_laboratorioActionPerformed
       new menu_laboratorio().setVisible(true);
    }//GEN-LAST:event_btn_laboratorioActionPerformed

    private void buscar_citasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar_citasKeyReleased
     cargar_citas();
    }//GEN-LAST:event_buscar_citasKeyReleased

    private void hoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoyActionPerformed
             hoy.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        cargar_citas();
    }
});
    }//GEN-LAST:event_hoyActionPerformed

    private void retrasadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retrasadasActionPerformed
             retrasadas.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        cargar_citas();
    }
});
    }//GEN-LAST:event_retrasadasActionPerformed

    private void canceladasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_canceladasActionPerformed
             canceladas.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        cargar_citas();
    }
});
    }//GEN-LAST:event_canceladasActionPerformed

    private void todasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todasActionPerformed
        todas.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        cargar_citas();
    }
});
    }//GEN-LAST:event_todasActionPerformed

    private void cargarDATOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarDATOSActionPerformed
        cargar();
    }//GEN-LAST:event_cargarDATOSActionPerformed

    private void tabla_citasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_citasMouseClicked
   new estado_citas().setVisible(true);
    }//GEN-LAST:event_tabla_citasMouseClicked
        

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
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup FILTRO;
    private javax.swing.JLabel LOGO;
    public static javax.swing.JPanel MenuPlegable;
    private javax.swing.JPanel OPCIONES;
    private javax.swing.JButton botonsalir;
    private javax.swing.JButton btn_ajustes;
    private javax.swing.JButton btn_citas;
    private javax.swing.JButton btn_emergencias;
    private javax.swing.JButton btn_empleados;
    private javax.swing.JButton btn_facturacion;
    private javax.swing.JButton btn_farmacia;
    private javax.swing.JButton btn_internos;
    private javax.swing.JButton btn_laboratorio;
    private javax.swing.JButton btn_pacientes;
    private javax.swing.JButton btn_reportes;
    private javax.swing.JTextField buscar_citas;
    private javax.swing.JRadioButton canceladas;
    public static javax.swing.JButton cargarDATOS;
    private javax.swing.JRadioButton hoy;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel numero_habitaciones;
    private javax.swing.JLabel numero_internos;
    private javax.swing.JRadioButton retrasadas;
    private javax.swing.JTable tabla_citas;
    private javax.swing.JRadioButton todas;
    private javax.swing.JLabel txtnumero_citas;
    private javax.swing.JLabel txtnumero_citas1;
    private javax.swing.JLabel txtnumero_citas11;
    private javax.swing.JLabel txtnumero_citas12;
    private javax.swing.JLabel txtnumero_citas14;
    private javax.swing.JLabel txtnumero_citas15;
    private javax.swing.JLabel txtnumero_citas2;
    private javax.swing.JLabel txtnumero_citas8;
    private javax.swing.JLabel txtnumero_citas9;
    private javax.swing.JLabel txtnumero_medicos;
    // End of variables declaration//GEN-END:variables
}
