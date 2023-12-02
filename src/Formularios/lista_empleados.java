
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
import javax.swing.table.DefaultTableModel;


public class lista_empleados extends javax.swing.JFrame {
    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
    
    public lista_empleados() throws SQLException {
        initComponents();
        cargar_datos();
        
    }
  void cargar_datos() {
    DefaultTableModel modelo2 = (DefaultTableModel) tabla_empleado.getModel();
    modelo2.setRowCount(0); 

    String sql = "SELECT p.nombre, p.apellido, p.genero, p.cedula, e.id_empleados, e.sueldo, c.nombre AS cargo, es.nombre AS especialidad " +
                 "FROM empleados e " +
                 "INNER JOIN personas p ON e.id_personas = p.id_personas " +
                 "INNER JOIN cargos_empleados c ON e.id_cargos_empleados = c.id_cargos_empleados " +
                 "LEFT JOIN especialidades es ON e.id_especialidades = es.id_especialidades "
            +    "WHERE (e.id_empleados LIKE '%" + buscar.getText() + "%' OR p.nombre LIKE '%" + buscar.getText() + "%' OR p.cedula LIKE '%" + buscar.getText() + "%' OR p.apellido LIKE '%" + buscar.getText() + "%'OR c.nombre LIKE '%" + buscar.getText() + "%') AND e.estado = 1 LIMIT 100";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String[] registros = new String[8];

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
        tabla_empleado.setModel(modelo2);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_empleado = new javax.swing.JTable();
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

        tabla_empleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO", "SEXO", "CEDULA", "TELEFONO", "CARGO", "ESPECIALIDAD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_empleado.setSelectionBackground(new java.awt.Color(102, 255, 102));
        tabla_empleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_empleadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_empleado);
        if (tabla_empleado.getColumnModel().getColumnCount() > 0) {
            tabla_empleado.getColumnModel().getColumn(0).setMinWidth(80);
            tabla_empleado.getColumnModel().getColumn(0).setMaxWidth(80);
            tabla_empleado.getColumnModel().getColumn(1).setMinWidth(150);
            tabla_empleado.getColumnModel().getColumn(1).setMaxWidth(150);
            tabla_empleado.getColumnModel().getColumn(2).setMinWidth(150);
            tabla_empleado.getColumnModel().getColumn(2).setMaxWidth(150);
            tabla_empleado.getColumnModel().getColumn(3).setMinWidth(100);
            tabla_empleado.getColumnModel().getColumn(3).setMaxWidth(100);
            tabla_empleado.getColumnModel().getColumn(4).setMinWidth(100);
            tabla_empleado.getColumnModel().getColumn(4).setMaxWidth(100);
            tabla_empleado.getColumnModel().getColumn(5).setMinWidth(100);
            tabla_empleado.getColumnModel().getColumn(5).setMaxWidth(100);
            tabla_empleado.getColumnModel().getColumn(6).setMinWidth(200);
            tabla_empleado.getColumnModel().getColumn(6).setMaxWidth(200);
            tabla_empleado.getColumnModel().getColumn(7).setMinWidth(100);
            tabla_empleado.getColumnModel().getColumn(7).setMaxWidth(100);
        }

        jPanel2.setBackground(new java.awt.Color(12, 93, 172));
        jPanel2.setForeground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LISTA DE EMPLEADOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(406, 406, 406)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jLabel3.setText("BUSCAR EMPLEADOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_imprimir)
                        .addGap(18, 18, 18)
                        .addComponent(SALIR))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 984, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 14, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_imprimir))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabla_empleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_empleadoMouseClicked
                   int fila= tabla_empleado.getSelectedRow();
     if(fila>=0){
       registro_usuarios.id_empleado.setText(tabla_empleado.getValueAt(fila, 0).toString());  
       registro_usuarios.usuario.requestFocus();
       this.dispose();

     }  
    }//GEN-LAST:event_tabla_empleadoMouseClicked

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
                    new lista_empleados().setVisible(true);
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
    private javax.swing.JTable tabla_empleado;
    // End of variables declaration//GEN-END:variables
}
