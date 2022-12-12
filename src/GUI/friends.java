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


    public friends(String user_id, chatting_client client, ListeningThread t1){
        get_data get = new get_data();
        get.setType50(user_id);
        get.start();
        this.onlinList = get.getList(); // 온라인 유저 목록 불러오기
        this.user_id = user_id;
        this.client = client;
        System.out.println(onlinList);
        this.t1 = t1;


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
