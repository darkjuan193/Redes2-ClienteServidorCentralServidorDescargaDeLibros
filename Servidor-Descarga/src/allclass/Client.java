package allclass;

import java.io.Serializable;

/**
 * 
 * @author Pablo
 */
public class Client implements Serializable{
   
    private static final long serialVersionUID = 8833492341333260484L;
    private String nombre;
    private String ip;
    private int bandera;
    private int port;
    
    /**
     * la clase client encargada de recibir los datos del cliente 
     * @param nombre este es el nombre del cliente
     * @param ip  la ip de donde se esta conectando el cliente
     * @param bandera variable para control de estadisticass
     * @param port puerto de escucha de respuesta de los servidores
     */
    public Client(String nombre,String ip,int bandera,int port){
        this.nombre=nombre;
        this.ip = ip;
        this.bandera = bandera;
        this.port=port;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getBandera() {
        return bandera;
    }

    public void setBandera(int bandera) {
        this.bandera = bandera;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    
}
