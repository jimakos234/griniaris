/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Griniaris_Client;

/**
 *
 * @author Jimis
 */
public class EndGameException extends Exception {

    /**
     * An exception that is thrown whenever a pioni ends the game 
     */
    public EndGameException() {
    }

    /**
     * Constructs an instance of <code>EndGame</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public EndGameException(String msg) {
        super(msg);
    }
}
