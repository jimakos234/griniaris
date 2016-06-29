/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Griniaris_Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Jimis
 */
public class Server {
    private static ServerSocket serverSocket;
    private static Socket clientSocket[]; //=new Socket[4];
    private static Scanner fromClient[]; //=new Scanner[4];
    private static PrintWriter toClient[]; //=new PrintWriter[4];

    /**
     * The main of the server
     * @param args 
     */    
    public static void main(String []args){
        int connections = 0, c, i, j;
        String reply;
        String names_and_colors[] = new String[4];
        clientSocket=new Socket[4];
        fromClient=new Scanner[4];
        toClient=new PrintWriter[4];
       
        try {
            serverSocket = new ServerSocket(7777, 4);
            System.out.println("Server is up!");
            
            clientSocket[0] = serverSocket.accept();
            System.out.println("New connection accepted!");

            fromClient[0] = new Scanner(clientSocket[0].getInputStream());            
            toClient[0] = new PrintWriter(clientSocket[0].getOutputStream(), true); 

            sendMessage(0, "end_rest_clients");
            getMessage(0); // OK

            sendMessage(0, "your_data");
            names_and_colors[0] = getMessage(0);
            
            sendMessage(0, "wait_other_players");
            getMessage(0); // ok
            c = 1;
            do{
                System.out.println("waiting for new connection...");
                clientSocket[c] = serverSocket.accept();
                System.out.println("New connection accepted!");
                fromClient[c] = new Scanner(clientSocket[c].getInputStream());            
                toClient[c] = new PrintWriter(clientSocket[c].getOutputStream(), true); 
                sendMessage(c, "rest_clients");
                for (i = 0 ; i < c ; i++){
                    sendMessage(c, names_and_colors[i]);                                   
                } 
                sendMessage(c, "end_rest_clients");
                getMessage(c); // OK
                
                sendMessage(c, "your_data");
                names_and_colors[c] = getMessage(c);
                // apostoli stoixeion neou paikti se olous tous proigoumenous
                for (i = 0 ; i < c ; i++){
                    sendMessage(i, "rest_clients");
                    sendMessage(i, names_and_colors[c]);
                    sendMessage(i, "end_rest_clients");
                    getMessage(i); // ok

                }              
                sendMessage(c, "start_game");
                reply = getMessage(c);
                c++;
            }while(c<4 && reply.equalsIgnoreCase("NO") == true);
            
            // teleftaios to kserei oti arxizei topaixnidi, oxi omos opoloipoi
            for (i = 0 ; i < c-1 ; i++) sendMessage(i, "game_starts_now");
            
            System.out.println("Start game having " + c + " clients");
            
            do{
                for (i = 0 ; i < c ; i++){
                    sendMessage(i, "send_move");
                    reply = getMessage(i);
                    for (j = 0 ; j < c ; j++){
                        if (i != j) {
                            sendMessage(j, "others_move");
                            sendMessage(j, reply);
                        }
                    }
                }
            } while (reply.equals("END") == false);
            //closing the socket
            serverSocket.close();
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
        
    }
    
    /**
     * function that sends a message to the x client from the server 
     * @param client
     * @param msg 
     */
    private static void sendMessage( int client, String msg){
        System.out.println("sending to " + client + ": " + msg);
        toClient[client].println(msg);
    }
    
    /**
     * function that sends the message of the x client to the server
     * @param client
     * @return 
     */
    private static String getMessage(int client){
        String msg = fromClient[client].nextLine();
        System.out.println("received from client " + client + ": " + msg);
        return msg;
    }
}
