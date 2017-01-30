/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pablo
 */
import allclass.ClaseServidor;
import central.MainServer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
public class NewClass {
    public static void main(String args[]){
        
    try{    
    Socket a = new Socket("192.168.0.102",18942);
    }catch(IOException e){
        System.out.println(e.getMessage());
    }

}
public static void leer(ArrayList<ClaseServidor> as){
    
}
}
