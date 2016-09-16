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
public class ServletFicha extends HttpServlet {

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
            String idFicha;
            String numficha;
            String fechaInicio;
            String fechaFinal;
            String jornada;
            String idPrograma;
            String gestor;      
            String json;
            String idF;
            List<Ficha> listaF;
            Ficha f;
            DaoFicha daoF;

            validacion = request.getParameter("validacion");
            idFicha = request.getParameter("idFicha");
            numficha = request.getParameter("numficha");
            fechaInicio = request.getParameter("fechaInicio");
            fechaFinal = request.getParameter("fechaFinal");
            jornada = request.getParameter("jornada");
            idPrograma = request.getParameter("idPrograma");
            gestor = request.getParameter("gestor");
            idF = request.getParameter("idF");
            
            daoF = new DaoFicha();
            switch (validacion) {
                case "listarFichas":
                    listaF = daoF.consultarFicha();
                    if (!listaF.isEmpty() || listaF != null) {
                        out.println("<table class='highlight bordered linea col s12' id='tblFichas'>");
                        out.println("<thead>");
                        out.println("<tr>");
                        out.println("<th data-field='id' >Programa</th>");
                        out.println("<th data-field='id'>Ficha</th>");
                        out.println("<th data-field='id'>Fecha Inicio</th>");
                        out.println("<th data-field='id'>Fecha Fin</th>");
                        out.println("<th data-field='id'>Gestor</th>");
                        out.println("<th data-field='id'>Jornada</th>");
                        out.println("<th data-field='id'>Acción</th>");
                        out.println("</tr>");
                        out.println("</thead>");
                        out.println("<tbody>");
                        for (int i = 0; i < listaF.size(); i++) {
                            f = listaF.get(i);
                            json = ""+f.getIdPrograma()+","+f.getNumeroFicha()+","+f.getFechaInicio()+","+f.getFechaFinal()+","+f.getGestor()+","+f.getJornada()+"";
                            out.println("<tr>");
                            out.println("<td id='' >" + f.getNomPrograma() + "</td>");
                            out.println("<td id=''  >" + f.getNumeroFicha() + "</td>");
                            out.println("<td id='' >" + f.getFechaInicio() + "</td>");
                            out.println("<td id='' >" + f.getFechaFinal() + "</td>");
                            out.println("<td id='' >" + f.getGestor() + "</td>");
                            out.println("<td id='' >" + f.getJornada() + "</td>");
                            out.println("<td id='' ><ul style='margin-bottom: 0px; margin-top: 0px;'>"
                                    + "<li><a  id='" + f.getId() + "' onclick='accion(this)' onmouseover='ubicacion(this)' onmouseout='normal(this)' style='cursor: pointer'>Eliminar</a></li>"
                                    + "<li><a id='" + f.getId() + "' onclick='accion(this)' onmouseover='ubicacion(this)' onmouseout='normal(this)' style='cursor: pointer' value='"+json+"'>Modificar</a></li>"
                                    + "</ul></td>");
                            out.println("</tr>");
                        }
                        out.println("</tbody>");
                        out.println("</table>");
                    } else {
                        out.println("<div class='center-align'><h5>No hay fichas</h5></div>");
                    }
                    break;
                case "eliminarFicha":
                    f = new Ficha();
                    f.setId(Integer.parseInt(idFicha));
                    if(daoF.eliminarFicha(f) != 0){
                        response.setStatus(200);
                        out.println("¡La ficha se eliminó con exito!");
                    }else{
                        response.setStatus(500);
                        response.sendError(400,"¡La ficha no se eliminó!");
                    }
                    break;
                case "guardarFicha":
                    f = new Ficha();
                    f.setNumeroFicha(numficha);
                    f.setFechaInicio(fechaInicio);
                    f.setFechaFinal(fechaFinal);
                    f.setJornada(jornada);
                    f.setIdPrograma(Integer.parseInt(idPrograma));
                    f.setGestor(gestor);
                    int bandera = daoF.insertarFicha(f);
                    if(bandera == 1){
                       // out.println("La ficha ya existe");
                        response.sendError(400, "La ficha ya existe");
                        
                    }else if(bandera == 0){
                        response.setStatus(200);
                        out.println("¡La ficha se guardó con exito!");
                    }else {
                         response.sendError(500, "La ficha no se guardó");
                    }
                    break;
                case "modificarFicha":
                    f = new Ficha();
                    f.setId(Integer.parseInt(idF));
                    f.setNumeroFicha(numficha);
                    f.setFechaInicio(fechaInicio);
                    f.setFechaFinal(fechaFinal);
                    f.setJornada(jornada);
                    f.setIdPrograma(Integer.parseInt(idPrograma));
                    f.setGestor(gestor);
                    int bandera2 = daoF.modificarFicha(f);
                    if(bandera2 == 1){
                        response.setStatus(200);
                        out.println("¡La ficha se modificó con exito!");
                    }else if(bandera2 == 0){
                         response.setStatus(400);
                        response.sendError(500, "¡La ficha no existe!");
                    }else {
                         response.sendError(500, "La ficha no se modificó");
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
