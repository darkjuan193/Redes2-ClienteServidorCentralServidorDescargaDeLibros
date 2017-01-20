package central;

import allclass.ClaseServidor;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import allclass.ClaseServidor;
import java.util.ArrayList;
import allclass.Client;
import allclass.Estadisticas;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;

/**
 *
 * @author Pablo
 */
public class MainServer{

    /**
     * @param args the command line arguments
     */
    public static ArrayList<ClaseServidor>losServidores= new ArrayList<ClaseServidor>();
    public static ArrayList<Client>losClientes = new ArrayList<Client>();
    public static ArrayList<String>libros = new ArrayList<String>();
    public static ArrayList<Estadisticas>descargaXservidor= new ArrayList<Estadisticas>();

    
    public static void main(String[] args) {
        
        try {
			ServerSocket server = new ServerSocket(4890);
			System.out.println("Esperando conexion");
                new consola();      
			
			int i=1;
			for(;;){
				
				Socket clienteNuevo = server.accept();                           
                                new hiloservidor(clienteNuevo,i,losServidores,losClientes).start();
				i++;
			}

		} catch (IOException e){
			e.printStackTrace();
		}
    }
    
    
    public static ArrayList<ClaseServidor> getLosServidores() {
        return losServidores;
    }

    public static void setLosServidores(ArrayList<ClaseServidor> losServidores) {
        MainServer.losServidores = losServidores;
    }

    public static ArrayList<Client> getLosClientes() {
        return losClientes;
    }

    public static void setLosClientes(ArrayList<Client> losClientes) {
        MainServer.losClientes = losClientes;
    }

    public static ArrayList<String> getLibros() {
        return libros;
    }

    public static void setLibros(ArrayList<String> libros) {
        MainServer.libros = libros;
    }

    
}
