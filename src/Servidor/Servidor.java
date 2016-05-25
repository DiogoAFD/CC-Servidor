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
<<<<<<< HEAD
                
                

=======
>>>>>>> origin/master
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}

//
//public Servidor(String ip)  throws IOException{
//		
//		this.ip = ip;
//                this.utilizadores=new HashMap<>();
//		
//
//	}
//
//
//        public boolean verificaUtilizador(String ip){
//		return this.utilizadores.containsKey(ip);
//	}
//        
//        
//        
//        // Metodo que devolve um arrayList com os ips do utilizadores que tem o ficheiro
//        public ArrayList<String> getUserFiles(){
//        
//        }
//        
//        
//         
//                
//        
//       public static void main(String[] args) throws InterruptedException, IOException{
//           
//           ServerSocket ss = new ServerSocket(Integer.parseInt(args[0]));
//           Socket c;
//           c=ss.accept();
//           PrintWriter pw= new PrintWriter(c.getOutputStream());
//           BufferedReader bf =new BufferedReader(new InputStreamReader(c.getInputStream()));
//           
//           while((c=ss.accept())!=null){
//               
//               String comando = bf.readLine();
//               
//               String[] parts = comando.split(",");
//               
//               int op = Integer.parseInt(parts[2]);
//               
//               switch(op){
//               
//                   //Registar Cliente
//                   case 1:
//                       if(parts[3]=="U"){
//                       
//                           if(utilizadores.containsKey(parts[4]) pw.println("1,0,1,User ja Existe");
//                           else {
//                           
//                           Cliente c = new Cliente(parts[4],parts[5],parts[6]);
//                           utilizadores.put(parts[4],c);
//                           pw.println("1,0,1,OK");
//                           
//                           } 
//                          
//                       }
//                       
//                       
//                       
//                   case 2:
//                       
//                   case 3:
//                       
//                   case 4:
//                       
//                   case 5:
//                   
//                   case 6:     
//               
//               
//               
//               
//               }
//           
//           
//           
//           }
//       
//       
//       
//       }         
//        
//       
//
//	
//    
//}
