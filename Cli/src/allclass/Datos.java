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
 * 
 */
public class Datos implements Serializable{

    private static final long serialVersionUID = 5965870219643689719L;
    private String ip;
    private int puerto;
    private String nombre;
    private long size;
/**
 *
 * @author Pablo
 * @param ip ip del servidor a la cual se conectara el cliente para descargar
 * @param puerto puerto del servidor a la cual se conectara el cliente para descargar
 * @param nombre de la coleccion a descargar
 * @param size tamaño de la colección a descargar
 */
    public Datos(String ip, int puerto,String nombre,long size) {
        this.ip = ip;
        this.puerto = puerto;
        this.nombre = nombre;
        this.size=size;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

   
    
}
