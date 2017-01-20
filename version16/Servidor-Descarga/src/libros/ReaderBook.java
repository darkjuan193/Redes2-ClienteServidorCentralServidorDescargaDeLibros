package libros;
import allclass.ReaderBook2;
import java.io.File;


import java.io.IOException;
import java.util.ArrayList;


public class ReaderBook {
	
public static ArrayList<ReaderBook2> lectura(){
		
		ArrayList<ReaderBook2> coleccion = new ArrayList<ReaderBook2>();
		int size=0;
		String path = null;
		try {
			path = new File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final File afolder = new File(path+"\\libros");
		
		coleccion = listRar(afolder,coleccion);
		
		return coleccion;
		
		
	}

public static ArrayList<ReaderBook2> listRar(File afolder,ArrayList<ReaderBook2>colection) {
	

	 for (final File fileEntry : afolder.listFiles()) {
	        if (fileEntry.isAbsolute()) { //leo el nombre de los direcorios o rar
	        
	        	//colection.add(fileEntry.getName().toString());
	        	ReaderBook2 col = new ReaderBook2(fileEntry.getName(),fileEntry.length());
	        	colection.add(col);
	        }
	 }
	return colection;
	
}
	
	/*public static ArrayList<String> lectura(){
		
		ArrayList<String> coleccion = new ArrayList<String>();
		int size=0;
		String path = null;
		try {
			path = new File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final File afolder = new File(path+"\\libros");
		
		coleccion = listRar(afolder,coleccion);
		
		return coleccion;
		
		
	}
	
	public static ArrayList<String> listRar(File afolder,ArrayList<String>colection) {
		

		 for (final File fileEntry : afolder.listFiles()) {
		        if (fileEntry.isAbsolute()) { //leo el nombre de los direcorios o rar
		        
		        	colection.add(fileEntry.getName().toString());
		        	
		        }
		 }
		return colection;
		
	}*/
	
	
}
