
package Formularios;

import static Formularios.empleado.id_persona;
import clases.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class registro_pacientes extends javax.swing.JFrame {
    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
 
    public registro_pacientes() {
        initComponents();
    }
    void limpiar(){
        id_persona.setText("");
        nombre.setText("");
        apellido.setText("");
        observaciones.setText("");
        info_suministrada.setText("");

    }
   void guardar_datos(){
        try {

            String sql = "";
            sql = "INSERT INTO pacientes (id_personas, observaciones, informacion_suministrada )VALUES('" + id_persona.getText() + "','" + observaciones.getText() + "','" + info_suministrada.getText() + "')";
            PreparedStatement psz = cn.prepareStatement(sql);

            int n;
            n = psz.executeUpdate();
            if (n > 0) {

            }
            
            String query = "SELECT (SELECT id_pacientes FROM pacientes ORDER BY id_pacientes DESC LIMIT 1) AS id_pacientes, (SELECT fecha_nacimiento FROM personas WHERE id_personas = '"+id_persona.getText()+"') as fecha_nacimiento, (SELECT genero FROM personas WHERE id_personas = '"+id_persona.getText()+"') as genero";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            internos.id_paciente.setText(rs.getString("id_pacientes"));
            internos.nombre.setText(nombre.getText() + " " + apellido.getText());
            internos.sexo.setSelectedItem(rs.getString("genero"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = sdf.parse(rs.getString("fecha_nacimiento"));
            internos.edad.setText(String.valueOf(calcularEdad(fechaNacimiento)));
            internos.observaciones.setText(observaciones.getText());
            this.dispose();
            JOptionPane.showMessageDialog(null, "GUARDADO CON EXITO"); 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
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
            PreparedStatement psU = cn.prepareStatement("UPDATE pacientes SET id_personas = '"+id_persona.getText()+"',observaciones='"+observaciones.getText()+"',informacion_suministrada='"+info_suministrada.getText()+"' where id_pacientes='"+id_paciente.getText()+"'");
            psU.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(empleado.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null,ex);
        }
        JOptionPane.showMessageDialog(null, "ACTUALIZADO CON EXITO");
    }
    void borrar_datos() {
         String query = "UPDATE pacientes SET estado = 0 WHERE id_pacientes = '" + id_paciente.getText() + "'";
            try{
                PreparedStatement psz = cn.prepareStatement(query);
                psz.executeUpdate();
                JOptionPane.showMessageDialog(null, "Paciente Eliminado");
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
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        observaciones = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        info_suministrada = new javax.swing.JTextField();
        LIMPIAR1 = new javax.swing.JButton();
        id_paciente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        AGREGAR = new javax.swing.JButton();
        ACTUALIZAR = new javax.swing.JButton();
        ELIMINAR = new javax.swing.JButton();
        LIMPIAR = new javax.swing.JButton();
        SALIR = new javax.swing.JButton();
        SEGURO = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 93, 172), null));

        jPanel2.setBackground(new java.awt.Color(12, 93, 172));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REGISTRAR PACIENTE");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(97, 97, 97)
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

        id_persona.setEditable(false);

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(12, 93, 172));
        jLabel4.setText("APELLIDO");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(12, 93, 172));
        jLabel3.setText("NOMBRE");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(12, 93, 172));
        jLabel2.setText("ID PERSONA");

        observaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                observacionesActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(12, 93, 172));
        jLabel14.setText("OBSERVACIONES");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(12, 93, 172));
        jLabel15.setText("INF SUMINISTRADA");

        info_suministrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                info_suministradaActionPerformed(evt);
            }
        });

        LIMPIAR1.setBackground(new java.awt.Color(12, 93, 172));
        LIMPIAR1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LIMPIAR1.setForeground(new java.awt.Color(255, 255, 255));
        LIMPIAR1.setText("...");
        LIMPIAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPIAR1ActionPerformed(evt);
            }
        });

        id_paciente.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(12, 93, 172));
        jLabel5.setText("ID PACIENTE");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel15)
                    .addComponent(jLabel5))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(id_paciente)
                    .addComponent(info_suministrada)
                    .addComponent(apellido)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(id_persona, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LIMPIAR1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nombre)
                    .addComponent(observaciones))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(id_paciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(id_persona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LIMPIAR1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(observaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(info_suministrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
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

        SEGURO.setBackground(new java.awt.Color(12, 93, 172));
        SEGURO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SEGURO.setForeground(new java.awt.Color(255, 255, 255));
        SEGURO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/proteger (1).png"))); // NOI18N
        SEGURO.setText("SEGURO");
        SEGURO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEGUROActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ACTUALIZAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AGREGAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ELIMINAR, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(LIMPIAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SEGURO, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AGREGAR)
                    .addComponent(ELIMINAR)
                    .addComponent(SEGURO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ACTUALIZAR)
                    .addComponent(LIMPIAR)
                    .addComponent(SALIR))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        new menu_pacientes().setVisible(true);
    }//GEN-LAST:event_SALIRActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void LIMPIAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPIAR1ActionPerformed
        try {
            new lista_personas1().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(registro_pacientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_LIMPIAR1ActionPerformed

    private void apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidoActionPerformed

    private void info_suministradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_info_suministradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_info_suministradaActionPerformed

    private void observacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_observacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_observacionesActionPerformed

    private void SEGUROActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEGUROActionPerformed
       new registro_seguros().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_SEGUROActionPerformed

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
            java.util.logging.Logger.getLogger(registro_pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registro_pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registro_pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registro_pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registro_pacientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ACTUALIZAR;
    private javax.swing.JButton AGREGAR;
    private javax.swing.JButton ELIMINAR;
    private javax.swing.JButton LIMPIAR;
    private javax.swing.JButton LIMPIAR1;
    private javax.swing.JButton SALIR;
    private javax.swing.JButton SEGURO;
    public static javax.swing.JTextField apellido;
    public static javax.swing.JTextField id_paciente;
    public static javax.swing.JTextField id_persona;
    private javax.swing.JTextField info_suministrada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JTextField nombre;
    public static javax.swing.JTextField observaciones;
    // End of variables declaration//GEN-END:variables
}
