/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allclass;

/**
 *
 * @author Pablo
 */
public class Estadística {
   private String nombre;
    private int cantidad;
 /**
  * clase encargada de ver cuantas veces se ha descargado una coleccion
  * @param nombre nombre de la coleccion
  * @param cantidad cantidad de veces descargadas
  */
    public Estadística(String nombre,int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

   
    
    
}
