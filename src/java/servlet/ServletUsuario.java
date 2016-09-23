/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DAOUsuario;
import beans.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sebas
 */
public class ServletUsuario extends HttpServlet {

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

            String fecha;
            String ficha;
            String idProgramacion;
            String validacion;
            String json = null;
            Usuario u;
            DAOUsuario dao;
            List<Usuario> lista;
            Gson jsonO;

            fecha = request.getParameter("fecha");
            ficha = request.getParameter("ficha");
            idProgramacion = request.getParameter("idProgramacion");
            validacion = request.getParameter("validacion");
            /*System.out.println("fecha "+fecha);
             System.out.println("ficha "+ficha);
             System.out.println("id "+idProgramacion);*/
            u = new Usuario();
            dao = new DAOUsuario();
            lista = new ArrayList();
            jsonO = new Gson();

            switch (validacion) {
                case "aprendicesQueNoAsistieron":
                    lista = dao.aprendicesNoAsistieron(fecha, ficha, Integer.parseInt(idProgramacion));
                    if (lista.isEmpty() || lista.size() == 0) {
                        out.println("<br>");
                        out.println("<center><h4>Ningún aprendiz faltó el " + fecha + "<h4></center>");
                    } else {
                        out.println("<center><h5>Aprendices que faltaron el " + fecha + "<h5></center><br>");
                        out.println("<table class='highlight bordered' id='tblInasistenciaApren'>");
                        out.println("<thead>");
                        out.println("<tr>");
                        out.println("<th data-field='id'><center>Numero documento</center></th>");
                        out.println("<th data-field='id'><center>Nombre</center></th>");
                        out.println("<th data-field='id'><center>Apellido</center></th>");
                        out.println("<th data-field='id'><center>Novedad</center></th>");
                        out.println("</tr>");
                        out.println("</thead>");
                        out.println("<tbody>");
                        int idA = 0;
                        for (int i = 0; i < lista.size(); i++) {
                            u = lista.get(i);
                            json = "[{idProgramacion:\n" + idProgramacion + "\n,idAprendiz:\n" + u.getId() + "\n}]";
                            //idA = u.getId();
                            out.println("<tr>");
                            out.println("<td id='" + "" + "' onclick=''>" + u.getDocumento() + "</td>");
                            out.println("<td id='" + "" + "' onclick=''>" + u.getNombre() + "</td>");
                            out.println("<td id='" + "" + "' onclick=''>" + u.getApellido() + "</td>");
                            if (u.getNovedad() == null || u.getNovedad().equals("") || u.getNovedad().equals("null")) {
                                out.println("<td id='' ><center><a class='waves-effect waves-light btn modal-trigger' onclick='modal(this)'  id='" + fecha + "' value='" + json + "'><i class=\"material-icons\" id=''>add</i></a></center></td>");//value='" + json +"'  
                            } else {
                                out.println("<td id='' >" + u.getNovedad() + "</td>");
                            }

                            out.println("<tr>");
                        }
                        out.println("</tbody>");
                        out.println("</table>");

                        out.println("<div id='modal6' class='modal' >");
                        out.println("<div class='modal-content'>");
                        out.println("<h4 id='titu'>Novedad</h4>");
                        out.println("<div id=''>");
                        out.println("<label for='txtaNovedad' style='font-size:14px'>Escriba la novedad del aprendiz</label>");
                        out.println("<textarea id='txtaNovedad' class='materialize-textarea' style='padding-top:0px; margin-bottom:5px'></textarea>");
                        out.println("<span id=\"validarTxtaN\" >El campo no puede estar vacio</span>");
                        out.println("</div>");
                        out.println("</div>");
                        out.println("<div class='modal-footer'>");
                        out.println("<button  class='btn waves-effect light-blue accent-4 waves-light ' type='button' name='modalAceptar' id='modalAceptarNovedad' onclick='guardarNovedad()' value='aceptar' >ACEPTAR</button>");
                        out.println("<button href='#!' class='modal-action modal-close btn waves-effect waves-green btn-flat' id='cerrarModalNovedad'><span id=''>Cancelar</span></button>");
                        out.println("</div>");
                        out.println("</div>");
                        out.println("<script type=\"text/javascript\">");
                        out.println("$(document).ready(function () {");
                        out.println("$('#validarTxtaN').hide()");
                        /*out.println("$('#tblInasistenciaApren').dataTable({");
                         out.println("'language': { ");
                         out.println("'url': '//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json'");
                         out.println("}");
                         out.println("});");*/
                        out.println("});");

                        out.println("$('#modalAceptarNovedad').click(function gNovedad(){");

                        //out.println("alert(modal());");
                        /*out.println("if ($(\"#txtaNovedad\").val().length < 1) {");
                         out.println("$('#txtaNovedad').focus();");
                         out.println("$('#validarTxtaN').show();");
                         out.println("$('#validarTxtaN').css('color', '#d50000');");
                         out.println("}else{");
                         out.println("$('#validarTxtaN').hide()");
                         out.println("    $.ajax({");
                         out.println(" beforeSend: function () {");
                         out.println("},");
                         out.println("method: 'POST',");
                         out.println(" url: '../ServletAsistencia', ");//nombre del servlet
                         out.println(" data: {");
                         out.println("    validacion: 'insertarNovedad',");
                         out.println(" novedad: $('#txtaNovedad').val(),");
                         out.println("    documento: "+idA+",");
                         out.println("    idProgramacion: "+idProgramacion+" ");//Esto es el id del usuaio logueado//CAMBIAR 
                         out.println(" }");
                         out.println(" , error: function (jqXHR, estado, error) {");
                         out.println("  alert('Error en el Servidor');");
                         out.println(" },");
                         out.println("complete: function (jqXHR, estado) {");
                         out.println("$(\"#modal6\").closeModal('#modalAceptarNovedad');");
                         out.println("}");
                         out.println("})");
                         out.println(" .done(function (msg) {");
                         out.println(" alert(msg);");
                         out.println("});");
                         out.println("");
                         out.println("}");*/
                        out.println("});");
                        out.println("</script>");
                        out.println("");
                    }
                    /*
                     //APRENDICES QUE NO ASISTIERON PERO TRAGERON ESCUSAS
                     lista = dao.aprendicesNoAsistieronRazones(fecha, ficha, Integer.parseInt(idProgramacion));
                     if (lista.isEmpty() || lista.size() == 0) {
                     out.println("<br>");
                     out.println("<div style=' border: solid 1px #2196f3;'></div>");
                     out.println("<br>");
                     out.println("<center><h4>Los aprendices aun no se han reportado<h4></center>");
                     } else {
                     out.println("<center><h5>Aprendices que aun no se han reportado por su inasistencia<h5></center><br>");
                     out.println("<table class='highlight bordered' >");
                     out.println("<thead>");
                     out.println("<tr>");
                     out.println("<th data-field='id'><center>Numero documento</center></th>");
                     out.println("<th data-field='id'><center>Nombre</center></th>");
                     out.println("<th data-field='id'><center>Apellido</center></th>");
                     out.println("<th data-field='id'><center>Novedad</center></th>");
                     out.println("</tr>");
                     out.println("</thead>");
                     out.println("<tbody>");
                     int idA = 0;
                     for (int i = 0; i < lista.size(); i++) {
                     u = lista.get(i);
                     json = "[{idProgramacion:\n" + idProgramacion + "\n,idAprendiz:\n" + u.getId() + "\n}]";
                     //idA = u.getId();
                     out.println("<tr>");
                     out.println("<td id='" + "" + "' onclick=''>" + u.getNumeroDoc() + "</td>");
                     out.println("<td id='" + "" + "' onclick=''>" + u.getNombre() + "</td>");
                     out.println("<td id='" + "" + "' onclick=''>" + u.getApellido() + "</td>");
                     out.println("<td id='' ><center><a class='waves-effect waves-light btn modal-trigger' onclick='modal(this)'  id='"+fecha+"' value='"+json+"'><i class=\"material-icons\" id=''>add</i></a></center></td>");//value='" + json +"'
                     out.println("<tr>");
                     }
                     out.println("</tbody>");
                     out.println("</table>");
                     }*/

                    break;
                case "aprendicesDeFichas":
                    System.out.println("Hola soy aprendicesDeFicha");
                    lista = dao.aprendicesDeFichas(ficha);
                    System.out.println(lista.size() + " - tamaño");
                    if (lista.isEmpty() || lista.size() == 0) {
                        out.println("<br>");
                        out.println("<center><h4>La ficha No tiene aprendices<h4></center>");
                    } else {
                        out.println("<table class='highlight bordered' >");
                        out.println("<thead>");
                        out.println("<tr>");
                        out.println("<th data-field='id'><center>Numero documento</center></th>");
                        out.println("<th data-field='id'><center>Nombre</center></th>");
                        out.println("<th data-field='id'><center>Apellido</center></th>");
                        out.println("<th data-field='id'><center>Faltas</center></th>");
                        out.println("<th data-field='id'><center>Tiempo</center></th>");
                        out.println("<th data-field='id'><center>General</center></th>");
                        out.println("</tr>");
                        out.println("</thead>");
                        out.println("<tbody>");
                        int idA = 0;
                        for (int i = 0; i < lista.size(); i++) {
                            u = lista.get(i);
                            idA = u.getIdProgramacion();
                            json = "[{idProgramacion:\n" + u.getIdProgramacion() + "\n,idAprendiz:\n" + u.getId() + "\n}]";
                            //idA = u.getId();
                            out.println("<tr>");
                            out.println("<td id='" + "" + "' onclick=''>" + u.getDocumento() + "</td>");
                            out.println("<td id='" + "" + "' onclick=''>" + u.getNombre() + "</td>");
                            out.println("<td id='" + "" + "' onclick=''>" + u.getApellido() + "</td>");
                            out.println("<td id='' ><center><a class='waves-effect waves-light btn modal-trigger' onclick='mdCntFaltas(this)'  id='btnMdlFalta' value='" + json + "'><i class=\"material-icons\" id=''>add</i></a></center></td>");//value='" + json +"'
                            out.println("<td id='' ><center><a class='waves-effect waves-light btn modal-trigger' onclick='mdCntTiempo(this)'  id='btnMdlTiempo' value='" + json + "'><i class=\"material-icons\" id=''>add</i></a></center></td>");
                            out.println("<td id='' ><center><a class='waves-effect waves-light btn modal-trigger' onclick='mdCntGenral(this)'  id='btnMdlEstadistica' value='" + json + "'><i class=\"material-icons\" id=''>add</i></a></center></td>");
                            out.println("<tr>");
                        }
                        out.println("</tbody>");
                        out.println("</table>");
                        out.println("<a class='waves-effect waves-light btn modal-trigger' onclick='mdCntFaltasGrupal(this)'  id='btnMdlFaltasGrupal' value='" + idA + "'>Faltas grupal</a>");

                        out.println("<div id='modal7' class='modal' >");
                        out.println("<div class='modal-content'>");
                        out.println("<h5 id=''>Tipo de información de asistencia</h5>");
                        out.println("<br>");
                        out.println("<div id='mdFaltasApre'>");
                        out.println("</div>");
                        out.println("<div id='contentEstadisticaApre' style='width: 600px; height: 300px;'>Hola</div>");
                        out.println("<div id='mdTiempoApre'>");
                        out.println("</div>");
                        out.println("<div id='contentEstadisticaFaltasApre' style='width: 600px; height: 300px;'></div>");
                        out.println("</div>");
                        out.println("<div class='modal-footer'>");
                        out.println("<button href='#!' class='modal-action modal-close btn waves-effect waves-green btn-flat' id='cerrarModalNovedad' ><span id=''>Cancelar</span></button>");
                        out.println("</div>");
                        out.println("</div>");
                        out.println("<script type=\"text/javascript\">");
                        out.println("$(document).ready(function () {");
                        out.println("");
                        out.println("});");
                        out.println("</script>");
                    }
                    break;
                case "faltasGrupal":
                    lista = dao.faltasAprendiz(Integer.parseInt(idProgramacion));
                    if (lista == null || lista.isEmpty()) {
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Atención\",\n"
                                + "                                text: \"Ha ocurrido un problema\",\n"
                                + "                                type: \"warning\",\n"
                                + "                                timer: 2200,\n"
                                + "                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    } else {
                        json = jsonO.toJson(lista);
                        out.println(json);
                    }
                    break;

                //ESTO LO PASA AL SERLET DE APRENDIZ SI QUIERE
                case "tblAprendicesFicha":
                    lista = new ArrayList();
                    lista = dao.aprendicesDeFichas2(ficha);
                    System.out.println(ficha);
                    if (lista.isEmpty() || lista == null) {
                        out.println("<br>");
                        out.println("<center><h4>La ficha No tiene aprendices<h4></center>");
                    } else {
                        out.println("<center><h5>" + ficha + "<h5></center><br>");

                        out.println("<table class='highlight bordered' id='sebas'>");
                        out.println("<thead>");
                        out.println("<tr>");
                        out.println("<th data-field='id'><center>Numero documento</center></th>");
                        out.println("<th data-field='id'><center>Nombre</center></th>");
                        out.println("<th data-field='id'><center>Apellido</center></th>");
                        out.println("<th data-field='id'><center>Foto</center></th>");
                        out.println("<th data-field='id'><center>Correo</center></th>");
                        out.println("<th data-field='id'>Acción</th>");
                        out.println("</tr>");
                        out.println("</thead>");
                        out.println("<tbody>");
                        int idA = 0;
                        System.out.println(lista.size() + "aca estoy");
                        for (int i = 0; i < lista.size(); i++) {

                            u = lista.get(i);
                            idA = u.getIdProgramacion();
                            json = "[{idProgramacion:\n" + u.getIdProgramacion() + "\n,idAprendiz:\n" + u.getId() + "\n}]";
                            //idA = u.getId();
                            out.println("<tr>");
                            out.println("<td id='" + "" + "' onclick=''>" + u.getDocumento() + "</td>");
                            out.println("<td id='" + "" + "' onclick=''>" + u.getNombre() + "</td>");
                            out.println("<td id='" + "" + "' onclick=''>" + u.getApellido() + "</td>");
                            out.println("<td id='" + u.getDocumento() + "' value='"+u.getNombre()+" "+u.getApellido() +"' onclick='verFotoAprendiz(this)'  style='cursor: pointer'><i class=\"material-icons\">add</i></td>");
                            out.println("<td id='" + "" + "' onclick=''>" + u.getCorreo() + "</td>");
                            out.println("<td id='' ><ul style='margin-bottom: 0px; margin-top: 0px;'>"
                                    + "<li><a  id='" + u.getId() + "' onclick='accion(this)' onmouseover='ubicacion(this)' onmouseout='normal(this)' style='cursor: pointer'>Eliminar</a></li>"
                                    + "<li><a id='" + u.getId() + "' onclick='accion(this)' onmouseover='ubicacion(this)' onmouseout='normal(this)' style='cursor: pointer' value='" + json + "'>Modificar</a></li>"
                                    + "</ul></td>");
                            out.println("<tr>");
                        }
                        out.println("</tbody>");
                        out.println("</table>");
                        
                        out.println("<div id='apeFoto' class='modal' >");
                        out.println("<div class='modal-content'>");
                        out.println("<center><h4 id=''>Foto</h4></center>");
                        out.println("<br>");
                        out.println("<center><P id='documentofoto'></P></center>");
                        out.println("<br>");
                        out.println("<center><img class=' circle' id='fotoapend' src=\"\" alt=\"\" /></center>");
                        out.println("</div>");
                        out.println("<div class='modal-footer'>");
                        out.println("<button href='#!' class='modal-action modal-close btn waves-effect waves-green btn-flat' id='cerrarModalNovedad'><span id=''>Cancelar</span></button>");
                        out.println("</div>");
                        out.println("</div>");

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
