import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Temp {
    public static void main(String[] args) {
        int[] cc = new int[9];
        cc[0] = 1352;
        JFrame f=new JFrame("Password Field Example");
        final JLabel label = new JLabel();
        label.setBounds(80,600, 250,50);
        JTextField value = new JTextField();
        value.setBounds(355,265,100,30);
        JLabel l2=new JLabel("Enter your treasure Code:");
        l2.setBounds(120,250, 300,60);
        l2.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton b = new JButton("Done");
        b.setBounds(250,320, 80,30);
        f.add(value); f.add(label); f.add(l2); f.add(b);
        f.setSize(600,600);
        f.setLayout(null);
        f.setVisible(true);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = value.getText();
                int sw = 0;
                for (int i = 0 ; i<cc.length ; i++){
                    if (cc[i]==Integer.parseInt(code)){
                        sw =1;
                        break;
                    }
                }
                if (sw==1){
                    JOptionPane.showMessageDialog(f, "You got 10000 money",
                            "Treasure Confirmed", JOptionPane.PLAIN_MESSAGE);
                    f.setVisible(false);
                }
            }
        });
    }
}
