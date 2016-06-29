/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Griniaris_Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Jimis
 */
public class Client {
    private PrintWriter toServer;
    private Scanner fromServer;
    private Socket echoSocket;
    
    /**
     * Constructor for creation 
     */
    public Client() {
 
         try{ 
            echoSocket = new Socket("localhost", 7777);
            // Η δεύτερη παράμετρος, με τιμή true, υποχρεώνει τον PrintWriter να κάνει
            // flush μετά από κάθε εγγραφή (print, printf, println) ώστε τα δεδομένα
            // όντως να φεύγουν προς τον server
            toServer = new PrintWriter(echoSocket.getOutputStream(), true);
            fromServer = new Scanner(echoSocket.getInputStream()); 

            
        } catch (UnknownHostException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (NoSuchElementException ex) {
            System.out.println(ex);
        }
    }
    /**
     * function that returns an array of Strings
     * with the replies of other users
     * @return 
     */
    public String[] acceptOtherUserData(){
        String data[] = new String[4];
        String reply;
        int i = 0;
        
        reply = getMessageFromServer();
        
        if (reply.equalsIgnoreCase("rest_clients") == false) {
            sendMessageToServer("OK");
            return null;
        }
        
        do{
            reply = getMessageFromServer();            
            
            if (reply.equalsIgnoreCase("end_rest_clients") == false)
                data[i++] = reply;        
            
        }while (i < 4 && reply.equalsIgnoreCase("end_rest_clients") == false);
        
        sendMessageToServer("OK");
        
        return data;        
    }
    
    /**
     *  function that sends the player name and the color he choose to the other users
     * @param name
     * @param colorNumber 
     */
    public void sendMyData(String name, String colorNumber){
        if (getMessageFromServer().equalsIgnoreCase("your_data") == false)
            System.out.println("server did not send YOUR_DATA command!");
        sendMessageToServer(name + " " + colorNumber);
    }
    
    /**
     * 
     * @return 
     */
    public String acceptRestOfPlayerData(){
        String reply = "NO_MSG_YET", check;       
        
        check = getMessageFromServer();
        
        if (check.equalsIgnoreCase("game_starts_now") == true) {
            sendMessageToServer("OK");
            reply = check;
        }
        else if (check.equalsIgnoreCase("rest_clients") == true){
            reply = getMessageFromServer();            

            if (getMessageFromServer().equalsIgnoreCase("end_rest_clients") == false)
                System.out.println("Did not receive end_rest_clients message as expected from server");

            sendMessageToServer("OK");
        }
        return reply;              
    }
    
    /**
     * function checks whether the game is started or not
     * @param start 
     */
    public void startGameChoice(boolean start){
        
        String reply;
        
        
        reply = getMessageFromServer();
        
        if (reply.equalsIgnoreCase("start_game") == false) {
            System.out.println("NO start_game COMMAND RECEIVED");
            sendMessageToServer("OK");
            return;
        }
        
        if (start == true) sendMessageToServer("YES");
        else sendMessageToServer("NO");            
    }
    /**
     * function that returns the message the client got from server
     * @return 
     */
    public String getMessageFromServer(){
        String msg = fromServer.nextLine();
        System.out.println("got from server: " + msg);
       return msg;
    }
    
    /**
     * function that sends a message to the server
     * @param s 
     */
    public void sendMessageToServer(String s){
        System.out.println("sending to server: "+s );
        toServer.println(s);
    }
    
   // public MyFrame
    
    

}
