
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
import javax.swing.table.DefaultTableModel;

public class medicamento_pedido extends javax.swing.JFrame {
    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
    public medicamento_pedido(){
        initComponents();
        cargar_datos();
    }

void cargar_datos() {
    DefaultTableModel modelo2 = (DefaultTableModel) tabla_productos.getModel();
    modelo2.getDataVector().clear();
    String[] registros = new String[15];
    String sql = "SELECT pro.id_productos, pro.id_compuestos_medicamentos, pro.codigo_sugemi, pro.codigo_promese, pro.nombre_comercial, pro.descripcion_comercial, pro.concentracion, pro.tipo_presentacion, pro.precio_compra, pro.precio_venta, pro.itbis, pro.reorden, pro.stock, tp.nombre AS tipo_producto " +
                 "FROM productos pro " +
                 "INNER JOIN tipos_productos tp ON pro.id_tipos_medicamentos = tp.id_tipos_productos " +
                 "WHERE (pro.id_productos LIKE '%" + buscar.getText() + "%' OR pro.nombre_comercial LIKE '%" + buscar.getText() + "%' OR pro.descripcion_comercial LIKE '%" + buscar.getText() + "%' OR pro.codigo_sugemi LIKE '%" + buscar.getText() + "%'OR pro.codigo_promese LIKE '%" + buscar.getText() + "%') AND pro.estado = 1 LIMIT 100";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            registros[0] = rs.getString("id_productos");
            registros[1] = rs.getString("codigo_sugemi");
            registros[2] = rs.getString("codigo_promese");
            registros[3] = rs.getString("nombre_comercial");
            registros[4] = rs.getString("descripcion_comercial");
            registros[5] = rs.getString("id_compuestos_medicamentos");
            registros[6] = rs.getString("concentracion");
            registros[7] = rs.getString("tipo_presentacion");
            registros[8] = rs.getString("stock");
            registros[9] = rs.getString("precio_compra");
            registros[10] = rs.getString("precio_venta");
            registros[11] = rs.getString("tipo_producto");
            registros[12] = rs.getString("itbis");
            registros[13] = rs.getString("reorden");
            modelo2.addRow(registros);
        }
        tabla_productos.setModel(modelo2);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_productos = new javax.swing.JTable();
        AGREGAR = new javax.swing.JButton();
        SALIR = new javax.swing.JButton();
        buscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 0, 204), null));

        jPanel2.setBackground(new java.awt.Color(12, 93, 172));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("LISTADO DE MEDICAMENTO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(686, 686, 686)
                .addComponent(jLabel8)
                .addContainerGap(872, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        tabla_productos.setBackground(new java.awt.Color(12, 93, 173));
        tabla_productos.setForeground(new java.awt.Color(255, 255, 255));
        tabla_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ", "SUGEMI", "PROMESE", "NOMBRE", "DESCRIPCION", "COMPUESTOS", "CONCENTRACION", "PRESENTACION", "STOCK", "COMPRA", "VENTA", "TIPO", "ITBIS", "REORDEN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_productosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_productos);
        if (tabla_productos.getColumnModel().getColumnCount() > 0) {
            tabla_productos.getColumnModel().getColumn(0).setMinWidth(80);
            tabla_productos.getColumnModel().getColumn(0).setMaxWidth(80);
            tabla_productos.getColumnModel().getColumn(1).setMinWidth(100);
            tabla_productos.getColumnModel().getColumn(1).setMaxWidth(100);
            tabla_productos.getColumnModel().getColumn(2).setMinWidth(80);
            tabla_productos.getColumnModel().getColumn(2).setMaxWidth(80);
            tabla_productos.getColumnModel().getColumn(3).setMinWidth(175);
            tabla_productos.getColumnModel().getColumn(3).setMaxWidth(175);
            tabla_productos.getColumnModel().getColumn(4).setMinWidth(250);
            tabla_productos.getColumnModel().getColumn(4).setMaxWidth(250);
            tabla_productos.getColumnModel().getColumn(5).setMinWidth(275);
            tabla_productos.getColumnModel().getColumn(5).setMaxWidth(275);
            tabla_productos.getColumnModel().getColumn(6).setMinWidth(150);
            tabla_productos.getColumnModel().getColumn(6).setMaxWidth(150);
            tabla_productos.getColumnModel().getColumn(7).setMinWidth(100);
            tabla_productos.getColumnModel().getColumn(7).setMaxWidth(100);
            tabla_productos.getColumnModel().getColumn(8).setMinWidth(100);
            tabla_productos.getColumnModel().getColumn(8).setMaxWidth(100);
            tabla_productos.getColumnModel().getColumn(9).setMinWidth(100);
            tabla_productos.getColumnModel().getColumn(9).setMaxWidth(100);
            tabla_productos.getColumnModel().getColumn(10).setMinWidth(100);
            tabla_productos.getColumnModel().getColumn(10).setMaxWidth(100);
        }

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

        SALIR.setBackground(new java.awt.Color(255, 0, 0));
        SALIR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SALIR.setForeground(new java.awt.Color(255, 255, 255));
        SALIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar-sesion (1).png"))); // NOI18N
        SALIR.setText("SALIR");
        SALIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALIRActionPerformed(evt);
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
        jLabel3.setText("BUSCAR MEDICAMENTOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(AGREGAR, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1858, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AGREGAR)
                    .addComponent(SALIR))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void AGREGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AGREGARActionPerformed
cargar_datos();
    }//GEN-LAST:event_AGREGARActionPerformed

    private void SALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALIRActionPerformed
        this.dispose();
    }//GEN-LAST:event_SALIRActionPerformed
 
    private void tabla_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productosMouseClicked
      int fila = tabla_productos.getSelectedRow();
     if(fila>=0){
       registro_pedidos.id_medicamento.setText(tabla_productos.getValueAt(fila, 0).toString());
       registro_pedidos.nombre_medicamento.setText(tabla_productos.getValueAt(fila, 3).toString());  
       registro_pedidos.precio_producto.setText(tabla_productos.getValueAt(fila, 9).toString());
       registro_pedidos.itbis_producto.setText(tabla_productos.getValueAt(fila, 12).toString());
       registro_pedidos.cantidad.requestFocus();
       this.dispose();
     } 
    }//GEN-LAST:event_tabla_productosMouseClicked

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed

    }//GEN-LAST:event_buscarActionPerformed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        cargar_datos();
    }//GEN-LAST:event_buscarKeyReleased

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
            java.util.logging.Logger.getLogger(medicamento_pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(medicamento_pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(medicamento_pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(medicamento_pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new medicamento_pedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AGREGAR;
    private javax.swing.JButton SALIR;
    private javax.swing.JTextField buscar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_productos;
    // End of variables declaration//GEN-END:variables
}
