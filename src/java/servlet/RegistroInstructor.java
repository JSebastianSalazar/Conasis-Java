/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DAOUsuario;
import util.EmailUtility;
import DAO.Instructor;
import beans.InsertarInstructor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sebas
 */
public class RegistroInstructor extends HttpServlet {

    private String host;
    private String port;
    private String user;
    private String pass;
   

    @Override
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
      
    }

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
            System.out.println("hola soy yooo bienvenido");
            String Documento2 = request.getParameter("numero_documento2");
            String Documento = request.getParameter("numero_documento");
            String tipoDocumento = request.getParameter("tipo_Documento");
            String nombre = request.getParameter("nombre1");
            String apellido = request.getParameter("apellido1");
            String correo = request.getParameter("correo1");
            String telefono = request.getParameter("telefono_fijo");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String clave = request.getParameter("numero_documento");
            String validar = request.getParameter("validar");
            HttpSession session = request.getSession(true);
            System.out.println(Documento+" "+nombre+" "+correo);
            session.setAttribute("usuario1", Documento);
            InsertarInstructor ins = new InsertarInstructor();
            switch (validar) {
                case "inserta":

                    String asunto = "CONASIS Registro Instructor";
                    String content = "Estimado " + nombre + "" + "" + apellido + " Tu registro fue exitoso y recueda que tu clave y tu nombre de usuario es tu numero de documento ";

                    EncriptarDatos encrip = new EncriptarDatos();
                    clave = encrip.cifrarMD5(clave);

                    ins.setDocumento(Documento);
                    ins.setTipoDocumento(tipoDocumento);
                    ins.setNombre(nombre);
                    ins.setApellido(apellido);
                    ins.setCorreo(correo);
                    ins.setFecha(fechaNacimiento);
                    ins.setTelefono(telefono);
                    ins.setClave(clave);
                    Instructor dao = new Instructor();

                    if (!Documento.isEmpty() && !nombre.isEmpty() && !correo.isEmpty() && !apellido.isEmpty()) {
                        int insertado = dao.insertarUsuario(ins);
                        if (insertado == 0) {

                            try {
                                EmailUtility.sendEmail(host, port, user, pass, correo, asunto,
                                        content);
                            } catch (MessagingException ex) {
                                System.out.println("no se envio el mensaje correctamente" + ex);
                            }

                        } else if (insertado == 2) {
                            response.setStatus(400);
                            response.getWriter().print("El correo ya esta registrado");
                        } else if (insertado == 1) {
                            response.setStatus(400);
                            response.getWriter().print("El numero de documento ya esta registrado");
                        }
                    } else {
                        response.setStatus(400);
                        response.getWriter().print("No se permiten campos vacios");

                    }
                    break;
                case "eliminar":
                    DAOUsuario u = new DAOUsuario();
                    int i = u.eliminarInstructor(Documento);
                    if (i == 0) {
                        out.println("Se eliminó correctamente al usuario");
                    } else if (i == 1) {
                        response.sendError(400, "Usuario no encontrado");
                    } else {
                        response.sendError(400, "Ha ocurrido algo inesperado");
                    }
                    break;

                case "modificar":
                    session.setAttribute("usuario2", Documento);
                    ins.setDocumento(Documento);
                    ins.setTipoDocumento(tipoDocumento);
                    ins.setNombre(nombre);
                    ins.setApellido(apellido);
                    ins.setCorreo(correo);
                    ins.setFecha(fechaNacimiento);
                    ins.setTelefono(telefono);
                    ins.setClave(Documento2);
                    dao = new Instructor();
                    System.out.println(Documento);
                    if (!Documento.isEmpty() && !nombre.isEmpty() && !correo.isEmpty() && !apellido.isEmpty()) {
                        int insertado2 = dao.ActualizarInstructor(ins);

                        if (insertado2 == 0) {

                        } else if (insertado2 == 2) {
                            response.setStatus(400);
                            response.getWriter().print("El correo ya esta registrado");
                        } else if (insertado2 == 1) {
                            response.setStatus(400);
                            response.getWriter().print("El numero de documento no existe");
                        } else {
                            response.setStatus(400);
                            response.getWriter().print("Ha ocurrido un error inesperado");

                        }

                    } else {
                        response.setStatus(400);
                        response.getWriter().print("No se permiten campos vacios");

                    }

                    break;

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
