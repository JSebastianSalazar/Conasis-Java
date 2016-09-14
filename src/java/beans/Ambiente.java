/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
/**
 * @author Emerson Javid Gonzalez Morales
 * @Fecha 19/06/2016
 */
public class Ambiente {
   private int id;
   private String capacidad;
   private String tipoAmbiente;
   private String suministros;
   private String  observacion;

    public Ambiente() {
    }

    public Ambiente(int id, String capacidad, String tipoAmbiente, String suministros, String observacion) {
        this.id = id;
        this.capacidad = capacidad;
        this.tipoAmbiente = tipoAmbiente;
        this.suministros = suministros;
        this.observacion = observacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getTipoAmbiente() {
        return tipoAmbiente;
    }

    public void setTipoAmbiente(String tipoAmbiente) {
        this.tipoAmbiente = tipoAmbiente;
    }

    public String getSuministros() {
        return suministros;
    }

    public void setSuministros(String suministros) {
        this.suministros = suministros;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
   
}
