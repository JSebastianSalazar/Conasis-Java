/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Ficha;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

/**
 * @author Emerson Javid Gonzalez Morales
 * @Fecha 19/06/2016
 */
public class DaoFicha {
    
    public  ArrayList<Ficha> consultarFicha(){
       Connection con = null ;
    PreparedStatement st  = null ;
    ResultSet r = null ;
    ArrayList<Ficha> list = null;
    try{
         con = Conexion.conectar("mysql");
         st = con.prepareStatement("SELECT f.id, f.numeroFicha, f.fechaInicio, f.fechaFinal, f.jornada, f.idPrograma, p.nombrePrograma, f.gestor "
                 + "FROM ficha as f, programas as p WHERE p.idPrograma = f.idPrograma");
         r = st.executeQuery();
         list = new ArrayList();
       while(r.next()){
            Ficha f = new Ficha();
            f.setId(r.getInt("id"));
            f.setNumeroFicha(r.getString("numeroFicha"));
            f.setFechaInicio(r.getString("fechaInicio"));
            f.setFechaFinal(r.getString("fechaFinal"));
            f.setJornada(r.getString("jornada"));
            f.setIdPrograma(r.getInt("IdPrograma"));
            f.setGestor(r.getString("gestor"));
            f.setNomPrograma(r.getString("nombrePrograma"));
          list.add(f);
           }
        return list;
      }catch (SQLException e) {
        System.out.println("error conusultando ficha " + e);
      }finally{
           try {
               st.close();
               r.close();
               con.close();
           } catch (SQLException ex) {
               System.out.println("Error cerrando conexion "+ex);
           }finally{
               return list;
           }
    }
}

    //eliminar fichas
    public int eliminarFicha(Ficha f){
           int  prueb = 0;
        Connection con = null;
          PreparedStatement st= null;
        try {
            con = Conexion.conectar("mysql");
            st = con.prepareStatement("DELETE FROM Ficha WHERE id = ?");
            st.setInt(1, f.getId());
            prueb = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error "+e);
            }finally{
            try {
                con.close();
                st.close();
            } catch (SQLException ex) {
                 System.out.println("Error al cerrar la stament" + ex.getMessage());
            }finally{ 
                return prueb;
            }
        }
    
                          
         }
    
    ///Insertar una ficha - 
    public int insertarFicha(Ficha f) {
         int prueba = 0 ;
     CallableStatement cStmt = null;
            String sql;
            Connection con = null;
            sql = "{CALL guardarFicha(?,?,?,?,?,?,?)}";
        try {
            con = Conexion.conectar("mysql");
            cStmt = con.prepareCall(sql);
            cStmt.setString(1, f.getNumeroFicha());
            cStmt.setString(2, f.getFechaInicio());
            cStmt.setString(3, f.getFechaFinal());
            cStmt.setString(4, f.getJornada());
            cStmt.setInt(5, f.getIdPrograma());
            cStmt.setString(6, f.getGestor());
            cStmt.registerOutParameter(7, Types.TINYINT);
            cStmt.execute();
            prueba = cStmt.getInt(7);
        } catch (SQLException ex) {
            //Logger.getLogger(DaoProgramacion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error insertando ficha " + ex);
        }finally{
                try {
                    cStmt.close();
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando conexiones en daoFicha (insertarFicha) "+ex);
                }finally{
                    return prueba;
                }
        }
    }
      
   // modificar ficha
    public int modificarFicha(Ficha f) {
         int prueba = 0 ;
     CallableStatement cStmt = null;
            String sql;
            Connection con = null;
            sql = "{CALL modificarFicha(?,?,?,?,?,?,?,?)}";
        try {
            con = Conexion.conectar("mysql");
            cStmt = con.prepareCall(sql);
            cStmt.setInt(1, f.getId());
            cStmt.setString(2, f.getNumeroFicha());
            cStmt.setString(3, f.getFechaInicio());
            cStmt.setString(4, f.getFechaFinal());
            cStmt.setString(5, f.getJornada());
            cStmt.setInt(6, f.getIdPrograma());
            cStmt.setString(7, f.getGestor());
            cStmt.registerOutParameter(8, Types.TINYINT);
            cStmt.executeUpdate();
            prueba = cStmt.getInt(8);
        } catch (SQLException ex) {
            System.out.println("Error modificando ficha " + ex);
        }finally{
                try {
                    cStmt.close();
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error cerrando conexiones en daoFicha (modificarFicha) "+ex);
                }finally{
                    return prueba;
                }
        }
    }
    
    
    //sirve para listar las fichas en un combobox de la programacion
    public List<Ficha> fichas(){
        PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql;
            Connection con = null;
            List<Ficha> lista = null;
            Ficha f;
        try {
            sql = "SELECT id,numeroFicha,idPrograma FROM ficha";
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                f = new Ficha();
                f.setId(rs.getInt("id"));
                f.setNumeroFicha(rs.getString("numeroFicha"));
                f.setIdPrograma(rs.getInt("idPrograma"));
                lista.add(f);
            }
            
        } catch (SQLException ex) {
            System.out.println("Listando fichas comboox " + ex);
        }finally{
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion en daoFicha (fichas) "+ex);
            }finally{
                return lista;
            }
        }
        
    }
    //consultando las fichas por el id del programa
    public List<Ficha> fichasPorPrograma(int idPrograma){
        PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql;
            Connection con = null;
            List<Ficha> lista = null;
            Ficha f;
        try {
            sql = "SELECT f.id,f.numeroFicha FROM ficha as f, programas as p WHERE p.idPrograma=f.idPrograma AND p.idPrograma=?";
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idPrograma);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                f = new Ficha();
                f.setId(rs.getInt("id"));
                f.setNumeroFicha(rs.getString("numeroFicha"));
                lista.add(f);
            }
            
        } catch (SQLException ex) {
            System.out.println("Listando fichas comboox " + ex);
        }finally{
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion en daoFicha (fichasPorProgramas) "+ex);
            }finally{
            return lista;
            }
        }
    }
    
    
    //consultando las fichas a la que el instructor tiene clase
   //consultando las fichas por el id del programa
    public List<Ficha> fichasInstructor(int idInstructor){
        PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql;
            Connection con = null;
            List<Ficha> lista = null;
            Ficha f;
        try {
            sql = "SELECT f.numeroFicha, f.id, p.idProgramacion,f.gestor " +
                    "FROM programacion as p, ficha as f, usuarioXprogramacion up " +
                    "WHERE up.idProgramacion=p.idProgramacion AND p.idFicha=f.id AND up.idUsuario=? GROUP BY f.numeroFicha;";//recive el id delinstructor logeado
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idInstructor);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                f = new Ficha();
                f.setId(rs.getInt("id"));
                f.setNumeroFicha(rs.getString("numeroFicha"));
                f.setIdProgramacion(rs.getInt("idProgramacion"));
                f.setGestor(rs.getString("gestor"));
                lista.add(f);
            }
            
        } catch (SQLException ex) {
            System.out.println("Listando fichas que el instructor tiene asociada" + ex);
        }finally{
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion en daoFicha (fichasInstructor) "+ex);
            }finally{
            return lista;
            }
        }
    } 
    
    
}
