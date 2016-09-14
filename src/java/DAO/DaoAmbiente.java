/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Ambiente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

/**
 * @author Emerson Javid Gonzalez Morales
 * @Fecha 19/06/2016
 */
public class DaoAmbiente extends Conexion{
    public List<Ambiente> ambientes(){
            Connection con = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql;
            List<Ambiente> lista = null;
            Ambiente a;
        try {
            sql = "SELECT * FROM ambiente";
            con = this.conectar("mysql");
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                a = new Ambiente();
                a.setId(rs.getInt("idAmbiente"));
                a.setCapacidad(rs.getString("capacidad"));
                a.setTipoAmbiente(rs.getString("tipoAmbiente"));
                a.setSuministros(rs.getString("suministros"));
                a.setObservacion(rs.getString("observacion"));
                lista.add(a);
            }
        } catch (SQLException ex) {
            System.out.println("Listando instructores comboox " + ex);
            return null;
        }finally{
                try {
                    rs.close();
                    pstm.close();
                    this.desconectar(con);
                    return lista;
                }
                catch (SQLException ex) {
                    //Logger.getLogger(DaoAmbiente.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Error cerrando conexión (DaoAmbiente)"+ex);
                }
        }
        return lista;
    }
    
    public int insertarAmbiente(Ambiente A) {
        int bandera = 0;
        PreparedStatement st = null;
        Connection con = null;
        try {
            con = this.conectar("mysql");
            st =  con.prepareStatement("INSERT INTO Ambiente (capacidad, tipoAmbiente,  suministros, observacion) values (?,?,?,?)");
            st.setString(1, A.getCapacidad());
            st.setString(2, A.getTipoAmbiente());
            st.setString(3, A.getSuministros());
            st.setString(4, A.getObservacion());
            bandera = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error insertando ambiente" + e);
        } finally {
            try {
                st.close();
                this.desconectar(con);
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion "+ex);
            }finally{
                return bandera;
            }
        }
    }

    public int eliminarAmbiente(int id) {
        int bandera = 0;
        PreparedStatement st = null;
        Connection con = null;
        try {
            con = this.conectar("mysql");
            st =  con.prepareStatement("DELETE FROM ambiente WHERE idAmbiente=?");
            st.setInt(1, id);
            bandera = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            try {
                st.close();
                this.desconectar(con);
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion "+ex);
            }finally{
                return bandera;
            }
        }
    }
    
     public int modificarAmbiente(Ambiente A) {
        int bandera = 0;
        PreparedStatement st = null;
        Connection con = null;
        try {
            con = this.conectar("mysql");
            st =  con.prepareStatement("UPDATE Ambiente SET capacidad = ?, tipoAmbiente = ?,  suministros = ?, observacion = ?"
                    + " WHERE idAmbiente=?");
            st.setString(1, A.getCapacidad());
            st.setString(2, A.getTipoAmbiente());
            st.setString(3, A.getSuministros());
            st.setString(4, A.getObservacion());
            st.setInt(5, A.getId());
            bandera = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error modificando ambiente" + e);
        } finally {
            try {
                st.close();
                this.desconectar(con);
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexion "+ex);
            }finally{
                return bandera;
            }
        }
    }
}
