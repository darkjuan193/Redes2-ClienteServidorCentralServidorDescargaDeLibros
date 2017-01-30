package server;

import java.io.File;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import allclass.Client;
import allclass.ClaseServidor;
import allclass.ReaderBook2;
import libros.ReaderBook;
import allclass.Estadística;
import archivos.leer2;

public class SerDes {
    
public static final int port = 17752;
public static String local ="192.168.4.23";
public static ArrayList<Estadística>estadistica = new ArrayList<Estadística>();
public static ArrayList<Estadística>librosDescargados = new ArrayList<Estadística>();
public static ArrayList<Client>clientes = new ArrayList<Client>();
      /**
       * Metodo encargado de enviarle todos los  datos al servidor central, tambien es el encargado de abrir los hilos de consola y recibir peticiones.
       */
	public static void main(String args[]){
		try {
                  
                         llenar();
                         leer2.cargarDatos2();
                         leer2.cargarFieles();
                        new hilo().start();
                        new consola().start();
			
			boolean salir=false;
                        
			ArrayList<ReaderBook2>Colection = new ArrayList<ReaderBook2>();
			Colection = ReaderBook.lectura();
			
			
			Socket cliente = new Socket(local,4890);
			ClaseServidor miServidor = new ClaseServidor( InetAddress.getLocalHost().getHostAddress(),port,0,Colection);
			
			System.out.println(miServidor.getIp());
			System.out.println(miServidor.getPuerto());
			System.out.println(miServidor.getColeccion());
		
			
			ObjectOutputStream msgToServer = new ObjectOutputStream(cliente.getOutputStream());
			msgToServer.writeObject(miServidor);
			msgToServer.close();
			cliente.close();
			
			System.out.println("datos del servidor enviados");
			
			
		}
		catch(IOException e){
			System.out.println("error no hay conexion"+e.getMessage());
		}
	}

    public static ArrayList<Estadística> getEstadistica() {
        return estadistica;
    }

    public static void setEstadistica(ArrayList<Estadística> estadistica) {
        SerDes.estadistica = estadistica;
    }

    public static ArrayList<Estadística> getLibrosDescargados() {
        return librosDescargados;
    }

    public static void setLibrosDescargados(ArrayList<Estadística> librosDescargados) {
        SerDes.librosDescargados = librosDescargados;
    }

    public static ArrayList<Client> getClientes() {
        return clientes;
    }

    public static void setClientes(ArrayList<Client> clientes) {
        SerDes.clientes = clientes;
    }

   
	
/**
 * metodo encargado de llenar el ArrayList de estadisticas con los libros, para manejar la cantidad de descarga
 */
public static void llenar(){
            File afolder = null;
            String path;
            String dir;
            try{
            path = new File(".").getCanonicalPath();
            afolder = new File(path+"\\libros");
            dir = path+"\\libros\\";
            }
            catch(Exception e){
                System.out.println("hubo un error en el archivo");
            }
            
            
            for (final File fileEntry : afolder.listFiles()) {
                
                if(fileEntry.isDirectory()){
                    
                    Estadística a = new Estadística(fileEntry.getName(),0);
                    Estadística b = new Estadística(fileEntry.getName(),0);
                    
                    estadistica.add(a);
                    librosDescargados.add(b);
                }
                
            }
           
           /* for(int i = 0;i<estadistica.size();i++){
                System.out.println("auto "+librosDescargados.get(i).getNombre());
            }*/
        }
	
}
