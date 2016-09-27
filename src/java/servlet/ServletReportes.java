/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sebas
 */
public class ServletReportes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("application/pdf");
            OutputStream out = response.getOutputStream();
            HttpSession session = request.getSession(true);

            String numeroFicha;
            String genero0 = "" + session.getAttribute("genero0");
            String genero1 = "" + session.getAttribute("genero1");
            numeroFicha = "" + session.getAttribute("fichaN");
            System.out.println("soy genero o" + genero0);
            System.out.println("soy genero1" + genero1);
            System.out.println("soy ficha" + numeroFicha);
            Image imagenes = Image.getInstance("C:\\Users\\sebas\\Desktop\\final\\conasis\\web\\imagenes\\conasis2.png");

            imagenes.setAlignment(Element.ALIGN_TOP);//tamaño de la imagen
            imagenes.setAlignment(Element.ALIGN_LEFT);//tamaño de la imagen

            imagenes.scaleToFit(150, 150);//agg imagen al documento

            Document documento = new Document();
            PdfWriter.getInstance(documento, out);
            documento.open();
            Paragraph par1 = new Paragraph();
            Font fondescripcion1 = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD, BaseColor.BLACK);
            par1.add(new Phrase("Género", fondescripcion1));
            par1.add(new Phrase(Chunk.NEWLINE));// salto de linea
            par1.add(new Phrase(Chunk.NEWLINE));
            par1.add(new Phrase(Chunk.NEWLINE));
            par1.add(new Phrase("Ficha "+""+numeroFicha, fondescripcion1));
            par1.setAlignment(Element.ALIGN_CENTER);
            par1.add(new Phrase(Chunk.NEWLINE));// salto de linea
            par1.add(new Phrase(Chunk.NEWLINE));
            par1.add(new Phrase(Chunk.NEWLINE));// salto de linea
            par1.add(new Phrase(Chunk.NEWLINE));
            par1.add(new Phrase(Chunk.NEWLINE));// salto de linea
            par1.add(new Phrase(Chunk.NEWLINE));
            par1.add(new Phrase(Chunk.NEWLINE));// salto de linea
            par1.add(new Phrase(Chunk.NEWLINE));

            Paragraph par2 = new Paragraph();
            Font fondescripcion = new Font(Font.FontFamily.ZAPFDINGBATS, 28, Font.NORMAL, BaseColor.DARK_GRAY);// texto de a descripcion
            par2.add(new Phrase("Reporte Genero" + fondescripcion));
            par2.setAlignment(Element.ALIGN_JUSTIFIED);// justifica el texto
            par2.add(new Phrase(Chunk.NEWLINE));
            par2.add(new Phrase(Chunk.NEWLINE));
            par2.add(new Phrase(Chunk.NEWLINE));//saltos de linea
            par2.add(new Phrase(Chunk.NEWLINE));
            PdfPTable tabla = new PdfPTable(2);//( Numero de columnas de la tabla)
            //columnas de la tabla, cabezera y agg un estilo
            PdfPCell celda1 = new PdfPCell(new Paragraph("Hombre", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda2 = new PdfPCell(new Paragraph("Mujer ", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK)));

            //AGREGAR COLUMNA A LA TABLA
            tabla.addCell(celda1);
            tabla.addCell(celda2);
            Paragraph par3 = new Paragraph();
            par3.add(new Phrase(Chunk.NEWLINE));
            par3.add(new Phrase(Chunk.NEWLINE));
            par3.add(new Phrase(Chunk.NEWLINE));//saltos de linea
            par3.add(new Phrase(Chunk.NEWLINE));
            tabla.addCell(genero0);
            tabla.addCell(genero1);
            documento.add(imagenes);
            documento.add(par1);
            documento.add(tabla);

            //cerrar el documento
            documento.close();
            //cerrar el documento

        } catch (DocumentException ex) {
            System.out.println("ERROR PDF" + ex);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
