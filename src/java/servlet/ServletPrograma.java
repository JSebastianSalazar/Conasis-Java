/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DaoFicha;
import DAO.DaoPrograma;
import DAO.DaoProgramaXcompetencia;
import beans.Ficha;
import beans.Programa;
import beans.ProgramaXcompetencia;
import com.google.gson.Gson;

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
public class ServletPrograma extends HttpServlet {

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
            String programa;
            String validacion;
            String idPrograma;
            DaoPrograma daoP;
            List<Programa> listP;
            List<Ficha> listaF;
            Programa p;
            DaoProgramaXcompetencia daoPc;
            DaoFicha daoF;
            ProgramaXcompetencia pc;
            Ficha f;
            Gson json;
            String jsons;

            programa = request.getParameter("programa");//obtiene el nombre programa
            validacion = request.getParameter("validacion"); //sirve para saber que accion se esta ejecutando y evitar que se impriman varias cosas en el sitio web
            idPrograma = request.getParameter("idPrograma"); //obtiene el id del programa

            daoP = new DaoPrograma();
            daoF = new DaoFicha();
            p = new Programa();
            daoPc = new DaoProgramaXcompetencia();
            pc = new ProgramaXcompetencia();
            json = new Gson();

            switch (validacion) {
                case "vListar"://listando programas 
                    listP = daoP.listar();
                    out.println("<table class='highlight' >");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th data-field='id'>Programas</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");
                    for (int i = 0; i < listP.size(); i++) {
                        p = listP.get(i);
                        out.println("<tr>");
                        out.println("<td id='" + p.getIdPrograma() + "' onclick='idProgramaF(this)'>" + p.getNomPrograma() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</tbody>");
                    out.println("</table>");
                    break;
                case "vGuardar":
                    p = new Programa();
                    p.setNomPrograma(programa);
                    if (daoP.insertar(p)) {
                         out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Perfecto\",\n" +
"                                text: \"¡El programa se ha guardado con exito!\",\n" +
"                                type: \"success\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    } else {
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Error\",\n" +
"                                text: \"No se pudo guardar el programa\",\n" +
"                                type: \"error\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    }
                    break;
                case "vModificar":
                    p = new Programa();
                    p.setIdPrograma(Integer.parseInt(idPrograma));
                    p.setNomPrograma(programa);
                    if (daoP.modificar(p)) {
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Perfecto\",\n" +
"                                text: \"¡El programa se ha modificado con exito!\",\n" +
"                                type: \"success\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    } else {
                         out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Error\",\n" +
"                                text: \"No se pudo modificar el programa\",\n" +
"                                type: \"error\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    }
                    break;
                case "vEliminar":
                    pc.setIdPrograma(Integer.parseInt(idPrograma));
                    if (daoPc.consultarPrograma(pc) != 0) {
                        pc.setIdPrograma(Integer.parseInt(idPrograma));
                        if (daoPc.eliminarTodo(pc)) { //eliminando en la tabla intermedia
                            p = new Programa();
                            p.setIdPrograma(Integer.parseInt(idPrograma));
                            if (daoP.eliminar(p)) {//eliminando en la tabla programa
                                out.println("<h5>Eliminado con exito</h5>");
                            } else {
                                out.println("<h5>No se eliminó</h5>");
                            }
                        } else {
                            out.println("No se ejecutó la eliminación en cascada");
                        }
                    } else {
                        if (daoP.eliminar(p)) {//eliminando en la tabla programa
                            out.println("<h5>Eliminado con exito</h5>");
                        } else {
                            out.println("<h5>No se eliminó</h5>");
                        }
                    }

                    break;
                case "vConsultaAutocomplete":
                    listP = daoP.consultar(programa);
                    out.println("<table class='highlight' >");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th data-field='id'>Programas</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");
                    for (int i = 0; i < listP.size(); i++) {
                        p = listP.get(i);
                        out.println("<tr>");
                        out.println("<td id='" + p.getIdPrograma() + "' onclick='idProgramaF(this)'>" + p.getNomPrograma() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</tbody>");
                    out.println("</table>");
                    break;
                case "programasComobox":
                    listP = daoP.listar();
                    if(listP == null || listP.isEmpty()){
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Atecnión\",\n" +
"                                text: \"¡No hay programas... O pudo ocurrir un error en el servidor!\",\n" +
"                                type: \"warning\",\n" +
"                                timer: 2000,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    }else{
                    jsons = json.toJson(listP);
                    out.println(jsons);
                    }
                    break;
                //esto iría en el servlet de ficha
                case "cuadrosFichas":
                    listaF = daoF.fichasPorPrograma(Integer.parseInt(idPrograma));
                    if (listaF.isEmpty() || listaF == null) {
                        out.println("<center><h4>El programa no tiene fichas asiciadas<h4></center>");
                    } else {
                        for (int i = 0; i < listaF.size(); i++) {
                            f = listaF.get(i);
                            out.println("<div class='col s2 center-align hoverable' id='" + f.getId() + "' name='cuadroFicha' onclick='idCuadroFicha(this)' style='background-color: #bbdefb; padding: 5px; background-clip: content-box'><h5>" + f.getNumeroFicha() + "</h5></div>");
                        }
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
