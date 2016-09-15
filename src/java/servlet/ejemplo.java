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
public class ejemplo extends HttpServlet {

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
        }
    }
    ResultSet rs;
    ArrayList<Municipios1> munis;
    Connection con = Conexion.conectar("mysql");
    PreparedStatement pstm;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*response.setContentType("application/json");
        processRequest(request, response);;*/
        munis = new ArrayList<Municipios1>();
        String valor = request.getParameter("valor1");
        System.out.println(valor);
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
        Gson gson = new Gson();
        String barrio = gson.toJson(munis);
        System.out.println(munis);
        PrintWriter out = response.getWriter();// respuesta
        System.out.println(barrio);//escrie resultado
      
        out.print(barrio);

    }

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
