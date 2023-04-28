import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.IntStream;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.BOLD;
import static java.awt.Font.ITALIC;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit;

public class Traveling_Salesman{

    public static int[] removeTheElement(int[] arr, int index)
    {
        if (arr == null
                || index < 0
                || index >= arr.length) {

            return arr;
        }

        List<Integer> arrayList = IntStream.of(arr)
                .boxed()
                .collect(Collectors.toList());

        arrayList.remove(index);

        return arrayList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }


    public static void main(String[] args) throws IOException {
        int won =0;
        final int[] uu = {0};
        final int[] xAdjustment = {0};
        final int[] yAdjustment = {0};
        final int[] xAdjustment2 = {0};
        final int[] yAdjustment2 = {0};

        QuestClass myQuest = new QuestClass();
        String questName = myQuest.questName();

        final int[][] treasureCodes = {new int[50]};
        final int[] treasureIndex = {0};

        final int[] switchPlayer = {1};

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
        System.out.println("Enter your table size : ");
        size = 9;
        Dimension boardSize = new Dimension(1100,750);

        player1.x=0;
        player1.y=0;
        player2.x=0;
        player2.y=size-1;
        xAdjustment2[0] = 0;
        yAdjustment2[0] = size-1;


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
                    arr[i][j].add(new JLabel(new ImageIcon("Assets/castle.png")));
                }
                if (logicBoard[i][j]==3){
                    arr[i][j].add(new JLabel(new ImageIcon("Assets/wall.png")));
                }
                if (logicBoard[i][j]==4){
                    arr[i][j].add(new JLabel(new ImageIcon("Assets/market.png")));
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

        final int[] ll = {0};
        final int[] ll2 = {0};
        ll2[0] = 0;
        int finalSize = size;
        int finalSize1 = size;
        int finalSize2 = size;
        final int[] yy = {0};

        arr[0][0].setBackground(Color.blue);

        JButton ShowScore = new JButton("Score Board");
        ShowScore.setBounds(120,275,140,50);
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
                        {"Player2",String.valueOf(player2.power),String.valueOf(player2.money),String.valueOf(player2.treasure_founded)}};

                String column[]={"Player","Power","Money" , "Founded treasure"};

                PLaying_Game.setVisible(false);

                JTable jt=new JTable(data,column);
                jt.setBounds(0,0,600,300);
                JTextField turned = new JTextField("Turn:");
                JTextField ShowPlayer = new JTextField(info.is_turn,JTextField.RIGHT);
                JTextField tf_treasureFounded =new JTextField(info.Founded_treasure+" Treasure Founded");

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
                Scoreboard.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        PLaying_Game.setVisible(true);
                    }
                    public void windowClosing(WindowEvent e){
                        PLaying_Game.setVisible(true);
                    }
                });
            }
        });

        JButton Quest1 = new JButton("Quest");
        Quest1.setBounds(138,365,100,50);
        Quest1.setBackground(Color.black);
        Quest1.setForeground(Color.white);
        Quest1.setBorder(blackline);
        Quest1.setFocusPainted(false);
        Quest1.setFont(new Font("Arial", Font.PLAIN, 30));
        Quest1.setVisible(true);
        Quest1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                Quest1.setBackground(Color.white);
                Quest1.setForeground(Color.black);
                Quest1.setBorder(BorderFactory.createLineBorder(Color.black));
            }
            public void mouseExited(MouseEvent evt) {
                Quest1.setBackground(Color.black);
                Quest1.setForeground(Color.white);
                Quest1.setBorder(blackline);
            }
        });
        Quest1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame("Quest");
                f.setSize(600,300);
                f.setVisible(true);
                f.add(new Text_Animation("Quest is",questName));
                f.setBackground(new Color(16,5,47));
                f.getContentPane().setForeground(new Color(16,5,47));
                f.getContentPane().setBackground(new Color(16,5,47));
            }
        });

        layeredPane.add(ShowScore);
        layeredPane.add(PlayerName);
        layeredPane.add(DiceNumber);
        layeredPane.add(Dice);
        layeredPane.add(Quest1);
        layeredPane.setBackground(new Color(16,5,47));
        layeredPane.setForeground(new Color(16,5,47));
        PLaying_Game.getContentPane().setForeground(new Color(16,5,47));
        PLaying_Game.getContentPane().setBackground(new Color(16,5,47));
        PLaying_Game.add(layeredPane);
        PLaying_Game.pack();


        /******************************************************************/

        JFrame PLaying_Game2 = new JFrame();


        player1.x=0;
        player1.y=0;
        player2.x=0;
        player2.y=size-1;
        xAdjustment2[0] = 0;
        yAdjustment2[0] = size-1;


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


        JPanel arr2[][] = new JPanel[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0 ; j<size ; j++){
                arr2[i][j] = new JPanel( new BorderLayout() );
                Board2.add(arr2[i][j]);
                int row = (i / size) % 2;
                Border black = BorderFactory.createLineBorder(Color.black);
                arr2[i][j].setBorder(black);
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0 ; j<size ; j++){
                if (logicBoard[i][j]==1){
                    arr2[i][j].add(new JLabel(new ImageIcon("Assets/castle.png")));
                }
                if (logicBoard[i][j]==3){
                    arr2[i][j].add(new JLabel(new ImageIcon("Assets/wall.png")));
                }
                if (logicBoard[i][j]==4){
                    arr2[i][j].add(new JLabel(new ImageIcon("Assets/market.png")));
                }
            }
        }

        JLabel PlayerName2 = new JLabel("Player 2" , JLabel.CENTER);
        PlayerName2.setBounds(90 , 50 , 200,100);
        PlayerName2.setFont(new Font("Arial", Font.BOLD, 30));
        PlayerName2.setForeground(Color.orange);

        JLabel DiceNumber2 = new JLabel("" , JLabel.CENTER);
        DiceNumber2.setOpaque(true);
        DiceNumber2.setBounds(200,160,100,70);
        DiceNumber2.setBackground(Color.white);
        DiceNumber2.setForeground(Color.black);
        DiceNumber2.setBorder(blackline);
        DiceNumber2.setFont(new Font("Arial", Font.PLAIN, 30));

        JButton Dice2 = new JButton("Roll");
        Dice2.setBounds(70,160,100,70);
        Dice2.setBackground(Color.black);
        Dice2.setForeground(Color.white);
        Dice2.setBorder(blackline);
        Dice2.setFocusPainted(false);
        Dice2.setFont(new Font("Arial", Font.PLAIN, 30));
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

        arr2[0][size - 1].setBackground(Color.red);

        JButton ShowScore2 = new JButton("Score Board");
        ShowScore2.setBounds(120,275,140,50);
        ShowScore2.setBackground(Color.black);
        ShowScore2.setForeground(Color.white);
        ShowScore2.setBorder(blackline);
        ShowScore2.setFocusPainted(false);
        ShowScore2.setFont(new Font("Arial", Font.PLAIN, 25));
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
                String data[][]={ {"Player1",String.valueOf(player1.power),String.valueOf(player1.money),String.valueOf(player1.treasure_founded)},
                        {"Player2",String.valueOf(player2.power),String.valueOf(player2.money),String.valueOf(player2.treasure_founded)}};

                String column[]={"Player","Power","Money" , "Founded treasure"};

                PLaying_Game2.setVisible(false);

                JTable jt=new JTable(data,column);
                jt.setBounds(0,0,600,300);
                JTextField turned = new JTextField("Turn:");
                JTextField ShowPlayer = new JTextField(info.is_turn,JTextField.RIGHT);
                JTextField tf_treasureFounded =new JTextField(info.Founded_treasure+" Treasure Founded");

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
                Scoreboard.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        PLaying_Game2.setVisible(true);
                    }
                    public void windowClosing(WindowEvent e){
                        PLaying_Game2.setVisible(true);
                    }
                });
            }
        });
        


        JButton Quest2 = new JButton("Quest");
        Quest2.setBounds(138,365,100,50);
        Quest2.setBackground(Color.black);
        Quest2.setForeground(Color.white);
        Quest2.setBorder(blackline);
        Quest2.setFocusPainted(false);
        Quest2.setFont(new Font("Arial", Font.PLAIN, 30));
        Quest2.setVisible(true);
        Quest2.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent evt) {
                Quest2.setBackground(Color.white);
                Quest2.setForeground(Color.black);
                Quest2.setBorder(BorderFactory.createLineBorder(Color.black));
            }
            public void mouseExited(MouseEvent evt) {
                Quest2.setBackground(Color.black);
                Quest2.setForeground(Color.white);
                Quest2.setBorder(blackline);
            }
        });
        Quest2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame("Quest");
                f.setSize(600,300);
                f.setVisible(true);
                f.add(new Text_Animation("Quest is",questName));
                f.setBackground(new Color(16,5,47));
            }
        });


        layeredPane2.add(ShowScore2);
        layeredPane2.add(PlayerName2);
        layeredPane2.add(DiceNumber2);
        layeredPane2.add(Dice2);
        layeredPane2.add(Quest2);
        layeredPane2.setBackground(new Color(16,5,47));
        layeredPane2.setForeground(new Color(16,5,47));
        PLaying_Game2.getContentPane().setForeground(new Color(16,5,47));
        PLaying_Game2.getContentPane().setBackground(new Color(16,5,47));
        PLaying_Game2.add(layeredPane2);
        PLaying_Game2.pack();


        /******************************************************************/

        Dice2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent event) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (switchPlayer[0] ==2){
                            int dd= Integer.parseInt(DiceNumber2.getText());
                            int counter = ll2[0];
                            while (counter != dd+1) {
                                if (counter==dd){
                                    JOptionPane.showMessageDialog(layeredPane2, "Moving is more than dice number",
                                            "Error", JOptionPane.ERROR_MESSAGE);

                                    switchPlayer[0]=1;
                                    counter=0;
                                    ll2[0] = 0;

                                    int saveX = xAdjustment2[0];
                                    int saveY = yAdjustment2[0];

                                    for (int i = 0; i< finalSize1; i++){
                                        for (int j = 0; j< finalSize1; j++){
                                            if (logicBoard[i][j]==99){
                                                arr2[i][j].setBackground(Color.white);
                                                logicBoard[i][j] = 0;
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
                                    }

                                    else {

                                        if (logicBoard[xAdjustment2[0]-1][yAdjustment2[0]] == 3){
                                            JOptionPane.showMessageDialog(layeredPane2, "Here is a wall",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            counter -=1;
                                        }

                                        else {

                                            arr2[xAdjustment2[0] - 1][yAdjustment2[0]].setBackground(Color.red);

                                            if (logicBoard[xAdjustment2[0]][yAdjustment2[0]] != 1 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=21 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=22 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=23 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=24 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=25 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=26 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=27 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=28 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=4){
                                                logicBoard[xAdjustment2[0]][yAdjustment2[0]] = 99;
                                            }

                                            if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==1){
                                                JFrame f=new JFrame("Castle");
                                                f.getContentPane().setBackground(new Color(16,5,47));
                                                final JLabel label = new JLabel();
                                                label.setBounds(80,600, 250,50);
                                                JTextField value = new JTextField();
                                                value.setBounds(355,265,100,30);
                                                JLabel l2=new JLabel("Enter your treasure Code:");
                                                l2.setBounds(120,250, 300,60);
                                                l2.setFont(new Font("Arial", Font.PLAIN, 20));
                                                l2.setForeground(Color.white);
                                                JButton b = new JButton("Done");
                                                b.setBounds(250,320, 80,30);
                                                f.add(value); f.add(label); f.add(l2); f.add(b);
                                                f.setSize(600,600);
                                                f.setLayout(null);
                                                f.setVisible(true);
                                                b.addActionListener(new ActionListener() {
                                                    public void actionPerformed(ActionEvent e) {
                                                        int index = 0;
                                                        String code = value.getText();
                                                        int sw = 0;
                                                        for (int i = 0; i< treasureCodes[0].length ; i++){
                                                            if (treasureCodes[0][i]==Integer.parseInt(code)){
                                                                sw =1;
                                                                index = i;
                                                                break;
                                                            }
                                                        }
                                                        if (sw==1){
                                                            info.treasureCounter +=1;
                                                            for (int i = 0; i< finalSize2; i++){
                                                                for (int j = 0 ; j<finalSize2 ; j++){
                                                                    if (logicBoard[i][j]==21 || logicBoard[i][j]==22 || logicBoard[i][j]==23 || logicBoard[i][j]==24 || logicBoard[i][j]==25 || logicBoard[i][j]==26 || logicBoard[i][j]==27 || logicBoard[i][j]==28){
                                                                        System.out.printf("--%d %d--\n" , i,j);
                                                                    }
                                                                }
                                                            }
                                                            for(int i = 0; i< 10 ; i++){
                                                                System.out.println(treasureCodes[0][i]);
                                                            }
                                                            String namme = null;
                                                            int name =  treasureCodes[0][index-1];
                                                            int temp1 = treasureCodes[0][index+1];
                                                            int temp2 = treasureCodes[0][index+2];
                                                            for (int i = 0; i< finalSize2; i++){
                                                                for (int j = 0 ; j<finalSize2 ; j++){
                                                                    if (logicBoard[i][j]==21 || logicBoard[i][j]==22 || logicBoard[i][j]==23 || logicBoard[i][j]==24 || logicBoard[i][j]==25 || logicBoard[i][j]==26 || logicBoard[i][j]==27 || logicBoard[i][j]==28){
                                                                        System.out.printf("--%d %d--\n" , i,j);
                                                                    }
                                                                }
                                                            }
                                                            logicBoard[temp1][temp2] = 0;
                                                            treasureCodes[0] =removeTheElement(treasureCodes[0],index);
                                                            System.out.println("-------------");
                                                            for(int i = 0; i< 10 ; i++){
                                                                System.out.println(treasureCodes[0][i]);
                                                            }
                                                            if (name==21){
                                                                namme = "Diamond ring";
                                                            }
                                                            if (name==22){
                                                                namme = "Jeweled sword";
                                                            }
                                                            if (name==23){
                                                                namme = "Golden glass";
                                                            }
                                                            if (name==24){
                                                                namme = "Glass cup";
                                                            }
                                                            if (name==25){
                                                                namme = "Wooden bow";
                                                            }
                                                            if (name==26){
                                                                namme = "Steel shield";
                                                            }
                                                            if (name==27){
                                                                namme = "Golden key";
                                                            }
                                                            if (name==28){
                                                                namme = "Dragon scroll";
                                                            }
                                                            JOptionPane.showMessageDialog(f, "You have found "+namme+"\n"+"Your money increased",
                                                                    "Treasure Confirmed", JOptionPane.OK_OPTION);
                                                            if(name == myQuest.quest){
                                                                JOptionPane.showMessageDialog(f, "Quest Done",
                                                                        "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                myQuest.questName();
                                                            }
                                                            player2.money += 100000;
                                                            player2.treasure_founded += 1;
                                                            info.treasureCounter += 1;
                                                            f.setVisible(false);
                                                            if (info.treasureCounter == 8){
                                                                PLaying_Game.setVisible(false);
                                                                PLaying_Game2.setVisible(false);
                                                                if (player1.treasure_founded > player2.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player1"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                if (player2.treasure_founded > player1.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player2"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                else{
                                                                    if (player1.treasure_founded > player2.treasure_founded){
                                                                        JFrame f = new JFrame("Quest");
                                                                        f.setSize(600,300);
                                                                        f.setVisible(true);
                                                                        f.add(new Text_Animation("Draw"," "));
                                                                        f.setBackground(new Color(16,5,47));
                                                                        f.getContentPane().setForeground(new Color(16,5,47));
                                                                        f.getContentPane().setBackground(new Color(16,5,47));
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        if(sw==0){
                                                            JOptionPane.showMessageDialog(f, "This is not a trasure code",
                                                                    "Treasure Confirmed", JOptionPane.ERROR_MESSAGE);
                                                            f.setVisible(false);
                                                        }
                                                    }
                                                });
                                            }

                                            if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==21){
                                                Random rand = new Random();
                                                int code = rand.nextInt(2000)+1000;
                                                JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                        "Founded", JOptionPane.ERROR_MESSAGE);
                                                treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]];
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = code;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] - 1;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                                treasureIndex[0] +=1;
                                            }
                                            if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==22){
                                                Random rand = new Random();
                                                int code = rand.nextInt(2000)+1000;
                                                JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                        "Founded", JOptionPane.ERROR_MESSAGE);
                                                treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]];
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = code;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] - 1;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                                treasureIndex[0] +=1;
                                            }
                                            if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==23){
                                                Random rand = new Random();
                                                int code = rand.nextInt(2000)+1000;
                                                JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                        "Founded", JOptionPane.ERROR_MESSAGE);
                                                treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]];
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = code;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] - 1;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                                treasureIndex[0] +=1;
                                            }
                                            if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==24){
                                                Random rand = new Random();
                                                int code = rand.nextInt(2000)+1000;
                                                JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                        "Founded", JOptionPane.ERROR_MESSAGE);
                                                treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]];
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = code;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] - 1;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                                treasureIndex[0] +=1;
                                            }
                                            if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==25){
                                                Random rand = new Random();
                                                int code = rand.nextInt(2000)+1000;
                                                JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                        "Founded", JOptionPane.ERROR_MESSAGE);
                                                treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]];
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = code;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] - 1;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                                treasureIndex[0] +=1;
                                            }
                                            if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==26){
                                                Random rand = new Random();
                                                int code = rand.nextInt(2000)+1000;
                                                JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                        "Founded", JOptionPane.ERROR_MESSAGE);
                                                treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]];
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = code;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] - 1;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                                treasureIndex[0] +=1;
                                            }
                                            if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==27){
                                                Random rand = new Random();
                                                int code = rand.nextInt(2000)+1000;
                                                JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                        "Founded", JOptionPane.ERROR_MESSAGE);
                                                treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]];
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = code;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] - 1;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                                treasureIndex[0] +=1;
                                            }
                                            if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==28){
                                                Random rand = new Random();
                                                int code = rand.nextInt(2000)+1000;
                                                JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                        "Founded", JOptionPane.ERROR_MESSAGE);
                                                treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]];
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = code;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] - 1;
                                                treasureIndex[0] +=1;
                                                treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                                treasureIndex[0] +=1;
                                            }

                                            if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==4){
                                                Border blackline2 = BorderFactory.createLineBorder(Color.white);
                                                JFrame f=new JFrame("Market");
                                                f.getContentPane().setBackground(new Color(16,5,47));
                                                JLabel l2=new JLabel("Market");
                                                l2.setBounds(240, 30 , 300,60);
                                                l2.setFont(new Font("Arial", Font.PLAIN, 30));
                                                l2.setForeground(Color.white);
                                                JButton b = new JButton("Buy treasure place");
                                                b.setFont(new Font("Arial", Font.BOLD, 10));
                                                b.setBounds(140,110, 130,60);
                                                b.setBackground(Color.black);
                                                b.setForeground(Color.white);
                                                b.setBorder(blackline2);
                                                b.setFocusPainted(false);
                                                b.addMouseListener(new MouseAdapter() {

                                                    public void mouseEntered(MouseEvent evt) {
                                                        b.setText("7000");
                                                        b.setBackground(Color.white);
                                                        b.setForeground(Color.black);
                                                        b.setBorder(BorderFactory.createLineBorder(Color.black));
                                                        b.setFont(new Font("Arial", Font.BOLD, 30));
                                                    }

                                                    public void mouseExited(MouseEvent evt) {
                                                        b.setBackground(Color.black);
                                                        b.setForeground(Color.white);
                                                        b.setBorder(blackline2);
                                                        b.setText("Buy treasure place");
                                                        b.setFont(new Font("Arial", Font.BOLD, 10));
                                                    }

                                                    @Override
                                                    public void mouseClicked(MouseEvent e) {

                                                    }
                                                });
                                                b.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        if (player2.money > 7000){
                                                            int sw = 0;
                                                            for (int i = 0 ;i<finalSize1 && sw ==0 ; i++){
                                                                for (int j = 0 ; j<finalSize1 && sw ==0 ; j++){
                                                                    if (logicBoard[i][j] == 21 || logicBoard[i][j] == 22 || logicBoard[i][j] == 23 || logicBoard[i][j] ==24 || logicBoard[i][j] ==25 || logicBoard[i][j] == 26 || logicBoard[i][j] ==27 || logicBoard[i][j] ==28){
                                                                        JOptionPane.showMessageDialog(f, "Treasure place : " + String.valueOf(i+1) + " " + String.valueOf(j+1),
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        sw = 1;
                                                                    }
                                                                }
                                                            }
                                                            player2.money -= 7000;
                                                        }
                                                        else {
                                                            JOptionPane.showMessageDialog(f, "Your money is low",
                                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                                        }
                                                    }
                                                });


                                                JButton b2 = new JButton("Buy weapon");
                                                b2.setFont(new Font("Arial", Font.BOLD, 15));
                                                b2.setBounds(300,110, 130,60);
                                                b2.setBackground(Color.black);
                                                b2.setForeground(Color.white);
                                                b2.setBorder(blackline2);
                                                b2.setFocusPainted(false);
                                                b2.addMouseListener(new MouseAdapter() {

                                                    public void mouseEntered(MouseEvent evt) {
                                                        b2.setBackground(Color.white);
                                                        b2.setForeground(Color.black);
                                                        b2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                    }

                                                    public void mouseExited(MouseEvent evt) {
                                                        b2.setBackground(Color.black);
                                                        b2.setForeground(Color.white);
                                                        b2.setBorder(blackline2);
                                                    }

                                                    @Override
                                                    public void mouseClicked(MouseEvent e) {
                                                        f.dispose();
                                                        JFrame h = new JFrame("Weapons");
                                                        h.setSize(600,400);
                                                        h.getContentPane().setBackground(new Color(16,5,47));
                                                        h.setForeground(Color.white);

                                                        JLabel l3=new JLabel("Weapons");
                                                        l3.setBounds(230, 30 , 300,60);
                                                        l3.setFont(new Font("Arial", Font.PLAIN, 30));
                                                        l3.setForeground(Color.white);

                                                        ImageIcon weapon1 = new ImageIcon("Assets/weapon3.png");
                                                        JButton w1 = new JButton();
                                                        w1.setIcon((weapon1));
                                                        w1.setBounds(50,110,120,80);
                                                        w1.setBackground(Color.black);
                                                        w1.setForeground(Color.white);
                                                        w1.setBorder(blackline2);
                                                        w1.setFocusPainted(false);
                                                        w1.addMouseListener(new MouseAdapter() {
                                                            @Override
                                                            public void mouseEntered(MouseEvent e) {
                                                                w1.setIcon(null);
                                                                w1.setText("5000");
                                                                w1.setBackground(Color.white);
                                                                w1.setForeground(Color.black);
                                                                w1.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                w1.setFont(new Font("Arial", Font.BOLD, 30));
                                                            }

                                                            @Override
                                                            public void mouseExited(MouseEvent e) {
                                                                w1.setBackground(Color.black);
                                                                w1.setForeground(Color.white);
                                                                w1.setBorder(blackline2);
                                                                w1.setText(null);
                                                                w1.setIcon((weapon1));
                                                            }

                                                            @Override
                                                            public void mouseClicked(MouseEvent e) {
                                                                super.mouseClicked(e);
                                                            }
                                                        });
                                                        w1.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (player2.money > 5000){
                                                                    JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                            "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                    player2.power += 5000;
                                                                    player2.money -= 5000;
                                                                    System.out.println(player2.power);
                                                                }
                                                                else {
                                                                    JOptionPane.showMessageDialog(f, "Your money is low",
                                                                            "Error", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            }
                                                        });


                                                        ImageIcon weapon2 = new ImageIcon("Assets/weapon2.png");
                                                        JButton w2 = new JButton();
                                                        w2.setIcon((weapon2));
                                                        w2.setBounds(230,110,120,80);
                                                        w2.setBackground(Color.black);
                                                        w2.setForeground(Color.white);
                                                        w2.setBorder(blackline2);
                                                        w2.setFocusPainted(false);
                                                        w2.addMouseListener(new MouseAdapter() {
                                                            @Override
                                                            public void mouseEntered(MouseEvent e) {
                                                                w2.setIcon(null);
                                                                w2.setText("6000");
                                                                w2.setBackground(Color.white);
                                                                w2.setForeground(Color.black);
                                                                w2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                w2.setFont(new Font("Arial", Font.BOLD, 30));
                                                            }

                                                            @Override
                                                            public void mouseExited(MouseEvent e) {
                                                                w2.setBackground(Color.black);
                                                                w2.setForeground(Color.white);
                                                                w2.setBorder(blackline2);
                                                                w2.setText(null);
                                                                w2.setIcon((weapon2));
                                                            }

                                                            @Override
                                                            public void mouseClicked(MouseEvent e) {
                                                                super.mouseClicked(e);
                                                            }
                                                        });
                                                        w2.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (player2.money > 6000){
                                                                    JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                            "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                    player2.power += 6000;
                                                                    player2.money -= 6000;
                                                                    System.out.println(player2.power);
                                                                }
                                                                else {
                                                                    JOptionPane.showMessageDialog(f, "Your money is low",
                                                                            "Error", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            }
                                                        });

                                                        ImageIcon weapon3 = new ImageIcon("Assets/weapon1.png");
                                                        JButton w3 = new JButton();
                                                        w3.setIcon((weapon3));
                                                        w3.setBounds(410,110,120,80);
                                                        w3.setBackground(Color.black);
                                                        w3.setForeground(Color.white);
                                                        w3.setBorder(blackline2);
                                                        w3.setFocusPainted(false);
                                                        w3.addMouseListener(new MouseAdapter() {
                                                            @Override
                                                            public void mouseEntered(MouseEvent e) {
                                                                w3.setIcon(null);
                                                                w3.setText("7000");
                                                                w3.setBackground(Color.white);
                                                                w3.setForeground(Color.black);
                                                                w3.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                w3.setFont(new Font("Arial", Font.BOLD, 30));
                                                            }

                                                            @Override
                                                            public void mouseExited(MouseEvent e) {
                                                                w3.setBackground(Color.black);
                                                                w3.setForeground(Color.white);
                                                                w3.setBorder(blackline2);
                                                                w3.setText(null);
                                                                w3.setIcon((weapon3));
                                                            }

                                                            @Override
                                                            public void mouseClicked(MouseEvent e) {
                                                                super.mouseClicked(e);
                                                            }
                                                        });
                                                        w3.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (player2.money > 7000){
                                                                    JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                            "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                    player2.power += 7000;
                                                                    player2.money -= 7000;
                                                                    System.out.println(player2.power);
                                                                }
                                                                else {
                                                                    JOptionPane.showMessageDialog(f, "Your money is low",
                                                                            "Error", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            }
                                                        });

                                                        ImageIcon weapon4 = new ImageIcon("Assets/weapon4.png");
                                                        JButton w4 = new JButton();
                                                        w4.setIcon((weapon4));
                                                        w4.setBounds(50,230,120,80);
                                                        w4.setBackground(Color.black);
                                                        w4.setForeground(Color.white);
                                                        w4.setBorder(blackline2);
                                                        w4.setFocusPainted(false);
                                                        w4.addMouseListener(new MouseAdapter() {
                                                            @Override
                                                            public void mouseEntered(MouseEvent e) {
                                                                w4.setIcon(null);
                                                                w4.setText("8000");
                                                                w4.setBackground(Color.white);
                                                                w4.setForeground(Color.black);
                                                                w4.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                w4.setFont(new Font("Arial", Font.BOLD, 30));
                                                            }

                                                            @Override
                                                            public void mouseExited(MouseEvent e) {
                                                                w4.setBackground(Color.black);
                                                                w4.setForeground(Color.white);
                                                                w4.setBorder(blackline2);
                                                                w4.setText(null);
                                                                w4.setIcon((weapon4));
                                                            }

                                                            @Override
                                                            public void mouseClicked(MouseEvent e) {
                                                                super.mouseClicked(e);
                                                            }
                                                        });
                                                        w4.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (player2.money > 8000){
                                                                    JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                            "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                    player2.power += 8000;
                                                                    player2.money -= 8000;
                                                                    System.out.println(player2.power);
                                                                }
                                                                else {
                                                                    JOptionPane.showMessageDialog(f, "Your money is low",
                                                                            "Error", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            }
                                                        });

                                                        ImageIcon weapon5 = new ImageIcon("Assets/weapon5.png");
                                                        JButton w5 = new JButton();
                                                        w5.setIcon((weapon5));
                                                        w5.setBounds(230,230,120,80);
                                                        w5.setBackground(Color.black);
                                                        w5.setForeground(Color.white);
                                                        w5.setBorder(blackline2);
                                                        w5.setFocusPainted(false);
                                                        w5.addMouseListener(new MouseAdapter() {
                                                            @Override
                                                            public void mouseEntered(MouseEvent e) {
                                                                w5.setIcon(null);
                                                                w5.setText("9000");
                                                                w5.setBackground(Color.white);
                                                                w5.setForeground(Color.black);
                                                                w5.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                w5.setFont(new Font("Arial", Font.BOLD, 30));
                                                            }

                                                            @Override
                                                            public void mouseExited(MouseEvent e) {
                                                                w5.setBackground(Color.black);
                                                                w5.setForeground(Color.white);
                                                                w5.setBorder(blackline2);
                                                                w5.setText(null);
                                                                w5.setIcon((weapon5));
                                                            }

                                                            @Override
                                                            public void mouseClicked(MouseEvent e) {
                                                                super.mouseClicked(e);
                                                            }
                                                        });
                                                        w5.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (player2.money > 9000){
                                                                    JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                            "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                    player2.power += 9000;
                                                                    player2.money -= 9000;
                                                                    System.out.println(player2.power);
                                                                }
                                                                else {
                                                                    JOptionPane.showMessageDialog(f, "Your money is low",
                                                                            "Error", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            }
                                                        });

                                                        ImageIcon weapon6 = new ImageIcon("Assets/weapon6.png");
                                                        JButton w6 = new JButton();
                                                        w6.setIcon((weapon6));
                                                        w6.setBounds(410,230,120,80);
                                                        w6.setBackground(Color.black);
                                                        w6.setForeground(Color.white);
                                                        w6.setBorder(blackline2);
                                                        w6.setFocusPainted(false);
                                                        w6.addMouseListener(new MouseAdapter() {
                                                            @Override
                                                            public void mouseEntered(MouseEvent e) {
                                                                w6.setIcon(null);
                                                                w6.setText("10000");
                                                                w6.setBackground(Color.white);
                                                                w6.setForeground(Color.black);
                                                                w6.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                w6.setFont(new Font("Arial", Font.BOLD, 30));
                                                            }

                                                            @Override
                                                            public void mouseExited(MouseEvent e) {
                                                                w6.setBackground(Color.black);
                                                                w6.setForeground(Color.white);
                                                                w6.setBorder(blackline2);
                                                                w6.setText(null);
                                                                w6.setIcon((weapon6));
                                                            }

                                                            @Override
                                                            public void mouseClicked(MouseEvent e) {
                                                                super.mouseClicked(e);
                                                            }
                                                        });
                                                        w6.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (player2.money > 10000){
                                                                    JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                            "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                    player2.power += 10000;
                                                                    player2.money -= 10000;
                                                                    System.out.println(player2.power);
                                                                }
                                                                else {
                                                                    JOptionPane.showMessageDialog(f, "Your money is low",
                                                                            "Error", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            }
                                                        });


                                                        h.add(w1);
                                                        h.add(w2);
                                                        h.add(w3);
                                                        h.add(w4);
                                                        h.add(w5);
                                                        h.add(w6);
                                                        h.add(l3);

                                                        h.setLocationRelativeTo(null);
                                                        h.setLayout(null);
                                                        h.setVisible(true);

                                                    }

                                                });


                                                f.add(l2); f.add(b);f.add(b2);
                                                f.setSize(600,250);
                                                f.setLocationRelativeTo(null);
                                                f.setLayout(null);
                                                f.setVisible(true);
                                            }

                                            if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==5){
                                                Random rand = new Random();
                                                int money = rand.nextInt(10)+1;
                                                money *= 1000;
                                                JOptionPane.showMessageDialog(layeredPane2, "You got"+ " "+ String.valueOf(money)+" money",
                                                        "congratulations", JOptionPane.PLAIN_MESSAGE);
                                                player1.money +=money;
                                                logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]=0;
                                            }

                                            if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==6){
                                                Random rand = new Random();
                                                int trap = rand.nextInt(10)+1;
                                                trap *= 100;
                                                JOptionPane.showMessageDialog(layeredPane2, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                        "Oh no", JOptionPane.ERROR_MESSAGE);
                                                player2.money -=trap;
                                                logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]=0;
                                            }

                                            if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==99){
                                                JOptionPane.showMessageDialog(layeredPane2, "You can not return",
                                                        "Oh no", JOptionPane.ERROR_MESSAGE);
                                                arr2[xAdjustment2[0] - 1][yAdjustment2[0]].setBackground(Color.white);
                                                xAdjustment2[0] += 1;
                                                counter -= 1;
                                            }

                                            if (xAdjustment2[0]-1 == xAdjustment[0] && yAdjustment2[0]==yAdjustment[0]){
                                                System.out.println("Yes");
                                                if (player1.power == player2.power){
                                                    JOptionPane.showMessageDialog(layeredPane2, "Player1 won.\nbeacuse Player1 is here more",
                                                            "Fight", JOptionPane.PLAIN_MESSAGE);

                                                    int moneyTemp = 0;
                                                    if (player1.power + player2.power != 0){
                                                        moneyTemp=((player1.power - player2.power)/(player1.power + player2.power)) * player2.money;
                                                    }
                                                    player1.money += moneyTemp;
                                                    player2.money -= moneyTemp;

                                                    player1.power -= player2.power;
                                                    player2.power = 0;

                                                    uu[0] =1;
                                                    arr2[xAdjustment2[0]-1][yAdjustment2[0]].setBackground(Color.white);
                                                }
                                                if (player1.power > player2.power){
                                                    JOptionPane.showMessageDialog(layeredPane2, "Player1 won.\n beacuse Player1's power is more",
                                                            "Fight", JOptionPane.PLAIN_MESSAGE);

                                                    int moneyTemp = 0;
                                                    if (player1.power + player2.power != 0){
                                                        moneyTemp=((player1.power - player2.power)/(player1.power + player2.power)) * player2.money;
                                                    }
                                                    player1.money += moneyTemp;
                                                    player2.money -= moneyTemp;

                                                    player1.power -= player2.power;
                                                    player2.power = 0;

                                                    uu[0] =1;
                                                    arr2[xAdjustment2[0]-1][yAdjustment2[0]].setBackground(Color.white);
                                                }
                                                if (player1.power < player2.power){
                                                    JOptionPane.showMessageDialog(layeredPane2, "Player2 won.\nbeacuse Player2's power is more",
                                                            "Fight", JOptionPane.PLAIN_MESSAGE);

                                                    int moneyTemp = 0;
                                                    if (player1.power + player2.power != 0){
                                                        moneyTemp = ((player2.power - player1.power)/(player2.power + player1.power)) * player1.money;
                                                    }
                                                    player2.money += moneyTemp;
                                                    player1.money -= moneyTemp;

                                                    player2.power -= player1.power;
                                                    player1.power = 0;

                                                    arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.white);
                                                    xAdjustment[0] = 0;
                                                    yAdjustment[0] = 0;
                                                }
                                            }
                                            if (uu[0]==0){
                                                player2.x=xAdjustment2[0] - 1;
                                                player2.y=yAdjustment2[0];
                                                xAdjustment2[0] -= 1;
                                                counter += 1;
                                                ll2[0] = counter;


                                                yy[0] = 0;
                                                arr2[xAdjustment2[0]+1][yAdjustment2[0]].setBackground(Color.white);
                                            }
                                            if (uu[0]==1){
                                                arr2[xAdjustment2[0]][yAdjustment2[0]].setBackground(Color.white);
                                                xAdjustment2[0] = 0;
                                                yAdjustment2[0] = finalSize-1;
                                                uu[0]=0;
                                                counter += 1;
                                                ll2[0] = 0;
                                                PLaying_Game2.setVisible(false);
                                                PLaying_Game.setVisible(true);
                                                arr2[xAdjustment2[0]][yAdjustment2[0]].setBackground(Color.red);
                                                switchPlayer[0] = 1;
                                            }
                                        }
                                    }
                                }
                                break;
                            }
                        }
                        break;

                    case KeyEvent.VK_DOWN:
                        if (switchPlayer[0]==2){
                            int dd= Integer.parseInt(DiceNumber2.getText());
                            int counter = ll[0];
                            if (yy[0]==0){
                                while (counter != dd+1) {
                                    dd= Integer.parseInt(DiceNumber2.getText());
                                    counter = ll2[0];
                                    if (counter==dd){
                                        JOptionPane.showMessageDialog(layeredPane2, "Moving is more than dice number",
                                                "Error", JOptionPane.ERROR_MESSAGE);

                                        switchPlayer[0]=1;
                                        counter=0;
                                        ll2[0] = 0;

                                        for (int i = 0; i< finalSize1; i++){
                                            for (int j = 0; j< finalSize1; j++){
                                                if (logicBoard[i][j]==99){
                                                    arr2[i][j].setBackground(Color.white);
                                                    logicBoard[i][j] = 0;
                                                }
                                            }
                                        }
                                        PLaying_Game2.setVisible(false);
                                        PLaying_Game.setVisible(true);

                                        break;
                                    }
                                    else {
                                        arr2[xAdjustment2[0] + 1][yAdjustment2[0]].setBackground(Color.red);

                                        if (logicBoard[xAdjustment2[0]][yAdjustment2[0]] != 1 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=21 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=22 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=23 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=24 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=25 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=26 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=27 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=28 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=4){
                                            logicBoard[xAdjustment2[0]][yAdjustment2[0]] = 99;
                                        }

                                        if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==1){
                                            JFrame f=new JFrame("Castle");
                                            f.getContentPane().setBackground(new Color(16,5,47));
                                            final JLabel label = new JLabel();
                                            label.setBounds(80,600, 250,50);
                                            JTextField value = new JTextField();
                                            value.setBounds(355,265,100,30);
                                            JLabel l2=new JLabel("Enter your treasure Code:");
                                            l2.setBounds(120,250, 300,60);
                                            l2.setFont(new Font("Arial", Font.PLAIN, 20));
                                            l2.setForeground(Color.white);
                                            JButton b = new JButton("Done");
                                            b.setBounds(250,320, 80,30);
                                            f.add(value); f.add(label); f.add(l2); f.add(b);
                                            f.setSize(600,600);
                                            f.setLayout(null);
                                            f.setVisible(true);
                                            b.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    int index = 0;
                                                    String code = value.getText();
                                                    int sw = 0;
                                                    for (int i = 0; i< treasureCodes[0].length ; i++){
                                                        if (treasureCodes[0][i]==Integer.parseInt(code)){
                                                            sw =1;
                                                            index = i;
                                                            break;
                                                        }
                                                    }
                                                    if (sw==1){
                                                        info.treasureCounter +=1;
                                                        for(int i = 0; i< 10 ; i++){
                                                            System.out.println(treasureCodes[0][i]);
                                                        }
                                                        String namme = null;
                                                        int name =  treasureCodes[0][index-1];
                                                        int temp1 = treasureCodes[0][index+1];
                                                        int temp2 = treasureCodes[0][index+2];
                                                        logicBoard[temp1][temp2] = 0;
                                                        treasureCodes[0] =removeTheElement(treasureCodes[0],index);
                                                        System.out.println("-------------");
                                                        for(int i = 0; i< 10 ; i++){
                                                            System.out.println(treasureCodes[0][i]);
                                                        }
                                                        if (name==21){
                                                            namme = "Diamond ring";
                                                        }
                                                        if (name==22){
                                                            namme = "Jeweled sword";
                                                        }
                                                        if (name==23){
                                                            namme = "Golden glass";
                                                        }
                                                        if (name==24){
                                                            namme = "Glass cup";
                                                        }
                                                        if (name==25){
                                                            namme = "Wooden bow";
                                                        }
                                                        if (name==26){
                                                            namme = "Steel shield";
                                                        }
                                                        if (name==27){
                                                            namme = "Golden key";
                                                        }
                                                        if (name==28){
                                                            namme = "Dragon scroll";
                                                        }
                                                        JOptionPane.showMessageDialog(f, "You have found "+namme+"\n"+"Your money increased",
                                                                "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                        if(name == myQuest.quest){
                                                            JOptionPane.showMessageDialog(f, "Quest Done",
                                                                    "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                            myQuest.questName();
                                                        }
                                                        player2.money += 100000;
                                                        player2.treasure_founded += 1;
                                                        info.treasureCounter += 1;
                                                        f.setVisible(false);
                                                            if (info.treasureCounter == 8){
                                                                PLaying_Game.setVisible(false);
                                                                PLaying_Game2.setVisible(false);
                                                                if (player1.treasure_founded > player2.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player1"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                if (player2.treasure_founded > player1.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player2"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                else{
                                                                    if (player1.treasure_founded > player2.treasure_founded){
                                                                        JFrame f = new JFrame("Quest");
                                                                        f.setSize(600,300);
                                                                        f.setVisible(true);
                                                                        f.add(new Text_Animation("Draw"," "));
                                                                        f.setBackground(new Color(16,5,47));
                                                                        f.getContentPane().setForeground(new Color(16,5,47));
                                                                        f.getContentPane().setBackground(new Color(16,5,47));
                                                                    }
                                                                }
                                                            }
                                                    }
                                                    if(sw==0){
                                                        JOptionPane.showMessageDialog(f, "This is not a trasure code",
                                                                "Treasure Confirmed", JOptionPane.ERROR_MESSAGE);
                                                        f.setVisible(false);
                                                    }
                                                }
                                            });
                                        }

                                        if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==21){
                                            Random rand = new Random();
                                            int code = rand.nextInt(2000)+1000;
                                            JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                    "Founded", JOptionPane.ERROR_MESSAGE);
                                            treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]];
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = code;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] + 1;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                            treasureIndex[0] +=1;
                                        }
                                        if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==22){
                                            Random rand = new Random();
                                            int code = rand.nextInt(2000)+1000;
                                            JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                    "Founded", JOptionPane.ERROR_MESSAGE);
                                            treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]];
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = code;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] + 1;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                            treasureIndex[0] +=1;
                                        }
                                        if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==23){
                                            Random rand = new Random();
                                            int code = rand.nextInt(2000)+1000;
                                            JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                    "Founded", JOptionPane.ERROR_MESSAGE);
                                            treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]];
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = code;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] + 1;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                            treasureIndex[0] +=1;
                                        }
                                        if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==24){
                                            Random rand = new Random();
                                            int code = rand.nextInt(2000)+1000;
                                            JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                    "Founded", JOptionPane.ERROR_MESSAGE);
                                            treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]];
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = code;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] + 1;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                            treasureIndex[0] +=1;
                                        }
                                        if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==25){
                                            Random rand = new Random();
                                            int code = rand.nextInt(2000)+1000;
                                            JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                    "Founded", JOptionPane.ERROR_MESSAGE);
                                            treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]];
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = code;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] + 1;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                            treasureIndex[0] +=1;
                                        }
                                        if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==26){
                                            Random rand = new Random();
                                            int code = rand.nextInt(2000)+1000;
                                            JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                    "Founded", JOptionPane.ERROR_MESSAGE);
                                            treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]];
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = code;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] + 1;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                            treasureIndex[0] +=1;
                                        }
                                        if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==27){
                                            Random rand = new Random();
                                            int code = rand.nextInt(2000)+1000;
                                            JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                    "Founded", JOptionPane.ERROR_MESSAGE);
                                            treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]];
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = code;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] + 1;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                            treasureIndex[0] +=1;
                                        }
                                        if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==28){
                                            Random rand = new Random();
                                            int code = rand.nextInt(2000)+1000;
                                            JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                    "Founded", JOptionPane.ERROR_MESSAGE);
                                            treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]];
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = code;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = xAdjustment2[0] + 1;
                                            treasureIndex[0] +=1;
                                            treasureCodes[0][treasureIndex[0]] = yAdjustment2[0];
                                            treasureIndex[0] +=1;
                                        }

                                        if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==4){
                                            Border blackline2 = BorderFactory.createLineBorder(Color.white);
                                            JFrame f=new JFrame("Market");
                                            f.getContentPane().setBackground(new Color(16,5,47));
                                            JLabel l2=new JLabel("Market");
                                            l2.setBounds(240, 30 , 300,60);
                                            l2.setFont(new Font("Arial", Font.PLAIN, 30));
                                            l2.setForeground(Color.white);
                                            JButton b = new JButton("Buy treasure place");
                                            b.setFont(new Font("Arial", Font.BOLD, 10));
                                            b.setBounds(140,110, 130,60);
                                            b.setBackground(Color.black);
                                            b.setForeground(Color.white);
                                            b.setBorder(blackline2);
                                            b.setFocusPainted(false);
                                            b.addMouseListener(new MouseAdapter() {

                                                public void mouseEntered(MouseEvent evt) {
                                                    b.setText("7000");
                                                    b.setBackground(Color.white);
                                                    b.setForeground(Color.black);
                                                    b.setBorder(BorderFactory.createLineBorder(Color.black));
                                                    b.setFont(new Font("Arial", Font.BOLD, 30));
                                                }

                                                public void mouseExited(MouseEvent evt) {
                                                    b.setBackground(Color.black);
                                                    b.setForeground(Color.white);
                                                    b.setBorder(blackline2);
                                                    b.setText("Buy treasure place");
                                                    b.setFont(new Font("Arial", Font.BOLD, 10));
                                                }

                                                @Override
                                                public void mouseClicked(MouseEvent e) {

                                                }
                                            });
                                            b.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (player2.money > 7000){
                                                        int sw = 0;
                                                        for (int i = 0 ;i<finalSize1 && sw ==0 ; i++){
                                                            for (int j = 0 ; j<finalSize1 && sw ==0 ; j++){
                                                                if (logicBoard[i][j] == 21 || logicBoard[i][j] == 22 || logicBoard[i][j] == 23 || logicBoard[i][j] ==24 || logicBoard[i][j] ==25 || logicBoard[i][j] == 26 || logicBoard[i][j] ==27 || logicBoard[i][j] ==28){
                                                                    JOptionPane.showMessageDialog(f, "Treasure place : " + String.valueOf(i+1) + " " + String.valueOf(j+1),
                                                                            "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                    sw = 1;
                                                                }
                                                            }
                                                        }
                                                        player2.money -= 7000;
                                                    }
                                                    else {
                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                    }
                                                }
                                            });


                                            JButton b2 = new JButton("Buy weapon");
                                            b2.setFont(new Font("Arial", Font.BOLD, 15));
                                            b2.setBounds(300,110, 130,60);
                                            b2.setBackground(Color.black);
                                            b2.setForeground(Color.white);
                                            b2.setBorder(blackline2);
                                            b2.setFocusPainted(false);
                                            b2.addMouseListener(new MouseAdapter() {

                                                public void mouseEntered(MouseEvent evt) {
                                                    b2.setBackground(Color.white);
                                                    b2.setForeground(Color.black);
                                                    b2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                }

                                                public void mouseExited(MouseEvent evt) {
                                                    b2.setBackground(Color.black);
                                                    b2.setForeground(Color.white);
                                                    b2.setBorder(blackline2);
                                                }

                                                @Override
                                                public void mouseClicked(MouseEvent e) {
                                                    f.dispose();
                                                    JFrame h = new JFrame("Weapons");
                                                    h.setSize(600,400);
                                                    h.getContentPane().setBackground(new Color(16,5,47));
                                                    h.setForeground(Color.white);

                                                    JLabel l3=new JLabel("Weapons");
                                                    l3.setBounds(230, 30 , 300,60);
                                                    l3.setFont(new Font("Arial", Font.PLAIN, 30));
                                                    l3.setForeground(Color.white);

                                                    ImageIcon weapon1 = new ImageIcon("Assets/weapon3.png");
                                                    JButton w1 = new JButton();
                                                    w1.setIcon((weapon1));
                                                    w1.setBounds(50,110,120,80);
                                                    w1.setBackground(Color.black);
                                                    w1.setForeground(Color.white);
                                                    w1.setBorder(blackline2);
                                                    w1.setFocusPainted(false);
                                                    w1.addMouseListener(new MouseAdapter() {
                                                        @Override
                                                        public void mouseEntered(MouseEvent e) {
                                                            w1.setIcon(null);
                                                            w1.setText("5000");
                                                            w1.setBackground(Color.white);
                                                            w1.setForeground(Color.black);
                                                            w1.setBorder(BorderFactory.createLineBorder(Color.black));
                                                            w1.setFont(new Font("Arial", Font.BOLD, 30));
                                                        }

                                                        @Override
                                                        public void mouseExited(MouseEvent e) {
                                                            w1.setBackground(Color.black);
                                                            w1.setForeground(Color.white);
                                                            w1.setBorder(blackline2);
                                                            w1.setText(null);
                                                            w1.setIcon((weapon1));
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {
                                                            super.mouseClicked(e);
                                                        }
                                                    });
                                                    w1.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (player2.money > 5000){
                                                                JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                        "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                player2.power += 5000;
                                                                player2.money -= 5000;
                                                                System.out.println(player2.power);
                                                            }
                                                            else {
                                                                JOptionPane.showMessageDialog(f, "Your money is low",
                                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        }
                                                    });


                                                    ImageIcon weapon2 = new ImageIcon("Assets/weapon2.png");
                                                    JButton w2 = new JButton();
                                                    w2.setIcon((weapon2));
                                                    w2.setBounds(230,110,120,80);
                                                    w2.setBackground(Color.black);
                                                    w2.setForeground(Color.white);
                                                    w2.setBorder(blackline2);
                                                    w2.setFocusPainted(false);
                                                    w2.addMouseListener(new MouseAdapter() {
                                                        @Override
                                                        public void mouseEntered(MouseEvent e) {
                                                            w2.setIcon(null);
                                                            w2.setText("6000");
                                                            w2.setBackground(Color.white);
                                                            w2.setForeground(Color.black);
                                                            w2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                            w2.setFont(new Font("Arial", Font.BOLD, 30));
                                                        }

                                                        @Override
                                                        public void mouseExited(MouseEvent e) {
                                                            w2.setBackground(Color.black);
                                                            w2.setForeground(Color.white);
                                                            w2.setBorder(blackline2);
                                                            w2.setText(null);
                                                            w2.setIcon((weapon2));
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {
                                                            super.mouseClicked(e);
                                                        }
                                                    });
                                                    w2.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (player2.money > 6000){
                                                                JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                        "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                player2.power += 6000;
                                                                player2.money -= 6000;
                                                                System.out.println(player2.power);
                                                            }
                                                            else {
                                                                JOptionPane.showMessageDialog(f, "Your money is low",
                                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        }
                                                    });

                                                    ImageIcon weapon3 = new ImageIcon("Assets/weapon1.png");
                                                    JButton w3 = new JButton();
                                                    w3.setIcon((weapon3));
                                                    w3.setBounds(410,110,120,80);
                                                    w3.setBackground(Color.black);
                                                    w3.setForeground(Color.white);
                                                    w3.setBorder(blackline2);
                                                    w3.setFocusPainted(false);
                                                    w3.addMouseListener(new MouseAdapter() {
                                                        @Override
                                                        public void mouseEntered(MouseEvent e) {
                                                            w3.setIcon(null);
                                                            w3.setText("7000");
                                                            w3.setBackground(Color.white);
                                                            w3.setForeground(Color.black);
                                                            w3.setBorder(BorderFactory.createLineBorder(Color.black));
                                                            w3.setFont(new Font("Arial", Font.BOLD, 30));
                                                        }

                                                        @Override
                                                        public void mouseExited(MouseEvent e) {
                                                            w3.setBackground(Color.black);
                                                            w3.setForeground(Color.white);
                                                            w3.setBorder(blackline2);
                                                            w3.setText(null);
                                                            w3.setIcon((weapon3));
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {
                                                            super.mouseClicked(e);
                                                        }
                                                    });
                                                    w3.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (player2.money > 7000){
                                                                JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                        "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                player2.power += 7000;
                                                                player2.money -= 7000;
                                                                System.out.println(player2.power);
                                                            }
                                                            else {
                                                                JOptionPane.showMessageDialog(f, "Your money is low",
                                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        }
                                                    });

                                                    ImageIcon weapon4 = new ImageIcon("Assets/weapon4.png");
                                                    JButton w4 = new JButton();
                                                    w4.setIcon((weapon4));
                                                    w4.setBounds(50,230,120,80);
                                                    w4.setBackground(Color.black);
                                                    w4.setForeground(Color.white);
                                                    w4.setBorder(blackline2);
                                                    w4.setFocusPainted(false);
                                                    w4.addMouseListener(new MouseAdapter() {
                                                        @Override
                                                        public void mouseEntered(MouseEvent e) {
                                                            w4.setIcon(null);
                                                            w4.setText("8000");
                                                            w4.setBackground(Color.white);
                                                            w4.setForeground(Color.black);
                                                            w4.setBorder(BorderFactory.createLineBorder(Color.black));
                                                            w4.setFont(new Font("Arial", Font.BOLD, 30));
                                                        }

                                                        @Override
                                                        public void mouseExited(MouseEvent e) {
                                                            w4.setBackground(Color.black);
                                                            w4.setForeground(Color.white);
                                                            w4.setBorder(blackline2);
                                                            w4.setText(null);
                                                            w4.setIcon((weapon4));
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {
                                                            super.mouseClicked(e);
                                                        }
                                                    });
                                                    w4.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (player2.money > 8000){
                                                                JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                        "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                player2.power += 8000;
                                                                player2.money -= 8000;
                                                                System.out.println(player2.power);
                                                            }
                                                            else {
                                                                JOptionPane.showMessageDialog(f, "Your money is low",
                                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        }
                                                    });

                                                    ImageIcon weapon5 = new ImageIcon("Assets/weapon5.png");
                                                    JButton w5 = new JButton();
                                                    w5.setIcon((weapon5));
                                                    w5.setBounds(230,230,120,80);
                                                    w5.setBackground(Color.black);
                                                    w5.setForeground(Color.white);
                                                    w5.setBorder(blackline2);
                                                    w5.setFocusPainted(false);
                                                    w5.addMouseListener(new MouseAdapter() {
                                                        @Override
                                                        public void mouseEntered(MouseEvent e) {
                                                            w5.setIcon(null);
                                                            w5.setText("9000");
                                                            w5.setBackground(Color.white);
                                                            w5.setForeground(Color.black);
                                                            w5.setBorder(BorderFactory.createLineBorder(Color.black));
                                                            w5.setFont(new Font("Arial", Font.BOLD, 30));
                                                        }

                                                        @Override
                                                        public void mouseExited(MouseEvent e) {
                                                            w5.setBackground(Color.black);
                                                            w5.setForeground(Color.white);
                                                            w5.setBorder(blackline2);
                                                            w5.setText(null);
                                                            w5.setIcon((weapon5));
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {
                                                            super.mouseClicked(e);
                                                        }
                                                    });
                                                    w5.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (player2.money > 9000){
                                                                JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                        "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                player2.power += 9000;
                                                                player2.money -= 9000;
                                                                System.out.println(player2.power);
                                                            }
                                                            else {
                                                                JOptionPane.showMessageDialog(f, "Your money is low",
                                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        }
                                                    });

                                                    ImageIcon weapon6 = new ImageIcon("Assets/weapon6.png");
                                                    JButton w6 = new JButton();
                                                    w6.setIcon((weapon6));
                                                    w6.setBounds(410,230,120,80);
                                                    w6.setBackground(Color.black);
                                                    w6.setForeground(Color.white);
                                                    w6.setBorder(blackline2);
                                                    w6.setFocusPainted(false);
                                                    w6.addMouseListener(new MouseAdapter() {
                                                        @Override
                                                        public void mouseEntered(MouseEvent e) {
                                                            w6.setIcon(null);
                                                            w6.setText("10000");
                                                            w6.setBackground(Color.white);
                                                            w6.setForeground(Color.black);
                                                            w6.setBorder(BorderFactory.createLineBorder(Color.black));
                                                            w6.setFont(new Font("Arial", Font.BOLD, 30));
                                                        }

                                                        @Override
                                                        public void mouseExited(MouseEvent e) {
                                                            w6.setBackground(Color.black);
                                                            w6.setForeground(Color.white);
                                                            w6.setBorder(blackline2);
                                                            w6.setText(null);
                                                            w6.setIcon((weapon6));
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {
                                                            super.mouseClicked(e);
                                                        }
                                                    });
                                                    w6.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (player2.money > 10000){
                                                                JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                        "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                player2.power += 10000;
                                                                player2.money -= 10000;
                                                                System.out.println(player2.power);
                                                            }
                                                            else {
                                                                JOptionPane.showMessageDialog(f, "Your money is low",
                                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        }
                                                    });


                                                    h.add(w1);
                                                    h.add(w2);
                                                    h.add(w3);
                                                    h.add(w4);
                                                    h.add(w5);
                                                    h.add(w6);
                                                    h.add(l3);

                                                    h.setLocationRelativeTo(null);
                                                    h.setLayout(null);
                                                    h.setVisible(true);

                                                }

                                            });


                                            f.add(l2); f.add(b);f.add(b2);
                                            f.setSize(600,250);
                                            f.setLocationRelativeTo(null);
                                            f.setLayout(null);
                                            f.setVisible(true);
                                        }

                                        if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==5){
                                            Random rand = new Random();
                                            int money = rand.nextInt(10)+1;
                                            money *= 1000;
                                            JOptionPane.showMessageDialog(layeredPane2, "You got"+ " "+ String.valueOf(money)+" money",
                                                    "congratulations", JOptionPane.PLAIN_MESSAGE);
                                            player2.money +=money;
                                            logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]=0;
                                        }

                                        if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==6){
                                            Random rand = new Random();
                                            int trap = rand.nextInt(10)+1;
                                            trap *= 100;
                                            JOptionPane.showMessageDialog(layeredPane2, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                    "Oh no", JOptionPane.ERROR_MESSAGE);
                                            player1.money -=trap;
                                            logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]=0;
                                        }

                                        if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==99){
                                            JOptionPane.showMessageDialog(layeredPane2, "You can not return",
                                                    "Oh no", JOptionPane.ERROR_MESSAGE);
                                            arr2[xAdjustment2[0] + 1][yAdjustment2[0]].setBackground(Color.white);
                                            xAdjustment2[0] -= 1;
                                            counter -= 1;
                                            ll[0] = counter;
                                        }

                                        if (xAdjustment2[0]+1 == xAdjustment[0] && yAdjustment2[0]==yAdjustment[0]){
                                            System.out.println("Yes");
                                            if (player1.power == player2.power){
                                                JOptionPane.showMessageDialog(layeredPane2, "Player1 won.\nbeacuse Player1 is here more",
                                                        "Fight", JOptionPane.PLAIN_MESSAGE);

                                                int moneyTemp = 0;
                                                if (player1.power + player2.power != 0){
                                                    moneyTemp = ((player1.power - player2.power)/(player1.power + player2.power)) * player2.money;
                                                }
                                                player1.money += moneyTemp;
                                                player2.money -= moneyTemp;

                                                player1.power -= player2.power;
                                                player2.power = 0;

                                                uu[0] =1;
                                                arr2[xAdjustment2[0]+1][yAdjustment2[0]].setBackground(Color.white);
                                            }
                                            if (player1.power > player2.power){
                                                JOptionPane.showMessageDialog(layeredPane2, "Player1 won.\nbeacuse Player1's power is more",
                                                        "Fight", JOptionPane.PLAIN_MESSAGE);

                                                int moneyTemp = 0;
                                                if (player1.power + player2.power != 0){
                                                    moneyTemp = ((player1.power - player2.power)/(player1.power + player2.power)) * player2.money;
                                                }
                                                player1.money += moneyTemp;
                                                player2.money -= moneyTemp;

                                                player1.power -= player2.power;
                                                player2.power = 0;

                                                uu[0] =1;
                                                arr2[xAdjustment2[0]+1][yAdjustment2[0]].setBackground(Color.white);
                                            }
                                            if (player1.power < player2.power){
                                                JOptionPane.showMessageDialog(layeredPane2, "Player2 won.\nbeacuse Player2's power is more ",
                                                        "Fight", JOptionPane.PLAIN_MESSAGE);

                                                int moneyTemp = 0;
                                                if (player1.power + player2.power != 0){
                                                    moneyTemp = ((player2.power - player1.power)/(player2.power + player1.power)) * player1.money;
                                                }
                                                player2.money += moneyTemp;
                                                player1.money -= moneyTemp;

                                                player2.power -= player1.power;
                                                player1.power = 0;

                                                arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.white);
                                                xAdjustment[0] = 0;
                                                yAdjustment[0] = 0;
                                            }
                                        }
                                        if (uu[0]==0){
                                            player2.x=xAdjustment2[0] + 1;
                                            player2.y=yAdjustment2[0];

                                            xAdjustment2[0] += 1;
                                            counter += 1;
                                            ll2[0] = counter;


                                            arr2[xAdjustment2[0]-1][yAdjustment2[0]].setBackground(Color.white);
                                        }
                                        if (uu[0]==1){
                                            arr2[xAdjustment2[0]][yAdjustment2[0]].setBackground(Color.white);
                                            xAdjustment2[0] = 0;
                                            yAdjustment2[0] = finalSize-1;
                                            uu[0]=0;
                                            counter += 1;
                                            ll2[0] = counter;
                                            PLaying_Game2.setVisible(false);
                                            PLaying_Game.setVisible(true);
                                            arr2[xAdjustment2[0]][yAdjustment2[0]].setBackground(Color.red);
                                            switchPlayer[0] = 1;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;

                    case KeyEvent.VK_RIGHT:
                        if (switchPlayer[0]==2){
                            int dd= Integer.parseInt(DiceNumber2.getText());
                            int counter = ll2[0];
                            if (yy[0]==0){
                                while (counter != dd+1) {

                                    if (counter==dd){
                                        JOptionPane.showMessageDialog(layeredPane2, "Moving is more than dice number",
                                                "Error", JOptionPane.ERROR_MESSAGE);

                                        switchPlayer[0]=1;
                                        counter=0;
                                        ll2[0] = 0;

                                        for (int i = 0; i< finalSize1; i++){
                                            for (int j = 0; j< finalSize1; j++){
                                                if (logicBoard[i][j]==99){
                                                    arr2[i][j].setBackground(Color.white);
                                                    logicBoard[i][j] = 0;
                                                }
                                            }
                                        }

                                        PLaying_Game2.setVisible(false);
                                        PLaying_Game.setVisible(true);

                                        break;
                                    }
                                    else{
                                        if (yAdjustment[0] == finalSize - 1) {
                                            JOptionPane.showMessageDialog(layeredPane2, "Out of board size",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            break;
                                        }
                                        else {
                                            if (logicBoard[xAdjustment2[0]][yAdjustment2[0]+1] == 3){
                                                JOptionPane.showMessageDialog(layeredPane2, "Here is a wall",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                counter -=1;
                                            }

                                            else {
                                                arr2[xAdjustment2[0]][yAdjustment2[0] +1].setBackground(Color.red);

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0]] != 1 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=21 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=22 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=23 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=24 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=25 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=26 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=27 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=28 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=4){
                                                    logicBoard[xAdjustment2[0]][yAdjustment2[0]] = 99;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] + 1]==1){
                                                    JFrame f=new JFrame("Castle");
                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                    final JLabel label = new JLabel();
                                                    label.setBounds(80,600, 250,50);
                                                    JTextField value = new JTextField();
                                                    value.setBounds(355,265,100,30);
                                                    JLabel l2=new JLabel("Enter your treasure Code:");
                                                    l2.setBounds(120,250, 300,60);
                                                    l2.setFont(new Font("Arial", Font.PLAIN, 20));
                                                    l2.setForeground(Color.white);
                                                    JButton b = new JButton("Done");
                                                    b.setBounds(250,320, 80,30);
                                                    f.add(value); f.add(label); f.add(l2); f.add(b);
                                                    f.setSize(600,600);
                                                    f.setLayout(null);
                                                    f.setVisible(true);
                                                    b.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            int index = 0;
                                                            String code = value.getText();
                                                            int sw = 0;
                                                            for (int i = 0; i< treasureCodes[0].length ; i++){
                                                                if (treasureCodes[0][i]==Integer.parseInt(code)){
                                                                    sw =1;
                                                                    index = i;
                                                                    break;
                                                                }
                                                            }
                                                            if (sw==1){
                                                                info.treasureCounter +=1;
                                                                for(int i = 0; i< 10 ; i++){
                                                                    System.out.println(treasureCodes[0][i]);
                                                                }
                                                                String namme = null;
                                                                int name =  treasureCodes[0][index-1];
                                                                int temp1 = treasureCodes[0][index+1];
                                                                int temp2 = treasureCodes[0][index+2];
                                                                logicBoard[temp1][temp2] = 0;
                                                                treasureCodes[0] =removeTheElement(treasureCodes[0],index);
                                                                System.out.println("-------------");
                                                                for(int i = 0; i< 10 ; i++){
                                                                    System.out.println(treasureCodes[0][i]);
                                                                }
                                                                if (name==21){
                                                                    namme = "Diamond ring";
                                                                }
                                                                if (name==22){
                                                                    namme = "Jeweled sword";
                                                                }
                                                                if (name==23){
                                                                    namme = "Golden glass";
                                                                }
                                                                if (name==24){
                                                                    namme = "Glass cup";
                                                                }
                                                                if (name==25){
                                                                    namme = "Wooden bow";
                                                                }
                                                                if (name==26){
                                                                    namme = "Steel shield";
                                                                }
                                                                if (name==27){
                                                                    namme = "Golden key";
                                                                }
                                                                if (name==28){
                                                                    namme = "Dragon scroll";
                                                                }
                                                                JOptionPane.showMessageDialog(f, "You have found "+namme+"\n"+"Your money increased",
                                                                        "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                if(name == myQuest.quest){
                                                                    JOptionPane.showMessageDialog(f, "Quest Done",
                                                                            "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                    myQuest.questName();
                                                                }
                                                                player2.money += 100000;
                                                                player2.treasure_founded += 1;
                                                                f.setVisible(false);
                                                            if (info.treasureCounter == 8){
                                                                PLaying_Game.setVisible(false);
                                                                PLaying_Game2.setVisible(false);
                                                                if (player1.treasure_founded > player2.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player1"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                if (player2.treasure_founded > player1.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player2"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                else{
                                                                    if (player1.treasure_founded > player2.treasure_founded){
                                                                        JFrame f = new JFrame("Quest");
                                                                        f.setSize(600,300);
                                                                        f.setVisible(true);
                                                                        f.add(new Text_Animation("Draw"," "));
                                                                        f.setBackground(new Color(16,5,47));
                                                                        f.getContentPane().setForeground(new Color(16,5,47));
                                                                        f.getContentPane().setBackground(new Color(16,5,47));
                                                                    }
                                                                }
                                                            }
                                                            }
                                                            if(sw==0){
                                                                JOptionPane.showMessageDialog(f, "This is not a trasure code",
                                                                        "Treasure Confirmed", JOptionPane.ERROR_MESSAGE);
                                                                f.setVisible(false);
                                                            }
                                                        }
                                                    });
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0]+1]==21){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0]+1]==22){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0]+1]==23){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0]+1]==24){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0]+1]==25){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0]+1]==26){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0]+1]==27){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0]+1]==28){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] +1]==4){
                                                    Border blackline2 = BorderFactory.createLineBorder(Color.white);
                                                    JFrame f=new JFrame("Market");
                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                    JLabel l2=new JLabel("Market");
                                                    l2.setBounds(240, 30 , 300,60);
                                                    l2.setFont(new Font("Arial", Font.PLAIN, 30));
                                                    l2.setForeground(Color.white);
                                                    JButton b = new JButton("Buy treasure place");
                                                    b.setFont(new Font("Arial", Font.BOLD, 10));
                                                    b.setBounds(140,110, 130,60);
                                                    b.setBackground(Color.black);
                                                    b.setForeground(Color.white);
                                                    b.setBorder(blackline2);
                                                    b.setFocusPainted(false);
                                                    b.addMouseListener(new MouseAdapter() {

                                                        public void mouseEntered(MouseEvent evt) {
                                                            b.setText("7000");
                                                            b.setBackground(Color.white);
                                                            b.setForeground(Color.black);
                                                            b.setBorder(BorderFactory.createLineBorder(Color.black));
                                                            b.setFont(new Font("Arial", Font.BOLD, 30));
                                                        }

                                                        public void mouseExited(MouseEvent evt) {
                                                            b.setBackground(Color.black);
                                                            b.setForeground(Color.white);
                                                            b.setBorder(blackline2);
                                                            b.setText("Buy treasure place");
                                                            b.setFont(new Font("Arial", Font.BOLD, 10));
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {

                                                        }
                                                    });
                                                    b.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (player2.money > 7000){
                                                                int sw = 0;
                                                                for (int i = 0 ;i<finalSize1 && sw ==0 ; i++){
                                                                    for (int j = 0 ; j<finalSize1 && sw ==0 ; j++){
                                                                        if (logicBoard[i][j] == 21 || logicBoard[i][j] == 22 || logicBoard[i][j] == 23 || logicBoard[i][j] ==24 || logicBoard[i][j] ==25 || logicBoard[i][j] == 26 || logicBoard[i][j] ==27 || logicBoard[i][j] ==28){
                                                                            JOptionPane.showMessageDialog(f, "Treasure place : " + String.valueOf(i+1) + " " + String.valueOf(j+1),
                                                                                    "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                            sw = 1;
                                                                        }
                                                                    }
                                                                }
                                                                player2.money -= 7000;
                                                            }
                                                            else {
                                                                JOptionPane.showMessageDialog(f, "Your money is low",
                                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        }
                                                    });


                                                    JButton b2 = new JButton("Buy weapon");
                                                    b2.setFont(new Font("Arial", Font.BOLD, 15));
                                                    b2.setBounds(300,110, 130,60);
                                                    b2.setBackground(Color.black);
                                                    b2.setForeground(Color.white);
                                                    b2.setBorder(blackline2);
                                                    b2.setFocusPainted(false);
                                                    b2.addMouseListener(new MouseAdapter() {

                                                        public void mouseEntered(MouseEvent evt) {
                                                            b2.setBackground(Color.white);
                                                            b2.setForeground(Color.black);
                                                            b2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                        }

                                                        public void mouseExited(MouseEvent evt) {
                                                            b2.setBackground(Color.black);
                                                            b2.setForeground(Color.white);
                                                            b2.setBorder(blackline2);
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {
                                                            f.dispose();
                                                            JFrame h = new JFrame("Weapons");
                                                            h.setSize(600,400);
                                                            h.getContentPane().setBackground(new Color(16,5,47));
                                                            h.setForeground(Color.white);

                                                            JLabel l3=new JLabel("Weapons");
                                                            l3.setBounds(230, 30 , 300,60);
                                                            l3.setFont(new Font("Arial", Font.PLAIN, 30));
                                                            l3.setForeground(Color.white);

                                                            ImageIcon weapon1 = new ImageIcon("Assets/weapon3.png");
                                                            JButton w1 = new JButton();
                                                            w1.setIcon((weapon1));
                                                            w1.setBounds(50,110,120,80);
                                                            w1.setBackground(Color.black);
                                                            w1.setForeground(Color.white);
                                                            w1.setBorder(blackline2);
                                                            w1.setFocusPainted(false);
                                                            w1.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w1.setIcon(null);
                                                                    w1.setText("5000");
                                                                    w1.setBackground(Color.white);
                                                                    w1.setForeground(Color.black);
                                                                    w1.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w1.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w1.setBackground(Color.black);
                                                                    w1.setForeground(Color.white);
                                                                    w1.setBorder(blackline2);
                                                                    w1.setText(null);
                                                                    w1.setIcon((weapon1));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w1.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player2.money > 5000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player2.power += 5000;
                                                                        player2.money -= 5000;
                                                                        System.out.println(player2.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });


                                                            ImageIcon weapon2 = new ImageIcon("Assets/weapon2.png");
                                                            JButton w2 = new JButton();
                                                            w2.setIcon((weapon2));
                                                            w2.setBounds(230,110,120,80);
                                                            w2.setBackground(Color.black);
                                                            w2.setForeground(Color.white);
                                                            w2.setBorder(blackline2);
                                                            w2.setFocusPainted(false);
                                                            w2.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w2.setIcon(null);
                                                                    w2.setText("6000");
                                                                    w2.setBackground(Color.white);
                                                                    w2.setForeground(Color.black);
                                                                    w2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w2.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w2.setBackground(Color.black);
                                                                    w2.setForeground(Color.white);
                                                                    w2.setBorder(blackline2);
                                                                    w2.setText(null);
                                                                    w2.setIcon((weapon2));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w2.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player2.money > 6000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player2.power += 6000;
                                                                        player2.money -= 6000;
                                                                        System.out.println(player2.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon3 = new ImageIcon("Assets/weapon1.png");
                                                            JButton w3 = new JButton();
                                                            w3.setIcon((weapon3));
                                                            w3.setBounds(410,110,120,80);
                                                            w3.setBackground(Color.black);
                                                            w3.setForeground(Color.white);
                                                            w3.setBorder(blackline2);
                                                            w3.setFocusPainted(false);
                                                            w3.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w3.setIcon(null);
                                                                    w3.setText("7000");
                                                                    w3.setBackground(Color.white);
                                                                    w3.setForeground(Color.black);
                                                                    w3.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w3.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w3.setBackground(Color.black);
                                                                    w3.setForeground(Color.white);
                                                                    w3.setBorder(blackline2);
                                                                    w3.setText(null);
                                                                    w3.setIcon((weapon3));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w3.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player2.money > 7000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player2.power += 7000;
                                                                        player2.money -= 7000;
                                                                        System.out.println(player2.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon4 = new ImageIcon("Assets/weapon4.png");
                                                            JButton w4 = new JButton();
                                                            w4.setIcon((weapon4));
                                                            w4.setBounds(50,230,120,80);
                                                            w4.setBackground(Color.black);
                                                            w4.setForeground(Color.white);
                                                            w4.setBorder(blackline2);
                                                            w4.setFocusPainted(false);
                                                            w4.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w4.setIcon(null);
                                                                    w4.setText("8000");
                                                                    w4.setBackground(Color.white);
                                                                    w4.setForeground(Color.black);
                                                                    w4.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w4.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w4.setBackground(Color.black);
                                                                    w4.setForeground(Color.white);
                                                                    w4.setBorder(blackline2);
                                                                    w4.setText(null);
                                                                    w4.setIcon((weapon4));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w4.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player2.money > 8000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player2.power += 8000;
                                                                        player2.money -= 8000;
                                                                        System.out.println(player2.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon5 = new ImageIcon("Assets/weapon5.png");
                                                            JButton w5 = new JButton();
                                                            w5.setIcon((weapon5));
                                                            w5.setBounds(230,230,120,80);
                                                            w5.setBackground(Color.black);
                                                            w5.setForeground(Color.white);
                                                            w5.setBorder(blackline2);
                                                            w5.setFocusPainted(false);
                                                            w5.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w5.setIcon(null);
                                                                    w5.setText("9000");
                                                                    w5.setBackground(Color.white);
                                                                    w5.setForeground(Color.black);
                                                                    w5.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w5.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w5.setBackground(Color.black);
                                                                    w5.setForeground(Color.white);
                                                                    w5.setBorder(blackline2);
                                                                    w5.setText(null);
                                                                    w5.setIcon((weapon5));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w5.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player2.money > 9000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player2.power += 9000;
                                                                        player2.money -= 9000;
                                                                        System.out.println(player2.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon6 = new ImageIcon("Assets/weapon6.png");
                                                            JButton w6 = new JButton();
                                                            w6.setIcon((weapon6));
                                                            w6.setBounds(410,230,120,80);
                                                            w6.setBackground(Color.black);
                                                            w6.setForeground(Color.white);
                                                            w6.setBorder(blackline2);
                                                            w6.setFocusPainted(false);
                                                            w6.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w6.setIcon(null);
                                                                    w6.setText("10000");
                                                                    w6.setBackground(Color.white);
                                                                    w6.setForeground(Color.black);
                                                                    w6.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w6.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w6.setBackground(Color.black);
                                                                    w6.setForeground(Color.white);
                                                                    w6.setBorder(blackline2);
                                                                    w6.setText(null);
                                                                    w6.setIcon((weapon6));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w6.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player2.money > 10000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player2.power += 10000;
                                                                        player2.money -= 10000;
                                                                        System.out.println(player2.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });


                                                            h.add(w1);
                                                            h.add(w2);
                                                            h.add(w3);
                                                            h.add(w4);
                                                            h.add(w5);
                                                            h.add(w6);
                                                            h.add(l3);

                                                            h.setLocationRelativeTo(null);
                                                            h.setLayout(null);
                                                            h.setVisible(true);

                                                        }

                                                    });


                                                    f.add(l2); f.add(b);f.add(b2);
                                                    f.setSize(600,250);
                                                    f.setLocationRelativeTo(null);
                                                    f.setLayout(null);
                                                    f.setVisible(true);
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] +1]==5){
                                                    Random rand = new Random();
                                                    int money = rand.nextInt(10)+1;
                                                    money *= 1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "You got"+ " "+ String.valueOf(money)+" money",
                                                            "congratulations", JOptionPane.PLAIN_MESSAGE);
                                                    player2.money +=money;
                                                    logicBoard[xAdjustment2[0]][yAdjustment2[0] +1]=0;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] +1]==6){
                                                    Random rand = new Random();
                                                    int trap = rand.nextInt(10)+1;
                                                    trap *= 100;
                                                    JOptionPane.showMessageDialog(layeredPane2, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                            "Oh no", JOptionPane.ERROR_MESSAGE);
                                                    player1.money -=trap;
                                                    logicBoard[xAdjustment2[0]][yAdjustment2[0] +1]=0;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] +1]==99){
                                                    JOptionPane.showMessageDialog(layeredPane2, "You can not return",
                                                            "Oh no", JOptionPane.ERROR_MESSAGE);
                                                    arr2[xAdjustment2[0]][yAdjustment2[0] +1].setBackground(Color.white);
                                                    yAdjustment2[0] -= 1;
                                                    counter -= 1;
                                                    ll[0] = counter;
                                                }

                                                if (xAdjustment2[0] == xAdjustment[0] && yAdjustment2[0] + 1==yAdjustment[0]){
                                                    System.out.println("Yes");
                                                    if (player1.power == player2.power){
                                                        JOptionPane.showMessageDialog(layeredPane2, "Player1 won.\nbeacuse Player1 is here more",
                                                                "Fight", JOptionPane.PLAIN_MESSAGE);

                                                        int moneyTemp = 0;
                                                        if (player1.power + player2.power != 0){
                                                            moneyTemp = ((player1.power - player2.power)/(player1.power + player2.power)) * player2.money;
                                                        }
                                                        player1.money += moneyTemp;
                                                        player2.money -= moneyTemp;

                                                        player1.power -= player2.power;
                                                        player2.power = 0;

                                                        uu[0] =1;
                                                        arr2[xAdjustment2[0]][yAdjustment2[0] + 1].setBackground(Color.white);
                                                    }
                                                    if (player1.power > player2.power){
                                                        JOptionPane.showMessageDialog(layeredPane2, "Player1 won.\nbeacuse Player1's power is more",
                                                                "Fight", JOptionPane.PLAIN_MESSAGE);

                                                        int moneyTemp = 0;
                                                        if (player1.power + player2.power != 0){
                                                            moneyTemp = ((player1.power - player2.power)/(player1.power + player2.power)) * player2.money;
                                                        }
                                                        player1.money += moneyTemp;
                                                        player2.money -= moneyTemp;

                                                        player1.power -= player2.power;
                                                        player2.power = 0;

                                                        uu[0] =1;
                                                        arr2[xAdjustment2[0]][yAdjustment2[0] + 1].setBackground(Color.white);
                                                    }
                                                    if (player1.power < player2.power){
                                                        JOptionPane.showMessageDialog(layeredPane2, "Player2 won.\nbeacuse Player2's is more",
                                                                "Fight", JOptionPane.PLAIN_MESSAGE);

                                                        int moneyTemp = 0;
                                                        if (player1.power + player2.power != 0){
                                                            moneyTemp = ((player2.power - player1.power)/(player2.power + player1.power)) * player1.money;
                                                        }
                                                        player2.money += moneyTemp;
                                                        player1.money -= moneyTemp;

                                                        player2.power -= player1.power;
                                                        player1.power = 0;

                                                        arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.white);
                                                        xAdjustment[0] = 0;
                                                        yAdjustment[0] = 0;
                                                    }
                                                }
                                                if (uu[0]==0){
                                                    player2.x=xAdjustment2[0];
                                                    player2.y=yAdjustment2[0] + 1;

                                                    yAdjustment2[0] += 1;
                                                    counter += 1;
                                                    ll2[0] = counter;


                                                    arr2[xAdjustment2[0]][yAdjustment2[0]-1].setBackground(Color.white);
                                                }
                                                if (uu[0]==1){
                                                    arr2[xAdjustment2[0]][yAdjustment2[0]].setBackground(Color.white);
                                                    xAdjustment2[0] = 0;
                                                    yAdjustment2[0] = finalSize-1;
                                                    uu[0]=0;
                                                    counter += 1;
                                                    ll2[0] = counter;
                                                    PLaying_Game2.setVisible(false);
                                                    PLaying_Game.setVisible(true);
                                                    arr2[xAdjustment2[0]][yAdjustment2[0]].setBackground(Color.red);
                                                    switchPlayer[0] = 1;
                                                }

                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;

                    case KeyEvent.VK_LEFT:
                        if (switchPlayer[0]==2){
                            int dd= Integer.parseInt(DiceNumber2.getText());
                            int counter = ll2[0];
                            if (yy[0]==0){
                                while (counter != dd+1) {
                                    if (counter==dd){
                                        JOptionPane.showMessageDialog(layeredPane2, "Moving is more than dice number",
                                                "Error", JOptionPane.ERROR_MESSAGE);

                                        switchPlayer[0]=1;
                                        counter=0;
                                        ll2[0] = 0;

                                        for (int i = 0; i< finalSize1; i++){
                                            for (int j = 0; j< finalSize1; j++){
                                                if (logicBoard[i][j]==99){
                                                    arr2[i][j].setBackground(Color.white);
                                                    logicBoard[i][j] = 0;
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
                                        }
                                        else {
                                            if (logicBoard[xAdjustment2[0]][yAdjustment2[0]-1] == 3){
                                                JOptionPane.showMessageDialog(layeredPane2, "Here is a wall",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                counter -=1;
                                            }

                                            else {
                                                arr2[xAdjustment2[0]][yAdjustment2[0] -1].setBackground(Color.red);
                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0]] != 1 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=21 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=22 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=23 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=24 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=25 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=26 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=27 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=28 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=4){
                                                    logicBoard[xAdjustment2[0]][yAdjustment2[0]] = 99;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] -1]==1){
                                                    JFrame f=new JFrame("Castle");
                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                    final JLabel label = new JLabel();
                                                    label.setBounds(80,600, 250,50);
                                                    JTextField value = new JTextField();
                                                    value.setBounds(355,265,100,30);
                                                    JLabel l2=new JLabel("Enter your treasure Code:");
                                                    l2.setBounds(120,250, 300,60);
                                                    l2.setFont(new Font("Arial", Font.PLAIN, 20));
                                                    l2.setForeground(Color.white);
                                                    JButton b = new JButton("Done");
                                                    b.setBounds(250,320, 80,30);
                                                    f.add(value); f.add(label); f.add(l2); f.add(b);
                                                    f.setSize(600,600);
                                                    f.setLayout(null);
                                                    f.setVisible(true);
                                                    b.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            int index = 0;
                                                            String code = value.getText();
                                                            int sw = 0;
                                                            for (int i = 0; i< treasureCodes[0].length ; i++){
                                                                if (treasureCodes[0][i]==Integer.parseInt(code)){
                                                                    sw =1;
                                                                    index = i;
                                                                    break;
                                                                }
                                                            }
                                                            if (sw==1){
                                                                info.treasureCounter +=1;
                                                                for(int i = 0; i< 10 ; i++){
                                                                    System.out.println(treasureCodes[0][i]);
                                                                }
                                                                String namme = null;
                                                                int name =  treasureCodes[0][index-1];
                                                                int temp1 = treasureCodes[0][index+1];
                                                                int temp2 = treasureCodes[0][index+2];
                                                                logicBoard[temp1][temp2] = 0;
                                                                treasureCodes[0] =removeTheElement(treasureCodes[0],index);
                                                                System.out.println("-------------");
                                                                for(int i = 0; i< 10 ; i++){
                                                                    System.out.println(treasureCodes[0][i]);
                                                                }
                                                                if (name==21){
                                                                    namme = "Diamond ring";
                                                                }
                                                                if (name==22){
                                                                    namme = "Jeweled sword";
                                                                }
                                                                if (name==23){
                                                                    namme = "Golden glass";
                                                                }
                                                                if (name==24){
                                                                    namme = "Glass cup";
                                                                }
                                                                if (name==25){
                                                                    namme = "Wooden bow";
                                                                }
                                                                if (name==26){
                                                                    namme = "Steel shield";
                                                                }
                                                                if (name==27){
                                                                    namme = "Golden key";
                                                                }
                                                                if (name==28){
                                                                    namme = "Dragon scroll";
                                                                }
                                                                JOptionPane.showMessageDialog(f, "You have found "+namme+"\n"+"Your money increased",
                                                                        "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                if(name == myQuest.quest){
                                                                    JOptionPane.showMessageDialog(f, "Quest Done",
                                                                            "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                    myQuest.questName();
                                                                }
                                                                player2.money += 100000;
                                                                player2.treasure_founded +=1;
                                                                f.setVisible(false);
                                                            if (info.treasureCounter == 8){
                                                                PLaying_Game.setVisible(false);
                                                                PLaying_Game2.setVisible(false);
                                                                if (player1.treasure_founded > player2.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player1"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                if (player2.treasure_founded > player1.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player2"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                else{
                                                                    if (player1.treasure_founded > player2.treasure_founded){
                                                                        JFrame f = new JFrame("Quest");
                                                                        f.setSize(600,300);
                                                                        f.setVisible(true);
                                                                        f.add(new Text_Animation("Draw"," "));
                                                                        f.setBackground(new Color(16,5,47));
                                                                        f.getContentPane().setForeground(new Color(16,5,47));
                                                                        f.getContentPane().setBackground(new Color(16,5,47));
                                                                    }
                                                                }
                                                            }
                                                            }
                                                            if(sw==0){
                                                                JOptionPane.showMessageDialog(f, "This is not a trasure code",
                                                                        "Treasure Confirmed", JOptionPane.ERROR_MESSAGE);
                                                                f.setVisible(false);
                                                            }
                                                        }
                                                    });
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1]==21){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1]==22){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1]==23){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1]==24){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1]==25){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1]==26){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1]==28){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1]==27){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment2[0]][yAdjustment2[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment2[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment2[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] -1]==4){
                                                    Border blackline2 = BorderFactory.createLineBorder(Color.white);
                                                    JFrame f=new JFrame("Market");
                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                    JLabel l2=new JLabel("Market");
                                                    l2.setBounds(240, 30 , 300,60);
                                                    l2.setFont(new Font("Arial", Font.PLAIN, 30));
                                                    l2.setForeground(Color.white);
                                                    JButton b = new JButton("Buy treasure place");
                                                    b.setFont(new Font("Arial", Font.BOLD, 10));
                                                    b.setBounds(140,110, 130,60);
                                                    b.setBackground(Color.black);
                                                    b.setForeground(Color.white);
                                                    b.setBorder(blackline2);
                                                    b.setFocusPainted(false);
                                                    b.addMouseListener(new MouseAdapter() {

                                                        public void mouseEntered(MouseEvent evt) {
                                                            b.setText("7000");
                                                            b.setBackground(Color.white);
                                                            b.setForeground(Color.black);
                                                            b.setBorder(BorderFactory.createLineBorder(Color.black));
                                                            b.setFont(new Font("Arial", Font.BOLD, 30));
                                                        }

                                                        public void mouseExited(MouseEvent evt) {
                                                            b.setBackground(Color.black);
                                                            b.setForeground(Color.white);
                                                            b.setBorder(blackline2);
                                                            b.setText("Buy treasure place");
                                                            b.setFont(new Font("Arial", Font.BOLD, 10));
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {

                                                        }
                                                    });
                                                    b.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (player2.money > 7000){
                                                                int sw = 0;
                                                                for (int i = 0 ;i<finalSize1 && sw ==0 ; i++){
                                                                    for (int j = 0 ; j<finalSize1 && sw ==0 ; j++){
                                                                        if (logicBoard[i][j] == 21 || logicBoard[i][j] == 22 || logicBoard[i][j] == 23 || logicBoard[i][j] ==24 || logicBoard[i][j] ==25 || logicBoard[i][j] == 26 || logicBoard[i][j] ==27 || logicBoard[i][j] ==28){
                                                                            JOptionPane.showMessageDialog(f, "Treasure place : " + String.valueOf(i+1) + " " + String.valueOf(j+1),
                                                                                    "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                            sw = 1;
                                                                        }
                                                                    }
                                                                }
                                                                player2.money -= 7000;
                                                            }
                                                            else {
                                                                JOptionPane.showMessageDialog(f, "Your money is low",
                                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        }
                                                    });


                                                    JButton b2 = new JButton("Buy weapon");
                                                    b2.setFont(new Font("Arial", Font.BOLD, 15));
                                                    b2.setBounds(300,110, 130,60);
                                                    b2.setBackground(Color.black);
                                                    b2.setForeground(Color.white);
                                                    b2.setBorder(blackline2);
                                                    b2.setFocusPainted(false);
                                                    b2.addMouseListener(new MouseAdapter() {

                                                        public void mouseEntered(MouseEvent evt) {
                                                            b2.setBackground(Color.white);
                                                            b2.setForeground(Color.black);
                                                            b2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                        }

                                                        public void mouseExited(MouseEvent evt) {
                                                            b2.setBackground(Color.black);
                                                            b2.setForeground(Color.white);
                                                            b2.setBorder(blackline2);
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {
                                                            f.dispose();
                                                            JFrame h = new JFrame("Weapons");
                                                            h.setSize(600,400);
                                                            h.getContentPane().setBackground(new Color(16,5,47));
                                                            h.setForeground(Color.white);

                                                            JLabel l3=new JLabel("Weapons");
                                                            l3.setBounds(230, 30 , 300,60);
                                                            l3.setFont(new Font("Arial", Font.PLAIN, 30));
                                                            l3.setForeground(Color.white);

                                                            ImageIcon weapon1 = new ImageIcon("Assets/weapon3.png");
                                                            JButton w1 = new JButton();
                                                            w1.setIcon((weapon1));
                                                            w1.setBounds(50,110,120,80);
                                                            w1.setBackground(Color.black);
                                                            w1.setForeground(Color.white);
                                                            w1.setBorder(blackline2);
                                                            w1.setFocusPainted(false);
                                                            w1.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w1.setIcon(null);
                                                                    w1.setText("5000");
                                                                    w1.setBackground(Color.white);
                                                                    w1.setForeground(Color.black);
                                                                    w1.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w1.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w1.setBackground(Color.black);
                                                                    w1.setForeground(Color.white);
                                                                    w1.setBorder(blackline2);
                                                                    w1.setText(null);
                                                                    w1.setIcon((weapon1));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w1.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player2.money > 5000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player2.power += 5000;
                                                                        player2.money -= 5000;
                                                                        System.out.println(player2.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });


                                                            ImageIcon weapon2 = new ImageIcon("Assets/weapon2.png");
                                                            JButton w2 = new JButton();
                                                            w2.setIcon((weapon2));
                                                            w2.setBounds(230,110,120,80);
                                                            w2.setBackground(Color.black);
                                                            w2.setForeground(Color.white);
                                                            w2.setBorder(blackline2);
                                                            w2.setFocusPainted(false);
                                                            w2.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w2.setIcon(null);
                                                                    w2.setText("6000");
                                                                    w2.setBackground(Color.white);
                                                                    w2.setForeground(Color.black);
                                                                    w2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w2.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w2.setBackground(Color.black);
                                                                    w2.setForeground(Color.white);
                                                                    w2.setBorder(blackline2);
                                                                    w2.setText(null);
                                                                    w2.setIcon((weapon2));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w2.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player2.money > 6000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player2.power += 6000;
                                                                        player2.money -= 6000;
                                                                        System.out.println(player2.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon3 = new ImageIcon("Assets/weapon1.png");
                                                            JButton w3 = new JButton();
                                                            w3.setIcon((weapon3));
                                                            w3.setBounds(410,110,120,80);
                                                            w3.setBackground(Color.black);
                                                            w3.setForeground(Color.white);
                                                            w3.setBorder(blackline2);
                                                            w3.setFocusPainted(false);
                                                            w3.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w3.setIcon(null);
                                                                    w3.setText("7000");
                                                                    w3.setBackground(Color.white);
                                                                    w3.setForeground(Color.black);
                                                                    w3.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w3.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w3.setBackground(Color.black);
                                                                    w3.setForeground(Color.white);
                                                                    w3.setBorder(blackline2);
                                                                    w3.setText(null);
                                                                    w3.setIcon((weapon3));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w3.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player2.money > 7000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player2.power += 7000;
                                                                        player2.money -= 7000;
                                                                        System.out.println(player2.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon4 = new ImageIcon("Assets/weapon4.png");
                                                            JButton w4 = new JButton();
                                                            w4.setIcon((weapon4));
                                                            w4.setBounds(50,230,120,80);
                                                            w4.setBackground(Color.black);
                                                            w4.setForeground(Color.white);
                                                            w4.setBorder(blackline2);
                                                            w4.setFocusPainted(false);
                                                            w4.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w4.setIcon(null);
                                                                    w4.setText("8000");
                                                                    w4.setBackground(Color.white);
                                                                    w4.setForeground(Color.black);
                                                                    w4.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w4.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w4.setBackground(Color.black);
                                                                    w4.setForeground(Color.white);
                                                                    w4.setBorder(blackline2);
                                                                    w4.setText(null);
                                                                    w4.setIcon((weapon4));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w4.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player2.money > 8000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player2.power += 8000;
                                                                        player2.money -= 8000;
                                                                        System.out.println(player2.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon5 = new ImageIcon("Assets/weapon5.png");
                                                            JButton w5 = new JButton();
                                                            w5.setIcon((weapon5));
                                                            w5.setBounds(230,230,120,80);
                                                            w5.setBackground(Color.black);
                                                            w5.setForeground(Color.white);
                                                            w5.setBorder(blackline2);
                                                            w5.setFocusPainted(false);
                                                            w5.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w5.setIcon(null);
                                                                    w5.setText("9000");
                                                                    w5.setBackground(Color.white);
                                                                    w5.setForeground(Color.black);
                                                                    w5.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w5.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w5.setBackground(Color.black);
                                                                    w5.setForeground(Color.white);
                                                                    w5.setBorder(blackline2);
                                                                    w5.setText(null);
                                                                    w5.setIcon((weapon5));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w5.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player2.money > 9000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player2.power += 9000;
                                                                        player2.money -= 9000;
                                                                        System.out.println(player2.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon6 = new ImageIcon("Assets/weapon6.png");
                                                            JButton w6 = new JButton();
                                                            w6.setIcon((weapon6));
                                                            w6.setBounds(410,230,120,80);
                                                            w6.setBackground(Color.black);
                                                            w6.setForeground(Color.white);
                                                            w6.setBorder(blackline2);
                                                            w6.setFocusPainted(false);
                                                            w6.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w6.setIcon(null);
                                                                    w6.setText("10000");
                                                                    w6.setBackground(Color.white);
                                                                    w6.setForeground(Color.black);
                                                                    w6.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w6.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w6.setBackground(Color.black);
                                                                    w6.setForeground(Color.white);
                                                                    w6.setBorder(blackline2);
                                                                    w6.setText(null);
                                                                    w6.setIcon((weapon6));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w6.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player2.money > 10000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player2.power += 10000;
                                                                        player2.money -= 10000;
                                                                        System.out.println(player2.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });


                                                            h.add(w1);
                                                            h.add(w2);
                                                            h.add(w3);
                                                            h.add(w4);
                                                            h.add(w5);
                                                            h.add(w6);
                                                            h.add(l3);

                                                            h.setLocationRelativeTo(null);
                                                            h.setLayout(null);
                                                            h.setVisible(true);

                                                        }

                                                    });


                                                    f.add(l2); f.add(b);f.add(b2);
                                                    f.setSize(600,250);
                                                    f.setLocationRelativeTo(null);
                                                    f.setLayout(null);
                                                    f.setVisible(true);
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] -1]==5){
                                                    Random rand = new Random();
                                                    int money = rand.nextInt(10)+1;
                                                    money *= 1000;
                                                    JOptionPane.showMessageDialog(layeredPane2, "You got"+ " "+ String.valueOf(money)+" money",
                                                            "congratulations", JOptionPane.PLAIN_MESSAGE);
                                                    player2.money +=money;
                                                    logicBoard[xAdjustment2[0]][yAdjustment2[0] -1]=0;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] -1]==6){
                                                    Random rand = new Random();
                                                    int trap = rand.nextInt(10)+1;
                                                    trap *= 100;
                                                    JOptionPane.showMessageDialog(layeredPane2, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                            "Oh no", JOptionPane.ERROR_MESSAGE);
                                                    player1.money -=trap;
                                                    logicBoard[xAdjustment2[0]][yAdjustment2[0] -1]=0;
                                                }

                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0] -1]==99){
                                                    JOptionPane.showMessageDialog(layeredPane2, "You can not return",
                                                            "Oh no", JOptionPane.ERROR_MESSAGE);
                                                    arr2[xAdjustment2[0]][yAdjustment2[0] -1].setBackground(Color.white);
                                                    yAdjustment[0] += 1;
                                                    counter -= 1;
                                                    ll[0] = counter;
                                                }

                                                if (xAdjustment2[0] == xAdjustment[0] && yAdjustment2[0] - 1==yAdjustment[0]){
                                                    System.out.println("Yes");
                                                    if (player1.power == player2.power){
                                                        JOptionPane.showMessageDialog(layeredPane2, "Player1 won.\nbeacuse Player1 is here more",
                                                                "Fight", JOptionPane.PLAIN_MESSAGE);

                                                        int moneyTemp = 0;
                                                        if (player1.power + player2.power != 0){
                                                            moneyTemp = ((player1.power - player2.power)/(player1.power + player2.power)) * player2.money;
                                                        }
                                                        player1.money += moneyTemp;
                                                        player2.money -= moneyTemp;

                                                        player1.power -= player2.power;
                                                        player2.power = 0;

                                                        uu[0] =1;
                                                        arr2[xAdjustment2[0]][yAdjustment2[0] - 1].setBackground(Color.white);
                                                    }
                                                    if (player1.power > player2.power){
                                                        JOptionPane.showMessageDialog(layeredPane2, "Player1 won.\nbeacuse Player1's power is more",
                                                                "Fight", JOptionPane.PLAIN_MESSAGE);

                                                        int moneyTemp = 0;
                                                        if (player1.power + player2.power != 0){
                                                            moneyTemp = ((player1.power - player2.power)/(player1.power + player2.power)) * player2.money;
                                                        }
                                                        player1.money += moneyTemp;
                                                        player2.money -= moneyTemp;

                                                        player1.power -= player2.power;
                                                        player2.power = 0;

                                                        uu[0] =1;
                                                        arr2[xAdjustment2[0]][yAdjustment2[0] - 1].setBackground(Color.white);
                                                    }
                                                    if (player1.power < player2.power){
                                                        JOptionPane.showMessageDialog(layeredPane2, "Player2 won\nbeacuse Player2's power is more",
                                                                "Fight", JOptionPane.PLAIN_MESSAGE);

                                                        int moneyTemp = 0;
                                                        if (player1.power + player2.power != 0){
                                                            moneyTemp = ((player2.power - player1.power)/(player2.power + player1.power)) * player1.money;
                                                        }
                                                        player2.money += moneyTemp;
                                                        player1.money -= moneyTemp;

                                                        player2.power -= player1.power;
                                                        player1.power = 0;

                                                        arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.white);
                                                        xAdjustment[0] = 0;
                                                        yAdjustment[0] = 0;
                                                    }
                                                }
                                                if (uu[0]==0){
                                                    player2.x=xAdjustment2[0];
                                                    player2.y=yAdjustment2[0] - 1;

                                                    yAdjustment2[0] -= 1;
                                                    counter += 1;
                                                    ll2[0] = counter;

                                                    arr2[xAdjustment2[0]][yAdjustment2[0]+1].setBackground(Color.white);
                                                }
                                                if (uu[0]==1){
                                                    arr2[xAdjustment2[0]][yAdjustment2[0]].setBackground(Color.white);
                                                    xAdjustment2[0] = 0;
                                                    yAdjustment2[0] = finalSize-1;
                                                    uu[0]=0;
                                                    counter += 1;
                                                    ll2[0] = counter;
                                                    PLaying_Game2.setVisible(false);
                                                    PLaying_Game.setVisible(true);
                                                    arr2[xAdjustment2[0]][yAdjustment2[0]].setBackground(Color.red);
                                                    switchPlayer[0] = 1;
                                                }
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        /***************************************** Dice player 1*****************************/


        Dice.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent event) {
                    switch (event.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            if (switchPlayer[0] ==1){
                                int dd= Integer.parseInt(DiceNumber.getText());
                                int counter = ll[0];
                                while (counter != dd+1) {
                                    xAdjustment[0] = player1.x;
                                    yAdjustment[0] = player1.y;

                                    if (counter==dd){
                                        JOptionPane.showMessageDialog(layeredPane, "Moving is more than dice number",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                        counter=0;
                                        ll[0] = 0;
                                        switchPlayer[0] = 2;

                                        for (int i = 0; i< finalSize1; i++){
                                            for (int j = 0; j< finalSize1; j++){
                                                if (logicBoard[i][j]==99){
                                                    arr[i][j].setBackground(Color.white);
                                                    logicBoard[i][j] = 0;
                                                }
                                            }
                                        }
                                        yy[0] =1;
                                        PLaying_Game.setVisible(false);
                                        PLaying_Game2.setVisible(true);
                                        break;
                                    }

                                    else{
                                        if (xAdjustment[0] == 0) {
                                            JOptionPane.showMessageDialog(layeredPane, "Out of board size",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            break;
                                        }
                                        else {
                                            if (logicBoard[xAdjustment[0]-1][yAdjustment[0]] == 3){
                                                JOptionPane.showMessageDialog(layeredPane, "Here is a wall",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                counter -=1;
                                            }

                                            else {
                                                if (logicBoard[xAdjustment[0]-1][yAdjustment[0]] == 3){
                                                    JOptionPane.showMessageDialog(layeredPane, "Here is a wall",
                                                            "Error", JOptionPane.ERROR_MESSAGE);
                                                    counter -=1;
                                                }

                                                else {
                                                    arr[xAdjustment[0] - 1][yAdjustment[0]].setBackground(Color.blue);

                                                    if (logicBoard[xAdjustment[0]][yAdjustment[0]] != 1 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=21 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=22 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=23 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=24 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=25 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=26 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=27 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=28 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=4){
                                                        logicBoard[xAdjustment[0]][yAdjustment[0]] = 99;
                                                    }

                                                    if (logicBoard[xAdjustment[0] - 1][yAdjustment[0]]==1){
                                                        JFrame f=new JFrame("Castle");
                                                        f.getContentPane().setBackground(new Color(16,5,47));
                                                        final JLabel label = new JLabel();
                                                        label.setBounds(80,600, 250,50);
                                                        JTextField value = new JTextField();
                                                        value.setBounds(355,265,100,30);
                                                        JLabel l2=new JLabel("Enter your treasure Code:");
                                                        l2.setBounds(120,250, 300,60);
                                                        l2.setFont(new Font("Arial", Font.PLAIN, 20));
                                                        l2.setForeground(Color.white);
                                                        JButton b = new JButton("Done");
                                                        b.setBounds(250,320, 80,30);
                                                        f.add(value); f.add(label); f.add(l2); f.add(b);
                                                        f.setSize(600,600);
                                                        f.setLayout(null);
                                                        f.setVisible(true);
                                                        b.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent e) {
                                                                int index = 0;
                                                                String code = value.getText();
                                                                int sw = 0;
                                                                for (int i = 0; i< treasureCodes[0].length ; i++){
                                                                    if (treasureCodes[0][i]==Integer.parseInt(code)){
                                                                        sw =1;
                                                                        index = i;
                                                                        break;
                                                                    }
                                                                }
                                                                if (sw==1){
                                                                    info.treasureCounter +=1;
                                                                    for (int i = 0; i< finalSize2; i++){
                                                                        for (int j = 0 ; j<finalSize2 ; j++){
                                                                            if (logicBoard[i][j]==21 || logicBoard[i][j]==22 || logicBoard[i][j]==23 || logicBoard[i][j]==24 || logicBoard[i][j]==25 || logicBoard[i][j]==26 || logicBoard[i][j]==27 || logicBoard[i][j]==28){
                                                                                System.out.printf("--%d %d--\n" , i,j);
                                                                            }
                                                                        }
                                                                    }
                                                                    for(int i = 0; i< 10 ; i++){
                                                                        System.out.println(treasureCodes[0][i]);
                                                                    }
                                                                    String namme = null;
                                                                    int name =  treasureCodes[0][index-1];
                                                                    int temp1 = treasureCodes[0][index+1];
                                                                    int temp2 = treasureCodes[0][index+2];
                                                                    for (int i = 0; i< finalSize2; i++){
                                                                        for (int j = 0 ; j<finalSize2 ; j++){
                                                                            if (logicBoard[i][j]==21 || logicBoard[i][j]==22 || logicBoard[i][j]==23 || logicBoard[i][j]==24 || logicBoard[i][j]==25 || logicBoard[i][j]==26 || logicBoard[i][j]==27 || logicBoard[i][j]==28){
                                                                                System.out.printf("--%d %d--\n" , i,j);
                                                                            }
                                                                        }
                                                                    }
                                                                    logicBoard[temp1][temp2] = 0;
                                                                    treasureCodes[0] =removeTheElement(treasureCodes[0],index);
                                                                    System.out.println("-------------");
                                                                    for(int i = 0; i< 10 ; i++){
                                                                        System.out.println(treasureCodes[0][i]);
                                                                    }
                                                                    if (name==21){
                                                                        namme = "Diamond ring";
                                                                    }
                                                                    if (name==22){
                                                                        namme = "Jeweled sword";
                                                                    }
                                                                    if (name==23){
                                                                        namme = "Golden glass";
                                                                    }
                                                                    if (name==24){
                                                                        namme = "Glass cup";
                                                                    }
                                                                    if (name==25){
                                                                        namme = "Wooden bow";
                                                                    }
                                                                    if (name==26){
                                                                        namme = "Steel shield";
                                                                    }
                                                                    if (name==27){
                                                                        namme = "Golden key";
                                                                    }
                                                                    if (name==28){
                                                                        namme = "Dragon scroll";
                                                                    }
                                                                    JOptionPane.showMessageDialog(f, "You have found "+namme+"\n"+"Your money increased",
                                                                            "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                    if(name == myQuest.quest){
                                                                        JOptionPane.showMessageDialog(f, "Quest Done",
                                                                                "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        myQuest.questName();
                                                                    }
                                                                    player1.money += 100000;
                                                                    player1.treasure_founded += 1;
                                                                    f.setVisible(false);
                                                            if (info.treasureCounter == 8){
                                                                PLaying_Game.setVisible(false);
                                                                PLaying_Game2.setVisible(false);
                                                                if (player1.treasure_founded > player2.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player1"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                if (player2.treasure_founded > player1.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player2"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                else{
                                                                    if (player1.treasure_founded > player2.treasure_founded){
                                                                        JFrame f = new JFrame("Quest");
                                                                        f.setSize(600,300);
                                                                        f.setVisible(true);
                                                                        f.add(new Text_Animation("Draw"," "));
                                                                        f.setBackground(new Color(16,5,47));
                                                                        f.getContentPane().setForeground(new Color(16,5,47));
                                                                        f.getContentPane().setBackground(new Color(16,5,47));
                                                                    }
                                                                }
                                                            }
                                                                }
                                                                if(sw==0){
                                                                    JOptionPane.showMessageDialog(f, "This is not a trasure code",
                                                                            "Treasure Confirmed", JOptionPane.ERROR_MESSAGE);
                                                                    f.setVisible(false);
                                                                }
                                                            }
                                                        });
                                                    }

                                                    if (logicBoard[xAdjustment[0] - 1][yAdjustment[0]]==21){
                                                        Random rand = new Random();
                                                        int code = rand.nextInt(2000)+1000;
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                                "Founded", JOptionPane.ERROR_MESSAGE);
                                                        treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] - 1][yAdjustment[0]];
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = code;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = xAdjustment[0] - 1;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                        treasureIndex[0] +=1;
                                                    }
                                                    if (logicBoard[xAdjustment[0] - 1][yAdjustment[0]]==22){
                                                        Random rand = new Random();
                                                        int code = rand.nextInt(2000)+1000;
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                                "Founded", JOptionPane.ERROR_MESSAGE);
                                                        treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] - 1][yAdjustment[0]];
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = code;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = xAdjustment[0] - 1;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                        treasureIndex[0] +=1;
                                                    }
                                                    if (logicBoard[xAdjustment[0] - 1][yAdjustment[0]]==23){
                                                        Random rand = new Random();
                                                        int code = rand.nextInt(2000)+1000;
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                                "Founded", JOptionPane.ERROR_MESSAGE);
                                                        treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] - 1][yAdjustment[0]];
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = code;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = xAdjustment[0] - 1;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                        treasureIndex[0] +=1;
                                                    }
                                                    if (logicBoard[xAdjustment[0] - 1][yAdjustment[0]]==24){
                                                        Random rand = new Random();
                                                        int code = rand.nextInt(2000)+1000;
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                                "Founded", JOptionPane.ERROR_MESSAGE);
                                                        treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] - 1][yAdjustment[0]];
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = code;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = xAdjustment[0] - 1;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                        treasureIndex[0] +=1;
                                                    }
                                                    if (logicBoard[xAdjustment[0] - 1][yAdjustment[0]]==25){
                                                        Random rand = new Random();
                                                        int code = rand.nextInt(2000)+1000;
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                                "Founded", JOptionPane.ERROR_MESSAGE);
                                                        treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] - 1][yAdjustment[0]];
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = code;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = xAdjustment[0] - 1;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                        treasureIndex[0] +=1;
                                                    }
                                                    if (logicBoard[xAdjustment[0] - 1][yAdjustment[0]]==26){
                                                        Random rand = new Random();
                                                        int code = rand.nextInt(2000)+1000;
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                                "Founded", JOptionPane.ERROR_MESSAGE);
                                                        treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] - 1][yAdjustment[0]];
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = code;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = xAdjustment[0] - 1;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                        treasureIndex[0] +=1;
                                                    }
                                                    if (logicBoard[xAdjustment[0] - 1][yAdjustment[0]]==27){
                                                        Random rand = new Random();
                                                        int code = rand.nextInt(2000)+1000;
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                                "Founded", JOptionPane.ERROR_MESSAGE);
                                                        treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] - 1][yAdjustment[0]];
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = code;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = xAdjustment[0] - 1;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                        treasureIndex[0] +=1;
                                                    }
                                                    if (logicBoard[xAdjustment[0] - 1][yAdjustment[0]]==28){
                                                        Random rand = new Random();
                                                        int code = rand.nextInt(2000)+1000;
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                                "Founded", JOptionPane.ERROR_MESSAGE);
                                                        treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] - 1][yAdjustment[0]];
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = code;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = xAdjustment[0] - 1;
                                                        treasureIndex[0] +=1;
                                                        treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                        treasureIndex[0] +=1;
                                                    }

                                                    if (logicBoard[xAdjustment[0] - 1][yAdjustment[0]]==4){
                                                        Border blackline2 = BorderFactory.createLineBorder(Color.white);
                                                        JFrame f=new JFrame("Market");
                                                        f.getContentPane().setBackground(new Color(16,5,47));
                                                        JLabel l2=new JLabel("Market");
                                                        l2.setBounds(240, 30 , 300,60);
                                                        l2.setFont(new Font("Arial", Font.PLAIN, 30));
                                                        l2.setForeground(Color.white);
                                                        JButton b = new JButton("Buy treasure place");
                                                        b.setFont(new Font("Arial", Font.BOLD, 10));
                                                        b.setBounds(140,110, 130,60);
                                                        b.setBackground(Color.black);
                                                        b.setForeground(Color.white);
                                                        b.setBorder(blackline2);
                                                        b.setFocusPainted(false);
                                                        b.addMouseListener(new MouseAdapter() {

                                                            public void mouseEntered(MouseEvent evt) {
                                                                b.setText("7000");
                                                                b.setBackground(Color.white);
                                                                b.setForeground(Color.black);
                                                                b.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                b.setFont(new Font("Arial", Font.BOLD, 30));
                                                            }

                                                            public void mouseExited(MouseEvent evt) {
                                                                b.setBackground(Color.black);
                                                                b.setForeground(Color.white);
                                                                b.setBorder(blackline2);
                                                                b.setText("Buy treasure place");
                                                                b.setFont(new Font("Arial", Font.BOLD, 10));
                                                            }

                                                            @Override
                                                            public void mouseClicked(MouseEvent e) {

                                                            }
                                                        });
                                                        b.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (player1.money > 7000){
                                                                    int sw = 0;
                                                                    for (int i = 0 ;i<finalSize1 && sw ==0 ; i++){
                                                                        for (int j = 0 ; j<finalSize1 && sw ==0 ; j++){
                                                                            if (logicBoard[i][j] == 21 || logicBoard[i][j] == 22 || logicBoard[i][j] == 23 || logicBoard[i][j] ==24 || logicBoard[i][j] ==25 || logicBoard[i][j] == 26 || logicBoard[i][j] ==27 || logicBoard[i][j] ==28){
                                                                                JOptionPane.showMessageDialog(f, "Treasure place : " + String.valueOf(i+1) + " " + String.valueOf(j+1),
                                                                                        "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                                sw = 1;
                                                                            }
                                                                        }
                                                                    }
                                                                    player1.money -= 7000;
                                                                }
                                                                else {
                                                                    JOptionPane.showMessageDialog(f, "Your money is low",
                                                                            "Error", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            }
                                                        });


                                                        JButton b2 = new JButton("Buy weapon");
                                                        b2.setFont(new Font("Arial", Font.BOLD, 15));
                                                        b2.setBounds(300,110, 130,60);
                                                        b2.setBackground(Color.black);
                                                        b2.setForeground(Color.white);
                                                        b2.setBorder(blackline2);
                                                        b2.setFocusPainted(false);
                                                        b2.addMouseListener(new MouseAdapter() {

                                                            public void mouseEntered(MouseEvent evt) {
                                                                b2.setBackground(Color.white);
                                                                b2.setForeground(Color.black);
                                                                b2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                            }

                                                            public void mouseExited(MouseEvent evt) {
                                                                b2.setBackground(Color.black);
                                                                b2.setForeground(Color.white);
                                                                b2.setBorder(blackline2);
                                                            }

                                                            @Override
                                                            public void mouseClicked(MouseEvent e) {
                                                                f.dispose();
                                                                JFrame h = new JFrame("Weapons");
                                                                h.setSize(600,400);
                                                                h.getContentPane().setBackground(new Color(16,5,47));
                                                                h.setForeground(Color.white);

                                                                JLabel l3=new JLabel("Weapons");
                                                                l3.setBounds(230, 30 , 300,60);
                                                                l3.setFont(new Font("Arial", Font.PLAIN, 30));
                                                                l3.setForeground(Color.white);

                                                                ImageIcon weapon1 = new ImageIcon("Assets/weapon3.png");
                                                                JButton w1 = new JButton();
                                                                w1.setIcon((weapon1));
                                                                w1.setBounds(50,110,120,80);
                                                                w1.setBackground(Color.black);
                                                                w1.setForeground(Color.white);
                                                                w1.setBorder(blackline2);
                                                                w1.setFocusPainted(false);
                                                                w1.addMouseListener(new MouseAdapter() {
                                                                    @Override
                                                                    public void mouseEntered(MouseEvent e) {
                                                                        w1.setIcon(null);
                                                                        w1.setText("5000");
                                                                        w1.setBackground(Color.white);
                                                                        w1.setForeground(Color.black);
                                                                        w1.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                        w1.setFont(new Font("Arial", Font.BOLD, 30));
                                                                    }

                                                                    @Override
                                                                    public void mouseExited(MouseEvent e) {
                                                                        w1.setBackground(Color.black);
                                                                        w1.setForeground(Color.white);
                                                                        w1.setBorder(blackline2);
                                                                        w1.setText(null);
                                                                        w1.setIcon((weapon1));
                                                                    }

                                                                    @Override
                                                                    public void mouseClicked(MouseEvent e) {
                                                                        super.mouseClicked(e);
                                                                    }
                                                                });
                                                                w1.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        if (player1.money > 5000){
                                                                            JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                    "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                            player1.power += 5000;
                                                                            player1.money -= 5000;
                                                                            System.out.println(player1.power);
                                                                        }
                                                                        else {
                                                                            JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    }
                                                                });


                                                                ImageIcon weapon2 = new ImageIcon("Assets/weapon2.png");
                                                                JButton w2 = new JButton();
                                                                w2.setIcon((weapon2));
                                                                w2.setBounds(230,110,120,80);
                                                                w2.setBackground(Color.black);
                                                                w2.setForeground(Color.white);
                                                                w2.setBorder(blackline2);
                                                                w2.setFocusPainted(false);
                                                                w2.addMouseListener(new MouseAdapter() {
                                                                    @Override
                                                                    public void mouseEntered(MouseEvent e) {
                                                                        w2.setIcon(null);
                                                                        w2.setText("6000");
                                                                        w2.setBackground(Color.white);
                                                                        w2.setForeground(Color.black);
                                                                        w2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                        w2.setFont(new Font("Arial", Font.BOLD, 30));
                                                                    }

                                                                    @Override
                                                                    public void mouseExited(MouseEvent e) {
                                                                        w2.setBackground(Color.black);
                                                                        w2.setForeground(Color.white);
                                                                        w2.setBorder(blackline2);
                                                                        w2.setText(null);
                                                                        w2.setIcon((weapon2));
                                                                    }

                                                                    @Override
                                                                    public void mouseClicked(MouseEvent e) {
                                                                        super.mouseClicked(e);
                                                                    }
                                                                });
                                                                w2.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        if (player1.money > 6000){
                                                                            JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                    "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                            player1.power += 6000;
                                                                            player1.money -= 6000;
                                                                            System.out.println(player1.power);
                                                                        }
                                                                        else {
                                                                            JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    }
                                                                });

                                                                ImageIcon weapon3 = new ImageIcon("Assets/weapon1.png");
                                                                JButton w3 = new JButton();
                                                                w3.setIcon((weapon3));
                                                                w3.setBounds(410,110,120,80);
                                                                w3.setBackground(Color.black);
                                                                w3.setForeground(Color.white);
                                                                w3.setBorder(blackline2);
                                                                w3.setFocusPainted(false);
                                                                w3.addMouseListener(new MouseAdapter() {
                                                                    @Override
                                                                    public void mouseEntered(MouseEvent e) {
                                                                        w3.setIcon(null);
                                                                        w3.setText("7000");
                                                                        w3.setBackground(Color.white);
                                                                        w3.setForeground(Color.black);
                                                                        w3.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                        w3.setFont(new Font("Arial", Font.BOLD, 30));
                                                                    }

                                                                    @Override
                                                                    public void mouseExited(MouseEvent e) {
                                                                        w3.setBackground(Color.black);
                                                                        w3.setForeground(Color.white);
                                                                        w3.setBorder(blackline2);
                                                                        w3.setText(null);
                                                                        w3.setIcon((weapon3));
                                                                    }

                                                                    @Override
                                                                    public void mouseClicked(MouseEvent e) {
                                                                        super.mouseClicked(e);
                                                                    }
                                                                });
                                                                w3.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        if (player1.money > 7000){
                                                                            JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                    "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                            player1.power += 7000;
                                                                            player1.money -= 7000;
                                                                            System.out.println(player1.power);
                                                                        }
                                                                        else {
                                                                            JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    }
                                                                });

                                                                ImageIcon weapon4 = new ImageIcon("Assets/weapon4.png");
                                                                JButton w4 = new JButton();
                                                                w4.setIcon((weapon4));
                                                                w4.setBounds(50,230,120,80);
                                                                w4.setBackground(Color.black);
                                                                w4.setForeground(Color.white);
                                                                w4.setBorder(blackline2);
                                                                w4.setFocusPainted(false);
                                                                w4.addMouseListener(new MouseAdapter() {
                                                                    @Override
                                                                    public void mouseEntered(MouseEvent e) {
                                                                        w4.setIcon(null);
                                                                        w4.setText("8000");
                                                                        w4.setBackground(Color.white);
                                                                        w4.setForeground(Color.black);
                                                                        w4.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                        w4.setFont(new Font("Arial", Font.BOLD, 30));
                                                                    }

                                                                    @Override
                                                                    public void mouseExited(MouseEvent e) {
                                                                        w4.setBackground(Color.black);
                                                                        w4.setForeground(Color.white);
                                                                        w4.setBorder(blackline2);
                                                                        w4.setText(null);
                                                                        w4.setIcon((weapon4));
                                                                    }

                                                                    @Override
                                                                    public void mouseClicked(MouseEvent e) {
                                                                        super.mouseClicked(e);
                                                                    }
                                                                });
                                                                w4.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        if (player1.money > 8000){
                                                                            JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                    "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                            player1.power += 8000;
                                                                            player1.money -= 8000;
                                                                            System.out.println(player1.power);
                                                                        }
                                                                        else {
                                                                            JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    }
                                                                });

                                                                ImageIcon weapon5 = new ImageIcon("Assets/weapon5.png");
                                                                JButton w5 = new JButton();
                                                                w5.setIcon((weapon5));
                                                                w5.setBounds(230,230,120,80);
                                                                w5.setBackground(Color.black);
                                                                w5.setForeground(Color.white);
                                                                w5.setBorder(blackline2);
                                                                w5.setFocusPainted(false);
                                                                w5.addMouseListener(new MouseAdapter() {
                                                                    @Override
                                                                    public void mouseEntered(MouseEvent e) {
                                                                        w5.setIcon(null);
                                                                        w5.setText("9000");
                                                                        w5.setBackground(Color.white);
                                                                        w5.setForeground(Color.black);
                                                                        w5.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                        w5.setFont(new Font("Arial", Font.BOLD, 30));
                                                                    }

                                                                    @Override
                                                                    public void mouseExited(MouseEvent e) {
                                                                        w5.setBackground(Color.black);
                                                                        w5.setForeground(Color.white);
                                                                        w5.setBorder(blackline2);
                                                                        w5.setText(null);
                                                                        w5.setIcon((weapon5));
                                                                    }

                                                                    @Override
                                                                    public void mouseClicked(MouseEvent e) {
                                                                        super.mouseClicked(e);
                                                                    }
                                                                });
                                                                w5.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        if (player1.money > 9000){
                                                                            JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                    "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                            player1.power += 9000;
                                                                            player1.money -= 9000;
                                                                            System.out.println(player1.power);
                                                                        }
                                                                        else {
                                                                            JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    }
                                                                });

                                                                ImageIcon weapon6 = new ImageIcon("Assets/weapon6.png");
                                                                JButton w6 = new JButton();
                                                                w6.setIcon((weapon6));
                                                                w6.setBounds(410,230,120,80);
                                                                w6.setBackground(Color.black);
                                                                w6.setForeground(Color.white);
                                                                w6.setBorder(blackline2);
                                                                w6.setFocusPainted(false);
                                                                w6.addMouseListener(new MouseAdapter() {
                                                                    @Override
                                                                    public void mouseEntered(MouseEvent e) {
                                                                        w6.setIcon(null);
                                                                        w6.setText("10000");
                                                                        w6.setBackground(Color.white);
                                                                        w6.setForeground(Color.black);
                                                                        w6.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                        w6.setFont(new Font("Arial", Font.BOLD, 30));
                                                                    }

                                                                    @Override
                                                                    public void mouseExited(MouseEvent e) {
                                                                        w6.setBackground(Color.black);
                                                                        w6.setForeground(Color.white);
                                                                        w6.setBorder(blackline2);
                                                                        w6.setText(null);
                                                                        w6.setIcon((weapon6));
                                                                    }

                                                                    @Override
                                                                    public void mouseClicked(MouseEvent e) {
                                                                        super.mouseClicked(e);
                                                                    }
                                                                });
                                                                w6.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        if (player1.money > 10000){
                                                                            JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                    "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                            player1.power += 10000;
                                                                            player1.money -= 10000;
                                                                            System.out.println(player1.power);
                                                                        }
                                                                        else {
                                                                            JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    }
                                                                });


                                                                h.add(w1);
                                                                h.add(w2);
                                                                h.add(w3);
                                                                h.add(w4);
                                                                h.add(w5);
                                                                h.add(w6);
                                                                h.add(l3);

                                                                h.setLocationRelativeTo(null);
                                                                h.setLayout(null);
                                                                h.setVisible(true);

                                                            }

                                                        });


                                                        f.add(l2); f.add(b);f.add(b2);
                                                        f.setSize(600,250);
                                                        f.setLocationRelativeTo(null);
                                                        f.setLayout(null);
                                                        f.setVisible(true);
                                                    }

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
                                                        JOptionPane.showMessageDialog(layeredPane, "You can not return",
                                                                "Oh no", JOptionPane.ERROR_MESSAGE);
                                                        arr[xAdjustment[0] - 1][yAdjustment[0]].setBackground(Color.white);
                                                        xAdjustment[0] += 1;
                                                        counter -= 1;
                                                    }

                                                    if (xAdjustment[0] - 1 == xAdjustment2[0] && yAdjustment[0] == yAdjustment2[0]){
                                                        if (player1.power == player2.power){
                                                            JOptionPane.showMessageDialog(layeredPane2, "Player2 won\nbeacuse Player2 is here more",
                                                                    "Fight", JOptionPane.PLAIN_MESSAGE);

                                                            int moneyTemp = 0;
                                                            if (player1.power + player2.power != 0){
                                                                moneyTemp = ((player2.power - player1.power)/(player2.power + player1.power)) * player1.money;
                                                            }
                                                            player2.money += moneyTemp;
                                                            player1.money -= moneyTemp;

                                                            player2.power -= player1.power;
                                                            player1.power = 0;

                                                            uu[0] =1;
                                                            arr[xAdjustment[0] - 1][yAdjustment[0]].setBackground(Color.white);
                                                        }
                                                        if (player1.power > player2.power){
                                                            JOptionPane.showMessageDialog(layeredPane2, "Player1 won\nbeacuse Player1's power is more",
                                                                    "Fight", JOptionPane.PLAIN_MESSAGE);

                                                            int moneyTemp = 0;
                                                            if (player1.power + player2.power != 0){
                                                                moneyTemp = ((player1.power - player2.power)/(player1.power + player2.power)) * player2.money;
                                                            }
                                                            player1.money += moneyTemp;
                                                            player2.money -= moneyTemp;

                                                            player1.power -= player2.power;
                                                            player2.power = 0;

                                                            xAdjustment2[0] = 0;
                                                            yAdjustment2[0] = finalSize-1;
                                                        }
                                                        if (player1.power < player2.power){
                                                            JOptionPane.showMessageDialog(layeredPane2, "Player2 won.\nbeacuse Player2's power is more",
                                                                    "Fight", JOptionPane.PLAIN_MESSAGE);

                                                            int moneyTemp = 0;
                                                            if (player1.power + player2.power != 0){
                                                                moneyTemp = ((player2.power - player1.power)/(player2.power + player1.power)) * player1.money;
                                                            }
                                                            player2.money += moneyTemp;
                                                            player1.money -= moneyTemp;

                                                            player2.power -= player1.power;
                                                            player1.power = 0;

                                                            uu[0] = 1;
                                                            arr[xAdjustment[0] - 1][yAdjustment[0]].setBackground(Color.white);
                                                        }
                                                    }

                                                    if (uu[0]==0){
                                                        xAdjustment[0] -= 1;
                                                        counter += 1;
                                                        ll[0] = counter;
                                                        player1.x=xAdjustment[0];
                                                        player1.y=yAdjustment[0];

                                                        arr[xAdjustment[0]+1][yAdjustment[0]].setBackground(Color.white);
                                                    }
                                                    if (uu[0]==1){
                                                        arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.white);
                                                        xAdjustment[0] = 0;
                                                        yAdjustment[0] = 0;
                                                        uu[0]=0;
                                                        counter += 1;
                                                        ll[0] = counter;
                                                        PLaying_Game.setVisible(false);
                                                        PLaying_Game2.setVisible(true);
                                                        arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.blue);
                                                        switchPlayer[0] = 2;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                            break;

                        case KeyEvent.VK_DOWN:
                            if (switchPlayer[0]==1){
                                int dd= Integer.parseInt(DiceNumber.getText());
                                int counter = ll[0];
                                while (counter != dd+1) {

                                    if (counter==dd){
                                        JOptionPane.showMessageDialog(layeredPane, "Moving is more than dice number",
                                                "Error", JOptionPane.ERROR_MESSAGE);

                                        counter=0;
                                        ll[0] = 0;
                                        switchPlayer[0]=2;


                                        for (int i = 0; i< finalSize1; i++){
                                            for (int j = 0; j< finalSize1; j++){
                                                if (logicBoard[i][j]==99){
                                                    arr[i][j].setBackground(Color.white);
                                                    logicBoard[i][j] = 0;
                                                }
                                            }
                                        }
                                        PLaying_Game.setVisible(false);
                                        PLaying_Game2.setVisible(true);
                                        yy[0] = 0;
                                        break;
                                    }
                                    else{

                                        if (xAdjustment[0] == finalSize - 1) {
                                            JOptionPane.showMessageDialog(layeredPane, "Out of board size",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            break;
                                        }

                                        else {

                                            if (logicBoard[xAdjustment[0]+1][yAdjustment[0]] == 3){
                                                JOptionPane.showMessageDialog(layeredPane, "Here is a wall",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                counter -=1;
                                            }

                                            else {
                                                arr[xAdjustment[0] + 1][yAdjustment[0]].setBackground(Color.blue);

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0]] != 1 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=21 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=22 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=23 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=24 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=25 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=26 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=27 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=28 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=4){
                                                    logicBoard[xAdjustment[0]][yAdjustment[0]] = 99;
                                                }

                                                if (logicBoard[xAdjustment[0] + 1][yAdjustment[0]]==1){
                                                    JFrame f=new JFrame("Castle");
                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                    final JLabel label = new JLabel();
                                                    label.setBounds(80,600, 250,50);
                                                    JTextField value = new JTextField();
                                                    value.setBounds(355,265,100,30);
                                                    JLabel l2=new JLabel("Enter your treasure Code:");
                                                    l2.setBounds(120,250, 300,60);
                                                    l2.setFont(new Font("Arial", Font.PLAIN, 20));
                                                    l2.setForeground(Color.white);
                                                    JButton b = new JButton("Done");
                                                    b.setBounds(250,320, 80,30);
                                                    f.add(value); f.add(label); f.add(l2); f.add(b);
                                                    f.setSize(600,600);
                                                    f.setLayout(null);
                                                    f.setVisible(true);
                                                    b.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            int index = 0;
                                                            String code = value.getText();
                                                            int sw = 0;
                                                            for (int i = 0; i< treasureCodes[0].length ; i++){
                                                                if (treasureCodes[0][i]==Integer.parseInt(code)){
                                                                    sw =1;
                                                                    index = i;
                                                                    break;
                                                                }
                                                            }
                                                            if (sw==1){
                                                                info.treasureCounter +=1;
                                                                for(int i = 0; i< 10 ; i++){
                                                                    System.out.println(treasureCodes[0][i]);
                                                                }
                                                                String namme = null;
                                                                int name =  treasureCodes[0][index-1];
                                                                int temp1 = treasureCodes[0][index+1];
                                                                int temp2 = treasureCodes[0][index+2];
                                                                logicBoard[temp1][temp2] = 0;
                                                                treasureCodes[0] =removeTheElement(treasureCodes[0],index);
                                                                System.out.println("-------------");
                                                                for(int i = 0; i< 10 ; i++){
                                                                    System.out.println(treasureCodes[0][i]);
                                                                }
                                                                if (name==21){
                                                                    namme = "Diamond ring";
                                                                }
                                                                if (name==22){
                                                                    namme = "Jeweled sword";
                                                                }
                                                                if (name==23){
                                                                    namme = "Golden glass";
                                                                }
                                                                if (name==24){
                                                                    namme = "Glass cup";
                                                                }
                                                                if (name==25){
                                                                    namme = "Wooden bow";
                                                                }
                                                                if (name==26){
                                                                    namme = "Steel shield";
                                                                }
                                                                if (name==27){
                                                                    namme = "Golden key";
                                                                }
                                                                if (name==28){
                                                                    namme = "Dragon scroll";
                                                                }
                                                                JOptionPane.showMessageDialog(f, "You have found "+namme+"\n"+"Your money increased",
                                                                        "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                if(name == myQuest.quest){
                                                                    JOptionPane.showMessageDialog(f, "Quest Done",
                                                                            "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                    myQuest.questName();
                                                                }
                                                                player1.money += 100000;
                                                                player1.treasure_founded += 1;
                                                                f.setVisible(false);
                                                            if (info.treasureCounter == 8){
                                                                PLaying_Game.setVisible(false);
                                                                PLaying_Game2.setVisible(false);
                                                                if (player1.treasure_founded > player2.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player1"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                if (player2.treasure_founded > player1.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player2"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                else{
                                                                    if (player1.treasure_founded > player2.treasure_founded){
                                                                        JFrame f = new JFrame("Quest");
                                                                        f.setSize(600,300);
                                                                        f.setVisible(true);
                                                                        f.add(new Text_Animation("Draw"," "));
                                                                        f.setBackground(new Color(16,5,47));
                                                                        f.getContentPane().setForeground(new Color(16,5,47));
                                                                        f.getContentPane().setBackground(new Color(16,5,47));
                                                                    }
                                                                }
                                                            }
                                                            }
                                                            if(sw==0){
                                                                JOptionPane.showMessageDialog(f, "This is not a trasure code",
                                                                        "Treasure Confirmed", JOptionPane.ERROR_MESSAGE);
                                                                f.setVisible(false);
                                                            }
                                                        }
                                                    });
                                                }

                                                if (logicBoard[xAdjustment[0] + 1][yAdjustment[0]]==21){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] + 1][yAdjustment[0]];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                }
                                                if (logicBoard[xAdjustment[0] + 1][yAdjustment[0]]==22){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] + 1][yAdjustment[0]];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                }
                                                if (logicBoard[xAdjustment[0] + 1][yAdjustment[0]]==23){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] + 1][yAdjustment[0]];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                }
                                                if (logicBoard[xAdjustment[0] + 1][yAdjustment[0]]==24){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] + 1][yAdjustment[0]];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                }
                                                if (logicBoard[xAdjustment[0] + 1][yAdjustment[0]]==25){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] + 1][yAdjustment[0]];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                }
                                                if (logicBoard[xAdjustment[0] + 1][yAdjustment[0]]==26){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] + 1][yAdjustment[0]];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                }
                                                if (logicBoard[xAdjustment[0] + 1][yAdjustment[0]]==27){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] + 1][yAdjustment[0]];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                }
                                                if (logicBoard[xAdjustment[0] + 1][yAdjustment[0]]==28){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0] + 1][yAdjustment[0]];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0] + 1][yAdjustment[0]]==4){
                                                    Border blackline2 = BorderFactory.createLineBorder(Color.white);
                                                    JFrame f=new JFrame("Market");
                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                    JLabel l2=new JLabel("Market");
                                                    l2.setBounds(240, 30 , 300,60);
                                                    l2.setFont(new Font("Arial", Font.PLAIN, 30));
                                                    l2.setForeground(Color.white);
                                                    JButton b = new JButton("Buy treasure place");
                                                    b.setFont(new Font("Arial", Font.BOLD, 10));
                                                    b.setBounds(140,110, 130,60);
                                                    b.setBackground(Color.black);
                                                    b.setForeground(Color.white);
                                                    b.setBorder(blackline2);
                                                    b.setFocusPainted(false);
                                                    b.addMouseListener(new MouseAdapter() {

                                                        public void mouseEntered(MouseEvent evt) {
                                                            b.setText("7000");
                                                            b.setBackground(Color.white);
                                                            b.setForeground(Color.black);
                                                            b.setBorder(BorderFactory.createLineBorder(Color.black));
                                                            b.setFont(new Font("Arial", Font.BOLD, 30));
                                                        }

                                                        public void mouseExited(MouseEvent evt) {
                                                            b.setBackground(Color.black);
                                                            b.setForeground(Color.white);
                                                            b.setBorder(blackline2);
                                                            b.setText("Buy treasure place");
                                                            b.setFont(new Font("Arial", Font.BOLD, 10));
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {

                                                        }
                                                    });
                                                    b.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (player1.money > 7000){
                                                                int sw = 0;
                                                                for (int i = 0 ;i<finalSize1 && sw ==0 ; i++){
                                                                    for (int j = 0 ; j<finalSize1 && sw ==0 ; j++){
                                                                        if (logicBoard[i][j] == 21 || logicBoard[i][j] == 22 || logicBoard[i][j] == 23 || logicBoard[i][j] ==24 || logicBoard[i][j] ==25 || logicBoard[i][j] == 26 || logicBoard[i][j] ==27 || logicBoard[i][j] ==28){
                                                                            JOptionPane.showMessageDialog(f, "Treasure place : " + String.valueOf(i+1) + " " + String.valueOf(j+1),
                                                                                    "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                            sw = 1;
                                                                        }
                                                                    }
                                                                }
                                                                player1.money -= 7000;
                                                            }
                                                            else {
                                                                JOptionPane.showMessageDialog(f, "Your money is low",
                                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        }
                                                    });


                                                    JButton b2 = new JButton("Buy weapon");
                                                    b2.setFont(new Font("Arial", Font.BOLD, 15));
                                                    b2.setBounds(300,110, 130,60);
                                                    b2.setBackground(Color.black);
                                                    b2.setForeground(Color.white);
                                                    b2.setBorder(blackline2);
                                                    b2.setFocusPainted(false);
                                                    b2.addMouseListener(new MouseAdapter() {

                                                        public void mouseEntered(MouseEvent evt) {
                                                            b2.setBackground(Color.white);
                                                            b2.setForeground(Color.black);
                                                            b2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                        }

                                                        public void mouseExited(MouseEvent evt) {
                                                            b2.setBackground(Color.black);
                                                            b2.setForeground(Color.white);
                                                            b2.setBorder(blackline2);
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {
                                                            f.dispose();
                                                            JFrame h = new JFrame("Weapons");
                                                            h.setSize(600,400);
                                                            h.getContentPane().setBackground(new Color(16,5,47));
                                                            h.setForeground(Color.white);

                                                            JLabel l3=new JLabel("Weapons");
                                                            l3.setBounds(230, 30 , 300,60);
                                                            l3.setFont(new Font("Arial", Font.PLAIN, 30));
                                                            l3.setForeground(Color.white);

                                                            ImageIcon weapon1 = new ImageIcon("Assets/weapon3.png");
                                                            JButton w1 = new JButton();
                                                            w1.setIcon((weapon1));
                                                            w1.setBounds(50,110,120,80);
                                                            w1.setBackground(Color.black);
                                                            w1.setForeground(Color.white);
                                                            w1.setBorder(blackline2);
                                                            w1.setFocusPainted(false);
                                                            w1.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w1.setIcon(null);
                                                                    w1.setText("5000");
                                                                    w1.setBackground(Color.white);
                                                                    w1.setForeground(Color.black);
                                                                    w1.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w1.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w1.setBackground(Color.black);
                                                                    w1.setForeground(Color.white);
                                                                    w1.setBorder(blackline2);
                                                                    w1.setText(null);
                                                                    w1.setIcon((weapon1));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w1.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 5000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 5000;
                                                                        player1.money -= 5000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });


                                                            ImageIcon weapon2 = new ImageIcon("Assets/weapon2.png");
                                                            JButton w2 = new JButton();
                                                            w2.setIcon((weapon2));
                                                            w2.setBounds(230,110,120,80);
                                                            w2.setBackground(Color.black);
                                                            w2.setForeground(Color.white);
                                                            w2.setBorder(blackline2);
                                                            w2.setFocusPainted(false);
                                                            w2.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w2.setIcon(null);
                                                                    w2.setText("6000");
                                                                    w2.setBackground(Color.white);
                                                                    w2.setForeground(Color.black);
                                                                    w2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w2.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w2.setBackground(Color.black);
                                                                    w2.setForeground(Color.white);
                                                                    w2.setBorder(blackline2);
                                                                    w2.setText(null);
                                                                    w2.setIcon((weapon2));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w2.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 6000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 6000;
                                                                        player1.money -= 6000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon3 = new ImageIcon("Assets/weapon1.png");
                                                            JButton w3 = new JButton();
                                                            w3.setIcon((weapon3));
                                                            w3.setBounds(410,110,120,80);
                                                            w3.setBackground(Color.black);
                                                            w3.setForeground(Color.white);
                                                            w3.setBorder(blackline2);
                                                            w3.setFocusPainted(false);
                                                            w3.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w3.setIcon(null);
                                                                    w3.setText("7000");
                                                                    w3.setBackground(Color.white);
                                                                    w3.setForeground(Color.black);
                                                                    w3.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w3.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w3.setBackground(Color.black);
                                                                    w3.setForeground(Color.white);
                                                                    w3.setBorder(blackline2);
                                                                    w3.setText(null);
                                                                    w3.setIcon((weapon3));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w3.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 7000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 7000;
                                                                        player1.money -= 7000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon4 = new ImageIcon("Assets/weapon4.png");
                                                            JButton w4 = new JButton();
                                                            w4.setIcon((weapon4));
                                                            w4.setBounds(50,230,120,80);
                                                            w4.setBackground(Color.black);
                                                            w4.setForeground(Color.white);
                                                            w4.setBorder(blackline2);
                                                            w4.setFocusPainted(false);
                                                            w4.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w4.setIcon(null);
                                                                    w4.setText("8000");
                                                                    w4.setBackground(Color.white);
                                                                    w4.setForeground(Color.black);
                                                                    w4.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w4.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w4.setBackground(Color.black);
                                                                    w4.setForeground(Color.white);
                                                                    w4.setBorder(blackline2);
                                                                    w4.setText(null);
                                                                    w4.setIcon((weapon4));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w4.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 8000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 8000;
                                                                        player1.money -= 8000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon5 = new ImageIcon("Assets/weapon5.png");
                                                            JButton w5 = new JButton();
                                                            w5.setIcon((weapon5));
                                                            w5.setBounds(230,230,120,80);
                                                            w5.setBackground(Color.black);
                                                            w5.setForeground(Color.white);
                                                            w5.setBorder(blackline2);
                                                            w5.setFocusPainted(false);
                                                            w5.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w5.setIcon(null);
                                                                    w5.setText("9000");
                                                                    w5.setBackground(Color.white);
                                                                    w5.setForeground(Color.black);
                                                                    w5.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w5.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w5.setBackground(Color.black);
                                                                    w5.setForeground(Color.white);
                                                                    w5.setBorder(blackline2);
                                                                    w5.setText(null);
                                                                    w5.setIcon((weapon5));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w5.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 9000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 9000;
                                                                        player1.money -= 9000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon6 = new ImageIcon("Assets/weapon6.png");
                                                            JButton w6 = new JButton();
                                                            w6.setIcon((weapon6));
                                                            w6.setBounds(410,230,120,80);
                                                            w6.setBackground(Color.black);
                                                            w6.setForeground(Color.white);
                                                            w6.setBorder(blackline2);
                                                            w6.setFocusPainted(false);
                                                            w6.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w6.setIcon(null);
                                                                    w6.setText("10000");
                                                                    w6.setBackground(Color.white);
                                                                    w6.setForeground(Color.black);
                                                                    w6.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w6.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w6.setBackground(Color.black);
                                                                    w6.setForeground(Color.white);
                                                                    w6.setBorder(blackline2);
                                                                    w6.setText(null);
                                                                    w6.setIcon((weapon6));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w6.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 10000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 10000;
                                                                        player1.money -= 10000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });


                                                            h.add(w1);
                                                            h.add(w2);
                                                            h.add(w3);
                                                            h.add(w4);
                                                            h.add(w5);
                                                            h.add(w6);
                                                            h.add(l3);

                                                            h.setLocationRelativeTo(null);
                                                            h.setLayout(null);
                                                            h.setVisible(true);

                                                        }

                                                    });


                                                    f.add(l2); f.add(b);f.add(b2);
                                                    f.setSize(600,250);
                                                    f.setLocationRelativeTo(null);
                                                    f.setLayout(null);
                                                    f.setVisible(true);
                                                }

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
                                                    JOptionPane.showMessageDialog(layeredPane, "You can not return",
                                                            "Oh no", JOptionPane.ERROR_MESSAGE);
                                                    arr[xAdjustment[0] + 1][yAdjustment[0]].setBackground(Color.white);
                                                    xAdjustment[0] -= 1;
                                                    counter -= 1;
                                                    ll[0] = counter;
                                                }

                                                if (xAdjustment[0] + 1 == xAdjustment2[0] && yAdjustment[0] == yAdjustment2[0]){
                                                    if (player1.power == player2.power){
                                                        JOptionPane.showMessageDialog(layeredPane2, "Player2 won\nbeacuse Player2 is here more",
                                                                "Fight", JOptionPane.PLAIN_MESSAGE);

                                                        int moneyTemp = 0;
                                                        if (player1.power + player2.power != 0){
                                                            moneyTemp = ((player2.power - player1.power)/(player2.power + player1.power)) * player1.money;
                                                        }
                                                        player2.money += moneyTemp;
                                                        player1.money -= moneyTemp;

                                                        player2.power -= player1.power;
                                                        player1.power = 0;

                                                        uu[0] =1;
                                                        arr[xAdjustment[0] + 1][yAdjustment[0]].setBackground(Color.white);
                                                    }
                                                    if (player1.power > player2.power){
                                                        JOptionPane.showMessageDialog(layeredPane2, "Player1 won.\nbeacuse Player1's power is more",
                                                                "Fight", JOptionPane.PLAIN_MESSAGE);

                                                        int moneyTemp = 0;
                                                        if (player1.power + player2.power != 0){
                                                            moneyTemp = ((player1.power - player2.power)/(player1.power + player2.power)) * player2.money;
                                                        }
                                                        player1.money += moneyTemp;
                                                        player2.money -= moneyTemp;

                                                        player1.power -= player2.power;
                                                        player2.power = 0;

                                                        xAdjustment2[0] = 0;
                                                        yAdjustment2[0] = finalSize-1;
                                                    }
                                                    if (player1.power < player2.power){
                                                        JOptionPane.showMessageDialog(layeredPane2, "Player2 won\nbeacuse Player2's power is more",
                                                                "Fight", JOptionPane.PLAIN_MESSAGE);

                                                        int moneyTemp = 0;
                                                        if (player1.power + player2.power != 0){
                                                            moneyTemp = ((player2.power - player1.power)/(player2.power + player1.power)) * player1.money;
                                                        }
                                                        player2.money += moneyTemp;
                                                        player1.money -= moneyTemp;

                                                        player2.power -= player1.power;
                                                        player1.power = 0;

                                                        uu[0] = 1;
                                                        arr[xAdjustment[0] + 1][yAdjustment[0]].setBackground(Color.white);
                                                    }
                                                }

                                                if (uu[0]==0){
                                                    xAdjustment[0] += 1;
                                                    counter += 1;
                                                    ll[0] = counter;

                                                    player1.x=xAdjustment[0];
                                                    player1.y=yAdjustment[0];

                                                    arr[xAdjustment[0]-1][yAdjustment[0]].setBackground(Color.white);
                                                }
                                                if (uu[0]==1){
                                                    arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.white);
                                                    xAdjustment[0] = 0;
                                                    yAdjustment[0] = 0;
                                                    uu[0]=0;
                                                    counter += 1;
                                                    ll[0] = counter;
                                                    PLaying_Game.setVisible(false);
                                                    PLaying_Game2.setVisible(true);
                                                    arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.blue);
                                                    switchPlayer[0] = 2;
                                                }

                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                            break;

                        case KeyEvent.VK_RIGHT:
                            if (switchPlayer[0]==1){
                                int dd= Integer.parseInt(DiceNumber.getText());
                                int counter = ll[0];
                                while (counter != dd+1) {
                                    xAdjustment[0]=player1.x;
                                    yAdjustment[0]=player1.y;
                                    if (counter==dd){
                                        JOptionPane.showMessageDialog(layeredPane, "Moving is more than dice number",
                                                "Error", JOptionPane.ERROR_MESSAGE);

                                        counter=0;
                                        ll[0] = 0;
                                        switchPlayer[0]=2;

                                        for (int i = 0; i< finalSize1; i++){
                                            for (int j = 0; j< finalSize1; j++){
                                                if (logicBoard[i][j]==99){
                                                    arr[i][j].setBackground(Color.white);
                                                    logicBoard[i][j] = 0;
                                                }
                                            }
                                        }
                                        yy[0] = 0;
                                        PLaying_Game.setVisible(false);
                                        PLaying_Game2.setVisible(true);
                                        break;
                                    }
                                    else{

                                        if (yAdjustment[0] == finalSize - 1) {
                                            JOptionPane.showMessageDialog(layeredPane, "Out of board size",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            break;
                                        }
                                        else {
                                            if (logicBoard[xAdjustment[0]][yAdjustment[0]+1] == 3){
                                                JOptionPane.showMessageDialog(layeredPane, "Here is a wall",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                counter -=1;
                                            }

                                            else {
                                                arr[xAdjustment[0]][yAdjustment[0] +1].setBackground(Color.blue);

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0]] != 1 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=21 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=22 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=23 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=24 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=25 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=26 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=27 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=28 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=4){
                                                    logicBoard[xAdjustment[0]][yAdjustment[0]] = 99;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] + 1]==1){
                                                    JFrame f=new JFrame("Castle");
                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                    final JLabel label = new JLabel();
                                                    label.setBounds(80,600, 250,50);
                                                    JTextField value = new JTextField();
                                                    value.setBounds(355,265,100,30);
                                                    JLabel l2=new JLabel("Enter your treasure Code:");
                                                    l2.setBounds(120,250, 300,60);
                                                    l2.setFont(new Font("Arial", Font.PLAIN, 20));
                                                    l2.setForeground(Color.white);
                                                    JButton b = new JButton("Done");
                                                    b.setBounds(250,320, 80,30);
                                                    f.add(value); f.add(label); f.add(l2); f.add(b);
                                                    f.setSize(600,600);
                                                    f.setLayout(null);
                                                    f.setVisible(true);
                                                    b.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            int index = 0;
                                                            String code = value.getText();
                                                            int sw = 0;
                                                            for (int i = 0; i< treasureCodes[0].length ; i++){
                                                                if (treasureCodes[0][i]==Integer.parseInt(code)){
                                                                    sw =1;
                                                                    index = i;
                                                                    break;
                                                                }
                                                            }
                                                            if (sw==1){
                                                                info.treasureCounter +=1;
                                                                for(int i = 0; i< 10 ; i++){
                                                                    System.out.println(treasureCodes[0][i]);
                                                                }
                                                                String namme = null;
                                                                int name =  treasureCodes[0][index-1];
                                                                int temp1 = treasureCodes[0][index+1];
                                                                int temp2 = treasureCodes[0][index+2];
                                                                logicBoard[temp1][temp2] = 0;
                                                                treasureCodes[0] =removeTheElement(treasureCodes[0],index);
                                                                System.out.println("-------------");
                                                                for(int i = 0; i< 10 ; i++){
                                                                    System.out.println(treasureCodes[0][i]);
                                                                }
                                                                if (name==21){
                                                                    namme = "Diamond ring";
                                                                }
                                                                if (name==22){
                                                                    namme = "Jeweled sword";
                                                                }
                                                                if (name==23){
                                                                    namme = "Golden glass";
                                                                }
                                                                if (name==24){
                                                                    namme = "Glass cup";
                                                                }
                                                                if (name==25){
                                                                    namme = "Wooden bow";
                                                                }
                                                                if (name==26){
                                                                    namme = "Steel shield";
                                                                }
                                                                if (name==27){
                                                                    namme = "Golden key";
                                                                }
                                                                if (name==28){
                                                                    namme = "Dragon scroll";
                                                                }
                                                                JOptionPane.showMessageDialog(f, "You have found "+namme+"\n"+"Your money increased",
                                                                        "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                if(name == myQuest.quest){
                                                                    JOptionPane.showMessageDialog(f, "Quest Done",
                                                                            "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                    myQuest.questName();
                                                                }
                                                                player1.money += 100000;
                                                                player1.treasure_founded += 1;
                                                                f.setVisible(false);
                                                            if (info.treasureCounter == 8){
                                                                PLaying_Game.setVisible(false);
                                                                PLaying_Game2.setVisible(false);
                                                                if (player1.treasure_founded > player2.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player1"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                if (player2.treasure_founded > player1.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player2"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                else{
                                                                    if (player1.treasure_founded > player2.treasure_founded){
                                                                        JFrame f = new JFrame("Quest");
                                                                        f.setSize(600,300);
                                                                        f.setVisible(true);
                                                                        f.add(new Text_Animation("Draw"," "));
                                                                        f.setBackground(new Color(16,5,47));
                                                                        f.getContentPane().setForeground(new Color(16,5,47));
                                                                        f.getContentPane().setBackground(new Color(16,5,47));
                                                                    }
                                                                }
                                                            }
                                                            }
                                                            if(sw==0){
                                                                JOptionPane.showMessageDialog(f, "This is not a trasure code",
                                                                        "Treasure Confirmed", JOptionPane.ERROR_MESSAGE);
                                                                f.setVisible(false);
                                                            }
                                                        }
                                                    });
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0]+1]==21){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0]+1]==22){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0]+1]==23){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0]+1]==24){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0]+1]==25){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0]+1]==26){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0]+1]==27){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0]+1]==28){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0]+1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] + 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] +1]==4){
                                                    Border blackline2 = BorderFactory.createLineBorder(Color.white);
                                                    JFrame f=new JFrame("Market");
                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                    JLabel l2=new JLabel("Market");
                                                    l2.setBounds(240, 30 , 300,60);
                                                    l2.setFont(new Font("Arial", Font.PLAIN, 30));
                                                    l2.setForeground(Color.white);
                                                    JButton b = new JButton("Buy treasure place");
                                                    b.setFont(new Font("Arial", Font.BOLD, 10));
                                                    b.setBounds(140,110, 130,60);
                                                    b.setBackground(Color.black);
                                                    b.setForeground(Color.white);
                                                    b.setBorder(blackline2);
                                                    b.setFocusPainted(false);
                                                    b.addMouseListener(new MouseAdapter() {

                                                        public void mouseEntered(MouseEvent evt) {
                                                            b.setText("7000");
                                                            b.setBackground(Color.white);
                                                            b.setForeground(Color.black);
                                                            b.setBorder(BorderFactory.createLineBorder(Color.black));
                                                            b.setFont(new Font("Arial", Font.BOLD, 30));
                                                        }

                                                        public void mouseExited(MouseEvent evt) {
                                                            b.setBackground(Color.black);
                                                            b.setForeground(Color.white);
                                                            b.setBorder(blackline2);
                                                            b.setText("Buy treasure place");
                                                            b.setFont(new Font("Arial", Font.BOLD, 10));
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {

                                                        }
                                                    });
                                                    b.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (player1.money > 7000){
                                                                int sw = 0;
                                                                for (int i = 0 ;i<finalSize1 && sw ==0 ; i++){
                                                                    for (int j = 0 ; j<finalSize1 && sw ==0 ; j++){
                                                                        if (logicBoard[i][j] == 21 || logicBoard[i][j] == 22 || logicBoard[i][j] == 23 || logicBoard[i][j] ==24 || logicBoard[i][j] ==25 || logicBoard[i][j] == 26 || logicBoard[i][j] ==27 || logicBoard[i][j] ==28){
                                                                            JOptionPane.showMessageDialog(f, "Treasure place : " + String.valueOf(i+1) + " " + String.valueOf(j+1),
                                                                                    "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                            sw = 1;
                                                                        }
                                                                    }
                                                                }
                                                                player1.money -= 7000;
                                                            }
                                                            else {
                                                                JOptionPane.showMessageDialog(f, "Your money is low",
                                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        }
                                                    });


                                                    JButton b2 = new JButton("Buy weapon");
                                                    b2.setFont(new Font("Arial", Font.BOLD, 15));
                                                    b2.setBounds(300,110, 130,60);
                                                    b2.setBackground(Color.black);
                                                    b2.setForeground(Color.white);
                                                    b2.setBorder(blackline2);
                                                    b2.setFocusPainted(false);
                                                    b2.addMouseListener(new MouseAdapter() {

                                                        public void mouseEntered(MouseEvent evt) {
                                                            b2.setBackground(Color.white);
                                                            b2.setForeground(Color.black);
                                                            b2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                        }

                                                        public void mouseExited(MouseEvent evt) {
                                                            b2.setBackground(Color.black);
                                                            b2.setForeground(Color.white);
                                                            b2.setBorder(blackline2);
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {
                                                            f.dispose();
                                                            JFrame h = new JFrame("Weapons");
                                                            h.setSize(600,400);
                                                            h.getContentPane().setBackground(new Color(16,5,47));
                                                            h.setForeground(Color.white);

                                                            JLabel l3=new JLabel("Weapons");
                                                            l3.setBounds(230, 30 , 300,60);
                                                            l3.setFont(new Font("Arial", Font.PLAIN, 30));
                                                            l3.setForeground(Color.white);

                                                            ImageIcon weapon1 = new ImageIcon("Assets/weapon3.png");
                                                            JButton w1 = new JButton();
                                                            w1.setIcon((weapon1));
                                                            w1.setBounds(50,110,120,80);
                                                            w1.setBackground(Color.black);
                                                            w1.setForeground(Color.white);
                                                            w1.setBorder(blackline2);
                                                            w1.setFocusPainted(false);
                                                            w1.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w1.setIcon(null);
                                                                    w1.setText("5000");
                                                                    w1.setBackground(Color.white);
                                                                    w1.setForeground(Color.black);
                                                                    w1.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w1.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w1.setBackground(Color.black);
                                                                    w1.setForeground(Color.white);
                                                                    w1.setBorder(blackline2);
                                                                    w1.setText(null);
                                                                    w1.setIcon((weapon1));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w1.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 5000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 5000;
                                                                        player1.money -= 5000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });


                                                            ImageIcon weapon2 = new ImageIcon("Assets/weapon2.png");
                                                            JButton w2 = new JButton();
                                                            w2.setIcon((weapon2));
                                                            w2.setBounds(230,110,120,80);
                                                            w2.setBackground(Color.black);
                                                            w2.setForeground(Color.white);
                                                            w2.setBorder(blackline2);
                                                            w2.setFocusPainted(false);
                                                            w2.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w2.setIcon(null);
                                                                    w2.setText("6000");
                                                                    w2.setBackground(Color.white);
                                                                    w2.setForeground(Color.black);
                                                                    w2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w2.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w2.setBackground(Color.black);
                                                                    w2.setForeground(Color.white);
                                                                    w2.setBorder(blackline2);
                                                                    w2.setText(null);
                                                                    w2.setIcon((weapon2));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w2.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 6000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 6000;
                                                                        player1.money -= 6000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon3 = new ImageIcon("Assets/weapon1.png");
                                                            JButton w3 = new JButton();
                                                            w3.setIcon((weapon3));
                                                            w3.setBounds(410,110,120,80);
                                                            w3.setBackground(Color.black);
                                                            w3.setForeground(Color.white);
                                                            w3.setBorder(blackline2);
                                                            w3.setFocusPainted(false);
                                                            w3.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w3.setIcon(null);
                                                                    w3.setText("7000");
                                                                    w3.setBackground(Color.white);
                                                                    w3.setForeground(Color.black);
                                                                    w3.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w3.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w3.setBackground(Color.black);
                                                                    w3.setForeground(Color.white);
                                                                    w3.setBorder(blackline2);
                                                                    w3.setText(null);
                                                                    w3.setIcon((weapon3));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w3.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 7000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 7000;
                                                                        player1.money -= 7000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon4 = new ImageIcon("Assets/weapon4.png");
                                                            JButton w4 = new JButton();
                                                            w4.setIcon((weapon4));
                                                            w4.setBounds(50,230,120,80);
                                                            w4.setBackground(Color.black);
                                                            w4.setForeground(Color.white);
                                                            w4.setBorder(blackline2);
                                                            w4.setFocusPainted(false);
                                                            w4.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w4.setIcon(null);
                                                                    w4.setText("8000");
                                                                    w4.setBackground(Color.white);
                                                                    w4.setForeground(Color.black);
                                                                    w4.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w4.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w4.setBackground(Color.black);
                                                                    w4.setForeground(Color.white);
                                                                    w4.setBorder(blackline2);
                                                                    w4.setText(null);
                                                                    w4.setIcon((weapon4));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w4.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 8000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 8000;
                                                                        player1.money -= 8000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon5 = new ImageIcon("Assets/weapon5.png");
                                                            JButton w5 = new JButton();
                                                            w5.setIcon((weapon5));
                                                            w5.setBounds(230,230,120,80);
                                                            w5.setBackground(Color.black);
                                                            w5.setForeground(Color.white);
                                                            w5.setBorder(blackline2);
                                                            w5.setFocusPainted(false);
                                                            w5.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w5.setIcon(null);
                                                                    w5.setText("9000");
                                                                    w5.setBackground(Color.white);
                                                                    w5.setForeground(Color.black);
                                                                    w5.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w5.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w5.setBackground(Color.black);
                                                                    w5.setForeground(Color.white);
                                                                    w5.setBorder(blackline2);
                                                                    w5.setText(null);
                                                                    w5.setIcon((weapon5));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w5.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 9000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 9000;
                                                                        player1.money -= 9000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon6 = new ImageIcon("Assets/weapon6.png");
                                                            JButton w6 = new JButton();
                                                            w6.setIcon((weapon6));
                                                            w6.setBounds(410,230,120,80);
                                                            w6.setBackground(Color.black);
                                                            w6.setForeground(Color.white);
                                                            w6.setBorder(blackline2);
                                                            w6.setFocusPainted(false);
                                                            w6.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w6.setIcon(null);
                                                                    w6.setText("10000");
                                                                    w6.setBackground(Color.white);
                                                                    w6.setForeground(Color.black);
                                                                    w6.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w6.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w6.setBackground(Color.black);
                                                                    w6.setForeground(Color.white);
                                                                    w6.setBorder(blackline2);
                                                                    w6.setText(null);
                                                                    w6.setIcon((weapon6));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w6.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 10000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 10000;
                                                                        player1.money -= 10000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });


                                                            h.add(w1);
                                                            h.add(w2);
                                                            h.add(w3);
                                                            h.add(w4);
                                                            h.add(w5);
                                                            h.add(w6);
                                                            h.add(l3);

                                                            h.setLocationRelativeTo(null);
                                                            h.setLayout(null);
                                                            h.setVisible(true);

                                                        }

                                                    });


                                                    f.add(l2); f.add(b);f.add(b2);
                                                    f.setSize(600,250);
                                                    f.setLocationRelativeTo(null);
                                                    f.setLayout(null);
                                                    f.setVisible(true);
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] +1]==5){
                                                    Random rand = new Random();
                                                    int money = rand.nextInt(10)+1;
                                                    money *= 1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "You got"+ " "+ String.valueOf(money)+" money",
                                                            "congratulations", JOptionPane.PLAIN_MESSAGE);
                                                    player1.money +=money;
                                                    logicBoard[xAdjustment[0]][yAdjustment[0] +1]=0;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] +1]==6){
                                                    Random rand = new Random();
                                                    int trap = rand.nextInt(10)+1;
                                                    trap *= 100;
                                                    JOptionPane.showMessageDialog(layeredPane, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                            "Oh no", JOptionPane.ERROR_MESSAGE);
                                                    player1.money -=trap;
                                                    logicBoard[xAdjustment[0]][yAdjustment[0] +1]=0;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] +1]==99){
                                                    JOptionPane.showMessageDialog(layeredPane, "You can not return",
                                                            "Oh no", JOptionPane.ERROR_MESSAGE);
                                                    arr[xAdjustment[0]][yAdjustment[0] +1].setBackground(Color.white);
                                                    yAdjustment[0] -= 1;
                                                    counter -= 1;
                                                    ll[0] = counter;
                                                }

                                                if (xAdjustment[0] == xAdjustment2[0] && yAdjustment[0] + 1 == yAdjustment2[0]){
                                                    if (player1.power == player2.power){
                                                        JOptionPane.showMessageDialog(layeredPane2, "Player2 won\nbeacuse Player2 is here more",
                                                                "Fight", JOptionPane.PLAIN_MESSAGE);

                                                        int moneyTemp = 0;
                                                        if (player1.power + player2.power != 0){
                                                            moneyTemp = ((player2.power - player1.power)/(player2.power + player1.power)) * player1.money;
                                                        }
                                                        player2.money += moneyTemp;
                                                        player1.money -= moneyTemp;

                                                        player2.power -= player1.power;
                                                        player1.power = 0;

                                                        uu[0] =1;
                                                        arr[xAdjustment[0]][yAdjustment[0] + 1].setBackground(Color.white);
                                                    }
                                                    if (player1.power > player2.power){
                                                        JOptionPane.showMessageDialog(layeredPane2, "Player1 won.\nbeacuse Player1's power is more",
                                                                "Fight", JOptionPane.PLAIN_MESSAGE);

                                                        int moneyTemp = 0;
                                                    if (player1.power + player2.power != 0){
                                                            moneyTemp = ((player1.power - player2.power)/(player1.power + player2.power)) * player2.money;
                                                        }
                                                        player1.money += moneyTemp;
                                                        player2.money -= moneyTemp;

                                                        player1.power -= player2.power;
                                                        player2.power = 0;

                                                        xAdjustment2[0] = 0;
                                                        yAdjustment2[0] = finalSize-1;
                                                    }
                                                    if (player1.power < player2.power){
                                                        JOptionPane.showMessageDialog(layeredPane2, "Player2 won.\nbeacuse Player2's power is more",
                                                                "Fight", JOptionPane.PLAIN_MESSAGE);

                                                        int moneyTemp = 0;
                                                        if (player1.power + player2.power != 0){
                                                            moneyTemp = ((player2.power - player1.power)/(player2.power + player1.power)) * player1.money;
                                                        }
                                                        player2.money += moneyTemp;
                                                        player1.money -= moneyTemp;

                                                        player2.power -= player1.power;
                                                        player1.power = 0;

                                                        uu[0] = 1;
                                                        arr[xAdjustment[0]][yAdjustment[0] + 1].setBackground(Color.white);
                                                    }
                                                }

                                                if (uu[0]==0){
                                                    player1.x=xAdjustment[0];
                                                    player1.y=yAdjustment[0] + 1;

                                                    yAdjustment[0] += 1;
                                                    counter += 1;
                                                    ll[0] = counter;

                                                    arr[xAdjustment[0]][yAdjustment[0]-1].setBackground(Color.white);
                                                }
                                                if (uu[0]==1){
                                                    arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.white);
                                                    xAdjustment[0] = 0;
                                                    yAdjustment[0] = 0;
                                                    uu[0]=0;
                                                    counter += 1;
                                                    ll[0] = counter;
                                                    PLaying_Game.setVisible(false);
                                                    PLaying_Game2.setVisible(true);
                                                    arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.blue);
                                                    switchPlayer[0] = 2;
                                                }
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                            break;

                        case KeyEvent.VK_LEFT:
                            if (switchPlayer[0]==1){
                                int dd= Integer.parseInt(DiceNumber.getText());
                                int counter = ll[0];
                                while (counter != dd+1) {
                                    xAdjustment[0]=player1.x;
                                    yAdjustment[0]=player1.y;
                                    if (counter==dd){
                                        JOptionPane.showMessageDialog(layeredPane, "Moving is more than dice number",
                                                "Error", JOptionPane.ERROR_MESSAGE);

                                        counter=0;
                                        ll[0] = 0;
                                        switchPlayer[0]=2;

                                        for (int i = 0; i< finalSize1; i++){
                                            for (int j = 0; j< finalSize1; j++){
                                                if (logicBoard[i][j]==99){
                                                    arr[i][j].setBackground(Color.white);
                                                    logicBoard[i][j] = 0;
                                                }
                                            }
                                        }
                                        yy[0] = 0;
                                        PLaying_Game.setVisible(false);
                                        PLaying_Game2.setVisible(true);

                                        break;
                                    }
                                    else{
                                        if (yAdjustment[0] == 0) {
                                            JOptionPane.showMessageDialog(layeredPane, "Out of board size",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            break;
                                        }
                                        else {
                                            if (logicBoard[xAdjustment[0]][yAdjustment[0]-1] == 3){
                                                JOptionPane.showMessageDialog(layeredPane, "Here is a wall",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                counter -=1;
                                            }

                                            else {
                                                arr[xAdjustment[0]][yAdjustment[0] -1].setBackground(Color.blue);
                                                if (logicBoard[xAdjustment[0]][yAdjustment[0]] != 1 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=21 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=22 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=23 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=24 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=25 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=26 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=27 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=28 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=4){
                                                    logicBoard[xAdjustment[0]][yAdjustment[0]] = 99;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] -1]==1){
                                                    JFrame f=new JFrame("Castle");
                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                    final JLabel label = new JLabel();
                                                    label.setBounds(80,600, 250,50);
                                                    JTextField value = new JTextField();
                                                    value.setBounds(355,265,100,30);
                                                    JLabel l2=new JLabel("Enter your treasure Code:");
                                                    l2.setBounds(120,250, 300,60);
                                                    l2.setFont(new Font("Arial", Font.PLAIN, 20));
                                                    l2.setForeground(Color.white);
                                                    JButton b = new JButton("Done");
                                                    b.setBounds(250,320, 80,30);
                                                    f.add(value); f.add(label); f.add(l2); f.add(b);
                                                    f.setSize(600,600);
                                                    f.setLayout(null);
                                                    f.setVisible(true);
                                                    b.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            int index = 0;
                                                            String code = value.getText();
                                                            int sw = 0;
                                                            for (int i = 0; i< treasureCodes[0].length ; i++){
                                                                if (treasureCodes[0][i]==Integer.parseInt(code)){
                                                                    sw =1;
                                                                    index = i;
                                                                    break;
                                                                }
                                                            }
                                                            if (sw==1){
                                                                info.treasureCounter +=1;
                                                                for(int i = 0; i< 10 ; i++){
                                                                    System.out.println(treasureCodes[0][i]);
                                                                }
                                                                String namme = null;
                                                                int name =  treasureCodes[0][index-1];
                                                                int temp1 = treasureCodes[0][index+1];
                                                                int temp2 = treasureCodes[0][index+2];
                                                                logicBoard[temp1][temp2] = 0;
                                                                treasureCodes[0] =removeTheElement(treasureCodes[0],index);
                                                                System.out.println("-------------");
                                                                for(int i = 0; i< 10 ; i++){
                                                                    System.out.println(treasureCodes[0][i]);
                                                                }
                                                                if (name==21){
                                                                    namme = "Diamond ring";
                                                                }
                                                                if (name==22){
                                                                    namme = "Jeweled sword";
                                                                }
                                                                if (name==23){
                                                                    namme = "Golden glass";
                                                                }
                                                                if (name==24){
                                                                    namme = "Glass cup";
                                                                }
                                                                if (name==25){
                                                                    namme = "Wooden bow";
                                                                }
                                                                if (name==26){
                                                                    namme = "Steel shield";
                                                                }
                                                                if (name==27){
                                                                    namme = "Golden key";
                                                                }
                                                                if (name==28){
                                                                    namme = "Dragon scroll";
                                                                }
                                                                JOptionPane.showMessageDialog(f, "You have found "+namme+"\n"+"Your money increased",
                                                                        "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                if(name == myQuest.quest){
                                                                    JOptionPane.showMessageDialog(f, "Quest Done",
                                                                            "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                    myQuest.questName();
                                                                }
                                                                player1.money += 100000;
                                                                player1.treasure_founded += 1;
                                                                f.setVisible(false);
                                                            if (info.treasureCounter == 8){
                                                                PLaying_Game.setVisible(false);
                                                                PLaying_Game2.setVisible(false);
                                                                if (player1.treasure_founded > player2.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player1"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                if (player2.treasure_founded > player1.treasure_founded){
                                                                    JFrame f = new JFrame("Quest");
                                                                    f.setSize(600,300);
                                                                    f.setVisible(true);
                                                                    f.add(new Text_Animation("Winner :","Player2"));
                                                                    f.setBackground(new Color(16,5,47));
                                                                    f.getContentPane().setForeground(new Color(16,5,47));
                                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                                }
                                                                else{
                                                                    if (player1.treasure_founded > player2.treasure_founded){
                                                                        JFrame f = new JFrame("Quest");
                                                                        f.setSize(600,300);
                                                                        f.setVisible(true);
                                                                        f.add(new Text_Animation("Draw"," "));
                                                                        f.setBackground(new Color(16,5,47));
                                                                        f.getContentPane().setForeground(new Color(16,5,47));
                                                                        f.getContentPane().setBackground(new Color(16,5,47));
                                                                    }
                                                                }
                                                            }
                                                            }
                                                            if(sw==0){
                                                                JOptionPane.showMessageDialog(f, "This is not a trasure code",
                                                                        "Treasure Confirmed", JOptionPane.ERROR_MESSAGE);
                                                                f.setVisible(false);
                                                            }
                                                        }
                                                    });
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] - 1]==21){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] - 1]==22){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] - 1]==23){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] - 1]==24){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] - 1]==25){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] - 1]==26){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] - 1]==28){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] - 1]==27){
                                                    Random rand = new Random();
                                                    int code = rand.nextInt(2000)+1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
                                                            "Founded", JOptionPane.ERROR_MESSAGE);
                                                    treasureCodes[0][treasureIndex[0]] = logicBoard[xAdjustment[0]][yAdjustment[0] - 1];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = code;
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = xAdjustment[0];
                                                    treasureIndex[0] +=1;
                                                    treasureCodes[0][treasureIndex[0]] = yAdjustment[0] - 1;
                                                    treasureIndex[0] +=1;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] -1]==4){
                                                    Border blackline2 = BorderFactory.createLineBorder(Color.white);
                                                    JFrame f=new JFrame("Market");
                                                    f.getContentPane().setBackground(new Color(16,5,47));
                                                    JLabel l2=new JLabel("Market");
                                                    l2.setBounds(240, 30 , 300,60);
                                                    l2.setFont(new Font("Arial", Font.PLAIN, 30));
                                                    l2.setForeground(Color.white);
                                                    JButton b = new JButton("Buy treasure place");
                                                    b.setFont(new Font("Arial", Font.BOLD, 10));
                                                    b.setBounds(140,110, 130,60);
                                                    b.setBackground(Color.black);
                                                    b.setForeground(Color.white);
                                                    b.setBorder(blackline2);
                                                    b.setFocusPainted(false);
                                                    b.addMouseListener(new MouseAdapter() {

                                                        public void mouseEntered(MouseEvent evt) {
                                                            b.setText("7000");
                                                            b.setBackground(Color.white);
                                                            b.setForeground(Color.black);
                                                            b.setBorder(BorderFactory.createLineBorder(Color.black));
                                                            b.setFont(new Font("Arial", Font.BOLD, 30));
                                                        }

                                                        public void mouseExited(MouseEvent evt) {
                                                            b.setBackground(Color.black);
                                                            b.setForeground(Color.white);
                                                            b.setBorder(blackline2);
                                                            b.setText("Buy treasure place");
                                                            b.setFont(new Font("Arial", Font.BOLD, 10));
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {

                                                        }
                                                    });
                                                    b.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (player1.money > 7000){
                                                                int sw = 0;
                                                                for (int i = 0 ;i<finalSize1 && sw ==0 ; i++){
                                                                    for (int j = 0 ; j<finalSize1 && sw ==0 ; j++){
                                                                        if (logicBoard[i][j] == 21 || logicBoard[i][j] == 22 || logicBoard[i][j] == 23 || logicBoard[i][j] ==24 || logicBoard[i][j] ==25 || logicBoard[i][j] == 26 || logicBoard[i][j] ==27 || logicBoard[i][j] ==28){
                                                                            JOptionPane.showMessageDialog(f, "Treasure place : " + String.valueOf(i+1) + " " + String.valueOf(j+1),
                                                                                    "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                            sw = 1;
                                                                        }
                                                                    }
                                                                }
                                                                player1.money -= 7000;
                                                            }
                                                            else {
                                                                JOptionPane.showMessageDialog(f, "Your money is low",
                                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        }
                                                    });


                                                    JButton b2 = new JButton("Buy weapon");
                                                    b2.setFont(new Font("Arial", Font.BOLD, 15));
                                                    b2.setBounds(300,110, 130,60);
                                                    b2.setBackground(Color.black);
                                                    b2.setForeground(Color.white);
                                                    b2.setBorder(blackline2);
                                                    b2.setFocusPainted(false);
                                                    b2.addMouseListener(new MouseAdapter() {

                                                        public void mouseEntered(MouseEvent evt) {
                                                            b2.setBackground(Color.white);
                                                            b2.setForeground(Color.black);
                                                            b2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                        }

                                                        public void mouseExited(MouseEvent evt) {
                                                            b2.setBackground(Color.black);
                                                            b2.setForeground(Color.white);
                                                            b2.setBorder(blackline2);
                                                        }

                                                        @Override
                                                        public void mouseClicked(MouseEvent e) {
                                                            f.dispose();
                                                            JFrame h = new JFrame("Weapons");
                                                            h.setSize(600,400);
                                                            h.getContentPane().setBackground(new Color(16,5,47));
                                                            h.setForeground(Color.white);

                                                            JLabel l3=new JLabel("Weapons");
                                                            l3.setBounds(230, 30 , 300,60);
                                                            l3.setFont(new Font("Arial", Font.PLAIN, 30));
                                                            l3.setForeground(Color.white);

                                                            ImageIcon weapon1 = new ImageIcon("Assets/weapon3.png");
                                                            JButton w1 = new JButton();
                                                            w1.setIcon((weapon1));
                                                            w1.setBounds(50,110,120,80);
                                                            w1.setBackground(Color.black);
                                                            w1.setForeground(Color.white);
                                                            w1.setBorder(blackline2);
                                                            w1.setFocusPainted(false);
                                                            w1.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w1.setIcon(null);
                                                                    w1.setText("5000");
                                                                    w1.setBackground(Color.white);
                                                                    w1.setForeground(Color.black);
                                                                    w1.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w1.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w1.setBackground(Color.black);
                                                                    w1.setForeground(Color.white);
                                                                    w1.setBorder(blackline2);
                                                                    w1.setText(null);
                                                                    w1.setIcon((weapon1));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w1.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 5000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 5000;
                                                                        player1.money -= 5000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });


                                                            ImageIcon weapon2 = new ImageIcon("Assets/weapon2.png");
                                                            JButton w2 = new JButton();
                                                            w2.setIcon((weapon2));
                                                            w2.setBounds(230,110,120,80);
                                                            w2.setBackground(Color.black);
                                                            w2.setForeground(Color.white);
                                                            w2.setBorder(blackline2);
                                                            w2.setFocusPainted(false);
                                                            w2.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w2.setIcon(null);
                                                                    w2.setText("6000");
                                                                    w2.setBackground(Color.white);
                                                                    w2.setForeground(Color.black);
                                                                    w2.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w2.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w2.setBackground(Color.black);
                                                                    w2.setForeground(Color.white);
                                                                    w2.setBorder(blackline2);
                                                                    w2.setText(null);
                                                                    w2.setIcon((weapon2));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w2.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 6000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 6000;
                                                                        player1.money -= 6000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon3 = new ImageIcon("Assets/weapon1.png");
                                                            JButton w3 = new JButton();
                                                            w3.setIcon((weapon3));
                                                            w3.setBounds(410,110,120,80);
                                                            w3.setBackground(Color.black);
                                                            w3.setForeground(Color.white);
                                                            w3.setBorder(blackline2);
                                                            w3.setFocusPainted(false);
                                                            w3.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w3.setIcon(null);
                                                                    w3.setText("7000");
                                                                    w3.setBackground(Color.white);
                                                                    w3.setForeground(Color.black);
                                                                    w3.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w3.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w3.setBackground(Color.black);
                                                                    w3.setForeground(Color.white);
                                                                    w3.setBorder(blackline2);
                                                                    w3.setText(null);
                                                                    w3.setIcon((weapon3));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w3.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 7000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 7000;
                                                                        player1.money -= 7000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon4 = new ImageIcon("Assets/weapon4.png");
                                                            JButton w4 = new JButton();
                                                            w4.setIcon((weapon4));
                                                            w4.setBounds(50,230,120,80);
                                                            w4.setBackground(Color.black);
                                                            w4.setForeground(Color.white);
                                                            w4.setBorder(blackline2);
                                                            w4.setFocusPainted(false);
                                                            w4.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w4.setIcon(null);
                                                                    w4.setText("8000");
                                                                    w4.setBackground(Color.white);
                                                                    w4.setForeground(Color.black);
                                                                    w4.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w4.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w4.setBackground(Color.black);
                                                                    w4.setForeground(Color.white);
                                                                    w4.setBorder(blackline2);
                                                                    w4.setText(null);
                                                                    w4.setIcon((weapon4));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w4.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 8000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 8000;
                                                                        player1.money -= 8000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon5 = new ImageIcon("Assets/weapon5.png");
                                                            JButton w5 = new JButton();
                                                            w5.setIcon((weapon5));
                                                            w5.setBounds(230,230,120,80);
                                                            w5.setBackground(Color.black);
                                                            w5.setForeground(Color.white);
                                                            w5.setBorder(blackline2);
                                                            w5.setFocusPainted(false);
                                                            w5.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w5.setIcon(null);
                                                                    w5.setText("9000");
                                                                    w5.setBackground(Color.white);
                                                                    w5.setForeground(Color.black);
                                                                    w5.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w5.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w5.setBackground(Color.black);
                                                                    w5.setForeground(Color.white);
                                                                    w5.setBorder(blackline2);
                                                                    w5.setText(null);
                                                                    w5.setIcon((weapon5));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w5.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 9000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 9000;
                                                                        player1.money -= 9000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });

                                                            ImageIcon weapon6 = new ImageIcon("Assets/weapon6.png");
                                                            JButton w6 = new JButton();
                                                            w6.setIcon((weapon6));
                                                            w6.setBounds(410,230,120,80);
                                                            w6.setBackground(Color.black);
                                                            w6.setForeground(Color.white);
                                                            w6.setBorder(blackline2);
                                                            w6.setFocusPainted(false);
                                                            w6.addMouseListener(new MouseAdapter() {
                                                                @Override
                                                                public void mouseEntered(MouseEvent e) {
                                                                    w6.setIcon(null);
                                                                    w6.setText("10000");
                                                                    w6.setBackground(Color.white);
                                                                    w6.setForeground(Color.black);
                                                                    w6.setBorder(BorderFactory.createLineBorder(Color.black));
                                                                    w6.setFont(new Font("Arial", Font.BOLD, 30));
                                                                }

                                                                @Override
                                                                public void mouseExited(MouseEvent e) {
                                                                    w6.setBackground(Color.black);
                                                                    w6.setForeground(Color.white);
                                                                    w6.setBorder(blackline2);
                                                                    w6.setText(null);
                                                                    w6.setIcon((weapon6));
                                                                }

                                                                @Override
                                                                public void mouseClicked(MouseEvent e) {
                                                                    super.mouseClicked(e);
                                                                }
                                                            });
                                                            w6.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (player1.money > 10000){
                                                                        JOptionPane.showMessageDialog(f, "Weapon bought",
                                                                                "Weapon Buying Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                        player1.power += 10000;
                                                                        player1.money -= 10000;
                                                                        System.out.println(player1.power);
                                                                    }
                                                                    else {
                                                                        JOptionPane.showMessageDialog(f, "Your money is low",
                                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                            });


                                                            h.add(w1);
                                                            h.add(w2);
                                                            h.add(w3);
                                                            h.add(w4);
                                                            h.add(w5);
                                                            h.add(w6);
                                                            h.add(l3);

                                                            h.setLocationRelativeTo(null);
                                                            h.setLayout(null);
                                                            h.setVisible(true);

                                                        }

                                                    });


                                                    f.add(l2); f.add(b);f.add(b2);
                                                    f.setSize(600,250);
                                                    f.setLocationRelativeTo(null);
                                                    f.setLayout(null);
                                                    f.setVisible(true);
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] -1]==5){
                                                    Random rand = new Random();
                                                    int money = rand.nextInt(10)+1;
                                                    money *= 1000;
                                                    JOptionPane.showMessageDialog(layeredPane, "You got"+ " "+ String.valueOf(money)+" money",
                                                            "congratulations", JOptionPane.PLAIN_MESSAGE);
                                                    player1.money +=money;
                                                    logicBoard[xAdjustment[0]][yAdjustment[0] -1]=0;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] -1]==6){
                                                    Random rand = new Random();
                                                    int trap = rand.nextInt(10)+1;
                                                    trap *= 100;
                                                    JOptionPane.showMessageDialog(layeredPane, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                            "Oh no", JOptionPane.ERROR_MESSAGE);
                                                    player1.money -=trap;
                                                    logicBoard[xAdjustment[0]][yAdjustment[0] -1]=0;
                                                }

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0] -1]==99){
                                                    JOptionPane.showMessageDialog(layeredPane, "You can not return",
                                                            "Oh no", JOptionPane.ERROR_MESSAGE);
                                                    arr[xAdjustment[0]][yAdjustment[0] -1].setBackground(Color.white);
                                                    yAdjustment[0] += 1;
                                                    counter -= 1;
                                                    ll[0] = counter;
                                                }

                                                if (xAdjustment[0] == xAdjustment2[0] && yAdjustment[0] - 1 == yAdjustment2[0]){
                                                    if (player1.power == player2.power){
                                                        JOptionPane.showMessageDialog(layeredPane2, "Player2 won.\nbeacuse Player2 is here more",
                                                                "Fight", JOptionPane.PLAIN_MESSAGE);

                                                        int moneyTemp = 0;
                                                        if (player1.power + player2.power != 0){
                                                            moneyTemp = ((player2.power - player1.power)/(player2.power + player1.power)) * player1.money;
                                                        }
                                                        player2.money += moneyTemp;
                                                        player1.money -= moneyTemp;

                                                        player2.power -= player1.power;
                                                        player1.power = 0;

                                                        uu[0] =1;
                                                        arr[xAdjustment[0]][yAdjustment[0] - 1].setBackground(Color.white);
                                                    }
                                                    if (player1.power > player2.power){
                                                        JOptionPane.showMessageDialog(layeredPane2, "Player1 won.\nbeacuse Player1's power is more",
                                                                "Fight", JOptionPane.PLAIN_MESSAGE);

                                                        int moneyTemp = 0;
                                                        if (player1.power + player2.power != 0){
                                                            moneyTemp = ((player1.power - player2.power)/(player1.power + player2.power)) * player2.money;
                                                        }
                                                        player1.money += moneyTemp;
                                                        player2.money -= moneyTemp;

                                                        player1.power -= player2.power;
                                                        player2.power = 0;

                                                        xAdjustment2[0] = 0;
                                                        yAdjustment2[0] = finalSize-1;
                                                    }
                                                    if (player1.power < player2.power){
                                                        JOptionPane.showMessageDialog(layeredPane2, "Player2 won.\nbeacuse Player2's power is more",
                                                                "Fight", JOptionPane.PLAIN_MESSAGE);

                                                        int moneyTemp = 0;
                                                        if (player1.power + player2.power != 0){
                                                            moneyTemp = ((player2.power - player1.power)/(player2.power + player1.power)) * player1.money;
                                                        }
                                                        player2.money += moneyTemp;
                                                        player1.money -= moneyTemp;

                                                        player2.power -= player1.power;
                                                        player1.power = 0;

                                                        uu[0] = 1;
                                                        arr[xAdjustment[0]][yAdjustment[0] - 1].setBackground(Color.white);
                                                    }
                                                }

                                                if (uu[0]==0){
                                                    yAdjustment[0] -= 1;
                                                    counter += 1;
                                                    ll[0] = counter;

                                                    player1.x=xAdjustment[0];
                                                    player1.y=yAdjustment[0];

                                                    arr[xAdjustment[0]][yAdjustment[0]+1].setBackground(Color.white);
                                                }
                                                if (uu[0]==1){
                                                    arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.white);
                                                    xAdjustment[0] = 0;
                                                    yAdjustment[0] = 0;
                                                    uu[0]=0;
                                                    counter += 1;
                                                    ll[0] = counter;
                                                    PLaying_Game.setVisible(false);
                                                    PLaying_Game2.setVisible(true);
                                                    arr[xAdjustment[0]][yAdjustment[0]].setBackground(Color.blue);
                                                    switchPlayer[0] = 2;
                                                }
                                            }
                                        }
                                    }
                                    break;
                                }
                            }

                            break;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });


        Scoreboard.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Scoreboard.setVisible(false);
                PLaying_Game.setVisible(true);
            }
        });

        JFrame StartingFrame = new JFrame("Traveling Salesman");

        StartingFrame.setSize(1100,750);
        StartingFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Assets/StartingWindowBackground.png")))));
        StartingFrame.setVisible(true);
        StartingFrame.setLayout(null);



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
        StartingFrame.pack();



    }
}