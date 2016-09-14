/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Emerson Javid Gonzalez Morales
 * @Fecha 05/06/2016
 */
public class Competencias {
    //este es el modelo de entidad competencias de la base de datos
    //se declaran las variables que son los atributos de esta entidad
    //se declaran los get and set para tener acceso a aestas variables
    private int idCompetencia;
    private String nomCompetencia;

    public Competencias() {
    }

    public Competencias(String nomCompetencia) {
        this.nomCompetencia = nomCompetencia;
    }

    public int getIdCompetencia() {
        return idCompetencia;
    }

    public String getNomCompetencia() {
        return nomCompetencia;
    }

    public void setNomCompetencia(String nomCompetencia) {
        this.nomCompetencia = nomCompetencia;
    }

    public void setIdCompetencia(int idCompetencia) {
        this.idCompetencia = idCompetencia;
    }
    
}
