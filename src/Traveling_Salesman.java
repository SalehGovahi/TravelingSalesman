import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Traveling_Salesman{
    public static void main(String[] args) throws IOException {
        final int[] xAdjustment = {0};
        final int[] yAdjustment = {0};

        Border green = BorderFactory.createLineBorder(Color.green);

        JFrame Scoreboard = new JFrame("Score Board");
        Player player1 = new Player();
        Player player2 = new Player();
        Information info = new Information();
        Scoreboard.setSize(600,600);
        Scoreboard.setLocationRelativeTo(null);
        Scoreboard.setVisible(false);



        JFrame PLaying_Game = new JFrame();
        int size = 10;
        Scanner scanner = new Scanner(System.in);
        size = scanner.nextInt();
        int sizecopy = size;
        Dimension boardSize = new Dimension(1100,750);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(boardSize);
        layeredPane.setBackground(new Color(16,5,47));
        layeredPane.setForeground(new Color(16,5,47));
        //Add a chess board to the Layered Pane
        JPanel Board = new JPanel();
        Board.setBackground(new Color(16,5,47));
        layeredPane.add(Board, JLayeredPane.DEFAULT_LAYER);
        Board.setLayout( new GridLayout(size, size) );
        Board.setPreferredSize( boardSize );
        if (size>8){
            Board.setBounds(boardSize.width-(size*size*7)-20, (boardSize.height-size*size*7)/2, size*size*7, size*size*7);
        }
        else{
            Board.setBounds(boardSize.width-(size*size*10)-20, (boardSize.height-size*size*10)/2, size*size*10, size*size*10);
        }

        Gamelogic logic1 = new Gamelogic();
        int logicBoard[][] = logic1.logicCreator(size);

        JPanel arr[][] = new JPanel[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0 ; j<size ; j++){
                arr[i][j] = new JPanel( new BorderLayout() );
                Board.add(arr[i][j]);
                int row = (i / size) % 2;
                Border blackline = BorderFactory.createLineBorder(Color.black);
                arr[i][j].setBorder(blackline);
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0 ; j<size ; j++){
                if (logicBoard[i][j]==1){
                    arr[i][j].add(new JLabel(new ImageIcon("D:\\dd\\Assets\\castle.png")));
                }
                if (logicBoard[i][j]==2){
                    //arr[i][j].setBackground(Color.green);
                }
                if (logicBoard[i][j]==3){
                    arr[i][j].add(new JLabel(new ImageIcon("D:\\dd\\Assets\\wall.png")));
                }
                if (logicBoard[i][j]==4){
                    arr[i][j].add(new JLabel(new ImageIcon("D:\\dd\\Assets\\market.png")));
                }
                if (logicBoard[i][j]==5){
                    //arr[i][j].setBackground(Color.blue);
                }
                if (logicBoard[i][j]==6){
                    //arr[i][j].setBackground(Color.red);
                }
            }
        }

        JLabel PlayerName = new JLabel("Player 1" , JLabel.CENTER);
        PlayerName.setBounds(90 , 50 , 200,100);
        PlayerName.setFont(new Font("Arial", Font.BOLD, 30));
        PlayerName.setForeground(Color.orange);

        Border blackline = BorderFactory.createLineBorder(Color.white);
        JLabel DiceNumber = new JLabel("" , JLabel.CENTER);
        DiceNumber.setOpaque(true);
        DiceNumber.setBounds(200,160,100,70);
        DiceNumber.setBackground(Color.white);
        DiceNumber.setForeground(Color.black);
        DiceNumber.setBorder(blackline);
        DiceNumber.setFont(new Font("Arial", Font.PLAIN, 30));

        JButton Dice = new JButton("Roll");
        Dice.setBounds(70,160,100,70);
        Dice.setBackground(Color.black);
        Dice.setForeground(Color.white);
        Dice.setBorder(blackline);
        Dice.setFocusPainted(false);
        Dice.setFont(new Font("Arial", Font.PLAIN, 30));
        Dice.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                Dice.setBackground(Color.white);
                Dice.setForeground(Color.black);
                Dice.setBorder(BorderFactory.createLineBorder(Color.black));
            }
            public void mouseExited(MouseEvent evt) {
                Dice.setBackground(Color.black);
                Dice.setForeground(Color.white);
                Dice.setBorder(blackline);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Random rand = new Random();
                DiceNumber.setText(""+(rand.nextInt(6)+1));
            }
        });



        JButton ShowScore = new JButton("Score Board");
        ShowScore.setBounds(120,300,140,50);
        ShowScore.setBackground(Color.black);
        ShowScore.setForeground(Color.white);
        ShowScore.setBorder(blackline);
        ShowScore.setFocusPainted(false);
        ShowScore.setFont(new Font("Arial", Font.PLAIN, 25));
        ShowScore.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                ShowScore.setBackground(Color.white);
                ShowScore.setForeground(Color.black);
                ShowScore.setBorder(BorderFactory.createLineBorder(Color.black));
            }
            public void mouseExited(MouseEvent evt) {
                ShowScore.setBackground(Color.black);
                ShowScore.setForeground(Color.white);
                ShowScore.setBorder(blackline);
            }
        });
        ShowScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data[][]={ {"Player1",String.valueOf(player1.power),String.valueOf(player1.money),String.valueOf(player1.treasure_founded)},
                        {"Player2",String.valueOf(player2.power),String.valueOf(player2.money),String.valueOf(player1.treasure_founded)}};
                String column[]={"Player","Power","Money" , "Founded treasure"};
                JTable jt=new JTable(data,column);
                jt.setBounds(0,0,600,300);
                PLaying_Game.setVisible(false);
                JTextField turned = new JTextField("Turn:");
                JTextField ShowPlayer = new JTextField(info.is_turn,JTextField.RIGHT);
                JTextField tf_treasureFounded =new JTextField(info.getFounded_treasure()+" Treasure Founded");
                tf_treasureFounded.setEditable(false);
                tf_treasureFounded.setBorder(null);
                tf_treasureFounded.setFont(new Font("Arial", Font.BOLD, 30));
                turned.setEditable(false);
                turned.setBorder(null);
                turned.setFont(new Font("Arial", Font.BOLD, 30));
                ShowPlayer.setBorder(null);
                ShowPlayer.setEditable(false);
                ShowPlayer.setFont(new Font("Arial", Font.BOLD, 30));
                tf_treasureFounded.setBounds(150,250,300,80);
                turned.setBounds(200,190,100,80);
                ShowPlayer.setBounds(300,190,120,80);
                Scoreboard.add(tf_treasureFounded);
                Scoreboard.add(turned);
                Scoreboard.add(ShowPlayer);
                Scoreboard.add(jt);
                JScrollPane sp=new JScrollPane(jt);
                Scoreboard.add(sp);
                Scoreboard.getContentPane().setForeground(new Color(16,5,47));
                Scoreboard.getContentPane().setBackground(new Color(16,5,47));
                Scoreboard.setBackground(new Color(16,5,47));
                Scoreboard.setForeground(new Color(16,5,47));
                Scoreboard.setVisible(true);
            }
        });

        info.setFounded_treasure(6);

        layeredPane.add(ShowScore);
        layeredPane.add(PlayerName);
        layeredPane.add(DiceNumber);
        layeredPane.add(Dice);
        layeredPane.setBackground(new Color(16,5,47));
        layeredPane.setForeground(new Color(16,5,47));
        PLaying_Game.getContentPane().setForeground(new Color(16,5,47));
        PLaying_Game.getContentPane().setBackground(new Color(16,5,47));
        PLaying_Game.add(layeredPane);
        PLaying_Game.pack();



        /***********************************************************************************************/


        final int[] xAdjustment2 = {0};
        final int[] yAdjustment2 = {0};
        JFrame PLaying_Game2 = new JFrame();
        JLayeredPane layeredPane2 = new JLayeredPane();
        layeredPane2.setPreferredSize(boardSize);
        layeredPane2.setBackground(new Color(16,5,47));
        layeredPane2.setForeground(new Color(16,5,47));
        //Add a chess board to the Layered Pane
        JPanel Board2 = new JPanel();
        Board2.setBackground(new Color(16,5,47));
        layeredPane2.add(Board2, JLayeredPane.DEFAULT_LAYER);
        Board2.setLayout( new GridLayout(size, size) );
        Board2.setPreferredSize( boardSize );
        if (size>8){
            Board2.setBounds(boardSize.width-(size*size*7)-20, (boardSize.height-size*size*7)/2, size*size*7, size*size*7);
        }
        else{
            Board2.setBounds(boardSize.width-(size*size*10)-20, (boardSize.height-size*size*10)/2, size*size*10, size*size*10);
        }

        Gamelogic logic2 = new Gamelogic();
        int logicBoard2[][] = logic2.logicCreator(size);

        JPanel arr2[][] = new JPanel[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0 ; j<size ; j++){
                arr2[i][j] = new JPanel( new BorderLayout() );
                Board2.add(arr2[i][j]);
                int row = (i / size) % 2;
                Border MM=BorderFactory.createLineBorder(Color.black);
                arr2[i][j].setBorder(MM);
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0 ; j<size ; j++){
                if (logicBoard2[i][j]==1){
                    arr2[i][j].add(new JLabel(new ImageIcon("D:\\dd\\Assets\\castle.png")));
                }
                if (logicBoard2[i][j]==2){
                    //arr[i][j].setBackground(Color.green);
                }
                if (logicBoard2[i][j]==3){
                    arr2[i][j].add(new JLabel(new ImageIcon("D:\\dd\\Assets\\wall.png")));
                }
                if (logicBoard2[i][j]==4){
                    arr2[i][j].add(new JLabel(new ImageIcon("D:\\dd\\Assets\\market.png")));
                }
                if (logicBoard2[i][j]==5){
                    //arr[i][j].setBackground(Color.blue);
                }
                if (logicBoard2[i][j]==6){
                    //arr[i][j].setBackground(Color.red);
                }
            }
        }

        JLabel PlayerName2 = new JLabel("Player 2" , JLabel.CENTER);
        PlayerName2.setBounds(90 , 50 , 200,100);
        PlayerName2.setFont(new Font("Arial",Font.BOLD, 30));
        PlayerName2.setForeground(Color.orange);

        Border blackline2 = BorderFactory.createLineBorder(Color.white);
        JLabel DiceNumber2 = new JLabel("" , JLabel.CENTER);
        DiceNumber2.setOpaque(true);
        DiceNumber2.setBounds(200,160,100,70);
        DiceNumber2.setBackground(Color.white);
        DiceNumber2.setForeground(Color.black);
        DiceNumber2.setBorder(blackline2);
        DiceNumber2.setFont(new Font("Arial",Font.PLAIN, 30));

        JButton Dice2 = new JButton("Roll");
        Dice2.setBounds(70,160,100,70);
        Dice2.setBackground(Color.black);
        Dice2.setForeground(Color.white);
        Dice2.setBorder(blackline);
        Dice2.setFocusPainted(false);
        Dice2.setFont(new Font("Arial",Font.PLAIN, 30));
        Dice2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                Dice2.setBackground(Color.white);
                Dice2.setForeground(Color.black);
                Dice2.setBorder(BorderFactory.createLineBorder(Color.black));
            }
            public void mouseExited(MouseEvent evt) {
                Dice2.setBackground(Color.black);
                Dice2.setForeground(Color.white);
                Dice2.setBorder(blackline);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Random rand = new Random();
                DiceNumber2.setText(""+(rand.nextInt(6)+1));
            }
        });

        final int[] ll2 = {0};
        int finalSize2 = size;
        Dice2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent event) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        int dd= Integer.parseInt(DiceNumber2.getText());
                        int counter = ll2[0];
                        while (counter != dd+1) {
                            if (counter==dd){
                                JOptionPane.showMessageDialog(layeredPane2, "Moving is more than dice number",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                ll2[0] = 0;
                                for (int i =0 ; i<sizecopy ;i++){
                                    for (int j = 0; j<sizecopy ; j++){
                                        if (logicBoard2[i][j]==99){
                                            arr2[i][j].setBackground(Color.white);
                                            logicBoard2[i][j] = 0;
                                        }
                                    }
                                }
                                PLaying_Game2.setVisible(false);
                                PLaying_Game.setVisible(true);
                                break;
                            }
                            else{
                                if (xAdjustment2[0] == 0) {
                                    JOptionPane.showMessageDialog(layeredPane2, "Out of board size",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    break;
                                } else {
                                    if (logicBoard2[xAdjustment2[0]-1][yAdjustment2[0]] == 3){
                                        JOptionPane.showMessageDialog(layeredPane2, "Here is a wall",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                        counter -=1;
                                    }
                                    else {
                                        arr2[xAdjustment2[0] - 1][yAdjustment2[0]].setBackground(Color.gray);
                                        arr2[xAdjustment2[0]][yAdjustment2[0]].setBackground(Color.red);
                                        logicBoard2[xAdjustment2[0]][yAdjustment2[0]]=99;
                                        xAdjustment2[0] -= 1;
                                        counter += 1;
                                        ll2[0] = counter;
                                        if (logicBoard2[xAdjustment2[0] - 1][yAdjustment2[0]]==5){
                                            Random rand = new Random();
                                            int money = rand.nextInt(10)+1;
                                            money *= 1000;
                                            JOptionPane.showMessageDialog(layeredPane2, "You got"+ " "+ String.valueOf(money)+" money",
                                                    "congratulations", JOptionPane.PLAIN_MESSAGE);
                                            player2.money +=money;
                                            logicBoard2[xAdjustment2[0] - 1][yAdjustment2[0]]=0;
                                        }
                                        if (logicBoard2[xAdjustment2[0] - 1][yAdjustment2[0]]==6){
                                            Random rand = new Random();
                                            int trap = rand.nextInt(10)+1;
                                            trap *= 100;
                                            JOptionPane.showMessageDialog(layeredPane2, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                    "Oh no", JOptionPane.ERROR_MESSAGE);
                                            player2.money -=trap;
                                            logicBoard2[xAdjustment2[0] - 1][yAdjustment2[0]]=0;
                                        }
                                        if (logicBoard2[xAdjustment2[0] - 1][yAdjustment2[0]]==99){
                                            arr2[xAdjustment2[0]][yAdjustment2[0]].setBackground(Color.red);
                                            JOptionPane.showMessageDialog(layeredPane2, "You can't return",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            counter -=1;
                                            xAdjustment2[0] += 1;
                                        }
                                        ll2[0] = counter;
                                    }
                                }
                            }
                            break;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        dd= Integer.parseInt(DiceNumber2.getText());
                        counter = ll2[0];
                        while (counter != dd+1) {
                            if (counter==dd){
                                JOptionPane.showMessageDialog(layeredPane2, "Moving is more than dice number",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                ll2[0] = 0;
                                for (int i =0 ; i<sizecopy ;i++){
                                    for (int j = 0; j<sizecopy ; j++){
                                        if (logicBoard2[i][j]==99){
                                            arr2[i][j].setBackground(Color.white);
                                            logicBoard2[i][j] = 0;
                                        }
                                    }
                                }
                                PLaying_Game2.setVisible(false);
                                PLaying_Game.setVisible(true);
                                break;
                            }
                            else{
                                if (xAdjustment2[0] == finalSize2 - 1) {
                                    JOptionPane.showMessageDialog(layeredPane2, "Out of board size",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    break;
                                } else {
                                    if (logicBoard2[xAdjustment2[0]+1][yAdjustment2[0]] == 3){
                                        JOptionPane.showMessageDialog(layeredPane2, "Here is a wall",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                        counter -=1;
                                    }
                                    else {
                                        arr2[xAdjustment2[0] + 1][yAdjustment2[0]].setBackground(Color.gray);
                                        arr2[xAdjustment2[0]][yAdjustment2[0]].setBackground(Color.red);
                                        logicBoard2[xAdjustment2[0]][yAdjustment2[0]]=99;
                                        xAdjustment2[0] += 1;
                                        counter += 1;
                                        if (logicBoard2[xAdjustment2[0] + 1][yAdjustment2[0]]==5){
                                            Random rand = new Random();
                                            int money = rand.nextInt(10)+1;
                                            money *= 1000;
                                            JOptionPane.showMessageDialog(layeredPane2, "You got"+ " "+ String.valueOf(money)+" money",
                                                    "congratulations", JOptionPane.PLAIN_MESSAGE);
                                            player2.money +=money;
                                            logicBoard2[xAdjustment2[0] + 1][yAdjustment2[0]]=0;
                                        }
                                        if (logicBoard2[xAdjustment2[0] + 1][yAdjustment2[0]]==6){
                                            Random rand = new Random();
                                            int trap = rand.nextInt(10)+1;
                                            trap *= 100;
                                            JOptionPane.showMessageDialog(layeredPane2, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                    "Oh no", JOptionPane.ERROR_MESSAGE);
                                            player2.money -=trap;
                                            logicBoard2[xAdjustment2[0] + 1][yAdjustment2[0]]=0;
                                        }
                                        if (logicBoard2[xAdjustment2[0] + 1][yAdjustment2[0]]==99){
                                            arr2[xAdjustment2[0] + 1][yAdjustment2[0]].setBackground(Color.red);
                                            JOptionPane.showMessageDialog(layeredPane2, "You can't return",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            counter -=1;
                                            xAdjustment2[0] -= 1;
                                        }
                                        ll2[0] = counter;
                                    }
                                }
                            }
                            break;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        dd = Integer.parseInt(DiceNumber2.getText());
                        counter = ll2[0];
                        while (counter != dd+1) {
                            if (counter==dd){
                                JOptionPane.showMessageDialog(layeredPane2, "Moving is more than dice number",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                ll2[0] = 0;
                                for (int i =0 ; i<sizecopy ;i++){
                                    for (int j = 0; j<sizecopy ; j++){
                                        if (logicBoard2[i][j]==99){
                                            arr2[i][j].setBackground(Color.white);
                                            logicBoard2[i][j] = 0;
                                        }
                                    }
                                }
                                PLaying_Game2.setVisible(false);
                                PLaying_Game.setVisible(true);
                                break;
                            }
                            else{
                                if (yAdjustment2[0] == finalSize2 - 1) {
                                    JOptionPane.showMessageDialog(layeredPane2, "Out of board size",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    break;
                                } else {
                                    if (logicBoard2[xAdjustment2[0]][yAdjustment2[0]+1] == 3){
                                        JOptionPane.showMessageDialog(layeredPane2, "Here is a wall",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                        counter -=1;
                                    }
                                    else {
                                        arr2[xAdjustment2[0]][yAdjustment2[0] +1].setBackground(Color.gray);
                                        arr2[xAdjustment2[0]][yAdjustment2[0]].setBackground(Color.red);
                                        logicBoard2[xAdjustment2[0]][yAdjustment2[0]]=99;
                                        yAdjustment2[0] += 1;
                                        counter += 1;
                                        ll2[0] = counter;
                                        if (logicBoard2[xAdjustment2[0]][yAdjustment2[0] +1]==5){
                                            Random rand = new Random();
                                            int money = rand.nextInt(10)+1;
                                            money *= 1000;
                                            JOptionPane.showMessageDialog(layeredPane2, "You got"+ " "+ String.valueOf(money)+" money",
                                                    "Congratulations", JOptionPane.PLAIN_MESSAGE);
                                            player2.money +=money;
                                            logicBoard2[xAdjustment2[0]][yAdjustment2[0] +1]=0;
                                        }
                                        if (logicBoard2[xAdjustment2[0]][yAdjustment2[0] +1]==5){
                                            Random rand = new Random();
                                            int trap = rand.nextInt(10)+1;
                                            trap *= 100;
                                            JOptionPane.showMessageDialog(layeredPane2, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                    "Oh no", JOptionPane.ERROR_MESSAGE);
                                            player2.money -=trap;
                                            logicBoard2[xAdjustment2[0]][yAdjustment2[0] +1]=0;
                                        }
                                        if (logicBoard2[xAdjustment2[0]][yAdjustment2[0] +1]==99){
                                            arr2[xAdjustment2[0]][yAdjustment2[0]].setBackground(Color.red);
                                            JOptionPane.showMessageDialog(layeredPane2, "You can't return",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            counter -=1;
                                            yAdjustment2[0] -= 1;
                                        }
                                        ll2[0] = counter;
                                    }
                                }
                            }
                            break;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        dd= Integer.parseInt(DiceNumber2.getText());
                        counter = ll2[0];
                        while (counter != dd+1) {
                            if (counter==dd){
                                JOptionPane.showMessageDialog(layeredPane2, "Moving is more than dice number",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                ll2[0] = 0;
                                for (int i =0 ; i<sizecopy ;i++){
                                    for (int j = 0; j<sizecopy ; j++){
                                        if (logicBoard2[i][j]==99){
                                            arr2[i][j].setBackground(Color.white);
                                            logicBoard2[i][j] = 0;
                                        }
                                    }
                                }
                                PLaying_Game2.setVisible(false);
                                PLaying_Game.setVisible(true);
                                break;
                            }
                            else{
                                if (yAdjustment2[0] == 0) {
                                    JOptionPane.showMessageDialog(layeredPane2, "Out of board size",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    break;
                                } else {
                                    if (logicBoard2[xAdjustment2[0]][yAdjustment2[0]-1] == 3){
                                        JOptionPane.showMessageDialog(layeredPane2, "Here is a wall",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                        counter -=1;
                                    }
                                    else {
                                        arr2[xAdjustment2[0]][yAdjustment2[0] -1].setBackground(Color.gray);
                                        arr2[xAdjustment2[0]][yAdjustment2[0]].setBackground(Color.red);
                                        logicBoard2[xAdjustment2[0]][yAdjustment2[0]]=99;
                                        yAdjustment2[0] -= 1;
                                        counter += 1;
                                        ll2[0] = counter;
                                        if (logicBoard2[xAdjustment2[0]][yAdjustment2[0] -1]==5){
                                            Random rand = new Random();
                                            int money = rand.nextInt(10)+1;
                                            money *= 1000;
                                            JOptionPane.showMessageDialog(layeredPane2, "You got"+ " "+ String.valueOf(money)+" money",
                                                    "congratulations", JOptionPane.PLAIN_MESSAGE);
                                            player2.money +=money;
                                            logicBoard2[xAdjustment2[0]][yAdjustment2[0] -1]=0;
                                        }
                                        if (logicBoard2[xAdjustment2[0]][yAdjustment2[0] -1]==5){
                                            Random rand = new Random();
                                            int trap = rand.nextInt(10)+1;
                                            trap *= 100;
                                            JOptionPane.showMessageDialog(layeredPane2, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                    "Oh no", JOptionPane.ERROR_MESSAGE);
                                            player2.money -=trap;
                                            logicBoard2[xAdjustment2[0]][yAdjustment2[0] -1]=0;
                                        }
                                        if (logicBoard2[xAdjustment2[0]][yAdjustment2[0] - 1]==99){
                                            arr2[xAdjustment2[0]][yAdjustment2[0]].setBackground(Color.red);
                                            JOptionPane.showMessageDialog(layeredPane2, "You can't return",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            counter -=1;
                                            yAdjustment2[0] += 1;
                                        }
                                        ll2[0] = counter;
                                    }
                                }
                            }
                            break;
                        }

                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        arr2[0][0].setBackground(Color.gray);

        JButton ShowScore2 = new JButton("Score Board");
        ShowScore2.setBounds(120,300,140,50);
        ShowScore2.setBackground(Color.black);
        ShowScore2.setForeground(Color.white);
        ShowScore2.setBorder(blackline);
        ShowScore2.setFocusPainted(false);
        ShowScore2.setFont(new Font("Arial",Font.PLAIN, 25));
        ShowScore2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                ShowScore2.setBackground(Color.white);
                ShowScore2.setForeground(Color.black);
                ShowScore2.setBorder(BorderFactory.createLineBorder(Color.black));
            }
            public void mouseExited(MouseEvent evt) {
                ShowScore2.setBackground(Color.black);
                ShowScore2.setForeground(Color.white);
                ShowScore2.setBorder(blackline);
            }
        });
        ShowScore2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PLaying_Game2.setVisible(false);
                String data[][]={ {"Player1",String.valueOf(player1.power),String.valueOf(player1.getMoney()),String.valueOf(player1.treasure_founded)},
                        {"Player2",String.valueOf(player2.power),String.valueOf(player2.getMoney()),String.valueOf(player1.treasure_founded)}};
                String column[]={"Player","Power","Money" , "Founded treasure"};
                JTable jt=new JTable(data,column);
                jt.setBounds(0,0,600,300);
                JTextField turned = new JTextField("Turn:");
                JTextField ShowPlayer = new JTextField(info.is_turn,JTextField.RIGHT);
                JTextField tf_treasureFounded =new JTextField(info.getFounded_treasure()+" Treasure Founded");
                tf_treasureFounded.setEditable(true);
                tf_treasureFounded.setBorder(null);
                tf_treasureFounded.setFont(new Font("Arial", Font.BOLD, 30));
                turned.setEditable(false);
                turned.setBorder(null);
                turned.setFont(new Font("Arial", Font.BOLD, 30));
                ShowPlayer.setBorder(null);
                ShowPlayer.setEditable(false);
                ShowPlayer.setFont(new Font("Arial", Font.BOLD, 30));
                tf_treasureFounded.setBounds(150,250,300,80);
                turned.setBounds(200,190,100,80);
                ShowPlayer.setBounds(300,190,120,80);
                Scoreboard.add(tf_treasureFounded);
                Scoreboard.add(turned);
                Scoreboard.add(ShowPlayer);
                Scoreboard.add(jt);
                JScrollPane sp=new JScrollPane(jt);
                Scoreboard.add(sp);
                Scoreboard.getContentPane().setForeground(new Color(16,5,47));
                Scoreboard.getContentPane().setBackground(new Color(16,5,47));
                Scoreboard.setBackground(new Color(16,5,47));
                Scoreboard.setForeground(new Color(16,5,47));
                Scoreboard.setVisible(true);
            }
        });

        info.setFounded_treasure(6);

        layeredPane2.add(ShowScore2);
        layeredPane2.add(PlayerName2);
        layeredPane2.add(DiceNumber2);
        layeredPane2.add(Dice2);
        layeredPane2.setBackground(new Color(16,5,47));
        layeredPane2.setForeground(new Color(16,5,47));
        PLaying_Game2.getContentPane().setForeground(new Color(16,5,47));
        PLaying_Game2.getContentPane().setBackground(new Color(16,5,47));
        PLaying_Game2.add(layeredPane2);
        PLaying_Game2.pack();




        /************************************************************************************************/
        final int[] ll = {0};
        int finalSize = size;
        Dice.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent event) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        int dd= Integer.parseInt(DiceNumber.getText());
                        int counter = ll[0];
                        while (counter != dd+1) {
                            if (counter==dd){
                                JOptionPane.showMessageDialog(layeredPane, "Moving is more than dice number",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                ll[0] = 0;
                                for (int i =0 ; i<sizecopy ;i++){
                                    for (int j = 0; j<sizecopy ; j++){
                                        if (logicBoard[i][j]==99){
                                            arr[i][j].setBackground(Color.white);
                                            logicBoard[i][j] = 0;
                                        }
                                    }
                                }
                                PLaying_Game.setVisible(false);
                                PLaying_Game2.setVisible(true);
                                break;
                            }
                            else{
                                if (xAdjustment[0] == 0) {
                                    JOptionPane.showMessageDialog(layeredPane, "Out of board size",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    break;
                                } else {
                                    if (logicBoard[xAdjustment[0]-1][yAdjustment[0]] == 3){
                                        JOptionPane.showMessageDialog(layeredPane, "Here is a wall",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                        counter -=1;
                                    }
                                    else {
                                        arr[xAdjustment[0] - 1][yAdjustment[0]].setBackground(Color.gray);
                                        arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.red);
                                        logicBoard[xAdjustment[0]][yAdjustment[0]]=99;
                                        xAdjustment[0] -= 1;
                                        counter += 1;
                                        ll[0] = counter;
                                        if (logicBoard[xAdjustment[0] - 1][yAdjustment[0]]==5){
                                            Random rand = new Random();
                                            int money = rand.nextInt(10)+1;
                                            money *= 1000;
                                            JOptionPane.showMessageDialog(layeredPane, "You got"+ " "+ String.valueOf(money)+" money",
                                                    "congratulations", JOptionPane.PLAIN_MESSAGE);
                                            player1.money +=money;
                                            logicBoard[xAdjustment[0] - 1][yAdjustment[0]]=0;
                                        }
                                        if (logicBoard[xAdjustment[0] - 1][yAdjustment[0]]==6){
                                            Random rand = new Random();
                                            int trap = rand.nextInt(10)+1;
                                            trap *= 100;
                                            JOptionPane.showMessageDialog(layeredPane, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                    "Oh no", JOptionPane.ERROR_MESSAGE);
                                            player1.money -=trap;
                                            logicBoard[xAdjustment[0] - 1][yAdjustment[0]]=0;
                                        }
                                        if (logicBoard[xAdjustment[0] - 1][yAdjustment[0]]==99){
                                            arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.red);
                                            JOptionPane.showMessageDialog(layeredPane, "You can't return",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            counter -=1;
                                            xAdjustment[0] += 1;
                                        }
                                        ll[0] = counter;
                                    }
                                }
                            }
                            break;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        dd= Integer.parseInt(DiceNumber.getText());
                        counter = ll[0];
                        while (counter != dd+1) {
                            if (counter==dd){
                                JOptionPane.showMessageDialog(layeredPane, "Moving is more than dice number",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                ll[0] = 0;
                                for (int i =0 ; i<sizecopy ;i++){
                                    for (int j = 0; j<sizecopy ; j++){
                                        if (logicBoard[i][j]==99){
                                            arr[i][j].setBackground(Color.white);
                                            logicBoard[i][j] = 0;
                                        }
                                    }
                                }
                                PLaying_Game.setVisible(false);
                                PLaying_Game2.setVisible(true);
                                break;
                            }
                            else{
                                if (xAdjustment[0] == finalSize - 1) {
                                    JOptionPane.showMessageDialog(layeredPane, "Out of board size",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    break;
                                } else {
                                    if (logicBoard[xAdjustment[0]+1][yAdjustment[0]] == 3){
                                        JOptionPane.showMessageDialog(layeredPane, "Here is a wall",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                        counter -=1;
                                    }
                                    else {
                                        arr[xAdjustment[0] + 1][yAdjustment[0]].setBackground(Color.gray);
                                        arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.red);
                                        logicBoard[xAdjustment[0]][yAdjustment[0]]=99;
                                        xAdjustment[0] += 1;
                                        counter += 1;
                                        if (logicBoard[xAdjustment[0] + 1][yAdjustment[0]]==5){
                                            Random rand = new Random();
                                            int money = rand.nextInt(10)+1;
                                            money *= 1000;
                                            JOptionPane.showMessageDialog(layeredPane, "You got"+ " "+ String.valueOf(money)+" money",
                                                    "congratulations", JOptionPane.PLAIN_MESSAGE);
                                            player1.money +=money;
                                            logicBoard[xAdjustment[0] + 1][yAdjustment[0]]=0;
                                        }
                                        if (logicBoard[xAdjustment[0] + 1][yAdjustment[0]]==6){
                                            Random rand = new Random();
                                            int trap = rand.nextInt(10)+1;
                                            trap *= 100;
                                            JOptionPane.showMessageDialog(layeredPane, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                    "Oh no", JOptionPane.ERROR_MESSAGE);
                                            player1.money -=trap;
                                            logicBoard[xAdjustment[0] + 1][yAdjustment[0]]=0;
                                        }
                                        if (logicBoard[xAdjustment[0] + 1][yAdjustment[0]]==99){
                                            arr[xAdjustment[0] + 1][yAdjustment[0]].setBackground(Color.red);
                                            JOptionPane.showMessageDialog(layeredPane, "You can't return",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            counter -=1;
                                            xAdjustment[0] -= 1;
                                        }
                                        ll[0] = counter;
                                    }
                                }
                            }
                            break;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        dd = Integer.parseInt(DiceNumber.getText());
                        counter = ll[0];
                        while (counter != dd+1) {
                            if (counter==dd){
                                JOptionPane.showMessageDialog(layeredPane, "Moving is more than dice number",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                ll[0] = 0;
                                for (int i =0 ; i<sizecopy ;i++){
                                    for (int j = 0; j<sizecopy ; j++){
                                        if (logicBoard[i][j]==99){
                                            arr[i][j].setBackground(Color.white);
                                            logicBoard[i][j] = 0;
                                        }
                                    }
                                }
                                PLaying_Game.setVisible(false);
                                PLaying_Game2.setVisible(true);
                                break;
                            }
                            else{
                                if (yAdjustment[0] == finalSize - 1) {
                                    JOptionPane.showMessageDialog(layeredPane, "Out of board size",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    break;
                                } else {
                                    if (logicBoard[xAdjustment[0]][yAdjustment[0]+1] == 3){
                                        JOptionPane.showMessageDialog(layeredPane, "Here is a wall",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                        counter -=1;
                                    }
                                    else {
                                        arr[xAdjustment[0]][yAdjustment[0] +1].setBackground(Color.gray);
                                        arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.red);
                                        logicBoard[xAdjustment[0]][yAdjustment[0]]=99;
                                        yAdjustment[0] += 1;
                                        counter += 1;
                                        ll[0] = counter;
                                        if (logicBoard[xAdjustment[0]][yAdjustment[0] +1]==5){
                                            Random rand = new Random();
                                            int money = rand.nextInt(10)+1;
                                            money *= 1000;
                                            JOptionPane.showMessageDialog(layeredPane, "You got"+ " "+ String.valueOf(money)+" money",
                                                    "Congratulations", JOptionPane.PLAIN_MESSAGE);
                                            player1.money +=money;
                                            logicBoard[xAdjustment[0]][yAdjustment[0] +1]=0;
                                        }
                                        if (logicBoard[xAdjustment[0]][yAdjustment[0] +1]==5){
                                            Random rand = new Random();
                                            int trap = rand.nextInt(10)+1;
                                            trap *= 100;
                                            JOptionPane.showMessageDialog(layeredPane, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                    "Oh no", JOptionPane.ERROR_MESSAGE);
                                            player1.money -=trap;
                                            logicBoard[xAdjustment[0]][yAdjustment[0] +1]=0;
                                        }
                                        if (logicBoard[xAdjustment[0]][yAdjustment[0] +1]==99){
                                            arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.red);
                                            JOptionPane.showMessageDialog(layeredPane, "You can't return",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            counter -=1;
                                            yAdjustment[0] -= 1;
                                        }
                                        ll[0] = counter;
                                    }
                                }
                            }
                            break;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        dd= Integer.parseInt(DiceNumber.getText());
                        counter = ll[0];
                        while (counter != dd+1) {
                            if (counter==dd){
                                JOptionPane.showMessageDialog(layeredPane, "Moving is more than dice number",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                ll[0] = 0;
                                for (int i =0 ; i<sizecopy ;i++){
                                    for (int j = 0; j<sizecopy ; j++){
                                        if (logicBoard[i][j]==99){
                                            arr[i][j].setBackground(Color.white);
                                            logicBoard[i][j] = 0;
                                        }
                                    }
                                }
                                PLaying_Game.setVisible(false);
                                PLaying_Game2.setVisible(true);
                                break;
                            }
                            else{
                                if (yAdjustment[0] == 0) {
                                    JOptionPane.showMessageDialog(layeredPane, "Out of board size",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                    break;
                                } else {
                                    if (logicBoard[xAdjustment[0]][yAdjustment[0]-1] == 3){
                                        JOptionPane.showMessageDialog(layeredPane, "Here is a wall",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                        counter -=1;
                                    }
                                    else {
                                        arr[xAdjustment[0]][yAdjustment[0] -1].setBackground(Color.gray);
                                        arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.red);
                                        logicBoard[xAdjustment[0]][yAdjustment[0]]=99;
                                        yAdjustment[0] -= 1;
                                        counter += 1;
                                        ll[0] = counter;
                                        if (logicBoard[xAdjustment[0]][yAdjustment[0] -1]==5){
                                            Random rand = new Random();
                                            int money = rand.nextInt(10)+1;
                                            money *= 1000;
                                            JOptionPane.showMessageDialog(layeredPane, "You got"+ " "+ String.valueOf(money)+" money",
                                                    "congratulations", JOptionPane.PLAIN_MESSAGE);
                                            player1.money +=money;
                                            logicBoard[xAdjustment[0]][yAdjustment[0] -1]=0;
                                        }
                                        if (logicBoard[xAdjustment[0]][yAdjustment[0] -1]==5){
                                            Random rand = new Random();
                                            int trap = rand.nextInt(10)+1;
                                            trap *= 100;
                                            JOptionPane.showMessageDialog(layeredPane, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                    "Oh no", JOptionPane.ERROR_MESSAGE);
                                            player1.money -=trap;
                                            logicBoard[xAdjustment[0]][yAdjustment[0] -1]=0;
                                        }
                                        if (logicBoard[xAdjustment[0]][yAdjustment[0] - 1]==99){
                                            arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.red);
                                            JOptionPane.showMessageDialog(layeredPane, "You can't return",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            counter -=1;
                                            yAdjustment[0] += 1;
                                        }
                                        ll[0] = counter;
                                    }
                                }
                            }
                            break;
                        }

                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        arr[0][0].setBackground(Color.gray);




        /**********************************************************************************************/
        Scoreboard.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Scoreboard.dispose();
                if (info.getTurn()==1){
                    PLaying_Game.setVisible(true);
                }
                else{
                    PLaying_Game2.setVisible(true);
                }
            }
        });

        JFrame StartingFrame = new JFrame("Traveling Salesman");
        StartingFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("D:\\dd\\Assets\\StartingWindowBackground.png")))));
        StartingFrame.setSize(1100,750);
        StartingFrame.pack();
        StartingFrame.setVisible(true);

        JButton play = new JButton("Play");
        play.setBounds(465,650 , 80,40);
        StartingFrame.add(play);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartingFrame.setVisible(false);
                PLaying_Game.setVisible(true);
            }
        });



    }
}
