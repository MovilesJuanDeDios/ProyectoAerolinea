
package LogicaNegocio;

import java.io.Serializable;

/**
 *
 * @author Giancarlo
 */
public class Aviones implements Serializable {

    public Aviones() {
    }

    public Aviones(int id, int ruta, int horario, int tipoAvion) {
        this.id = id;
        this.ruta = ruta;
        this.horario = horario;
        this.tipoAvion = tipoAvion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRuta() {
        return ruta;
    }

    public void setRuta(int ruta) {
        this.ruta = ruta;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public int getTipoAvion() {
        return tipoAvion;
    }

    public void setTipoAvion(int tipoAvion) {
        this.tipoAvion = tipoAvion;
    }

    private int id;
    private int ruta;
    private int horario;
    private int tipoAvion;

}
