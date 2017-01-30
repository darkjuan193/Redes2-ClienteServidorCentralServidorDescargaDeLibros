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
        boolean encontrado=false;
        private ArrayList<String>librosalcliente = new ArrayList<String>();
	public hiloservidor(Socket i,int c,ArrayList<ClaseServidor>Servidores,ArrayList<Client>clientes){
		
		this.ss=i;
		this.counter=c;
                this.Servidores = Servidores;
                this.clientes=clientes;
	}
	
        @Override
        /**
         * metodo encargado de gestionar todas las solicitudes tanto de los clientes como de los servidores de descarga.
         * Encargado de enviar los datos a los clientes para la descarga de archivos.
         * Encargado de asignar los servidores a los clientes.
         * Encargado de registrar las estadisticas del servidor central.
         */
	public void run(){
			
		try{
                        boolean done,ba=false;
                        String ip;
			System.out.println("hola cliente "+counter);
                        ObjectInputStream entrada = new ObjectInputStream(ss.getInputStream());
                        Object a = entrada.readObject();
                           
                              if(a instanceof ClaseServidor){//registro de los servidores de descarga.
                                 //System.out.println("si es insancia");
                                 ClaseServidor e=(ClaseServidor)a;
                                 bo= e.getColeccion();
                                // System.out.println("LA IP "+e.getIp());
                                 Estadisticas esta = new Estadisticas(e.getIp(), e.getPuerto(),0);
                                 
                                 
                                 
                                //Servidores.add((ClaseServidor)a);
                                // MainServer.setLosServidores(Servidores);
                                for(int o = 0;o<MainServer.getClientesAtendidos().size();o++){
                                    
                                    if(MainServer.getClientesAtendidos().get(o).getIpser().equals(e.getIp())){
                                       // System.out.println("ya esta agregado en las estadisticas");
                                        ba=true;
                                        break;//servior en las estadisticas cortamos ciclo
                                    }else{
                                        ba = false; 
                                    }
                                    
                                }
                                if(ba==false){//agregamos el servidor en las estadisticas
                                    MainServer.getClientesAtendidos().add(esta);
                                }
                                
                                for(int o = 0;o<MainServer.getLosServidores().size();o++){
                                    System.out.println("Los servidores "+MainServer.getLosServidores().get(o).getIp());
                                }
                                
                              
                                
                                for(int i=0;i<MainServer.getLosServidores().size();i++){//este ciclo verifica que cuando el servidor se conecte de nuevo ya este registrado en el xml
                                    //System.out.println("los Servidores: "+MainServer.getLosServidores().get(i).getIp() + "  "+MainServer.getLosServidores().get(i).getPuerto());
                                    //System.out.println("Ip :"+MainServer.getLosServidores().get(i).getIp());
                                    
                                 if(MainServer.getLosServidores().get(i).getIp().equals(e.getIp())){
                                     encontrado = true;
                                     //System.out.println("Ip ya esta registrada");
                                     break;
                                 }
                                 else{
                                     encontrado=false;
                                 }
    
                                }
                                if(encontrado ==false){//se agrega el servidor en caso de que no sea encontrado
                                    
                                 Servidores.add((ClaseServidor)a);
                                 MainServer.setLosServidores(Servidores);
                                    //System.out.println("agregado al central");
                                }
                                 ss.close();
                                  
                              }else                               
                              if(a instanceof Client){
                                   Client cli = (Client) a;
                                   clientes.add((Client)a);
                                   MainServer.setLosClientes(clientes);
                                   for(int i=0;i<MainServer.getLosClientes().size();i++){
                                       System.out.println("los clientes: "+MainServer.getLosClientes().get(i).getIp() + "  "+MainServer.getLosClientes().get(i).getPort());
                                   }
                                  ss.close();
                                  enviarClientes(cli);
                                
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
                                          System.out.println("Coleccion género enviada");
                                      }
                                      
                                        if(a.equals("3")){//pedir libros por coleccion
                                          System.out.println("Buscando Coleccion..");
                                          Servidores = MainServer.getLosServidores();
                                          //aqui tengo q hacer una busqueda de la ip de ss en clientes para extraer el peurto
                                          Socket alCliente2 = new Socket(ss.getInetAddress().getHostAddress(),puertoActual(ss.getInetAddress().getHostAddress(),MainServer.getLosClientes()));
                                          ObjectOutputStream obj2 = new ObjectOutputStream(alCliente2.getOutputStream());
                                          librosalcliente = listarAutor(MainServer.getLosServidores(),librosalcliente);
                                          //for(int i=0;i<librosalcliente.size();i++){
                                          //    System.out.println("los libros"+librosalcliente.get(i));
                                         // }
                                          obj2.writeObject(librosalcliente);
                                          obj2.close();
                                          alCliente2.close();
                                          System.out.println("Coleccion autor enviada");
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
        /**
         * Metodo encargado de buscar las colecciones en los servidores
         * @param servidores lista de los servidores de descarga
         * @param nombre nombre de la coleccion a buscar 
         * @return 
         */
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
                      //System.out.println("lo encontro"+coleccion.get(j).getSize());
                      
                      coleccion.get(j).setContador(coleccion.get(j).getContador()+1);
                     // System.out.println("el contador "+coleccion.get(j).getContador());
                      
                      datos = new Datos(servidores.get(i).getIp(),servidores.get(i).getPuerto(),nombre,coleccion.get(j).getSize());
                      return datos;
                  }
              }
              
              //System.out.println("el servidor es"+ser.getIp());
              //System.out.println("el servidor es"+ser.getPuerto());
          }
           
           return null;
        }
        /**
         * metodo encargado de mostrale todas las colecciones disponibles al cliente
         * @param servidores lista de los servidores
         * @param libros arraylist de string de todos las colecciones del servidor
         * @return array con el nombre de cada coleccion 
         */
       public ArrayList<String>listarColeccion(ArrayList<ClaseServidor> servidores,ArrayList<String>libros){
            
            ArrayList<String> array = null;
           ArrayList<ReaderBook2>coleccion = new ArrayList<ReaderBook2>();
          ClaseServidor ser = null;
            for(int i=0;i<servidores.size();i++){
               ser = servidores.get(i);
               coleccion = ser.getColeccion();
                
                for(int a =0;a<coleccion.size();a++){
                    if(coleccion.get(a).getNombre().contains("Autor")==false){
                   libros.add(coleccion.get(a).getNombre());
                    }
                }
            }
            System.out.println("coleccion genero"+libros);
            return libros;
        }
       /**
        * metodo encargado de mostrale todas los autores disponibles al cliente
      * @param servidores lista de los servidores
         * @param libros arraylist de string de todos los autores del servidor
         * @return array con el nombre de cada autor 
         */
          public ArrayList<String>listarAutor(ArrayList<ClaseServidor> servidores,ArrayList<String>libros){
            
            ArrayList<String> array = null;
           ArrayList<ReaderBook2>coleccion = new ArrayList<ReaderBook2>();
          ClaseServidor ser = null;
          
            for(int i=0;i<servidores.size();i++){
               ser = servidores.get(i);
               coleccion = ser.getColeccion();
                
                for(int a =0;a<coleccion.size();a++){
                    
                    if(coleccion.get(a).getNombre().contains("Autor"))
                    {
                   libros.add(coleccion.get(a).getNombre());
                    }
                }
            }
              System.out.println("coleccion autor"+libros);
            return libros;
        }

        
        
        public int puertoActual(String ip,ArrayList<Client>clientes){
            
            
            int puerto=0;
           // System.out.println("La ip dentro"+ip);
                for(int i=0;i<clientes.size();i++)
                {
                   
                    if(clientes.get(i).getIp().equals(ip)){
                       //System.out.println("las ips:"+clientes.get(i).getIp());
                       puerto = clientes.get(i).getPort();
                       return puerto;
                    }
                }
            return puerto;
            
         
        }
	/**
         * encargado de manejar las estadisticas de descargas por servidor
         * @param estadisticas estadisticas del servidor
         * @param ipser ip del servidor
         */
	   public void revisar(ArrayList<Estadisticas> estadisticas,String ipser){
            
            for(int i=0;i<estadisticas.size();i++){
                
                
                if(estadisticas.get(i).getIpser().equals(ipser)){
                    estadisticas.get(i).setVeces(estadisticas.get(i).getVeces()+1);
                }
                
            }
   
        }
           /**
            * metodo encargado de enviar clientes al servidor para efectuar estadisticas
            * @param clientes lisrta de clientes
            */
           public void enviarClientes(Client clientes) throws IOException{
               
               for(int i = 0;i<MainServer.getLosServidores().size();i++){
                   
                   Socket cli = new Socket(MainServer.getLosServidores().get(i).getIp(),MainServer.getLosServidores().get(i).getPuerto());
                   ObjectOutputStream c = new ObjectOutputStream(cli.getOutputStream());
                   c.writeObject(clientes);
                   c.close();
                   cli.close();
               }
               //System.out.println("Datos enviados a todos los Servidores");
               
           }
}


