/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Griniaris_Client;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Jimis
 */
public class Movement {
    
    private Boxes prev=null,current=null,temp=null;
    private int zarieto=0;
    
    public Movement(){
        
    }
    /**
     * Here we set the zarieto as the value we got from the dice
     * @param zarieto 
     */
    public void setZarieto(int zarieto) {
        this.zarieto = zarieto;
    }
    
    /**
     * this is the function that sets the new current thesis of a pioni
     * @param lel 
     */
    public void addcurrent(Boxes lel){
        current=lel;
    }

    /**
     * function that returns the previous position of a pioni
     * @return 
     */
    public Boxes getPrev() {
        return prev;
    }

    /**
     * function that returns the current position of a pioni
     * @return 
     */
    public Boxes getCurrent() {
        return current;
    }
    /**
     * function that sets the previous value in the movement
     * @param prev 
     */
    public void setPrev(Boxes prev) {
        this.prev = prev;
       
    }

    /**
     * function that sets the new current thesis
     * the color is need if we need to initiate the first move of a pioni
     * @param color
     * @param item
     * @throws EndGameException 
     */
    public void setCurrent(Color color,Pioni item) throws EndGameException {
        Move(color,item);
    }
    
    /**
     * private function that processes the movement of a pioni
     * @param color
     * @param item
     * @throws EndGameException 
     */
    private  void Move(Color color,Pioni  item) throws EndGameException{
        int counter=item.getTotal();
        boolean near_end = false, setcounter = false;
        if (prev != null) {
            int x_i = prev.getPos_i();
            int y_j = prev.getPos_j();

            for (int i = 0; i < zarieto; i++) {
                if (setcounter == false) {
                    counter += zarieto;
                    setcounter = true;
                }

                if (counter < 56) {
                    /*
                    */
                    
                    
                    
                    if (color == Color.YELLOW) {
                        if (x_i == 14 && y_j == 7) {
                            x_i--;
                            continue;
                        }
                        if (x_i == 13 && y_j == 7) {
                            x_i--;
                            continue;
                        }
                        if (x_i == 12 && y_j == 7) {
                            x_i--;
                            continue;
                        }
                        if (x_i == 11 && y_j == 7) {
                            x_i--;
                            continue;
                        }
                        if (x_i == 10 && y_j == 7) {
                            x_i--;
                            continue;
                        }
                        if (x_i == 9 && y_j == 7) {
                            x_i--;
                            continue;
                        }
                    }
                     if (color == Color.GREEN) {
                        if(x_i == 7 && (y_j == 0 || y_j == 1 ||y_j == 2||y_j == 3||y_j == 4||y_j == 5)){
                            y_j++;
                            continue;
                        }
                    }
                    if (color == Color.RED){
                        if((x_i == 0|| x_i == 1|| x_i == 2|| x_i == 3|| x_i == 4 || x_i == 5) && y_j == 7){
                            x_i++;
                            continue;
                        }
                    }
                     if (color == Color.BLUE){
                        if((y_j == 9|| y_j == 10|| y_j == 11|| y_j == 12|| y_j == 13 || y_j == 14) && x_i == 7){
                            y_j--;
                            continue;
                        }
                    }
                    if (((x_i < 14 && x_i > 9) || (x_i > 0 && x_i < 6)) && y_j == 6) {
                        x_i -= 1;
                        continue;
                    }
                    if (x_i == 9 && y_j == 6) {
                        y_j--;
                        x_i--;
                        continue;
                    }
                    if (x_i == 8 && (y_j > 0 && y_j < 6)) {
                        y_j--;
                        continue;
                    }
                    if ((x_i == 8 || x_i == 7) && y_j == 0) {
                        x_i--;
                        continue;
                    }
                    if (x_i == 6 && ((y_j >= 0 && y_j < 5) || (y_j < 14 && y_j > 9))) {
                        y_j++;
                        continue;
                    }
                    if (x_i == 6 && y_j == 5) {
                        x_i--;
                        y_j++;
                        continue;
                    }
                    if (x_i == 0 && (y_j == 6 || y_j == 7)) {

                        y_j++;
                        continue;
                    }
                    if (x_i == 0 && y_j == 8) {
                        x_i++;
                        continue;
                    }
                    if ((x_i > 0 && x_i < 5) && y_j == 8) {
                        x_i++;
                        continue;
                    }
                    if (x_i == 5 && y_j == 8) {
                        x_i++;
                        y_j++;
                        continue;
                    }
                    if (x_i == 6 && (y_j > 8 && y_j < 14)) {
                        y_j++;

                        continue;
                    }
                    if (y_j == 14 && (x_i == 6 || x_i == 7)) {
                        x_i++;
                        continue;
                    }
                    if (y_j == 14 && x_i == 8) {
                        y_j--;
                        continue;
                    }
                    if ((y_j < 14 && y_j > 9) && x_i == 8) {
                        y_j--;
                        continue;
                    }
                    if (y_j == 9 && x_i == 8) {
                        y_j--;
                        x_i++;
                        continue;
                    }
                    if (y_j == 8 && (x_i > 8 && x_i < 14)) {

                        x_i++;
                        continue;
                    }
                    if ((y_j == 8 || y_j == 7) && x_i == 14) {
                        y_j--;
                        continue;
                    }
                    if (y_j == 6 && x_i == 14) {
                        x_i--;
                        continue;
                    }
                } else if (counter > 56) {
                    //System.out.println("megalytero 56");
                    counter = counter - zarieto;
                    //item.setTotal(counter);
                    break;
                } else if (counter == 56) {
                    //item.setTotal(counter);
                    throw new EndGameException("Pioni Termastise");
                }
            }
            if (color != Color.GREEN && x_i==10 && y_j==6){
                x_i=6;
                y_j=4;
                counter+=13;
            }else  if(color != Color.RED && x_i==6 && y_j==4){
                x_i=4;
                y_j=8;
                counter+=13;
            }else  if(color != Color.BLUE && x_i==4 && y_j==8){
                x_i=8;
                y_j=10;
                counter+=13;
            }else  if(color != Color.YELLOW && x_i==8 && y_j==10){
                x_i=10;
                y_j=6;
                counter+=13;
            }
             
            item.setFinalTotal(counter);
            //System.out.println("xi "+x_i+" yi "+y_j+ " counter "+counter);
            temp=new Boxes();
            temp.setPos(x_i,y_j);
            current=temp;
        }
       
    }
}
