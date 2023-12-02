
package Formularios;

import static Formularios.empleado.id_persona;
import static Formularios.registro_pacientes.apellido;
import static Formularios.registro_pacientes.id_persona;
import static Formularios.registro_pacientes.nombre;
import static Formularios.registro_pacientes.observaciones;
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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class registro_personas extends javax.swing.JFrame {
    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
 
    public registro_personas() {
        initComponents();
        id_personas();
    }

    void id_personas(){
        String id_factura="";
        try{
            Statement sq2 = cn.createStatement();
            ResultSet rq2 = sq2.executeQuery("select id_personas from contador_personas");
            rq2.next();
            id_factura = rq2.getString("id_personas");
        }catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "ERROR\n" + ex.getMessage());
    }
        int secuencia = Integer.parseInt(id_factura);
        secuencia = secuencia + 1;
        id_persona.setText(String.valueOf(secuencia));
        
    }
    void limpiar(){
        id_persona.setText("");
        nombre.setText("");
        apellido.setText("");
        cedula.setText("");
        raza.setText("");
        residencia.setText("");
        religion.setText("");
        nacionalidad.setText("");
        escolaridad.setText("");
        ocupacion.setText("");
        info_suministrada.setText("");
        sexo.setSelectedIndex(0);
        estado_civil.setSelectedIndex(0);
        tipo_sangre.setSelectedIndex(0);
    }
    void guardar_datos(){
        try {
            String sexoseleccionado;
            sexoseleccionado = sexo.getSelectedItem().toString();
                if ("MASCULINO".equals(sexoseleccionado)) {
                } else if ("FEMENINO".equals(sexoseleccionado)) {
                        } 
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechanacimientoseleccionada = sdf.format(fecha_nacimiento.getDate());

            String estadocivilseleccionado;
            estadocivilseleccionado = sexo.getSelectedItem().toString();
                if ("SOLTERO".equals(estadocivilseleccionado)) {
                } else if ("CASADO".equals(estadocivilseleccionado)) {
                }  else if ("UNION LIBRE".equals(estadocivilseleccionado)) {
                } 
                
            String sangreseleccionado;
            sangreseleccionado = sexo.getSelectedItem().toString();
                if ("A+".equals(sangreseleccionado)) {
                }  else if ("A-".equals(sangreseleccionado)) {
                }  else if ("B+".equals(sangreseleccionado)) {
                }  else if ("B-".equals(sangreseleccionado)) {
                }  else if ("AB+".equals(sangreseleccionado)) {
                }  else if ("AB-".equals(sangreseleccionado)) {
                }  else if ("O+".equals(sangreseleccionado)) {
                }  else if ("O-".equals(sangreseleccionado)) {
                }
            String sql = "";
            sql = "INSERT INTO personas (id_personas,tipo_sangre,nombre,apellido,genero,cedula,fecha_nacimiento,raza,religion,nacionalidad,escolaridad,ocupacion,estado_civil)VALUES('" + id_persona.getText() + "','" + sangreseleccionado + "','" + nombre.getText() + "','" + apellido.getText() + "','" + sexoseleccionado + "','" + cedula.getText() + "','" + fechanacimientoseleccionada + "','" + raza.getText() + "','" + religion.getText() + "','" + nacionalidad.getText() + "','" + escolaridad.getText() + "','" + ocupacion.getText() + "','" + estadocivilseleccionado + "')";
            PreparedStatement psz = cn.prepareStatement(sql);

            int n;
            n = psz.executeUpdate();
            if (n > 0) {
            }
   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
          try {

            PreparedStatement psU = cn.prepareStatement("UPDATE contador_personas SET id_personas=id_personas+1");
            psU.executeUpdate();

        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
        }
        JOptionPane.showMessageDialog(null, "GUARDADO CON EXITO");  
    }
    private int calcularEdad(Date fechaNacimiento) {
    Calendar fechaNac = Calendar.getInstance();
    fechaNac.setTime(fechaNacimiento);
    Calendar fechaActual = Calendar.getInstance();

    int edad = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
    if (fechaActual.get(Calendar.DAY_OF_YEAR) < fechaNac.get(Calendar.DAY_OF_YEAR)) {
        edad--;
    }

    return edad;
    
}
    void actualizar_datos(){
        try {
            String sexoseleccionado;
                sexoseleccionado = sexo.getSelectedItem().toString();
                if ("MASCULINO".equals(sexoseleccionado)) {
                } else if ("FEMENINO".equals(sexoseleccionado)) {
                } 
                String estadocivilseleccionado;
                   estadocivilseleccionado = sexo.getSelectedItem().toString();
                    if ("SOLTERO".equals(estadocivilseleccionado)) {
                    } else if ("CASADO".equals(estadocivilseleccionado)) {
                    }  else if ("UNION LIBRE".equals(estadocivilseleccionado)) {
                    }
String sangreseleccionado;
sangreseleccionado = sexo.getSelectedItem().toString();
        if ("A+".equals(sangreseleccionado)) {
}  else if ("A-".equals(sangreseleccionado)) {
}  else if ("B+".equals(sangreseleccionado)) {
}  else if ("B-".equals(sangreseleccionado)) {
}  else if ("AB+".equals(sangreseleccionado)) {
}  else if ("AB-".equals(sangreseleccionado)) {
}  else if ("O+".equals(sangreseleccionado)) {
}  else if ("O-".equals(sangreseleccionado)) {
}
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String fechanacimientoseleccionada = sdf.format(fecha_nacimiento.getDate());

            PreparedStatement psU = cn.prepareStatement("UPDATE personas SET tipo_sangre= '" + sangreseleccionado+ "',nombre='"+nombre.getText()+"',apellido='"+apellido.getText()+"',cedula='"+cedula.getText()+"',raza='"+raza.getText()+"',residencia='"+residencia.getText()+"',fecha_nacimiento='"+fechanacimientoseleccionada+"',ocupacion='"+ocupacion.getText()+"',religion='"+religion.getText()+"',nacionalidad='"+nacionalidad.getText()+"',escolaridad='"+escolaridad.getText()+"',genero='"+sexoseleccionado+"',estado_civil='"+estadocivilseleccionado+"' where id_personas='"+id_persona.getText()+"'");
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        id_persona = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        apellido = new javax.swing.JTextField();
        sexo = new javax.swing.JComboBox<>();
        residencia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        estado_civil = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        raza = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        religion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        nacionalidad = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        escolaridad = new javax.swing.JTextField();
        ocupacion = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tipo_sangre = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        info_suministrada = new javax.swing.JTextField();
        cedula = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        fecha_nacimiento = new com.toedter.calendar.JDateChooser();
        info_suministrada1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        AGREGAR = new javax.swing.JButton();
        ACTUALIZAR = new javax.swing.JButton();
        ELIMINAR = new javax.swing.JButton();
        LIMPIAR = new javax.swing.JButton();
        SALIR = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 93, 172), null));

        jPanel2.setBackground(new java.awt.Color(12, 93, 172));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REGISTRO DE PERSONAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS GENERALES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(12, 93, 172))); // NOI18N

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "MASCULINO", "FEMENINO" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(12, 93, 172));
        jLabel7.setText("RESIDENCIA");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(12, 93, 172));
        jLabel6.setText("SEXO");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(12, 93, 172));
        jLabel5.setText("FECHA NACIMIENTO");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(12, 93, 172));
        jLabel4.setText("APELLIDO");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(12, 93, 172));
        jLabel3.setText("NOMBRE");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(12, 93, 172));
        jLabel2.setText("ID PERSONA");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(12, 93, 172));
        jLabel8.setText("TIPO DE SANGRE");

        estado_civil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "SOLTERO", "CASADO", "UNION LIBRE" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(12, 93, 172));
        jLabel9.setText("RAZA");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(12, 93, 172));
        jLabel10.setText("RELIGION");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(12, 93, 172));
        jLabel11.setText("ESTADO CIVIL");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(12, 93, 172));
        jLabel12.setText("NACIONALIDAD");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(12, 93, 172));
        jLabel13.setText("ESCOLARIDAD");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(12, 93, 172));
        jLabel14.setText("OCUPACION");

        tipo_sangre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(12, 93, 172));
        jLabel15.setText("INF SUMINISTRADA");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(12, 93, 172));
        jLabel16.setText("CEDULA");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(12, 93, 172));
        jLabel17.setText("TELEFONO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cedula, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(nombre)
                    .addComponent(apellido)
                    .addComponent(sexo, 0, 212, Short.MAX_VALUE)
                    .addComponent(residencia)
                    .addComponent(id_persona, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tipo_sangre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fecha_nacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(escolaridad)
                            .addComponent(ocupacion)
                            .addComponent(nacionalidad)
                            .addComponent(estado_civil, 0, 236, Short.MAX_VALUE)
                            .addComponent(religion)
                            .addComponent(raza)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(info_suministrada1)
                            .addComponent(info_suministrada))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(id_persona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(fecha_nacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(raza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(religion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(estado_civil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(nacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(residencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(escolaridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(ocupacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8)
                        .addComponent(tipo_sangre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(info_suministrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(info_suministrada1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        AGREGAR.setBackground(new java.awt.Color(12, 93, 172));
        AGREGAR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AGREGAR.setForeground(new java.awt.Color(255, 255, 255));
        AGREGAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/boton-agregar.png"))); // NOI18N
        AGREGAR.setText("AGREGAR");
        AGREGAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AGREGARActionPerformed(evt);
            }
        });

        ACTUALIZAR.setBackground(new java.awt.Color(12, 93, 172));
        ACTUALIZAR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ACTUALIZAR.setForeground(new java.awt.Color(255, 255, 255));
        ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/sincronizar.png"))); // NOI18N
        ACTUALIZAR.setText("ACTUALIZAR");
        ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACTUALIZARActionPerformed(evt);
            }
        });

        ELIMINAR.setBackground(new java.awt.Color(12, 93, 172));
        ELIMINAR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ELIMINAR.setForeground(new java.awt.Color(255, 255, 255));
        ELIMINAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/papelera-de-reciclaje.png"))); // NOI18N
        ELIMINAR.setText("ELIMINAR");
        ELIMINAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ELIMINARActionPerformed(evt);
            }
        });

        LIMPIAR.setBackground(new java.awt.Color(12, 93, 172));
        LIMPIAR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LIMPIAR.setForeground(new java.awt.Color(255, 255, 255));
        LIMPIAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/limpiar.png"))); // NOI18N
        LIMPIAR.setText("LIMPIAR");
        LIMPIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPIARActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(AGREGAR, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ACTUALIZAR, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ELIMINAR, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LIMPIAR, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AGREGAR)
                    .addComponent(ACTUALIZAR)
                    .addComponent(ELIMINAR)
                    .addComponent(LIMPIAR)
                    .addComponent(SALIR))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void AGREGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AGREGARActionPerformed
        if (nombre.getText().equals("")) {
           // JOptionPane.showMessageDialog(null, "LA DESCRIPCION NO PUEDE ESTAR EN BLANCO");
           
           nombre.requestFocus(true);
            return;
        }
        if (apellido.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL APELLIDO NO PUEDE ESTAR EN BLANCO");
            apellido.requestFocus(true);
            return;
        }
        if (nacionalidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LA NACIONALIDAD NO PUEDE ESTAR EN BLANCO");
            nacionalidad.requestFocus(true);
            return;
        }
        if (fecha_nacimiento.getDate().equals("")) {
            JOptionPane.showMessageDialog(null, "LA FECHA DE NACIMIENTO NO PUEDE ESTAR EN BLANCO");
            return;
        }
            if (info_suministrada.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LA INFORMACION SUMINISTRADA NO PUEDE ESTAR EN BLANCO");
            info_suministrada.requestFocus(true);
            return;
        }
        
        guardar_datos();
        limpiar();
        nombre.requestFocus();
    }//GEN-LAST:event_AGREGARActionPerformed

    private void ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACTUALIZARActionPerformed
        actualizar_datos();
        limpiar();

    }//GEN-LAST:event_ACTUALIZARActionPerformed

    private void ELIMINARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELIMINARActionPerformed
        borrar_datos();
    }//GEN-LAST:event_ELIMINARActionPerformed

    private void LIMPIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPIARActionPerformed
        limpiar();
        nombre.requestFocus();
    }//GEN-LAST:event_LIMPIARActionPerformed

    private void SALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALIRActionPerformed
        this.dispose();
    }//GEN-LAST:event_SALIRActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

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
            java.util.logging.Logger.getLogger(registro_personas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registro_personas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registro_personas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registro_personas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registro_personas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ACTUALIZAR;
    private javax.swing.JButton AGREGAR;
    private javax.swing.JButton ELIMINAR;
    private javax.swing.JButton LIMPIAR;
    private javax.swing.JButton SALIR;
    private javax.swing.JTextField apellido;
    private javax.swing.JTextField cedula;
    private javax.swing.JTextField escolaridad;
    private javax.swing.JComboBox<String> estado_civil;
    private com.toedter.calendar.JDateChooser fecha_nacimiento;
    private javax.swing.JTextField id_persona;
    private javax.swing.JTextField info_suministrada;
    private javax.swing.JTextField info_suministrada1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JTextField nacionalidad;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField ocupacion;
    private javax.swing.JTextField raza;
    private javax.swing.JTextField religion;
    private javax.swing.JTextField residencia;
    private javax.swing.JComboBox<String> sexo;
    private javax.swing.JComboBox<String> tipo_sangre;
    // End of variables declaration//GEN-END:variables
}
