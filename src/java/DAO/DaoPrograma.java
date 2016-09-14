/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 */
package DAO;


import beans.Programa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;

/**
 *
 * @author Emerson Javid Gonzalez Morales
 * @Fecha 05/06/2016
 */
public class DaoPrograma{//definiendo la clase DaoCompetencias e implementand los metos de la interfaz
//servirá para almacenar en la base de datos mediante el parametro de entrada de un objeto que contiene los datos a almacenar

    public boolean insertar(Programa p) {//se recive un objeto y se devuelve un si o no se almaceno el programa
        PreparedStatement pstm = null;
        String sql = "INSERT INTO programas (nombrePrograma) VALUES (?)";
        Connection con = null;
        boolean retorno = false;
        try {
            con = Conexion.conectar("mysql");//se hace la conexion con la base de datos (se emplea)
            pstm = con.prepareStatement(sql);//se prepara la sentencia sql
            pstm.setString(1, p.getNomPrograma());//se le envia el objeto a la consulta
            if(pstm.executeUpdate() != 0){//se ejecuta la sentencia consulta
                retorno = true;
            }
        } catch (SQLException ex) {// se captura algun error en la ejecucion de la sentencia
            System.out.println("Error insertar Programa: "+ ex);
        }finally{
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion en daoPrograma (insertar) "+ex);
            }finally{
                return retorno;
            }
        }
    }
//servirá para eliminar algo de la base de datos recibe un objeto que servira para la condicion para eliminar
    public boolean eliminar(Programa p) {
        PreparedStatement pstm = null;
            Connection con = null;
            boolean retorno = false;
        try {
            con = Conexion.conectar("mysql");//se hace la conexion con la base de datos (se emplea)
            String sql = "DELETE FROM programas WHERE idPrograma = ?";
            pstm = con.prepareStatement(sql);//se prepara la sentencia sql
            pstm.setInt(1, p.getIdPrograma());//se le envia el objeto a la consulta
            if(pstm.executeUpdate() != 0){//se ejecuta la sentencia consulta
                retorno = true;
            }
        } catch (SQLException ex) {//capturamos los errores
            System.out.println("Error al eliminar un programa: "+ ex);
        }finally{
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion en daoPrograma (eliminar) "+ex);
            }finally{
                return retorno;
            }
        }
    }
//servirá para modificar en la base de datos y lo que se va a modificar sera lo que ingrese ppor el parametro de entrada

    public boolean modificar(Programa p) {
        PreparedStatement pstm = null;
            Connection con = null;
            boolean retorno = false;
        try {
            con = Conexion.conectar("mysql");//se hace la conexion con la base de datos (se emplea)
            String sql = "UPDATE programas SET nombrePrograma = ? WHERE idPrograma = ? ";
            pstm = con.prepareStatement(sql);//se prepara la sentencia sql
            //se le envia el objeto a la consulta
            pstm.setString(1, p.getNomPrograma());
            pstm.setInt(2, p.getIdPrograma());
            int y = pstm.executeUpdate();//se ejecuta la sentencia consulta
            //se valida si se modofico el registro
            if(y != 0 ){
               retorno = true; 
            }
        } catch (SQLException ex) {// se captura algun error en la ejecucion de la sentencia
            System.out.println("Error al modificar el programa: "+ ex);
        }finally{
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion en daoPrograma (modificar) "+ex);
            }finally{
                return retorno;
            }
        }
    }
//permitira consultar un registro de la base de datos
    public List<Programa> consultar(String programa) {
        PreparedStatement pstm = null;
            ResultSet rs = null;
            Connection con = null;
            List lista = null;
        try {
            con = Conexion.conectar("mysql");
            String sql = "SELECT * FROM programas WHERE nombrePrograma LIKE '%"+programa+"%'";
            pstm = con.prepareStatement(sql);// se prepara la sentencia sql
            lista = new ArrayList();//se instancia un arraylist
            rs = pstm.executeQuery();//se ejecuta la consulta
            //se verifica si hay datos de la consulta
            //se recuperan todos enviandolos al objeto
            //y luego añadiendo cada objeto a el arrayList
            while(rs.next()){
                Programa p = new Programa();
                p.setNomPrograma(rs.getString("nombrePrograma"));
                p.setIdPrograma(rs.getInt("idPrograma"));
                lista.add(p);
            }
        } catch (SQLException ex) {//se capturan los errores
            System.out.println("Error al consultar el nombre del programa: "+ ex);
        }finally{
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoPrograma (consultar)");
            }finally{
                return lista;//se retorna la lista con todos los registros encontrados
            }
        }
    }

//permitirá listar toda una entidad
    public List<Programa> listar() {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection con = null;
        List lista = null;
        String sql = "SELECT * FROM programas";
        try {
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            lista = new ArrayList();//se instancia un arraylist
            rs = pstm.executeQuery();//se ejecuta la consulta
            //se verifica si hay datos de la consulta
            //se recuperan todos enviandolos al objeto
            //y luego añadiendo cada objeto a el arrayList
            while(rs.next()){
                Programa p = new Programa();
                p.setNomPrograma(rs.getString("nombrePrograma"));
                p.setIdPrograma(rs.getInt("idPrograma"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Error listando programas: "+ ex);
        }finally{
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoPrograma (listar)");
            }finally{
                return lista;//se retorna la lista con todos los registros encontrados
            }
        }
    }
}
