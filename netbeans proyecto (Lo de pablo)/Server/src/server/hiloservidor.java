package server;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


public class hiloservidor extends Thread implements Serializable{

	private Socket ss;
	private int counter;
	
	public hiloservidor(Socket i,int c){
		
		this.ss=i;
		this.counter=c;
	}
	
        @Override
	public void run(){
			//System.out.println("en el hilo 1 servidor");
		try{
                        boolean done=false;
                       String ip;
                    ip = ss.getRemoteSocketAddress().toString();
			System.out.println("hola cliente "+counter+" ip: "+ip);
                       // hilohablar hablar = new hilohablar(ss,counter,false);
                       // hablar.start();*/
                   
                        while(!done){
                            
                            ObjectInputStream entrada = new ObjectInputStream(ss.getInputStream());
                             System.out.println("asd");
                              ClaseServidor a = (ClaseServidor)entrada.readObject();
                              System.out.println(a.getIp());
                            
                        }
                        
                        
		
		}
		catch(Exception e){
			
		}
	}
	
	
}
