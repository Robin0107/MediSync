
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
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;


public class dealtas extends javax.swing.JFrame {
    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
    
    public dealtas() throws SQLException {
        initComponents();
        cargar_datos();
   
    }
void cargar_datos() {
    DefaultTableModel modelo2 = (DefaultTableModel) tabla_internos.getModel();
    modelo2.setRowCount(0);

    String sql = "SELECT internos.id_internos, salas.id_salas, salas.codigo AS sala_codigo, empleados.id_empleados, personas.nombre AS empleado_nombre, "
            + "personas.apellido AS empleado_apellido, pacientes.id_pacientes, personas_pacientes.nombre AS paciente_nombre, "
            + "personas_pacientes.apellido AS paciente_apellido, internos.motivo_interno, internos.observaciones, internos.fecha_entrada "
            + "FROM internos "
            + "INNER JOIN salas ON internos.id_salas = salas.id_salas "
            + "INNER JOIN empleados ON internos.id_empleados = empleados.id_empleados "
            + "INNER JOIN personas ON empleados.id_personas = personas.id_personas "
            + "INNER JOIN pacientes ON internos.id_pacientes = pacientes.id_pacientes "
            + "INNER JOIN personas AS personas_pacientes ON pacientes.id_personas = personas_pacientes.id_personas "
            + "WHERE internos.estado = 2 LIMIT 100";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String[] registros = new String[10]; // Considerando 10 campos de información

            registros[0] = rs.getString("id_internos");
            registros[1] = rs.getString("id_pacientes");
            registros[2] = rs.getString("paciente_nombre") + " " + rs.getString("paciente_apellido");
            registros[3] = rs.getString("id_salas");
            registros[4] = rs.getString("sala_codigo");
            registros[5] = rs.getString("id_empleados");
            registros[6] = rs.getString("empleado_nombre") + " " + rs.getString("empleado_apellido");
            registros[7] = rs.getString("motivo_interno");
            registros[8] = rs.getString("observaciones");
            registros[9] = rs.getString("fecha_entrada");

            modelo2.addRow(registros);
        }
        tabla_internos.setModel(modelo2);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}


 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_internos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        SALIR = new javax.swing.JButton();
        btn_imprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 93, 172), null));

        tabla_internos.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_internos.setSelectionBackground(new java.awt.Color(102, 255, 102));
        tabla_internos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_internosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_internos);
        if (tabla_internos.getColumnModel().getColumnCount() > 0) {
            tabla_internos.getColumnModel().getColumn(0).setMinWidth(80);
            tabla_internos.getColumnModel().getColumn(0).setMaxWidth(80);
            tabla_internos.getColumnModel().getColumn(1).setMinWidth(80);
            tabla_internos.getColumnModel().getColumn(1).setMaxWidth(80);
            tabla_internos.getColumnModel().getColumn(3).setMinWidth(80);
            tabla_internos.getColumnModel().getColumn(3).setMaxWidth(80);
        }

        jPanel2.setBackground(new java.awt.Color(12, 93, 172));
        jPanel2.setForeground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LISTA DE PACIENTES DE ALTA");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(749, 749, 749)
                .addComponent(btn_imprimir)
                .addGap(18, 18, 18)
                .addComponent(SALIR)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_imprimir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void tabla_internosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_internosMouseClicked
                   int fila= tabla_internos.getSelectedRow();
     if(fila>=0){
       internos.ID_INTERNOS.setText(tabla_internos.getValueAt(fila, 0).toString());  
       internos.id_paciente.setText(tabla_internos.getValueAt(fila, 1).toString());
       internos.nombre.setText(tabla_internos.getValueAt(fila, 2).toString());      
       internos.IDSALAS.setText(tabla_internos.getValueAt(fila, 3).toString());  
       internos.sala.setText(tabla_internos.getValueAt(fila, 4).toString());  
       internos.txtid_medico.setText(tabla_internos.getValueAt(fila, 5).toString());  
       internos.txtnombre_medico.setText(tabla_internos.getValueAt(fila, 6).toString());  
       internos.diagnostico.setText(tabla_internos.getValueAt(fila, 7).toString());  
       internos.observaciones.setText(tabla_internos.getValueAt(fila, 8).toString());  
       internos.fecha_ingreso.setText(tabla_internos.getValueAt(fila, 9).toString());
       internos.id_medicamento.requestFocus();
       internos.cargartabla.doClick();
       internos.btn_agregar_interno.setEnabled(false);
       this.dispose();

     }   
    }//GEN-LAST:event_tabla_internosMouseClicked

    private void SALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALIRActionPerformed
        this.dispose();
        new menu_pacientes().setVisible(true);
    }//GEN-LAST:event_SALIRActionPerformed

    private void btn_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimirActionPerformed
       new Reportes.Empleados().generate_report();
    }//GEN-LAST:event_btn_imprimirActionPerformed

 
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
                    new dealtas().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(empleado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SALIR;
    private javax.swing.JButton btn_imprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_internos;
    // End of variables declaration//GEN-END:variables
}
