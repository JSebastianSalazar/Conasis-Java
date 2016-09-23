/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Aprendiz;
import beans.InsertarInstructor;
import beans.InsertarSecretaria;
import beans.Login;
import beans.RecuperarClave1;
import beans.Usuario;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import util.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebas
 */
public class DAOUsuario extends Conexion {

    public List logueo(Login U) {
        CallableStatement procedure = null;

        List respuesta = new ArrayList();
        Connection con = this.conectar("mysql");
        String sql = "{CALL logueo (?,?,?,?,?,?,?,?)}";
        try {
            procedure = (CallableStatement) con.prepareCall(sql);

            procedure.setString(1, U.getUsuario());
            procedure.setString(2, U.getClave());
            procedure.registerOutParameter(3, Types.INTEGER);
            procedure.registerOutParameter(4, Types.VARCHAR);
            procedure.registerOutParameter(5, Types.VARCHAR);
            procedure.registerOutParameter(6, Types.VARCHAR);
            procedure.registerOutParameter(7, Types.INTEGER);
            procedure.registerOutParameter(8, Types.VARCHAR);
            procedure.execute();
            respuesta.add(procedure.getInt(3)); //obtiene lo retornado en el procedimiento)
            respuesta.add(procedure.getString(4)); //obtiene lo retornado en el procedimiento
            respuesta.add(procedure.getString(5)); //obtiene lo retornado en el procedimiento)7
            respuesta.add(procedure.getString(6)); //obtiene lo retornado en el procedimiento)
            respuesta.add(procedure.getInt(7)); //obtiene lo retornado en el procedimiento)
            respuesta.add(procedure.getString(8)); //obtiene lo retornado en el procedimiento)
            System.out.println(procedure.getInt(3) + "2 dao");

        } catch (SQLException ex) {
            System.out.println("Error al insertar " + ex.getMessage());
        } finally {
            try {
                procedure.close();
                this.desconectar(con);

            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexion " + ex.getMessage());
            } finally {
                return respuesta;
            }
        }

    }

    public int insertarSecretaria(InsertarSecretaria U) {
        CallableStatement procedure = null;

        int respuesta = 0;
        Connection con = this.conectar("mysql");
        String sql = "{CALL insertSecretaria (?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            procedure = (CallableStatement) con.prepareCall(sql);

            procedure.setString(1, U.getDocumento());
            procedure.setString(2, U.getTipoDocumento());
            procedure.setString(3, U.getNombre());
            procedure.setString(4, U.getApellido());
            procedure.setString(5, null);
            procedure.setString(6, U.getFecha());
            procedure.setString(7, U.getCorreo());
            procedure.setString(8, U.getTelefono());
            procedure.setInt(9, 3);
            procedure.setString(10, U.getClave());
            procedure.registerOutParameter(11, Types.INTEGER);
            procedure.execute();
            respuesta = procedure.getInt(11); //obtiene lo retornado en el procedimiento

        } catch (SQLException ex) {
            System.out.println("Error al insertar " + ex.getMessage());
        } finally {
            try {
                procedure.close();
                this.desconectar(con);
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                return respuesta;
            }
        }
    }

    public int insertarAprendiz(Aprendiz A) {
        CallableStatement procedure = null;

        int respuesta = 0;
        Connection con = this.conectar("mysql");
        String sql = "{CALL insertAprendiz (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            procedure = (CallableStatement) con.prepareCall(sql);

            procedure.setString(1, A.getDocumento());
            procedure.setString(2, A.getTipoDocumento());
            procedure.setString(3, A.getNombre());
            procedure.setString(4, A.getApellido());
            procedure.setString(5, null);
            procedure.setString(6, A.getFecha());
            procedure.setString(7, A.getCorreo());
            procedure.setString(8, A.getEstrato());
            procedure.setString(9, A.getGenero());
            procedure.setString(10, A.getTelefono());
            procedure.setString(11, A.getNumeroFicha());
            procedure.setInt(12, 4);
            procedure.setString(13, A.getBarrio());

            procedure.registerOutParameter(14, Types.INTEGER);
            procedure.execute();
            respuesta = procedure.getInt(14); //obtiene lo retornado en el procedimiento

        } catch (SQLException ex) {
            System.out.println("Error al insertar " + ex.getMessage());
        } finally {
            try {
                procedure.close();
                this.desconectar(con);
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                return respuesta;
            }
        }
    }

    public boolean consultarCorreo(RecuperarClave1 rep) {
        boolean bandera = false;
        java.sql.PreparedStatement pstm = null;
        Connection con = this.conectar("mysql");
        ResultSet rs;
        String sql = "SELECT email FROM USUARIO WHERE email=? limit 1";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, rep.getCorreo());
            rs = pstm.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.out.println("error al preparar la consultar" + ex);
        } finally {
            try {
                pstm.close();
                this.desconectar(con);
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                return bandera;
            }
        }

    }

    public int mensajeRecu(String clave, String email) {
        CallableStatement procedure = null;

        int respuesta = 0;
        Connection con = this.conectar("mysql");
        String sql = "{CALL mensajeRecu(?,?,?)}";
        try {
            procedure = (CallableStatement) con.prepareCall(sql);

            procedure.setString(1, email);
            procedure.setString(2, clave);
            procedure.registerOutParameter(3, Types.INTEGER);
            procedure.execute();
            respuesta = procedure.getInt(3); //obtiene lo retornado en el procedimiento

        } catch (SQLException ex) {
            System.out.println("Error al insertar " + ex.getMessage());
        } finally {
            try {
                procedure.close();
                this.desconectar(con);
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                return respuesta;
            }
        }
    }

    //Eliminar usuario INACTIVOS
    public int eliminarInstructor(String s) {
        CallableStatement procedure = null;

        int respuesta = 0;
        Connection con = this.conectar("mysql");
        String sql = "{CALL eliminar (?,?)}";
        try {
            procedure = (CallableStatement) con.prepareCall(sql);
            procedure.setString(1, s);
            procedure.registerOutParameter(2, Types.INTEGER);
            procedure.execute();
            respuesta = procedure.getInt(2); //obtiene lo retornado en el procedimiento

        } catch (SQLException ex) {
            System.out.println("Error al insertar " + ex.getMessage());
        } finally {
            try {
                procedure.close();
                this.desconectar(con);
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                return respuesta;
            }
        }
    }

    // emer 
    //sirve para listar los instructores en un combobox
    public List<Usuario> instructores() {
        java.sql.PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;
        Connection con = null;
        List<Usuario> lista = null;
        Usuario u;
        try {
            sql = "SELECT u.id,u.nombre,u.apellido "
                    + "FROM usuario as u, tipoUsuario as t, estado as e "
                    + "WHERE t.idTipoUsuario=u.idTipousuario AND u.id=e.idUsuario AND  t.idTipousuario='2' AND e.tipo='1'";
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                lista.add(u);
            }
        } catch (SQLException ex) {
            System.out.println("Listando instructores comboox " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoUsuario (instructores) " + ex);
            } finally {
                return lista;
            }
        }
    }

    //consulta los usuarios que faltaron en una fecha y no han reportado el porque no asistieron
    public List<Usuario> aprendicesNoAsistieron(String fecha, String ficha, int idProgramacion) {
        java.sql.PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;
        Connection con = null;
        List<Usuario> lista = null;
        Usuario u;
        try {
            sql = "Select id,numeroDoc,nombre,apellido,novedad \n"
                    + "from usuario AS u ,asistencia as a \n"
                    + "where a.idUsuario = u.id AND u.idTipoUsuario=3 AND u.numeroFicha=? AND DATE(a.fechaHora)=? AND a.idProgramacion=? AND a.entradaSalida='3';";//AND novedad is null AND fechaNovedad is null
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setString(2, fecha);
            pstm.setString(1, ficha);
            pstm.setInt(3, idProgramacion);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setDocumento(rs.getString("numeroDoc"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setNovedad(rs.getString("novedad"));
                lista.add(u);
            }
        } catch (SQLException ex) {
            System.out.println("Listando que faltaron en tabla " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoUsuario (aprendicesNoAsistieron) " + ex);
            } finally {
                return lista;
            }
        }
    }

    //Muestra los aprendices de una ficha
    public List<Usuario> aprendicesDeFichas(String ficha) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;
        Connection con = null;
        List<Usuario> lista = null;
        Usuario u;
        try {
            sql = "select distinct id,numeroDoc,nombre,apellido,p.idProgramacion from usuario as u, asistencia as a, programacion as p "
                    + "WHERE u.id=a.idUsuario AND a.idProgramacion=p.idProgramacion AND u.numeroficha=?";//
            con = Conexion.conectar("mysql");
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setString(1, ficha);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setDocumento(rs.getString("numeroDoc"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setIdProgramacion(rs.getInt("idProgramacion"));
                lista.add(u);
                System.out.println(rs.getString("numeroDoc"));
            }
        } catch (SQLException ex) {
            System.out.println("Listando que faltaron en tabla " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoUsuario (aprendicesNoAsistieron) " + ex);
            } finally {
                return lista;
            }
        }
    }

    //traerá los días faltados a clases por estudiante
    public List<Usuario> faltasAprendiz(int idProgramacion) {
        java.sql.PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;
        Connection con = null;
        List<Usuario> lista = null;
        try {
            sql = "select count(entradaSalida), nombre,Apellido from asistencia, usuario where idUsuario=id and  entradaSalida='3' and idProgramacion=? group by idUsuario";//
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idProgramacion);
            rs = pstm.executeQuery();
            Usuario u = new Usuario();
            lista = new ArrayList();
            while (rs.next()) {
                u = new Usuario();
                u.setDia(rs.getInt(1));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                lista.add(u);
            }
        } catch (SQLException ex) {
            System.out.println("dias faltados " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoAsistencia (diasfaltados) " + ex);
            } finally {
                return lista;
            }
        }
    }

// MODIFIQUE ESTO
    //pongalo en el servlet de aprendiz
    //complete los datos en la consulta
    public List<Usuario> aprendicesFichas(String ficha) {
        java.sql.PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;
        Connection con = null;
        List<Usuario> lista = null;
        Usuario u;
        try {
            sql = "select distinct id,numeroDoc,nombre,apellido from usuario "
                    + "WHERE  u.id=a.idUsuario AND a.idProgramacion=p.idProgramacion AND u.numeroficha=?";//
            con = Conexion.conectar("mysql");
            pstm = con.prepareStatement(sql);
            pstm.setString(1, ficha);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setDocumento(rs.getString("numeroDoc"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setIdProgramacion(rs.getInt("idProgramacion"));
                lista.add(u);
            }
        } catch (SQLException ex) {
            System.out.println("Listando que faltaron en tabla " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoUsuario (aprendicesNoAsistieron) " + ex);
            } finally {
                return lista;
            }
        }
    }

    public List stdGenero(String ficha) {
        CallableStatement procedure = null;

        List respuesta = new ArrayList();
        Connection con = this.conectar("mysql");
        String sql = "{CALL genero(?,?,?)}";
        try {
            procedure = (CallableStatement) con.prepareCall(sql);

            procedure.setString(1, ficha);
            procedure.registerOutParameter(2, Types.INTEGER);
            procedure.registerOutParameter(3, Types.INTEGER);
            procedure.execute();
            respuesta.add(0, procedure.getInt(2)); //obtiene lo retornado en el procedimiento
            respuesta.add(1, procedure.getInt(3)); //obtiene lo retornado en el procedimiento
            System.out.println(procedure.getInt(2));
            System.out.println("HGF");
            System.out.println(respuesta.get(0));
            System.out.println(respuesta.get(1));
        } catch (SQLException ex) {
            System.out.println("Error al stdGenro 1" + ex.getMessage());
        } finally {
            try {
                procedure.close();
                this.desconectar(con);
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                return respuesta;
            }
        }
    }

    //Muestra los aprendices de una ficha
    public List<Usuario> aprendicesDeFichas2(String ficha) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;
        Connection con = null;
        List<Usuario> lista = null;
        Usuario u;
        try {
            sql = "select distinct id,numeroDoc,nombre,apellido,foto,numeroFicha,email from usuario as u "
                    + "WHERE  u.numeroficha=?";//
            con = Conexion.conectar("mysql");
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setString(1, ficha);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setDocumento(rs.getString("numeroDoc"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setFoto(rs.getString("foto"));
                u.setFoto(rs.getString("numeroFicha"));
                u.setCorreo(rs.getString("email"));
                // u.setIdProgramacion(rs.getInt("idProgramacion"));
                lista.add(u);
                System.out.println(rs.getString("numeroDoc"));
            }
        } catch (SQLException ex) {
            System.out.println("Listando que faltaron en tabla " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoUsuario (aprendicesNoAsistieron) " + ex);
            } finally {
                return lista;
            }
        }
    }

    public List estrato(String ficha) {
        CallableStatement procedure = null;

        List respuesta = new ArrayList();
        Connection con = this.conectar("mysql");
        String sql = "{CALL estrato(?,?,?,?,?,?,?)}";
        try {
            procedure = (CallableStatement) con.prepareCall(sql);
            procedure.setString(1, ficha);
            procedure.registerOutParameter(2, Types.INTEGER);//estrato1
            procedure.registerOutParameter(3, Types.INTEGER);//estrato2
            procedure.registerOutParameter(4, Types.INTEGER);//estrato3
            procedure.registerOutParameter(5, Types.INTEGER);//estrato4
            procedure.registerOutParameter(6, Types.INTEGER);//estrato5
            procedure.registerOutParameter(7, Types.INTEGER);//estrato6
            procedure.execute();
            respuesta.add(0, procedure.getInt(2)); //estrato1
            respuesta.add(1, procedure.getInt(3)); //estrato2
            respuesta.add(2, procedure.getInt(4));//estrato3
            respuesta.add(3, procedure.getInt(5));//estrato4
            respuesta.add(4, procedure.getInt(6));//estrato6
            respuesta.add(5, procedure.getInt(7));
            System.out.println(respuesta.get(0));
            System.out.println(respuesta.get(1));
            System.out.println("HGF en el dao");

        } catch (SQLException ex) {
            System.out.println("Error al stdGenro 2" + ex.getMessage());
        } finally {
            try {
                procedure.close();
                this.desconectar(con);
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                return respuesta;
            }
        }
    }

    public List MunicipioEstadistica(String ficha) {
        CallableStatement procedure = null;

        List respuesta = new ArrayList();
        Connection con = this.conectar("mysql");
        String sql = "{CALL municipioestadistica(?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            procedure = (CallableStatement) con.prepareCall(sql);
            procedure.setString(1, ficha);
            procedure.registerOutParameter(2, Types.INTEGER);//medellin
            procedure.registerOutParameter(3, Types.INTEGER);//bello
            procedure.registerOutParameter(4, Types.INTEGER);//itagui
            procedure.registerOutParameter(5, Types.INTEGER);//caldas
            procedure.registerOutParameter(6, Types.INTEGER);//estrella
            procedure.registerOutParameter(7, Types.INTEGER);//sabaneta
            procedure.registerOutParameter(8, Types.INTEGER);//envigado
            procedure.registerOutParameter(9, Types.INTEGER);//copacabana;
            procedure.registerOutParameter(10, Types.INTEGER);//girardota
            procedure.registerOutParameter(11, Types.INTEGER);//barbosao

            procedure.execute();
            respuesta.add(0, procedure.getInt(2)); ///medellin
            respuesta.add(1, procedure.getInt(3)); //ebello
            respuesta.add(2, procedure.getInt(4));//itagui
            respuesta.add(3, procedure.getInt(5));//caldas
            respuesta.add(4, procedure.getInt(6));//estrella
            respuesta.add(5, procedure.getInt(7));//sabaneta
            respuesta.add(6, procedure.getInt(8));//envigado
            respuesta.add(7, procedure.getInt(9));//copacabana
            respuesta.add(8, procedure.getInt(10));//girardota
            respuesta.add(9, procedure.getInt(11));//barbosao

            System.out.println(respuesta.get(0));
            System.out.println(respuesta.get(1));
            System.out.println("HGF en el dao");

        } catch (SQLException ex) {
            System.out.println("Error al stdGenro 2" + ex.getMessage());
        } finally {
            try {
                procedure.close();
                this.desconectar(con);
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                return respuesta;
            }
        }
    }
}
