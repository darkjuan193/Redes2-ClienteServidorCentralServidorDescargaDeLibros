package server;
import server.SerDes;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class hilo extends Thread{
/**
 * metodo encargado de abrir un hilo por cada nueva conexion 
 */
	public void run(){
	
		System.out.println("Esperando peticions");
		try{
			ServerSocket server = new ServerSocket(SerDes.port);
			
			
			
			
			
			for(;;){
				
				Socket cli = server.accept();
				new hilodescarga(cli).start();
			}
			
			
		}catch(IOException e){
			
		}
		
		
	}
		
	
}
