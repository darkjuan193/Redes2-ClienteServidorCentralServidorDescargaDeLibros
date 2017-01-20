package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import allclass.Datos;
import allclass.Datos;

public class Descarga extends Thread {

	private Socket ss;
	private Datos datos;
	private boolean terminar = false;
	
	public Descarga(Socket ss,Datos datos){
		this.ss = ss;
		this.datos=datos;
	}
	
	
	public void run(){
		
		
		System.out.println("los datos del servior de descarga son -->"+datos.getIp()+" "+datos.getPuerto()+" "+datos.getNombre());
	  //AQUI SE DEBE INICIAR LA DESCARGA DEL LIBRO debe hacer un hilo nuevo
		/*try{
		Socket descarga = new Socket("127.0.0.1",datos.getPuerto());
		ObjectOutputStream co = new ObjectOutputStream(descarga.getOutputStream());
		co.writeObject(datos.getNombre());
		co.close();
		}
		catch(IOException e){
			System.out.println("cualquiermierda");
		}*/
		
	}
	
}
