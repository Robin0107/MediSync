
package Formularios;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import clases.Conectar;

public class registro_tipo_salas extends javax.swing.JFrame {
    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
    
    public registro_tipo_salas() {
        initComponents();
        cargar_tipo_habitacion();
    }
    void limpiar_campos(){
        idtipo.setText("");
        nombre.setText("");
    }
    void cargar_tipo_habitacion(){
        try{
            String query = "SELECT * FROM tipos_salas WHERE (id_tipos_salas LIKE '%" + buscar.getText() + "%' OR nombre LIKE '%"+buscar.getText()+"%') and estado = 1";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            DefaultTableModel model_table = (DefaultTableModel)tabla_tipo_habitacion.getModel();
            model_table.getDataVector().clear();
            String registros[] = new String[5];
            while(rs.next()){
                registros[0] = rs.getString("id_tipos_salas");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("descripcion");  
                model_table.addRow(registros);
            }  
        } catch(Exception error){
            JOptionPane.showMessageDialog(null, "Error: " + error);
        }
    }   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        idtipo = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_tipo_habitacion = new javax.swing.JTable();
        agregar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        buscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        descripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(12, 93, 172));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 93, 172), null));

        idtipo.setEditable(false);

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(12, 93, 172));
        jLabel1.setText("ID TIPO SALAS");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(12, 93, 172));
        jLabel2.setText("TIPO SALAS");

        tabla_tipo_habitacion.setBackground(new java.awt.Color(12, 93, 172));
        tabla_tipo_habitacion.setForeground(new java.awt.Color(255, 255, 255));
        tabla_tipo_habitacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TIPO SALAS", "DESCRIPCION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_tipo_habitacion.setSelectionBackground(new java.awt.Color(102, 255, 51));
        tabla_tipo_habitacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_tipo_habitacionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_tipo_habitacion);
        if (tabla_tipo_habitacion.getColumnModel().getColumnCount() > 0) {
            tabla_tipo_habitacion.getColumnModel().getColumn(1).setMinWidth(150);
            tabla_tipo_habitacion.getColumnModel().getColumn(1).setMaxWidth(150);
            tabla_tipo_habitacion.getColumnModel().getColumn(2).setMinWidth(150);
            tabla_tipo_habitacion.getColumnModel().getColumn(2).setMaxWidth(150);
        }

        agregar.setBackground(new java.awt.Color(12, 93, 172));
        agregar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        agregar.setForeground(new java.awt.Color(255, 255, 255));
        agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/boton-agregar.png"))); // NOI18N
        agregar.setText("  AGREGAR");
        agregar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        actualizar.setBackground(new java.awt.Color(12, 93, 172));
        actualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        actualizar.setForeground(new java.awt.Color(255, 255, 255));
        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/sincronizar.png"))); // NOI18N
        actualizar.setText("  ACTUALIZAR");
        actualizar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        eliminar.setBackground(new java.awt.Color(12, 93, 172));
        eliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        eliminar.setForeground(new java.awt.Color(255, 255, 255));
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/papelera-de-reciclaje.png"))); // NOI18N
        eliminar.setText("  ELIMINAR");
        eliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        salir.setBackground(new java.awt.Color(255, 0, 0));
        salir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar-sesion (1).png"))); // NOI18N
        salir.setText("SALIR");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(12, 93, 172));
        jLabel3.setText("BUSCAR");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(12, 93, 172));
        jLabel5.setText("DESCRIPCION DE SALA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(idtipo)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buscar)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(nombre)
                    .addComponent(descripcion))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(salir)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(2, 2, 2)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(agregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(actualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eliminar)
                            .addComponent(salir))
                        .addContainerGap())))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("REGISTRAR TIPO DE SALA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
this.dispose();
new menu_ajustes().setVisible(true);
    }//GEN-LAST:event_salirActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
         if(nombre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos");
        } else {
            try{
                String query = "INSERT INTO tipos_salas(nombre,descripcion) VALUES ('" + nombre.getText() + "','" + descripcion.getText() + "')";
                PreparedStatement psz = cn.prepareStatement(query);
                psz.executeUpdate();
                JOptionPane.showMessageDialog(null, "Tipo de sala agregado");
                limpiar_campos();
                cargar_tipo_habitacion();
            } catch(Exception error){
                JOptionPane.showMessageDialog(null, "Error: " + error);
            }
        }
    }//GEN-LAST:event_agregarActionPerformed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
  cargar_tipo_habitacion();
    }//GEN-LAST:event_buscarKeyReleased

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
           if(nombre.getText().equals("") || idtipo.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos");
        } else {
            String query = "UPDATE tipos_salas SET descripcion = '" + descripcion.getText() +"', nombre = '" + nombre.getText() +"' WHERE id_tipos_salas = '" + idtipo.getText() + "'";
            try{
                PreparedStatement psz = cn.prepareStatement(query);
                psz.executeUpdate();
                JOptionPane.showMessageDialog(null, "Tipo de sala modificado");
                limpiar_campos();
                cargar_tipo_habitacion();
            } catch(Exception error){
                JOptionPane.showMessageDialog(null, "Error: " + error);
            }
        }
    }//GEN-LAST:event_actualizarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
             if(idtipo.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos");
        } else {
            String query = "UPDATE tipos_salas SET estado = 0 WHERE id_tipos_salas = '" + idtipo.getText() + "'";
            try{
                PreparedStatement psz = cn.prepareStatement(query);
                psz.executeUpdate();
                JOptionPane.showMessageDialog(null, "Tipo de sala habitacion eliminado");
                limpiar_campos();
                cargar_tipo_habitacion();
            } catch(Exception error){
                JOptionPane.showMessageDialog(null, "Error: " + error);
            }
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void tabla_tipo_habitacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_tipo_habitacionMouseClicked
    DefaultTableModel table_model = (DefaultTableModel)tabla_tipo_habitacion.getModel();
    idtipo.setText((String) table_model.getValueAt(tabla_tipo_habitacion.getSelectedRow(), 0));
    nombre.setText((String) table_model.getValueAt(tabla_tipo_habitacion.getSelectedRow(), 1));
    descripcion.setText((String) table_model.getValueAt(tabla_tipo_habitacion.getSelectedRow(), 2));
    }//GEN-LAST:event_tabla_tipo_habitacionMouseClicked

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

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
            java.util.logging.Logger.getLogger(registro_tipo_salas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registro_tipo_salas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registro_tipo_salas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registro_tipo_salas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new registro_tipo_salas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton agregar;
    private javax.swing.JTextField buscar;
    private javax.swing.JTextField descripcion;
    private javax.swing.JButton eliminar;
    private javax.swing.JTextField idtipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton salir;
    private javax.swing.JTable tabla_tipo_habitacion;
    // End of variables declaration//GEN-END:variables
}
