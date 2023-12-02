package Formularios;


import Formularios.mensajes_emergentes.login_error;
import Formularios.mensajes_emergentes.login_incorrecto;
import Formularios.mensajes_emergentes.login_rellenar;
import static Formularios.menu.MenuPlegable;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.*;
import clases.Conectar;
import java.awt.Window;
import java.util.Arrays;
import javax.swing.JFrame;
import clases.encoder;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

public class login_codigo extends javax.swing.JFrame {
    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
    
    public static int idEmpleado;
    public static String empleado;
    public static int idrolusuario;
    public login_codigo() {
        initComponents();
        closeAllFormsExceptLogin();
        
    }
  
private void closeAllFormsExceptLogin() {
    for (Window window : Window.getWindows()) {
        if (window instanceof JFrame && window != this) {
            JFrame frame = (JFrame) window;
            frame.dispose();
        }
    }
}
 

//  public void iniciarsesion() {
//        if (txtUsuario.getText().isEmpty() || txtContrasena.getText().isEmpty()) {
//            new login_rellenar().setVisible(true);
//        } else {
//            String usuario = txtUsuario.getText();
//            encoder ec = new encoder();
//            String contrasena = ec.ecnode(txtContrasena.getText());
//
//            String query = "SELECT id_usuarios, id_roles_usuarios, contrasena FROM usuarios WHERE usuario = ? and contrasena = ? and estado = 1";
//
//            try (PreparedStatement statement = cn.prepareStatement(query)) {
//                statement.setString(1, usuario);
//                statement.setString(2, contrasena);
//
//                try (ResultSet rs = statement.executeQuery()) {
//                    if (rs.next()) {
//                        idrolusuario = rs.getInt("id_roles_usuarios");
//                        new menu().setVisible(true);
//                    } else {
//                        new login_incorrecto().setVisible(true);
//                    }
//                }
//            } catch (SQLException error) {
//                JOptionPane.showMessageDialog(null, "SUCEDIO UN ERROR AL INICIAR SESIÓN: " + error.getMessage());
//            }
//        }
//    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menulogin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btniniciarsesion = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menulogin.setBackground(new java.awt.Color(0, 147, 214));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CODIGO DE EMPLEADO");

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
        });

        btniniciarsesion.setBackground(new java.awt.Color(12, 93, 173));
        btniniciarsesion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btniniciarsesion.setForeground(new java.awt.Color(255, 255, 255));
        btniniciarsesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iniciar-sesion.png"))); // NOI18N
        btniniciarsesion.setText("  INICIAR SECCION");
        btniniciarsesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btniniciarsesionActionPerformed(evt);
            }
        });
        btniniciarsesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btniniciarsesionKeyPressed(evt);
            }
        });

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

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuario.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("INICIAR CON USUARIO");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuloginLayout = new javax.swing.GroupLayout(menulogin);
        menulogin.setLayout(menuloginLayout);
        menuloginLayout.setHorizontalGroup(
            menuloginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuloginLayout.createSequentialGroup()
                .addGroup(menuloginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(menuloginLayout.createSequentialGroup()
                        .addGroup(menuloginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(menuloginLayout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(jLabel3))
                            .addGroup(menuloginLayout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addGroup(menuloginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btniniciarsesion, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 85, Short.MAX_VALUE))
                    .addGroup(menuloginLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salir)))
                .addContainerGap())
            .addGroup(menuloginLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuloginLayout.setVerticalGroup(
            menuloginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuloginLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btniniciarsesion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(salir)
                .addGap(20, 20, 20))
        );

        getContentPane().add(menulogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, -1, 410));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/logo empresa.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(115, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(104, 104, 104))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 0, 390, 410));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btniniciarsesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btniniciarsesionActionPerformed
// iniciarsesion();
    }//GEN-LAST:event_btniniciarsesionActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
      this.dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
     
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btniniciarsesionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btniniciarsesionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btniniciarsesionKeyPressed

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//        iniciarsesion();
    }
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
       this.dispose();
        new login().setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked
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
            java.util.logging.Logger.getLogger(login_codigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login_codigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login_codigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login_codigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new login_codigo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btniniciarsesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel menulogin;
    private javax.swing.JButton salir;
    public static javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
