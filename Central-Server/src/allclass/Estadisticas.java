package allclass;

public class Estadisticas{


	private String ipser;
        //private String ipcli;
	private int port;
	private int veces;
/**
 * Metodo encargado de manejar las estadisticas de servidor central
 * @param ipser ip derl servidor 
 * @param port puerto del servidor 
 * @param veces veces que se conecta un servidor
 */
    public Estadisticas(String ipser,int port, int veces) {
        this.ipser = ipser;
        //this.ipcli = ipcli;
        this.port = port;
        this.veces = veces;
    }

    public String getIpser() {
        return ipser;
    }

    public void setIpser(String ipser) {
        this.ipser = ipser;
    }

    //public String getIpcli() {
        //return ipcli;
    //}

//    public void setIpcli(String ipcli) {
//        this.ipcli = ipcli;
//    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getVeces() {
        return veces;
    }

    public void setVeces(int veces) {
        this.veces = veces;
    }
	


        

}