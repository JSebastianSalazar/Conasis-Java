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
public class ProgramaXcompetencia {
    //este es el modelo de entidad ProgramaXcompetencia de la base de datos
    //se declaran las variables que son los atributos de esta entidad
    //se declaran los get and set para tener acceso a aestas variables
    private int id;
    private int idCompetencia;
    private int idPrograma;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(int idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }
}
