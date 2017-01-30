package archivos;

import allclass.Estadística;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import server.SerDes;

public class archivo {
/**
 * este metodo se encarga de abrir una conexion con el cliente para la transferencia
 * @param nombre nombre del archivo a transferir
 * @param ip ip del cliente
 * @param puerto puerto del cliente
 */	public static void buscar(String nombre,String ip,int puerto){
            
                for(int i = 0;i<SerDes.getEstadistica().size();i++){
                    if(SerDes.getEstadistica().get(i).getNombre().equals(nombre)){
                      SerDes.getEstadistica().get(i).setCantidad(SerDes.getEstadistica().get(i).getCantidad()+1);
                     SerDes.getLibrosDescargados().get(i).setCantidad(SerDes.getLibrosDescargados().get(i).getCantidad()+1);
                    }
                }
                          
		try {
			System.out.println(ip);
			System.out.println(puerto);
			Socket so = new Socket(ip,puerto);
			String cu = new File(".").getCanonicalPath();
                        
			final File transferFile = new File(cu+"\\libros\\"+nombre+".rar");
			byte [] bytearray = new byte [(int)transferFile.length()];
			//System.out.println(bytearray.clone());
			//System.out.println(transferFile.length());
			
			FileInputStream fin = new FileInputStream(transferFile); 
			BufferedInputStream bin = new BufferedInputStream(fin);
			
			bin.read(bytearray,0,bytearray.length); 
			ObjectOutputStream os = new ObjectOutputStream(so.getOutputStream());
			
			System.out.println("Sending Files..."); 
			try{
			os.write(bytearray,0,bytearray.length); 
			os.flush(); 
			so.close();
                        System.out.println("File transfer complete"); 
                        remove(nombre);
                        clientes_fieles(ip);
                        }catch(IOException e2){
                            System.out.println("El profesor quito el cable" +e2.getMessage());
                           remove2(nombre);
                           remove(nombre);
                        }
			
			
			//System.out.println(cu);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
         * este metodo encargado de controlar la estadistica libros descargando
         * @param nombre nobre de la coleccion a remover de la estadistica
         */
        public static void remove(String nombre){
            

            for(int i =0;i<SerDes.getEstadistica().size();i++){
                if(SerDes.getEstadistica().get(i).getNombre().equals(nombre)){
                    SerDes.getEstadistica().get(i).setCantidad(SerDes.getEstadistica().get(i).getCantidad()-1);
                }
            }
            
            
        }
        /**
         * metodo encargado de manejar la estadistica de los libros descargados
         * @param nombre2 nombre de la coleccion 
         */
        public static void remove2(String nombre2){
            

            for(int i =0;i<SerDes.getLibrosDescargados().size();i++){
                if(SerDes.getLibrosDescargados().get(i).getNombre().equals(nombre2)){
                    SerDes.getLibrosDescargados().get(i).setCantidad(SerDes.getLibrosDescargados().get(i).getCantidad()-1);
                }
            }
            
            
        }
        /**
         * metodo encargado de manejar la estadistica de clientes fieles
         * @param ip ip del cliente
         */
        public static void clientes_fieles(String ip){
            
            for(int i = 0;i<SerDes.getClientes().size();i++){
                if(SerDes.getClientes().get(i).getIp().equals(ip))
                {
                    SerDes.getClientes().get(i).setBandera(SerDes.getClientes().get(i).getBandera()+1);
                    //System.out.println("cliente aumentado el contador");
                }
            }
        }
        
}
