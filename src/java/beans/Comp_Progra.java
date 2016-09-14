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
public class Comp_Progra {
    //propiedades del objeto
    private int idCompeProgra;

    public Comp_Progra() {
    }
    private int idCompetencia;

    public Comp_Progra(int idCompeProgra, int idCompetencia, int idProgramacion) {
        this.idCompeProgra = idCompeProgra;
        this.idCompetencia = idCompetencia;
        this.idProgramacion = idProgramacion;
    }
    private int idProgramacion;

    public int getIdCompeProgra() {
        return idCompeProgra;
    }

    public void setIdCompeProgra(int idCompeProgra) {
        this.idCompeProgra = idCompeProgra;
    }

    public int getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(int idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public int getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(int idProgramacion) {
        this.idProgramacion = idProgramacion;
    }
}
