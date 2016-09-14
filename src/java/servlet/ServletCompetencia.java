/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DaoComp_Progra;
import DAO.DaoCompetencia;
import beans.Comp_Progra;
import beans.Competencias;
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
public class ServletCompetencia extends HttpServlet {

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
            String validacion;
            String competencia;
            String idCompetencia;

            idPrograma = request.getParameter("idPrograma");//id lo resivo de cuando se da clic en un campo de la tabla
            validacion = request.getParameter("validacion");//servirá para ver que funcion se esta ejecutando
            competencia = request.getParameter("competencia");//recibe el nombre de la competencia
            idCompetencia = request.getParameter("idCompetencia");

            Competencias c = new Competencias();
            DaoCompetencia daoC;
            DaoComp_Progra daoCp;
            Comp_Progra cp;
            List<Competencias> listC;
            int id;
            Gson json;
            String jsons;

            switch (validacion) {
                case "dependiente":
                    if (idPrograma != null) {//evitando un error al iniciar la aplicacion
                        id = Integer.parseInt(idPrograma);
                        daoC = new DaoCompetencia();
                        listC = daoC.listar(id);
                        if (listC.isEmpty() || listC == null) {//verificando que el hay competencias(no hay competencias) 
                            out.println("<p>No hay competencias asociadas al programa<p>");
                        } else {//hay competencias
                            out.println("<table class='highlight'>");
                            out.println("<thead>");
                            out.println("<tr>");
                            out.println("<th data-field='id'>Competencias</th>");
                            out.println("</tr>");
                            out.println("</thead>");
                            out.println("<tbody>");
                            for (int i = 0; i < listC.size(); i++) {
                                c = listC.get(i);
                                out.println("<tr style='background-color: #bbdefb'>");
                                out.println("<td class='tdCompe"+c.getIdCompetencia()+"' id='" + c.getIdCompetencia() + "' onclick='idCompetenciaIntermedia(this)'>" + c.getNomCompetencia() + "</td>");
                                out.println("</tr>");
                            }
                            out.println("</tbody>");
                            out.println("</table>");
                        }
                    }
                    break;
                case "combobox":
                    //listando competencias al inicio de la pagina en el combobox con json
                    json = new Gson();
                    daoC = new DaoCompetencia();
                    listC = daoC.listar();
                    if(listC == null || listC.isEmpty()){
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Atecnión\",\n" +
"                                text: \"¡No hay competencias... O pudo ocurrir un error en el servidor!\",\n" +
"                                type: \"warning\",\n" +
"                                timer: 2000,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    }else{
                    jsons = json.toJson(listC);
                    out.println(jsons);
                }
                    break;
                case "vGuardar":
                    c = new Competencias();
                    c.setNomCompetencia(competencia);
                    daoC = new DaoCompetencia();
                    if (daoC.insertar(c)) {
                        //out.println("Insertado con exito");
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Perfecto\",\n" +
"                                text: \"¡La competencia se ha guardado con exito!\",\n" +
"                                type: \"success\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    } else {
                        //out.println("No se ha insertado");
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Error\",\n" +
"                                text: \"¡No se pudo guardar la competencia!\",\n" +
"                                type: \"error\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    }
                    break;
                case "vModificar":
                    c = new Competencias();
                    c.setNomCompetencia(competencia);
                    c.setIdCompetencia(Integer.parseInt(idCompetencia));
                    daoC = new DaoCompetencia();
                    if (daoC.modificar(c)) {
                        //out.println("Modificado con exito");
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Perfecto\",\n" +
"                                text: \"¡La competencia se ha modificado con exito!\",\n" +
"                                type: \"success\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    } else {
                        //out.println("No se ha modificado");
                        out.println("<script>"
                                + "$(document).ready(function () {"
                                + "swal({title: \"Error\",\n" +
"                                text: \"No se pudo modificar la competencia\",\n" +
"                                type: \"error\",\n" +
"                                timer: 2200,\n" +
"                                showConfirmButton: true});"
                                + "});"
                                + "</script>");
                    }
                    break;
                case "vEliminarCompe":

                    daoCp = new DaoComp_Progra();
                    cp = new Comp_Progra();
                    daoC = new DaoCompetencia();
                    c = new Competencias();
                    cp.setIdCompetencia(Integer.parseInt(idCompetencia));
                    System.out.println(idCompetencia);
                    if (daoCp.consultarCompetencia(cp) != 0) {
                        if (daoCp.eliminar(cp)) { //eliminando en la tabla intermedia
                            c.setIdCompetencia(Integer.parseInt(idCompetencia));
                            if (daoC.eliminar(c)) {//eliminando en la tabla competencia
                                out.println("<h5>Eliminado con exito</h5>");
                            } else {
                                out.println("<h5>No se eliminó</h5>");
                            }
                        } else {
                            out.println("<h5>No se eliminó</h5>");
                        }
                    } else {
                        c.setIdCompetencia(Integer.parseInt(idCompetencia));
                        if (daoC.eliminar(c)) {//eliminando en la tabla programa
                            out.println("<h5>Eliminado con exito</h5>");
                        } else {
                            out.println("<h5>No se eliminó</h5>");
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
