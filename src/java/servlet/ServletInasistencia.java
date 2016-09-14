/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DaoFicha;
import beans.Ficha;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sebas
 */
public class ServletInasistencia extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            
            String validacion;
            String idInstructor;
            List<Ficha> listaF;
            Ficha f;
            DaoFicha daoF;
            
            validacion = request.getParameter("validacion");
            idInstructor = request.getParameter("idInstructor");
            
             daoF = new DaoFicha();
             
             
             switch (validacion) {
                case "fichasInstructor"://listando programas
                    if(!idInstructor.isEmpty()){
                    listaF = daoF.fichasInstructor(Integer.parseInt(idInstructor));
                    if(listaF.isEmpty() || listaF == null){
                        out.println("<center><h4>Usted no tiene fichas asiciadas<h4></center>");
                }else{
                        for(int i = 0; i < listaF.size(); i++){
                            f = listaF.get(i);
                            out.println("<div class='col s2 center-align hoverable text-aling' id='"+f.getId()+"' name='cuadroFicha' onclick='idCuadroFicha(this)' style='background-color: #bbdefb; padding: 5px; background-clip: content-box'><h5>"+f.getNumeroFicha()+"</h5></div>");
                    
                        }
                    }
                    }else{
                       // out.println("No hay instructor logueado");
                         out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Error\",\n" +
"                                text: \"No se ha logueado un instructor\",\n" +
"                                type: \"error\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
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
