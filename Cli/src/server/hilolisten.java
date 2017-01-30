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

	/**
         * 
         * Metodo encargado de recibir las respuestas del servidor de descargas y central para la descargas de los datos
         */
    public void run() {

         try{
      
             int i =0;
             ServerSocket cliente = new ServerSocket(Main.port);
             System.out.println("Escuchador en cliente...");
           
             while(true){
                Socket descarga = cliente.accept();
                new multihilo(descarga).start();
                System.out.println("abriendo hilo--.-");
             
         }
             
             
             
             
         }catch(IOException e){
             System.out.println("Hay un problema "+e.getMessage());
         }

    }

}

