package GUI;

import chatting_function.ListeningThread;
import chatting_function.chatting_client;
import function.ImgSetSize;
import function.get_data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class invite extends JFrame{
    private JPanel main;
    private JTextField searchTextField;
    private JButton search;
    private JScrollPane invitePanel;
    private JPanel invite;
    private JButton addFriend;
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

        // 친구추가 버튼
        addFriend.setIcon(new ImgSetSize("src/IMG/invite_friend.png", 50, 50).getImg());

        search.setIcon(new ImgSetSize("src/IMG/search.png", 50, 50).getImg());

        GridBagLayout Gbag = new GridBagLayout();
        invite.setLayout(Gbag);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        for(int i = 0;i<friend_list.size();i++){
            friend pane = new friend(friend_list.get(i));
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

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = searchTextField.getText();
                invite.removeAll();
                for(int i = 0;i< friend_list.size();i++){
                    if(friend_list.get(i).contains(email)){
                        friend pane = new friend(friend_list.get(i));
                        gbc.fill = GridBagConstraints.BOTH;
                        gbc.ipadx = 850;
                        gbc.ipady = 100;
                        gbc.gridx = 0;
                        gbc.gridy = i;
                        Gbag.setConstraints(pane,gbc);
                        invite.add(pane);
                        invite.updateUI();
                    }
                }
                invitePanel.setViewportView(invite);
                invitePanel.setVisible(true);
                invite.setVisible(true);
            }
        });
        addFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 친구 추가 만들기


                // 이건 아님
//                for(int i = 0;i< List.size();i++){
//                    System.out.println(List.get(i));
//                }
//                //chatting_client에 List 전달
//                client.make_room(1,user_id,List);
//                get_data getData = new get_data();
//                getData.setType11(11, user_id);
//                getData.start();
//                ArrayList<String> b = getData.getMy_room_list();
//                System.out.println("chatting_data/" + b.get(b.size()-1) + ".txt");
//                File file = new File("chatting_data/" + b.get(b.size()-1) + ".txt");
//                try{
//                    FileWriter fw =new FileWriter(file,true);
//                    BufferedWriter bw= new BufferedWriter(fw);
//                    bw.close();
//                }
//                catch(IOException e2){
//                    e2.printStackTrace();
//                }
//
//                chattingRoom a = new chattingRoom(user_id,client,t1);
//                a.setVisible(true);
//                dispose();
            }
        });
    }
    public class friend extends JPanel{

        private JButton invite_friend;
        private JButton remove_friend;
        private JLabel friend_name;
        private String friend_id;

        public friend(String friend_id){
            // 행 스크롤 고정
            invitePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            this.friend_id = friend_id;

            setLayout(new FlowLayout(FlowLayout.LEFT));

            invite_friend = new JButton();
            ImgSetSize invite = new ImgSetSize("src/IMG/invite.png", 50, 50);
            invite_friend.setIcon(invite.getImg());
            invite_friend.setBackground(new Color(255,255,255));

            remove_friend = new JButton();
            ImgSetSize remove = new ImgSetSize("src/IMG/revert.png", 50, 50);
            remove_friend.setIcon(remove.getImg());
            remove_friend.setBackground(new Color(255,255,255));

            friend_name = new JLabel();
            String text = friend_id;
            friend_name.setText(text);
            friend_name.setPreferredSize(new Dimension(330,50));
            friend_name.setHorizontalAlignment(JLabel.CENTER);


            add(friend_name);
            add(invite_friend);
            add(remove_friend);

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
                        searchTextField.setText(searchTextField.getText() + friend_id + " , ");
                    }
                }
            });

            remove_friend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i =0;i<List.size();i++){
                        if(List.get(i) == friend_id){
                            List.remove(i);
                            searchTextField.setText(searchTextField.getText().replace(friend_id + " , ",""));
                        }
                    }
                }
            });
        }
    }
}
