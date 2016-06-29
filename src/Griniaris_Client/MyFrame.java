/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Griniaris_Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
//

/**
 *
 * @author Jimis
 */
public class MyFrame extends JFrame {

    private Boxes arr[][],init;
    private JDialog dialog2, dialog3, smt;
    private JMenuItem item1, item2, item3, item4, item5,item6;
    private Player p;
    private JTextField t1;
    private JLabel l1,playername[];
    private EA_Dice dic;
    private Pioni[] pionia;
    private JButton play, dice,startgame;
    private JCheckBox lel1, lel2, lel3, lel4,lel5,cc1,cc2,cc3,cc4;
    private ButtonGroup buttonGroup;
    private boolean end = false;
    private int zaria_moment = 0,selection = -1, i1=0,i2=0;
    private Movement move[];
    private Color selected_color;
    private String info;
    private Client client;
    
    private int connectedPlayers; //nea metavliti

    public MyFrame() {
        client=new Client();
        
        //client.sendMessageToServer("Hello "+selection);
        //System.out.println("String from sr "+client.getMessageFromServer());
        init=new Boxes();
        setTitle("Γκρινιάρης");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new Dimension(600, 600));
        setSize(new Dimension(700, 700));
        JMenuBar bar = new JMenuBar();
        JMenu menu1 = new JMenu("Παιχνίδι");
        JMenu menu2 = new JMenu("Βοήθεια");
        item1 = new JMenuItem("Συμμετοχή σε παιχνίδι");
        item2 = new JMenuItem("Διακοπή Παιχνιδιού");
        item3 = new JMenuItem("Έξοδος");
        item4 = new JMenuItem("Βοήθεια");
        item5 = new JMenuItem("Σχετικά με τον Γκρινίαρη");
        item2.setEnabled(false);
        JPanel boxy = new JPanel();
        playername=new JLabel[4];
        for(int i=0;i<4;i++){
            playername[i]=new JLabel(" Enemy Player : ");
            playername[i].setBorder(BorderFactory.createEtchedBorder());
        }

        BoxLayout box = new BoxLayout(boxy, BoxLayout.PAGE_AXIS);
        boxy.setLayout(box);
        JPanel ziggzy = new JPanel(new GridLayout(15, 15));
        arr = new Boxes[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                arr[i][j] = new Boxes();
                arr[i][j].setEnabled(false);
                arr[i][j].setVisible(false);
                if ((i == 6 || i == 7 || i == 8 || j == 6 || j == 7 || j == 8) && (i != j)) {
                    if ((j == 8 && i == 6) || (j == 6 && i == 8)) {
                    } else {
                        arr[i][j].setEnabled(true);
                        arr[i][j].setVisible(true);
                        arr[i][j].setBackground(Color.WHITE);
                        arr[i][j].setOpaque(true);
                    }
                }
                if (i == 7 && (j > 0 && j < 7)) {
                    arr[i][j].setBackground(Color.GREEN);
                    arr[i][j].setOpaque(true);
                }
                if (i == 7 && (j > 7 && j < 14)) {
                    arr[i][j].setBackground(Color.BLUE);
                    arr[i][j].setOpaque(true);
                }
                if (j == 7 && (i > 7 && i < 14)) {
                    arr[i][j].setBackground(Color.YELLOW);
                    arr[i][j].setOpaque(true);
                }
                if (j == 7 && (i > 0 && i < 7)) {
                    arr[i][j].setBackground(Color.RED);
                    arr[i][j].setOpaque(true);
                }
                if (i == 6 && j == 1) {
                    arr[i][j].setBackground(Color.GREEN);
                    arr[i][j].setOpaque(true);
                }
                if (i == 1 && j == 8) {
                    arr[i][j].setBackground(Color.RED);
                    arr[i][j].setOpaque(true);
                }
                if (i == 13 && j == 6) {
                    arr[i][j].setBackground(Color.YELLOW);
                    arr[i][j].setOpaque(true);
                }
                if (i == 8 && j == 13) {
                    arr[i][j].setBackground(Color.BLUE);
                    arr[i][j].setOpaque(true);
                }
                if (i==6 && j == 4){
                    arr[i][j].setBackground(Color.ORANGE);
                    arr[i][j].setOpaque(true);
                }
                if (i==10 && j == 6){
                    arr[i][j].setBackground(Color.ORANGE);
                    arr[i][j].setOpaque(true);
                }
                if (i==4 && j == 8){
                    arr[i][j].setBackground(Color.ORANGE);
                    arr[i][j].setOpaque(true);
                }
                if (i==8 && j == 10){
                    arr[i][j].setBackground(Color.ORANGE);
                    arr[i][j].setOpaque(true);
                }
                arr[i][j].setPos(i, j);
                ziggzy.add(arr[i][j]);
            }
        }
        boxy.add(ziggzy);
        add(boxy, BorderLayout.CENTER);
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
        JPanel down = new JPanel(f1);
        dice = new JButton("Ρίξε το Ζάρι");
        play = new JButton("Παίξε");
        play.setEnabled(false);
        l1 = new JLabel("Δεν Έχετε Ρίξει :");
        dic = new EA_Dice();
        dice.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                zaria_moment = dic.getZari();
                l1.setText("Έφερες : " + zaria_moment);
                play.setEnabled(true);
                dice.setEnabled(false);
            }
        });
        move=new Movement[4];
        for(int i=0;i<4;i++){
            move[i]=new Movement();
        }
        play.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    smt = new JDialog(MyFrame.this);
                    smt.setTitle("Play");
                    smt.setSize(200, 200);
                    smt.setLayout(new FlowLayout(FlowLayout.CENTER));
                    smt.setVisible(true);
                    lel1 = new JCheckBox("Pioni 1");
                    lel2 = new JCheckBox("Pioni 2");
                    lel3 = new JCheckBox("Pioni 3");
                    lel4 = new JCheckBox("Pioni 4");
                    lel5 = new JCheckBox("Auto Play Random Button");
                    buttonGroup = new ButtonGroup();
                    buttonGroup.add(lel1);
                    buttonGroup.add(lel2);
                    buttonGroup.add(lel3);
                    buttonGroup.add(lel4);
                    buttonGroup.add(lel5);
                    smt.add(lel1);
                    smt.add(lel2);
                    smt.add(lel3);
                    smt.add(lel4);
                    smt.add(lel5);
                    JButton b1 = new JButton("Submit");
                    b1.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            end=false;
                            selection=-1;
                            if (lel1.isSelected() == true) {
                                selection = 0;
                            } else if (lel2.isSelected() == true) {
                                selection = 1;
                            } else if (lel3.isSelected() == true) {
                                selection = 2;
                            } else if (lel4.isSelected() == true) {
                                selection = 3;
                            }
                            else if (lel5.isSelected() == true) {
                                selection = new Random().nextInt(4);
                                JOptionPane.showMessageDialog(MyFrame.this,  "Pioni Randomly Selected: "+(selection),"Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                            if (selection == -1) {
                                JOptionPane.showMessageDialog(MyFrame.this,  "Pioni Not Selected","Error", JOptionPane.ERROR_MESSAGE);
                                smt.dispose();
                            } else {
                                 //client.sendMessageToServer("Hello "+selection);
                                //System.out.println("String from sr "+client.getMessageFromServer());
                                if (/*zaria_moment == 5 &&*/pionia[selection].isIsIngame() == false) {
                                    if(init.sumofPionia()==0){
                                        if (pionia[selection].isIsIngame() == false) {
                                            pionia[selection].setIsIngame(true);
                                            init.setElements(pionia[selection]);
                                            init.setText(p.getName()+"-p"+(selection+1));
                                            pionia[selection].setData(init.getText());
                                            move[selection].setPrev(init);
                                            move[selection].getPrev().setPos(init.getPos_i(), init.getPos_j());
                                            end = true;
                                        }
                                    }else{
                                        if (pionia[selection].isIsIngame() == false) {
                                            pionia[selection].setIsIngame(true);
                                            init.setElements(pionia[selection]);
                                            pionia[selection].setData(p.getName()+"-p"+(selection+1));
                                            move[selection].setPrev(init);
                                            move[selection].getPrev().setPos(init.getPos_i(), init.getPos_j());
                                            end = true;
                                        }
                                    }
                                } else { 
                                    if (pionia[selection].isIsIngame() == true) {
                                        try{
                                            //System.out.println("selected "+(selection+1));
                                            move[selection].setZarieto(zaria_moment);
                                            move[selection].setPrev(move[selection].getCurrent());
                                            move[selection].setCurrent(selected_color,pionia[selection]);
                                            arr[move[selection].getCurrent().getPos_i()][move[selection].getCurrent().getPos_j()].setText(arr[move[selection].getPrev().getPos_i()][move[selection].getPrev().getPos_j()].getText());
                                            move[selection].setPrev(move[selection].getPrev());
                                        }catch(EndGameException as){
                                            System.out.println(as.getMessage());
                                            arr[move[selection].getPrev().getPos_i()][move[selection].getPrev().getPos_j()].setText("");
                                            move[selection].setPrev(null);
                                        }finally{
                                            try{
                                                if(arr[move[selection].getPrev().getPos_i()][move[selection].getPrev().getPos_j()].sumofPionia()>0){
                                                    
                                                    arr[move[selection].getPrev().getPos_i()][move[selection].getPrev().getPos_j()].removeFromList(pionia[selection]);
                                                    arr[move[selection].getCurrent().getPos_i()][move[selection].getCurrent().getPos_j()].setElements(pionia[selection]);
                                                }else{
                                                    
                                                    arr[move[selection].getCurrent().getPos_i()][move[selection].getCurrent().getPos_j()].setElements(pionia[selection]);
                                                }
                                                if((move[selection].getPrev().getPos_i()!=move[selection].getCurrent().getPos_i())||(move[selection].getPrev().getPos_j()!=move[selection].getCurrent().getPos_j())){
                                                    if(arr[move[selection].getPrev().getPos_i()][move[selection].getPrev().getPos_j()].sumofPionia()>0){
                                                        arr[move[selection].getPrev().getPos_i()][move[selection].getPrev().getPos_j()].setText(arr[move[selection].getPrev().getPos_i()][move[selection].getPrev().getPos_j()].getElement().getData());
                                                    }else{
                                                        arr[move[selection].getPrev().getPos_i()][move[selection].getPrev().getPos_j()].setText("");
                                                    }
                                                }
                                            }catch(NullPointerException w){
                                            }
                                        }
                                    }
                                    end=true;
                                    String msg = p.getName() +  " from: (" + move[selection].getPrev().getPos_i() + ", " + move[selection].getPrev().getPos_j() + ") to: (" + 
                                            move[selection].getCurrent().getPos_i() + ", " + move[selection].getCurrent().getPos_j() + ")";
                                    client.sendMessageToServer(msg);
                                    playGame();
                                }
                                if (end == true) {
                                    smt.dispose();                                    
                                }
                                dice.setEnabled(true);
                                play.setEnabled(false);
                            }
                        } 
                    });
                    smt.add(b1);
            }
        });
        down.add(l1);
        down.add(dice);
        down.add(play);
        item1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                item2.setEnabled(true);
                item3.setEnabled(false);
                dialog3 = new JDialog(MyFrame.this);
                if (t1 == null) {
                    final String data[] = client.acceptOtherUserData();
                    info = "iparxoun kai alloi paixtes";
                    if (data != null){
                        connectedPlayers = data.length;  //prepei na thimamai posoi einai oi alloi paiktes
                        int i=0;
                        for (String s: data){ 
                            
                            playername[i].setText("Enemy Player "+s);
                            i++;
                        }
                    }else{ 
                        info = "Protos paixtis sto paixnidi!";
                    }
                    dialog3.setTitle("Νέος Παίχτης. " + info);
                    dialog3.setSize(400, 150);
                    dialog3.setLayout(new  BorderLayout());
                    JPanel boxy2 = new JPanel();
                    BoxLayout box2 = new BoxLayout(boxy2, BoxLayout.PAGE_AXIS);
                    boxy2.setLayout(box2);
                    JPanel o1=new JPanel(new FlowLayout());
                    o1.add(new JLabel("<html><style type=\"text/css\">\n"
                            + "	.lolimage{\n"
                            + "		text-align: center;\n"
                            + "	}\n"
                            + "</style><body><div class=lolimage>Όνομα Παίχτη: </div></html>"));
                    t1 = new JTextField(15);
                    o1.add(t1);
                    boxy2.add(o1);
                    cc1 = new JCheckBox("Red");
                    cc2 = new JCheckBox("Blue");
                    cc3 = new JCheckBox("Yellow");
                    cc4 = new JCheckBox("Green");
                    buttonGroup = new ButtonGroup();
                    buttonGroup.add(cc1);
                    buttonGroup.add(cc2);
                    buttonGroup.add(cc3);
                    buttonGroup.add(cc4);
                    cc3.setSelected(true);
                    JPanel o2=new JPanel(new FlowLayout());
                    o2.add(new JLabel("<html><style type=\"text/css\">\n"
                            + "	.lolimage{\n"
                            + "		text-align: center;\n"
                            + "	}\n"
                            + "</style><body><div class=lolimage>Διαλέχτε Χρώμα: </div></html>"));
                    o2.add(cc1);
                    o2.add(cc2);
                    o2.add(cc3);
                    o2.add(cc4);
                    boxy2.add(o2);
                    JButton b1 = new JButton("Submit");
                    b1.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            p = new Player(t1.getText());
                            pionia = new Pioni[4];
                            for (int i = 0; i < 4; i++) {
                                pionia[i] = new Pioni(p);
                                pionia[i].setIsIngame(false);
                            }
                            if(cc1.isSelected()==true){
                                selected_color=Color.RED;
                            }else if(cc2.isSelected()==true){
                                selected_color=Color.BLUE;
                            }else if(cc3.isSelected()==true){
                                selected_color=Color.YELLOW;
                            }else if(cc4.isSelected()==true){
                                selected_color=Color.GREEN;
                            }
                            if(selected_color==Color.YELLOW){
                               init=arr[13][6];
                               init.setPos(13, 6);
                           }else if(selected_color==Color.RED){
                               init=arr[1][8];
                               init.setPos(1, 8);
                           }else if(selected_color==Color.GREEN){
                               init=arr[6][1];
                               init.setPos(6, 1);
                           }else if(selected_color==Color.BLUE){
                               init=arr[8][13];
                               init.setPos(8, 13);
                           }
                            for(int i=0;i<4;i++){
                                move[i].addcurrent(init);
                            }
                            String colorNumber = "c";
                            if(cc1.isSelected()==true){
                                colorNumber = " Color: Red";
                            }else if(cc2.isSelected()==true){
                                 colorNumber = " Color: Blue";
                            }else if(cc3.isSelected()==true){
                                 colorNumber = " Color: Yellow";
                            }else if(cc4.isSelected()==true){
                                 colorNumber = " Color: Green";
                            }        
                            client.sendMyData(t1.getText(), colorNumber);
                            int answer =0;//HERE
                            if(!info.equals("Protos paixtis sto paixnidi!")){
                                answer= JOptionPane.showConfirmDialog(MyFrame.this, "Do you want to start the game\n with the current number of players?", "Start Game Now?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            
                            }
                            if (data == null || answer == JOptionPane.NO_OPTION){ 
                                 client.startGameChoice(false);
                                
                                //edo peftei se anastoli kai perimenei tous ipoloipous paixtes (meta apo afton)
                                
                                String  nextPlayerData[] = new String[4];
                                int i = 0,ccc=0;
                                String msg;
                                
                                do {
                                    msg = client.acceptRestOfPlayerData();
                                    if (msg.equalsIgnoreCase("game_starts_now") == false)
                                        nextPlayerData[i++] = msg;
                                }while (msg.equalsIgnoreCase("game_starts_now") == false); //bainoun kai alloi paiktes
                                connectedPlayers += i;
                                //edo gnorizeis olous toys paiktes apo to nextPlayerData[]
                                
                                for (String s: nextPlayerData){ 
                                    //System.out.println("another player: " + s);
                                    playername[ccc].setText("Enemy Player : "+s);
                                    ccc++;
                                }
                                
                                playGame();                                
                            }else if(data!=null&&answer == JOptionPane.YES_OPTION){
                                client.startGameChoice(true);
                                for(int y=0;y<4;y++){
                                    if(playername[y].getText()!=null||!"".equals(playername[y].getText())){
                                        
                                    }else{
                                        playername[y].setText("Enemy Player : "+p.getName()+colorNumber);
                                    }
                                }
                                playGame();
                                
                            }
                            
                            dialog3.dispose();
                        }
                    });
                    JPanel t=new JPanel(new FlowLayout(FlowLayout.CENTER));
                    t.add(b1);
                    dialog3.add(t,BorderLayout.SOUTH);
                    dialog3.add(boxy2,BorderLayout.NORTH);
                } else {                   
                    dialog3.setTitle("Υπάρχει Παίχτης");
                    dialog3.setSize(200, 75);
                    dialog3.setLayout(new FlowLayout());
                    JButton b1 = new JButton("Close");
                    b1.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog3.dispose();
                        }
                    });
                    dialog3.add(b1);
                }
                try {
                    dialog3.setVisible(true);
                } catch (NullPointerException a) {
                    System.out.println(a.getMessage());
                }
            }
        });
        item2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showConfirmDialog(MyFrame.this, "Are you sure \nyou want to end the game?", "Game Stoping", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                }
            }
        });
        item3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showConfirmDialog(MyFrame.this, "Are you sure \nyou want to close the window?", "Window closing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                   
                    System.exit(0);
                } else {
                }
            }
        });
        item4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (dialog2 == null) {
                    dialog2 = new JDialog(MyFrame.this);
                    dialog2.setTitle("Βοήθεια");
                    dialog2.setSize(400, 200);
                    dialog2.setLayout(new FlowLayout(FlowLayout.CENTER));
                    dialog2.add(new JLabel("<html><style type=\"text/css\">\n"
                            + "	.lolimage{\n"
                            + "		text-align: center;\n"
                            + "	}\n"
                            + "</style><body><div class=lolimage><br><br>Βοήθεια<br>Ρίχτε το ζάρι και μετακινήστε τα πιόνα αντίστοιχα<br>"
                            + "Μπορείτε να χτίσετε φράγματα με 2 ίδια πιόνα σε 1 σημείο<br></div></html>"));
                }
                dialog2.setVisible(true);
            }
        });
        item5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MyFrame.this, "Σταυρουλάκης Δημήτρης\n ΑΜ 2025201100080\n\n Βασίλειος Στάμος \n AM 2025200900072\n ", "Σχετικά", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu1.add(item1);
        menu1.add(item2);
        menu1.addSeparator();
        menu1.add(item3);
        menu2.add(item4);
        menu2.add(item5);
        bar.add(menu1);
        bar.add(menu2);
        setJMenuBar(bar);
        JPanel names=new JPanel(new FlowLayout(FlowLayout.CENTER));
        for(int i=0;i<4;i++){
            names.add(playername[i]);
        }

        add(names, BorderLayout.NORTH);
        add(down, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                if (item3.isEnabled()) {
                    int answer = JOptionPane.showConfirmDialog(MyFrame.this, "Are you sure \nyou want to close the window?", "Window closing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    } else {
                    }
                } else {
                    int answer = JOptionPane.showConfirmDialog(MyFrame.this, "Are you sure \nyou want to end the game?", "Game Stoping", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    } else {
                    }
                }
            }

        });

    }

    
    private void playGame(){
        int i=0,j=0,xx1=0,xx2=0;
        Boxes temp1=new Boxes();
        String command;
        while (true){
            i=0;
            command = client.getMessageFromServer();
            if (command.equalsIgnoreCase("others_move") == true){
                //arr[i1][i2].setText("");
                command = client.getMessageFromServer();
                JOptionPane.showMessageDialog(MyFrame.this,  "Έγινε Κίνηση\n "+command,"Info", JOptionPane.INFORMATION_MESSAGE);
                //System.out.println("Got move: " + command);
                // show move on board
                Pattern sp = Pattern.compile("-?\\d+");
                Matcher m = sp.matcher(command);
                while (m.find()) {
                  int tt=Integer.parseInt(m.group());
                  //System.out.println("Syntetagmenes :"+tt);
                  if(i==2){
                      i1=tt;
                  }else if(i==3){
                      i2=tt;
                  }else if( i==1){
                      xx2=tt;
                  }else if( i==0){
                      xx1=tt;
                  }
                  i++;
                }
                if(arr[i1][i2].sumofPionia()==0){
                    arr[xx1][xx2].setText("");
                    arr[i1][i2].setText(/*command.substring(0, j)*/"Enemy");
                }else{
                    arr[xx1][xx2].setText("");
                    arr[i1][i2].getElement().setIsIngame(false);
                    arr[i1][i2].removeFromList(arr[i1][i2].getElement());
                    arr[i1][i2].setText(/*command.substring(0, j)*/"Enemy");
                    JOptionPane.showMessageDialog(MyFrame.this,  "Σας φάγανε ένα Πιόνι","Ατυχία", JOptionPane.ERROR_MESSAGE);
                }
                temp1.setPos(i1,i2);
                // exoume parei tin kinisi allou paikti                
            }
            else if (command.equals("send_move")) break;
        } 

    }
}
