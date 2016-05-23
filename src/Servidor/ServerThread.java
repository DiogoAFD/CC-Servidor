/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class ServerThread extends Thread {

    private ConcurrentHashMap<Integer, Users> users;
    private Socket cs;

    public ServerThread(ConcurrentHashMap<Integer, Users> user, Socket cs) {
        this.users = user;
        this.cs = cs;
    }

    public void run() {
        try {

            InputStream in = cs.getInputStream();

            byte[] pdu = new byte[100];

            in.read(pdu); // preenche o array pdu
            String porta = "";
            String ip = "";
            int i;
            for (i = 7; (char) pdu[i] != ','; i++) {
                porta += (char) pdu[i];
            }

            // faz i++ ao inicio para avancar o ,
            for (i++; (char) pdu[i] != '\0'; i++) {
                ip += (char) pdu[i];
            }
            //ARRANJAR ISTO
            int id = 0;

            Users utilizador = new Users(id, porta, ip);

            users.put(id, utilizador);

            System.out.println("User: " + id + ", porta: " + porta + ", ip: " + ip);
            System.out.println("Existem "+users.size()+" no sistema");

            //OutputStream out = cs.getOutputStream();
            //out.write(pdu);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}
