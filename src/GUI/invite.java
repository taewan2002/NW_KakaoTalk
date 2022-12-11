package GUI;

import javax.swing.*;
import java.awt.*;

public class invite extends JFrame{
    private JPanel main;
    private JTextField searchTextField;
    private JButton search;
    private JScrollPane invitePanel;
    private JPanel invite;

    public invite(){
        setContentPane(main);
        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);


    }
}
