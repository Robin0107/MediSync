
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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class lista_pacientes_seguros extends javax.swing.JFrame {
    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
    
    public lista_pacientes_seguros() throws SQLException {
        initComponents();
        cargar_datos();
        
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
    void cargar_datos() {
    DefaultTableModel modelo2 = (DefaultTableModel) tabla_pacientes.getModel();
    modelo2.setRowCount(0);

    String sql = "SELECT pa.id_pacientes, pa.observaciones, p.id_personas, p.nombre, p.apellido, p.genero, p.fecha_nacimiento, p.cedula, p.tipo_sangre " +
                 "FROM pacientes AS pa " +
                 "INNER JOIN personas AS p ON p.id_personas = pa.id_personas " +
                 "WHERE (pa.id_pacientes LIKE '%" + buscar.getText() + "%' OR p.nombre LIKE '%" + buscar.getText() + "%' OR p.apellido LIKE '%" + buscar.getText() + "%' OR p.cedula LIKE '%" + buscar.getText() + "%') AND pa.estado = 1 LIMIT 100";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String[] registros = new String[10];

            registros[0] = rs.getString("id_pacientes");
            registros[1] = rs.getString("id_personas");
            registros[2] = rs.getString("nombre");
            registros[3] = rs.getString("apellido");
            registros[4] = rs.getString("genero");
            registros[8] = rs.getString("fecha_nacimiento");
            registros[5] = rs.getString("cedula");
            registros[7] = rs.getString("tipo_sangre");
            registros[9] = rs.getString("observaciones");
            modelo2.addRow(registros);
        }
        tabla_pacientes.setModel(modelo2);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}



 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        SALIR = new javax.swing.JButton();
        buscar = new javax.swing.JTextField();
        AGREGAR = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_pacientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 93, 172), null));

        jPanel2.setBackground(new java.awt.Color(12, 93, 172));
        jPanel2.setForeground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LISTA DE PACIENTES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(381, 381, 381))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        SALIR.setBackground(new java.awt.Color(204, 0, 0));
        SALIR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SALIR.setForeground(new java.awt.Color(255, 255, 255));
        SALIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar-sesion (1).png"))); // NOI18N
        SALIR.setText("SALIR");
        SALIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALIRActionPerformed(evt);
            }
        });

        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
        });

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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(12, 93, 172));
        jLabel3.setText("BUSCAR PACIENTE");

        tabla_pacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID PERSONAS", "NOMBRE", "APELLIDO", "SEXO", "CEDULA", "TELEFONO", "TIPO DE SANGRE", "FECHA NACIMIENTO", "OBSERVACIONES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_pacientes.setSelectionBackground(new java.awt.Color(102, 255, 102));
        tabla_pacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_pacientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_pacientes);
        if (tabla_pacientes.getColumnModel().getColumnCount() > 0) {
            tabla_pacientes.getColumnModel().getColumn(0).setMinWidth(80);
            tabla_pacientes.getColumnModel().getColumn(0).setMaxWidth(80);
            tabla_pacientes.getColumnModel().getColumn(1).setMinWidth(150);
            tabla_pacientes.getColumnModel().getColumn(1).setMaxWidth(150);
            tabla_pacientes.getColumnModel().getColumn(2).setMinWidth(150);
            tabla_pacientes.getColumnModel().getColumn(2).setMaxWidth(150);
            tabla_pacientes.getColumnModel().getColumn(3).setMinWidth(100);
            tabla_pacientes.getColumnModel().getColumn(3).setMaxWidth(100);
            tabla_pacientes.getColumnModel().getColumn(4).setMinWidth(100);
            tabla_pacientes.getColumnModel().getColumn(4).setMaxWidth(100);
            tabla_pacientes.getColumnModel().getColumn(5).setMinWidth(100);
            tabla_pacientes.getColumnModel().getColumn(5).setMaxWidth(100);
            tabla_pacientes.getColumnModel().getColumn(6).setMinWidth(200);
            tabla_pacientes.getColumnModel().getColumn(6).setMaxWidth(200);
            tabla_pacientes.getColumnModel().getColumn(7).setMinWidth(150);
            tabla_pacientes.getColumnModel().getColumn(7).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AGREGAR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SALIR))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SALIR)
                    .addComponent(AGREGAR))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabla_pacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_pacientesMouseClicked
  int fila = tabla_pacientes.getSelectedRow();
if (fila >= 0) {
    String nombreCompleto = tabla_pacientes.getValueAt(fila, 2).toString() + " " + tabla_pacientes.getValueAt(fila, 3).toString();
    String fechaNacimientoStr = tabla_pacientes.getValueAt(fila, 8).toString();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date fechaNacimiento;
    try {
        fechaNacimiento = sdf.parse(fechaNacimientoStr);
        int edad = calcularEdad(fechaNacimiento);
        registro_seguros.id_paciente.setText(tabla_pacientes.getValueAt(fila, 0).toString());
        registro_seguros.nombre.setText(tabla_pacientes.getValueAt(fila, 2).toString());
        registro_seguros.apellido.setText(tabla_pacientes.getValueAt(fila, 3).toString());
        String sexo = tabla_pacientes.getValueAt(fila, 4).toString();
        registro_seguros.sexo.setSelectedItem(sexo);
        registro_seguros.cedula.setText(tabla_pacientes.getValueAt(fila, 5).toString());
        registro_seguros.numero_afiliado.requestFocus();
        
        this.dispose();

    } catch (ParseException ex) {
        JOptionPane.showMessageDialog(null, "Error al parsear la fecha de nacimiento: " + ex.getMessage());
    }
}
    }//GEN-LAST:event_tabla_pacientesMouseClicked

    private void SALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALIRActionPerformed
        this.dispose();
    }//GEN-LAST:event_SALIRActionPerformed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        cargar_datos();
    }//GEN-LAST:event_buscarKeyReleased

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        
    }//GEN-LAST:event_buscarActionPerformed

    private void AGREGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AGREGARActionPerformed
        new registro_pacientes().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AGREGARActionPerformed

 
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
                    new lista_pacientes_seguros().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(empleado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AGREGAR;
    private javax.swing.JButton SALIR;
    private javax.swing.JTextField buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_pacientes;
    // End of variables declaration//GEN-END:variables
}
