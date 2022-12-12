package GUI;

import chatting_function.ListeningThread;
import chatting_function.chatting_client;
import function.get_data;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class invite extends JFrame{
    private JPanel main;
    private JTextField searchTextField;
    private JButton search;
    private JScrollPane invitePanel;
    private JPanel invite;
    private String user_id;
    private chatting_client client;
    private ListeningThread t1;

    private ArrayList<String> friend_list = new ArrayList<String>();

    public invite(String user_id, chatting_client client, ListeningThread t1){
        this.user_id = user_id;
        this.client = client;
        this.t1 = t1;

        // 유저리스트 검색해서 받아오기
        get_data data = new get_data();
        data.setType15(15, user_id);
        data.start();
        friend_list = data.getAllUserList();
//        for(int i = 0;i<friend_list.size();i++){
//            System.out.println(friend_list.get(i));
//        }

        setContentPane(main);
        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);


    }
}
