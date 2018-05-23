
package LogicaNegocio;

import java.io.Serializable;

/**
 *
 * @author Giancarlo
 */
public class Rutas implements Serializable {

    public Rutas() {
    }

    public Rutas(int id, String ruta, String duraccion) {
        this.id = id;
        this.ruta = ruta;
        this.duraccion = duraccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getDuraccion() {
        return duraccion;
    }

    public void setDuraccion(String duraccion) {
        this.duraccion = duraccion;
    }
    
    private int id;
    private String ruta;
    private String duraccion;
    
}