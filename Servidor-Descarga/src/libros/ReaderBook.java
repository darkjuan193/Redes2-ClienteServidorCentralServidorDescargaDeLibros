package libros;
import allclass.Estadística;
import allclass.ReaderBook2;
import java.io.File;


import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.SerDes;
import static server.SerDes.estadistica;
import static server.SerDes.librosDescargados;


public class ReaderBook {
	/**
         * metodo encargado de leer los archivos. 
         * @return retorna una ArrayList de los libros que contiene el servidor
         */
    public static ArrayList<ReaderBook2> lectura(){
    
      
            String dir=null;
            String path;
            ArrayList<String>carpeta = new ArrayList<String>();
            ArrayList<ReaderBook2>server = new ArrayList<ReaderBook2>();
            File afolder = null;
            try{
            path = new File(".").getCanonicalPath();
            afolder = new File(path+"\\libros");
            dir = path+"\\libros\\";
            }
            catch(Exception e){
                System.out.println("hubo un error en el archivo");
            }
            //carpeta.add("Ciencia-ficcion");
            //carpeta.add("Fantasia-epica-historica-oscura");
            carpeta = llenar(carpeta);
            int i=0;
            for (final File fileEntry : afolder.listFiles()) {
                ArrayList<String>libro = new ArrayList<String>();
                if(fileEntry.getName().contains(".rar")){
                    
                    
                    File folder = new File(dir+carpeta.get(i));
                    //System.out.println(folder.getAbsolutePath());
                    libro=leerlibros(libro,folder);
                    ReaderBook2 bo = new ReaderBook2(carpeta.get(i),fileEntry.length(),0,libro);
                   // System.out.println(bo.getNombre()+" " + bo.getSize() + " BTS "+bo.getLibros());
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
    
    public static ArrayList<String> llenar(ArrayList<String>carpeta){
            File afolder = null;
            String path;
            String dir;
            try{
            path = new File(".").getCanonicalPath();
            afolder = new File(path+"\\libros");
            dir = path+"\\libros\\";
            }
            catch(Exception e){
                System.out.println("hubo un error en el archivo");
            }
            
            
            for (final File fileEntry : afolder.listFiles()) {
                
                if(fileEntry.isDirectory()){
                    
                    carpeta.add(fileEntry.getName());
                    
                }
                
            }
           // System.out.println(carpeta);
           return carpeta;
        }
	
// fin de lfuncion
 //fin del a fncion de afuera
}