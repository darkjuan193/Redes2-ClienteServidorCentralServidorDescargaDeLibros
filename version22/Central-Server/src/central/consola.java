package central;

import allclass.ClaseServidor;
import allclass.ReaderBook2;
import java.util.ArrayList;
import java.util.Scanner;



public class consola extends Thread{


public void run() {
		while (true){
                        System.out.println("Entrando a la consola");
			Scanner teclado = new Scanner(System.in);
			String texto = teclado.nextLine();

                        if(texto.equals("Descargas")){
                            
                            ArrayList<ReaderBook2>colecc;
                            
                              for(int i = 0;i<MainServer.getLosServidores().size();i++){
                                  
                                  ClaseServidor ser = MainServer.getLosServidores().get(i);
                                  colecc = ser.getColeccion();
                                  
                                  for(int o = 0;o<colecc.size();o++){
                                      
                                      if(0 == colecc.get(o).getContador()){
                                          System.out.println("No se han registrado descargas del Genero: "+colecc.get(o).getNombre());
                                      }else{
                                          System.out.println("el contador global Del Genero "+colecc.get(o).getNombre()+" es: " + colecc.get(o).getContador()+" Donde el IP del servidor es: "+ser.getIp()+" Con puerto: "+ser.getPuerto());
                                      }
                                  
                                  }
                                  
                                  
                              }   
                              
                              try {
                              MainServer.generate("Datos",MainServer.getLosServidores()); 
                             }
                              catch (Exception e){
                                  System.out.println("Exploto esta mierda");
                              }
                        }
                        if(texto.equals("clientes")){
                            System.out.println("holaaaaaaaaaaa");
                            for(int i=0;i<MainServer.getClientesAtendidos().size();i++){
                                System.out.println("Servidor :"+MainServer.getClientesAtendidos().get(i).getIpser()+" veces antenidas "+MainServer.getClientesAtendidos().get(i).getVeces());
                            }
                        }
		
                        
                }            
	}


}