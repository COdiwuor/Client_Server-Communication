package server.client;

import javax.swing.JFrame;
public class ClientProtocol {
     //main method
    public static void main(String[] args){
        SocketClient Client;
        Client = new SocketClient("127.0.0.1");
        Client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Client.startRunning();
    }

}