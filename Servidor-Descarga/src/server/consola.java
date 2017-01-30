/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import archivos.escribirXml;
import static archivos.escribirXml.libros_descargados;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Pablo
 */
public class consola extends Thread{
    /**
     * metodo encargado de mostrar en pantalla las distintas opciones que tiene el servidor de descarga 
     */
    public void run(){
        
    while(true){
        System.out.println("consola");
    Scanner es = new Scanner(System.in);
    
    String a = es.nextLine();
    
    
        if(a.equals("descargando")){
            
            if(SerDes.getEstadistica().size()!=0){
                for(int i =0;i<SerDes.getEstadistica().size();i++){
                    
                    System.out.println("el servidor tiene la coleccion "+SerDes.getEstadistica().get(i).getNombre()+" con "+SerDes.getEstadistica().get(i).getCantidad()+" clientes asociados");
                }
            }
            else
                System.out.println("No hay libros descargando");
        
        }
        
        if(a.equals("descargas")){
             if(SerDes.getLibrosDescargados().size()!=0){
                for(int i =0;i<SerDes.getLibrosDescargados().size();i++){
                
                    System.out.println("La coleccion "+SerDes.getLibrosDescargados().get(i).getNombre()+" ha sido descargada  "+SerDes.getLibrosDescargados().get(i).getCantidad());
                }
            }
             else
                 System.out.println("no hay libros descargados por parte del servidor");
        }
        
        if(a.equals("clientes_fieles")){
         // System.out.println("la cantidad de lcietnes ene lser des "+SerDes.getClientes().size());
            for(int i = 0; i<SerDes.getClientes().size();i++){
                System.out.println("El cliente "+SerDes.getClientes().get(i).getNombre()+" con ip: "+SerDes.getClientes().get(i).getIp()+" ha descargado "+SerDes.getClientes().get(i).getBandera()+ " veces");
            }
        }
        
        
        
        if(a.equals("salir")){
            System.out.println("Saliendo consola");
            
            try {
                escribirXml.libros_descargados(SerDes.getLibrosDescargados());
                escribirXml.escribir_fieles(SerDes.getClientes());
                
            } catch (TransformerException ex) {
                Logger.getLogger(consola.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(consola.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    }
}
