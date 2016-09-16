/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DaoAmbiente;
import beans.Ambiente;
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
public class ServletAmbiente extends HttpServlet {

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
           
            String validacion;
            String tipo;
            String capacidad;
            String suministros;
            String observacion;
            String idAmbiente;
            String json;
            Ambiente a;
            List<Ambiente> listaA;
            DaoAmbiente daoA;
           
            validacion = request.getParameter("validacion");
            tipo = request.getParameter("tipo");
            capacidad = request.getParameter("capacidad");
            suministros = request.getParameter("suministros");
            observacion = request.getParameter("observacion");
            idAmbiente = request.getParameter("idAmbiente");
            
            daoA = new DaoAmbiente();
            a = new Ambiente();
            System.out.println("AmbienteServlet");
            switch(validacion){
                case "listaAmbienteTbl":
                   listaA = daoA.ambientes();
                    if (!listaA.isEmpty() || listaA != null) {
                        
                        out.println("<div></div>");
                        out.println("<table class='highlight bordered linea col s12' id='tblAmbiente'>");
                        out.println("<thead>");
                        out.println("<tr>");
                        out.println("<th data-field='id' >Capacidad</th>");
                        out.println("<th data-field='id'>Tipo Ambiente</th>");
                        out.println("<th data-field='id'>Suministros</th>");
                        out.println("<th data-field='id'>Observación</th>");
                        out.println("<th data-field='id'>Acción</th>");
                        out.println("</tr>");
                        out.println("</thead>");
                        out.println("<tbody>");
                        for (int i = 0; i < listaA.size(); i++) {
                            a = listaA.get(i);
                            json = ""+a.getCapacidad()+","+a.getTipoAmbiente()+","+a.getSuministros()+","+a.getObservacion()+","+a.getId()+"";
                            out.println("<tr>");
                            out.println("<td id='' >" + a.getCapacidad() + "</td>");
                            out.println("<td id=''  >" + a.getTipoAmbiente() + "</td>");
                            out.println("<td id='' >" + a.getSuministros() + "</td>");
                            out.println("<td id='' >" + a.getObservacion() + "</td>");
                            out.println("<td id='' ><ul style='margin-bottom: 0px; margin-top: 0px;'>"
                                    + "<li><a  id='" + a.getId() + "' onclick='accion(this)' onmouseover='ubicacion(this)' onmouseout='normal(this)' style='cursor: pointer'>Eliminar</a></li>"
                                    + "<li><a id='" + a.getId() + "' onclick='accion(this)' onmouseover='ubicacion(this)' onmouseout='normal(this)' style='cursor: pointer' value='"+json+"'>Modificar</a></li>"
                                    + "</ul></td>");
                            out.println("</tr>");
                        }
                        out.println("</tbody>");
                        out.println("</table>");
                    } else {
                        out.println("<div class='center-align'><h5>No hay Ambientes</h5></div>");
                    }
                    break;
                case "insertarAmbiente":
                    System.out.println("servletAmbiente");
                    out.println("Servlet ambiente");
                    /*
                    a.setCapacidad(capacidad);
                    a.setObservacion(observacion);
                    a.setSuministros(suministros);
                    a.setTipoAmbiente(tipo);
                    if(daoA.insertarAmbiente(a) != 0){
                        response.setStatus(200);
                        out.println("¡El ambiente se guardó con exito!");
                    }else{
                        response.sendError(400, "No se guardo el ambiente");
                    }*/
                    break;
                case "eliminarAmbiente":
                    if(daoA.eliminarAmbiente(Integer.parseInt(idAmbiente)) != 0){
                    response.setStatus(200);
                    out.println("¡El ambiente se eliminó!");
                    }else{
                        response.sendError(500,"El ambiente no se eliminó");
                    }
                    break;
                case "guardarA":
                    a = new Ambiente();
                    a.setCapacidad(capacidad);
                    a.setObservacion(observacion);
                    a.setSuministros(suministros);
                    a.setTipoAmbiente(tipo);
                    if(daoA.insertarAmbiente(a) != 0){
                        response.setStatus(200);
                        out.println("¡El ambiente se guardó con exito!");
                    }else{
                        response.sendError(400, "No se guardo el ambiente");
                    }
                    break;
                case "modificarA":
                     a = new Ambiente();
                    a.setCapacidad(capacidad);
                    a.setObservacion(observacion);
                    a.setSuministros(suministros);
                    a.setTipoAmbiente(tipo);
                    a.setId(Integer.parseInt(idAmbiente));
                    if(daoA.insertarAmbiente(a) != 0){
                        response.setStatus(200);
                        out.println("¡El ambiente se modificó con exito!");
                    }else{
                        response.sendError(400, "No se modificó el ambiente");
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
