/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DaoProgramaXcompetencia;
import beans.ProgramaXcompetencia;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sebas
 */
public class ServletProgramaXcompetencia extends HttpServlet {

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
            String idPrograma;
            String idCompetencia;
            String validacion;
            DaoProgramaXcompetencia daoPc;
            ProgramaXcompetencia pc;

            idPrograma = request.getParameter("idPrograma");//id lo resivo de cuando se da clic en un campo de la tabla
            validacion = request.getParameter("validacion");//servirá para ver que funcion se esta ejecutando
            idCompetencia = request.getParameter("idCompetencia");//recibe el id de la competencia
            daoPc = new DaoProgramaXcompetencia();
            pc = new ProgramaXcompetencia();
            
            switch(validacion){
                case "vAgrgar":
                    pc.setIdCompetencia(Integer.parseInt(idCompetencia));
                    pc.setIdPrograma(Integer.parseInt(idPrograma));
                    if (daoPc.insertar(pc)) {
                            //out.println("");
                            out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Perfecto\",\n" +
"                                text: \"¡Se agregó la competencia al programa!\",\n" +
"                                type: \"success\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                        } else {
                            //out.println("No se pudo asociar la competencia");
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Error\",\n" +
"                                text: \"¡No se pudo asociar la competencia!\",\n" +
"                                type: \"error\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    }
                    break;
                    case "vEliminar":
                        
                    if (daoPc.eliminar(Integer.parseInt(idPrograma), Integer.parseInt(idCompetencia))) {
                            out.println("<h5>Eliminado con exito</h5>");
                    }else{
                        out.println("<h5>No se eliminó</h5>");
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
