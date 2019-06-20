package server.client;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class SocketServer extends JFrame {

    private JTextField userInput;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    private ToyMerchant toyinformation;

    //the code below is for the GUI
    public SocketServer(){
        super("Server Interface ");
        userInput = new JTextField();
        userInput.setEditable(false);
        userInput.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        sendMessage(event.getActionCommand());
                        userInput.setText("");
                    }
                }
        );
        add(userInput, BorderLayout.SOUTH);
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow));
        setSize(500,450);
        setVisible(true);
        toyinformation = new ToyMerchant();
    }

    //setting up the server
    public void Onstart(){

        try{
            server = new ServerSocket(9090, 100);
            while(true){
                try{
                    //connect and have conversation
                    Connection();
                    StreamSetUp();
                    OnRunning();
                }catch(EOFException eofException){
                    showMessage("\nServer ended the connection");
                }finally {
                    closeSocket();
                }
            }
        } catch (IOException ioException){
            ioException.printStackTrace();
        }

    }
    //wait for connection
    private void Connection() throws IOException{
        showMessage("Waiting for connection...\n");
        connection = server.accept();
        showMessage("Connected to "+connection.getInetAddress().getHostName());
    }

    //setting the stream to receive and send data
    private void StreamSetUp() throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("\nServer is up and running");
    }

    //code for while the server is running(during the conversation)
    private void OnRunning() throws IOException{
        String message = "Hello Toy Merchant...";
        sendMessage(message);
        ableToType(true);
        ArrayList<String> merchantQuestions = setQuestions();

        int queryIncrement = 1;
        sendMessage(merchantQuestions.get(0));

        do{
            try{
                message = (String) input.readObject();
                showMessage("\n" + message);
                if (queryIncrement < merchantQuestions.size()){
//                    System.out.println(queryIncrement + "<>" + studentQuestions.size());
                    sendMessage(merchantQuestions.get(queryIncrement));
                    saveInfo(queryIncrement, message);
                } else if (queryIncrement == merchantQuestions.size()){
                    saveInfo(queryIncrement, message);
                    sendMessage(toyinformation.toString() + "\nYour random number is: " + String.valueOf(new Random().nextInt(99999)));
                }
            } catch(ClassNotFoundException classNotFoundException){
                showMessage("\nObject not known");
            }
            queryIncrement++;
        } while(!message.equals("MERCHANT - END"));
        System.out.println(toyinformation.toString());
    }

    //question sent to the merchant
    private ArrayList<String> setQuestions(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Enter your Toy name...");
        arrayList.add("Enter your Toy code...");
        arrayList.add("Enter your Toy Description..");
        arrayList.add("Enter your Toy Price...");
        arrayList.add("Enter your Date of manufacture...");
        arrayList.add("Enter your Batch number...");
        arrayList.add("Enter your Company name...");
        arrayList.add("Enter your Street Address...");
        arrayList.add("Enter your Zip Code...");
        arrayList.add("Enter your Country...");
        arrayList.add("Enter your Thank you message with a unique code...");
        return arrayList;
    }

    //closing the streams and sockect
    private void closeSocket(){
        showMessage("\nClosing connection...\n");
        ableToType(false);
        try{
            output.close();
            input.close();
            connection.close();
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

    //send a message to the client
    private void sendMessage(String message){
        try{
            output.writeObject("SERVER - "+ message);
            output.flush();
            showMessage("\nSERVER - " + message);
        }catch(IOException ioException){
            chatWindow.append("\nERROR: MESSAGE NOT SENT!");
        }
    }

    //the following code updates the chat window
    private void showMessage (final String text){
        SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                        chatWindow.append(text);
                    }
                }
        );
    }

    //enable users to type from their end
    private void ableToType(final boolean trueOrFalse){
        SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                        userInput.setEditable(trueOrFalse);
                    }
                }
        );
    }

    private void saveInfo(int queryIncrement, String message) {
        String[] temp = message.split(" - ");
        message = temp[1];
//        System.out.println(queryIncrement + "<==>" + message);
        switch(queryIncrement){
            case 1:
                toyinformation.setToy_name(message);
                break;
            case 2:
                toyinformation.setToy_code(message);
                break;
            case 3:
                toyinformation.setToy_description(message);
                break;
            case 4:
                toyinformation.setToy_price(message);
                break;
            case 5:
               toyinformation.setToy_date_of_manufacture(message);
                break;
            case 6:
               toyinformation.setToy_batch_number(message);
                break;
            case 7:
               toyinformation.setCompany_name(message);
                break;
            case 8:
               toyinformation.setStreet_address(message);
                break;
            case 9:
               toyinformation.setZip_code(message);
                break;
            case 10:
               toyinformation.setCountry(message);
                break;    
            case 11:
               toyinformation.setMessage(message);
                break;
            default:
                break;

        }
    }

    
}