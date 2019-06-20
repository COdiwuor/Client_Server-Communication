package server.client;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SocketClient extends JFrame{

    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message = "";
    private String serverIP;
    private Socket connection;

    //constructor for the client
    public SocketClient(String host){
        super  ("Client Interface");
        serverIP = host;
        userText = new JTextField();
        userText.setEditable(false);
        userText.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        sendMessage(event.getActionCommand());
                        userText.setText("");
                    }
                }
        );
        add(userText, BorderLayout.SOUTH);
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow), BorderLayout.CENTER);
        setSize(500,450);
        setVisible(true);
    }

    //connect to the server
    public void startRunning(){
        try{
            connectToServer();
            setUpStream();
            whileRunning();
        }catch(EOFException eofException){
            showMessage("\nClient terminated connection");
        }catch(IOException ioException){
            ioException.printStackTrace();
        }finally{
            closeSocket();
        }
    }

    //connect to server
    private void connectToServer() throws IOException{
        showMessage("Trying to connect..\n");
        connection = new Socket(InetAddress.getByName(serverIP),9090);
        showMessage("Connected to: " + connection.getInetAddress().getHostName());
    }

    //set up streams for sending and receiving messages
    private void setUpStream() throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("\nStreams are now set up");
    }

    //while communicating with the server
    private void whileRunning() throws IOException{
        ableToType(true);
        do{

            try{
                message = (String) input.readObject();
                showMessage("\n" + message);
            }catch(ClassNotFoundException classNotFoundException){
                showMessage("\nObject not recognized");
            }

        }while(!message.equals("SERVER - END"));
    }

    //close the streams and sockets
    private void closeSocket(){
        showMessage("\nClosing the sockets");
        ableToType(false);
        try{
            output.close();
            input.close();
            connection.close();
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

    //sening messages to the server
    private void sendMessage(String message){
        try{
            output.writeObject("MERCHANT - " + message);
            output.flush();
            showMessage("\nMERCHANT - " + message);
        }catch(IOException ioException){
            chatWindow.append("\nMessage was interfered with");
        }
    }

    //change/update chat window
    private void showMessage(final String messages){
        SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                        chatWindow.append(messages);
                    }
                }
        );
    }

    //allows user to enter information
    private void ableToType(final boolean trueOrFalse){
        SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                        userText.setEditable(trueOrFalse);
                    }
                }
        );
    }

   
}