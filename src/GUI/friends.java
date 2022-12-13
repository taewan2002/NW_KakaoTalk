package GUI;

import chatting_function.ListeningThread;
import chatting_function.chatting_client;
import function.*;
import public_data.setCoinData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class friends extends JFrame{
    private JPanel main;
    private JScrollPane friendPanel;
    private JPanel friend;
    private JButton friendButton;
    private JButton roomButton;
    private JButton publicDataButton;
    private JTextField search_friend;
    private JButton searchButton;
    private JButton inviteFriend;
    private JButton more;
    private String user_id;
    private chatting_client client;
    private ListeningThread t1;
    private ArrayList<String> friend_list = new ArrayList<String>();
    private ArrayList<String> onlinList;
    private ArrayList<String> friendList;


    public friends(String user_id, chatting_client client, ListeningThread t1){
        this.user_id = user_id;
        this.client = client;
        this.t1 = t1;

        // 친구 목록 불러오고 온라인 유저 목록 불러와서 표시하기
        // 온라인 유저 목록 불러오기
        get_data get = new get_data();
        get.setType50(user_id);
        get.start();
        this.onlinList = get.getList();

        // 내 친구 목록 불러오기
        get_data getFriends = new get_data();
        getFriends.setType54(user_id);
        getFriends.start();
        this.friendList = getFriends.getList();

        System.out.println(onlinList);
        System.out.println(friendList);
//
//        GridBagLayout Gbag = new GridBagLayout();
//        friend.setLayout(Gbag);
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.weightx = 1.0;
//        gbc.weighty = 1.0;
//        for(int i = 0;i<friendList.size();i++){
//            JLabel label = new JLabel(friendList.get(i), JLabel.CENTER);;
//            gbc.fill = GridBagConstraints.BOTH;
//            gbc.ipadx = 850;
//            gbc.ipady = 100;
//            gbc.gridx = 0;
//            gbc.gridy = i*100;
//            Gbag.setConstraints(label,gbc);
//            friendPanel.add(label);
//            friendPanel.updateUI();
//        }

        ImgSetSize friendIcon = new ImgSetSize("src/IMG/friendButtonIconActive.png", 35,35);
        friendButton.setIcon(friendIcon.getImg());
        ImgSetSize roomIcon = new ImgSetSize("src/IMG/chattingButtonIconNoActive.png", 35,35);
        roomButton.setIcon(roomIcon.getImg());
        ImgSetSize dataIcon = new ImgSetSize("src/IMG/btcBlack.png", 35,35);
        publicDataButton.setIcon(dataIcon.getImg());
        ImgSetSize search = new ImgSetSize("src/IMG/search.png", 35,25);
        searchButton.setIcon(search.getImg());
        ImgSetSize invite_friend = new ImgSetSize("src/IMG/invite_friend.png", 35,25);
        inviteFriend.setIcon(invite_friend.getImg());
        ImgSetSize moreicon = new ImgSetSize("src/IMG/moreicon.png", 35,35);
        more.setIcon(moreicon.getImg());

        setContentPane(main);
        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = search_friend.getText();
                for(int i = 0;i< friend_list.size();i++) {
                    if (friend_list.get(i).contains(email)) {
                        // 친구 검색
                    }
                }
            }
        });
        roomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new chats(user_id, client, t1);
                setVisible(false);
            }
        });
        publicDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new setCoinData(user_id, client, t1);
                setVisible(false);
            }
        });
        more.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new more(user_id, client, t1);
                setVisible(false);
            }
        });
        inviteFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new invite(user_id, client, t1);
                setVisible(false);
            }
        });
    }
}
