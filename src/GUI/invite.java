package GUI;

import chatting_function.ListeningThread;
import chatting_function.chatting_client;
import function.ImgSetSize;
import function.get_data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private final static ArrayList<String> List= new ArrayList<String>();

    public invite(String user_id, chatting_client client, ListeningThread t1){
        this.user_id = user_id;
        this.client = client;
        this.t1 = t1;

        // 유저리스트 검색해서 받아오기
        get_data data = new get_data();
        data.setType15(15, user_id);
        data.start();
        friend_list = data.getAllUserList();
        invitePanel.getVerticalScrollBar().setUnitIncrement(15);

        GridBagLayout Gbag = new GridBagLayout();
        invite.setLayout(Gbag);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        for(int i = 0;i<friend_list.size();i++){
            friend pane = new friend(friend_list.get(i));
            System.out.println(friend_list.get(i));
            gbc.fill = GridBagConstraints.BOTH;
            gbc.ipadx = 850;
            gbc.ipady = 100;
            gbc.gridx = 0;
            gbc.gridy = i;
            Gbag.setConstraints(pane,gbc);
            invite.add(pane);
            invite.updateUI();
        }
        invitePanel.setViewportView(invite);
        invitePanel.setVisible(true);
        invite.setVisible(true);

        setContentPane(main);
        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);

    }
    public class friend extends JPanel{

        private JButton invite_friend;
        private JButton remove_friend;
        private JLabel friend_name;
        private String friend_id;

        public friend(String friend_id){
            this.friend_id = friend_id;

            setLayout(new FlowLayout(FlowLayout.LEFT));

            setSize(850,100);
            invite_friend = new JButton("invite");
//            ImgSetSize invite = new ImgSetSize("src/IMG/invite_invite.png", 50, 50);
//            invite_friend.setIcon(invite.getImg());
            invite_friend.setBackground(new Color(255,255,255));

            remove_friend = new JButton("remove");
//            ImgSetSize remove = new ImgSetSize("src/IMG/invite_exit.png", 50, 50);
//            remove_friend.setIcon(remove.getImg());
            remove_friend.setBackground(new Color(255,255,255));

            friend_name = new JLabel();
            friend_name.setText(String.valueOf(friend_id));
            //friend_name.setSize(100,50);

            add(invite_friend);
            add(remove_friend);
            add(friend_name);
            setVisible(true);
            invite_friend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int same = 0;
                    for(int i = 0;i< List.size();i++){
                        if(List.get(i) == friend_id){
                            same = 1;
                        }
                    }
                    if(same == 0){
                        List.add(friend_id);
                        // invite_list.setText(invite_list.getText() + friend_id + " , ");
                    }
                }
            });

            remove_friend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i =0;i<List.size();i++){
                        if(List.get(i) == friend_id){
                            List.remove(i);
                            // invite_list.setText(invite_list.getText().replace(friend_id + " , ",""));
                        }
                    }
                }
            });
        }
    }
}
