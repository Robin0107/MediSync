
package Formularios;

import clases.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;


public class citas extends javax.swing.JFrame {
private menu menuForm;
    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
    public citas() {
        initComponents();
        medico.setEnabled(false); 
        cargar_especialidad();
        id_personas();
        id_citas();
        this.menuForm = menuForm;
    }
    public void cargar_especialidad(){
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
    void cargar_medico(String txtespecialidad){
        try{
            medico.removeAllItems();
            medico.addItem("SELECCIONE");
            String query = "SELECT CONCAT(p.nombre,' ',p.apellido) AS MEDICO FROM empleados AS e INNER JOIN especialidades AS es ON es.id_especialidades = e.id_especialidades "
                    + "INNER JOIN personas AS p ON p.id_personas = e.id_personas WHERE es.nombre = '"+txtespecialidad+"'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                medico.addItem(rs.getString("Medico"));
            }
        } catch (Exception error){
            JOptionPane.showMessageDialog(null,"Error: " + error);
        }
          
    }
void guardar_datos() {
    try {
        String id_especialidad = "0";
        String id_empleado = "0";

        if (!especialidad.getSelectedItem().equals("SELECCIONE")) {
            String query_especialidad = "SELECT id_especialidades FROM especialidades WHERE nombre = ?";
            PreparedStatement thp = cn.prepareStatement(query_especialidad);
            thp.setString(1, especialidad.getSelectedItem().toString());
            ResultSet rst = thp.executeQuery();

            if (rst.next()) {
                id_especialidad = rst.getString("id_especialidades");

                if (!medico.getSelectedItem().equals("SELECCIONE")) {
                    String[] nombreApellido = medico.getSelectedItem().toString().split(" ");
                    String nombre = nombreApellido[0];
                    String apellido = nombreApellido[1];

                    String query_empleado = "SELECT e.id_empleados FROM empleados e " +
                                            "INNER JOIN personas p ON e.id_personas = p.id_personas " +
                                            "WHERE p.nombre = ? AND p.apellido = ?";
                    PreparedStatement thp3 = cn.prepareStatement(query_empleado);
                    thp3.setString(1, nombre);
                    thp3.setString(2, apellido);
                    ResultSet rst3 = thp3.executeQuery();

                    if (rst3.next()) {
                        id_empleado = rst3.getString("id_empleados");

                        java.util.Date fechaSeleccionada = fecha1.getDate();
                        java.sql.Date fechaSql = new java.sql.Date(fechaSeleccionada.getTime());

                        String horaString = hora.getText();
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

                        java.util.Date horaSeleccionada = null;
                        try {
                            horaSeleccionada = sdf.parse(horaString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        // Combinar fecha y hora
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fechaSeleccionada);
                        calendar.set(Calendar.HOUR_OF_DAY, horaSeleccionada.getHours());
                        calendar.set(Calendar.MINUTE, horaSeleccionada.getMinutes());

                        java.sql.Timestamp fechaHoraSql = new java.sql.Timestamp(calendar.getTimeInMillis());

                        String sql = "INSERT INTO citas (id_personas, id_empleados, id_especialidades, fecha, estado) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement psz = cn.prepareStatement(sql);
                        psz.setString(1, id_persona.getText());
                        psz.setString(2, id_empleado);
                        psz.setString(3, id_especialidad);
                        psz.setTimestamp(4, fechaHoraSql);
                        psz.setInt(5, 1);

                        int n = psz.executeUpdate();

                        if (n > 0) {
                            JOptionPane.showMessageDialog(null, "CITA GUARDADA EXITOSAMENTE");
                        }
                    }
                }
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
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
    void id_citas() {
    try {
       
        String query = "SELECT MAX(id_citas) AS ultimo_id FROM citas";
        Statement statement = cn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            int ultimoId = resultSet.getInt("ultimo_id");
            int siguienteId = ultimoId + 1;
            id_cita.setText(String.valueOf(siguienteId));
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "ERROR\n" + ex.getMessage());
    }
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
    void limpiar() {
    especialidad.setSelectedIndex(0);
    sexo.setSelectedIndex(0);
    medico.setSelectedIndex(0);
    id_persona.setText("");
    nombre.setText("");
    apellido.setText("");
    cedula.setText("");
    hora.setText("");
    telefono.setText("");
    fecha1.setDate(null);
}



  /*  void actualizar_datos(){
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
            PreparedStatement psU = cn.prepareStatement("UPDATE empleado SET id_cargo = '" + rst.getString("id_cargo") + "',nombre='"+nombre.getText()+"',apellido='"+apellido.getText()+"',cedula='"+cedula.getText()+"',telefono='"+telefono.getText()+"',direccion='"+direccion.getText()+"',sexo='"+sexoseleccionado+"' where id_empleado='"+id_empleado.getText()+"'");
            psU.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(empleado.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null,ex);
        }
        JOptionPane.showMessageDialog(null, "ACTUALIZADO CON EXITO");
    }
    void borrar_datos() {
        try {

            PreparedStatement psU = cn.prepareStatement("DELETE from empleado where id_empleado='"+id_empleado.getText()+"'");
            psU.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "ELIMINADO CON EXITO");
    }
     */   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        id_cita = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        especialidad = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        medico = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        hora = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        fecha1 = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        nombre = new javax.swing.JTextField();
        cedula = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        sexo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        id_persona = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 153), null));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS CITA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(12, 93, 172));
        jLabel1.setText("ID CITA");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(12, 93, 172));
        jLabel5.setText("ESPECIALIDAD MEDICA");

        especialidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE" }));
        especialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                especialidadActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(12, 93, 172));
        jLabel6.setText("MEDICO");

        medico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(12, 93, 172));
        jLabel7.setText("FECHA");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(12, 93, 172));
        jLabel8.setText("HORA");

        jButton4.setBackground(new java.awt.Color(12, 93, 172));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("AGENDAR CITA");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(12, 93, 172));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("LIMPIAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        fecha1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fecha1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(hora)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(154, 154, 154)
                        .addComponent(medico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(id_cita, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(71, 71, 71)
                        .addComponent(especialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(id_cita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(especialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(medico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(86, 86, 86))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS PACIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(12, 93, 172));
        jLabel3.setText("TELEFONO");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(12, 93, 172));
        jLabel4.setText("CEDULA");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(12, 93, 172));
        jLabel2.setText("NOMBRE ");

        jButton1.setBackground(new java.awt.Color(12, 93, 172));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("AGREGAR PACIENTE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(12, 93, 172));
        jLabel9.setText("SEXO");

        sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "MASCULINO", "FEMENINO" }));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(12, 93, 172));
        jLabel10.setText("ID PERSONA");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(12, 93, 172));
        jLabel12.setText("APELLIDO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(id_persona, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sexo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton1)
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(id_persona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(12, 93, 172));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("AGENDAR CITAS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(295, 295, 295)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        salir.setBackground(new java.awt.Color(204, 0, 0));
        salir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar-sesion (1).png"))); // NOI18N
        salir.setText("SALIR");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(salir)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salir)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void especialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_especialidadActionPerformed
        String seleccionespecialidad = (String) especialidad.getSelectedItem();
        if(!seleccionespecialidad.equals("SELECCIONE")){
            medico.setEnabled(true);
            cargar_medico(seleccionespecialidad);
            
            
        } else {
            medico.setEnabled(false);  
        }
    }//GEN-LAST:event_especialidadActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        guardar_persona();
        guardar_datos();
        guardar_telefono();
        limpiar();
        id_personas();
        id_citas();
        menu.cargarDATOS.doClick();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        limpiar();
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            
            java.util.logging.Logger.getLogger(citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(citas.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new citas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellido;
    private javax.swing.JTextField cedula;
    private javax.swing.JComboBox<String> especialidad;
    private com.toedter.calendar.JDateChooser fecha1;
    private javax.swing.JTextField hora;
    private javax.swing.JTextField id_cita;
    private javax.swing.JTextField id_persona;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JComboBox<String> medico;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton salir;
    private javax.swing.JComboBox<String> sexo;
    private javax.swing.JTextField telefono;
    // End of variables declaration//GEN-END:variables
}
