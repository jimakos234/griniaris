/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Griniaris_Client;

import java.util.Random;

/**
 *
 * @author Jimis
 */
public class EA_Dice {
    private int zari=6;

    /**
     * Constructor to create a zari 
     */
    public EA_Dice() {
    }

    /**
     * function that return a number number between 1-6
     * @return 
     */
    public int getZari() {
        return new Random().nextInt(zari)+1;
    }

    /**
     * * function that sets the dice to a  value
     * @param zari 
     */
    public void setZari(int zari) {
        this.zari = zari;
    }
    
    
}
