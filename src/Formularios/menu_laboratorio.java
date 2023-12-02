
package Formularios;

import clases.Conectar;
import java.awt.BorderLayout;
import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class menu_laboratorio extends javax.swing.JFrame {

    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
    public menu_laboratorio() {
        initComponents();
            setExtendedState(JFrame.MAXIMIZED_BOTH);
           cargarLogo();
    }
    public void cargarLogo() {
    try {
        String query = "SELECT logo_empresa FROM empresa WHERE id_empresa = ?";
        try (PreparedStatement pst = cn.prepareStatement(query)) {
            pst.setInt(1, 1); 

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Blob blob = rs.getBlob("logo_empresa");

                    if (blob != null) {
                        byte[] bytes = blob.getBytes(1, (int) blob.length());
                        ImageIcon imagen = new ImageIcon(bytes);
                        Image image = imagen.getImage().getScaledInstance(125, 50, Image.SCALE_SMOOTH);
                        LOGO.setIcon(new ImageIcon(image));
                    } else {
                        LOGO.setIcon(null);
                        JOptionPane.showMessageDialog(null, "No hay imagen para cargar.");
                    }
                }
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al cargar el logo: " + ex.getMessage());
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botonsalir = new javax.swing.JButton();
        LOGO = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        REGISTROMEDICAMENTO = new javax.swing.JButton();
        LISTADOMEDICAMENTO = new javax.swing.JButton();
        HISTORIALMEDICAMENTO = new javax.swing.JButton();
        REGISTROFORMAS = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        OPCIONES = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(12, 93, 172));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        botonsalir.setBackground(new java.awt.Color(255, 51, 0));
        botonsalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botonsalir.setForeground(new java.awt.Color(255, 255, 255));
        botonsalir.setText("X");
        botonsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonsalirActionPerformed(evt);
            }
        });

        LOGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/logo empresa peque.png"))); // NOI18N
        LOGO.setToolTipText("");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("LABORATORIOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(LOGO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(411, 411, 411)
                .addComponent(botonsalir)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(botonsalir)))
        );

        jPanel1.setBackground(new java.awt.Color(0, 147, 214));

        REGISTROMEDICAMENTO.setBackground(new java.awt.Color(12, 93, 172));
        REGISTROMEDICAMENTO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        REGISTROMEDICAMENTO.setForeground(new java.awt.Color(255, 255, 255));
        REGISTROMEDICAMENTO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/prescripcion.png"))); // NOI18N
        REGISTROMEDICAMENTO.setText("REALIZAR ESTUDIO");
        REGISTROMEDICAMENTO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        REGISTROMEDICAMENTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REGISTROMEDICAMENTOActionPerformed(evt);
            }
        });

        LISTADOMEDICAMENTO.setBackground(new java.awt.Color(12, 93, 172));
        LISTADOMEDICAMENTO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LISTADOMEDICAMENTO.setForeground(new java.awt.Color(255, 255, 255));
        LISTADOMEDICAMENTO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/estar.png"))); // NOI18N
        LISTADOMEDICAMENTO.setText("LISTADO DE PRODUCTOS");
        LISTADOMEDICAMENTO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        HISTORIALMEDICAMENTO.setBackground(new java.awt.Color(12, 93, 172));
        HISTORIALMEDICAMENTO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        HISTORIALMEDICAMENTO.setForeground(new java.awt.Color(255, 255, 255));
        HISTORIALMEDICAMENTO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/controlar.png"))); // NOI18N
        HISTORIALMEDICAMENTO.setText("HISTORIAL MEDICAMENTOS");
        HISTORIALMEDICAMENTO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        HISTORIALMEDICAMENTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HISTORIALMEDICAMENTOActionPerformed(evt);
            }
        });

        REGISTROFORMAS.setBackground(new java.awt.Color(12, 93, 172));
        REGISTROFORMAS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        REGISTROFORMAS.setForeground(new java.awt.Color(255, 255, 255));
        REGISTROFORMAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/caja.png"))); // NOI18N
        REGISTROFORMAS.setText("REPORTE DE ESTUDIOS");
        REGISTROFORMAS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        REGISTROFORMAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REGISTROFORMASActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(REGISTROMEDICAMENTO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(REGISTROFORMAS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HISTORIALMEDICAMENTO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LISTADOMEDICAMENTO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(REGISTROMEDICAMENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(REGISTROFORMAS, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LISTADOMEDICAMENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HISTORIALMEDICAMENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(384, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        OPCIONES.setBackground(new java.awt.Color(255, 255, 255));
        OPCIONES.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 153), null));
        OPCIONES.setPreferredSize(new java.awt.Dimension(827, 500));
        OPCIONES.setRequestFocusEnabled(false);

        javax.swing.GroupLayout OPCIONESLayout = new javax.swing.GroupLayout(OPCIONES);
        OPCIONES.setLayout(OPCIONESLayout);
        OPCIONESLayout.setHorizontalGroup(
            OPCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 983, Short.MAX_VALUE)
        );
        OPCIONESLayout.setVerticalGroup(
            OPCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(OPCIONES, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(OPCIONES, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonsalirActionPerformed
     this.dispose();
    }//GEN-LAST:event_botonsalirActionPerformed

    private void HISTORIALMEDICAMENTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HISTORIALMEDICAMENTOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HISTORIALMEDICAMENTOActionPerformed

    private void REGISTROFORMASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REGISTROFORMASActionPerformed
       new registro_tipos_estudios().setVisible(true);
    }//GEN-LAST:event_REGISTROFORMASActionPerformed

    private void REGISTROMEDICAMENTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REGISTROMEDICAMENTOActionPerformed
       new registro_estudios().setVisible(true);
    }//GEN-LAST:event_REGISTROMEDICAMENTOActionPerformed
        

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
            java.util.logging.Logger.getLogger(menu_laboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu_laboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu_laboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu_laboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu_laboratorio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton HISTORIALMEDICAMENTO;
    private javax.swing.JButton LISTADOMEDICAMENTO;
    private javax.swing.JLabel LOGO;
    private javax.swing.JPanel OPCIONES;
    private javax.swing.JButton REGISTROFORMAS;
    private javax.swing.JButton REGISTROMEDICAMENTO;
    private javax.swing.JButton botonsalir;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
