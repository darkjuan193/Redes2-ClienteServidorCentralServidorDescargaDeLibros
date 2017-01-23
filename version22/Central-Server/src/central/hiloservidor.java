package central;

import allclass.ClaseServidor;
import central.MainServer;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import allclass.Client;
import allclass.Datos;
import allclass.Estadisticas;
import allclass.ReaderBook2;


public class hiloservidor extends Thread implements Serializable{

	private Socket ss;
	private int counter;
        private ArrayList<ClaseServidor>Servidores;
        private ArrayList<Client>clientes;
        private ArrayList<ReaderBook2>bo;
        private ArrayList<String>librosalcliente = new ArrayList<String>();
	public hiloservidor(Socket i,int c,ArrayList<ClaseServidor>Servidores,ArrayList<Client>clientes){
		
		this.ss=i;
		this.counter=c;
                this.Servidores = Servidores;
                this.clientes=clientes;
	}
	
        @Override
	public void run(){
			
		try{
                        boolean done=false;
                        String ip;
			System.out.println("hola cliente "+counter);
                        ObjectInputStream entrada = new ObjectInputStream(ss.getInputStream());
                        Object a = entrada.readObject();
                           
                              if(a instanceof ClaseServidor){//registro de los servidores de descarga.
                                 //System.out.println("si es insancia");
                                 ClaseServidor e=(ClaseServidor)a;
                                 bo= e.getColeccion();
                                 System.out.println(bo.get(0).getNombre()+" "+bo.get(0).getSize()+" ");
                                 Estadisticas esta = new Estadisticas(e.getIp(), e.getPuerto(),0);
                                 MainServer.getClientesAtendidos().add(esta);
                                // System.out.println(e.getIp());
                                 Servidores.add((ClaseServidor)a);
                                 MainServer.setLosServidores(Servidores);
                                // System.out.println(MainServer.getLosServidores().size());
                                //Datos da = leer(MainServer.getLosServidores(),"Fantasia-urbana-paranormal.rar");
                                // System.out.println(MainServer.getLosServidores().size());
                                for(int i=0;i<MainServer.getLosServidores().size();i++){
                                    System.out.println("los Servidores: "+MainServer.getLosServidores().get(i).getIp() + "  "+MainServer.getLosServidores().get(i).getPuerto());
                                }
                                 ss.close();
                                  
                              }else                               
                              if(a instanceof Client){
                                   Client cli = (Client) a;
                                   clientes.add((Client)a);
                                   MainServer.setLosClientes(clientes);
                                   for(int i=0;i<MainServer.getLosServidores().size();i++){
                                       System.out.println("los clientes: "+MainServer.getLosClientes().get(i).getIp() + "  "+MainServer.getLosClientes().get(i).getPort());
                                   }
                                  ss.close();
                                
                              }else
                                  if(a instanceof String){
                                      if(a.equals("2")){//pedir libros por coleccion
                                          System.out.println("Buscando Coleccion..");
                                          Servidores = MainServer.getLosServidores();
                                          //aqui tengo q hacer una busqueda de la ip de ss en clientes para extraer el peurto
                                          Socket alCliente = new Socket(ss.getInetAddress().getHostAddress(),puertoActual(ss.getInetAddress().getHostAddress(),MainServer.getLosClientes()));
                                          ObjectOutputStream obj = new ObjectOutputStream(alCliente.getOutputStream());
                                          librosalcliente = listarColeccion(MainServer.getLosServidores(),librosalcliente);
                                          //for(int i=0;i<librosalcliente.size();i++){
                                          //    System.out.println("los libros"+librosalcliente.get(i));
                                         // }
                                          obj.writeObject(librosalcliente);
                                          obj.close();
                                          alCliente.close();
                                          System.out.println("Coleccion enviada");
                                      }
                                      
                                      if(a.equals("3")==false && a.equals("2")==false){// esta pidiendo un libro
                                          //System.out.println("el nomrbe del libro a buscar"+a);
                                          
                                          Datos datos=null;
                                          //System.out.println(MainServer.getLosServidores().size());
                                           datos = buscar(MainServer.getLosServidores(),a.toString());

                                           if(datos != null)
                                           {
                                                revisar(MainServer.getClientesAtendidos(),datos.getIp());
                                                Socket alCliente2 = new Socket(ss.getInetAddress().getHostAddress(),puertoActual(ss.getInetAddress().getHostAddress(),MainServer.getLosClientes()));
                                                ObjectOutputStream o = new ObjectOutputStream(alCliente2.getOutputStream());
                                                o.writeObject(datos);
                                                System.out.println("Datos enviados al cliente");
                                                o.close();
                                                alCliente2.close();
                                                //escribir(datos,MainServer.descargaXservidor);
                                           }
                                           else
                                           {
                                                Socket alCliente2 = new Socket(ss.getInetAddress().getHostAddress(),puertoActual(ss.getInetAddress().getHostAddress(),MainServer.getLosClientes()));
                                                ObjectOutputStream o = new ObjectOutputStream(alCliente2.getOutputStream());
                                                o.writeObject("ho hay la coleccion disponible");

                                           }
                                       
                                      }
                                  }
                        
		}
		catch(Exception e){
			
		}
	}
        
        public Datos buscar(ArrayList<ClaseServidor> servidores,String nombre){
          Datos datos = null;
          //ArrayList<String>lista = new ArrayList<String>();
          ArrayList<ReaderBook2>coleccion = new ArrayList<ReaderBook2>();
          ClaseServidor ser = null;
          for(int i = 0;i<servidores.size();i++){
              
              ser = servidores.get(i);
              coleccion = ser.getColeccion();
             
              for(int j = 0;j<coleccion.size();j++){
                  
                  if(coleccion.get(j).getNombre().equals(nombre)){
                      System.out.println("lo encontro"+coleccion.get(j).getSize());
                      
                      coleccion.get(j).setContador(coleccion.get(j).getContador()+1);
                      System.out.println("el contador "+coleccion.get(j).getContador());
                      
                      datos = new Datos(servidores.get(i).getIp(),servidores.get(i).getPuerto(),nombre,coleccion.get(j).getSize());
                      return datos;
                  }
              }
              
              //System.out.println("el servidor es"+ser.getIp());
              //System.out.println("el servidor es"+ser.getPuerto());
          }
           /* for(int i =0;i<servidores.size();i++){
                 lista=servidores.get(i).getColeccion();
                 
                for(int y = 0;y<lista.size();y++){
                    if(lista.get(y).equals(nombre)){
                     System.out.println(servidores.get(i).getIp() + " "+servidores.get(i).getPuerto());
                     //int size= buscar();
                     datos = new Datos(servidores.get(i).getIp(),servidores.get(i).getPuerto(),nombre); 
                     return datos;
                    }
                 }
                
            }
        
            
            return datos;*/
           return null;
        }
        
       public ArrayList<String>listarColeccion(ArrayList<ClaseServidor> servidores,ArrayList<String>libros){
            
            ArrayList<String> array = null;
           ArrayList<ReaderBook2>coleccion = new ArrayList<ReaderBook2>();
          ClaseServidor ser = null;
            for(int i=0;i<servidores.size();i++){
               ser = servidores.get(i);
               coleccion = ser.getColeccion();
                
                for(int a =0;a<coleccion.size();a++){
                   libros.add(coleccion.get(a).getNombre());
                }
            }
            
            return libros;
        }

        
        
        public int puertoActual(String ip,ArrayList<Client>clientes){
            
            
            int puerto=0;
            System.out.println("La ip dentro"+ip);
                for(int i=0;i<clientes.size();i++)
                {
                   
                    if(clientes.get(i).getIp().equals(ip)){
                       System.out.println("las ips:"+clientes.get(i).getIp());
                       puerto = clientes.get(i).getPort();
                       return puerto;
                    }
                }
            return puerto;
            
         
        }
	
	   public void revisar(ArrayList<Estadisticas> estadisticas,String ipser){
            
            for(int i=0;i<estadisticas.size();i++){
                
                
                if(estadisticas.get(i).getIpser().equals(ipser)){
                    estadisticas.get(i).setVeces(estadisticas.get(i).getVeces()+1);
                }
                
            }
   
        }
}


