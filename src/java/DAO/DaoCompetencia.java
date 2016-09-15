/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Competencias;
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
public class DaoCompetencia { //definiendo la clase DaoCompetencias e implementand los metos de la interfaz
//servirá para almacenar en la base de datos mediante el parametro de entrada de un objeto que contiene los datos a almacenar
   
    public boolean insertar(Competencias c) {//se recive un objeto y se devuelve un si o no se almaceno la competencia
        Connection con = null;
        PreparedStatement pstm = null;
        String sql;
        boolean retorno = false;
        try {
            sql = "INSERT INTO competencias (nomCompetencia) VALUES (?)";
            con = Conexion.conectar("mysql");//se hace la conexion con la base de datos (se emplea)
            pstm = con.prepareStatement(sql);//se prepara la sentencia sql
            pstm.setString(1, c.getNomCompetencia());//se le envia el objeto a la consulta
            if(pstm.executeUpdate() > 0){//se ejecuta la sentencia consulta
                retorno = true;
            }
        } catch (SQLException ex) {// se captura algun error en la ejecucion de la sentencia
            System.out.println("Error insertando competencia: "+ ex);
        }finally{
            try {
                pstm.close();
                con.close();
                return retorno;//retornamos el si o no se almaceno 
            } catch (SQLException ex) {
                System.out.println("error cerrando conexiones en DaoCompetencia (insertar) "+ex);
            }
        }
        return retorno;//retornamos el si o no se almaceno 
    }
//servirá para eliminar algo de la base de datos recibe un objeto que servira para la condicion para eliminar
 
    public boolean eliminar(Competencias c) {
        Connection con = null;
        PreparedStatement pstm = null;
        String sql;
        boolean retorno = false;
        try {
            con = Conexion.conectar("mysql");//se hace la conexion con la base de datos (se emplea)
            sql = "DELETE FROM competencias WHERE idCompetencia = ?";
            pstm = con.prepareStatement(sql);//se prepara la sentencia sql
            pstm.setInt(1, c.getIdCompetencia());//se le envia el objeto a la consulta
            if(pstm.executeUpdate() != 0){//se ejecuta la sentencia consulta
                retorno = true;
            }
        } catch (SQLException ex) {// se captura algun error en la ejecucion de la sentencia
            System.out.println("Error al eliminar una competencia: "+ ex);
        }finally{//cerramos las conexiones y capturamos las exepciones
            try {
                pstm.close();
                con.close();
                return retorno;
            } catch (SQLException ex) {
             System.out.println("error cerrando conexiones en DaoCompetencia (eliminar) "+ex);   
            }
        }
        return retorno;//retornamos el si o no se eliminó
    }
//servirá para modificar en la base de datos y lo que se va a modificar sera lo que ingrese ppor el parametro de entrada
   
    public boolean modificar(Competencias c) {
        Connection con = null;
        PreparedStatement pstm = null;
        String sql;
        boolean vandera = false;
        try {
            con = Conexion.conectar("mysql");//se hace la conexion con la base de datos (se emplea)
            sql = "UPDATE competencias SET nomCompetencia = ? WHERE idCompetencia = ? ";
            pstm = con.prepareStatement(sql);//se prepara la sentencia sql
            //se le envia el objeto a la consulta
            pstm.setString(1, c.getNomCompetencia());
            pstm.setInt(2, c.getIdCompetencia());
            int y = pstm.executeUpdate();//se ejecuta la sentencia consulta
            //se valida si se modofico el registro
            if(y != 0 ){
               vandera = true;
            }
        } catch (SQLException ex) {// se captura algun error en la ejecucion de la sentencia
            System.out.println("Error al modificar el la competencia: "+ ex);
        }finally{
            try {
                pstm.close();
                con.close();
                return vandera;
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en DaoCompetencia (modificar) : "+ ex);
            }
        }
        return vandera;
    }
//permitira consultar un registro de la base de datos
    public List<Competencias> consultar(String competencia) {
        PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql;
            Competencias c;
            Connection con = null;
            List<Competencias> lista = null;
        try {
            c = new Competencias();
            sql = "SELECT * FROM competencias WHERE nomCompetencia LIKE '%?%'";
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);// se prepara la sentencia sql
            pstm.setString(1, competencia); //se envia los parametros de la sentencia
            lista = new ArrayList();//se instancia un arraylist
            rs = pstm.executeQuery();//se ejecuta la consulta
            //se verifica si hay datos de la consulta
            //se recuperan todos enviandolos al objeto
            //y luego añadiendo cada objeto a el arrayList
            while(rs.next()){
                c = new Competencias();
                c.setNomCompetencia(rs.getString("nomCompetencia"));
                c.setIdCompetencia(rs.getInt("idCompetencia"));
                lista.add(c);
            }
        } catch (SQLException ex) {//se capatura el objeto
            System.out.println("Error consultando el nombre competencia: "+ ex);
        }finally{
            try {
                rs.close();
                pstm.close();
                con.close();
                return lista;//se retorna el objeto
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en DaoCompetencia (consultar) : "+ ex);
            }
        }
        return lista;//se retorna el objeto
    }
//permitirá listar toda una entidad de pendiendo de lo ingresado enel parametro de entrada
    public List<Competencias> listar(int id) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT c.nomCompetencia, c.idCompetencia FROM programas as p, competencias as c, "
                + "programaXcompetencia as pc "
                + "WHERE p.idPrograma=pc.idPrograma AND pc.idCompetencia=c.idCompetencia AND pc.idPrograma=?";
        Connection con = null;
        List<Competencias> lista = null;
        try {
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);//se prepara la sentencia sql
            pstm.setInt(1, id);//se envia los parametros de la sentencia
            rs = pstm.executeQuery();// se ejecuta la sentencia
            //se verifica si hay datos de la consulta
            //se recuperan todos enviandolos al objeto
            //y luego añadiendo cada objeto a el arrayList
            lista = new ArrayList();//se instancia un arraylist
           while(rs.next()){
                Competencias c = new Competencias();
                c.setNomCompetencia(rs.getString("nomCompetencia"));
                c.setIdCompetencia(rs.getInt("idCompetencia"));
                lista.add(c);
            }
        } catch (SQLException ex) {//se captutaran los errores 
            System.out.println("Error listando competencias: "+ ex);
        }finally{
            try {
                rs.close();
                pstm.close();
                con.close();
                return lista;//se retorna el objeto
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en DaoCompetencia (listar(int id)) : "+ ex);
            }
        }
        return lista;//se retorna la lista con todos los registros encontrados
    }
//permitirá listar toda una entidad
    public List<Competencias> listar() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;
        List<Competencias> lista = null;
        try {
            sql = "SELECT * FROM competencias;";
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            lista = new ArrayList();//se instancia un arraylist
            rs = pstm.executeQuery();//se ejecuta la consulta
            //se verifica si hay datos de la consulta
            //se recuperan todos enviandolos al objeto
            //y luego añadiendo cada objeto a el arrayList
            while(rs.next()){
                Competencias c = new Competencias();
                c.setNomCompetencia(rs.getString("nomCompetencia"));
                c.setIdCompetencia(rs.getInt("idCompetencia"));
                lista.add(c);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error Listando Competencias: " + ex);
        }finally{
            try {
                rs.close();
                pstm.close();
                con.close();
                return lista;//se retorna el objeto
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en DaoCompetencia (listar()) : "+ ex);
            }
        }
        return lista;//se retorna la lista con todos los registros encontrados
    }
    
    //permitirá listar toda una entidad
    public List<Competencias> listarCompetenciasDependintesFicha(int idPrograma) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;
        List<Competencias> lista = null;
        try {
            sql = "select c.nomCompetencia,c.idCompetencia from programaxcompetencia as p, competencias as c "
                    + "where p.idCompetencia=c.idCompetencia And \n" +
                    "p.idPrograma=?;";
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            lista = new ArrayList();//se instancia un arraylist
            pstm.setInt(1, idPrograma);//se envia los parametros de la sentencia
            rs = pstm.executeQuery();//se ejecuta la consulta
            //se verifica si hay datos de la consulta
            //se recuperan todos enviandolos al objeto
            //y luego añadiendo cada objeto a el arrayList
            while(rs.next()){
                Competencias c = new Competencias();
                c.setNomCompetencia(rs.getString("nomCompetencia"));
                c.setIdCompetencia(rs.getInt("idCompetencia"));
                lista.add(c);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error Listando Competencias: " + ex);
        }finally{
            try {
                rs.close();
                pstm.close();
                con.close();
                return lista;//se retorna el objeto
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en DaoCompetencia (listar()) : "+ ex);
            }
        }
        return lista;//se retorna la lista con todos los registros encontrados
    }
}

   
    

