/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allclass;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom.Document;			// |
import org.jdom.Element;			// |\ Librerias
import org.jdom.JDOMException;		// |/ JDOM
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 */
public class Leerxml {

  /**
   * metodo encargado de cargar los datos de descargas por servidor 
   * @param servidores devuelve la cantidad de libros descargados por servidores
   */
    public static void cargaDdatos(ArrayList<ClaseServidor>servidores) {
          SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File( "Datos.xml" );
                int i =0;
              
              
                
        try {
            try{
            Document document = (Document) builder.build( xmlFile );
            
            Element rootNode = document.getRootElement();
            
            List list = rootNode.getChildren( "Servidor" );
            
            //System.out.println("sixe "+list.size());
            
            for(int j =0;j<list.size();j++){
                
                Element datos = (Element) list.get(j);
               
               // System.out.println(datos.getChildTextTrim("Puerto"));
               // System.out.println(datos.getChildTextTrim("Ip"));
                
                
                 List colec = datos.getChildren();
                   ArrayList<ReaderBook2>reader = new ArrayList<ReaderBook2>();
                 for(int k =0;k<colec.size();k++){
                    
                     Element d =(Element) colec.get(k);
                     
                     if(d.getChildTextTrim("Genero")!=null || d.getChildTextTrim("Descargas")!=null){
                     //System.out.println(d.getChildTextTrim("Genero"));
                    // System.out.println(d.getChildTextTrim("Descargas"));
                     
                     ReaderBook2 bo = new ReaderBook2(d.getChildTextTrim("Genero"),(long)Integer.parseInt(d.getChildTextTrim("Peso")),Integer.parseInt(d.getChildTextTrim("Descargas")),null);
                     reader.add(bo);
                     }
                    
                 }
                 ClaseServidor ob = new ClaseServidor(datos.getChildTextTrim("Ip"),Integer.parseInt(datos.getChildTextTrim("Puerto")),0,reader);
                 servidores.add(ob);
            }
            
           // System.out.println("los servidores "+servidores.size());
            /*for(int p = 0;p<servidores.size();p++){
                System.out.println("Serviodor ip:  "+servidores.get(p).getIp()+" puerto "+servidores.get(p).getPuerto()); 
                
                ArrayList<ReaderBook2> bo = servidores.get(p).getColeccion();
                System.out.println("tamamo colec "+bo.size());
                for(int u = 0;u<bo.size();u++){
                    System.out.println("Coleccion "+bo.get(u).getNombre() +" contador  "+bo.get(u).getContador());
                }
            }c
            }*/
            }catch(Exception ne){
                System.out.println("exploto lectura xml "+ne.getMessage());
            }
            
        }
        catch(Exception ee){
            System.out.println("Exploto esa mierda");
        }
    }
  
     public static void cargarDatos2(ArrayList<Estadisticas>clientes){
        
          SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File( "estadistica.xml" );
                int i =0;
                
                        
               
        try {
            try{
            Document document = (Document) builder.build( xmlFile );
            
            Element rootNode = document.getRootElement();
            
            List list = rootNode.getChildren( "Servidor" );
            
            //System.out.println("sixe "+list.size());
            
            for(int j =0;j<list.size();j++){
                
                Element datos = (Element) list.get(j);
               
              //  System.out.println(datos.getChildTextTrim("Ip"));
               // System.out.println(datos.getChildTextTrim("clientes_atendidos"));
                
                
                Estadisticas es = new Estadisticas(datos.getChildTextTrim("Ip"),0,Integer.parseInt(datos.getChildTextTrim("clientes_atendidos")));
                clientes.add(es);
            }
            
            }
            catch(Exception e){
                System.out.println("exploto en xml tambien");
            }
     
            
            
        }
        catch(Exception a){
            System.out.println("exploto");
        }
    
     }
    
}
