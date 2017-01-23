package server;

import java.io.DataOutputStream;
import java.io.IOException;

import server.Main;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import allclass.Client;
import allclass.Client;
import server.Main;


public class hilohablar extends Thread{

	public void run(){
		
	      String teclado;
	      String valor = null;
	      boolean salir=false;
              final int port =50000;
	        
	      while(salir==false){
	        System.out.println("1. Inscribirse en el servidor central");
	        System.out.println("2. Consultar Libros por coleccion");
	        System.out.println("3. Consultar Libros por autor");
	        System.out.println("4. salir");
	        Socket cliente,cliente2 = null;  
	   
	        Scanner entrada = new Scanner(System.in);
	        teclado = entrada.nextLine();
	
	        switch(teclado){
	      
	            case "1":
	                 try {
	                	 try{
                                 cliente = new Socket(Main.ipservidor,Main.portcentral);
	                         Client cli = new Client("PabloBarboza",InetAddress.getLocalHost().getHostAddress(),2,Main.port);
	                         ObjectOutputStream obj = new ObjectOutputStream(cliente.getOutputStream());
	 	                 obj.writeObject(cli);
	 	                 obj.close();
	 	                 cliente.close();
	 	                 System.out.println("Datos enviados");
	 	                
	                	 }
	                	 catch(SocketException e){
	                		 System.out.println("error"+e.getMessage());
	                	 }
	                     
	                  } catch (IOException ex) {
	                         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	                  }
	                 break;
	        
	            case "2":
	                try{	                	
	                	try{
	                	cliente = new Socket(Main.ipservidor,Main.portcentral);
	                	ObjectOutputStream bandera = new ObjectOutputStream(cliente.getOutputStream());
	                	bandera.writeObject("2");
	                	bandera.close();
	                	cliente.close();
	                	
	                	System.out.println("intro libro...");
	                	Scanner o = new Scanner(System.in);
	                	String da = o.nextLine();
	           
	                	
	                	cliente2 = new Socket(Main.ipservidor,Main.portcentral);
	                	ObjectOutputStream bandera2 = new ObjectOutputStream(cliente2.getOutputStream());
	                	bandera2.writeObject(da);
	                	bandera2.close();
	                	cliente2.close();
	                	System.out.println("Opcion enviada");
	                	}
	                	catch(SocketException e){
	                		System.out.println("errorw"+e.getMessage());
	                	}
	                	
	                	
	                }catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);   
	                }
	                break;
	            case "3":
	            	break;
	            case "4":
	            	
	            	salir=true;
	            	System.out.println(salir);
	            	break;
	            	
	            	case "5":
	            	break;
	        }
	      }   
	    System.out.println("terminado el civlo");
	}
	
	
}
