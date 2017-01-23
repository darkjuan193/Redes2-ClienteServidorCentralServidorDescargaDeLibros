package archivos;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class archivo {

	public static void buscar(String nombre,String ip,int puerto){
		try {
			System.out.println(ip);
			System.out.println(puerto);
			Socket so = new Socket(ip,puerto);
			String cu = new File(".").getCanonicalPath();
                        System.out.println("el puto nombre "+nombre);
			final File transferFile = new File(cu+"\\libros\\"+nombre+".rar");
			byte [] bytearray = new byte [(int)transferFile.length()];
			System.out.println(bytearray.clone());
			System.out.println(transferFile.length());
			
			FileInputStream fin = new FileInputStream(transferFile); 
			BufferedInputStream bin = new BufferedInputStream(fin);
			
			bin.read(bytearray,0,bytearray.length); 
			ObjectOutputStream os = new ObjectOutputStream(so.getOutputStream());
			
			System.out.println("Sending Files..."); 
			
			os.write(bytearray,0,bytearray.length); 
			os.flush(); 
			so.close(); 
			System.out.println("File transfer complete"); 
			
			System.out.println(cu);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		buscar("Fantasia-urbana-paranormal.rar","192.168.0.101",50000);
	}
	}
