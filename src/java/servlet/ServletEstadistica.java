/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DAOUsuario;
import DAO.DaoFicha;
import beans.Ficha;
import com.google.gson.Gson;
import com.sun.xml.ws.security.impl.policy.Constants;
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
 * @author Emerson
 */
public class ServletEstadistica extends HttpServlet {

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
            String numeroFicha;
            List<Ficha> listaF;
            List genero;
            Ficha f;
            DaoFicha daoF;
            DAOUsuario daoU;
            String json;
            Gson jsonO;
            List estrato;
            List Mun;
            String numeroFicha1;
            String numeroFicha2;
            HttpSession session = request.getSession(true);

            validacion = request.getParameter("validacion");
            numeroFicha = request.getParameter("numeroFicha");
            numeroFicha1 = request.getParameter("numeroFicha1");
            numeroFicha2 = request.getParameter("numeroFicha2");
            System.out.println("resul" + numeroFicha1);

            genero = new ArrayList();
            // estrato = new ArrayList();

            daoU = new DAOUsuario();

            switch (validacion) {
                case "stdGenero":
                    genero = daoU.stdGenero(numeroFicha);
                    System.out.println(numeroFicha);
                    System.out.println("z" + genero.size());
                    session.setAttribute("fichaN", numeroFicha);

                    if (genero.isEmpty() || genero == null) {
                        response.setStatus(200);
                        out.println("No hay datos para esta ficha");

                    } else {

                        response.setStatus(200);
                        out.println(genero.get(0) + "," + genero.get(1));
                        session.setAttribute("genero0", genero.get(0));
                        session.setAttribute("genero1", genero.get(1));

                    }
                    break;
                case "stdEstrato67":
                    estrato = daoU.estrato(numeroFicha1);
                    System.out.println("ser" + estrato.size());
                    if (estrato.isEmpty() || estrato == null) {
                        response.setStatus(200);
                        out.println("No hay datos para esta ficha");
                    } else {
                        response.setStatus(200);
                        out.println(estrato.get(0) + "," + estrato.get(1) + "," + estrato.get(2) + "," + estrato.get(3) + ","
                                + estrato.get(4) + "," + estrato.get(5));
                    }
                case "stMunicipio":
                    System.out.println("el llego aca2");
                    Mun = daoU.MunicipioEstadistica(numeroFicha2);

                    if (Mun.isEmpty() || Mun == null) {
                        response.setStatus(200);
                        out.println("No hay datos para esta ficha");
                    } else {
                        response.setStatus(200);
                        out.println(Mun.get(0) + "," + Mun.get(1) + "," + Mun.get(2) + "," + Mun.get(3) + ","
                                + Mun.get(4) + "," + Mun.get(5) + "," + Mun.get(6) + "," + Mun.get(7) + "," + Mun.get(8) + "," + Mun.get(9));
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
