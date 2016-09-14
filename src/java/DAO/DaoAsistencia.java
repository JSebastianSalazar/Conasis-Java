/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Asistencia;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;

/**
 *
 * @author Emerson Javid Gonzalez Morales
 * @fecha 18/08/2016
 */
public class DaoAsistencia {
    
    public int verificacionFicha(int idP) {
        Connection con = null;
        CallableStatement cStmt = null;
        String sql;
        sql = "{CALL verificacionFichaAsistencia(?,?)}"; //
        int retorno = 3;
        try {
            con = Conexion.conectar("mysql");
            cStmt = con.prepareCall(sql);
            cStmt.setInt(1, idP);
            cStmt.registerOutParameter(2, Types.INTEGER);
            cStmt.execute();
            retorno =  cStmt.getInt(2);
        } catch (SQLException ex) {
            System.out.println("Error en la verificación de la ficha " + ex);
        } finally {
            try {
                cStmt.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion en la verificación de la ficha " + ex);
            } finally {
                return retorno;
            }
        }
    }
    
    //vertirá todos los aprendices pertenecientes a la ficha escogida a la entidad asistencia
    //cuando se seleccione que va a entrar,es decir, cuando se va a registrar la asistencia
    public boolean  vertimientoAprendices(int idP, String ficha){
        Connection con = null;
        PreparedStatement pstm = null;
        boolean retorno = false;
        String sql = "insert into asistencia (entradaSalida, fechaHora, idUsuario, idProgramacion)\n" +
                    "select '3',NOW(), id, ?\n" +//el parametro ? es el id de la programacion
                    "from usuario\n" +
                    "WHERE numeroFicha=? AND idTipoUsuario=4;";//este parametro ? es el numero de la ficha
         try {
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idP);
            pstm.setString(2, ficha);
            if(pstm.executeUpdate() != 0){
                retorno = true;
            }
        } catch (SQLException ex) {
             System.out.println("Error en vertimientoAprendices "+ex);
        }finally{
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en vertimientoAprendices " +ex);
            }finally{
                return retorno;
            }
         }
    }
    //SE UTILIZA CUANDO UN APRENDIZ FALTÓ POR MARCAR ASISTENCIA (LLEGO RETRASDO)
    public List tomaAsistencia(int idP, String ficha, String documento, String tipo) {
        Connection con = null;
        CallableStatement cStmt = null;
        String sql;
        sql = "{CALL tomaAsistencia(?,?,?,?,?,?,?,?,?,?,?)}"; //CALL tomaAsistencia(2,'901528','1020478637','0',@hora,@respuesta,@nombre,@apellido)
        List retorno = new ArrayList();
        try {
            con = Conexion.conectar("mysql");
            cStmt = con.prepareCall(sql);
            cStmt.setInt(1, idP);
            cStmt.setString(2, ficha);
            cStmt.setString(3, documento);
            cStmt.setString(4, tipo);
            cStmt.registerOutParameter(5, Types.TIME);//retorna un entero
            cStmt.registerOutParameter(6, Types.TINYINT);
            cStmt.registerOutParameter(7, Types.VARCHAR);
            cStmt.registerOutParameter(8, Types.VARCHAR);
            cStmt.registerOutParameter(9, Types.INTEGER);
            cStmt.registerOutParameter(10, Types.TIME);//retorna un entero
            cStmt.registerOutParameter(11, Types.VARCHAR);
            cStmt.execute();
            retorno.add(0, cStmt.getString(5));//no serecupera como INT ya que solo mostraria la HORA
            retorno.add(1, cStmt.getInt(6));
            retorno.add(2, cStmt.getString(7));
            retorno.add(3, cStmt.getString(8));
            retorno.add(4, cStmt.getInt(9));
            retorno.add(5, cStmt.getString(10));//no serecupera como INT ya que solo mostraria la HORA
            retorno.add(6, cStmt.getString(11));
        } catch (SQLException ex) {
            System.out.println("Error en toma de asistencia " + ex);
        } finally {
            try {
                cStmt.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion en la toma de asistencia " + ex);
            } finally {
                return retorno;
            }
        }
    }

    //SE EJECUTA CUANDO ESA PROGRAMACIÓN VA A MARCAR LA ENTRADA Y NO HA MARCADO LA SALIDA
    public List tomaAsistencia2(int idP, String ficha, String documento, String tipo) {
        Connection con = null;
        CallableStatement cStmt = null;
        String sql;
        sql = "{CALL tomaAsistencia2(?,?,?,?,?,?,?,?,?)}"; //CALL tomaAsistencia(2,'901528','1020478637',@hora,@respuesta,@nombre,@apellido,@faltas)
        List retorno = new ArrayList();
        try {
            con = Conexion.conectar("mysql");
            cStmt = con.prepareCall(sql);
            cStmt.setInt(1, idP);
            cStmt.setString(2, ficha);
            cStmt.setString(3, documento);
            //cStmt.setString(4, "0");
            cStmt.registerOutParameter(4, Types.TIME);//retorna un entero
            cStmt.registerOutParameter(5, Types.TINYINT);
            cStmt.registerOutParameter(6, Types.VARCHAR);
            cStmt.registerOutParameter(7, Types.VARCHAR);
            cStmt.registerOutParameter(8, Types.INTEGER);
            cStmt.registerOutParameter(9, Types.VARCHAR);
            cStmt.execute();
            retorno.add(0, cStmt.getString(4));//no serecupera como INT ya que solo mostraria la HORA
            retorno.add(1, cStmt.getInt(5));
            retorno.add(2, cStmt.getString(6));
            retorno.add(3, cStmt.getString(7));
            retorno.add(4, cStmt.getInt(8));
            retorno.add(5, cStmt.getString(9));
        } catch (SQLException ex) {
            System.out.println("Error en toma de asistencia 2 " + ex);
        } finally {
            try {
                cStmt.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion en la toma de asistencia 2 " + ex);
            } finally {
                return retorno;
            }
        }
    }

//SE EJECUTA CUANDO LA FICHA EN LA PROGRAMACIÓN YA TOMO ASISTENCIA    
        public List tomaAsistenciaSalida(int idP, String ficha, String documento, String tipo) {
        Connection con = null;
        CallableStatement cStmt = null;
        String sql;
        sql = "{CALL tomaAsistenciaSalida(?,?,?,?,?,?,?,?,?,?)}"; //CALL tomaAsistencia(2,'901528','1020478637',@hora,@respuesta,@nombre,@apellido,@faltas)
        List retorno = new ArrayList();
        try {
            con = Conexion.conectar("mysql");
            cStmt = con.prepareCall(sql);
            cStmt.setInt(1, idP);
            cStmt.setString(2, ficha);
            cStmt.setString(3, documento);
            //cStmt.setString(4, "0");
            cStmt.registerOutParameter(4, Types.TIME);//retorna un entero
            cStmt.registerOutParameter(5, Types.TINYINT);
            cStmt.registerOutParameter(6, Types.VARCHAR);
            cStmt.registerOutParameter(7, Types.VARCHAR);
            cStmt.registerOutParameter(8, Types.INTEGER);
            cStmt.registerOutParameter(9, Types.TIME);//retorna un entero
            cStmt.registerOutParameter(10, Types.VARCHAR);
            cStmt.execute();
            retorno.add(0, cStmt.getString(4));//no serecupera como INT ya que solo mostraria la HORA
            retorno.add(1, cStmt.getInt(5));
            retorno.add(2, cStmt.getString(6));
            retorno.add(3, cStmt.getString(7));
            retorno.add(4, cStmt.getInt(8));
            retorno.add(5, cStmt.getString(9));//no serecupera como INT ya que solo mostraria la HORA
            retorno.add(6, cStmt.getString(10));
        } catch (SQLException ex) {
            System.out.println("Error en toma de asistencia 2 " + ex);
        } finally {
            try {
                cStmt.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion en la toma de asistencia 2 " + ex);
            } finally {
                return retorno;
            }
        }
    }        
//MUESTRA LAS VECES QUE EL INSTRUCTOR HA DICTADO CLASE EN LA FICHA
    public List InasistenciaFicha(int idInstructor, String ficha) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;
        Connection con = null;
        List lista = null;
        Asistencia a;
        try {
            sql = "SELECT distinct p.idProgramacion, c.nomCompetencia, DATE(a.fechaHora) "
                    + "FROM usuarioXprogramacion as up, programacion as p, ficha as f, asistencia as a, comp_progra as cp, competencias as c "
                    + "WHERE up.idProgramacion=p.idProgramacion AND a.idProgramacion=p.idProgramacion AND p.idFicha=f.id AND "
                    + "p.idProgramacion=cp.idProgramacion AND cp.idCompetencia=c.idCompetencia AND f.numeroFicha=? AND up.idUsuario=? AND a.entradaSalida=3 ORDER BY a.fechaHora DESC";//recive el id delinstructor logeado
            con = Conexion.conectar("mysql");                                                                                           //entradaSalida<>2que es 
            pstm = con.prepareStatement(sql);
            pstm.setString(1, ficha);
            pstm.setInt(2, idInstructor);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                a = new Asistencia();
                a.setFecha(rs.getString(3));
                a.setCompepetencia(rs.getString("nomCompetencia"));
                a.setIdP(rs.getInt("idProgramacion"));
                lista.add(a);
            }
        } catch (SQLException ex) {
            System.out.println("Listando inasistencia de las fichas" + ex);
        } finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion en daoFicha (inasistenciaFicha) " + ex);
            } finally {
                return lista;
            }
        }
    }
//INSERTA LA NOVEDAD DE UN APRENDIZ QUE FALTO A CLASE
    public boolean insertarNovedad(String novedad, int idAprendiz, int idProgramacion, String fecha) {
        PreparedStatement pstm = null;
        Connection con = null;
        boolean retorno;
        String sql;
        retorno = false;
        sql = "UPDATE asistencia SET novedad = ? where idUsuario=? AND idProgramacion=? AND entradaSalida='3' AND date(fechaHora)=?";
        try {
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setString(1, novedad);
            pstm.setInt(2, idAprendiz);
            pstm.setInt(3, idProgramacion);
            pstm.setString(4, fecha);
            if(pstm.executeUpdate() != 0){
                retorno = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error insertando novedad en DaoAsistencia "+ex);
        } finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion en daoAsistencia "+ex);
            } finally {
                return retorno;
            }
        }
    }
    
    //MUESTRA LAS COMPETENCIAS QUE EL INSTRUCTOR DICTA EN LA FICHA
    public List competencias(int idInstructor, String ficha) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;
        Connection con = null;
        List lista = null;
        Asistencia a;
        try {
            sql = "SELECT p.idProgramacion, c.nomCompetencia\n" +
"FROM usuarioXprogramacion as up, programacion as p, ficha as f, comp_progra as cp, competencias as c\n" +
"WHERE up.idProgramacion=p.idProgramacion AND p.idFicha=f.id AND \n" +
"p.idProgramacion=cp.idProgramacion AND cp.idCompetencia=c.idCompetencia AND f.numeroFicha=? AND up.idUsuario=?;";//recive el id delinstructor logeado
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setString(1, ficha);
            pstm.setInt(2, idInstructor);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                a = new Asistencia();
                a.setCompepetencia(rs.getString("nomCompetencia"));
                a.setIdP(rs.getInt("idProgramacion"));
                lista.add(a);
            }
        } catch (SQLException ex) {
            System.out.println("Listando fichas del instructor" + ex);
        } finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion en daoFicha (competencias) " + ex);
            } finally {
                return lista;
            }
        }
    }
    
     //Muestra los aprendices de una ficha
     public List<Asistencia> faltas(int idProgramacion, int idAprendiz){
        PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql;
            Connection con = null;
            List<Asistencia> lista = null;
            Asistencia a;
        try {
            sql = "select date(fechaHora),novedad from asistencia\n" +
                  "where idProgramacion=? and idUsuario=? and entradaSalida='3';";//
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idProgramacion);
            pstm.setInt(2, idAprendiz);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                a = new Asistencia();
                a.setFecha(rs.getString(1));
                a.setNovedad(rs.getString("novedad"));
                lista.add(a);
            }
        } catch (SQLException ex) {
            System.out.println("faltas del aprendiz " + ex);
        }finally{
            try{
            pstm.close();
            rs.close();
            con.close();
            }catch(SQLException ex){
                System.out.println("Error cerrando conexiones en daoAsistencia (faltas) "+ex);
            }finally{
                return lista;
            }
        }
    }
     
     //aun no lo perfecciono en la gráfica
     //Servirá para la grafica de un aprendiz
     public List<Asistencia> diasAsistidos(int idProgramacion, int idAprendiz){
        PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql;
            Connection con = null;
            List<Asistencia> lista = null;
            Asistencia a;
        try {
            sql = "select date(fechaHora),entradaSalida from asistencia where idUsuario=? and idProgramacion=? and entradaSalida<>1;";//
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setInt(2, idProgramacion);
            pstm.setInt(1, idAprendiz);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                a = new Asistencia();
                a.setFecha(rs.getString(1));
                a.setEntradaSalida(rs.getString("entradaSalida"));
                lista.add(a);
            }
        } catch (SQLException ex) {
            System.out.println("dias Asistidos " + ex);
        }finally{
            try{
            pstm.close();
            rs.close();
            con.close();
            }catch(SQLException ex){
                System.out.println("Error cerrando conexiones en daoAsistencia (dias asistidos) "+ex);
            }finally{
                return lista;
            }
        }  
     }
     
        //
        //Tiempo en que el estudiante esta en clase
        public String tiempoEnClase(int idProgramacion, int idAprendiz){
        PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql;
            Connection con = null;
            String retorno = null;
            Asistencia a;
        try {
            sql = "SELECT SEC_TO_TIME( SUM( TIME_TO_SEC( horaEnClase ) ) ) FROM asistencia where idUsuario=? and idProgramacion=? and entradaSalida='1'";
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setInt(2, idProgramacion);
            pstm.setInt(1, idAprendiz);
            rs = pstm.executeQuery();
            if(rs.next()){
                retorno = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println("TiempoEn clase" + ex);
        }finally{
            try{
            pstm.close();
            rs.close();
            con.close();
            }catch(SQLException ex){
                System.out.println("Error cerrando conexiones en daoAsistencia (TiempoEnClase) "+ex);
            }finally{
                return retorno;
            }
        }
    }
        
     
}
