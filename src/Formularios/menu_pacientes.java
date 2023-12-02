
package Formularios;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class menu_pacientes extends javax.swing.JFrame {

  
    public menu_pacientes() {
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
        btn_listapacientes = new javax.swing.JButton();
        btn_registropacientes = new javax.swing.JButton();
        btn_especialidad = new javax.swing.JButton();
        btn_especialidad1 = new javax.swing.JButton();

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
        jLabel11.setText("ADMINISTRAR PACIENTES");

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

        btn_listapacientes.setBackground(new java.awt.Color(12, 93, 172));
        btn_listapacientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_listapacientes.setForeground(new java.awt.Color(255, 255, 255));
        btn_listapacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/historial-medico.png"))); // NOI18N
        btn_listapacientes.setText("LISTADO DE PACIENTES");
        btn_listapacientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_listapacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listapacientesActionPerformed(evt);
            }
        });

        btn_registropacientes.setBackground(new java.awt.Color(12, 93, 172));
        btn_registropacientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_registropacientes.setForeground(new java.awt.Color(255, 255, 255));
        btn_registropacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuario_1.png"))); // NOI18N
        btn_registropacientes.setText("REGISTRO DE PACIENTES");
        btn_registropacientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_registropacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registropacientesActionPerformed(evt);
            }
        });

        btn_especialidad.setBackground(new java.awt.Color(12, 93, 172));
        btn_especialidad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_especialidad.setForeground(new java.awt.Color(255, 255, 255));
        btn_especialidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/medico.png"))); // NOI18N
        btn_especialidad.setText("PACIENTES INTERNOS");
        btn_especialidad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_especialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_especialidadActionPerformed(evt);
            }
        });

        btn_especialidad1.setBackground(new java.awt.Color(12, 93, 172));
        btn_especialidad1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_especialidad1.setForeground(new java.awt.Color(255, 255, 255));
        btn_especialidad1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/proteger (2).png"))); // NOI18N
        btn_especialidad1.setText("ASOCIAR SEGUROS");
        btn_especialidad1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_especialidad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_especialidad1ActionPerformed(evt);
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
                        .addComponent(btn_listapacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_registropacientes)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_especialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_especialidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_listapacientes)
                    .addComponent(btn_registropacientes))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_especialidad)
                    .addComponent(btn_especialidad1))
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

    private void btn_listapacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listapacientesActionPerformed
        try {
            new lista_pacientes().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(menu_pacientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_listapacientesActionPerformed

    private void btn_registropacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registropacientesActionPerformed
        this.dispose();
         new registro_pacientes().setVisible(true);
     
    }//GEN-LAST:event_btn_registropacientesActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void salir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salir1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_salir1ActionPerformed

    private void btn_especialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_especialidadActionPerformed
       this.dispose();
        try {
            new lista_internos().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(menu_pacientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_especialidadActionPerformed

    private void btn_especialidad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_especialidad1ActionPerformed
       new registro_seguros().setVisible(true);
    }//GEN-LAST:event_btn_especialidad1ActionPerformed

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
            java.util.logging.Logger.getLogger(menu_pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu_pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu_pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu_pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu_pacientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_especialidad;
    private javax.swing.JButton btn_especialidad1;
    private javax.swing.JButton btn_listapacientes;
    private javax.swing.JButton btn_registropacientes;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton salir;
    private javax.swing.JButton salir1;
    // End of variables declaration//GEN-END:variables
}
