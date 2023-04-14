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
        final int[] xAdjustment = {0};
        final int[] yAdjustment = {0};
        final int[] xAdjustment2 = {0};
        final int[] yAdjustment2 = {0};

        final int[][] treasureCodes = {new int[50]};
        final int[] treasureIndex = {0};

        final int[] switchPlayer = {2};

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
        arr[0][size - 1].setBackground(Color.red);

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
            }
        });

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


        /******************************************************************/

        if (switchPlayer[0]==1){
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
                                        PlayerName.setText("Player2");
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

                                                    if (logicBoard[xAdjustment[0]][yAdjustment[0]] != 1 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=2 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=4){
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
                                                                    player1.money += 100000;
                                                                    for (int i = 0; i< finalSize2; i++){
                                                                        for (int j = 0 ; j<finalSize2 ; j++){
                                                                            if (logicBoard[i][j]==21 || logicBoard[i][j]==22 || logicBoard[i][j]==23 || logicBoard[i][j]==24 || logicBoard[i][j]==25 || logicBoard[i][j]==26 || logicBoard[i][j]==27 || logicBoard[i][j]==28){
                                                                                System.out.printf("--%d %d--\n" , i,j);
                                                                            }
                                                                        }
                                                                    }
                                                                    f.setVisible(false);
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


                                                    xAdjustment[0] -= 1;
                                                    counter += 1;
                                                    ll[0] = counter;
                                                    player1.x=xAdjustment[0];
                                                    player1.y=yAdjustment[0];

                                                    arr[xAdjustment[0]+1][yAdjustment[0]].setBackground(Color.white);
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

                                        PlayerName.setText("Player2");
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

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0]] != 1 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=2 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=4){
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
                                                                player1.money += 100000;
                                                                f.setVisible(false);
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

                                                xAdjustment[0] += 1;
                                                counter += 1;
                                                ll[0] = counter;

                                                player1.x=xAdjustment[0];
                                                player1.y=yAdjustment[0];

                                                arr[xAdjustment[0]-1][yAdjustment[0]].setBackground(Color.white);
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

                                        PlayerName.setText("Player2");
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

                                                if (logicBoard[xAdjustment[0]][yAdjustment[0]] != 1 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=2 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=4){
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
                                                                player1.money += 100000;
                                                                f.setVisible(false);
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

                                                yAdjustment[0] += 1;
                                                counter += 1;
                                                ll[0] = counter;

                                                player1.x=xAdjustment[0];
                                                player1.y=yAdjustment[0];

                                                arr[xAdjustment[0]][yAdjustment[0]-1].setBackground(Color.white);
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

                                        PlayerName.setText("Player2");
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
                                                if (logicBoard[xAdjustment[0]][yAdjustment[0]] != 1 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=2 && logicBoard[xAdjustment[0]][yAdjustment[0]] !=4){
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
                                                                player1.money += 100000;
                                                                f.setVisible(false);
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


                                                yAdjustment[0] -= 1;
                                                counter += 1;
                                                ll[0] = counter;

                                                player1.x=xAdjustment[0];
                                                player1.y=yAdjustment[0];

                                                arr[xAdjustment[0]][yAdjustment[0]+1].setBackground(Color.white);
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
        }
        if (switchPlayer[0]==2){
            Dice.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent event) {
                    switch (event.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            if (switchPlayer[0] ==2){
                                int dd= Integer.parseInt(DiceNumber.getText());
                                int counter = ll2[0];
                                while (counter != dd+1) {
                                    if (counter==dd){
                                        JOptionPane.showMessageDialog(layeredPane, "Moving is more than dice number",
                                                "Error", JOptionPane.ERROR_MESSAGE);

                                        switchPlayer[0]=1;
                                        PlayerName.setText("Player1");
                                        counter=0;
                                        ll2[0] = 0;

                                        for (int i = 0; i< finalSize1; i++){
                                            for (int j = 0; j< finalSize1; j++){
                                                if (logicBoard[i][j]==99){
                                                    arr[i][j].setBackground(Color.white);
                                                    logicBoard[i][j] = 0;
                                                }
                                            }
                                        }

                                        break;
                                    }

                                    else{
                                        if (xAdjustment2[0] == 0) {
                                            JOptionPane.showMessageDialog(layeredPane, "Out of board size",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                            break;
                                        }

                                        else {
                                            if (logicBoard[xAdjustment2[0]-1][yAdjustment2[0]] == 3){
                                                JOptionPane.showMessageDialog(layeredPane, "Here is a wall",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                counter -=1;
                                            }

                                            else {

                                                if (logicBoard[xAdjustment2[0]-1][yAdjustment2[0]] == 3){
                                                    JOptionPane.showMessageDialog(layeredPane, "Here is a wall",
                                                            "Error", JOptionPane.ERROR_MESSAGE);
                                                    counter -=1;
                                                }

                                                else {

                                                    arr[xAdjustment2[0] - 1][yAdjustment2[0]].setBackground(Color.red);

                                                    if (logicBoard[xAdjustment2[0]][yAdjustment2[0]] != 1 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=2 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=4){
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
                                                                            "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                                                                    player1.money += 100000;
                                                                    for (int i = 0; i< finalSize2; i++){
                                                                        for (int j = 0 ; j<finalSize2 ; j++){
                                                                            if (logicBoard[i][j]==21 || logicBoard[i][j]==22 || logicBoard[i][j]==23 || logicBoard[i][j]==24 || logicBoard[i][j]==25 || logicBoard[i][j]==26 || logicBoard[i][j]==27 || logicBoard[i][j]==28){
                                                                                System.out.printf("--%d %d--\n" , i,j);
                                                                            }
                                                                        }
                                                                    }
                                                                    f.setVisible(false);
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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

                                                    if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==5){
                                                        Random rand = new Random();
                                                        int money = rand.nextInt(10)+1;
                                                        money *= 1000;
                                                        JOptionPane.showMessageDialog(layeredPane, "You got"+ " "+ String.valueOf(money)+" money",
                                                                "congratulations", JOptionPane.PLAIN_MESSAGE);
                                                        player1.money +=money;
                                                        logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]=0;
                                                    }

                                                    if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==6){
                                                        Random rand = new Random();
                                                        int trap = rand.nextInt(10)+1;
                                                        trap *= 100;
                                                        JOptionPane.showMessageDialog(layeredPane, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                                "Oh no", JOptionPane.ERROR_MESSAGE);
                                                        player1.money -=trap;
                                                        logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]=0;
                                                    }

                                                    if (logicBoard[xAdjustment2[0] - 1][yAdjustment2[0]]==99){
                                                        JOptionPane.showMessageDialog(layeredPane, "You can not return",
                                                                "Oh no", JOptionPane.ERROR_MESSAGE);
                                                        arr[xAdjustment2[0] - 1][yAdjustment2[0]].setBackground(Color.white);
                                                        xAdjustment2[0] += 1;
                                                        counter -= 1;
                                                    }

                                                    player2.x=xAdjustment2[0] - 1;
                                                    player2.y=yAdjustment2[0];
                                                    xAdjustment2[0] -= 1;
                                                    counter += 1;
                                                    ll2[0] = counter;


                                                    yy[0] = 0;
                                                    arr[xAdjustment2[0]+1][yAdjustment2[0]].setBackground(Color.white);
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
                                int dd= Integer.parseInt(DiceNumber.getText());
                                int counter = ll[0];
                                if (yy[0]==0){
                                    while (counter != dd+1) {
                                        dd= Integer.parseInt(DiceNumber.getText());
                                        counter = ll2[0];
                                        if (counter==dd){
                                            JOptionPane.showMessageDialog(layeredPane, "Moving is more than dice number",
                                                    "Error", JOptionPane.ERROR_MESSAGE);

                                            switchPlayer[0]=1;
                                            PlayerName.setText("Player1");
                                            counter=0;
                                            ll2[0] = 0;

                                            for (int i = 0; i< finalSize1; i++){
                                                for (int j = 0; j< finalSize1; j++){
                                                    if (logicBoard[i][j]==99){
                                                        arr[i][j].setBackground(Color.white);
                                                        logicBoard[i][j] = 0;
                                                    }
                                                }
                                            }

                                            break;
                                        }
                                        else{

                                            if (xAdjustment2[0] == finalSize - 1) {
                                                JOptionPane.showMessageDialog(layeredPane, "Out of board size",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                break;
                                            }

                                            else {
                                                if (logicBoard[xAdjustment2[0]+1][yAdjustment2[0]] == 3){
                                                    JOptionPane.showMessageDialog(layeredPane, "Here is a wall",
                                                            "Error", JOptionPane.ERROR_MESSAGE);
                                                    counter -=1;
                                                }

                                                else {
                                                    arr[xAdjustment2[0] + 1][yAdjustment2[0]].setBackground(Color.red);

                                                    if (logicBoard[xAdjustment2[0]][yAdjustment2[0]] != 1 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=2 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=4){
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
                                                                    player1.money += 100000;
                                                                    f.setVisible(false);
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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

                                                    if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==5){
                                                        Random rand = new Random();
                                                        int money = rand.nextInt(10)+1;
                                                        money *= 1000;
                                                        JOptionPane.showMessageDialog(layeredPane, "You got"+ " "+ String.valueOf(money)+" money",
                                                                "congratulations", JOptionPane.PLAIN_MESSAGE);
                                                        player1.money +=money;
                                                        logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]=0;
                                                    }

                                                    if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==6){
                                                        Random rand = new Random();
                                                        int trap = rand.nextInt(10)+1;
                                                        trap *= 100;
                                                        JOptionPane.showMessageDialog(layeredPane, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                                "Oh no", JOptionPane.ERROR_MESSAGE);
                                                        player1.money -=trap;
                                                        logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]=0;
                                                    }

                                                    if (logicBoard[xAdjustment2[0] + 1][yAdjustment2[0]]==99){
                                                        JOptionPane.showMessageDialog(layeredPane, "You can not return",
                                                                "Oh no", JOptionPane.ERROR_MESSAGE);
                                                        arr[xAdjustment2[0] + 1][yAdjustment2[0]].setBackground(Color.white);
                                                        xAdjustment2[0] -= 1;
                                                        counter -= 1;
                                                        ll[0] = counter;
                                                    }

                                                    player2.x=xAdjustment2[0] + 1;
                                                    player2.y=yAdjustment2[0];

                                                    xAdjustment2[0] += 1;
                                                    counter += 1;
                                                    ll2[0] = counter;


                                                    arr[xAdjustment2[0]-1][yAdjustment2[0]].setBackground(Color.white);
                                                }
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                            break;

                        case KeyEvent.VK_RIGHT:
                            if (switchPlayer[0]==2){
                                int dd= Integer.parseInt(DiceNumber.getText());
                                int counter = ll2[0];
                                if (yy[0]==0){
                                    while (counter != dd+1) {

                                        if (counter==dd){
                                            JOptionPane.showMessageDialog(layeredPane, "Moving is more than dice number",
                                                    "Error", JOptionPane.ERROR_MESSAGE);

                                            switchPlayer[0]=1;
                                            PlayerName.setText("Player1");
                                            counter=0;
                                            ll2[0] = 0;

                                            for (int i = 0; i< finalSize1; i++){
                                                for (int j = 0; j< finalSize1; j++){
                                                    if (logicBoard[i][j]==99){
                                                        arr[i][j].setBackground(Color.white);
                                                        logicBoard[i][j] = 0;
                                                    }
                                                }
                                            }

                                            break;
                                        }
                                        else{
                                            if (yAdjustment[0] == finalSize - 1) {
                                                JOptionPane.showMessageDialog(layeredPane, "Out of board size",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                break;
                                            }
                                            else {
                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0]+1] == 3){
                                                    JOptionPane.showMessageDialog(layeredPane, "Here is a wall",
                                                            "Error", JOptionPane.ERROR_MESSAGE);
                                                    counter -=1;
                                                }

                                                else {
                                                    arr[xAdjustment2[0]][yAdjustment2[0] +1].setBackground(Color.red);

                                                    if (logicBoard[xAdjustment2[0]][yAdjustment2[0]] != 1 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=2 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=4){
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
                                                                    player1.money += 100000;
                                                                    f.setVisible(false);
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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

                                                    if (logicBoard[xAdjustment2[0]][yAdjustment2[0] +1]==5){
                                                        Random rand = new Random();
                                                        int money = rand.nextInt(10)+1;
                                                        money *= 1000;
                                                        JOptionPane.showMessageDialog(layeredPane, "You got"+ " "+ String.valueOf(money)+" money",
                                                                "congratulations", JOptionPane.PLAIN_MESSAGE);
                                                        player1.money +=money;
                                                        logicBoard[xAdjustment2[0]][yAdjustment2[0] +1]=0;
                                                    }

                                                    if (logicBoard[xAdjustment2[0]][yAdjustment2[0] +1]==6){
                                                        Random rand = new Random();
                                                        int trap = rand.nextInt(10)+1;
                                                        trap *= 100;
                                                        JOptionPane.showMessageDialog(layeredPane, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                                "Oh no", JOptionPane.ERROR_MESSAGE);
                                                        player1.money -=trap;
                                                        logicBoard[xAdjustment2[0]][yAdjustment2[0] +1]=0;
                                                    }

                                                    if (logicBoard[xAdjustment2[0]][yAdjustment2[0] +1]==99){
                                                        JOptionPane.showMessageDialog(layeredPane, "You can not return",
                                                                "Oh no", JOptionPane.ERROR_MESSAGE);
                                                        arr[xAdjustment2[0]][yAdjustment2[0] +1].setBackground(Color.white);
                                                        yAdjustment2[0] -= 1;
                                                        counter -= 1;
                                                        ll[0] = counter;
                                                    }

                                                    player2.x=xAdjustment2[0];
                                                    player2.y=yAdjustment2[0] + 1;

                                                    yAdjustment2[0] += 1;
                                                    counter += 1;
                                                    ll2[0] = counter;


                                                    arr[xAdjustment2[0]][yAdjustment2[0]-1].setBackground(Color.white);
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
                                int dd= Integer.parseInt(DiceNumber.getText());
                                int counter = ll2[0];
                                if (yy[0]==0){
                                    while (counter != dd+1) {


                                        if (counter==dd){
                                            JOptionPane.showMessageDialog(layeredPane, "Moving is more than dice number",
                                                    "Error", JOptionPane.ERROR_MESSAGE);

                                            switchPlayer[0]=1;
                                            PlayerName.setText("Player1");
                                            counter=0;
                                            ll2[0] = 0;

                                            for (int i = 0; i< finalSize1; i++){
                                                for (int j = 0; j< finalSize1; j++){
                                                    if (logicBoard[i][j]==99){
                                                        arr[i][j].setBackground(Color.white);
                                                        logicBoard[i][j] = 0;
                                                    }
                                                }
                                            }
                                            break;
                                        }
                                        else{
                                            if (yAdjustment2[0] == 0) {
                                                JOptionPane.showMessageDialog(layeredPane, "Out of board size",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                break;
                                            }
                                            else {
                                                if (logicBoard[xAdjustment2[0]][yAdjustment2[0]-1] == 3){
                                                    JOptionPane.showMessageDialog(layeredPane, "Here is a wall",
                                                            "Error", JOptionPane.ERROR_MESSAGE);
                                                    counter -=1;
                                                }

                                                else {
                                                    arr[xAdjustment2[0]][yAdjustment2[0] -1].setBackground(Color.red);
                                                    if (logicBoard[xAdjustment2[0]][yAdjustment2[0]] != 1 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=2 && logicBoard[xAdjustment2[0]][yAdjustment2[0]] !=4){
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
                                                                    player1.money += 100000;
                                                                    f.setVisible(false);
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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
                                                        JOptionPane.showMessageDialog(layeredPane, "Code of treasure : " + String.valueOf(code),
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

                                                    if (logicBoard[xAdjustment2[0]][yAdjustment2[0] -1]==5){
                                                        Random rand = new Random();
                                                        int money = rand.nextInt(10)+1;
                                                        money *= 1000;
                                                        JOptionPane.showMessageDialog(layeredPane, "You got"+ " "+ String.valueOf(money)+" money",
                                                                "congratulations", JOptionPane.PLAIN_MESSAGE);
                                                        player1.money +=money;
                                                        logicBoard[xAdjustment2[0]][yAdjustment2[0] -1]=0;
                                                    }

                                                    if (logicBoard[xAdjustment2[0]][yAdjustment2[0] -1]==6){
                                                        Random rand = new Random();
                                                        int trap = rand.nextInt(10)+1;
                                                        trap *= 100;
                                                        JOptionPane.showMessageDialog(layeredPane, "You are in a trap\n"+"You lost "+String.valueOf(trap)+" money",
                                                                "Oh no", JOptionPane.ERROR_MESSAGE);
                                                        player1.money -=trap;
                                                        logicBoard[xAdjustment2[0]][yAdjustment2[0] -1]=0;
                                                    }

                                                    if (logicBoard[xAdjustment2[0]][yAdjustment2[0] -1]==99){
                                                        JOptionPane.showMessageDialog(layeredPane, "You can not return",
                                                                "Oh no", JOptionPane.ERROR_MESSAGE);
                                                        arr[xAdjustment2[0]][yAdjustment2[0] -1].setBackground(Color.white);
                                                        yAdjustment[0] += 1;
                                                        counter -= 1;
                                                        ll[0] = counter;
                                                    }

                                                    player2.x=xAdjustment2[0];
                                                    player2.y=yAdjustment2[0] - 1;

                                                    yAdjustment2[0] -= 1;
                                                    counter += 1;
                                                    ll2[0] = counter;



                                                    arr[xAdjustment2[0]][yAdjustment2[0]+1].setBackground(Color.white);
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
        }

        Scoreboard.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Scoreboard.setVisible(false);
                PLaying_Game.setVisible(true);
            }
        });

        JFrame StartingFrame = new JFrame("Traveling Salesman");
        StartingFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Assets/StartingWindowBackground.png")))));
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