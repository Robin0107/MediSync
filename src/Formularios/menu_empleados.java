
package Formularios;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class menu_empleados extends javax.swing.JFrame {

  
    public menu_empleados() {
        initComponents();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        salir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        salir1 = new javax.swing.JButton();
        btn_listaempleados = new javax.swing.JButton();
        btn_registroempleados = new javax.swing.JButton();
        btn_especialidad = new javax.swing.JButton();
        btn_cargos = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 93, 172), null));

        jPanel4.setBackground(new java.awt.Color(12, 93, 172));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ADMINISTRAR DE EMPLEADOS");

        salir1.setBackground(new java.awt.Color(204, 0, 0));
        salir1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        salir1.setForeground(new java.awt.Color(255, 255, 255));
        salir1.setText("X");
        salir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(125, 125, 125)
                .addComponent(salir1)
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(salir1))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        btn_listaempleados.setBackground(new java.awt.Color(12, 93, 172));
        btn_listaempleados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_listaempleados.setForeground(new java.awt.Color(255, 255, 255));
        btn_listaempleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/historial-medico.png"))); // NOI18N
        btn_listaempleados.setText("LISTADO DE EMPLEADOS");
        btn_listaempleados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_listaempleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listaempleadosActionPerformed(evt);
            }
        });

        btn_registroempleados.setBackground(new java.awt.Color(12, 93, 172));
        btn_registroempleados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_registroempleados.setForeground(new java.awt.Color(255, 255, 255));
        btn_registroempleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuario_1.png"))); // NOI18N
        btn_registroempleados.setText("REGISTRO DE EMPLEADOS");
        btn_registroempleados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_registroempleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registroempleadosActionPerformed(evt);
            }
        });

        btn_especialidad.setBackground(new java.awt.Color(12, 93, 172));
        btn_especialidad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_especialidad.setForeground(new java.awt.Color(255, 255, 255));
        btn_especialidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/medico.png"))); // NOI18N
        btn_especialidad.setText("ESPECIALIDAD MEDICA");
        btn_especialidad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_especialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_especialidadActionPerformed(evt);
            }
        });

        btn_cargos.setBackground(new java.awt.Color(12, 93, 172));
        btn_cargos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_cargos.setForeground(new java.awt.Color(255, 255, 255));
        btn_cargos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ganar-dinero_1.png"))); // NOI18N
        btn_cargos.setText("CARGOS DE EMPLEADOS");
        btn_cargos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_cargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cargosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_listaempleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_cargos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_registroempleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_especialidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_listaempleados)
                    .addComponent(btn_registroempleados))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_especialidad)
                    .addComponent(btn_cargos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btn_listaempleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listaempleadosActionPerformed
        try {
            new lista_empleados().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(menu_empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_listaempleadosActionPerformed

    private void btn_registroempleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registroempleadosActionPerformed
 this.dispose();
        try {
            new empleado().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(menu_empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_registroempleadosActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void salir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salir1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_salir1ActionPerformed

    private void btn_especialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_especialidadActionPerformed
       this.dispose();
        new especialidad().setVisible(true);
    }//GEN-LAST:event_btn_especialidadActionPerformed

    private void btn_cargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cargosActionPerformed
        this.dispose();
        new registro_cargo().setVisible(true);
    }//GEN-LAST:event_btn_cargosActionPerformed

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
            java.util.logging.Logger.getLogger(menu_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu_empleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cargos;
    private javax.swing.JButton btn_especialidad;
    private javax.swing.JButton btn_listaempleados;
    private javax.swing.JButton btn_registroempleados;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton salir;
    private javax.swing.JButton salir1;
    // End of variables declaration//GEN-END:variables
}
