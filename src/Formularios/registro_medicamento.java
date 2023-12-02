
package Formularios;

import clases.Conectar;
import java.io.File;
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
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class registro_medicamento extends javax.swing.JFrame {
    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
    public registro_medicamento(){
        initComponents();
        cargar_formas();
        cargar_datos();
        ruta_img.setVisible(false);
    }
   void cargar_formas(){
        try{
            String query = "SELECT * FROM tipos_productos WHERE estado = 1";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                tipo.addItem(rs.getString("nombre"));
            }
        } catch (Exception error){
            JOptionPane.showMessageDialog(null,"Error: " + error);
        }
    }
 
   void actualizar_datos(){
        try {
               String seguroseleccionado;
            seguroseleccionado = seguro.getSelectedItem().toString();
                if ("SI".equals(seguroseleccionado)) {
                } else if ("NO".equals(seguroseleccionado)) {
                } 
           
        String tiposeleccionado;
        tiposeleccionado = presentacion.getSelectedItem().toString();
                if ("A+".equals(tiposeleccionado)) {
                }  else if ("TABLETAS".equals(tiposeleccionado)) {
                }  else if ("CÁPSULAS".equals(tiposeleccionado)) {
                }  else if ("JARABE".equals(tiposeleccionado)) {
                }  else if ("SUSPENSIÓN".equals(tiposeleccionado)) {
                }  else if ("GOTAS".equals(tiposeleccionado)) {
                }  else if ("INYECCIÓN".equals(tiposeleccionado)) {
                }  else if ("CREMA".equals(tiposeleccionado)) {
                }  else if ("PARCHE TRANSDÉRMICO".equals(tiposeleccionado)) {
                }  else if ("SUPOSITORIO".equals(tiposeleccionado)) {
                }  else if ("AEROSOL".equals(tiposeleccionado)) {
                }  else if ("POLVO".equals(tiposeleccionado)) {
                }
        String tipoMedicamentoSeleccionado = (String) tipo.getSelectedItem();
        String query_tipo_medicamento = "SELECT id FROM tipos_productos WHERE nombre = ?";
        PreparedStatement pst2 = cn.prepareStatement(query_tipo_medicamento);
        pst2.setString(1, tipoMedicamentoSeleccionado);
        ResultSet rst2 = pst2.executeQuery();
        rst2.next();
        int idTipoMedicamento = rst2.getInt("id_tipos_productos");

            PreparedStatement psU = cn.prepareStatement("UPDATE productos SET id_tipos_productos = '" + rst2.getString("nombre") + "', tipo_presentacion='"+tiposeleccionado+"', seguro='"+seguroseleccionado+"', codigo_sugemi='"+codigo_sugemi.getText()+"',codigo_promese='"+codigo_promese.getText()+"',nombre_comercial='"+nombre_comercial.getText()+"',descripcion_comercial ='"+descripcion_comercial.getText()+"',stock='"+cantidad.getText()+"',precio_compra='"+precio_compra.getText()+"',precio_venta='"+precio_venta.getText()+"', concentracion='"+concentracion.getText()+"',itbis='"+itbis.getText()+"', reorden='"+reorden.getText()+"' where id_productos ='"+id_productos.getText()+"'");
            psU.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(empleado.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null,ex);
        }
        JOptionPane.showMessageDialog(null, "ACTUALIZADO CON EXITO");
    }
   void borrar_datos() {
             if(id_productos.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos");
        } else {
            String query = "UPDATE productos SET estado = 0 WHERE id_productos = '" + id_productos.getText() + "'";
            try{
                PreparedStatement psz = cn.prepareStatement(query);
                psz.executeUpdate();
                JOptionPane.showMessageDialog(null, "Medicamento eliminado");
                limpiar();
                cargar_datos();
            } catch(Exception error){
                JOptionPane.showMessageDialog(null, "Error: " + error);
            }
        }
   }
void guardar_datos() throws FileNotFoundException, SQLException {
    try {
       String seguroseleccionado;
            seguroseleccionado = seguro.getSelectedItem().toString();
                if ("SI".equals(seguroseleccionado)) {
                } else if ("NO".equals(seguroseleccionado)) {
                } 
        
        String tipoforma;
        tipoforma = presentacion.getSelectedItem().toString();
                        if ("TABLETAS".equals(tipoforma)) {
                }  else if ("CÁPSULAS".equals(tipoforma)) {
                }  else if ("JARABE".equals(tipoforma)) {
                }  else if ("SUSPENSIÓN".equals(tipoforma)) {
                }  else if ("GOTAS".equals(tipoforma)) {
                }  else if ("INYECCIÓN".equals(tipoforma)) {
                }  else if ("CREMA".equals(tipoforma)) {
                }  else if ("PARCHE TRANSDÉRMICO".equals(tipoforma)) {
                }  else if ("SUPOSITORIO".equals(tipoforma)) {
                }  else if ("AEROSOL".equals(tipoforma)) {
                }  else if ("POLVO".equals(tipoforma)) {
                }   
  
        String tiposeleccionado = tipo.getSelectedItem().toString();
        String tipoMedicamentoSeleccionado = tiposeleccionado; 
        String query_tipo_medicamento = "SELECT id_tipos_productos FROM tipos_productos WHERE nombre = ?";

        try (PreparedStatement pst2 = cn.prepareStatement(query_tipo_medicamento)) {
            pst2.setString(1, tipoMedicamentoSeleccionado);

            try (ResultSet rst2 = pst2.executeQuery()) {
                rst2.next();
                int idTipoMedicamento = rst2.getInt("id_tipos_productos");
                String sql = "INSERT INTO productos (nombre_comercial, descripcion_comercial, concentracion, stock, precio_compra, precio_venta, codigo_sugemi, codigo_promese, tipo_presentacion, id_tipos_medicamentos, itbis, reorden, seguro, ruta_img, imagen) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                     
               
                try (PreparedStatement psz = cn.prepareStatement(sql)) {
                    psz.setString(1, nombre_comercial.getText());
                    psz.setString(2, descripcion_comercial.getText());
                    psz.setString(3, concentracion.getText());
                    psz.setString(4, cantidad.getText());
                    psz.setString(5, precio_compra.getText());
                    psz.setString(6, precio_venta.getText());
                    psz.setString(7, codigo_sugemi.getText());
                    psz.setString(8, codigo_promese.getText());
                    psz.setString(9, tipoforma);
                    psz.setInt(10, idTipoMedicamento);
                    psz.setString(11, itbis.getText());
                    psz.setString(12, reorden.getText());
                    psz.setString(13, seguroseleccionado);
                    psz.setString(14, ruta_img.getText());
                    File file = new File(ruta_img.getText());            
                    FileInputStream archivofoto = new FileInputStream(file);
                    psz.setBinaryStream(15, archivofoto, (int) file.length());

            int n = psz.executeUpdate();

            if (n > 0) {
                JOptionPane.showMessageDialog(null, "GUARDADO CON ÉXITO");
            }
        }

    } catch (SQLException | FileNotFoundException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, ex);
    }
   }
  } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, ex);
}
}
    
void cargar_imagen() {
    try {
        String query = "SELECT imagen FROM productos WHERE id_productos = ?";
        PreparedStatement pst = cn.prepareStatement(query);
        pst.setString(1, id_productos.getText());
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            // Recupera la imagen en formato de bytes
            byte[] imgBytes = rs.getBytes("imagen");

            if (imgBytes != null) {
                // Convierte los bytes a una imagen
                ImageIcon imageIcon = new ImageIcon(imgBytes);
                // Escala la imagen si es necesario
                Image image = imageIcon.getImage().getScaledInstance(labelfoto.getWidth(), labelfoto.getHeight(), Image.SCALE_SMOOTH);
                // Asigna la imagen al JLabel
                labelfoto.setIcon(new ImageIcon(image));
            } else {
                // Si no hay imagen, puedes establecer una imagen de marcador de posición o dejar el JLabel en blanco
                labelfoto.setIcon(null);
            }
        }
    } catch (SQLException error) {
        JOptionPane.showMessageDialog(null, "Error: " + error);
    }
}


   void limpiar(){
        id_productos.setText("");
        nombre_comercial.setText("");
        codigo_sugemi.setText("");
        codigo_promese.setText("");
        descripcion_comercial.setText("");
        cantidad.setText("");
        precio_compra.setText("");
        precio_venta.setText("");
        presentacion.setSelectedIndex(0);
        tipo.setSelectedIndex(0);
        concentracion.setText("");
    }
void cargar_datos() {
    DefaultTableModel modelo2 = (DefaultTableModel) tabla_productos.getModel();
    modelo2.getDataVector().clear();
    String[] registros = new String[20];
    String sql = "SELECT pro.id_productos, pro.id_compuestos_medicamentos, pro.codigo_sugemi, pro.codigo_promese, pro.nombre_comercial,pro.seguro, pro.itbis, pro.reorden, pro.descripcion_comercial, pro.concentracion, pro.tipo_presentacion, pro.precio_compra, pro.precio_venta, pro.stock, tp.nombre AS tipo_producto " +
                 "FROM productos pro " +
                 "INNER JOIN tipos_productos tp ON pro.id_tipos_medicamentos = tp.id_tipos_productos " +
                 "LIMIT 100";

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
            registros[14] = rs.getString("seguro");
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
        jLabel2 = new javax.swing.JLabel();
        id_productos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        codigo_sugemi = new javax.swing.JTextField();
        codigo_promese = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nombre_comercial = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        descripcion_comercial = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        presentacion = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();
        precio_compra = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        precio_venta = new javax.swing.JTextField();
        tipo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_productos = new javax.swing.JTable();
        LIMPIAR = new javax.swing.JButton();
        ELIMINAR = new javax.swing.JButton();
        ACTUALIZAR = new javax.swing.JButton();
        AGREGAR = new javax.swing.JButton();
        SALIR = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        labelfoto = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        concentracion = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        itbis = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        seguro = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        reorden = new javax.swing.JTextField();
        LIMPIAR1 = new javax.swing.JButton();
        ruta_img = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 0, 204), null));

        jPanel2.setBackground(new java.awt.Color(12, 93, 172));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("REGISTRO DE MEDICAMENTO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(369, 369, 369)
                    .addComponent(jLabel8)
                    .addContainerGap(370, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel8)
                    .addContainerGap(11, Short.MAX_VALUE)))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(12, 93, 172));
        jLabel2.setText("ID PRODUCTO");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(12, 93, 172));
        jLabel3.setText("CODIGO SUGEMI");

        codigo_sugemi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigo_sugemiActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(12, 93, 172));
        jLabel4.setText("CODIGO PROMESE");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(12, 93, 172));
        jLabel5.setText("NOMBRE COMERCIAL");

        nombre_comercial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_comercialActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(12, 93, 172));
        jLabel6.setText("DESCRIPCION COMERCIAL");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(12, 93, 172));
        jLabel9.setText("PRESENTACION");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(12, 93, 172));
        jLabel10.setText("TIPO");

        presentacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "TABLETAS", "CÁPSULAS", "JARABE", "SUSPENSIÓN", "GOTAS", "INYECCIÓN", "CREMA", "PARCHE TRANSDÉRMICO", "SUPOSITORIO", "AEROSOL", "POLVO" }));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(12, 93, 172));
        jLabel11.setText("CANTIDAD");

        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });

        precio_compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precio_compraActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(12, 93, 172));
        jLabel12.setText("PRECIO COMPRA");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(12, 93, 172));
        jLabel13.setText("PRECIO VENTA");

        tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE" }));
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });

        tabla_productos.setBackground(new java.awt.Color(12, 93, 172));
        tabla_productos.setForeground(new java.awt.Color(255, 255, 255));
        tabla_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ", "SUGEMI", "PROMESE", "NOMBRE", "DESCRIPCION", "COMPUESTOS", "CONCENTRACION", "PRESENTACION", "STOCK", "COMPRA", "VENTA", "TIPO", "ITBIS", "REORDEN", "SEGURO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FOTO DEL MEDICAMENTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 173))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(250, 230));
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.add(labelfoto, java.awt.BorderLayout.PAGE_START);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(12, 93, 172));
        jLabel7.setText("CONCENTRACION");

        concentracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                concentracionActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(12, 93, 172));
        jLabel14.setText("ITBIS");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(12, 93, 172));
        jLabel15.setText("SEGURO");

        seguro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "SI", "NO" }));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(12, 93, 172));
        jLabel16.setText("REORDEN");

        LIMPIAR1.setBackground(new java.awt.Color(12, 93, 172));
        LIMPIAR1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LIMPIAR1.setForeground(new java.awt.Color(255, 255, 255));
        LIMPIAR1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/limpiar.png"))); // NOI18N
        LIMPIAR1.setText("SELECCIONAR IMAGEN");
        LIMPIAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPIAR1ActionPerformed(evt);
            }
        });

        ruta_img.setText("H:\\MediSync\\src\\Foto_Medicamentos\\");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(concentracion, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                    .addGap(212, 212, 212)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(descripcion_comercial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                                        .addComponent(id_productos, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(codigo_sugemi, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(codigo_promese, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(nombre_comercial, javax.swing.GroupLayout.Alignment.LEADING))))
                                            .addGap(29, 29, 29)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel11)
                                                .addComponent(jLabel12)
                                                .addComponent(jLabel13)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel9)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel14)
                                                                .addComponent(jLabel10))
                                                            .addGap(92, 92, 92)
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(presentacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(precio_compra)
                                                                .addComponent(precio_venta)
                                                                .addComponent(cantidad)
                                                                .addComponent(itbis)
                                                                .addComponent(tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel15)
                                                        .addComponent(jLabel16)))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(92, 92, 92)
                                            .addComponent(AGREGAR, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(ACTUALIZAR, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(ELIMINAR, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(LIMPIAR, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel7)
                                                    .addGap(47, 47, 47))
                                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(seguro, 0, 209, Short.MAX_VALUE)
                                        .addComponent(reorden))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(LIMPIAR1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(ruta_img, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(0, 13, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1882, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(5, 5, 5)))
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(id_productos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(seguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(codigo_sugemi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)
                                .addComponent(reorden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(codigo_promese, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)
                                .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(nombre_comercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addComponent(precio_compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(descripcion_comercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)
                                .addComponent(precio_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(concentracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(itbis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(32, 32, 32)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(AGREGAR)
                                .addComponent(ACTUALIZAR)
                                .addComponent(ELIMINAR)
                                .addComponent(LIMPIAR))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ruta_img, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(22, 22, 22))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(LIMPIAR1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(SALIR)
                    .addContainerGap())
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            pack();
            setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

    private void codigo_sugemiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigo_sugemiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigo_sugemiActionPerformed

    private void precio_compraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precio_compraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precio_compraActionPerformed

    private void LIMPIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPIARActionPerformed
limpiar(); 
    }//GEN-LAST:event_LIMPIARActionPerformed

    private void ELIMINARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELIMINARActionPerformed
   borrar_datos();
    limpiar(); 
   cargar_datos();
    }//GEN-LAST:event_ELIMINARActionPerformed

    private void ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACTUALIZARActionPerformed
    actualizar_datos();
    limpiar(); 
    cargar_datos();
    }//GEN-LAST:event_ACTUALIZARActionPerformed

    private void AGREGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AGREGARActionPerformed
        try {
            guardar_datos();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(registro_medicamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(registro_medicamento.class.getName()).log(Level.SEVERE, null, ex);
        }
limpiar(); 
cargar_datos();
    }//GEN-LAST:event_AGREGARActionPerformed

    private void SALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALIRActionPerformed
        this.dispose();
    }//GEN-LAST:event_SALIRActionPerformed

    private void nombre_comercialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_comercialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_comercialActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadActionPerformed

    private void concentracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_concentracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_concentracionActionPerformed

    private void tabla_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productosMouseClicked
     int fila= tabla_productos.getSelectedRow();
     if(fila>=0){
        DefaultTableModel table_model = (DefaultTableModel)tabla_productos.getModel();
        String id_tipo = (String) table_model.getValueAt(tabla_productos.getSelectedRow(), 11); 
        try{ 
            String query = "SELECT * FROM tipos_productos WHERE nombre = '" + id_tipo + "'";
            PreparedStatement pst = cn.prepareStatement(query);
            ResultSet rst = pst.executeQuery();
            rst.next();
            tipo.setSelectedItem(rst.getString("nombre"));
        } catch(Exception error){
            JOptionPane.showMessageDialog(null, "Error: "+error);
        }
        String presentacionseleccionado = (String) table_model.getValueAt(tabla_productos.getSelectedRow(), 7);
       presentacion.setSelectedItem(presentacionseleccionado);
    id_productos.setText((String) table_model.getValueAt(tabla_productos.getSelectedRow(), 0));
    nombre_comercial.setText((String) table_model.getValueAt(tabla_productos.getSelectedRow(),3));
    codigo_sugemi.setText((String) table_model.getValueAt(tabla_productos.getSelectedRow(), 1));
    codigo_promese.setText((String) table_model.getValueAt(tabla_productos.getSelectedRow(), 2));
    descripcion_comercial.setText((String) table_model.getValueAt(tabla_productos.getSelectedRow(), 4));
    cantidad.setText((String) table_model.getValueAt(tabla_productos.getSelectedRow(), 8));
    precio_compra.setText((String) table_model.getValueAt(tabla_productos.getSelectedRow(), 9));
    precio_venta.setText((String) table_model.getValueAt(tabla_productos.getSelectedRow(), 10));
    concentracion.setText((String) table_model.getValueAt(tabla_productos.getSelectedRow(), 6));
     }
     cargar_imagen();
    }//GEN-LAST:event_tabla_productosMouseClicked

    private void LIMPIAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPIAR1ActionPerformed

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos de Imágenes(*.JPG; *.PNG; *.JPEG)", "jpg", "jpeg", "png", "gif", "bmp", "webp");
JFileChooser archivo = new JFileChooser();
archivo.addChoosableFileFilter(filtro);
archivo.setDialogTitle("Abrir Archivo");

// Utiliza un directorio por defecto en lugar de uno fijo
File ruta = new File(System.getProperty("user.home")); 
archivo.setCurrentDirectory(ruta);

int ventana = archivo.showOpenDialog(null);

if (ventana == JFileChooser.APPROVE_OPTION) {
    File file = archivo.getSelectedFile();
    ruta_img.setText(file.getAbsolutePath()); // Usa getAbsolutePath() para obtener la ruta completa

    try {
        Image foto = ImageIO.read(file); // Utiliza ImageIO para leer la imagen

        if (foto != null) {
            foto = foto.getScaledInstance(250, 230, Image.SCALE_DEFAULT);
            labelfoto.setIcon(new ImageIcon(foto));
        } else {
            System.out.println("La imagen es nula o no válida.");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    }//GEN-LAST:event_LIMPIAR1ActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoActionPerformed

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
            java.util.logging.Logger.getLogger(registro_medicamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registro_medicamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registro_medicamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registro_medicamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registro_medicamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ACTUALIZAR;
    private javax.swing.JButton AGREGAR;
    private javax.swing.JButton ELIMINAR;
    private javax.swing.JButton LIMPIAR;
    private javax.swing.JButton LIMPIAR1;
    private javax.swing.JButton SALIR;
    private javax.swing.JTextField cantidad;
    private javax.swing.JTextField codigo_promese;
    private javax.swing.JTextField codigo_sugemi;
    private javax.swing.JTextField concentracion;
    private javax.swing.JTextField descripcion_comercial;
    private javax.swing.JTextField id_productos;
    private javax.swing.JTextField itbis;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelfoto;
    private javax.swing.JTextField nombre_comercial;
    private javax.swing.JTextField precio_compra;
    private javax.swing.JTextField precio_venta;
    private javax.swing.JComboBox<String> presentacion;
    private javax.swing.JTextField reorden;
    private javax.swing.JTextField ruta_img;
    private javax.swing.JComboBox<String> seguro;
    private javax.swing.JTable tabla_productos;
    private javax.swing.JComboBox<String> tipo;
    // End of variables declaration//GEN-END:variables
}
