/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Municipios1;
import com.google.gson.Gson;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Conexion;

/**
 *
 * @author sebas
 */
public class Barrio extends HttpServlet {

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
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Barrio</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Barrio at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    ResultSet rs;
    ArrayList<Municipios1> munis;
    Connection con = Conexion.conectar("mysql");
    PreparedStatement pstm;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        processRequest(request, response);

        munis = new ArrayList<Municipios1>();

        String valor = request.getParameter("valor1");

        try {
            String sql = "select idBarrio, nomBarrio from Barrio where idMunicipio='" + Integer.parseInt(valor) + "' ORDER BY nomBarrio ASC" + "";
            pstm = (PreparedStatement) con.prepareStatement(sql);

            rs = pstm.executeQuery();
            while (rs.next()) {
                munis.add(new Municipios1(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.out.println("error sql" + ex);
        }
        // Json cambio de datos en un formato ligero  con la libreria de google convetir objetos de listas matrices o vectores a un tipo json
        Gson nuevo = new Gson();
        String a = nuevo.toJson(munis); 
        PrintWriter out = response.getWriter();// respuesta
        System.out.println(a);//escrie resultado
        out.println(a);
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
