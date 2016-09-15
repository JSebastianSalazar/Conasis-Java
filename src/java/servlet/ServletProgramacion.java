/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DAOUsuario;
import DAO.DaoAmbiente;
import DAO.DaoFicha;
import DAO.DaoProgramacion;
import beans.Ambiente;
import beans.Ficha;
import beans.Programacion;
import beans.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ServletProgramacion extends HttpServlet {

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
           String idProgramacion;
            String idFicha;
            String idAmbiente;
            String idCompetencia;
            String idUsuario;
            String trimestre;
            String fechaInicio;
            String fechaFinal;
            String diaSemana;
            String horaIngreso;
            String horaSalida;
            String validacion;
            String nombreFicha;
            String nombrePrograma;
            String idInstructor;
            //String vandera;
            Programacion p;
            Ficha f;
            Ambiente a;
            DaoProgramacion daop;
            DAOUsuario daoU;
            DaoFicha daoF;
            DaoAmbiente daoA;
            List<Programacion> lista;
            List<Usuario> listaU;
            List<Ficha> listaF;
            List<Ambiente> listaA;
            Gson json;
            String jsons;
            HttpSession session= request.getSession(true);
            Usuario u;
            String idUsuarioLogueado;

            idProgramacion = request.getParameter("idProgramacion");
            idFicha = request.getParameter("idFicha");
            idAmbiente = request.getParameter("idAmbiente");
            trimestre = request.getParameter("trimestre");
            fechaInicio = request.getParameter("fechaInicio");
            fechaFinal = request.getParameter("fechaFin");
            diaSemana = request.getParameter("dia");
            horaIngreso = request.getParameter("horaInicio");
            horaSalida = request.getParameter("horaFinal");
            validacion = request.getParameter("validacion");
            nombreFicha = request.getParameter("nombreFicha");
            nombrePrograma = request.getParameter("nombrePrograma");
            idCompetencia = request.getParameter("idCompetencia");
            idUsuario = request.getParameter("idUsuario");
           idInstructor = request.getParameter("idInstructor");
            idUsuarioLogueado =session.getAttribute("idIns")+"";
            
            p = new Programacion();
            f = new Ficha();
            json = new Gson();
            daop = new DaoProgramacion();
            daoU = new DAOUsuario();
            daoF = new DaoFicha();
            daoA = new DaoAmbiente();

            switch (validacion) {
                case "comboboxInstructores":
                    listaU = daoU.instructores();
                    jsons = json.toJson(listaU);
                    out.println(jsons);
                    break;
                case "comboboxFicha":
                    listaF = daoF.fichas();
                    jsons = json.toJson(listaF);
                    out.println(jsons);
                    break;
                case "tblAmbientes":
                    listaA = daoA.ambientes();
                    if (!listaA.isEmpty() || listaA.size() != 0) {
                       /*  out.println("<script>");
                        out.println("$(document).ready(function () {");
                        out.println("$('#tblAmbnt').dataTable({");
                        out.println("'language': { ");
                        out.println("'url': '//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json'");
                        out.println("}");
                        out.println("});");
                        out.println("});");
                        out.println("</script");*/
                        out.println("<div></div>");
                        out.println("<table class='highlight bordered' id='tblAmbnt'>");
                        out.println("<thead>");
                        out.println("<tr>");
                        out.println("<th data-field='id'>Capacidad</th>");
                        out.println("<th data-field='id'>Tipo ambiente</th>");
                        out.println("<th data-field='id'>Suministros</th>");
                        out.println("<th data-field='id'>Acción</th>");
                        out.println("</tr>");
                        out.println("</thead>");
                        out.println("<tbody>");
                        for (int i = 0; i < listaA.size(); i++) {
                            a = listaA.get(i);
                            out.println("<tr>");
                            out.println("<td id='" + a.getId() + "' onclick='idAmbiente(this)'>" + a.getCapacidad() + "</td>");
                            out.println("<td id='" + a.getId() + "' onclick='idAmbiente(this)'>" + a.getTipoAmbiente() + "</td>");
                            out.println("<td id='" + a.getId() + "' onclick='idAmbiente(this)'>" + a.getSuministros() + "</td>");
                            out.println("<td id='' ><a id='"+a.getId()+"' onclick='escogerAmbiente(this)' onmouseover='ubicacion(this)' onmouseout='normal(this)'  style='cursor: pointer'>Aceptar</a></td>");
                            out.println("<tr>");
                        }
                        out.println("</tbody>");
                        out.println("</table>");
                        
                       /**/
                    } else {
                        out.println("<center><h5>No hay Ambientes</h5></center>");
                    }
                    break;
                case "insertarProgramacion":
                    //convirtiendo de String a DATE
                    //convirtiendo de String a TIME
                    //System.out.println("antes -> " +horaSalida);
                    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                    //SimpleDateFormat formatoAhora = new SimpleDateFormat("hh:mm");
                    Date fechaDate1 = null;
                    Date fechaDate2 = null;
                    /*Date  hora1D = null;
                     Date hora2D = null;*/
                    try {
                        fechaDate1 = formatoDelTexto.parse(fechaInicio);
                        fechaDate2 = formatoDelTexto.parse(fechaFinal);
                        /* hora1D =  formatoAhora.parse(horaIngreso);
                         hora2D = formatoAhora.parse(horaSalida);*/
                    } catch (ParseException ex) {
                        //Logger.getLogger(ServletProgramacion.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Error convirtiendo fechas y horas " + ex);
                    }

                    java.sql.Date sql1 = new java.sql.Date(fechaDate1.getTime());
                    java.sql.Date sql2 = new java.sql.Date(fechaDate2.getTime());
                    /*java.sql.Time sqlhora1 = new java.sql.Time(hora1D.getTime());
                     java.sql.Time sqlhora2 = new java.sql.Time(hora2D.getTime());*/
                    //System.out.println("despues -> " +horaSalida);
                    p.setIdAmbiente(Integer.parseInt(idAmbiente));
                    p.setIdFicha(Integer.parseInt(idFicha));
                    p.setTrimestre(trimestre);
                    p.setFechaInicio(sql1);
                    p.setFechaFinal(sql2);
                    p.setDiaSemana(diaSemana);
                    p.setHoraIngreso(horaIngreso);
                    p.setHoraSalida(horaSalida);
                    p.setIsUsuario(Integer.parseInt(idInstructor));
                    p.setIdCompetencia(Integer.parseInt(idCompetencia));
                    int result = daop.insertar2(p);
                    if (result == 1) {
                        /* int id = daop.conultarUltimoID();
                         if(id != 0){
                         out.print(id);
                         }*/
                        //out.println("No se puede guardar, hay un cruce de horarios con otra programación que tiene asignada la ficha");
                        out.println("<script>");
                       out.println("$(document).ready(function () {");
                       out.println("swal({title: \"No se puede guardar\",\n" +
"                    text: \"Hay un cruce de horarios con otra programación que tiene asignada la ficha\",\n" +
"                    timer: 2000,\n" +
"                    showConfirmButton: true});");
                       out.println("});");
                        out.println("</script>");
                    } else if (result == 2) {
                        //out.println("No se puede guardar, hay un cruce de horarios y ambientes con otra ficha");
                        out.println("<script>");
                       out.println("$(document).ready(function () {");
                       out.println("swal({title: \"No se puede guardar\",\n" +
"                    text: \"Hay un cruce de horarios y ambientes con otra ficha\",\n" +
"                    timer: 2000,\n" +
"                    showConfirmButton: true});");
                       out.println("});");
                        out.println("</script>");
                    } else if (result == 3) {
                        //out.println("No se puede guardar, el instructor ya tiene asignado una programación con esta franja de horario");
                        out.println("<script>");
                       out.println("$(document).ready(function () {");
                       out.println("swal({title: \"No se puede guardar\",\n" +
"                    text: \"El instructor ya tiene asignado una programación con esta franja de horario\",\n" +
"                    timer: 2000,\n" +
"                    showConfirmButton: true});");
                       out.println("});");
                        out.println("</script>");
                    } else if (result == 4) {
                        //out.println("La programación se ha guardado con exito");
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Perfecto\",\n" +
"                                text: \"¡La programación se ha guardado con exito!\",\n" +
"                                type: \"success\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    } else {
                        //out.println("Ha ocurrido un error");
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Error\",\n" +
"                                text: \"No se pudo guardar la programación\",\n" +
"                                type: \"error\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    }
                    break;
                case "programacionFicha":
                    lista = daop.programacionFicha(Integer.parseInt(idFicha));
                    if (!lista.isEmpty() || lista.size() != 0) {
                        out.println("<script>");
                        out.println("$(document).ready(function () {");
                        out.println("$('#tblProgramacionFicha').dataTable({");
                        out.println("'language': { ");
                        out.println("'url': '//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json'");
                        out.println("}");
                        out.println("});");
                        out.println("});");
                        out.println("</script");
                        
                        out.println("<div class='center-align'><h5>" + nombrePrograma + "</h5></div>");
                        out.println("<div class='center-align'><h5>" + nombreFicha + "</h5></div>");
                        out.println("<table class='highlight bordered' id='tblProgramacionFicha'>");
                        out.println("<thead>");
                        out.println("<tr>");
                        out.println("<th data-field='id' >Competencia</th>");
                        out.println("<th data-field='id'>Dia</th>");
                        out.println("<th data-field='id'>Fecha Inicio</th>");
                        out.println("<th data-field='id'>Fecha Fin</th>");
                        out.println("<th data-field='id'>Hora Inicio</th>");
                        out.println("<th data-field='id'>Hora Fin</th>");
                        out.println("<th data-field='id'>Instructor</th>");
                        out.println("<th data-field='id'>Ambiente</th>");
                        out.println("<th data-field='id'>Trimestre</th>");
                        out.println("<th data-field='id'>Acción</th>");
                        out.println("</tr>");
                        out.println("</thead>");
                        out.println("<tbody>");
                        for (int i = 0; i < lista.size(); i++) {
                            p = lista.get(i);
                            out.println("<tr>");
                            out.println("<td id='tblNomCompetencia' >" + p.getNomCompetencia() + "</td>");
                            out.println("<td id='tblDia'  >" + p.getDiaSemana() + "</td>");
                            out.println("<td id='tblFechaI' >" + p.getFechaInicio() + "</td>");
                            out.println("<td id='tblFechaF' >" + p.getFechaFinal() + "</td>");
                            out.println("<td id='tblHoraI' >" + p.getHoraIngreso() + "</td>");
                            out.println("<td id='tblHoraS' >" + p.getHoraSalida() + "</td>");
                            out.println("<td id='tblInstructor' >" + p.getNombreI() + " " + p.getApellidoI() + "</td>");
                            out.println("<td id='tblIdAmbiente' >" + p.getIdAmbiente() + "</td>");
                            out.println("<td id='tblTrimestre' >" + p.getTrimestre() + "</td>");
                            out.println("<td id='' ><ul style='margin-bottom: 0px; margin-top: 0px;'>"
                                    + "<li><a  id='" + p.getIdProgramacion() + "' onclick='accion(this)' onmouseover='ubicacion(this)' onmouseout='normal(this)' style='cursor: pointer'>Eliminar</a></li>"
                                    + "<li><a id='" + p.getIdProgramacion() + "' onclick='accion(this)' onmouseover='ubicacion(this)' onmouseout='normal(this)' style='cursor: pointer'>Modificar</a></li>"
                                    + "</ul></td>");
                            out.println("</tr>");
                        }
                        out.println("</tbody>");
                        out.println("</table>");
                    } else {
                        out.println("<div class='center-align'><h5>" + nombrePrograma + "</h5></div>");
                        out.println("<div class='center-align'><h5>" + nombreFicha + "</h5></div>");
                        out.println("<div class='center-align'><h6>Esta ficha no tiene una programación</h6></div>");
                    }
                    break;
                //esto iría en el servlet de ficha
                case "cuadrosFichasTodos":
                    listaF = daoF.fichas();
                    if (listaF.isEmpty() || listaF == null) {
                        out.println("<center><h4>No hay fichas en el sistema<h4></center>");
                    } else {
                        for (int i = 0; i < listaF.size(); i++) {
                            f = listaF.get(i);
                            out.println("<div class='col s2 center-align hoverable' id='" + f.getId() + "' name='cuadroFicha' onclick='idCuadroFicha(this)' style='background-color: #bbdefb; padding: 5px; background-clip: content-box;'><h5>" + f.getNumeroFicha() + "</h5></div>");
                        }
                    }
                    break;
                case "eliminarProgramacion":
                    if (daop.eliminarProgramacion(Integer.parseInt(idProgramacion))) {
                        //out.println("se eliminó la programación");
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Perfecto\",\n" +
"                                text: \"¡La programación se ha eliminado con exito!\",\n" +
"                                type: \"success\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    } else {
                        //out.println("No eliminó la programación");
                         out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Error\",\n" +
"                                text: \"No se pudo eliminar la programación\",\n" +
"                                type: \"error\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    }
                    break;
                case "ProgramacionModificar":
                    //convirtiendo de String a DATE
                    //convirtiendo de String a TIME
                    SimpleDateFormat formatoDelTextom = new SimpleDateFormat("yyyy-MM-dd");
                    // SimpleDateFormat formatoAhoram = new SimpleDateFormat("hh:mm");
                    Date fechaDate1m = null;
                    Date fechaDate2m = null;
                    /*Date  hora1Dm = null;
                     Date hora2Dm = null;*/
                    try {
                        fechaDate1m = formatoDelTextom.parse(fechaInicio);
                        fechaDate2m = formatoDelTextom.parse(fechaFinal);
                        /* hora1Dm =  formatoAhoram.parse(horaIngreso);
                         hora2Dm = formatoAhoram.parse(horaSalida);*/
                    } catch (ParseException ex) {
                        //Logger.getLogger(ServletProgramacion.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Error convirtiendo fechas y horas " + ex);
                    }
                    java.sql.Date sql1m = new java.sql.Date(fechaDate1m.getTime());
                    java.sql.Date sql2m = new java.sql.Date(fechaDate2m.getTime());
                    /*java.sql.Time sqlhora1m = new java.sql.Time(hora1Dm.getTime());
                     java.sql.Time sqlhora2m = new java.sql.Time(hora2Dm.getTime());*/

                    p.setIdProgramacion(Integer.parseInt(idProgramacion));
                    p.setIdAmbiente(Integer.parseInt(idAmbiente));
                    p.setIdFicha(Integer.parseInt(idFicha));
                    p.setTrimestre(trimestre);
                    p.setFechaInicio(sql1m);
                    p.setFechaFinal(sql2m);
                    p.setDiaSemana(diaSemana);
                    p.setHoraIngreso(horaIngreso);
                    p.setHoraSalida(horaSalida);
                    p.setIsUsuario(Integer.parseInt(idUsuario));
                    p.setIdCompetencia(Integer.parseInt(idCompetencia));
                    int retorno = daop.modificarProgramacion2(p);
                    if (retorno == 1) {
                        //out.println("No se puede modificar, hay un cruce de horarios con otra programación que tiene asignada la ficha");
                        out.println("<script>");
                       out.println("$(document).ready(function () {");
                       out.println("swal({title: \"No se puede modificar\",\n" +
"                    text: \"Hay un cruce de horarios con otra programación que tiene asignada la ficha\",\n" +
"                    timer: 2000,\n" +
"                    showConfirmButton: true});");
                       out.println("});");
                        out.println("</script>");
                    } else if (retorno == 2) {
                        //out.println("No se puede modificar, hay un cruce de horarios y ambientes con otra ficha");
                         out.println("<script>");
                       out.println("$(document).ready(function () {");
                       out.println("swal({title: \"No se puede modificar\",\n" +
"                    text: \"Hay un cruce de horarios y ambientes con otra ficha\",\n" +
"                    timer: 2000,\n" +
"                    showConfirmButton: true});");
                       out.println("});");
                        out.println("</script>");
                    } else if (retorno == 3) {
                       // out.println("No se puede modificar, el instructor ya tiene asignado una programación con esta franja de horario");
                        out.println("<script>");
                       out.println("$(document).ready(function () {");
                       out.println("swal({title: \"No se puede modificar\",\n" +
"                    text: \"El instructor ya tiene asignado una programación con esta franja de horario\",\n" +
"                    timer: 2000,\n" +
"                    showConfirmButton: true});");
                       out.println("});");
                        out.println("</script>");
                    } else if (retorno == 4) {
                       // out.println("La programación se ha modificado con exito");
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Perfecto\",\n" +
"                                text: \"¡La programación se ha modificado con exito!\",\n" +
"                                type: \"success\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    } else {
                        //out.println("Ha ocurrido un error");
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Error\",\n" +
"                                text: \"No se pudo modificar la programación\",\n" +
"                                type: \"error\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    }
                    break;
                case "competenciasDictadasXintstructor":                    
                    lista = daop.competenciasDictadasXintstructor(Integer.parseInt(idUsuarioLogueado));
                    if (!lista.isEmpty() || lista.size() != 0) {
                                               
                        out.println("<table class='highlight bordered' id='tblCompetenciasInstructor'>");
                        out.println("<thead>");
                        out.println("<tr>");
                        out.println("<th data-field='id'>Ficha</th>");
                        out.println("<th data-field='id'>Nombre Competencia</th>");
                        out.println("<th data-field='id'>Hora Inicio</th>");
                        out.println("<th data-field='id'>Hora Fin</th>");
                        out.println("<th data-field='id'>Acción</th>");
                        out.println("</tr>");
                        out.println("</thead>");
                        out.println("<tbody>");
                        for (int i = 0; i < lista.size(); i++) {
                            p = lista.get(i);
                            out.println("<tr >");///onclick='datosAcademicosAsistencia(" + p.getNumeroficha() + ","+p.getNomCompetencia()+")'
                            out.println("<td id='' >" + p.getNumeroficha() + "</td>");
                            out.println("<td id=''  >" + p.getNomCompetencia() + "</td>");
                            out.println("<td id='' >" + p.getHoraIngreso() + "</td>");
                            out.println("<td id='' >" + p.getHoraSalida() + "</td>");
                            out.println("<td id='' ><a  href='asistencia.jsp?ficha="+p.getNumeroficha()+"&nomComp="+p.getNomCompetencia()+"&idProgramacion="+p.getIdProgramacion()+"' onmouseover='ubicacion(this)' onmouseout='normal(this)' style='cursor: pointer'>Aceptar</a></td>");
                            out.println("</tr>");
                            
                        }
                        out.println("</tbody>");
                        out.println("</table>");
                        
                        out.println("<script>");
                        out.println("$(document).ready(function () {");
                        out.println("$('#tblCompetenciasInstructor').dataTable({");
                        out.println("'language': { ");
                        out.println("'url': '//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json'");
                        out.println("}");
                        out.println("});");
                        out.println("});");
                        out.println("</script");
                    } else {
                        out.println("<div class='center-align'><h5>No tiene que tomar asistencia en el día de hoy</h5></div>");
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
