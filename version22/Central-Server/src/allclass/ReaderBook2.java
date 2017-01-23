package allclass;

import java.io.Serializable;
import java.util.ArrayList;

public class ReaderBook2 implements Serializable{

	
	private static final long serialVersionUID = -3575008262277095190L;
	private String nombre;
	private long size;
        private int contador;
        private ArrayList<String>libros;

    public ReaderBook2(String nombre, long size, int contador, ArrayList<String> libros) {
        this.nombre = nombre;
        this.size = size;
        this.contador = contador;
        this.libros = libros;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNombre() {
        return nombre;
    }

    public long getSize() {
        return size;
    }

    public int getContador() {
        return contador;
    }

    public ArrayList<String> getLibros() {
        return libros;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public void setLibros(ArrayList<String> libros) {
        this.libros = libros;
    }

    
}
