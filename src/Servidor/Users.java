/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

public class Users {

    private Integer id;
    private String name;
    private String pass;
    private String ip;
    private String porta;

    public Users() {
        this.id = 0;
        this.name = "";
        this.pass = "";
        this.ip = "";
        this.porta = "";
        
    }

    public Users(Integer id, String name, String pass, String ip, String porta) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.ip = ip;
        this.porta = porta;
    }

}
