
package Formularios;

import clases.Conectar;
import clases.encoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class registro_usuarios extends javax.swing.JFrame {
   Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
  
    public registro_usuarios() {
        initComponents();
        cargar_tipo_usuario();
        cargar_datos();
    }
    void cargar_tipo_usuario(){
        try{
            String query = "SELECT * FROM roles_usuarios WHERE estado = 1";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                roles.addItem(rs.getString("nombre"));
            }
        } catch (Exception error){
            JOptionPane.showMessageDialog(null,"Error: " + error);
        }
    } 
    void guardar_datos(){
        try {
            String query_tipo = "SELECT * FROM roles_usuarios WHERE nombre = '" + roles.getSelectedItem() + "'";
            PreparedStatement pst = cn.prepareStatement(query_tipo);
            ResultSet rst = pst.executeQuery();
            rst.next();
            String sql = "";
            encoder ec = new encoder();
            sql = "INSERT INTO usuarios (id_roles_usuarios,id_empleados,usuario,contrasena)VALUES('" + rst.getString("id_roles_usuarios") + "','" + id_empleado.getText() + "','" + usuario.getText() + "','" + ec.ecnode(contrasena.getText())+ "')";
            PreparedStatement psz = cn.prepareStatement(sql);

            int n;
            n = psz.executeUpdate();
            if (n > 0) {

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        JOptionPane.showMessageDialog(null, "GUARDADO CON EXITO");  
    }
    void actualizar_datos(){
        try {
            String query_tipo = "SELECT * FROM roles_usuarios WHERE nombre = '" + roles.getSelectedItem() + "'";
            PreparedStatement pst = cn.prepareStatement(query_tipo);
            ResultSet rst = pst.executeQuery();
            rst.next();
            encoder ec = new encoder();
            PreparedStatement psU = cn.prepareStatement("UPDATE usuarios SET id_roles_usuarios = '" + rst.getString("id_roles_usuarios") + "',id_empleados='"+id_empleado.getText()+"',usuario='"+usuario.getText()+"',contrasena='"+ec.ecnode(contrasena.getText())+"' where id_usuarios='"+id_usuario.getText()+"'");
            psU.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(empleado.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null,ex);
        }
        JOptionPane.showMessageDialog(null, "ACTUALIZADO CON EXITO");
    }
    void borrar_datos() {
          String query = "UPDATE usuarios SET estado = 0 WHERE id_usuarios = '" + id_usuario.getText() + "'";
            try{
                PreparedStatement psz = cn.prepareStatement(query);
                psz.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuario Eliminado");
                limpiar();
            } catch(Exception error){
                JOptionPane.showMessageDialog(null, "Error: " + error);
            }
    }
    void limpiar(){
        id_empleado.setText("");
        id_usuario.setText("");
        usuario.setText("");
        contrasena.setText("");
    }
    void cargar_datos(){
        DefaultTableModel modelo2 = (DefaultTableModel) tabla_usuario.getModel();
        modelo2.getDataVector().clear();
        String[] registros = new String[10];
        String sql = "SELECT id_usuarios,id_empleados,usuario,contrasena,id_roles_usuarios FROM usuarios limit 100";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[1] = rs.getString("id_empleados");
                registros[2] = rs.getString("usuario");
                registros[3] = rs.getString("contrasena");
                registros[4] = rs.getString("id_roles_usuarios");
                registros[0] = rs.getString("id_usuarios");
                modelo2.addRow(registros);
            }
            tabla_usuario.setModel(modelo2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        id_usuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        id_empleado = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        roles = new javax.swing.JComboBox<>();
        contrasena = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        AGREGAR = new javax.swing.JButton();
        ACTUALIZAR = new javax.swing.JButton();
        ELIMINAR = new javax.swing.JButton();
        LIMPIAR = new javax.swing.JButton();
        SALIR = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_usuario = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 93, 172), null));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("ID USUARIO");

        id_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_usuarioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("ID EMPLEADO");

        id_empleado.setEditable(false);
        id_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_empleadoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("USUARIO");

        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("ROLES");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("CONTRASEÑA");

        roles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE" }));

        jButton1.setBackground(new java.awt.Color(12, 93, 172));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        ACTUALIZAR.setBackground(new java.awt.Color(12, 93, 172));
        ACTUALIZAR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ACTUALIZAR.setForeground(new java.awt.Color(255, 255, 255));
        ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/sincronizar.png"))); // NOI18N
        ACTUALIZAR.setText("ACTUALIZAR");
        ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACTUALIZARActionPerformed(evt);
            }
        });

        ELIMINAR.setBackground(new java.awt.Color(12, 93, 172));
        ELIMINAR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ELIMINAR.setForeground(new java.awt.Color(255, 255, 255));
        ELIMINAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/papelera-de-reciclaje.png"))); // NOI18N
        ELIMINAR.setText("ELIMINAR");
        ELIMINAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ELIMINARActionPerformed(evt);
            }
        });

        LIMPIAR.setBackground(new java.awt.Color(12, 93, 172));
        LIMPIAR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LIMPIAR.setForeground(new java.awt.Color(255, 255, 255));
        LIMPIAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/limpiar.png"))); // NOI18N
        LIMPIAR.setText("LIMPIAR");
        LIMPIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPIARActionPerformed(evt);
            }
        });

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

        tabla_usuario.setBackground(new java.awt.Color(12, 93, 172));
        tabla_usuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tabla_usuario.setForeground(new java.awt.Color(255, 255, 255));
        tabla_usuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID USUARIO", "ID EMPLEADO", "USUARIO", "CONTRASEÑA", "ROLES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_usuario.setSelectionBackground(new java.awt.Color(102, 255, 102));
        tabla_usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_usuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_usuario);
        if (tabla_usuario.getColumnModel().getColumnCount() > 0) {
            tabla_usuario.getColumnModel().getColumn(0).setMinWidth(80);
            tabla_usuario.getColumnModel().getColumn(0).setMaxWidth(80);
            tabla_usuario.getColumnModel().getColumn(1).setMinWidth(100);
            tabla_usuario.getColumnModel().getColumn(1).setMaxWidth(100);
            tabla_usuario.getColumnModel().getColumn(2).setMinWidth(100);
            tabla_usuario.getColumnModel().getColumn(2).setMaxWidth(100);
            tabla_usuario.getColumnModel().getColumn(3).setMinWidth(100);
            tabla_usuario.getColumnModel().getColumn(3).setMaxWidth(100);
            tabla_usuario.getColumnModel().getColumn(4).setMinWidth(100);
            tabla_usuario.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roles, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(id_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(id_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AGREGAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(LIMPIAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ACTUALIZAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ELIMINAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(SALIR)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(id_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(id_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(roles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AGREGAR)
                            .addComponent(ACTUALIZAR))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LIMPIAR)
                            .addComponent(ELIMINAR))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SALIR)))
                .addGap(21, 21, 21))
        );

        jPanel2.setBackground(new java.awt.Color(12, 93, 172));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/logo empresa peque.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void id_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_usuarioActionPerformed
        
    }//GEN-LAST:event_id_usuarioActionPerformed

    private void id_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_empleadoActionPerformed
       
    }//GEN-LAST:event_id_empleadoActionPerformed

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        
    }//GEN-LAST:event_usuarioActionPerformed

    private void SALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALIRActionPerformed
      this.dispose();
      new menu_ajustes().setVisible(true);
    }//GEN-LAST:event_SALIRActionPerformed

    private void LIMPIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPIARActionPerformed
    limpiar();        
    }//GEN-LAST:event_LIMPIARActionPerformed

    private void ELIMINARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELIMINARActionPerformed
      borrar_datos();
      cargar_datos();
      limpiar();  
    }//GEN-LAST:event_ELIMINARActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            new lista_empleados().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(registro_usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void AGREGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AGREGARActionPerformed
        guardar_datos();
        cargar_datos();
        limpiar();  
    }//GEN-LAST:event_AGREGARActionPerformed

    private void ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACTUALIZARActionPerformed
       actualizar_datos();
       cargar_datos();
       limpiar();  
    }//GEN-LAST:event_ACTUALIZARActionPerformed

    private void tabla_usuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_usuarioMouseClicked
      int fila= tabla_usuario.getSelectedRow();
     if(fila>=0){
        DefaultTableModel table_model = (DefaultTableModel)tabla_usuario.getModel();
        String id_tipo = (String) table_model.getValueAt(tabla_usuario.getSelectedRow(), 4); 
        try{ 
            String query = "SELECT * FROM roles_usuarios WHERE id_roles_usuarios = '" + id_tipo + "'";
            PreparedStatement pst = cn.prepareStatement(query);
            ResultSet rst = pst.executeQuery();
            rst.next();
            roles.setSelectedItem(rst.getString("nombre"));
        } catch(Exception error){
            JOptionPane.showMessageDialog(null, "Error: "+error);
        }
       encoder ec = new encoder();
       id_usuario.setText(tabla_usuario.getValueAt(fila, 0).toString());
       id_empleado.setText(tabla_usuario.getValueAt(fila, 1).toString());
       usuario.setText(tabla_usuario.getValueAt(fila, 2).toString());
       contrasena.setText(ec.deecnode(tabla_usuario.getValueAt(fila, 3).toString()));
     }
    }//GEN-LAST:event_tabla_usuarioMouseClicked

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
            java.util.logging.Logger.getLogger(registro_usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registro_usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registro_usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registro_usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registro_usuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ACTUALIZAR;
    private javax.swing.JButton AGREGAR;
    private javax.swing.JButton ELIMINAR;
    private javax.swing.JButton LIMPIAR;
    private javax.swing.JButton SALIR;
    private javax.swing.JPasswordField contrasena;
    public static javax.swing.JTextField id_empleado;
    private javax.swing.JTextField id_usuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> roles;
    private javax.swing.JTable tabla_usuario;
    public static javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
