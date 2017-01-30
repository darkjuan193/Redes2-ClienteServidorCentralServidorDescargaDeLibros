
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;
import allclass.ClaseServidor;
import allclass.Client;
import allclass.ReaderBook2;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom.Document;			// |
import org.jdom.Element;			// |\ Librerias
import org.jdom.JDOMException;		// |/ JDOM
import org.jdom.input.SAXBuilder;
import allclass.Estadística;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Namespace;
import server.SerDes;

/**
 *
 * @author Pablo
 */
public class leer2 {

  
    /**
     * metodo encargado de leer el XML y cargar los libros descargados en el ArrayList de libros descargados 
     */
     public static void cargarDatos2(){
        
          SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File( "libros_descargados.xml" );
                int i =0;
  
        try {
            try{
            Document document = (Document) builder.build( xmlFile );
            
            Element rootNode = document.getRootElement();
            
            List list = rootNode.getChildren( "libros");
                //System.out.println("asdsa");
            //System.out.println("sixe "+list.size());
            
            for(int j =0;j<list.size();j++){
                
                Element datos = (Element) list.get(j);
               
                //System.out.println(datos.getChildTextTrim("Ip"));
               // System.out.println(datos.getChildTextTrim("clientes_atendidos"));
               // System.out.println("nombre "+datos.getChildTextTrim("Nombre"));
               // System.out.println(" veces "+datos.getChildTextTrim("veces"));
                

               
              if( SerDes.getLibrosDescargados().get(j).getNombre().equals(datos.getChildTextTrim("Nombre"))){
                  
                  SerDes.getLibrosDescargados().get(j).setCantidad((Integer.parseInt(datos.getChildTextTrim("veces"))));
              }
              else{
                  Estadística es = new Estadística(datos.getChildTextTrim("Nombre"),Integer.parseInt(datos.getChildTextTrim("veces")));
                  SerDes.getLibrosDescargados().add(es);
              }
              
               
            }
            
            }
            catch(Exception e){
                System.out.println("exploto en xml tambien"+e.getMessage());
            }
     
            
            
        }
        catch(Exception a){
            System.out.println("exploto");
        }
    
     }
    
     public static void cargarFieles(){
        
          SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File( "clientes_fieles.xml" );
                int i =0;
  
        try {
            try{
            Document document = (Document) builder.build( xmlFile );
            
            Element rootNode = document.getRootElement();
            
            List list = rootNode.getChildren( "clientesfieles");
                //System.out.println("asdsa");
            //System.out.println("sixe "+list.size());
            
            for(int j =0;j<list.size();j++){
                
                Element datos = (Element) list.get(j);
               
                //System.out.println(datos.getChildTextTrim("Ip"));
               // System.out.println(datos.getChildTextTrim("clientes_atendidos"));
               // System.out.println("nombre "+datos.getChildTextTrim("Nombre"));
               // System.out.println(" veces "+datos.getChildTextTrim("veces"));
                Client cli = new Client(datos.getChildTextTrim("Nombre"),datos.getChildTextTrim("Ip"),Integer.parseInt(datos.getChildTextTrim("veces")),0);
               SerDes.getClientes().add(cli);
              /*if( SerDes.getLibrosDescargados().get(j).getNombre().equals(datos.getChildTextTrim("Nombre"))){
                  
                  SerDes.getLibrosDescargados().get(j).setCantidad((Integer.parseInt(datos.getChildTextTrim("veces"))));
              }
              else{
                  Estadística es = new Estadística(datos.getChildTextTrim("Nombre"),Integer.parseInt(datos.getChildTextTrim("veces")));
                  SerDes.getLibrosDescargados().add(es);
              }*/
              
               
            }
            
            }
            catch(Exception e){
                System.out.println("exploto en xml tambien"+e.getMessage());
            }
     
            
            
        }
        catch(Exception a){
            System.out.println("exploto");
        }
    
     }

}
