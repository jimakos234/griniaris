/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Griniaris_Client;

import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author Jimis
 */
public class Boxes extends JButton{
    
    private ArrayList<Pioni> alist;
    private int i=0,j=0;
    /**
     * Constructor for Boxes extending JButton
     */
    public Boxes() {
        super();
        alist=new ArrayList<>();
    }
    
    /**
     * Adding Pionia to each box
     * @param sneaky 
     */
    public void setElements(Pioni sneaky){
        alist.add(sneaky);
    }

    /**
     * 
     * @return the sum of pionia in every box
     */
    public int sumofPionia(){
        return alist.size();
    }
    
    /**
     * through this we give
     * the position of each box inside the box
     * so we can access it whenever we want
     * @param i
     * @param j 
     */
    public void setPos(int i,int j){
        this.i=i;
        this.j=j;
    }
    
    public int getPos_i(){
        return i;
    }
    
    public int getPos_j(){
        return j;
    }
    /**
     * remove specific element from the box
     * @param sneaky 
     */
    public void removeFromList(Pioni sneaky){
        alist.remove(sneaky);
    }
    /**
     * if we have 2 elements in the box(fragma) and we move one we need to get the first added
     * @return 
     */
    public Pioni getElement(){
        if(alist.isEmpty()==false){
            return alist.get(0);
        }else{
            return null;
        }
    }
}
