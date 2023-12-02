
package Formularios;


import static Formularios.registro_usuarios.id_empleado;
import static Formularios.registro_usuarios.usuario;
import clases.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class empleado extends javax.swing.JFrame {
    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
    
    public empleado() throws SQLException {
        initComponents();
        id_personas();
        id_empleados();
        cargar_cargo();
        cargar_datos();
        cargar_especialidad();
        nombre.requestFocus();
        especialidad.setEnabled(false);
        
    }
        void guardar_persona(){
        try {
            String sexoseleccionado;
            sexoseleccionado = sexo.getSelectedItem().toString();
                if ("MASCULINO".equals(sexoseleccionado)) {
                } else if ("FEMENINO".equals(sexoseleccionado)) {
                        } 
                
            String sql = "";
            sql = "INSERT INTO personas (id_personas,nombre,apellido,genero,cedula)VALUES('" + id_persona.getText() + "','" + nombre.getText() + "','" + apellido.getText() + "','" + sexoseleccionado + "','" + cedula.getText() + "')";
            PreparedStatement psz = cn.prepareStatement(sql);

            int n;
            n = psz.executeUpdate();
            if (n > 0) {
            }
   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    void guardar_telefono(){
        int ultimoIdpersona = 0;
        int ultimoIdtelefono = 0;
        
           if(telefono.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El Telefono No puede estar en Blanco");
        } else {
            try{
                String query = "INSERT INTO telefonos(numero_telefono) VALUES ('" + telefono.getText() + "')";
                PreparedStatement psz = cn.prepareStatement(query);
                psz.executeUpdate();
            } catch(Exception error){
                JOptionPane.showMessageDialog(null, "Error: " + error);
            }
        }
        try {
       
        String query = "SELECT MAX(id_personas) AS ultimo_idpersona FROM personas";
        Statement statement = cn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
           ultimoIdpersona = resultSet.getInt("ultimo_idpersona");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "ERROR\n" + ex.getMessage());
    }
          try {
       
        String query = "SELECT MAX(id_telefonos) AS ultimo_idtelefono FROM telefonos";
        Statement statement = cn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
             ultimoIdtelefono = resultSet.getInt("ultimo_idtelefono");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "ERROR\n" + ex.getMessage());
    } 
           try{
                String query = "INSERT INTO telefonos_personas(id_telefonos, id_personas) VALUES ('" + ultimoIdtelefono + "','" + ultimoIdpersona + "')";
                PreparedStatement psz = cn.prepareStatement(query);
                psz.executeUpdate();
            } catch(Exception error){
                JOptionPane.showMessageDialog(null, "Error: " + error);
            }    
    }
    void guardar_datos(){
        try {
String sexoseleccionado;
sexoseleccionado = sexo.getSelectedItem().toString();
if ("MASCULINO".equals(sexoseleccionado)) {
} else if ("FEMENINO".equals(sexoseleccionado)) {
} 

            String query_tipo = "SELECT * FROM cargos_empleados WHERE nombre = '" + cargo.getSelectedItem() + "'";
            PreparedStatement pst = cn.prepareStatement(query_tipo);
            ResultSet rst = pst.executeQuery();
            rst.next();
            String id_especialidad = "0";
            if(!especialidad.getSelectedItem().equals("SELECCIONA")){
                String query_especialidad = "SELECT * FROM especialidades WHERE nombre = '" + especialidad.getSelectedItem() + "'";
                PreparedStatement thp = cn.prepareStatement(query_especialidad);
                ResultSet rst1 = thp.executeQuery();
                rst1.next();
                id_especialidad = rst1.getString("id_especialidades");
            }

         String sql = "";
             if ("MEDICO".equals(cargo.getSelectedItem())) {
                      sql = "INSERT INTO empleados (id_cargos_empleados, id_especialidades, id_personas, sueldo) VALUES (?, ?, ?, ?)";                      
                     PreparedStatement psz = cn.prepareStatement(sql);

                     psz.setString(1, rst.getString("id_cargos_empleados"));
                         if (!id_especialidad.equals("0")) {
                            psz.setString(2, id_especialidad);
                         } else {
                            psz.setNull(2, java.sql.Types.INTEGER);
                         }
                     psz.setString(3, id_persona.getText());
                     psz.setString(4, sueldo.getText());

                    int n = psz.executeUpdate();
                        if (n > 0) {
                         JOptionPane.showMessageDialog(null, "GUARDADO CON ÉXITO");
                         }
            } else {
            sql = "INSERT INTO empleados (id_cargos_empleados, id_personas, sueldo) VALUES (?, ?, ?)";
            PreparedStatement psz = cn.prepareStatement(sql);

            psz.setString(1, rst.getString("id_cargos_empleados"));
            psz.setString(2, id_persona.getText());
            psz.setString(3, sueldo.getText());

            int n = psz.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "GUARDADO CON ÉXITO");
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
    }
    void actualizar_datos(){
        try {
            String sexoseleccionado;
                sexoseleccionado = sexo.getSelectedItem().toString();
                if ("MASCULINO".equals(sexoseleccionado)) {
                } else if ("FEMENINO".equals(sexoseleccionado)) {
                } 
            String query_tipo = "SELECT * FROM cargo WHERE cargo = '" + cargo.getSelectedItem() + "'";
            PreparedStatement pst = cn.prepareStatement(query_tipo);
            ResultSet rst = pst.executeQuery();
            rst.next();
            String id_especialidad = "0";
            if(!especialidad.getSelectedItem().equals("SELECCIONA")){
                String query_especialidad = "SELECT * FROM especialidad WHERE especialidad = '" + especialidad.getSelectedItem() + "'";
                PreparedStatement thp = cn.prepareStatement(query_especialidad);
                ResultSet rst1 = thp.executeQuery();
                rst1.next();
                id_especialidad = rst1.getString("id_especialidad");
            }
            PreparedStatement psU = cn.prepareStatement("UPDATE empleado SET id_cargo = '" + rst.getString("id_cargo") + "',nombre='"+nombre.getText()+"',apellido='"+apellido.getText()+"',cedula='"+cedula.getText()+"',telefono='"+telefono.getText()+"',direccion='"+sueldo.getText()+"',sexo='"+sexoseleccionado+"' where id_empleado='"+id_empleado.getText()+"'");
            psU.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(empleado.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null,ex);
        }
        JOptionPane.showMessageDialog(null, "ACTUALIZADO CON EXITO");
    }
    void borrar_datos() {
          String query = "UPDATE personas SET estado = 0 WHERE id_personas = '" + id_persona.getText() + "'";
            try{
                PreparedStatement psz = cn.prepareStatement(query);
                psz.executeUpdate();
                JOptionPane.showMessageDialog(null, "Persona Eliminada");
                limpiar();
            } catch(Exception error){
                JOptionPane.showMessageDialog(null, "Error: " + error);
            }
    }
    void cargar_especialidad(){
        try{
            String query = "SELECT * FROM especialidades WHERE estado = 1";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                especialidad.addItem(rs.getString("nombre"));
            }
        } catch (Exception error){
            JOptionPane.showMessageDialog(null,"Error: " + error);
        }
    } 
    void cargar_cargo(){
        try{
            String query = "SELECT * FROM cargos_empleados WHERE estado = 1";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                cargo.addItem(rs.getString("nombre"));
            }
        } catch (Exception error){
            JOptionPane.showMessageDialog(null,"Error: " + error);
        }
    } 
    void cargar_datos() {
    DefaultTableModel modelo2 = (DefaultTableModel) tabla_empleados.getModel();
    modelo2.setRowCount(0); // Limpia el modelo de la tabla

    String sql = "SELECT p.nombre, p.apellido, p.genero, p.cedula, e.id_empleados, e.sueldo, c.nombre AS cargo, es.nombre AS especialidad " +
                 "FROM empleados e " +
                 "INNER JOIN personas p ON e.id_personas = p.id_personas " +
                 "INNER JOIN cargos_empleados c ON e.id_cargos_empleados = c.id_cargos_empleados " +
                 "LEFT JOIN especialidades es ON e.id_especialidades = es.id_especialidades LIMIT 100";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String[] registros = new String[8]; // Considerando 8 campos de información

            registros[0] = rs.getString("id_empleados");
            registros[1] = rs.getString("nombre");
            registros[2] = rs.getString("apellido");
            registros[3] = rs.getString("genero");
            registros[4] = rs.getString("cedula");
            registros[5] = rs.getString("sueldo");
            registros[6] = rs.getString("cargo");
            registros[7] = rs.getString("especialidad");

            modelo2.addRow(registros);
        }
        tabla_empleados.setModel(modelo2);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}
    void limpiar(){
        nombre.setText("");
        apellido.setText("");
        cedula.setText("");
        telefono.setText("");
        sueldo.setText("");
        cargo.setSelectedIndex(0);
        especialidad.setSelectedIndex(0);
        sexo.setSelectedIndex(0);
    }
    void cargar_datos2(){
    DefaultTableModel modelo2 = (DefaultTableModel) tabla_empleados.getModel();
    modelo2.getDataVector().clear();
    String[] registros = new String[10];
    String sql = "SELECT e.id_empleados, e.nombre, e.apellido, e.sexo, e.cedula, e.telefono, e.direccion,es.especialidad, c.descripcion " +
                 "FROM empleados e " +
                 "INNER JOIN cargos_empleados c ON e.id_cargos_empleados = c.id_cargos_empleados " +
                 "INNER JOIN especialidades es ON e.id_especialidades = es.id_especialidades " +
                 "LIMIT 100";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            registros[1] = rs.getString("nombre");
            registros[2] = rs.getString("apellido");
            registros[3] = rs.getString("sexo");
            registros[4] = rs.getString("cedula");
            registros[5] = rs.getString("telefono");
            registros[6] = rs.getString("direccion");
            registros[7] = rs.getString("descripcion");
            registros[8] = rs.getString("especialidad");// Ahora obtenemos la descripción del cargo
            registros[0] = rs.getString("id_empleado");
            modelo2.addRow(registros);
        }
        tabla_empleados.setModel(modelo2);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}
    void id_personas() {
    try {
       
        String query = "SELECT MAX(id_personas) AS ultimo_id FROM personas";
        Statement statement = cn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            int ultimoId = resultSet.getInt("ultimo_id");
            int siguienteId = ultimoId + 1;
            id_persona.setText(String.valueOf(siguienteId));
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "ERROR\n" + ex.getMessage());
    }
}
    void id_empleados() {
    try {
       
        String query = "SELECT MAX(id_empleados) AS ultimo_id FROM empleados";
        Statement statement = cn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            int ultimoId = resultSet.getInt("ultimo_id");
            int siguienteId = ultimoId + 1;
            id_empleado.setText(String.valueOf(siguienteId));
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "ERROR\n" + ex.getMessage());
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        id_empleado = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        sueldo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cedula = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        sexo = new javax.swing.JComboBox<>();
        cargo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_empleados = new javax.swing.JTable();
        AGREGAR = new javax.swing.JButton();
        ACTUALIZAR = new javax.swing.JButton();
        ELIMINAR = new javax.swing.JButton();
        SALIR = new javax.swing.JButton();
        LIMPIAR = new javax.swing.JButton();
        especialidad = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        id_persona = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        buscar_persona = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 153), null));

        id_empleado.setEditable(false);
        id_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_empleadoActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(12, 93, 173));
        jLabel1.setText("ID EMPLEADO");

        jLabel2.setBackground(new java.awt.Color(0, 153, 153));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(12, 93, 173));
        jLabel2.setText("NOMBRE");

        apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 153, 153));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(12, 93, 173));
        jLabel3.setText("APELLIDO");

        jLabel4.setBackground(new java.awt.Color(0, 153, 153));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(12, 93, 173));
        jLabel4.setText("TELEFONO");

        sueldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sueldoActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 153, 153));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(12, 93, 173));
        jLabel5.setText("SUELDO");

        jLabel6.setBackground(new java.awt.Color(0, 153, 153));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(12, 93, 173));
        jLabel6.setText("SEXO");

        jLabel7.setBackground(new java.awt.Color(0, 153, 153));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(12, 93, 173));
        jLabel7.setText("CEDULA");

        jLabel8.setBackground(new java.awt.Color(0, 153, 153));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(12, 93, 173));
        jLabel8.setText("CARGO");

        sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONA", "MASCULINO", "FEMENINO" }));

        cargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONA" }));
        cargo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cargoItemStateChanged(evt);
            }
        });
        cargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargoActionPerformed(evt);
            }
        });

        tabla_empleados.setBackground(new java.awt.Color(12, 93, 172));
        tabla_empleados.setForeground(new java.awt.Color(255, 255, 255));
        tabla_empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO", "SEXO", "CEDULA", "SUELDO", "CARGO", "ESPECIALIDAD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_empleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_empleados);
        if (tabla_empleados.getColumnModel().getColumnCount() > 0) {
            tabla_empleados.getColumnModel().getColumn(0).setMinWidth(80);
            tabla_empleados.getColumnModel().getColumn(0).setMaxWidth(80);
            tabla_empleados.getColumnModel().getColumn(1).setMinWidth(150);
            tabla_empleados.getColumnModel().getColumn(1).setMaxWidth(150);
            tabla_empleados.getColumnModel().getColumn(2).setMinWidth(150);
            tabla_empleados.getColumnModel().getColumn(2).setMaxWidth(150);
            tabla_empleados.getColumnModel().getColumn(3).setMinWidth(100);
            tabla_empleados.getColumnModel().getColumn(3).setMaxWidth(100);
            tabla_empleados.getColumnModel().getColumn(4).setMinWidth(100);
            tabla_empleados.getColumnModel().getColumn(4).setMaxWidth(100);
            tabla_empleados.getColumnModel().getColumn(5).setMinWidth(100);
            tabla_empleados.getColumnModel().getColumn(5).setMaxWidth(100);
            tabla_empleados.getColumnModel().getColumn(6).setMinWidth(150);
            tabla_empleados.getColumnModel().getColumn(6).setMaxWidth(150);
            tabla_empleados.getColumnModel().getColumn(7).setMinWidth(150);
            tabla_empleados.getColumnModel().getColumn(7).setMaxWidth(150);
        }

        AGREGAR.setBackground(new java.awt.Color(12, 93, 173));
        AGREGAR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AGREGAR.setForeground(new java.awt.Color(255, 255, 255));
        AGREGAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/boton-agregar.png"))); // NOI18N
        AGREGAR.setText("AGREGAR");
        AGREGAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AGREGARActionPerformed(evt);
            }
        });

        ACTUALIZAR.setBackground(new java.awt.Color(12, 93, 173));
        ACTUALIZAR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ACTUALIZAR.setForeground(new java.awt.Color(255, 255, 255));
        ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/sincronizar.png"))); // NOI18N
        ACTUALIZAR.setText("ACTUALIZAR");
        ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACTUALIZARActionPerformed(evt);
            }
        });

        ELIMINAR.setBackground(new java.awt.Color(12, 93, 173));
        ELIMINAR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ELIMINAR.setForeground(new java.awt.Color(255, 255, 255));
        ELIMINAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/papelera-de-reciclaje.png"))); // NOI18N
        ELIMINAR.setText("ELIMINAR");
        ELIMINAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ELIMINARActionPerformed(evt);
            }
        });

        SALIR.setBackground(new java.awt.Color(255, 0, 0));
        SALIR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SALIR.setForeground(new java.awt.Color(255, 255, 255));
        SALIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar-sesion (1).png"))); // NOI18N
        SALIR.setText("SALIR");
        SALIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALIRActionPerformed(evt);
            }
        });

        LIMPIAR.setBackground(new java.awt.Color(12, 93, 173));
        LIMPIAR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LIMPIAR.setForeground(new java.awt.Color(255, 255, 255));
        LIMPIAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/limpiar.png"))); // NOI18N
        LIMPIAR.setText("LIMPIAR");
        LIMPIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPIARActionPerformed(evt);
            }
        });

        especialidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONA" }));

        jLabel9.setBackground(new java.awt.Color(0, 153, 153));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(12, 93, 173));
        jLabel9.setText("ESPECIALIDAD");

        id_persona.setEditable(false);
        id_persona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_personaActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(0, 153, 153));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(12, 93, 173));
        jLabel10.setText("ID PERSONA");

        buscar_persona.setBackground(new java.awt.Color(12, 93, 173));
        buscar_persona.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buscar_persona.setForeground(new java.awt.Color(255, 255, 255));
        buscar_persona.setText("...");
        buscar_persona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_personaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cedula)
                    .addComponent(jLabel1)
                    .addComponent(id_empleado)
                    .addComponent(nombre)
                    .addComponent(jLabel3)
                    .addComponent(apellido)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4)
                    .addComponent(telefono)
                    .addComponent(jLabel5)
                    .addComponent(sueldo)
                    .addComponent(jLabel6)
                    .addComponent(sexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(especialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(id_persona, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar_persona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(AGREGAR, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ACTUALIZAR, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ELIMINAR, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LIMPIAR, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AGREGAR)
                    .addComponent(ACTUALIZAR)
                    .addComponent(ELIMINAR)
                    .addComponent(LIMPIAR)
                    .addComponent(SALIR))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28))
                    .addComponent(id_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(id_persona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buscar_persona)))
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(especialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(12, 93, 173));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ADMINISTRAR EMPLEADOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(599, 599, 599)
                .addComponent(jLabel14)
                .addContainerGap(484, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargoActionPerformed
String seleccioncargo = (String) cargo.getSelectedItem();
if(seleccioncargo.equals("MEDICO")){
    especialidad.setEnabled(true);
} else {
    especialidad.setEnabled(false);  
}
    }//GEN-LAST:event_cargoActionPerformed

    private void AGREGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AGREGARActionPerformed
    guardar_persona();
    guardar_datos();
    guardar_telefono();
    limpiar();
    id_personas();
    id_empleados();
    cargar_datos();
        nombre.requestFocus();
    }//GEN-LAST:event_AGREGARActionPerformed

    private void ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACTUALIZARActionPerformed
    actualizar_datos();
    id_personas();
    id_empleados();
    limpiar();
    cargar_datos();
    }//GEN-LAST:event_ACTUALIZARActionPerformed

    private void SALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALIRActionPerformed
    this.dispose();
    new menu_empleados().setVisible(true);
    }//GEN-LAST:event_SALIRActionPerformed

    private void ELIMINARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELIMINARActionPerformed
    borrar_datos();
    }//GEN-LAST:event_ELIMINARActionPerformed

    private void LIMPIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPIARActionPerformed
    limpiar();
    id_personas();
    id_empleados();
    nombre.requestFocus();
    }//GEN-LAST:event_LIMPIARActionPerformed

    private void tabla_empleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_empleadosMouseClicked
    int fila = tabla_empleados.getSelectedRow();
if (fila >= 0) {
    DefaultTableModel table_model = (DefaultTableModel) tabla_empleados.getModel();
    String id_tipo = (String) table_model.getValueAt(tabla_empleados.getSelectedRow(), 6);
    try {
        String query = "SELECT * FROM cargos_empleados WHERE nombre = ?";
        try (PreparedStatement pst = cn.prepareStatement(query)) {
            pst.setString(1, id_tipo);
            try (ResultSet rst = pst.executeQuery()) {
                if (rst.next()) {
                    cargo.setSelectedItem(rst.getString("nombre"));
                }
            }
        }
    } catch (Exception error) {
        JOptionPane.showMessageDialog(null, "Error: " + error);
    }
    String nombre_especialidad = (String) table_model.getValueAt(tabla_empleados.getSelectedRow(), 7);
    try {
        String query = "SELECT * FROM especialidades WHERE nombre = ?";
        try (PreparedStatement pst = cn.prepareStatement(query)) {
            pst.setString(1, nombre_especialidad);
            try (ResultSet rst = pst.executeQuery()) {
                if (rst.next()) {
                    especialidad.setSelectedItem(rst.getString("nombre"));
                } else {
                    especialidad.setSelectedIndex(0);
                }
            }
        }
    } catch (Exception error) {
        JOptionPane.showMessageDialog(null, "Error: " + error);
    }
    String id_empleado_seleccionado = (String) table_model.getValueAt(tabla_empleados.getSelectedRow(), 0);
    try {
        String query = "SELECT id_personas FROM empleados WHERE id_empleados = ?";
        try (PreparedStatement pst = cn.prepareStatement(query)) {
            pst.setString(1, id_empleado_seleccionado);
            try (ResultSet rst = pst.executeQuery()) {
                if (rst.next()) {
                    String id_persona_seleccionado = rst.getString("id_personas");
                    id_persona.setText(id_persona_seleccionado);
                }
            }
        }
    } catch (Exception error) {
        JOptionPane.showMessageDialog(null, "Error: " + error);
    }
       String sexoEmpleado = (String) table_model.getValueAt(tabla_empleados.getSelectedRow(), 3);
       sexo.setSelectedItem(sexoEmpleado);


    id_empleado.setText(id_empleado_seleccionado);
    nombre.setText((String) table_model.getValueAt(tabla_empleados.getSelectedRow(), 1));
    apellido.setText((String) table_model.getValueAt(tabla_empleados.getSelectedRow(), 2));
    cedula.setText((String) table_model.getValueAt(tabla_empleados.getSelectedRow(), 4));
    sueldo.setText((String) table_model.getValueAt(tabla_empleados.getSelectedRow(), 5));
}


    }//GEN-LAST:event_tabla_empleadosMouseClicked

    private void cargoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cargoItemStateChanged
        
    }//GEN-LAST:event_cargoItemStateChanged

    private void id_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_empleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_empleadoActionPerformed

    private void apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidoActionPerformed

    private void id_personaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_personaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_personaActionPerformed

    private void buscar_personaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_personaActionPerformed
        try {
            new lista_personas().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buscar_personaActionPerformed

    private void sueldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sueldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sueldoActionPerformed

 
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
            java.util.logging.Logger.getLogger(empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new empleado().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(empleado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ACTUALIZAR;
    private javax.swing.JButton AGREGAR;
    private javax.swing.JButton ELIMINAR;
    private javax.swing.JButton LIMPIAR;
    private javax.swing.JButton SALIR;
    public static javax.swing.JTextField apellido;
    private javax.swing.JButton buscar_persona;
    private javax.swing.JComboBox<String> cargo;
    public static javax.swing.JTextField cedula;
    private javax.swing.JComboBox<String> especialidad;
    private javax.swing.JTextField id_empleado;
    public static javax.swing.JTextField id_persona;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
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
    public static javax.swing.JTextField nombre;
    public static javax.swing.JComboBox<String> sexo;
    public static javax.swing.JTextField sueldo;
    private javax.swing.JTable tabla_empleados;
    private javax.swing.JTextField telefono;
    // End of variables declaration//GEN-END:variables
}
