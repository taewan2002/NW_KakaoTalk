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
import java.util.List;

public class invite extends JFrame{
    private JPanel main;
    private JTextField searchTextField;
    private JButton search;
    private JScrollPane invitePanel;
    private JPanel invite;
    private JButton addFriend;
    private JTextField friend;
    private String user_id;
    private chatting_client client;
    private ListeningThread t1;

    private ArrayList<String> friend_list = new ArrayList<String>();
    private final static ArrayList<String> List= new ArrayList<String>();

    private ArrayList<String> friendList = new ArrayList<>();

    public invite(String user_id, chatting_client client, ListeningThread t1, ArrayList<String> friend1){
        this.user_id = user_id;
        this.client = client;
        this.t1 = t1;
        this.friendList = friend1;
        // 유저리스트 검색해서 받아오기
        get_data data = new get_data();
        data.setType15(15, user_id);
        data.start();
        friend_list = data.getAllUserList();
        invitePanel.getVerticalScrollBar().setUnitIncrement(15);

        // 친구추가 버튼
        addFriend.setIcon(new ImgSetSize("src/IMG/invite_friend.png", 30, 30).getImg());

        search.setIcon(new ImgSetSize("src/IMG/search.png", 30, 30).getImg());

        GridBagLayout Gbag = new GridBagLayout();
        invite.setLayout(Gbag);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        for(int i = 0;i<friend_list.size();i++){
            boolean duplicate = false;
            for(int k = 0;k<friendList.size();k++){
                if(friend_list.get(i).equals(friendList.get(k)) == true){
                    duplicate = true;
                }
            }
            if(!duplicate){
                friend pane = new friend(friend_list.get(i));
                gbc.fill = GridBagConstraints.BOTH;
                gbc.ipadx = 0;
                gbc.ipady = 0;
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

        setContentPane(main);
        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);

        friend.setDisabledTextColor(new Color(0,0,0));
        friend.setEditable(false);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = searchTextField.getText();
                invite.removeAll();
                for(int i = 0;i< friend_list.size();i++){
                    if(friend_list.get(i).contains(email)){
                        friend pane = new friend(friend_list.get(i));
                        gbc.fill = GridBagConstraints.BOTH;
                        gbc.ipadx = 0;
                        gbc.ipady = 0;
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
                if(List.size() != 0){
                    get_data data = new get_data();
                    data.setType16(user_id, List);
                    data.start();
                    JOptionPane.showMessageDialog(null, "친구 추가 완료");
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "추가할 친구를 선택해주세요");
                }



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

            GridBagLayout Gbag = new GridBagLayout();
            setLayout(Gbag);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;

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
            friend_name.setHorizontalAlignment(JLabel.CENTER);

            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 4;
            gbc.gridheight = 1;
            add(friend_name,gbc);

            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 4;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            add(invite_friend);

            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 5;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
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
                        friend.setText(friend.getText() + friend_id + " , ");
                    }
                }
            });

            remove_friend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i =0;i<List.size();i++){
                        if(List.get(i) == friend_id){
                            List.remove(i);
                            friend.setText(friend.getText().replace(friend_id + " , ",""));
                        }
                    }
                }
            });
        }
    }
}
