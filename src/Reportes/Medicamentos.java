package Reportes;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import clases.Conectar;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Medicamentos {
    Conectar cc = new Conectar();
    Connection cn = (Connection) cc.conexion();

    private void open_document(String path) {
        try {
            File objectFile = new File(path);
            Desktop.getDesktop().open(objectFile);
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Hubo un error al abrir el archivo: " + error);
        }
    }

    public void generate_report() {
        try {
            Document _document = new Document(PageSize.A2.rotate());

            // Ruta del archivo PDF
            String filePath = "Reportes/Medicamentos/Medicamentos.pdf";

            // Verificar si el archivo ya existe
            File existingFile = new File(filePath);
            int counter = 1;
            while (existingFile.exists()) {
                // Si el archivo ya existe, agregar un contador al nombre del archivo
                filePath = "Reportes/Medicamentos/Medicamentos_" + counter + ".pdf";
                existingFile = new File(filePath);
                counter++;
            }

            PdfWriter.getInstance(_document, new FileOutputStream(filePath));
            _document.open();

            Paragraph _date = new Paragraph(LocalDate.now().toString());
            _date.setAlignment(Element.ALIGN_RIGHT);
            _document.add(_date);
            
            String query_company = "SELECT * FROM empresa";
            PreparedStatement psc = cn.prepareStatement(query_company);
            ResultSet rsc = psc.executeQuery();
            rsc.next();
            Font name_font = new Font();
            name_font.setSize(28);
            Font sub_font = new Font();
            sub_font.setSize(14);
            
            Paragraph razon_social = new Paragraph(rsc.getString("razon_social"), name_font);
            razon_social.setAlignment(Element.ALIGN_CENTER);
            _document.add(razon_social);
            
            
           
            
            
            Paragraph rnc = new Paragraph("RNC: " + rsc.getString("rnc"));
            rnc.setAlignment(Element.ALIGN_LEFT);
            _document.add(rnc);
            
            Paragraph telefono = new Paragraph("TELEFONO: " + rsc.getString("telefono"));
            telefono.setAlignment(Element.ALIGN_LEFT);
            _document.add(telefono);
            
            Paragraph email = new Paragraph("CORREO: " + rsc.getString("correo"));
            email.setAlignment(Element.ALIGN_LEFT);
            _document.add(email);
            
            Paragraph direccion = new Paragraph("DIRECCION: " + rsc.getString("direccion"));
            direccion.setAlignment(Element.ALIGN_LEFT);
            _document.add(direccion);
            
            Paragraph Reporte = new Paragraph("Reporte De Los Medicamentos", sub_font);
            Reporte.setAlignment(Element.ALIGN_CENTER);
            _document.add(Reporte);
            
           DecimalFormat frmt = new DecimalFormat();
            frmt.setMaximumFractionDigits(2);
            PdfPTable _table = new PdfPTable(10);
            float[] columnWidths = {25f, 25f, 25f, 35f, 20f, 25f, 25f, 25f,30f,30f}; // Por ejemplo, 30% - 40% - 30%
            _table.setWidths(columnWidths);
            _table.addCell("Codigo_promese");
            _table.addCell("Codigo_sugemi");
            _table.addCell("Concentracion");
            _table.addCell("Descripcion_comercial");
            _table.addCell("Descripcion");
            _table.addCell("Tipo_producto");
            _table.addCell("Nombre_Comercial");
            _table.addCell("Precio de Compra");
            _table.addCell("Precio de Venta");
            _table.addCell("Stock");

            String query_guarantee = "SELECT \n" +
                "    pro.codigo_promese,\n" +
                "    pro.codigo_sugemi,\n" +
                "    pro.concentracion,\n" +
                "    pro.descripcion_comercial,\n" +
                "    co.descripcion as descripcion_compuesto,\n" +
                "    TP.nombre as tipo_producto,\n" +
                "    pro.nombre_comercial,\n" +
                "    pro.precio_compra,\n" +
                "    pro.precio_venta,\n" +
                "    pro.stock \n" +
                "FROM \n" +
                "    productos pro \n" +
                "    LEFT JOIN compuestos_medicamentos com ON com.id_productos = pro.id_productos\n" +
                "    LEFT JOIN compuestos co ON com.id_compuestos = co.id_compuestos\n" +
                "    LEFT JOIN tipos_productos TP ON TP.id_tipos_productos = pro.id_tipos_medicamentos;";

            try (PreparedStatement psp = cn.prepareStatement(query_guarantee);
                ResultSet rsp = psp.executeQuery()) {

                while (rsp.next()) {
                    _table.addCell(rsp.getString("pro.codigo_promese"));
                    _table.addCell(rsp.getString("pro.codigo_sugemi"));
                    _table.addCell(rsp.getString("pro.concentracion"));
                    _table.addCell(rsp.getString("pro.descripcion_comercial"));
                    _table.addCell(rsp.getString("descripcion_compuesto"));
                    _table.addCell(rsp.getString("tipo_producto"));
                    _table.addCell(rsp.getString("pro.nombre_comercial"));
                    _table.addCell(rsp.getString("pro.precio_compra"));
                    _table.addCell(rsp.getString("pro.precio_venta"));
                    _table.addCell(rsp.getString("pro.stock"));
                    
                    
                }
            } catch (SQLException e) {
                // Manejar la excepción (por ejemplo, imprimir el error)
                e.printStackTrace();
            }

            
            
            
            _document.add(new Phrase(""));
            _document.add(new Phrase(""));
            _document.add(_table);
            _document.add(new Phrase(""));
            _document.close();

            open_document(filePath);
        } catch (DocumentException | FileNotFoundException | NumberFormatException | SQLException error) {
            JOptionPane.showMessageDialog(null, "Hubo un error al generar el reporte del inventario: " + error);
        }
    }
}
