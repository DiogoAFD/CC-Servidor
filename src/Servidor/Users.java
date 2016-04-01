/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

public class Users {

    private Integer id;
    private String porta;
    private String ip;

    public Users() {
        this.id = 0;
        this.porta = "";
        this.ip = "";
    }

    public Users(Integer id, String porta, String ip) {
        this.id = id;
        this.porta = porta;
        this.ip = ip;
    }

}
