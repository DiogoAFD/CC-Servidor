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
    private byte[] pdu;
    private Connect cs;
    private int id;

    public ServerThread(ConcurrentHashMap<Integer, Users> user, Socket s) throws IOException {
        this.users = user;
        this.cs = new Connect(s);
        id = 0;
    }

    public void run() {
<<<<<<< HEAD
        try{
=======
        try {
            while ((pdu = cs.readPDU()) != null) {
                dispacher(pdu);
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public void dispacher(byte[] pdu) throws IOException {

        char codigo = (char) pdu[2];
        System.out.println("codig -> "+codigo);
        switch (codigo) {
            /*case '0':
                //login de utilizador
                login(msg);
                break;*/
            case '1':
                //registo de novo utilizador
                registar(pdu);
                break;
            default:
                //mensagem mal recebida - codigo inexistente
                cs.sendMessage("KO");
                break;
        }
    }

    private void registar(byte[] pdu) {
        int i;
        String name = "", pass = "", ip = "", porta = "";

        for (i = 7; (char) pdu[i] != ','; i++) {
            name += (char) pdu[i];
        }
        // faz i++ ao inicio para avancar o ,
        for (i++; (char) pdu[i] != ','; i++) {
            pass += (char) pdu[i];
        }
        for (i++; (char) pdu[i] != ','; i++) {
            ip += (char) pdu[i];
        }
        for (i++; (char) pdu[i] != '\0'; i++) {
            porta += (char) pdu[i];
        }
        id++;
        Users utilizador = new Users(id, name, pass, ip, porta);

        if (this.ipExiste(ip)) {
            i--;
            System.out.println("cheguei");
            cs.sendMessage("1,ip ja existe");
        } else if (users.put(id, utilizador) != null) {
            cs.sendMessage("1,ok");
        } else {
            i--;
            cs.sendMessage("KO");
        }
    }
>>>>>>> origin/master

    /*
    public void run() {
        try {
            System.out.println("gsdjhzdf");
            InputStream in = cs.getInputStream();

            byte[] pdu = new byte[100];

            in.read(pdu); // preenche o array pdu
            int op=pdu[2];
            switch(op){
                case 1:
            String porta = "";
            String ip = "";
            String name = "";
            String pass = "";
            int i;
            for (i = 7; (char) pdu[i] != ','; i++) {
                name += (char) pdu[i];
            }
            for (i++; (char) pdu[i] != ','; i++) {
                pass += (char) pdu[i];
            }
            for (i++; (char) pdu[i] != ','; i++) {
                ip += (char) pdu[i];
            }
            // faz i++ ao inicio para avancar o ,
            for (i++; (char) pdu[i] != '\0'; i++) {
                porta += (char) pdu[i];
            }
            System.out.println("fors");
            //ARRANJAR ISTO
            int id = 0;

            Users utilizador = new Users(id, name, pass, ip, porta);
            System.out.println("cheugi2");
            users.put(id, utilizador);
            System.out.println("cheguei");
            System.out.println("User: " + id + ", porta: " + porta + ", ip: " + ip);
            System.out.println("Existem "+users.size()+" no sistema");
            
                case 2:
                    String nomeMusica ="";
                    String banda="";
                    String extensao ="";
                    
                    for(i=7;(char) pdu[i] != ','; i++){
                     nomeMusica += (char) pdu[i];
                    }
                    
                for(i++;(char) pdu[i] != ','; i++){
                     banda += (char) pdu[i];
                    }
                for(i++;(char) pdu[i] != '\0'; i++){
                     extensao += (char) pdu[i];
                    }
               
              
                
                    

            //OutputStream out = cs.getOutputStream();
            //out.write(pdu);
        }
        } catch(IOException e) {
            System.err.println(e.toString());
        }
<<<<<<< HEAD
    
=======
    }*/
    private boolean ipExiste(String ip) {
        for (Users s : users.values()) {
            if (s.getIp().equals(ip)) {
                return true;
            }
        }
        return false;
>>>>>>> origin/master
    }

}
