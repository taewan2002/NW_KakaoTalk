package GUI;

import chatting_function.ListeningThread;
import chatting_function.chatting_client;
import function.get_data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class more extends JFrame {
    private JPanel main;
    private JButton exit;
    private JTextField nameField;
    private JTextField phoneField;
    private String user_id;
    private chatting_client client;
    private ListeningThread t1;
    public more(String user_id, chatting_client client, ListeningThread t1){
        this.user_id = user_id;
        this.client = client;
        this.t1 = t1;
        setContentPane(main);
        get_data data = new get_data();
        data.setType51(user_id);
        data.start();

        System.out.println(data.getName());
        System.out.println(data.getPhoneNum());

        nameField.setText(data.getName());
        phoneField.setText(data.getPhoneNum());
        nameField.setEnabled(false);
        phoneField.setEnabled(false);



        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);

        nameField.setDisabledTextColor(new Color(0,0,0));
        nameField.setEditable(false);

        phoneField.setDisabledTextColor(new Color(0,0,0));
        phoneField.setEditable(false);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new friends(user_id,client,t1);
                setVisible(false);
            }
        });
    }
}
