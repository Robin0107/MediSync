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

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Citas {
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
            Document _document = new Document();

            // Ruta del archivo PDF
            String filePath = "Reportes/Citas/citas.pdf";

            // Verificar si el archivo ya existe
            File existingFile = new File(filePath);
            int counter = 1;
            while (existingFile.exists()) {
                // Si el archivo ya existe, agregar un contador al nombre del archivo
                filePath = "Reportes/Citas/citas_" + counter + ".pdf";
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
            
            Paragraph Reporte = new Paragraph("Reporte De Las Citas Del Dia",sub_font);
            Reporte.setAlignment(Element.ALIGN_CENTER);
            _document.add(Reporte);
            
            
            DecimalFormat frmt = new DecimalFormat();
            frmt.setMaximumFractionDigits(2);
            PdfPTable _table = new PdfPTable(4);
            _table.setTotalWidth(1000); // Puedes ajustar este valor según tus preferencias

            // Definir el ancho de cada columna (en porcentajes del ancho total)
            float[] columnWidths = {20f, 25f, 25f, 30f}; // Por ejemplo, 30% - 40% - 30%
            _table.setWidths(columnWidths);
            _table.addCell("Fecha");
            _table.addCell("Nombres");
            _table.addCell("Apellidos");
            _table.addCell("Nombre Departamento");
            
            
            String query_guarantee = "SELECT c.fecha, p.nombre, p.apellido, d.nombre as Nombre_Departamento FROM citas c INNER JOIN personas p ON p.id_personas = c.id_personas INNER JOIN departamentos d ON c.id_departamentos = d.id_departamentos;";
            PreparedStatement psp = cn.prepareStatement(query_guarantee);
            ResultSet rsp = psp.executeQuery();
            while(rsp.next()){
                _table.addCell(rsp.getString("c.fecha"));
                _table.addCell(rsp.getString("p.nombre"));
                _table.addCell(rsp.getString("p.apellido"));
                _table.addCell(rsp.getString("Nombre_Departamento"));
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
