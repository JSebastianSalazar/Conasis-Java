/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Emerson Javid Gonzalez Morales
 * 
 */
public class Asistencia {
    private int idP;//id de programacion 
    private String competencia;
    private String fecha;
    private String novedad;
    private String entradaSalida;

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompepetencia(String competencia) {
        this.competencia = competencia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNovedad() {
        return novedad;
    }

    public void setNovedad(String novedad) {
        this.novedad = novedad;
    }

    public String getEntradaSalida() {
        return entradaSalida;
    }

    public void setEntradaSalida(String entradaSalida) {
        this.entradaSalida = entradaSalida;
    }
    
}
