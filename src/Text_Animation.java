import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.BOLD;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ccs
 */
public class Text_Animation extends JPanel{
    String Quest;
    String Quest2;
    Text_Animation(String Quest , String Quest2){
        this.Quest = Quest;
        this.Quest2 = Quest2;
    }

    int x=0;
    int y=100;
    int a=400;
    int b=160;
    public void paint(Graphics gp)
    {
        super.paint(gp);
        Graphics2D g2d= (Graphics2D) gp;
        g2d.setColor(Color.white);
        this.setBackground(new Color(16,5,47));
        g2d.setFont(new Font("BOLD", BOLD, 35));

        g2d.drawString(Quest, x, y);
        g2d.drawString(Quest2, a, b);
        try {
            Thread.sleep(10);
            x+=2;
            a-=2;

            if(x>getWidth())
            {

                x=0;
            }
            if(a<0)
            {

                a=500;
            }
            repaint();

        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

}