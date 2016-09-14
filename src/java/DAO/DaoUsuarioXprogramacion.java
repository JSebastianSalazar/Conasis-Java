/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.UsuarioXprogramacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.Conexion;

/**
 *
 * @author Emerson Javid Gonzalez Morales
 * @Fecha 19/06/2016
 */
public class DaoUsuarioXprogramacion {
    public boolean insertar(UsuarioXprogramacion up){
        PreparedStatement pstm = null;
            String sql;
            Connection con = null;
            boolean retorno = false;
        try {
            sql = "INSERT INTO usuarioXprogramacion (idUsuario, idProgramacion) VALUES (?,?)";
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, up.getIdUsuario());
            pstm.setInt(2, up.getIdProgramacion());
            if(pstm.executeUpdate() != 0){
                retorno = true;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DaoUsuarioXprogramacion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error ingresando en daoUsuarioXprogramacion " + ex);
        }finally{
            try{
            pstm.close();
            con.close();
            }catch(SQLException ex){
                System.out.println("Error cerrando conexiones en daoUsuarioXprogramacion (insertar) "+ex);
            }finally{
                return retorno;
            }
        }
        
    }
    
}
