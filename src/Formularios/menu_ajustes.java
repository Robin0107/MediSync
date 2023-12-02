
package Formularios;


public class menu_ajustes extends javax.swing.JFrame {

  
    public menu_ajustes() {
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
        btn_salas = new javax.swing.JButton();
        btn_tiposalas = new javax.swing.JButton();
        btn_roles = new javax.swing.JButton();
        btn_usuarios = new javax.swing.JButton();
        btn_informacion = new javax.swing.JButton();
        btn_permisosusuarios = new javax.swing.JButton();
        btn_laboratorios = new javax.swing.JButton();
        btn_departamentos = new javax.swing.JButton();
        btn_asociacion = new javax.swing.JButton();
        btn_tipos_estudios = new javax.swing.JButton();
        btn_tipo_seguros = new javax.swing.JButton();
        btn_registro_aseguradoras = new javax.swing.JButton();

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
        jLabel11.setText("MENU DE AJUSTES");

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
                .addGap(266, 266, 266)
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

        btn_salas.setBackground(new java.awt.Color(12, 93, 172));
        btn_salas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_salas.setForeground(new java.awt.Color(255, 255, 255));
        btn_salas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cama-de-hospital.png"))); // NOI18N
        btn_salas.setText(" REGISTRO DE SALAS");
        btn_salas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_salas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salasActionPerformed(evt);
            }
        });

        btn_tiposalas.setBackground(new java.awt.Color(12, 93, 172));
        btn_tiposalas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_tiposalas.setForeground(new java.awt.Color(255, 255, 255));
        btn_tiposalas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cama.png"))); // NOI18N
        btn_tiposalas.setText("TIPO DE SALAS");
        btn_tiposalas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_tiposalas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tiposalasActionPerformed(evt);
            }
        });

        btn_roles.setBackground(new java.awt.Color(12, 93, 172));
        btn_roles.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_roles.setForeground(new java.awt.Color(255, 255, 255));
        btn_roles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/gestion-de-equipos.png"))); // NOI18N
        btn_roles.setText("ROLES DE EMPLEADOS");
        btn_roles.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_roles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rolesActionPerformed(evt);
            }
        });

        btn_usuarios.setBackground(new java.awt.Color(12, 93, 172));
        btn_usuarios.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_usuarios.setForeground(new java.awt.Color(255, 255, 255));
        btn_usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/especialista.png"))); // NOI18N
        btn_usuarios.setText("REGISTRO DE USUARIOS");
        btn_usuarios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuariosActionPerformed(evt);
            }
        });

        btn_informacion.setBackground(new java.awt.Color(12, 93, 172));
        btn_informacion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_informacion.setForeground(new java.awt.Color(255, 255, 255));
        btn_informacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/hospital.png"))); // NOI18N
        btn_informacion.setText("INFORMACION EMPRESA");
        btn_informacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_informacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_informacionActionPerformed(evt);
            }
        });

        btn_permisosusuarios.setBackground(new java.awt.Color(12, 93, 172));
        btn_permisosusuarios.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_permisosusuarios.setForeground(new java.awt.Color(255, 255, 255));
        btn_permisosusuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/visa.png"))); // NOI18N
        btn_permisosusuarios.setText("PERMISOS DE USUARIOS");
        btn_permisosusuarios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_permisosusuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_permisosusuariosActionPerformed(evt);
            }
        });

        btn_laboratorios.setBackground(new java.awt.Color(12, 93, 172));
        btn_laboratorios.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_laboratorios.setForeground(new java.awt.Color(255, 255, 255));
        btn_laboratorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/investigador.png"))); // NOI18N
        btn_laboratorios.setText("REGISTRO DE LABORATORIOS");
        btn_laboratorios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_laboratorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_laboratoriosActionPerformed(evt);
            }
        });

        btn_departamentos.setBackground(new java.awt.Color(12, 93, 172));
        btn_departamentos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_departamentos.setForeground(new java.awt.Color(255, 255, 255));
        btn_departamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/empresa.png"))); // NOI18N
        btn_departamentos.setText("REGISTRO DE DEPARTAMENTOS");
        btn_departamentos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_departamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_departamentosActionPerformed(evt);
            }
        });

        btn_asociacion.setBackground(new java.awt.Color(12, 93, 172));
        btn_asociacion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_asociacion.setForeground(new java.awt.Color(255, 255, 255));
        btn_asociacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/perfil (1).png"))); // NOI18N
        btn_asociacion.setText("ASOCIACION DE DEP. CON ESP.");
        btn_asociacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_asociacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_asociacionActionPerformed(evt);
            }
        });

        btn_tipos_estudios.setBackground(new java.awt.Color(12, 93, 172));
        btn_tipos_estudios.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_tipos_estudios.setForeground(new java.awt.Color(255, 255, 255));
        btn_tipos_estudios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/prescripcion.png"))); // NOI18N
        btn_tipos_estudios.setText("REGISTRO DE TIPOS ESTUDIOS");
        btn_tipos_estudios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_tipos_estudios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tipos_estudiosActionPerformed(evt);
            }
        });

        btn_tipo_seguros.setBackground(new java.awt.Color(12, 93, 172));
        btn_tipo_seguros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_tipo_seguros.setForeground(new java.awt.Color(255, 255, 255));
        btn_tipo_seguros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/seguro-de-vida.png"))); // NOI18N
        btn_tipo_seguros.setText("REGISTRO DE TIPOS SEGUROS");
        btn_tipo_seguros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_tipo_seguros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tipo_segurosActionPerformed(evt);
            }
        });

        btn_registro_aseguradoras.setBackground(new java.awt.Color(12, 93, 172));
        btn_registro_aseguradoras.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_registro_aseguradoras.setForeground(new java.awt.Color(255, 255, 255));
        btn_registro_aseguradoras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/asegurado.png"))); // NOI18N
        btn_registro_aseguradoras.setText("REGISTRO DE ASEGURADORAS");
        btn_registro_aseguradoras.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_registro_aseguradoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registro_aseguradorasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_tipos_estudios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(btn_laboratorios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_tiposalas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_salas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_permisosusuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_roles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_usuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_tipo_seguros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_departamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_informacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_asociacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_registro_aseguradoras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_salas, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_roles, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_informacion, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tiposalas, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_departamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_laboratorios, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_permisosusuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_asociacion, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tipos_estudios, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tipo_seguros, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_registro_aseguradoras, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
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

    private void btn_salasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salasActionPerformed
     this.dispose();
 new registro_salas().setVisible(true);
    }//GEN-LAST:event_btn_salasActionPerformed

    private void btn_tiposalasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tiposalasActionPerformed
 this.dispose();
 new registro_tipo_salas().setVisible(true);
    }//GEN-LAST:event_btn_tiposalasActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void salir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salir1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_salir1ActionPerformed

    private void btn_rolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rolesActionPerformed
       this.dispose();
        new registro_roles().setVisible(true);
    }//GEN-LAST:event_btn_rolesActionPerformed

    private void btn_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuariosActionPerformed
       this.dispose();
        new registro_usuarios().setVisible(true);
    }//GEN-LAST:event_btn_usuariosActionPerformed

    private void btn_informacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_informacionActionPerformed
       this.dispose();
        new empresa().setVisible(true);
    }//GEN-LAST:event_btn_informacionActionPerformed

    private void btn_permisosusuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_permisosusuariosActionPerformed
       
    }//GEN-LAST:event_btn_permisosusuariosActionPerformed

    private void btn_laboratoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_laboratoriosActionPerformed
     this.dispose();
        new registro_laboratorio().setVisible(true);    }//GEN-LAST:event_btn_laboratoriosActionPerformed

    private void btn_departamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_departamentosActionPerformed
    this.dispose();
        new registro_departamentos().setVisible(true);
    }//GEN-LAST:event_btn_departamentosActionPerformed

    private void btn_asociacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_asociacionActionPerformed
        
    }//GEN-LAST:event_btn_asociacionActionPerformed

    private void btn_tipos_estudiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tipos_estudiosActionPerformed
           this.dispose();
        new registro_tipos_estudios().setVisible(true);
    }//GEN-LAST:event_btn_tipos_estudiosActionPerformed

    private void btn_tipo_segurosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tipo_segurosActionPerformed
        this.dispose();
        new registro_tipo_seguro().setVisible(true);
    }//GEN-LAST:event_btn_tipo_segurosActionPerformed

    private void btn_registro_aseguradorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registro_aseguradorasActionPerformed
    this.dispose();
        new registro_aseguradoras().setVisible(true);
    }//GEN-LAST:event_btn_registro_aseguradorasActionPerformed

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
            java.util.logging.Logger.getLogger(menu_ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu_ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu_ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu_ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu_ajustes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_asociacion;
    private javax.swing.JButton btn_departamentos;
    private javax.swing.JButton btn_informacion;
    private javax.swing.JButton btn_laboratorios;
    private javax.swing.JButton btn_permisosusuarios;
    private javax.swing.JButton btn_registro_aseguradoras;
    private javax.swing.JButton btn_roles;
    private javax.swing.JButton btn_salas;
    private javax.swing.JButton btn_tipo_seguros;
    private javax.swing.JButton btn_tipos_estudios;
    private javax.swing.JButton btn_tiposalas;
    private javax.swing.JButton btn_usuarios;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton salir;
    private javax.swing.JButton salir1;
    // End of variables declaration//GEN-END:variables
}
