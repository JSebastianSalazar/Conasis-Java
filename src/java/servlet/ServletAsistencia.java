/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DaoAsistencia;
import DAO.DaoFicha;
import beans.Asistencia;
import beans.Ficha;
import com.google.gson.Gson;
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
public class ServletAsistencia extends HttpServlet {

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

            String idProgramacion;
            String documento;
            String tipo;
            String ficha;
            String validacion;
            String novedad;
            String fecha;
            DaoAsistencia dao;
            List lista;
            String idInstructor;
            List<Ficha> listaF;
            List<Asistencia> listaA;
            Ficha f;
            Asistencia a;
            DaoFicha daoF;
            String json;
            Gson jsonO;
            HttpSession session = request.getSession(true);

            idProgramacion = request.getParameter("idProgramacion");
            documento = request.getParameter("documento");
            tipo = request.getParameter("tipo");
            ficha = request.getParameter("ficha");
            validacion = request.getParameter("validacion");
            //idInstructor = request.getParameter("idInstructor");
            novedad = request.getParameter("novedad");
            fecha = request.getParameter("fecha");

            idInstructor = session.getAttribute("idIns") + "";
            String nombre = "" + session.getAttribute("usuario");

            f = new Ficha();
            a = new Asistencia();
            dao = new DaoAsistencia();
            lista = new ArrayList();
            listaF = new ArrayList();
            listaA = new ArrayList();
            daoF = new DaoFicha();
            jsonO = new Gson();

            switch (validacion) {
                case "tomaAsistencia"://toma asistencia
                    if (!tipo.isEmpty()) {
                        if (!documento.isEmpty()) {
                            int idP = Integer.parseInt(idProgramacion);
                            lista = dao.tomaAsistencia(idP, ficha, documento, tipo);

                            if (lista.get(1) == (Object) 1) {
                                //out.print("el usuario no se encuentra, no pertenece a esta ficha");
                                out.println("<script>");
                                out.println("$(document).ready(function () {");
                                out.println("swal({title: \"Atención\",\n"
                                        + "                    text: \"El aprendiz no pertenece a esta ficha\",\n"
                                        + "                    timer: 1900,\n"
                                        + "                    showConfirmButton: true});");
                                out.println("});");
                                out.println("</script>");
                            } else if (lista.get(1) == (Object) 2) {
                                //out.print("el usuario ya marcó la entrada");
                                out.println("<script>");
                                out.println("$(document).ready(function () {");
                                out.println("swal({title: \"Atención\",\n"
                                        + "                    text: \"El aprendiz ya registró la entrada\",\n"
                                        + "                    timer: 1900,\n"
                                        + "                    showConfirmButton: true});");
                                out.println("});");
                                out.println("</script>");
                            } else if (lista.get(1) == (Object) 3) {
                                //out.print("insertó la entrada");
                                //out.print("<label class='grupolbl2' id='lblNombre'>"+lista.get(2)+"</label>");//"'faltas' : '"+lista.get(1)+"'," +
                                out.println("[" + "{" + "	'nombre' : '" + lista.get(2) + "'," + "'apellido' : '" + lista.get(3) + "'," + "'faltas' : '" + lista.get(4) + "','hora' : '" + lista.get(0) + "', 'foto' : '" + lista.get(6) + "'," + "'mensaje' : 'insertó la entrada','tiempo':' '\n" + "}" + "]");
                            } else if (lista.get(1) == (Object) 4) {
                                //out.print("el usuario ya marcó la salida");
                                out.println("<script>");
                                out.println("$(document).ready(function () {");
                                out.println("swal({title: \"Atención\",\n"
                                        + "                    text: \"El aprendiz ya registró la salida\",\n"
                                        + "                    timer: 1900,\n"
                                        + "                    showConfirmButton: true});");
                                out.println("});");
                                out.println("</script>");
                            } else if (lista.get(1) == (Object) 5) {
                                //out.print("insertó la salida");+ "'tiempo' : '" + lista.get(5) + "'," +
                                out.println("[\n"
                                        + "	{\n"
                                        + "		'nombre' : '" + lista.get(2) + "',\n"
                                        + "		'apellido' : '" + lista.get(3) + "',\n"
                                        + "		'hora' : '" + lista.get(0) + "',\n"
                                        + "		'faltas' : '" + lista.get(4) + "',\n"
                                        + "             'tiempo' :  '" + lista.get(5) + "',\n"
                                        + "             'foto' :  '" + lista.get(6) + "',\n"
                                        + "		'mensaje' : 'registró la salida'\n"
                                        + "	}\n"
                                        + "]");
                            } else if (lista.get(1) == (Object) 6) {
                                //out.print("No puede marcar salida --- No ha insertado la entrada aun");
                                out.println("<script>");
                                out.println("$(document).ready(function () {");
                                out.println("swal({title: \"Atención\",\n"
                                        + "                    text: \"No se puede registrar la salida. No ha insertado la entrada aun\",\n"
                                        + "                    timer: 1900,\n"
                                        + "                    showConfirmButton: true});");
                                out.println("});");
                                out.println("</script>");
                            } else {
                                // out.print("Ha ocurrido un error");
                                out.println("<script>"
                                        + "$(document).ready(function () {"
                                        + "swal({title: \"Error\",\n"
                                        + "                                text: \"Hay problemas con el servidor... Intente nuevamente\",\n"
                                        + "                                type: \"error\",\n"
                                        + "                                timer: 2200,\n"
                                        + "                                showConfirmButton: true});"
                                        + "});"
                                        + "</script>");
                            }
                        } else {
                            //out.println("Debe ingresar un numero de documento de identidad");
                            out.println("<script>");
                            out.println("$(document).ready(function () {");
                            out.println("swal({title: \"Atención\",\n"
                                    + "                    text: \"Debe ingresar un numero de documento de identidad\",\n"
                                    + "                    timer: 1900,\n"
                                    + "                    showConfirmButton: true});");
                            out.println("});");
                            out.println("</script>");
                        }
                    } else {
                        //out.print("Debe seleccionar la acción a realizar en: Seleccione tipo de asistencia");
                        out.println("<script>");
                        out.println("$(document).ready(function () {");
                        out.println("swal({title: \"Atención\",\n"
                                + "                    text: \"Debe seleccionar la acción a realizar en: Seleccione tipo de asistencia\",\n"
                                + "                    timer: 1900,\n"
                                + "                    showConfirmButton: true});");
                        out.println("});");
                        out.println("</script>");
                    }
                    break;
                case "fichasInstructor"://listando programas
                    if (!idInstructor.isEmpty()) {
                        listaF = daoF.fichasInstructor(Integer.parseInt(idInstructor));
                        if (listaF.isEmpty() || listaF == null) {
                            out.println("<center><h4>Usted no tiene fichas asiciadas<h4></center>");
                        } else {
                            for (int i = 0; i < listaF.size(); i++) {
                                f = listaF.get(i);
                                out.println("<center>");
                                out.println("<div class='col s2 center-align hoverable' id='" + f.getId() + "' value='" + f.getNumeroFicha() + "' name='cuadroFicha' onclick='tablaInasistenciaFicha(this)' style='background-color: #bbdefb; padding: 5px; background-clip: content-box'><h5>" + f.getNumeroFicha() + "</h5></div>");
                                out.println("</center>");
                            }
                        }
                    } else {
                        //  out.println("No hay instructor logueado");
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Error\",\n"
                                + "                                text: \"No se ha logueado un instructor\",\n"
                                + "                                type: \"error\",\n"
                                + "                                timer: 2200,\n"
                                + "                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    }
                    break;
                case "inasistenciaFicha":
                    if (!idInstructor.isEmpty()) {
                        listaA = dao.InasistenciaFicha(Integer.parseInt(idInstructor), ficha);
                        if (listaA.isEmpty() || listaA == null) {
                            out.println("<div class='center-align'><h5>" + ficha + "</h5></div>");
                            out.println("<br>");
                            out.println("<center><h4>No se ha tomado asistencia aun en esta ficha<h4></center>");
                        } else {
                            out.println("<div class='center-align'><h5>" + ficha + "</h5></div>");
                            out.println("<br>");
                            out.println("<table class='highlight bordered' >");
                            out.println("<thead>");
                            out.println("<tr>");
                            out.println("<th data-field='id'><center>Fecha</center></th>");
                            out.println("<th data-field='id'><center>Competencia</center></th>");
                            out.println("<th data-field='id'><center>Ver aprendices</center></th>");
                            out.println("</tr>");
                            out.println("</thead>");
                            out.println("<tbody>");
                            for (int i = 0; i < listaA.size(); i++) {
                                a = listaA.get(i);
                                json = "[{idProgramacion: \n" + a.getIdP() + "\n, ficha:" + ficha + "\n}]";
                                //System.out.println(json);
                                out.println("<tr>");
                                out.println("<td id='" + "" + "' onclick=''>" + a.getFecha() + "</td>");
                                out.println("<td id='" + "" + "' onclick=''>" + a.getCompetencia() + "</td>");
                                out.println("<td ><center><a class='waves-effect waves-light btn'  id='" + json + "' value='" + a.getFecha() + "' onclick='FaltasAprendices(this)'><i class=\"material-icons\" id=''>add</i></a></center></td>");//<i class=\"material-icons\" id='bntTablaFaltasAprendices'>add</i>
                                out.println("<tr>");
                            }
                            out.println("</tbody>");
                            out.println("</table>");
                        }
                    } else {
                        // out.println("No hay instructor logueado");
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Error\",\n"
                                + "                                text: \"No se ha logueado un instructor\",\n"
                                + "                                type: \"error\",\n"
                                + "                                timer: 2200,\n"
                                + "                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    }
                    break;
                case "insertarNovedad":
                    if (!novedad.isEmpty()) {
                        if (!documento.isEmpty()) {
                            if (!idProgramacion.isEmpty()) {
                                System.out.println(novedad + " " + documento + " " + idProgramacion + " " + fecha);
                                if (dao.insertarNovedad(novedad, Integer.parseInt(documento), Integer.parseInt(idProgramacion), fecha)) {

                                    out.println("<script>"
                                            + "$(document).ready(function () {"
                                            + "swal({title: \"Perfecto\",\n"
                                            + "                                text: \"¡La novedad se ha guardado con exito!\",\n"
                                            + "                                type: \"success\",\n"
                                            + "                                timer: 2200,\n"
                                            + "                                showConfirmButton: true});"
                                            + "});"
                                            + "</script>");
                                } else {
                                    // out.println("Ha ocurrido un error al intentar guardar la novedad");
                                    out.println("<script>"
                                            + "$(document).ready(function () {"
                                            + "swal({title: \"Error\",\n"
                                            + "                                text: \"No se pudo guardar la novedad\",\n"
                                            + "                                type: \"error\",\n"
                                            + "                                timer: 2200,\n"
                                            + "                                showConfirmButton: true});"
                                            + "});"
                                            + "</script>");
                                }
                            } else {
                                //out.println("No se ha logueado un instructor");
                                out.println("<script>"
                                        + "$(document).ready(function () {"
                                        + "swal({title: \"Error\",\n"
                                        + "                                text: \"No se ha logueado un instructor\",\n"
                                        + "                                type: \"error\",\n"
                                        + "                                timer: 2200,\n"
                                        + "                                showConfirmButton: true});"
                                        + "});"
                                        + "</script>");
                            }
                        } else {
                            //out.println("El aprendiz no esta en la lista");
                            out.println("<script>");
                            out.println("$(document).ready(function () {");
                            out.println("swal({title: \"Atención\",\n"
                                    + "                    text: \"El aprendiz no esta en la lista\",\n"
                                    + "                    timer: 1900,\n"
                                    + "                    showConfirmButton: true});");
                            out.println("});");
                            out.println("</script>");
                        }
                    } else {
                        // out.println("No se hacepta el campo vacio");
                        out.println("<script>");
                        out.println("$(document).ready(function () {");
                        out.println("swal({title: \"Atención\",\n"
                                + "                    text: \"No se hacepta el campo vacio\",\n"
                                + "                    timer: 1900,\n"
                                + "                    showConfirmButton: true});");
                        out.println("});");
                        out.println("</script>");
                    }
                    break;
                case "verificacionFicha":
                    int i = dao.verificacionFicha(Integer.parseInt(idProgramacion));
                    if (i == 0) {
                        response.setStatus(200);
                        out.println("0");
                    } else if (i == 1) {
                        response.setStatus(200);
                        out.println("1");
                    } else if (i == 2) {
                        response.setStatus(200);
                        out.println("2");
                    } else {
                        response.sendError(400, "error en el servidor2");

                    }
                    // System.out.println(i);
                    break;
                case "vertimientoFicha":
                    if (dao.vertimientoAprendices(Integer.parseInt(idProgramacion), ficha)) {
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Perfecto\",\n"
                                + "                                text: \"¡Todo esta listo para tomar asistencia!\",\n"
                                + "                                type: \"success\",\n"
                                + "                                timer: 1900,\n"
                                + "                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    } else {
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Error\",\n"
                                + "                                text: \"Ha ocurrido un error.. intente nuevamente\",\n"
                                + "                                type: \"error\",\n"
                                + "                                timer: 2200,\n"
                                + "                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    }
                    break;
                case "tomaAsistencia2":
                    if (!documento.isEmpty()) {
                        int idP = Integer.parseInt(idProgramacion);
                        lista = dao.tomaAsistencia2(idP, ficha, documento, tipo);

                        if (lista.get(1) == (Object) 1) {
                            //out.print("el usuario no se encuentra, no pertenece a esta ficha");
                            out.println("<script>");
                            out.println("$(document).ready(function () {");
                            out.println("swal({title: \"Atención\",\n"
                                    + "                    text: \"El aprendiz no pertenece a esta ficha\",\n"
                                    + "                    timer: 1900,\n"
                                    + "                    showConfirmButton: true});");
                            out.println("});");
                            out.println("</script>");
                        } else if (lista.get(1) == (Object) 2) {
                            //out.print("el usuario ya marcó la entrada");
                            out.println("<script>");
                            out.println("$(document).ready(function () {");
                            out.println("swal({title: \"Atención\",\n"
                                    + "                    text: \"El aprendiz ya registró la entrada\",\n"
                                    + "                    timer: 1900,\n"
                                    + "                    showConfirmButton: true});");
                            out.println("});");
                            out.println("</script>");
                        } else if (lista.get(1) == (Object) 3) {
                            //out.print("insertó la entrada");
                            System.out.println(lista.get(5)+"Servlet");
                            out.println("[" + "{" + "	'nombre' : '" + lista.get(2) + "'," + "'apellido' : '" + lista.get(3) + "'," + "'hora' : '" + lista.get(0) + "'," + "'faltas' : '" + lista.get(4) + "'," + "'foto' : '" + lista.get(5) + "'," + "'mensaje' : 'Se registró la entrada con exito'\n" + "}" + "]");
                        } else {
                            out.println("<script>"
                                    + "$(document).ready(function () {"
                                    + "swal({title: \"Error\",\n"
                                    + "                                text: \"Hay problemas con el servidor... Intente nuevamente\",\n"
                                    + "                                type: \"error\",\n"
                                    + "                                timer: 2200,\n"
                                    + "                                showConfirmButton: true});"
                                    + "});"
                                    + "</script>");
                        }
                    } else {
                        //out.println("Debe ingresar un numero de documento de identidad");
                        out.println("<script>");
                        out.println("$(document).ready(function () {");
                        out.println("swal({title: \"Atención\",\n"
                                + "                    text: \"Debe ingresar un numero de documento de identidad\",\n"
                                + "                    timer: 1900,\n"
                                + "                    showConfirmButton: true});");
                        out.println("});");
                        out.println("</script>");
                    }

                    break;
                case "tomaAsistenciaSalida":
                    if (!documento.isEmpty()) {
                        int idP = Integer.parseInt(idProgramacion);
                        lista = dao.tomaAsistenciaSalida(idP, ficha, documento, tipo);

                        if (lista.get(1) == (Object) 1) {
                            //out.print("el usuario no se encuentra, no pertenece a esta ficha");
                            out.println("<script>");
                            out.println("$(document).ready(function () {");
                            out.println("swal({title: \"Atención\",\n"
                                    + "                    text: \"El aprendiz no pertenece a esta ficha\",\n"
                                    + "                    timer: 1900,\n"
                                    + "                    showConfirmButton: true});");
                            out.println("});");
                            out.println("</script>");
                        } else if (lista.get(1) == (Object) 2) {
                            //out.print("el usuario ya marcó la entrada");
                            out.println("<script>");
                            out.println("$(document).ready(function () {");
                            out.println("swal({title: \"Atención\",\n"
                                    + "                    text: \"El aprendiz no ha registrado la entrada\",\n"
                                    + "type:'warning',"
                                    + "                    timer: 1900,\n"
                                    + "                    showConfirmButton: true});");
                            out.println("});");
                            out.println("</script>");
                        } else if (lista.get(1) == (Object) 3) {
                            //out.print("insertó la entrada");
                            out.println("[" + "{" + "	'nombre' : '" + lista.get(2) + "'," + "'apellido' : '" + lista.get(3) + "'," + "'hora' : '" + lista.get(0) + "'," + "'faltas' : '" + lista.get(4) + "'," + "'tiempo' : '" + lista.get(5) + "'," + "'mensaje' : 'Se registró la salida con exito'\n" + "}" + "]");
                        } else if (lista.get(1) == (Object) 4) {
                            //out.print("el usuario no se encuentra, no pertenece a esta ficha");
                            out.println("<script>");
                            out.println("$(document).ready(function () {");
                            out.println("swal({title: \"Atención\",\n"
                                    + "                    text: \"El aprendiz ya registró la salida\",\n"
                                    + "                    timer: 1900,\n"
                                    + "                    showConfirmButton: true});");
                            out.println("});");
                            out.println("</script>");
                        } else {
                            out.println("<script>"
                                    + "$(document).ready(function () {"
                                    + "swal({title: \"Error\",\n"
                                    + "                                text: \"Hay problemas con el servidor... Intente nuevamente\",\n"
                                    + "                                type: \"error\",\n"
                                    + "                                timer: 2200,\n"
                                    + "                                showConfirmButton: true});"
                                    + "});"
                                    + "</script>");
                        }
                    } else {
                        //out.println("Debe ingresar un numero de documento de identidad");
                        out.println("<script>");
                        out.println("$(document).ready(function () {");
                        out.println("swal({title: \"Atención\",\n"
                                + "                    text: \"Debe ingresar un numero de documento de identidad\",\n"
                                + "                    timer: 1900,\n"
                                + "                    showConfirmButton: true});");
                        out.println("});");
                        out.println("</script>");
                    }
                    break;
                case "fichasInstructor2"://listando programas
                    if (!idInstructor.isEmpty()) {
                        listaF = daoF.fichasInstructor(Integer.parseInt(idInstructor));
                        if (listaF.isEmpty() || listaF == null) {
                            out.println("<center><h4>Usted no tiene fichas asiciadas<h4></center>");
                        } else {
                            for (int j = 0; j < listaF.size(); j++) {
                                f = listaF.get(j);
                                out.println("<center>");
                                if(nombre.equals(f.getGestor())){
                                    out.println("<div class='col s2 center-align hoverable' id='" + f.getId() + "' value='" + f.getNumeroFicha() + "' name='cuadroFicha' onclick='tablaCompetenciasComoGestor(this)' style='background-color: #6ab7f6; padding: 5px; background-clip: content-box'><h5>" + f.getNumeroFicha() + "</h5></div>");
                                }else{
                                   out.println("<div class='col s2 center-align hoverable' id='" + f.getId() + "' value='" + f.getNumeroFicha() + "' name='cuadroFicha' onclick='tablaCompetencias(this)' style='background-color: #bbdefb; padding: 5px; background-clip: content-box'><h5>" + f.getNumeroFicha() + "</h5></div>");
                                 }
                                out.println("</center>");
                            }
                        }
                    } else {
                        //  out.println("No hay instructor logueado");
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Error\",\n"
                                + "                                text: \"No se ha logueado un instructor\",\n"
                                + "                                type: \"error\",\n"
                                + "                                timer: 2200,\n"
                                + "                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    }
                    break;
                case "competenciasInss":
                    if (!idInstructor.isEmpty()) {
                        listaA = dao.competencias(Integer.parseInt(idInstructor), ficha);
                        if (listaA.isEmpty() || listaA == null) {
                            out.println("<center><h4>No tiene competencias asociadas<h4></center>");
                        } else {
                            out.println("<div class='center-align'><h5>" + ficha + "</h5></div>");
                            out.println("<br>");
                            out.println("<table class='highlight bordered' >");
                            out.println("<thead>");
                            out.println("<tr>");
                            out.println("<th data-field='id'><center>Competencia</center></th>");
                            out.println("<th data-field='id'><center>Ver aprendices</center></th>");
                            out.println("</tr>");
                            out.println("</thead>");
                            out.println("<tbody>");
                            for (int j = 0; j < listaA.size(); j++) {
                                a = listaA.get(j);
                            //json = "[{idProgramacion: \n"+a.getIdP()+"\n, ficha:"+ficha+"\n}]";
                                //System.out.println(json);
                                out.println("<tr>");
                                out.println("<td id='" + "" + "' onclick=''>" + a.getCompetencia() + "</td>");
                                out.println("<td ><center><a class='waves-effect waves-light btn'  id='" + ficha + "' value='" + a.getIdP() + "' onclick='tablaAprendices(this)'><i class=\"material-icons\" id=''>add</i></a></center></td>");//<i class=\"material-icons\" id='bntTablaFaltasAprendices'>add</i>
                                out.println("<tr>");
                            }
                            out.println("</tbody>");
                            out.println("</table>");
                        }
                    } else {
                        // out.println("No hay instructor logueado");
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Error\",\n"
                                + "                                text: \"No se ha logueado un instructor\",\n"
                                + "                                type: \"error\",\n"
                                + "                                timer: 2200,\n"
                                + "                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    }
                    break;
                                 case "competenciasInssGestor":
                    if (!idInstructor.isEmpty()) {
                        listaA = dao.competenciasGestor(ficha);
                        if (listaA.isEmpty() || listaA == null) {
                            out.println("<center><h4>No tiene competencias asociadas<h4></center>");
                        } else {
                            out.println("<div class='center-align'><h5>" + ficha + "</h5></div>");
                            out.println("<br>");
                            out.println("<table class='highlight bordered' >");
                        out.println("<thead>");
                        out.println("<tr>");
                        out.println("<th data-field='id'><center>Competencia</center></th>");
                        out.println("<th data-field='id'><center>Ver aprendices</center></th>");
                        out.println("</tr>");
                        out.println("</thead>");
                        out.println("<tbody>");
                        for (int j = 0; j < listaA.size(); j++) {
                            a = listaA.get(j);
                            out.println("<tr>");
                            out.println("<td id='" + "" + "' onclick=''>" + a.getCompetencia() + "</td>");
                            out.println("<td ><center><a class='waves-effect waves-light btn'  id='" +ficha + "' value='"+a.getIdP()+"' onclick='tablaAprendices(this)'><i class=\"material-icons\" id=''>add</i></a></center></td>");//<i class=\"material-icons\" id='bntTablaFaltasAprendices'>add</i>
                            out.println("<tr>");
                        }
                        out.println("</tbody>");
                        out.println("</table>");
                        }
                    } else {
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
                case "faltas":
                    listaA = dao.faltas(Integer.parseInt(idProgramacion), Integer.parseInt(documento));
                    if (listaA == null || listaA.isEmpty()) {
                        out.println("<center><h5>El aprendiz no ha faltado</h5></center>");
                    } else {
                        out.println("<center><h5>Faltas del aprendiz</h5></center>");
                        out.println("<br>");
                        out.println("<table class='highlight bordered' >");
                        out.println("<thead>");
                        out.println("<tr>");
                        out.println("<th data-field='id'><center>Fecha</center></th>");
                        out.println("<th data-field='id'><center>Novedad</center></th>");
                        out.println("</tr>");
                        out.println("</thead>");
                        out.println("<tbody>");
                        for (int j = 0; j < listaA.size(); j++) {
                            a = listaA.get(j);
                            json = "[{idProgramacion: \n" + a.getIdP() + "\n, ficha:" + ficha + "\n}]";
                            //System.out.println(json);
                            out.println("<tr>");
                            out.println("<td id='" + "" + "' onclick=''>" + a.getFecha() + "</td>");
                            if (a.getNovedad() == null || a.getNovedad().isEmpty() || a.getNovedad().equals("null")) {
                                out.println("<td >N / A</td>");
                            } else {
                                out.println("<td id='columnConInfo' value='" + json + "'>" + a.getNovedad() + "</td>");//<i class=\"material-icons\" id='bntTablaFaltasAprendices'>add</i>
                            }
                            out.println("<tr>");
                        }
                        out.println("</tbody>");
                        out.println("</table>");
                    }
                    break;
                case "diasAsistidos":
                    listaA = dao.diasAsistidos(Integer.parseInt(idProgramacion), Integer.parseInt(documento));
                    if (listaA == null || listaA.isEmpty()) {
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
                        json = jsonO.toJson(listaA);
                        out.println(json);
                    }
                    break;
                case "tiempoEnClase":
                    String tmp = dao.tiempoEnClase(Integer.parseInt(idProgramacion), Integer.parseInt(documento));
                    if (tmp == null || tmp.equals("") || tmp.isEmpty()) {
                        out.println("<br>");
                        out.println("<center><h5>El Aprendiz no tiene horas de clase asistidas</h5></center>");
                        out.println("");
                    } else {
                        out.println("<br>");
                        out.println("<center><h5>Tiempo del aprendiz en la competencia</h5></center>");
                        out.println("<center><h4>" + tmp + "</h4></center>");
                    }
                    break;
                case "fichasInstructor3"://listando programas
                    if (!idInstructor.isEmpty()) {
                        listaF = daoF.fichas();
                        if (listaF.isEmpty() || listaF == null) {
                            out.println("<center><h4>Usted no tiene fichas asiciadas<h4></center>");
                        } else {
                            for (int j = 0; j < listaF.size(); j++) {
                                f = listaF.get(j);
                                out.println("<center>");
                                out.println("<div class='col s2 center-align hoverable' id='" + f.getId() + "' value='" + f.getNumeroFicha() + "' name='cuadroFicha' onclick='tablaAprendicesFicha(this)' style='background-color: #bbdefb; padding: 5px; background-clip: content-box'><h5>" + f.getNumeroFicha() + "</h5></div>");
                                out.println("</center>");
                            }
                        }
                    } else {
                        //  out.println("No hay instructor logueado");
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Error\",\n"
                                + "                                text: \"No se ha logueado un instructor\",\n"
                                + "                                type: \"error\",\n"
                                + "                                timer: 2200,\n"
                                + "                                showConfirmButton: true});"
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
