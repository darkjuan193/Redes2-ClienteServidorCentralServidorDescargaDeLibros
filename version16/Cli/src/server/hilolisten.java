package server;

import java.net.ServerSocket;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import server.Main;
import allclass.Datos;
import allclass.Datos;

import java.io.*;
import java.lang.reflect.Array;


public class hilolisten extends Thread{

	
    public void run() {

         try{
             ServerSocket cliente = new ServerSocket(Main.port);
             System.out.println("Escuchador en cliente...");
             
             while(true){
                Socket descarga = cliente.accept();
                new multihilo(descarga).start();
                System.out.println("abriendo hilo--.-");
             
         }
             
             
             
             
         }catch(IOException e){
             System.out.println("hay un peo ahi");
         }

    }

}

