/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.InsertarInstructor;
import beans.Usuario;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import util.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebas
 */
public class Instructor {

    public int insertarUsuario(InsertarInstructor U) {
        CallableStatement procedure = null;

        int respuesta = 0;
        Connection con = Conexion.conectar("mysql");
        String sql = "{CALL insertUsuario (?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            procedure = (CallableStatement) con.prepareCall(sql);

            procedure.setString(1, U.getDocumento());
            procedure.setString(2, U.getTipoDocumento());
            procedure.setString(3, U.getNombre());
            procedure.setString(4, U.getApellido());
            procedure.setString(5, null);
            procedure.setString(6, U.getFecha());
            procedure.setString(7, U.getCorreo());
            procedure.setString(8, U.getTelefono());
            procedure.setInt(9, 2);
            procedure.setString(10, U.getClave());
            procedure.registerOutParameter(11, Types.INTEGER);
            procedure.execute();
            respuesta = procedure.getInt(11); //obtiene lo retornado en el procedimiento

        } catch (SQLException ex) {
            System.out.println("Error al insertar " + ex.getMessage());
        } finally {
            try {
                procedure.close();
                con.close();
                return respuesta;
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexion " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean foto(String foto, String numero) {
        boolean h = false;
        CallableStatement procedure;
        Connection con = Conexion.conectar("mysql");
        String sql = "{CALL foto(?,?)}";
        try {

            procedure = (CallableStatement) con.prepareCall(sql);
            procedure.setString(1, foto);
            procedure.setString(2, numero);
            if (procedure.executeUpdate() != 0) {
                h = true;
            }
            procedure.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("error al preparar el procedimiento" + ex);
        }
        return h;
    }

    public List<Usuario> fotosInstructor() {
        PreparedStatement pstm = null;
        Connection con = null;
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList();
        try {
            String sql = "select id,foto,nombre,apellido from usuario,estado where idUsuario=id AND idTipoUsuario=2 AND estado.tipo='1'";
            con = Conexion.conectar("mysql");
            pstm = (PreparedStatement) con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setFoto(rs.getString("foto"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setId(rs.getInt("id"));
                lista.add(u);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error listando fotos " + ex.getMessage());
        } finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                //Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error cerrando conexion " + ex.getMessage());
            } finally {
                return lista;
            }
        }

    }

    public List<Usuario> fotosSecretaria() {
        PreparedStatement pstm = null;
        Connection con = null;
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList();
        try {
            String sql = "select id,foto from usuario where idTipoUsuario=3";
            con = Conexion.conectar("mysql");
            pstm = (PreparedStatement) con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setFoto(rs.getString("foto"));
                lista.add(u);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error listando fotos " + ex.getMessage());
        } finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                //Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error cerrando conexion " + ex.getMessage());
            } finally {
                return lista;
            }
        }

    }

    public List<Usuario> fotosAprendiz() {
        PreparedStatement pstm = null;
        Connection con = null;
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList();
        try {
            String sql = "select id,foto from usuario where idTipoUsuario=4";
            con = Conexion.conectar("mysql");
            pstm = (PreparedStatement) con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setFoto(rs.getString("foto"));
                lista.add(u);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error listando fotos " + ex.getMessage());
        } finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                //Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error cerrando conexion " + ex.getMessage());
            } finally {
                return lista;
            }
        }

    }

    public int ActualizarInstructor(InsertarInstructor U) {
        CallableStatement procedure = null;

        int respuesta = 0;
        Connection con = Conexion.conectar("mysql");
        String sql = "{CALL ActualizarUsuario(?,?,?,?,?,?,?,?,?,?)}";
        try {
            procedure = (CallableStatement) con.prepareCall(sql);

            procedure.setString(2, U.getDocumento());
            procedure.setString(3, U.getTipoDocumento());
            procedure.setString(4, U.getNombre());
            procedure.setString(5, U.getApellido());
            procedure.setString(6, null);
            procedure.setString(7, U.getFecha());
            procedure.setString(8, U.getCorreo());
            procedure.setString(9, U.getTelefono());
            procedure.setString(1, U.getClave());
            procedure.registerOutParameter(10, Types.INTEGER);
            procedure.execute();
            respuesta = procedure.getInt(10); //obtiene lo retornado en el procedimiento

        } catch (SQLException ex) {
            System.out.println("Error al insertar " + ex.getMessage());
        } finally {
            try {
                procedure.close();
                con.close();
                return respuesta;
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexion " + ex.getMessage());
            }
        }
        return respuesta;
    }

}
