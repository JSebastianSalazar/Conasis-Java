/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Aprendiz;
import java.io.IOException;
import java.io.PrintWriter;
import javax.mail.MessagingException;
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
public class RegistroAprendiz extends HttpServlet {

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
            String tipoDoc = request.getParameter("tipo_Documento");
            String Doc = request.getParameter("numero_documento");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String genero = request.getParameter("genero");
            String telefono_fijo = request.getParameter("telefono_fijo");
            String estrato = request.getParameter("estrato");
            String email = request.getParameter("email");
            String barrio1 = request.getParameter("barrio1");
            String Ficha = request.getParameter("Ficha");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario1", Doc);
            String asunto = "CONASIS Registro Aprendiz";
            String content = "Estimado " + nombre + "" + "" + apellido + " Tu registro fue exitoso con la ficha:" + "" + Ficha;
            Aprendiz ape = new Aprendiz();

            ape.setTipoDocumento(tipoDoc);
            ape.setDocumento(Doc);
            ape.setNombre(nombre);
            ape.setGenero(genero);
            ape.setCorreo(email);
            ape.setNumeroFicha(Ficha);
            ape.setFecha(fechaNacimiento);
            ape.setApellido(apellido);
            ape.setBarrio(barrio1);
            ape.setEstrato(estrato);
            ape.setTelefono(telefono_fijo);
            DAO.DAOUsuario dao = new DAO.DAOUsuario();
            System.out.println("antes de insertar ");
             int insertado = dao.insertarAprendiz(ape);
            if (!Doc.isEmpty() && !nombre.isEmpty() && !email.isEmpty() && !apellido.isEmpty()) {

               
                if (insertado == 0) {
                    System.out.println("enviado");
                    try {
                        EmailUtility.sendEmail(host, port, user, pass, email, asunto,
                                content);
                    } catch (MessagingException ex) {
                        System.out.println("no se envio el mensaje correctamente" + ex);
                    }

                } else {
                    response.setStatus(400);//errores del cliente
                    response.getWriter().print("Usuario ya registrado");
                }
            } else {
                response.setStatus(400);
                response.getWriter().print("No se permiten campos vacios");

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
