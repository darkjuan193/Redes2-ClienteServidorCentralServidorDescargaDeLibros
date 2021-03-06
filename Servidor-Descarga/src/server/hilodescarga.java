package server;
import allclass.Client;
import archivos.archivo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import allclass.Estadística;

import allclass.Estructura;
import java.util.ArrayList;

public class hilodescarga extends Thread{
private Socket ss;
private boolean encontro = false;

/**
 * metodo encargado de asignar el socket a la variable local 
 * @param ss nuevop socket abierto por el hilo anterior 
 */
	public hilodescarga(Socket ss){
		this.ss=ss;
	}
	
	
	/**
         * metodo encargado de recibir los clientes y la estructura de lo que se va a descargar 
         */
	public void run(){
	
		System.out.println("se estan conectando un cliente al servidor de descarga");
		System.out.println("direccion del cliene"+ss.getInetAddress().getHostAddress());
		
		try {
			ObjectInputStream en = new ObjectInputStream(ss.getInputStream());
			Object a = en.readObject();
			
			if(a instanceof Estructura){
				
				Estructura es = (Estructura)a;
				
				//System.out.println("nombre :"+es.getNombre());
				//System.out.println("String nombre del libr: "+es.getMiPuerto());
				archivo.buscar(es.getNombre(),ss.getInetAddress().getHostAddress(),es.getMiPuerto());
				
                                
                                
			}
                        else
                            if(a instanceof Client){
                                
                             
                             // System.out.println("Recibi los clietnes");
                              
                              for(int i =0;i<SerDes.getClientes().size();i++){
                                  
                                  if(SerDes.getClientes().get(i).getIp().equals(((Client) a).getIp())){
                                      
                                      encontro=true;
                                      break;
                                  }
                                  else
                                  {
                                      encontro = false;
                                  }
                              }
                                if(encontro == false){
                                     SerDes.getClientes().add((Client) a);
                                }
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
