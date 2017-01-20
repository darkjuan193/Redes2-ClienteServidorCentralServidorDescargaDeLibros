package allclass;

import java.io.Serializable;

import java.util.ArrayList;


public class ClaseServidor implements Serializable{

	
	
	private static final long serialVersionUID = 8833492341333260484L;
	String ip;
	int puerto;
    int bandera;
    ArrayList<ReaderBook2>coleccion;
	 
	 
	 
	 
	public ClaseServidor(String ip, int puerto,int bandera,ArrayList<ReaderBook2> coleccion) {
		super();
		this.ip = ip;
		this.puerto = puerto;
        this.bandera = bandera;
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


    public int getBandera() {
        return bandera;
    }

    public void setBandera(int bandera) {
        this.bandera = bandera;
    }




	public ArrayList<ReaderBook2> getColeccion() {
		return coleccion;
	}




	public void setColeccion(ArrayList<ReaderBook2> coleccion) {
		this.coleccion = coleccion;
	}



	
    
	
}
	