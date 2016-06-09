 package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Servidor {

    static ConcurrentHashMap<Integer, Users> utilizadores;
    
    public static void main(String[] args) throws IOException {
       
        
        utilizadores = null;
        try {
            
            utilizadores = new ConcurrentHashMap<>();
            ServerSocket ss = new ServerSocket(2000);
            Socket cs = null;

            while ((cs = ss.accept()) != null){
                ServerThread t = new ServerThread(utilizadores, cs);
                t.start();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
