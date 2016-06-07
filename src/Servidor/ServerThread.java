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
import java.util.Map;
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
    
    private void perguntarFicheiro(byte[] pdu){
        int i;
        String nomeMusica = "", banda = "", extensao="";
        
          for (i = 7; (char) pdu[i] != ','; i++) {
            nomeMusica += (char) pdu[i];
        }
        // faz i++ ao inicio para avancar o ,
        for (i++; (char) pdu[i] != ','; i++) {
            banda += (char) pdu[i];
        }
        for (i++; (char) pdu[i] != '\0'; i++) {
            extensao += (char) pdu[i];
        }
        
        for(Map.Entry<Integer, Users> us:users.entrySet()){
            
           cs.sendMessage(new String(nomeMusica","+banda+","+extensao));
        
        }
        
        
        
        
    
    }
    
    
    private boolean ipExiste(String ip) {
        for (Users s : users.values()) {
            if (s.getIp().equals(ip)) {
                return true;
            }
        }
        return false;
    }
}