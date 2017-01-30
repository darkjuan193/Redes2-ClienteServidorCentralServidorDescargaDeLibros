package central;

import allclass.ClaseServidor;
import allclass.ReaderBook2;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



public class consola extends Thread{
    /**
     * metodo encargado de mostrar en pantalla las distintas opciones que tiene el servidor de descarga 
     */
private boolean hola=false;

public void run() {
		while (hola==false){
                        System.out.println("Entrando a la consola");
			Scanner teclado = new Scanner(System.in);
			String texto = teclado.nextLine();

                        if(texto.equals("descargas")){
                            
                            ArrayList<ReaderBook2>colecc;
                            
                              for(int i = 0;i<MainServer.getLosServidores().size();i++){
                                  
                                  ClaseServidor ser = MainServer.getLosServidores().get(i);
                                  colecc = ser.getColeccion();
                                  
                                  for(int o = 0;o<colecc.size();o++){
                                      
                                      if(0 == colecc.get(o).getContador()){
                                          System.out.println("Descargas del Genero: "+colecc.get(o).getNombre()+" para el serevidor "+ser.getIp());
                                      }else{
                                          System.out.println("Descargas del Genero "+colecc.get(o).getNombre()+" es: " + colecc.get(o).getContador()+" Donde el IP del servidor es: "+ser.getIp());
                                      }
                                  
                                  }  
                              }   
                              
                            
                        }
                        if(texto.equals("clientes")){
                            //System.out.println("holaaaaaaaaaaa");
                            for(int i=0;i<MainServer.getClientesAtendidos().size();i++){
                                System.out.println("Servidor :"+MainServer.getClientesAtendidos().get(i).getIpser()+" veces antenidas "+MainServer.getClientesAtendidos().get(i).getVeces());
                            }
                        }
		
                        if(texto.equals("salir")){
                            try {
                                System.out.println("Saliendo de la consola"); 
                                MainServer.generate("Datos",MainServer.getLosServidores());
                                MainServer.clientesXservidor(MainServer.getClientesAtendidos());
                            } catch (Exception ex) {
                                Logger.getLogger(consola.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println("archivo generado");
                            hola = true;
                        }
                }            
	}


}