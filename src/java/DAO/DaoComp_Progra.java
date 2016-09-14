/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Comp_Progra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;

/**
 *
 * @author Emerson Javid Gonzalez Morales
 * @Fecha 19/06/2016
 */
public class DaoComp_Progra {
    public boolean insertar(Comp_Progra cp){
            Connection con = null;
            PreparedStatement pstm = null;
            String sql;
            boolean retorno = false;
        try {
            sql = "INSERT INTO comp_progra (idCompetencia,idProgramacion) VALUES (?,?)";
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, cp.getIdCompetencia());
            pstm.setInt(2, cp.getIdProgramacion());
            if(pstm.executeUpdate() == 1){
                retorno = true;
            }
           /* pstm.close();
            con.close();
            return true;*/
        } catch (SQLException ex) {
            System.out.println("error ingresando en comp_progra " + ex);
            //return false;
        }finally{
                try {
                    pstm.close();
                    con.close();
                    return retorno;
                } catch (SQLException ex) {
                    System.out.println("Error cerrando la conexion (DaoComp_progra)" +ex);
                }
        }
        return retorno;
    }
    //consulta para ver si existe una competencia en la entidad comp_progra
    public int consultarCompetencia(Comp_Progra cp){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int retorno = 0;
        String sql = "select count(idCompetencia) from comp_progra where idcompetencia = ?;";
        try {
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, cp.getIdCompetencia());
            rs = pstm.executeQuery();
            if(rs.next()){
                retorno = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error en consultar si hay competencia en la entidad comp_progra "+ex);
        }finally{
           try {
            rs.close();
            pstm.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error cerrando conexion en daoComp_progra "+ex);
        }finally{
            return retorno;
        } 
        }
    }
    //Eliminación en cascada, es decir,
    //elimina en la entidad competencias y despues elimina en la entidad comp_progra
//servirá para eliminar algo de la base de datos recibe un objeto que servira para la condicion para eliminar
    public boolean eliminar(Comp_Progra cp) {
        PreparedStatement pstm = null;
        String sql = "DELETE pc FROM competencias as c, comp_progra as pc WHERE c.idCompetencia=pc.idCompetencia AND c.idCompetencia=?";
        Connection con = null;
        boolean retorno = false;
        try {
            con = Conexion.conectar("mysql");//se hace la conexion con la base de datos (se emplea)
            pstm = con.prepareStatement(sql);//se prepara la sentencia sql
            pstm.setInt(1,cp.getIdCompetencia());//se le envia el objeto a la consulta
            if(pstm.executeUpdate() != 0){//se ejecuta la sentencia consulta
                retorno =  true;//asignamos si efectivamente elimino
            }
        } catch (SQLException ex) {//capturamos los errores
            System.out.println("Error eliminando Competencias relacionadas2: " + ex);
        }finally{
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando las conexion en daoProgramaXcompetencia eliminar " +ex);
            }finally{
                return retorno;//retornamos que no se eliminó
            }
        }
    }
}
