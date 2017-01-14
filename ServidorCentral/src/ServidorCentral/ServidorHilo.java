package ServidorCentral;

import java.io.*;
import java.net.*;
import java.util.logging.*;

public class ServidorHilo extends Thread {
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSessio;
    
    public ServidorHilo(Socket socket, int id) {
        this.socket = socket;
        this.idSessio = id;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        String accion = "";
        try {
            accion = dis.readUTF();
            //Divido el msj que envia el cliente
            String[] parte = accion.split(":");
            System.out.println("Protocolo 0 "+parte[0]);
            System.out.println("Protocolo 1 "+parte[1]);
            System.out.println("Protocolo 2 "+parte[2]);
            //utilizo el protocolo parte 0
            switch ( parte[0] ) {
                case "1":
                    {//Registro
                     //Almaceno en variables   
                     String Nick = parte[1];
                     String Pass = parte[2];
                     //Comparo con el ARCHIVO A VER SI ESTOY REGISTRADO!
                     if("juan".equals(Nick)){
                        //ESTAS LOGUEADO
                        dos.writeUTF("Estas registrado");
                     }
                     else{
                        //Guardo datos en el XML
                        dos.writeUTF("Tu nick es: "+ Nick +" Y tu Pass "+Pass+" GUARDAR");
                     }    
                break;}
                case "2":
                    {//logueo
                     //Almaceno en variables   
                     String Nick = parte[1];
                     String Pass = parte[2];
                     //Comparo con el ARCHIVO A VER si pertenezco al archivo!
                     if("juan3".equals(Nick)){
                        dos.writeUTF("Usuario y contraseña incorrectas");
                     }
                     else{
                        dos.writeUTF("LOGUEADO");
                     }   
                break;}
                case "3":
                    {//solicitan colecciones de libros por autor o por género. 
                break;}
                case "4":
                    {//
                break;}
                default:
                {System.out.println("error" );
                break;}
            }
         } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconnectar();
    }
}