/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import clases.Conectar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

/**
 *
 * @author Trabajos Produccion
 */
public class permisos extends javax.swing.JFrame {
    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
    
    public permisos() {
        initComponents();
    }
    
    void limpiar(){
                empleados.setSelected(false);
                emergencias.setSelected(false);
                laboratorio.setSelected(false);
                facturacion.setSelected(false);
                ajustes.setSelected(false);
                farmacia.setSelected(false);
                reportes.setSelected(false);
                pacientes.setSelected(false);
                internos.setSelected(false);
                citas.setSelected(false);
                id_roles.setText("");
                descripcion.setText("");
    }

 private void agregarPermisos() {
    int idRolesEmpleados = Integer.parseInt(id_roles.getText());
    try {
 
        agregarPermiso(cn, idRolesEmpleados, emergencias, 1);
        agregarPermiso(cn, idRolesEmpleados, citas, 2);
        agregarPermiso(cn, idRolesEmpleados, empleados, 3);
        agregarPermiso(cn, idRolesEmpleados, internos, 4);
        agregarPermiso(cn, idRolesEmpleados, farmacia, 5);
        agregarPermiso(cn, idRolesEmpleados, ajustes, 6);
        agregarPermiso(cn, idRolesEmpleados, reportes, 7);
        agregarPermiso(cn, idRolesEmpleados, pacientes, 8);
        agregarPermiso(cn, idRolesEmpleados, facturacion, 9);
        agregarPermiso(cn, idRolesEmpleados, laboratorio, 10);
    

        cn.close();
    }catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
      JOptionPane.showMessageDialog(null, "PERMISOS AGREGADOS CON EXITO");
      limpiar();
}

private void agregarPermiso(Connection connection, int idRolesEmpleados, JCheckBox checkBox, int idVista) throws SQLException {
    if (checkBox.isSelected()) {
        String sql = "INSERT INTO permisos (id_roles_usuarios, id_vistas) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idRolesEmpleados);
            statement.setInt(2, idVista);
            statement.executeUpdate();
        }
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        salir1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        empleados = new javax.swing.JCheckBox();
        emergencias = new javax.swing.JCheckBox();
        pacientes = new javax.swing.JCheckBox();
        facturacion = new javax.swing.JCheckBox();
        internos = new javax.swing.JCheckBox();
        farmacia = new javax.swing.JCheckBox();
        laboratorio = new javax.swing.JCheckBox();
        ajustes = new javax.swing.JCheckBox();
        reportes = new javax.swing.JCheckBox();
        seleccionartodos = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        AGREGAR = new javax.swing.JButton();
        ACTUALIZAR = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        descripcion = new javax.swing.JTextField();
        id_roles = new javax.swing.JTextField();
        agregar = new javax.swing.JButton();
        citas = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 93, 172), null));

        jPanel2.setBackground(new java.awt.Color(12, 93, 172));

        salir1.setBackground(new java.awt.Color(204, 0, 0));
        salir1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        salir1.setForeground(new java.awt.Color(255, 255, 255));
        salir1.setText("X");
        salir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salir1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("PERMISOS DE USUARIOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                .addComponent(salir1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(salir1))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        empleados.setBackground(new java.awt.Color(255, 255, 255));
        empleados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        empleados.setForeground(new java.awt.Color(12, 93, 172));
        empleados.setText("EMPLEADOS");

        emergencias.setBackground(new java.awt.Color(255, 255, 255));
        emergencias.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        emergencias.setForeground(new java.awt.Color(12, 93, 172));
        emergencias.setText("EMERGENCIAS");

        pacientes.setBackground(new java.awt.Color(255, 255, 255));
        pacientes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pacientes.setForeground(new java.awt.Color(12, 93, 172));
        pacientes.setText("PACIENTES");

        facturacion.setBackground(new java.awt.Color(255, 255, 255));
        facturacion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        facturacion.setForeground(new java.awt.Color(12, 93, 172));
        facturacion.setText("FACTURACION");
        facturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facturacionActionPerformed(evt);
            }
        });

        internos.setBackground(new java.awt.Color(255, 255, 255));
        internos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        internos.setForeground(new java.awt.Color(12, 93, 172));
        internos.setText("INTERNOS");

        farmacia.setBackground(new java.awt.Color(255, 255, 255));
        farmacia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        farmacia.setForeground(new java.awt.Color(12, 93, 172));
        farmacia.setText("FARMACIA");

        laboratorio.setBackground(new java.awt.Color(255, 255, 255));
        laboratorio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        laboratorio.setForeground(new java.awt.Color(12, 93, 172));
        laboratorio.setText("LABORATORIO");

        ajustes.setBackground(new java.awt.Color(255, 255, 255));
        ajustes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ajustes.setForeground(new java.awt.Color(12, 93, 172));
        ajustes.setText("AJUSTES");

        reportes.setBackground(new java.awt.Color(255, 255, 255));
        reportes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        reportes.setForeground(new java.awt.Color(12, 93, 172));
        reportes.setText("REPORTES");

        seleccionartodos.setBackground(new java.awt.Color(255, 255, 255));
        seleccionartodos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        seleccionartodos.setForeground(new java.awt.Color(12, 93, 172));
        seleccionartodos.setText("SELECIONAR TODOS");
        seleccionartodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionartodosActionPerformed(evt);
            }
        });

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(12, 93, 172));
        jLabel1.setText("ID ROLES");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(12, 93, 172));
        jLabel2.setText("ROLES");

        agregar.setBackground(new java.awt.Color(12, 93, 172));
        agregar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        agregar.setForeground(new java.awt.Color(255, 255, 255));
        agregar.setText("...");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        citas.setBackground(new java.awt.Color(255, 255, 255));
        citas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        citas.setForeground(new java.awt.Color(12, 93, 172));
        citas.setText("CITAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(AGREGAR, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ACTUALIZAR, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(seleccionartodos)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pacientes)
                                            .addComponent(emergencias)
                                            .addComponent(empleados))
                                        .addGap(19, 19, 19)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(laboratorio)
                                            .addComponent(farmacia)
                                            .addComponent(internos))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ajustes)
                                            .addComponent(facturacion)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(reportes)
                                                .addGap(43, 43, 43)
                                                .addComponent(citas))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(id_roles, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(458, 458, 458)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(id_roles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(seleccionartodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(empleados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emergencias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pacientes))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(internos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(farmacia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(laboratorio)
                            .addComponent(facturacion)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reportes)
                            .addComponent(citas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ajustes)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AGREGAR)
                    .addComponent(ACTUALIZAR))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void salir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salir1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_salir1ActionPerformed

    private void facturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facturacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facturacionActionPerformed

    private void seleccionartodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionartodosActionPerformed
        seleccionartodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = seleccionartodos.isSelected();
                empleados.setSelected(isSelected);
                emergencias.setSelected(isSelected);
                laboratorio.setSelected(isSelected);
                facturacion.setSelected(isSelected);
                ajustes.setSelected(isSelected);
                farmacia.setSelected(isSelected);
                reportes.setSelected(isSelected);
                pacientes.setSelected(isSelected);
                internos.setSelected(isSelected);
                citas.setSelected(isSelected);
            }
        });    }//GEN-LAST:event_seleccionartodosActionPerformed

    private void AGREGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AGREGARActionPerformed
        agregarPermisos();      
    }//GEN-LAST:event_AGREGARActionPerformed

    private void ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACTUALIZARActionPerformed
       
    }//GEN-LAST:event_ACTUALIZARActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        new registro_roles().setVisible(true);  
    }//GEN-LAST:event_agregarActionPerformed

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
            java.util.logging.Logger.getLogger(permisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(permisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(permisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(permisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new permisos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ACTUALIZAR;
    private javax.swing.JButton AGREGAR;
    private javax.swing.JButton agregar;
    private javax.swing.JCheckBox ajustes;
    private javax.swing.JCheckBox citas;
    public static javax.swing.JTextField descripcion;
    private javax.swing.JCheckBox emergencias;
    private javax.swing.JCheckBox empleados;
    private javax.swing.JCheckBox facturacion;
    private javax.swing.JCheckBox farmacia;
    public static javax.swing.JTextField id_roles;
    private javax.swing.JCheckBox internos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JCheckBox laboratorio;
    private javax.swing.JCheckBox pacientes;
    private javax.swing.JCheckBox reportes;
    private javax.swing.JButton salir1;
    private javax.swing.JCheckBox seleccionartodos;
    // End of variables declaration//GEN-END:variables
}
