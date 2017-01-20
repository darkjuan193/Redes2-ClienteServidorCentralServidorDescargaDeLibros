package server;

import java.io.Serializable;
import java.util.ArrayList;

public class ClaseServidor implements Serializable{
private static final long serialVersionUID = -5399605122490343339L;
	 String ip;
	 int puerto;
	 ArrayList<String> coleccion;
	
	public ClaseServidor(){
            
        }
	
	public ClaseServidor(String ip, int puerto, ArrayList<String> coleccion) {
		super();
		this.ip = ip;
		this.puerto = puerto;
		this.coleccion = coleccion;
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
	public ArrayList<String> getColeccion() {
		return coleccion;
	}
	public void setColeccion(ArrayList<String> coleccion) {
		this.coleccion = coleccion;
	}
	
	
	
	
}
