
package LogicaNegocio;

import java.io.Serializable;

/**
 *
 * @author Giancarlo
 */
public class TipoAviones implements Serializable {

    public TipoAviones(int id, String annio, String modelo, String marca, int capacidad, int filas, int asientosXFila) {
        this.id = id;
        this.annio = annio;
        this.modelo = modelo;
        this.marca = marca;
        this.capacidad = capacidad;
        this.filas = filas;
        this.asientosXFila = asientosXFila;
    }

    public TipoAviones() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnio() {
        return annio;
    }

    public void setAnnio(String annio) {
        this.annio = annio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getAsientosXFila() {
        return asientosXFila;
    }

    public void setAsientosXFila(int asientosXFila) {
        this.asientosXFila = asientosXFila;
    }

    private int id;
    private String annio;
    private String modelo;
    private String marca;
    private int capacidad;
    private int filas;
    private int asientosXFila;
    
}