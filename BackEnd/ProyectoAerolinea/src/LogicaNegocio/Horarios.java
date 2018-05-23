
package LogicaNegocio;

import java.io.Serializable;

/**
 *
 * @author Giancarlo
 */
public class Horarios implements Serializable {

    public Horarios() {
    }

    public Horarios(int id, String diaSalida, String diaLlegada, String horaSalida, String horaLlegada, int precio) {
        this.id = id;
        this.diaSalida = diaSalida;
        this.diaLlegada = diaLlegada;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiaSalida() {
        return diaSalida;
    }

    public void setDiaSalida(String diaSalida) {
        this.diaSalida = diaSalida;
    }

    public String getDiaLlegada() {
        return diaLlegada;
    }

    public void setDiaLlegada(String diaLlegada) {
        this.diaLlegada = diaLlegada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    private int id;
    private String diaSalida;
    private String diaLlegada;
    private String horaSalida;
    private String horaLlegada;
    private int precio;

}
