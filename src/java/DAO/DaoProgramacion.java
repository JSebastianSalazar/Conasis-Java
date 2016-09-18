/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Programacion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;

/**
 *
 * @author Emerson Javid Gonzalez Morales
 * @Fecha 19/06/2016
 */
public class DaoProgramacion {

    /*public boolean insertar(Programacion p) {//no esta siendo utilizado lo reemplazó el procedimiento almacenado
     PreparedStatement pstm;
     String sql;
     Connection con;
     sql = "INSERT INTO programacion (trimestre,fechaInicio,"
     + "fechaFinal,diaSemana,horaIngreso,horaSalida,idAmbiente,idFicha) "
     + "VALUES (?,?,?,?,?,?,?,?)";
     try {
     con = Conexion.conectar("mysql");
     pstm = con.prepareStatement(sql);
     pstm.setString(1, p.getTrimestre());
     pstm.setDate(2, p.getFechaInicio());
     pstm.setDate(3, p.getFechaFinal());
     pstm.setString(4, p.getDiaSemana());
     pstm.setString(5, p.getHoraIngreso());
     pstm.setString(6, p.getHoraSalida());
     pstm.setInt(7, p.getIdAmbiente());
     pstm.setInt(8, p.getIdFicha());
     pstm.executeUpdate();
     pstm.close();
     con.close();
     return true;
     } catch (SQLException ex) {
     //Logger.getLogger(DaoUsuarioXprogramacion.class.getName()).log(Level.SEVERE, null, ex);
     System.out.println("error ingresando una programación " + ex);
     return false;
     }
     }*/
    public List<Programacion> programacionFicha(int idFicha) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;
        Connection con = null;
        List<Programacion> lista = null;
        Programacion p;
        try {
            sql = "SELECT c.nomCompetencia,p.idProgramacion, p.trimestre, p.fechaInicio, p.fechaFinal, "
                    + "p.diaSemana,p.horaIngreso,p.horaSalida,p.idAmbiente, u.nombre, u.apellido "
                    + "FROM "
                    + "programacion as p, competencias as c, comp_progra as cp, usuario as u, "
                    + "tipoUsuario as tu, usuarioXprogramacion as up "
                    + "WHERE "
                    + "c.idCompetencia=cp.idCompetencia AND cp.idProgramacion=p.idProgramacion AND "
                    + "p.idProgramacion=up.idProgramacion AND "
                    + "up.idUsuario=u.id AND u.idTipoUsuario=tu.idTipoUsuario AND "
                    + "tu.usuario='Instructor' AND p.idFicha=? ORDER BY p.trimestre";
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idFicha);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                p = new Programacion();
                p.setIdProgramacion(rs.getInt("idProgramacion"));
                p.setTrimestre(rs.getString("trimestre"));
                p.setFechaInicio(rs.getDate("fechaInicio"));
                p.setFechaFinal(rs.getDate("fechaFinal"));
                p.setDiaSemana(rs.getString("diaSemana"));
                p.setHoraIngreso(rs.getString("horaIngreso"));
                p.setHoraSalida(rs.getString("horaSalida"));
                p.setIdAmbiente(rs.getInt("idAmbiente"));
                //propiedades de otros objetos
                p.setNomCompetencia(rs.getString("nomCompetencia"));
                p.setNombreI(rs.getString("nombre"));
                p.setApellidoI(rs.getString("apellido"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DaoProgramacion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error consultando programacion por ficha " + ex);
        } finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoProgramación (programaciónFicha) " + ex);
            } finally {
                return lista;
            }
        }
    }

    /*public int conultarUltimoID() {
     try {
     //;
     PreparedStatement pstm;
     ResultSet rs;
     String sql;
     Connection con;
     int id = 0;
     sql = "SELECT MAX(idProgramacion) FROM programacion";
     con = Conexion.conectar("mysql");
     pstm = con.prepareStatement(sql);
     rs = pstm.executeQuery();
     if (rs.next()) {
     id = rs.getInt(1);
     }
     pstm.close();
     rs.close();
     con.close();
     return id;
     } catch (SQLException ex) {
     //Logger.getLogger(DaoProgramacion.class.getName()).log(Level.SEVERE, null, ex);
     System.out.println("Error consultando ultimo ID: " + ex);
     return 0;
     }
     }*/
    public boolean eliminarProgramacion(int id) {
        CallableStatement cStmt = null;
        String sql;
        Connection con = null;
        sql = "{CALL eliminarProgramacion(?)}";
        boolean retorno = false;
        try {
            con = Conexion.conectar("mysql");
            cStmt = con.prepareCall(sql);
            cStmt.setInt(1, id);
            if (cStmt.executeUpdate() != 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DaoProgramacion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error eliminando " + ex);
        } finally {
            try {
                cStmt.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoProgramación (eliminarProgramación) " + ex);
            } finally {
                return retorno;
            }
        }
    }

    /* public boolean modificarProgramacion(Programacion p) {//no esta siendo utilizado
     CallableStatement cStmt = null;
     String sql = null;
     Connection con = null;
     boolean retorno = false;
     try {
     sql = "{CALL modificarProgramacion (?,?,?,?,?,?,?,?,?,?,?)}";
     con = Conexion.conectar("mysql");
     cStmt = con.prepareCall(sql);
     cStmt.setInt(1, p.getIdProgramacion());
     cStmt.setString(2, p.getTrimestre());
     cStmt.setDate(3, p.getFechaInicio());
     cStmt.setDate(4, p.getFechaFinal());
     cStmt.setString(5, p.getDiaSemana());
     cStmt.setString(6, p.getHoraIngreso());
     cStmt.setString(7, p.getHoraSalida());
     cStmt.setInt(8, p.getIdAmbiente());
     cStmt.setInt(9, p.getIdFicha());
     cStmt.setInt(10, p.getIsUsuario());
     cStmt.setInt(11, p.getIdCompetencia());
     if(cStmt.executeUpdate() != 0){
     retorno = true;
     }
     } catch (SQLException ex) {
     //Logger.getLogger(DaoProgramacion.class.getName()).log(Level.SEVERE, null, ex);
     System.out.println("Error modificando " + ex);
     }finally{
     try {
     cStmt.close();
     con.close();
     } catch (SQLException ex) {
     System.out.println("Error cerrando conexiones en daoProgramación (modificarProgramación) "+ex);
     }finally{
     return retorno;
     }
     }
     }*/
    public int modificarProgramacion2(Programacion p) {
        CallableStatement cStmt = null;
        String sql;
        Connection con = null;
        int respuesta = 0;
        try {
            sql = "{CALL modificarProgramacion (?,?,?,?,?,?,?,?,?,?,?,?)}";
            con = Conexion.conectar("mysql");
            cStmt = con.prepareCall(sql);
            cStmt.setInt(1, p.getIdProgramacion());
            cStmt.setString(2, p.getTrimestre());
            cStmt.setDate(3, p.getFechaInicio());
            cStmt.setDate(4, p.getFechaFinal());
            cStmt.setString(5, p.getDiaSemana());
            cStmt.setString(6, p.getHoraIngreso());
            cStmt.setString(7, p.getHoraSalida());
            cStmt.setInt(8, p.getIdAmbiente());
            cStmt.setInt(9, p.getIdFicha());
            cStmt.setInt(10, p.getIsUsuario());
            cStmt.setInt(11, p.getIdCompetencia());
            cStmt.registerOutParameter(12, Types.INTEGER);
            cStmt.execute();
            respuesta = cStmt.getInt(12);
        } catch (SQLException ex) {
            //Logger.getLogger(DaoProgramacion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error modificando2 " + ex);
        } finally {
            try {
                cStmt.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoProgramación (modificarProgramación2) " + ex);
            } finally {
                return respuesta;
            }
        }
    }

    public int insertar2(Programacion p) {
        CallableStatement cStmt = null;
        String sql;
        Connection con = null;
        int respuesta = 0;
        try {
            sql = "{CALL guardarProgramacion (?,?,?,?,?,?,?,?,?,?,?)}";
            con = Conexion.conectar("mysql");
            cStmt = con.prepareCall(sql);
            cStmt.setString(1, p.getTrimestre());
            cStmt.setDate(2, p.getFechaInicio());
            cStmt.setDate(3, p.getFechaFinal());
            cStmt.setString(4, p.getDiaSemana());
            cStmt.setString(5, p.getHoraIngreso());
            cStmt.setString(6, p.getHoraSalida());
            cStmt.setInt(7, p.getIdAmbiente());
            cStmt.setInt(8, p.getIdFicha());
            cStmt.setInt(9, p.getIsUsuario());
            cStmt.setInt(10, p.getIdCompetencia());
            cStmt.registerOutParameter(11, Types.INTEGER);
            cStmt.execute();
            respuesta = cStmt.getInt(11);
        } catch (SQLException ex) {
            //Logger.getLogger(DaoProgramacion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error insertando Programacion " + ex);
        } finally {
            try {
                cStmt.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoProgramación (insertar2) " + ex);
            } finally {
                return respuesta;
            }
        }
    }

    public List<Programacion> competenciasDictadasXintstructor(int id) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;
        Connection con = null;
        List<Programacion> lista = null;
        Programacion p;
        try {
            sql = "SELECT p.idProgramacion, f.numeroFicha, c.nomCompetencia,p.idAmbiente, p.horaIngreso, p.horaSalida "
                    + "FROM programacion as p, ficha as f, competencias as c, usuarioXprogramacion up, comp_progra cp "
                    + "WHERE up.idProgramacion=p.idProgramacion AND p.idFicha=f.id AND p.idProgramacion=cp.idProgramacion "
                    + "AND cp.idCompetencia=c.idCompetencia AND up.idUsuario=? AND p.diaSemana=? ORDER BY p.horaIngreso DESC";
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            String[] dias = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sabado"};
            Date hoy = new Date();
            int numeroDia = 0;
            Calendar cal = Calendar.getInstance();
            cal.setTime(hoy);
            numeroDia = cal.get(Calendar.DAY_OF_WEEK);
            //System.out.println("hoy es " + dias[numeroDia - 1]);
            //pstm.setString(2, "" + dias[numeroDia - 1]);
            pstm.setString(2, "Lunes");//ESTO NO VA
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                p = new Programacion();
                p.setIdProgramacion(rs.getInt("idProgramacion"));
                p.setHoraIngreso(rs.getString("horaIngreso"));
                p.setHoraSalida(rs.getString("horaSalida"));
                p.setIdAmbiente(rs.getInt("idAmbiente"));
                //propiedades de otros objetos
                p.setNomCompetencia(rs.getString("nomCompetencia"));
                p.setNumeroficha(rs.getString("numeroFicha"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DaoProgramacion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error consultando competenciasImplantadasXinstructor" + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoProgramación (competenciasDictadasXintstructor) " + ex);
            } finally {
                return lista;
            }
        }
    }

    ///Serve para mostrar competencias al gestor para tomar asistencia
    public List<Programacion> competenciasGestorF(String gestor, int idInstruc) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;
        Connection con = null;
        List<Programacion> lista = null;
        Programacion p;
        try {
            sql = "SELECT p.idProgramacion, f.numeroFicha, c.nomCompetencia,p.idAmbiente, p.horaIngreso, p.horaSalida \n"
                    + "              FROM programacion as p, ficha as f, competencias as c, usuarioXprogramacion up, comp_progra cp \n"
                    + "              WHERE up.idProgramacion=p.idProgramacion AND p.idFicha=f.id AND p.idProgramacion=cp.idProgramacion \n"
                    + "              AND cp.idCompetencia=c.idCompetencia AND f.gestor=? AND up.idUsuario<>? AND p.diaSemana=? ORDER BY p.horaIngreso DESC";
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setString(1, gestor);
            pstm.setInt(2, idInstruc);
            String[] dias = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sabado"};
            Date hoy = new Date();
            int numeroDia = 0;
            Calendar cal = Calendar.getInstance();
            cal.setTime(hoy);
            numeroDia = cal.get(Calendar.DAY_OF_WEEK);
            //System.out.println("hoy es " + dias[numeroDia - 1]);
            //pstm.setString(3, ""+dias[numeroDia - 1]);
            pstm.setString(3, "Martes");//ESTO NO VA
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                p = new Programacion();
                p.setIdProgramacion(rs.getInt("idProgramacion"));
                p.setHoraIngreso(rs.getString("horaIngreso"));
                p.setHoraSalida(rs.getString("horaSalida"));
                p.setIdAmbiente(rs.getInt("idAmbiente"));
                //propiedades de otros objetos
                p.setNomCompetencia(rs.getString("nomCompetencia"));
                p.setNumeroficha(rs.getString("numeroFicha"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DaoProgramacion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error consultando competenciasImplantadasXinstructor" + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoProgramación (competenciasDictadasXintstructor) " + ex);
            } finally {
                return lista;
            }
        }
    }

    ///sirve por si falta un instructor y el administrador puede tomar asistencia
    ///Serve para mostrar competencias al gestor para tomar asistencia
    public List<Programacion> competenciasAdministrador() {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;
        Connection con = null;
        List<Programacion> lista = null;
        Programacion p;
        try {
            sql = "SELECT p.idProgramacion, f.numeroFicha, c.nomCompetencia,p.idAmbiente, p.horaIngreso, p.horaSalida \n"
                    + "              FROM programacion as p, ficha as f, competencias as c, usuarioXprogramacion up, comp_progra cp \n"
                    + "              WHERE up.idProgramacion=p.idProgramacion AND p.idFicha=f.id AND p.idProgramacion=cp.idProgramacion \n"
                    + "              AND cp.idCompetencia=c.idCompetencia AND p.diaSemana=? ORDER BY p.horaIngreso DESC";
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            String[] dias = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sabado"};
            Date hoy = new Date();
            int numeroDia = 0;
            Calendar cal = Calendar.getInstance();
            cal.setTime(hoy);
            numeroDia = cal.get(Calendar.DAY_OF_WEEK);
            //System.out.println("hoy es " + dias[numeroDia - 1]);
            //pstm.setString(3, ""+dias[numeroDia - 1]);
            pstm.setString(1, "Martes");//ESTO NO VA
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                p = new Programacion();
                p.setIdProgramacion(rs.getInt("idProgramacion"));
                p.setHoraIngreso(rs.getString("horaIngreso"));
                p.setHoraSalida(rs.getString("horaSalida"));
                p.setIdAmbiente(rs.getInt("idAmbiente"));
                //propiedades de otros objetos
                p.setNomCompetencia(rs.getString("nomCompetencia"));
                p.setNumeroficha(rs.getString("numeroFicha"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DaoProgramacion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error consultando competenciasImplantadasXinstructor" + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoProgramación (competenciasDictadasXintstructor) " + ex);
            } finally {
                return lista;
            }
        }
    }
}
