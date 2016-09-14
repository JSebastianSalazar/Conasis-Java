/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import beans.Competencias;
import beans.ProgramaXcompetencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

/**
 *
 * @author Emerson Javid Gonzalez Morales
 * @Fecha 05/06/2016
 */
public class DaoProgramaXcompetencia{//definiendo la clase DaoCompetencias e implementand los metos de la interfaz
//servirá para almacenar en la base de datos mediante el parametro de entrada de un objeto que contiene los datos a almacenar
    public boolean insertar(ProgramaXcompetencia pc) {//se recive un objeto y se devuelve un si o no se almaceno en la tabla intermedia
        PreparedStatement pstm = null;
        String sql = "INSERT INTO programaXcompetencia (idPrograma, idCompetencia) VALUES (?,?)";
        Connection con = null;
        boolean retorno = false;
        try {
            con = Conexion.conectar("mysql");//se hace la conexion con la base de datos (se emplea)
            pstm = con.prepareStatement(sql);//se prepara la sentencia sql
            //se le envia el objeto a la consulta
            pstm.setInt(1, pc.getIdPrograma());
            pstm.setInt(2, pc.getIdCompetencia());
            // se ejecuta la sentencia
            //se verifica si existe algun registro
            if(pstm.executeUpdate() != 0){
                retorno = true;
            }
        } catch (SQLException ex) {//capturamos los errores
            System.out.println("Error insertar Programa: "+ ex);
        }finally{
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando las conexiones en daoProgramaXcompetencia insertar "+ex);
            }finally{
                return retorno;//retornamos el si o no se almaceno 
            }
        }
    }
    
    //consulta para ver si existe un programa en la entidad programaxcompetencia
    public int consultarPrograma(ProgramaXcompetencia pc){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int retorno = 0;
        String sql = "select count(idPrograma) from programaXcompetencia where idPrograma = ?;";
        try {
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, pc.getIdPrograma());
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
            System.out.println("Error cerrando conexion en daoProgramaXcompetencia "+ex);
        }finally{
            return retorno;
        } 
        }
    }
    
    //eliminación en cascada, es decir,
    //Elimina todos los datos de la entidad programaXcompetencia
//es el mismo metodo que el anterior solo que cambia es la sentencia sql
    public boolean eliminarTodo(ProgramaXcompetencia pc) {
        PreparedStatement pstm = null;
            String sql = "DELETE pc FROM programas as p, programaXcompetencia as pc WHERE p.idPrograma=pc.idPrograma AND pc.idPrograma=?";
            Connection con = null;
            boolean retorno = false;
        try {
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setInt(1,pc.getIdPrograma());
            if(pstm.executeUpdate() != 0){
                retorno  = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error eliminando Competencias relacionadas1: " + ex);
        }finally{
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando las conexion en daoProgramaXcompetencia eliminartodo "+ ex);
            }finally{
                return retorno;//retornamos que no se eliminó
            }
        }
    }
    
    
    
///SIRVE ES PARA ELIMINAR LAS COMPETENCIAS DE LA ENTIDAD programaXcompetencia
    public boolean eliminar(int idPrograma, int idCompetencia) {
        PreparedStatement pstm = null;
        String sql = "delete from programaXcompetencia where idCompetencia = ? AND idPrograma = ?";
        Connection con = null;
        boolean retorno = false;
        try {
        con = Conexion.conectar("mysql");
        pstm = con.prepareStatement(sql);
        pstm.setInt(2, idPrograma);
        pstm.setInt(1, idCompetencia);
        if(pstm.executeUpdate() != 0){
            retorno = true;
        }
        } catch (SQLException ex) {
            System.out.println("Error eliminando en la tabla programaXcompetencia "+ ex);
        }finally{
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando las conexiones en daoProgramaXcompetencia (eliminar)"+ ex);
            }finally{
                return retorno;
            }
        }
    }
    
    //no esta siendo ultilizado
    public List<Competencias> consultar(String competencia) {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql = "select * FROM "
                    + " programaXcompetencia as pc, competencias as c "
                    + " where pc.idCompetencia=c.idCompetencia AND c.nomCompetencia LIKE '%?%'";
            Connection con = null;
            List<Competencias> lista = new ArrayList();//se instancia un arraylist
        try {
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);//se prepara la sentencia sql
            pstm.setString(1, competencia);//se envia los parametros de la sentencia
            rs = pstm.executeQuery();// se ejecuta la sentencia
            //se verifica si hay datos de la consulta
            //se recuperan todos enviandolos al objeto
            //y luego añadiendo cada objeto a el arrayList
           while(rs.next()){
                Competencias c = new Competencias();
                c.setNomCompetencia(rs.getString("nomCompetencia"));
                c.setIdCompetencia(rs.getInt("idCompetencia"));
                lista.add(c);
            }
        } catch (SQLException ex) {//se capturan los datos
            System.out.println("error consulta con like "+ ex);
        }finally{
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando las conexion en daoProgramaXcompetencia (consultar) "+ ex);
            }finally{
                return lista;//retornamos que no si encontro algo
            }
        }
    }
    
}
