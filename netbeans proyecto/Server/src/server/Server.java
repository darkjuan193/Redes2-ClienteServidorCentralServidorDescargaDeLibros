package server;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Pablo
 */
public class Server implements Serializable{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
			
			ServerSocket server = new ServerSocket(4890);
			System.out.println("Esperando conexion");
			
			int i=1;
			for(;;){
				
				Socket clienteNuevo = server.accept();
                                new hiloservidor(clienteNuevo,i).start();
				i++;
			}
			
			
			

		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }
    
}
