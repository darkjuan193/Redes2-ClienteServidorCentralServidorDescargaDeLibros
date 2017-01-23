package libros;
import allclass.ReaderBook2;
import java.io.File;


import java.io.IOException;
import java.util.ArrayList;


public class ReaderBook {
	
    public static ArrayList<ReaderBook2> lectura(){
    
       String dir="C:\\Users\\JOSEGREGORIO\\Desktop\\version16\\Servidor-Descarga\\libros\\";
   
        ArrayList<String>carpeta = new ArrayList<String>();
         ArrayList<ReaderBook2>server = new ArrayList<ReaderBook2>();
        
        carpeta.add("Ciencia-ficcion");
        carpeta.add("Fantasia-epica-historica-oscura");
        final File afolder = new File("C:\\Users\\JOSEGREGORIO\\Desktop\\version16\\Servidor-Descarga\\libros\\");
        int i=0;
         for (final File fileEntry : afolder.listFiles()) {
             ArrayList<String>libro = new ArrayList<String>();
		        if(fileEntry.getName().contains(".rar")){
                         
                               
                            File folder = new File(dir+carpeta.get(i));
                            libro=leerlibros(libro,folder);
                            ReaderBook2 bo = new ReaderBook2(carpeta.get(i),fileEntry.length(),0,libro);
                            System.out.println(bo.getNombre()+" " + bo.getSize() + " BTS "+bo.getLibros());
                            server.add(bo);
                            i++;
                     
		 }
         
    }
        return server;
    }
    
    public static ArrayList<String>leerlibros(ArrayList<String> libros,File file){
        
        
         for (final File fileEntry : file.listFiles()) {
             if(fileEntry.isFile()){
                 // System.out.println("el libro"+fileEntry.getName());
                 libros.add(fileEntry.getName());
             }
            
         }
        
        
        
        return libros;
    }
}
