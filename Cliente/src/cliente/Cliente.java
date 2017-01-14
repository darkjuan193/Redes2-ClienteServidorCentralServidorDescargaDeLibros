import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket sk;
            DataOutputStream dos;
            DataInputStream dis;

            sk = new Socket("127.0.0.1", 10578);
            dos = new DataOutputStream(sk.getOutputStream());
            dis = new DataInputStream(sk.getInputStream());
            dos.writeUTF("2:juan:123");
            String respuesta="";
            respuesta = dis.readUTF();
            System.out.println(respuesta);
            dis.close();
            dos.close();
            sk.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
