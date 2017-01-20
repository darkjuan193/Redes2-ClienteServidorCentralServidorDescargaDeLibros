package server;

import java.io.File;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import allclass.ClaseServidor;
import allclass.ReaderBook2;
import libros.ReaderBook;


public class SerDes {
public static final int port = 15251;

	public static void main(String args[]){
		try {
			
			boolean salir=false;
			ArrayList<ReaderBook2>Colection = new ArrayList<ReaderBook2>();
			new hilo().start();
			Colection = ReaderBook.lectura();
			
			
			Socket cliente = new Socket("192.168.0.101",4890);
			//System.out.println(cliente.getClass());//ip del servidor y puerto del servidor
			ClaseServidor miServidor = new ClaseServidor( InetAddress.getLocalHost().getHostAddress(),port,2,Colection);
			
			System.out.println(miServidor.getIp());
			System.out.println(miServidor.getPuerto());
			System.out.println(miServidor.getColeccion());
			//Scanner i = new Scanner(System.in);
			///String g = i.nextLine();
			//
			
			/*for(int ii = 0;ii<miServidor.getColeccion().size();ii++){
				ReaderBook2 book = miServidor.getColeccion().get(ii);
				System.out.println(book.getNombre());
				System.out.println(book.getSize());
			}*/
			
			
			ObjectOutputStream msgToServer = new ObjectOutputStream(cliente.getOutputStream());
			msgToServer.writeObject(miServidor);
			msgToServer.close();
			cliente.close();
			
			System.out.println("datos del servidor enviados");
			
			
		}
		catch(Exception e){
			System.out.println("error no hay conexion"+e.getMessage());
		}
	}
	


	
}
