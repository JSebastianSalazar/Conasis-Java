/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author sebas
 */
public class Aprendiz extends Usuario {

    private String estrato;
    private String genero;
    private String numeroFicha;
    private String barrio;

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

}
