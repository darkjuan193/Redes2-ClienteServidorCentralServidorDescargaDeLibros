/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidordescarga;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DarkJuan
 */
public class ServidorDescarga {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         //Creo socket
        ServerSocket ss;
        System.out.print("Inicializando servidor De Descargas ");
        try {
            //creo el SK y asigno puerto de escucha
            ss = new ServerSocket(10577);
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
            Logger.getLogger(ServidorDescarga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
