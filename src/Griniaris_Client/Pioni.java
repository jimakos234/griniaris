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
public class Pioni {
    
    private Player owner;
    private String data;
    private boolean isIngame=false;
    private int total=0;

    /**
     * we set in the constructor the owner of a pioni
     * @param owner 
     */
    public Pioni(Player owner) {
        this.owner=owner;
    }
    /**
     * function that returns the owner
     * @return 
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * function that returns if the pioni is set in the table 
     * @return 
     */
    public boolean isIsIngame() {
        return isIngame;
    }

    /**
     * function that sets the owner
     * @param owner 
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * function that enables or disables the pioni in the table
     * @param isIngame 
     */
    public void setIsIngame(boolean isIngame) {
        this.isIngame = isIngame;
    }

    /**
     * function that returns the data
     * @return 
     */
    public String getData() {
        return data;
    }

    /**
     * function that is used in order to set the data of a pioni
     * @param data 
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * function that return the counter of the moves the pioni has done
     * @return 
     */
    public int getTotal() {
        return total;
    }
    
    /**
     * function that sets the total counter of the moves a pioni has done
     * @param total 
     */
    public void setFinalTotal(int total) {
        this.total = total;
    }
    
}
