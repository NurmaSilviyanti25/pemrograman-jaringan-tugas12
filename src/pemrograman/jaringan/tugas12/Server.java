/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pemrograman.jaringan.tugas12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author User
 */
public class Server {
    
    private ServerSocket serverSocket;
    private Socket socket;
    private PrintWriter pw;
    private BufferedReader br;
    
    public void start(int port){
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            pw = new PrintWriter(socket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String inputLine;
            
            while((inputLine = br.readLine()) != null){
               pw.println(inputLine);
               pw.println();
            }
        }catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stop(){
        try{
            br.close();
            pw.close();
            serverSocket.close();
        }catch(IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
    Server server = new Server();
    server.start(6666);
}

}