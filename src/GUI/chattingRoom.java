package GUI;

import chatting_function.ListeningThread;
import chatting_function.chatting_client;

import javax.swing.*;
import java.awt.*;


public class chattingRoom extends JFrame {
    private String user_id;
    private chatting_client client;
    private ListeningThread t1;
    private JTextField textField1;
    private JButton button1;
    private JButton fileOption;

    public chattingRoom(String user_id, chatting_client client, ListeningThread t1){
        this.user_id = user_id;
        this.client = client;
        this.t1 = t1;

        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);
    }
}
