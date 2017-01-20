package server;
import archivos.archivo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import allclass.Estructura;

public class hilodescarga extends Thread{
private Socket ss;


	public hilodescarga(Socket ss){
		this.ss=ss;
	}
	
	
	
	public void run(){
	
		System.out.println("se estan conectando un cliente al servidor de descarga");
		System.out.println("direccion del cliene"+ss.getInetAddress().getHostAddress());
		
		try {
			ObjectInputStream en = new ObjectInputStream(ss.getInputStream());
			Object a = en.readObject();
			
			if(a instanceof Estructura){
				
				Estructura es = (Estructura)a;
				
				System.out.println("nombre :"+es.getNombre());
				System.out.println("String nombre del libr: "+es.getMiPuerto());
				archivo.buscar(es.getNombre(),ss.getInetAddress().getHostAddress(),es.getMiPuerto());
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	
}
