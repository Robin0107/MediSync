
package Formularios;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import clases.Conectar;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class registro_pedidos extends javax.swing.JFrame {
Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();
  
    public registro_pedidos() {
        initComponents();
        id_pedidos();
        cargar_tipo_factura();
        cargar_empleado();
                ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            LocalDateTime fechaactual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            fecha.setText(fechaactual.format(formato));
        },0,1,TimeUnit.SECONDS);
          setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    
    
    void cargar_empleado() {
    int idEmpleados = login.idEmpleado;

    try {
        String queryEmpleado = "SELECT id_personas FROM empleados WHERE id_empleados = ?";
        try (PreparedStatement pstEmpleado = cn.prepareStatement(queryEmpleado)) {
            pstEmpleado.setInt(1, idEmpleados);
            ResultSet rsEmpleado = pstEmpleado.executeQuery();

            if (rsEmpleado.next()) {
                int idPersonas = rsEmpleado.getInt("id_personas");

                String queryPersona = "SELECT nombre, apellido FROM personas WHERE id_personas = ?";
                try (PreparedStatement pstPersona = cn.prepareStatement(queryPersona)) {
                    pstPersona.setInt(1, idPersonas);
                    ResultSet rsPersona = pstPersona.executeQuery();

                    if (rsPersona.next()) {
                        String nombre = rsPersona.getString("nombre");
                        String apellido = rsPersona.getString("apellido");

                        nombre_empleado.setText(nombre + " " + apellido);
                        id_empleado.setText(String.valueOf(idEmpleados));
                    }
                }
            }
        }
    } catch (SQLException error) {
        JOptionPane.showMessageDialog(null, "Error al cargar empleado: " + error.getMessage());
    }
}

    
    
    void id_pedidos() {
    try {
       
        String query = "SELECT MAX(id_pedidos_proveedores) AS ultimo_id FROM pedidos_proveedores";
        Statement statement = cn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            int ultimoId = resultSet.getInt("ultimo_id");
            int siguienteId = ultimoId + 1;
            id_pedido.setText(String.valueOf(siguienteId));
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "ERROR\n" + ex.getMessage());
    }
}
    void llenar_datos(){
    DecimalFormat formateador = new DecimalFormat("##########.##");
    DefaultTableModel modelo2 = (DefaultTableModel) tabla_medicamentos.getModel();
    String[] registros = new String[10];
    
      float precio2 = Float.parseFloat(precio_producto.getText());
    float cantidad2 = Float.parseFloat(cantidad.getText());
    float itbisproducto = Float.parseFloat(itbis_producto.getText());
    float total2 = precio2 * cantidad2;
    float itbis2 = total2 * (itbisproducto / 100);
    float sub_total2 = total2 + itbis2;
    
    registros[0] = id_medicamento.getText();
    registros[1] = nombre_medicamento.getText();
    registros[2] = cantidad.getText();
    registros[3] = precio_producto.getText();
    registros[5] = formateador.format(itbis2);
    registros[4] = formateador.format(sub_total2);
    registros[6] = formateador.format(total2);
    modelo2.addRow(registros);
    tabla_medicamentos.setModel(modelo2);
}
    void sumar_productos(){
        DecimalFormat formateador = new DecimalFormat("#,###,###,###.##");
        DecimalFormat formateador2 = new DecimalFormat("##########.##");
        DefaultTableModel modelo2 = (DefaultTableModel)tabla_medicamentos.getModel();
        float totalg=0,totalg2=0;
        float itbisg=0,itbisg2=0;
        float subtotalg=0,subtotalg2=0;
        
        for (int i = 0; i < tabla_medicamentos.getRowCount(); i++){
            totalg=Float.parseFloat(tabla_medicamentos.getValueAt(i, 6).toString());
            totalg2=totalg2+totalg;
            itbisg=Float.parseFloat(tabla_medicamentos.getValueAt(i, 5).toString());
            itbisg2=itbisg2+itbisg;
            subtotalg=Float.parseFloat(tabla_medicamentos.getValueAt(i, 4).toString());
            subtotalg2=subtotalg2+subtotalg;            
        }
        total_general.setText(formateador.format(subtotalg2));
        itbis_general.setText(formateador.format(itbisg2));
        subtotal_general.setText(formateador.format(totalg2));
        total2=(formateador2.format(totalg2));
        itbis2=(formateador2.format(itbisg2));
        sub_total2=(formateador2.format(subtotalg2));
 

     }
    void cargar_tipo_factura(){
        try{
            String query = "SELECT * FROM tipos_facturas WHERE estado = 1";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                cbxtipo_factura.addItem(rs.getString("descripcion"));
            }
        } catch (Exception error){
            JOptionPane.showMessageDialog(null,"Error: " + error);
        }
    }
    void guardar_pedido(){
        try {
               String id_tipofactura = "0";
                String query_especialidad = "SELECT * FROM tipos_facturas WHERE descripcion = '" + cbxtipo_factura.getSelectedItem() + "'";
                PreparedStatement thp = cn.prepareStatement(query_especialidad);
                ResultSet rst1 = thp.executeQuery();
                rst1.next();
                id_tipofactura = rst1.getString("id_tipos_facturas");
                
            String sql2 = "";
            sql2 = "INSERT INTO pedidos_proveedores (id_tipos_facturas,subtotal,itbis,total,id_empleados,id_proveedores)VALUES('" + id_tipofactura + "','" + sub_total2 +"','" + itbis2 +"','" + total2 +"','" + id_empleado.getText() + "','" + id_proveedor.getText() + "')";
            
            PreparedStatement psz = cn.prepareStatement(sql2);

            int n;
            n = psz.executeUpdate();
            if (n > 0) {

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }       
    }
    void guardar_detalle_pedido(){
      
        for (int i = 0; i < tabla_medicamentos.getRowCount(); i++){
         try {
            String sql2 = "";
            sql2 = "INSERT INTO detalles_pedidos (id_pedidos_proveedores,id_productos,cantidad,precio_unitario,subtotal,itbis,total)VALUES('" + id_pedido.getText() + "','" + tabla_medicamentos.getValueAt(i,0).toString()+ "','" + tabla_medicamentos.getValueAt(i,2).toString()+"','" + tabla_medicamentos.getValueAt(i,3).toString()+"','" + tabla_medicamentos.getValueAt(i,4).toString() +"','" + tabla_medicamentos.getValueAt(i,5).toString()+"','" + tabla_medicamentos.getValueAt(i,6).toString()+ "')";
            
            PreparedStatement psz = cn.prepareStatement(sql2);

            int n;
            n = psz.executeUpdate();
            if (n > 0) {

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        salir1 = new javax.swing.JButton();
        DATOS_PACIENTE = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        nombre_compania = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        nombre_contacto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        id_proveedor = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        rnc = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_medicamentos = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        subtotal_general = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        itbis_general = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        total_general = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        id_pedido = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        fecha = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        id_empleado = new javax.swing.JTextField();
        nombre_empleado = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbxtipo_factura = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        nombre_medicamento = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        precio_producto = new javax.swing.JTextField();
        itbis_producto = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        id_medicamento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 153), null));

        jPanel4.setBackground(new java.awt.Color(12, 93, 172));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("REGISTRO DE PEDIDO");

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
                .addGap(596, 596, 596)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salir1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salir1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DATOS_PACIENTE.setBackground(new java.awt.Color(255, 255, 255));
        DATOS_PACIENTE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DEL PROVEEDOR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(12, 93, 172));
        jLabel6.setText("COMPAÑIA");

        nombre_compania.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(12, 93, 172));
        jLabel7.setText("NOMBRE CONTACTO");

        nombre_contacto.setEditable(false);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(12, 93, 172));
        jLabel12.setText("TELEFONO");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(12, 93, 172));
        jLabel8.setText("ID PROVEEDOR");

        id_proveedor.setEditable(false);

        telefono.setEditable(false);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(12, 93, 172));
        jLabel25.setText("RNC");

        rnc.setEditable(false);

        jButton1.setBackground(new java.awt.Color(12, 93, 172));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SELECCIONAR PROVEEDOR");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DATOS_PACIENTELayout = new javax.swing.GroupLayout(DATOS_PACIENTE);
        DATOS_PACIENTE.setLayout(DATOS_PACIENTELayout);
        DATOS_PACIENTELayout.setHorizontalGroup(
            DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DATOS_PACIENTELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel25)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(telefono, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                    .addComponent(nombre_contacto, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                    .addComponent(rnc)
                    .addComponent(nombre_compania)
                    .addComponent(id_proveedor))
                .addContainerGap())
            .addGroup(DATOS_PACIENTELayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jButton1)
                .addContainerGap(266, Short.MAX_VALUE))
        );
        DATOS_PACIENTELayout.setVerticalGroup(
            DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DATOS_PACIENTELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(id_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(nombre_compania, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(rnc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(nombre_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DATOS_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODUCTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        tabla_medicamentos.setAutoCreateRowSorter(true);
        tabla_medicamentos.setBackground(new java.awt.Color(12, 93, 173));
        tabla_medicamentos.setForeground(new java.awt.Color(255, 255, 255));
        tabla_medicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "PRODUCTO", "CANTIDAD", "PRECIO U.", "SUBTOTAL", "ITBIS", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabla_medicamentos);
        if (tabla_medicamentos.getColumnModel().getColumnCount() > 0) {
            tabla_medicamentos.getColumnModel().getColumn(0).setMinWidth(100);
            tabla_medicamentos.getColumnModel().getColumn(0).setMaxWidth(100);
            tabla_medicamentos.getColumnModel().getColumn(2).setMinWidth(150);
            tabla_medicamentos.getColumnModel().getColumn(2).setMaxWidth(150);
            tabla_medicamentos.getColumnModel().getColumn(3).setMinWidth(150);
            tabla_medicamentos.getColumnModel().getColumn(3).setMaxWidth(150);
            tabla_medicamentos.getColumnModel().getColumn(4).setMinWidth(150);
            tabla_medicamentos.getColumnModel().getColumn(4).setMaxWidth(150);
            tabla_medicamentos.getColumnModel().getColumn(5).setMinWidth(150);
            tabla_medicamentos.getColumnModel().getColumn(5).setMaxWidth(150);
            tabla_medicamentos.getColumnModel().getColumn(6).setMinWidth(150);
            tabla_medicamentos.getColumnModel().getColumn(6).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setBackground(new java.awt.Color(12, 93, 172));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("IMPRIMIR");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(12, 93, 172));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("GUARDAR PEDIDO");
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(12, 93, 172));
        jLabel20.setText("SUB TOTAL");

        subtotal_general.setEditable(false);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(12, 93, 172));
        jLabel21.setText("ITBIS");

        itbis_general.setEditable(false);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(12, 93, 172));
        jLabel23.setText("TOTAL");

        total_general.setEditable(false);
        total_general.setBackground(new java.awt.Color(0, 153, 255));
        total_general.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DE PEDIDO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(12, 93, 172));
        jLabel17.setText("#PEDIDO");

        id_pedido.setEditable(false);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(12, 93, 172));
        jLabel18.setText("FECHA");

        fecha.setEditable(false);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(12, 93, 172));
        jLabel19.setText("ID EMPLEADO");

        id_empleado.setEditable(false);
        id_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_empleadoActionPerformed(evt);
            }
        });

        nombre_empleado.setEditable(false);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(12, 93, 172));
        jLabel24.setText("EMPLEADO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id_pedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(id_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addComponent(nombre_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(12, 93, 172));
        jLabel16.setText("TIPO DE FACTURA");

        cbxtipo_factura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxtipo_facturaActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BUSCAR PRODUCTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 93, 172))); // NOI18N

        jButton4.setBackground(new java.awt.Color(12, 93, 172));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("BUSCAR");
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(12, 93, 172));
        jLabel26.setText("NOMBRE");

        nombre_medicamento.setEditable(false);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(12, 93, 172));
        jLabel27.setText("PRECIO U");

        precio_producto.setEditable(false);

        itbis_producto.setEditable(false);
        itbis_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itbis_productoActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(12, 93, 172));
        jLabel28.setText("ITBIS");

        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(12, 93, 172));
        jLabel29.setText("CANTIDAD");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(12, 93, 172));
        jLabel30.setText("CODIGO");

        id_medicamento.setEditable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(id_medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(nombre_medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(precio_producto, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(itbis_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jLabel26)
                        .addComponent(nombre_medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27)
                        .addComponent(precio_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itbis_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28)
                        .addComponent(jLabel30)
                        .addComponent(id_medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(DATOS_PACIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(cbxtipo_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(122, 122, 122)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(total_general, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(72, 72, 72)
                                .addComponent(itbis_general, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(subtotal_general, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DATOS_PACIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(cbxtipo_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(subtotal_general, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(itbis_general, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total_general, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void salir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salir1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_salir1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       guardar_pedido();
       guardar_detalle_pedido();
    JOptionPane.showMessageDialog(null, "GUARDADO CON ÉXITO");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            new listado_proveedor().setVisible(true); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbxtipo_facturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxtipo_facturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxtipo_facturaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new medicamento_pedido().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void itbis_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itbis_productoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itbis_productoActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        if (cantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LA CANTIDAD NO PUEDE ESTAR EN BLANCO");
            cantidad.requestFocus(true);
            return;
        }
        llenar_datos();
        sumar_productos();
    }//GEN-LAST:event_cantidadActionPerformed

    private void id_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_empleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_empleadoActionPerformed

    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registro_pedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DATOS_PACIENTE;
    public static javax.swing.JTextField cantidad;
    public static javax.swing.JComboBox<String> cbxtipo_factura;
    public static javax.swing.JTextField fecha;
    public static javax.swing.JTextField id_empleado;
    public static javax.swing.JTextField id_medicamento;
    public static javax.swing.JTextField id_pedido;
    public static javax.swing.JTextField id_proveedor;
    public static javax.swing.JTextField itbis_general;
    public static javax.swing.JTextField itbis_producto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTextField nombre_compania;
    public static javax.swing.JTextField nombre_contacto;
    public static javax.swing.JTextField nombre_empleado;
    public static javax.swing.JTextField nombre_medicamento;
    public static javax.swing.JTextField precio_producto;
    public static javax.swing.JTextField rnc;
    private javax.swing.JButton salir1;
    public static javax.swing.JTextField subtotal_general;
    private javax.swing.JTable tabla_medicamentos;
    public static javax.swing.JTextField telefono;
    public static javax.swing.JTextField total_general;
    // End of variables declaration//GEN-END:variables
    String tipo_factura="";
    String total2 = "", itbis2 = "", sub_total2 = "";
}
