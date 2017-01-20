/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import allclass.Datos;
import allclass.Estructura;
import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Pablo
 */
public class multihilo extends Thread{
    private static int p =0;
    private Socket ss;
    private Socket res=null;
    ObjectInputStream var,var2=null;
    
        public static boolean c25,c50,c75 = true;
    public multihilo(Socket ss){
        this.ss=ss;
        this.res=ss;
    }
    
    public void run(){
        
        int filesize=300000000;
        int bytesRead; 
        int currentTot = 0; 
        float tamano = filesize;
        
         
       byte [] bytearray = new byte [filesize]; 
        try{
           ObjectInputStream datos = new ObjectInputStream(ss.getInputStream());
           var= datos;
           var2=datos;
        
           try{   
           FileOutputStream fos = new FileOutputStream("C:\\Users\\Pablo\\Desktop\\descarga"+Integer.toString(p)+".rar");
           BufferedOutputStream bos = new BufferedOutputStream(fos);
           bytesRead = datos.read(bytearray,0,bytearray.length);
           currentTot = bytesRead; 
            System.out.println(bytesRead);
            
           if(bytesRead!=-1){ 
                  System.out.println("Entrando en la transferencia");
                  p++;
                  
            do 
                {
                  bytesRead = datos.read(bytearray, currentTot, (bytearray.length-currentTot)); 
                    if(bytesRead >= 0) 
                        currentTot += bytesRead;  
                    
                    carga(Main.getPeso(),"elarchivo",(float)currentTot);
                }
            while(bytesRead > -1);
            Main.setPeso(0);
          
            
             System.out.println(currentTot);
            bos.write(bytearray, 0 , currentTot); 
            bos.flush(); 
            bos.close();
            System.out.println("Transferencia completa");
            ss.close(); 
          }
          else{
               System.out.println("entrando enb las otras");
               Object obj = var.readObject();
               
                if(obj instanceof ArrayList<?>){
                System.out.println("Recibiendo Coleccion..");
                ArrayList<String>Lacoleccion;
                Lacoleccion = (ArrayList<String>) obj;
                System.out.println("Los libros disponibles: ");
                    for(int i =0;i<Lacoleccion.size();i++){
                        System.out.println(Lacoleccion.get(i));
                    }
                
            }else{
                  if(obj instanceof Datos){
                Datos data;
                data = (Datos)obj;
                System.out.println("estoy recibiendo los datos para ladescarga");
                datos.close();
                ss.close();
                System.out.println("ip servidor: "+data.getIp());
                System.out.println("puerto servidor: "+data.getPuerto());
                System.out.println("nombre del libro a descargar :"+data.getNombre());
                System.out.println("nombre del libro a descargar :"+data.getSize());
                System.out.println("Conex cerrada");
                Main.setPeso(data.getSize());
                System.out.println("variabale "+Main.getPeso());
                //conctandome con el de descarga
                Socket serdes = new Socket(data.getIp(),data.getPuerto());
                ObjectOutputStream nombre = new ObjectOutputStream(serdes.getOutputStream());
                Estructura es = new Estructura(Main.port,data.getNombre());
                nombre.writeObject(es);
                System.out.println("nombre neviado y mi puerto enviado");
                nombre.close();
                serdes.close();
              
                }
               
           }
           else
                  {
                      if(obj instanceof String){


                        String error = (String)obj;
                        System.out.println(error);
                        ss.close();

                      }

                  }
           }
           }catch(Exception ea){
               System.out.println("gfdfdsf"+ea.getMessage());
           }
           
          
        } catch (IOException ex) {
            Logger.getLogger(multihilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
  
      public static void carga(float Tamano, String fileName,float acum){
         
          float a = (acum/Tamano)*100;
          
          System.out.println("el porcentaje de la descarga --> "+a);
         //System.out.println("Descargando al "+((acum/Tamano)*100)+"%"); 
          
    }
    
 
}
