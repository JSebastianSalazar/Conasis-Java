/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Emerson
 */
public class Conexion {
    public Conexion(){
    }
    public static Connection conectar(String db) {
        Connection con = null;
    try {
            if (db.compareTo("mysql") == 0) {
                String conexion = "";
                
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://" + "localhost:3306"
                            + "/conasis", "root", "eme");
                   
                    con.setAutoCommit(true);
                }

            }
            
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage() + "Error En El Controlador, No Se Ha Podido Establecer La Conexi�n con la Base De Datos. Clase Conexion");
        } catch (SQLException sqle) {
            System.out.println(
                    sqle.getMessage() + "  ErrorSQL Problemas en la Ejecucion de Alguna Cla�sula SQL. . Clase Conexion");
        } catch (Exception e) {
            System.out.println(
                    e.getMessage() + "Ha Ocurrido Una Excepci�n. Clase Conexi�n");
        }
        return con;
    }

    public void desconectar(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
        }
    }
    
}
