
package Formularios;

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
import Reportes.Empleados;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;


public class lista_emergencias extends javax.swing.JFrame {
    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
    
    public lista_emergencias() throws SQLException {
        initComponents();
        cargar_datos();
   
    }
void cargar_datos() {
    DefaultTableModel modelo2 = (DefaultTableModel) tabla_emergencias.getModel();
    modelo2.setRowCount(0);

    String sql = "SELECT emergencias.id_emergencias, salas.id_salas, salas.codigo AS sala_codigo, empleados.id_empleados, personas.nombre AS empleado_nombre, "
            + "personas.apellido AS empleado_apellido, pacientes.id_pacientes, personas_pacientes.nombre AS paciente_nombre, "
            + "personas_pacientes.apellido AS paciente_apellido, emergencias.motivo_emergencia, emergencias.observaciones, emergencias.fecha "
            + "FROM emergencias "
            + "INNER JOIN salas ON emergencias.id_salas = salas.id_salas "
            + "INNER JOIN empleados ON emergencias.id_empleados = empleados.id_empleados "
            + "INNER JOIN personas ON empleados.id_personas = personas.id_personas "
            + "INNER JOIN pacientes ON emergencias.id_pacientes = pacientes.id_pacientes "
            + "INNER JOIN personas AS personas_pacientes ON pacientes.id_personas = personas_pacientes.id_personas "
            + "WHERE (emergencias.id_emergencias LIKE '%" + buscar.getText() + "%' OR personas.nombre LIKE '%" + buscar.getText() + "%' OR pacientes.id_pacientes LIKE '%" + buscar.getText() + "%' OR personas.apellido LIKE '%" + buscar.getText() + "%'OR emergencias.motivo_emergencia LIKE '%" + buscar.getText() + "%') AND emergencias.estado = 1 LIMIT 100";


    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String[] registros = new String[10]; // Considerando 10 campos de información

            registros[0] = rs.getString("id_emergencias");
            registros[1] = rs.getString("id_pacientes");
            registros[2] = rs.getString("paciente_nombre") + " " + rs.getString("paciente_apellido");
            registros[3] = rs.getString("id_salas");
            registros[4] = rs.getString("sala_codigo");
            registros[5] = rs.getString("id_empleados");
            registros[6] = rs.getString("empleado_nombre") + " " + rs.getString("empleado_apellido");
            registros[7] = rs.getString("motivo_emergencia");
            registros[8] = rs.getString("observaciones");
            registros[9] = rs.getString("fecha");

            modelo2.addRow(registros);
        }
        tabla_emergencias.setModel(modelo2);
    } catch (SQLException ex) {
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

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_emergencias = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        SALIR = new javax.swing.JButton();
        btn_imprimir = new javax.swing.JButton();
        buscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 93, 172), null));

        tabla_emergencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID PACIENTE", "NOMBRE ", "ID SALA", "SALA", "ID MEDICO", "MEDICO", "DIAGNOSTICO", "OBSERVACIONES", "FECHA INGRESO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_emergencias.setSelectionBackground(new java.awt.Color(102, 255, 102));
        tabla_emergencias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_emergenciasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_emergencias);
        if (tabla_emergencias.getColumnModel().getColumnCount() > 0) {
            tabla_emergencias.getColumnModel().getColumn(0).setMinWidth(80);
            tabla_emergencias.getColumnModel().getColumn(0).setMaxWidth(80);
            tabla_emergencias.getColumnModel().getColumn(1).setMinWidth(80);
            tabla_emergencias.getColumnModel().getColumn(1).setMaxWidth(80);
            tabla_emergencias.getColumnModel().getColumn(3).setMinWidth(80);
            tabla_emergencias.getColumnModel().getColumn(3).setMaxWidth(80);
        }

        jPanel2.setBackground(new java.awt.Color(12, 93, 172));
        jPanel2.setForeground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LISTA DE EMERGENCIAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(507, 507, 507))
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

        btn_imprimir.setBackground(new java.awt.Color(12, 93, 172));
        btn_imprimir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_imprimir.setForeground(new java.awt.Color(255, 255, 255));
        btn_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/impresora.png"))); // NOI18N
        btn_imprimir.setText("IMPRIMIR ");
        btn_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimirActionPerformed(evt);
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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(12, 93, 172));
        jLabel3.setText("BUSCAR EMERGENCIA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_imprimir)
                .addGap(24, 24, 24)
                .addComponent(SALIR)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_imprimir)
                    .addComponent(SALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabla_emergenciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_emergenciasMouseClicked
             int fila= tabla_emergencias.getSelectedRow();
     if(fila>=0){
       emergencias.id_emergencias.setText(tabla_emergencias.getValueAt(fila, 0).toString());  
       emergencias.id_paciente.setText(tabla_emergencias.getValueAt(fila, 1).toString());
       emergencias.nombre.setText(tabla_emergencias.getValueAt(fila, 2).toString());      
       emergencias.IDSALAS.setText(tabla_emergencias.getValueAt(fila, 3).toString());  
       emergencias.sala.setText(tabla_emergencias.getValueAt(fila, 4).toString());  
       emergencias.txtid_medico.setText(tabla_emergencias.getValueAt(fila, 5).toString());  
       emergencias.txtnombre_medico.setText(tabla_emergencias.getValueAt(fila, 6).toString());  
       emergencias.motivo_consulta.setText(tabla_emergencias.getValueAt(fila, 7).toString());  
       emergencias.observaciones.setText(tabla_emergencias.getValueAt(fila, 8).toString());  
       emergencias.id_medicamento.requestFocus();
       emergencias.cargartabla.doClick();
       emergencias.guardar_emergencias.setEnabled(false);
       this.dispose();

     }  
 
    }//GEN-LAST:event_tabla_emergenciasMouseClicked

    private void SALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALIRActionPerformed
        this.dispose();
      
    }//GEN-LAST:event_SALIRActionPerformed

    private void btn_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimirActionPerformed
       new Reportes.Empleados().generate_report();
    }//GEN-LAST:event_btn_imprimirActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed

    }//GEN-LAST:event_buscarActionPerformed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        cargar_datos();
    }//GEN-LAST:event_buscarKeyReleased

 
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
                    new lista_emergencias().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(empleado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SALIR;
    private javax.swing.JButton btn_imprimir;
    private javax.swing.JTextField buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_emergencias;
    // End of variables declaration//GEN-END:variables
}
