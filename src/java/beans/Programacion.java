/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Emerson Javid Gonzalez Morales
 * @Fecha 19/06/2016
 */
public class Programacion {
   private int idProgramacion;
   private String trimestre;
   private Date fechaInicio;
   private Date fechaFinal;
   private String diaSemana;
   private String horaIngreso;
   private String horaSalida;
   private int idAmbiente;
   private int idFicha;
   //propiedades de otros objetos
   private String nomCompetencia;
   private String nombreI;
   private String apellidoI;
   private int idCompetencia;
   private int isUsuario;
   private String numeroficha;
   
    public Programacion() {
    }
   

    public Programacion(int idProgramacion, String trimestre, Date fechaInicio, Date fechaFinal, String diaSemana, String horaIngreso, String horaSalida, int idAmbiente, int idFicha) {
        this.idProgramacion = idProgramacion;
        this.trimestre = trimestre;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.diaSemana = diaSemana;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.idAmbiente = idAmbiente;
        this.idFicha = idFicha;
    }
   
    public int getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(int idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getIdAmbiente() {
        return idAmbiente;
    }

    public void setIdAmbiente(int idAmbiente) {
        this.idAmbiente = idAmbiente;
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public String getNomCompetencia() {
        return nomCompetencia;
    }

    public void setNomCompetencia(String nomCompetencia) {
        this.nomCompetencia = nomCompetencia;
    }

    public String getNombreI() {
        return nombreI;
    }

    public void setNombreI(String nombreI) {
        this.nombreI = nombreI;
    }

    public String getApellidoI() {
        return apellidoI;
    }

    public void setApellidoI(String apellidoI) {
        this.apellidoI = apellidoI;
    }

    public int getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(int idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public int getIsUsuario() {
        return isUsuario;
    }

    public void setIsUsuario(int isUsuario) {
        this.isUsuario = isUsuario;
    }

    public String getNumeroficha() {
        return numeroficha;
    }

    public void setNumeroficha(String numeroficha) {
        this.numeroficha = numeroficha;
    }
   
}
