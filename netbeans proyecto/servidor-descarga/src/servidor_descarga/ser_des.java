package servidor_descarga;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;


public class ser_des implements Serializable{

	public static void main(String args[]){
		try {
			
			boolean salir=false;
			
			int i = 1;
			Socket cliente = new Socket("127.0.0.1",4890); //ip del servidor y puerto del servidor
			ArrayList<String>coleccion = new ArrayList<String>();
			coleccion.add("Libro1");
			coleccion.add("Libro2");
			coleccion.add("Libro3");
			coleccion.add("Libro4");
			coleccion.add("Libro5");
			coleccion.add("Libro6");
			
			ClaseServidor miServidor = new ClaseServidor( InetAddress.getLocalHost().getHostAddress(),15253,coleccion);
			/*System.out.println(miServidor.getIp());
			System.out.println(miServidor.getPuerto());try{
			
			
			System.out.println(miServidor.getColeccion());*/
			
			while(salir==false){
					ObjectOutputStream msgToServer = new ObjectOutputStream(cliente.getOutputStream());
					//System.out.println(InetAddress.getLocalHost().getHostAddress());
					msgToServer.writeObject(miServidor);
					//msgToServer.flush();
					cliente.close();
					System.out.println("datos del servidor enviados");
					salir = true;
			}
		}
		catch(Exception e){
			
		}
	}
	
	
}
		
				
	
