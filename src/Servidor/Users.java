/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.net.Socket;

public class Users {

    private Integer id;
    private String name;
    private String pass;
    private String ip;
    private String porta;
    private Socket Sc;

    public Users() {
        this.id = 0;
        this.name = "";
        this.pass = "";
        this.ip = "";
        this.porta = "";
        this.Sc=new Socket();
        
    }

    public Users(Integer id, String name, String pass, String ip, String porta) throws IOException {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.ip = ip;
        this.porta = porta;
        this.Sc=new Socket(ip,Integer.getInteger(porta));
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getPorta() {
        return porta;
    }

    public String getIp() {
        return ip;
    }

    
    
    

}
