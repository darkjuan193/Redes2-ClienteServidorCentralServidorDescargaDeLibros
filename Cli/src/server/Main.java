/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 */
public class Main {
public static final int port = 54350;
public static long peso = 0;
public static int portcentral=4890;
public static int seteador =0;
public static ArrayList<Float> array = new ArrayList<Float>();


public static String ipservidor="192.168.4.23";
    /**
     * Cosntructor principal de la clase Main encargado de iniciar los hilos de escucha y habla del cliente
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
    	new hilohablar().start();
    	new hilolisten().start();
    
    }

    public int getPort() {
        return port;
    }

    public static long getPeso() {
        return peso;
    }

    public static void setPeso(long peso) {
        Main.peso = peso;
    }

 
    
}
