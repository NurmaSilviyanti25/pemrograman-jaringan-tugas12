/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pemrograman.jaringan.tugas12;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 *
 * @author User
 */
public class Client {
    
    private Socket clientSocket;
    private PrintWriter pw;
    private BufferedReader br;
    
    public void startConnection(String ip, int port) {
        
        try {
            clientSocket = new Socket(ip, port);
            pw = new PrintWriter(clientSocket.getOutputStream(), true);
            br = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
        }catch(IOException ex){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String sendMessage(String msg){
        String resp = null;
        
        try{
            pw.println(msg);
            resp = br.readLine();
        }catch(IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }
    
    public void stopConnection(){
        
        try{
            br.close();
            pw.close();
            clientSocket.close();
        }catch(IOException ex){
           Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
}
