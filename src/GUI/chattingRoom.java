package GUI;

import javax.swing.*;
import java.awt.*;

public class chattingRoom extends JFrame {
    public chattingRoom(){
        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);
    }
}
