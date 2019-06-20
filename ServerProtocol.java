package server.client;

import javax.swing.JFrame;

public class ServerProtocol {
//main method
    public static void main(String[] args){
        SocketServer merchantServer = new SocketServer();
        merchantServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        merchantServer.Onstart();
    }

}