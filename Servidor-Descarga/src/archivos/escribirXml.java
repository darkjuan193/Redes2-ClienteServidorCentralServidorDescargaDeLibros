/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import allclass.Client;
import allclass.Estadística;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import server.SerDes;

/**
 *
 * @author Pablo
 */
public class escribirXml {
    /**
     * metodo encargado de escribir en XML los libros descargados 
     * @param librosDescargados recibe el arrayList de los libros descargados 
     **/
      public static void libros_descargados(ArrayList<Estadística>librosDescargados) throws  TransformerConfigurationException, TransformerException, ParserConfigurationException{
           DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null,"Libros_Descargados", null);
            document.setXmlVersion("1.0");
            Element raiz = document.getDocumentElement();
           
             
             for(int i = 0;i<librosDescargados.size();i++){
                 
                  Element datoNode = document.createElement("libros"); 
                 
                 Element GeneroNode = document.createElement("Nombre"); 
                Text nodeKeyValue = document.createTextNode(librosDescargados.get(i).getNombre());
                GeneroNode.appendChild(nodeKeyValue);      
                
                Element valueNode = document.createElement("veces"); 
                Text nodeValueValue = document.createTextNode(Integer.toString(librosDescargados.get(i).getCantidad()));                
                valueNode.appendChild(nodeValueValue);
                
                datoNode.appendChild(GeneroNode);
                datoNode.appendChild(valueNode);
                
                raiz.appendChild(datoNode); 
                 
             }
                Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File("libros_descargados.xml")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
             
             
      }
      
      public static void escribir_fieles(ArrayList<Client>fieles) throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
          
                
          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null,"clientes_fieles", null);
            document.setXmlVersion("1.0");
            Element raiz = document.getDocumentElement();
            
            for(int i = 0;i<fieles.size();i++){
                 
                  Element datoNode = document.createElement("clientesfieles"); 
                 
                 Element GeneroNode = document.createElement("Nombre"); 
                Text nodeKeyValue = document.createTextNode(fieles.get(i).getNombre());
                GeneroNode.appendChild(nodeKeyValue);      
                
                Element valueNode = document.createElement("Ip"); 
                Text nodeValueValue = document.createTextNode(fieles.get(i).getIp());                
                valueNode.appendChild(nodeValueValue);
                
                Element valueNode2 = document.createElement("veces"); 
                Text nodeValueValue2 = document.createTextNode(Integer.toString(fieles.get(i).getBandera()));                
                valueNode2.appendChild(nodeValueValue2);
                
                datoNode.appendChild(GeneroNode);
                datoNode.appendChild(valueNode);
                datoNode.appendChild(valueNode2);
                raiz.appendChild(datoNode); 
                 
             }
          Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File("clientes_fieles.xml")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
      }
     
      
}
