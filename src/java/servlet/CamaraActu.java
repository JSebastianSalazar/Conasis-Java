/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.Instructor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.misc.BASE64Decoder;

/**
 *
 * @author sebas
 */
public class CamaraActu extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           HttpSession session = request.getSession(true);
            String foto = (String) session.getAttribute("usuario2");
            System.out.println("act"+foto);
            try {
                StringBuffer buffer = new StringBuffer();
                Reader reader = request.getReader();
                int current;

                while ((current = reader.read()) >= 0) {
                    buffer.append((char) current);
                }

                String data = new String(buffer);
                data = data.substring(data.indexOf(",") + 1);

                System.out.println("PNG image data on Base64: " + data);
                out.println("");
                File rut = new File("Img/" + foto + ".png");
                FileOutputStream output = new FileOutputStream("C:\\Users\\sebas\\Desktop\\conasis\\web\\" + rut);
                String ruta = "" + rut;
                output.write(new BASE64Decoder().decodeBuffer(data));
                output.flush();
                output.close();
                System.out.println(ruta);
                System.out.println(foto);
                Instructor dao = new Instructor();

                if (dao.foto(ruta, foto)) {

                } else {
                    response.setStatus(400);
                    response.getWriter().print("No hay foto");
                }
                System.out.println(ruta);

            } catch (Exception e) {
                e.printStackTrace();
            }
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
