package central;

import allclass.ClaseServidor;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import allclass.ClaseServidor;
import java.util.ArrayList;
import allclass.Client;
import allclass.Estadisticas;
import allclass.ReaderBook2;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author Pablo
 */
public class MainServer{

    /**
     * @param args the command line arguments
     */
    public static ArrayList<ClaseServidor>losServidores= new ArrayList<ClaseServidor>();
    public static ArrayList<Client>losClientes = new ArrayList<Client>();
    public static ArrayList<String>libros = new ArrayList<String>();
    public static ArrayList<Estadisticas>ClientesAtendidos= new ArrayList<Estadisticas>();

    
    public static void main(String[] args) {
        
        try {
			ServerSocket server = new ServerSocket(4890);
			System.out.println("Esperando conexion");
                        new consola().start();      
			
			int i=1;
			for(;;){
				
				Socket clienteNuevo = server.accept();                           
                                new hiloservidor(clienteNuevo,i,losServidores,losClientes).start();
				i++;
			}

		} catch (IOException e){
			e.printStackTrace();
		}
    }
    
    
    public static ArrayList<ClaseServidor> getLosServidores() {
        return losServidores;
    }

    public static void setLosServidores(ArrayList<ClaseServidor> losServidores) {
        MainServer.losServidores = losServidores;
    }

    public static ArrayList<Client> getLosClientes() {
        return losClientes;
    }

    public static void setLosClientes(ArrayList<Client> losClientes) {
        MainServer.losClientes = losClientes;
    }

    public static ArrayList<String> getLibros() {
        return libros;
    }

    public static void setLibros(ArrayList<String> libros) {
        MainServer.libros = libros;
    }

    public static ArrayList<Estadisticas> getClientesAtendidos() {
        return ClientesAtendidos;
    }

    public static void setClientesAtendidos(ArrayList<Estadisticas> ClientesAtendidos) {
        MainServer.ClientesAtendidos = ClientesAtendidos;
    }

    
      public static void generate(String name, ArrayList<ClaseServidor> servidores) throws Exception{

  

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, name, null);
            document.setXmlVersion("1.0");

            //Main Node
            ArrayList<ReaderBook2> reader;
            Element raiz = document.getDocumentElement();
            //Por cada Genero creamos un item que contendrá la Genero y el value
            for(int i=0; i<servidores.size();i++){
                reader=servidores.get(i).getColeccion();
                
              
                    
                Element datoNode = document.createElement("Servidor"+i); 
                //Key Node
                Element GeneroNode = document.createElement("Puerto"); 
                Text nodeKeyValue = document.createTextNode(Integer.toString(servidores.get(i).getPuerto()));
                GeneroNode.appendChild(nodeKeyValue);      
                
                Element valueNode = document.createElement("Ip"); 
                Text nodeValueValue = document.createTextNode(servidores.get(i).getIp());                
                valueNode.appendChild(nodeValueValue);
                
               datoNode.appendChild(GeneroNode);
                datoNode.appendChild(valueNode);
                  for(int p = 0;p<reader.size();p++){
                 Element coleccion = document.createElement("Coleccion");
             
                 Element contadorNode2 = document.createElement("Genero");
                 Text nodeContador2 = document.createTextNode(reader.get(p).getNombre());
                 contadorNode2.appendChild(nodeContador2);
                 coleccion.appendChild(contadorNode2);
                 
                 Element contadorNode3 = document.createElement("Descargas");
                 Text nodeContador3 = document.createTextNode(Integer.toString(reader.get(p).getContador()));
                 contadorNode3.appendChild(nodeContador3);
                 coleccion.appendChild(contadorNode3);
                
                datoNode.appendChild(coleccion);
               
                //append itemNode to raiz
                //pegamos el elemento a la raiz "Documento"}
            }
                 
            raiz.appendChild(datoNode);       
                  
                  
           }                
            //Generate XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File(name+".xml")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
       // }
    }
        
}