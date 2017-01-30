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
import java.awt.FlowLayout;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Pablo
 */
public class multihilo extends Thread{
    private static int p =0;
    private Socket ss;
    private int i =0;
    public JLabel label = null;
    private Socket res=null;
    ObjectInputStream var,var2=null;
    
        public static boolean c25,c50,c75 = true;
    public multihilo(Socket ss){
        this.ss=ss;
        this.res=ss;

    }
    /**
     * Metodo encargado de gestionar cada uan de las solicitudes que recibe el clietne ya sea de parte del servidor central o del de descarga
     */
    public void run(){
        
        int filesize=250000000;
        int bytesRead; 
        int currentTot = 0; 
        float tamano = filesize;
        final int valor=0;
         
       byte [] bytearray = new byte [filesize]; 
        try{
            
           ObjectInputStream datos = new ObjectInputStream(ss.getInputStream());
           var= datos;
          
        
           try{   
          
           bytesRead = datos.read(bytearray,0,bytearray.length);
           currentTot = bytesRead; 
           System.out.println(bytesRead);
            
           if(bytesRead!=-1){ 
                FileOutputStream fos = new FileOutputStream("C:\\Users\\DarkJuan\\Desktop\\descarga"+Integer.toString(p)+".rar");
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                  System.out.println("Entrando en la transferencia");
                   i =p;
                  p++;
                 JFrame ventana= frame();
                 
            do 
                {
                  bytesRead = datos.read(bytearray, currentTot, (bytearray.length-currentTot)); 
                    if(bytesRead >= 0) 
                        currentTot += bytesRead;  
                    
                    carga(i,Main.array.get(i),"el archivo",(float)currentTot,ventana,label);
                }
            while(bytesRead > -1);
              
            Main.setPeso(0);
            //System.out.println("peso seteado");
            
            System.out.println(currentTot);
            bos.write(bytearray, 0 , currentTot); 
            bos.flush(); 
            bos.close();
            System.out.println("Transferencia completa");
            ss.close(); 
         
          }
          else{
               //System.out.println("entrando enb las otras");
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
               // System.out.println("estoy recibiendo los datos para ladescarga");
                datos.close();
                ss.close();
             
                Main.array.add((float)data.getSize());
                Main.setPeso(data.getSize());
               // System.out.println("variabale "+Main.getPeso());
                //conctandome con el de descarga
                    //  System.out.println(data.getIp());
                      //System.out.println(data.getPuerto());
                    //  System.out.println("");
                Socket serdes = new Socket(data.getIp(),data.getPuerto());
                ObjectOutputStream nombre = new ObjectOutputStream(serdes.getOutputStream());
                Estructura es = new Estructura(Main.port,data.getNombre());
                nombre.writeObject(es);
                //System.out.println("nombre neviado y mi puerto enviado");
                nombre.close();
                serdes.close();
              
                }
               
           }
           
                  {
                      if(obj instanceof String){


                        String error = (String)obj;
                        System.out.println(error);
                        ss.close();

                      }

                  }
           }
           }catch(Exception ea){
               System.out.println("Caida de COnexión "+ea.getMessage());
           }
           
          
        } catch (IOException ex) {
            Logger.getLogger(multihilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("termino el hilo");
    }
   
  /**
   * Método carga encargado de imprimir el porcentaje de descarga del archivo del lado del cliente
   * @param i variable de control
   * @param Tamano tamaño del archivo a descargar
   * @param fileName nombre del archivo
   * @param acum lo que se lleva descargado del archivo
   * @param ventana ventana en la cual se mostrara el porcentaje de descarga
   * @param label  seccion en la cual se mostrara el porcentaje de descarga
   */
      public static void carga(int i,float Tamano, String fileName,float acum, JFrame ventana,JLabel label){
          int p=0;
          float a = (acum/Tamano)*100;
          
          //System.out.println(" porcentaje --> "+a + "el peso totalv  "+Main.getPeso()+" lo que lleva "+acum);
          
          ventana.setTitle(" porcentaje --> "+a);
          
    }
      /**
       * Método encargado de crear una ventana por cada descarga que se realiza
       * @return JFrame retorna la ventana en la cual se imprimirá el procentaje de descarga
       */
      public JFrame frame(){
        JFrame frame = new JFrame("JFrame Example");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("");
   
        panel.add(label);
        frame.add(panel);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
   
        frame.setVisible(true);
         
        return frame;
      }
    
  
}
