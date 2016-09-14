
package beans;

/**
 *
 * @author Emerson Javid Gonzalez Morales
 * @Fecha 05/06/2016
 */
public class Programa {
    //este es el modelo de entidad programa de la base de datos
    //se declaran las variables que son los atributos de esta entidad
    //se declaran los get and set para tener acceso a aestas variables
    private int idPrograma;
    private String nomPrograma;

    public Programa() {
    }

    public Programa(String nomPrograma) {
        this.nomPrograma = nomPrograma;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public String getNomPrograma() {
        return nomPrograma;
    }

    public void setNomPrograma(String nomPrograma) {
        this.nomPrograma = nomPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }
    
}
