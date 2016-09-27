/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.DAOUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.EmailUtility;

/**
 *
 * @author sebas
 */
public class RecuperarClave extends HttpServlet {

    private String host;
    private String port;
    private String user;
    private String pass;

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOUsuario dao = new DAOUsuario();
            String correo = request.getParameter("correo");
            RecuperarClave1 rep = new RecuperarClave1();
            rep.setCorreo(correo);
            HttpSession session = request.getSession(true);
            session.setAttribute("mail", correo);
            System.out.println(session);
            String resultMessage = "";
            try {
                if (!correo.isEmpty()) {
                    if (dao.consultarCorreo(rep)) {

                        String asunto = "CONASIS recuperacion de clave";
                        String content = "Click en el siguiente link para continuar con el proceso    http://localhost:8080/ConasisResponsive/MensajeRecuperar.jsp";
                        EmailUtility.sendEmail(host, port, user, pass, correo, asunto,
                                content);
                        response.sendRedirect("index.html");
                    } else {
                       response.setStatus(400, resultMessage = "USUARIO NO REGISTRADO");

                    }

                } else {
                    response.setStatus(400, resultMessage = "El campo esta vacio");
                }

            } catch (Exception ex) {
                response.setStatus(400, resultMessage = "COMPRUEBA TU CONEXIÓN");
            } finally {
                request.setAttribute("Message", resultMessage);
//                getServletContext().getRequestDispatcher("/Result.jsp").forward(
//                        request, response);
            }

            //out.println(resultMessage);
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
