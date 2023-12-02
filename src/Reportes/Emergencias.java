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
import static com.itextpdf.text.PageSize.A3;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Emergencias {
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
            String filePath = "Reportes/Emergencias/Emergencias.pdf";

            // Verificar si el archivo ya existe
            File existingFile = new File(filePath);
            int counter = 1;
            while (existingFile.exists()) {
                // Si el archivo ya existe, agregar un contador al nombre del archivo
                filePath = "Reportes/Emergencias/Emergencias_" + counter + ".pdf";
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
            
            Paragraph Reporte = new Paragraph("Reporte De Emergencias",sub_font);
            Reporte.setAlignment(Element.ALIGN_CENTER);
            _document.add(Reporte);
            
            
            DecimalFormat frmt = new DecimalFormat();
            frmt.setMaximumFractionDigits(2);
            PdfPTable _table = new PdfPTable(6);
            _table.setTotalWidth(1000); // Puedes ajustar este valor según tus preferencias

            // Definir el ancho de cada columna (en porcentajes del ancho total)
            float[] columnWidths = {20f, 20f, 25f, 25f ,40f,40f}; // Por ejemplo, 30% - 40% - 30%
            _table.setWidths(columnWidths);
            _table.addCell("Fecha");
            _table.addCell("Sala");
            _table.addCell("Paciente");
            _table.addCell("Doctor");
            _table.addCell("Motivo de la Emergencia");
            _table.addCell("Observaciones");
            
            String query_guarantee = "SELECT em.fecha, s.codigo, CONCAT_WS(' ', p1.nombre, p1.apellido) as Nombre_Paciente, CONCAT_WS(' ', p2.nombre, p2.apellido) as Nombre_Empleado, em.motivo_emergencia, em.observaciones, pro.codigo_promese, pro.codigo_sugemi, pro.nombre_comercial, pro.concentracion, pro.tipo_presentacion FROM emergencias em INNER JOIN empleados empl ON empl.id_empleados = em.id_empleados INNER JOIN detalles_emergencias de_emerg ON em.id_emergencias = de_emerg.id_emergencias INNER JOIN productos pro ON de_emerg.id_productos = pro.id_productos INNER JOIN salas s ON em.id_salas = s.id_salas INNER JOIN pacientes pac ON pac.id_pacientes = em.id_pacientes INNER JOIN personas p1 ON p1.id_personas = pac.id_personas INNER JOIN personas p2 ON empl.id_personas = p2.id_personas;";
            
            PreparedStatement psp = cn.prepareStatement(query_guarantee);
            ResultSet rsp = psp.executeQuery();
            while(rsp.next()){
                _table.addCell(rsp.getString("em.fecha"));
                _table.addCell(rsp.getString("s.codigo"));
                _table.addCell(rsp.getString("Nombre_Paciente"));
                _table.addCell(rsp.getString("Nombre_Empleado"));
                _table.addCell(rsp.getString("em.motivo_emergencia"));
                _table.addCell(rsp.getString("em.observaciones"));
            }

            
            
             PdfPTable _table1 = new PdfPTable(5);
            _table1.setTotalWidth(1000); // Puedes ajustar este valor según tus preferencias

            // Definir el ancho de cada columna (en porcentajes del ancho total)
            float[] columnWidths1 = {20f, 20f, 25f, 25f ,30f}; // Por ejemplo, 30% - 40% - 30%
            _table1.setWidths(columnWidths1);
            _table1.addCell("Codgigo Promese");
            _table1.addCell("Codgigo Sugemi");
            _table1.addCell("Medicamento");
            _table1.addCell("Concentracion");
            _table1.addCell("Tipo de Presentacion");

            // Usar un nuevo ResultSet para la segunda consulta
            ResultSet rsp1 = psp.executeQuery();
            while(rsp1.next()){
                _table1.addCell(rsp1.getString("pro.codigo_promese"));
                _table1.addCell(rsp1.getString("pro.codigo_sugemi"));
                _table1.addCell(rsp1.getString("pro.nombre_comercial"));
                _table1.addCell(rsp1.getString("pro.concentracion"));
                _table1.addCell(rsp1.getString("pro.tipo_presentacion"));
            }
            _document.add(new Phrase(""));
            _document.add(new Phrase(""));
            _document.add(_table);
            _document.add(new Phrase(""));
            _document.add(new Paragraph(" "));
            Paragraph Reporte1 = new Paragraph("Reporte Detalles Emergencias",sub_font);
            Reporte1.setAlignment(Element.ALIGN_CENTER);
            _document.add(Reporte1);
            _document.add(new Paragraph(" "));
            _document.add(_table1);
            _document.add(new Phrase(""));
            _document.close();

            open_document(filePath);
        } catch (DocumentException | FileNotFoundException | NumberFormatException | SQLException error) {
            JOptionPane.showMessageDialog(null, "Hubo un error al generar el reporte del inventario: " + error);
        }
    }
}
