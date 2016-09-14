/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
/**
 *
 * @author Emerson Javid Gonzalez Morales
 * @Fecha 19/06/2016
 */
public class UsuarioXprogramacion {

    
    //propiedades del objeto
    private int id;
    private int idUsuario;
    private int idProgramacion;

    public UsuarioXprogramacion() {
    }
        
    public UsuarioXprogramacion(int id, int idUsuario, int idProgramacion) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idProgramacion = idProgramacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(int idProgramacion) {
        this.idProgramacion = idProgramacion;
    }
}
