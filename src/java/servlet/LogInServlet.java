/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DAOUsuario;
import beans.Login;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sebas
 */
public class LogInServlet extends HttpServlet {

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
            Login log = new Login();

            List list = new ArrayList();

            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");
            String nombre;
            String foto;
            int c = 0;

            String id;
            if (usuario.isEmpty() && password.isEmpty()) {
                response.setStatus(400);
                out.println("Debe llenar todos los campos");
            } else {
                DAOUsuario dao = new DAOUsuario();
                HttpSession session = request.getSession(true);
                log.setUsuario(usuario);
                log.setClave(password);
                list = dao.logueo(log);
                if (list.isEmpty() || list == null) {
                    response.setStatus(400);
                    out.println("Ocurrio un error");
                } else {
//                   
                    if (list.get(0) == (Object) 1) {
                        // puede loguearse
                        if (list.get(4) == (Object) 1) {
                            System.out.println("admi entro");
                            nombre = list.get(2) + " " + list.get(3);
                            foto = list.get(1) + "";

                            session.setAttribute("usuario", nombre);
                            session.setAttribute("foto", foto);
                            id = (String) list.get(5);
                            session.setAttribute("idIns", id);
                            session.setAttribute("tipo", "administrador");

                            out.print("Admin");

                        } else if (list.get(4) == (Object) 2) {
                            // instru
                            nombre = list.get(2) + " " + list.get(3);
                            foto = list.get(1) + "";
                            id = (String) list.get(5);
                            session.setAttribute("usuario", nombre);
                            session.setAttribute("foto", foto);
                            session.setAttribute("idIns", id);
                            System.out.println(id);
                            session.setAttribute("tipo", "Instructor");
                            out.print("Instructor");
                        } else if (list.get(4) == (Object) 3) {
                            //secre
                            nombre = list.get(2) + " " + list.get(3);
                            foto = list.get(1) + "";
                            //id = Integer.parseInt((String) list.get(5));
                            session.setAttribute("usuario", nombre);
                            session.setAttribute("foto", foto);
                            //  session.setAttribute("id", id);
                            session.setAttribute("tipo", "Secretaria");
                            out.print("secretaria");
                        } else {
//                            response.setStatus(400);
//                            out.println("Ocurrio un error ");
                            response.sendError(400, "Ocurrio un error ");

                        }
                    } else if (list.get(0) == (Object) 0) {
                        // el usuario nop eiste
                        //response.setStatus(400);
                        out.println("Esta cuenta no se encutra en la base de datos");
                        response.sendError(400, "Esta cuanta no se encuentra en la base de datos");
                    } else if (list.get(0) == (Object) 2) {
                        response.setStatus(400);
//                        estado
                        out.println("Lo sentimos la cuenta la cual desea ingresar  se encuentra inactiva");
                        response.sendError(400, "Lo sentimos la cuenta la cual desea ingresar  se encuentra inactiva");
                    } else {
//                        response.setStatus(400);
                        response.sendError(400, "Ocurrio un error ");

                    }
                }

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
