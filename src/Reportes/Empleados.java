package Reportes;


import com.itextpdf.text.Image;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import clases.Conectar;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Empleados {
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
            String filePath = "Reportes/Empleados/Empleados.pdf";

            // Verificar si el archivo ya existe
            File existingFile = new File(filePath);
            int counter = 1;
            while (existingFile.exists()) {
                // Si el archivo ya existe, agregar un contador al nombre del archivo
                filePath = "Reportes/Empleados/Empleados_" + counter + ".pdf";
                existingFile = new File(filePath);
                counter++;
            }

            PdfWriter.getInstance(_document, new FileOutputStream(filePath));
            _document.open();

            // Obtener el logo de la empresa desde la base de datos
            String query_logo = "SELECT logo_empresa FROM empresa WHERE id_empresa = 2";
            PreparedStatement psLogo = cn.prepareStatement(query_logo);
            ResultSet rsLogo = psLogo.executeQuery();
            if (rsLogo.next()) {
                byte[] logoBytes = rsLogo.getBytes("logo_empresa");
                Image logo;
                logo = Image.getInstance(logoBytes);
                logo.setAlignment(Element.ALIGN_RIGHT);
                logo.scaleToFit(150, 150); // Ajustar el tamaño del logo según tus preferencias
                _document.add((Element) logo);
            }

            // Datos de la empresa a la izquierda
            String query_company = "SELECT * FROM empresa";
            PreparedStatement psc = cn.prepareStatement(query_company);
            ResultSet rsc = psc.executeQuery();
            rsc.next();

            Font boldFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
            Font normalFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);

            Paragraph rnc = new Paragraph("RNC: " + rsc.getString("rnc"), normalFont);
            rnc.setAlignment(Element.ALIGN_LEFT);
            _document.add(rnc);

            Paragraph telefono = new Paragraph("TELEFONO: " + rsc.getString("telefono"), normalFont);
            telefono.setAlignment(Element.ALIGN_LEFT);
            _document.add(telefono);

            Paragraph email = new Paragraph("CORREO: " + rsc.getString("correo"), normalFont);
            email.setAlignment(Element.ALIGN_LEFT);
            _document.add(email);

            Paragraph direccion = new Paragraph("DIRECCION: " + rsc.getString("direccion"), normalFont);
            direccion.setAlignment(Element.ALIGN_LEFT);
            _document.add(direccion);

            // Título centralizado
            Paragraph title = new Paragraph("Lista de Empleados", boldFont);
            title.setAlignment(Element.ALIGN_CENTER);
            _document.add(title);

            // Espaciado
            _document.add(new Phrase("\n"));

            // Tabla de empleados
            PdfPTable _table = new PdfPTable(6); // Ajustado a 6 columnas según los campos que mencionaste
            _table.setWidthPercentage(100);

            _table.addCell(new Phrase("No.", boldFont));
            _table.addCell(new Phrase("Nombre", boldFont));
            _table.addCell(new Phrase("Apellidos", boldFont));
            _table.addCell(new Phrase("Cargo", boldFont));
            _table.addCell(new Phrase("Salario", boldFont));
            _table.addCell(new Phrase("Especialidad", boldFont));

            String query_employees = "SELECT p.nombre, p.apellido, c.nombre as Cargo_Empleado, e.sueldo, es.nombre FROM empleados e " +
                    "INNER JOIN personas p ON p.id_personas = e.id_personas " +
                    "INNER JOIN cargos_empleados c ON e.id_cargos_empleados = c.id_cargos_empleados " +
                    "LEFT JOIN especialidades es ON e.id_especialidades = es.id_especialidades";
            PreparedStatement psp = cn.prepareStatement(query_employees);
            ResultSet rsp = psp.executeQuery();
            int rowNum = 1;
            while (rsp.next()) {
                _table.addCell(new Phrase(String.valueOf(rowNum), normalFont));
                _table.addCell(new Phrase(rsp.getString("p.nombre"), normalFont));
                _table.addCell(new Phrase(rsp.getString("p.apellido"), normalFont));
                _table.addCell(new Phrase(rsp.getString("Cargo_Empleado"), normalFont));
                _table.addCell(new Phrase(rsp.getString("e.sueldo"), normalFont));
                _table.addCell(new Phrase(rsp.getString("es.nombre"), normalFont));
                rowNum++;
            }

            // Ajuste automático de anchos de columna
            _table.setWidths(new int[]{1, 2, 2, 2, 1, 2});

            _document.add(_table);
            _document.close();

            open_document(filePath);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error: " + error);
        }
    }
}
