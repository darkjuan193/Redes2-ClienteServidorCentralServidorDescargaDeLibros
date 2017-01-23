/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allclass;

import java.io.Serializable;

/**
 *
 * @author Pablo
 */
public class Estructura implements Serializable {

    private static final long serialVersionUID = 6929648353105263224L;
    private int miPuerto;
    private String nombre;
    
    public Estructura(int miPuerto,String nombre){
        this.miPuerto = miPuerto;
        this.nombre = nombre;
    }

    public int getMiPuerto() {
        return miPuerto;
    }

    public void setMiPuerto(int miPuerto) {
        this.miPuerto = miPuerto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
