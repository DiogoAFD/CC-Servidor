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

        
        //System.out.println(pdu[2]);
        switch (pdu[2]) {
            /*case 0:
                //login de utilizador
                login(msg);
                break;*/
            case 1:
                //registo de novo utilizador
                registar(pdu);
               // System.out.println("");
                break;
            case 2:
                //perguntarFicheiro(pdu,id);
                break;
            default:
                //mensagem mal recebida - codigo inexistente
                cs.sendMessage("KO");
                break;
        }
    }

    private void registar(byte[] pdu) throws IOException {
        
       
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
       
        //id++;
        id=users.keySet().size();
        Users utilizador = new Users(id, name, pass, ip, porta);   
        
        if (this.ipExiste(ip)) {  
            i--;
            cs.sendMessage("1,ip ja existe");
        } 
        else{
            //System.out.println("Antes do PUT" +users.keySet().size());
            users.put(id, utilizador);
            //System.out.println("Depois do PUT" +users.keySet().size());
            
            cs.sendMessage("1,ok");
        } 
    }
    
    // o id é do cliente que faz o pedido e direciona o pdu de pedido para todos os cliente registados no servidor
   private void perguntarFicheiro(byte[] pdu,int id1) throws IOException{
        
        
        for(Map.Entry<Integer, Users> us:users.entrySet()){
            
            if(us.getKey()!=id1 && us.getValue().isAtivo()){
            
           Socket auxS= new Socket(us.getValue().getIp(),Integer.getInteger(us.getValue().getPorta()));
           Connect cc = new Connect(auxS);
           cc.getOut().print(pdu);
            }
        
        }
        
        
  }
    
    /* Este metodo vai receber as resposta de todos os clientes e encaminha las 
    para o cliente que fez o pedido*/
    
    public void trataRespostas(int ident) throws IOException{
        
        
        Users u= users.get(ident);
        Socket aux=new Socket(String.valueOf(ident),Integer.getInteger(u.getPorta()));
        Connect cc = new Connect(aux);
        
        while( (pdu= cs.readPDU()) != null){
        
            if(pdu[2]==3) cc.getOut().print(pdu);
        
        
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