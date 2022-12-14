package concave;

import javax.swing.*;
import function.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import java.awt.Graphics;

public class GUI_board extends JFrame{
    BufferedImage img = null;

    public static myPanel panel;

    public static JButton order1;
    public static JButton order2;

    public static JLayeredPane layeredPane;

    public GUI_board(GameMain ad) {
        setTitle("오목");

        layeredPane = new JLayeredPane();
        layeredPane.setSize(480,650);
        setResizable(false);
        layeredPane.setVisible(true);

        //setLayout(null);
        //add(layeredPane);
        //setBounds(720,220,480,650);
        //setVisible(true);

        try {
            img = ImageIO.read(new File("src/concave/concave_img.png"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "이미지 불러오기 실패");
            System.exit(0);
        }

        panel = new myPanel();
        panel.setSize(480,650);
        layeredPane.add(panel);

        setLayout(null);

        add(layeredPane);

        setBounds(720,220,480,650);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);

        for (int i=0;i<15;i++)
        {
            for (int j=0;j<15;j++)
            {
                String button_name = Integer.toString(i) + " " + Integer.toString(j);
                JButton btn = new JButton(button_name);
                btn.setBorderPainted(false);
                btn.setContentAreaFilled(false);
                btn.setFocusPainted(false);
                btn.setOpaque(false);
                btn.setBounds(31*j+9, 31*i+49, 16, 16);
                btn.addActionListener(new EventHandler(ad));
                panel.add(btn);

            }
        }

        order1 = new JButton();
        order1.setBounds(0, 532, 70, 80);
        order2 = new JButton();
        order2.setBounds(395, 532, 70, 80);

        order1.setBorderPainted(false);
        order2.setBorderPainted(false);

        order1.setBackground(new Color(255,255,255));
        order2.setBackground(new Color(0,0,0));


        panel.add(order1);
        panel.add(order2);

        panel.setVisible(true);



    }

    class myPanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }

}
