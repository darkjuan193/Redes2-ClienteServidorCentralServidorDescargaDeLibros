package allclass;

import java.io.Serializable;

public class ReaderBook2 implements Serializable{

	
	private static final long serialVersionUID = -3575008262277095190L;
	private String nombre;
	private long size;
	
	
	public ReaderBook2(String nombre, long size) {
		super();
		this.nombre = nombre;
		this.size = size;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
	
	
	
	
}
