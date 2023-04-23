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

public class Temp2 {
    public static void main(String[] args) {
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
                b.setBackground(Color.white);
                b.setForeground(Color.black);
                b.setBorder(BorderFactory.createLineBorder(Color.black));
            }

            public void mouseExited(MouseEvent evt) {
                b.setBackground(Color.black);
                b.setForeground(Color.white);
                b.setBorder(blackline2);
            }

            @Override
            public void mouseClicked(MouseEvent e) {

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
}
