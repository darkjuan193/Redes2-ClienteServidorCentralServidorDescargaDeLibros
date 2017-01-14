package ServidorEscucha;

import java.io.*;
import java.net.*;
import java.util.logging.*;
public class Servidor {
    public static void main(String args[]) throws IOException {
        //Creo socket
        ServerSocket ss;
        System.out.print("Inicializando servidor Central ");
        try {
            //creo el SK y asigno puerto de escucha
            ss = new ServerSocket(10578);
            System.out.println("\t[OK]");
            //contador de clientes
            int idSession = 0;
            while (true) {
                //Creo objeto tipo socket para pasarlo al servidor de hilos
                Socket socket;
                //Espero cliente
                socket = ss.accept();
                System.out.println("Nueva conexión entrante: "+socket);
                //levanto el hilo
                ((ServidorHilo) new ServidorHilo(socket, idSession)).start();
                idSession++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}