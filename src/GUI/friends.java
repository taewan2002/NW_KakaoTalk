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
    private JPanel friend_pane;
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
    private ArrayList<String> onlineList;
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
        this.onlineList = get.getList();

        // 내 친구 목록 불러오기
        get_data getFriends = new get_data();
        getFriends.setType54(user_id);
        getFriends.start();
        this.friendList = getFriends.getList();

        GridBagLayout Gbag = new GridBagLayout();
        friend_pane.setLayout(Gbag);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        System.out.println(friendList.size());
        for(int i = 0;i<friendList.size();i++){
            friend_panel a = new friend_panel(friendList.get(i),this.onlineList);
            gbc.fill = GridBagConstraints.BOTH;
            gbc.ipadx = 0;
            gbc.ipady = 10;
            gbc.gridx = 0;
            gbc.gridy = i;
            Gbag.setConstraints(a,gbc);
            friend_pane.add(a);
            friend_pane.updateUI();
        }

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
                String[] option_message = {"내 정보","로그 아웃","탈퇴"};
                int result = JOptionPane.showOptionDialog(null,"Option","option",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,option_message,null);

                if (result == 0){
                    new more(user_id,client,t1);
                    setVisible(false);
                }
                else if (result == 1) {
                    new login();
                    setVisible(false);
                }
                else {
                    // 탈퇴하기
                    get_data Data = new get_data();
                    Data.setType55(user_id);
                    Data.start();
                    if(Data.getTf()){
                        JOptionPane.showMessageDialog(null, "탈퇴되었습니다.");
                        login a = new login();
                        a.setVisible(true);
                        setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "탈퇴에 실패하였습니다.");
                    }
                }
            }
        });
        inviteFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new invite(user_id, client, t1, friendList);
                setVisible(false);
            }
        });
    }
    public class friend_panel extends JPanel{

        private String friend;

        private ArrayList<String> online_list;

        private boolean online_bool;

        private JTextArea name;

        private JLabel online_bool_label;
        public friend_panel(String friend, ArrayList<String> online){
            this.friend = friend;
            this.online_list = online;
            this.online_bool = false;

            GridBagLayout Gbag = new GridBagLayout();
            setLayout(Gbag);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;

            for(int k=0;k<online.size();k++){
                if(friend.equals(online.get(k))){
                    this.online_bool = true;
                }
            }

            name = new JTextArea();
            name.setDisabledTextColor(new Color(0,0,0));
            name.setLineWrap(true);
            name.setWrapStyleWord(true);
            name.setEditable(false);
            name.setText(friend);

            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.gridheight = 1;
            gbc.weightx = 0.4;
            gbc.weighty = 1;
            add(name,gbc);

            online_bool_label = new JLabel();
            if(this.online_bool){
                online_bool_label.setForeground(new Color(0, 180, 0));

                online_bool_label.setText("online");
            }
            else{
                online_bool_label.setForeground(new Color(200, 200, 200));

                online_bool_label.setText("offline");
            }

            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 0.2;
            gbc.weighty = 1;
            add(online_bool_label,gbc);

            JLabel a = new JLabel();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.gridheight = 1;
            gbc.weightx = 0.4;
            gbc.weighty = 1;
            add(a,gbc);

            setSize(480,50);
            setVisible(true);
        }
    }
}
