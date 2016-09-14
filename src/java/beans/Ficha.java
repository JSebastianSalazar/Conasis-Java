/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;

/**
 * @author Emerson Javid Gonzalez Morales
 * @Fecha 19/06/2016
 */
public class Ficha {
    private int id;
    private String numeroFicha;
    private String fechaInicio;
    private String fechaFinal;
    private String jornada;
    private int idPrograma;
    private String gestor;
    private int idProgramacion;
    //otros atributos
    private String nomPrograma;

    public Ficha(int id, String numeroFicha, String fechaInicio, String fechaFinal, String jornada, int idPrograma, String gestor, int idProgramacion, String nomPrograma) {
        this.id = id;
        this.numeroFicha = numeroFicha;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.jornada = jornada;
        this.idPrograma = idPrograma;
        this.gestor = gestor;
        this.idProgramacion = idProgramacion;
        this.nomPrograma = nomPrograma;
    }
   

    public Ficha() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getGestor() {
        return gestor;
    }

    public void setGestor(String gestor) {
        this.gestor = gestor;
    }

    public int getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(int idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public String getNomPrograma() {
        return nomPrograma;
    }

    public void setNomPrograma(String nomPrograma) {
        this.nomPrograma = nomPrograma;
    }

}
