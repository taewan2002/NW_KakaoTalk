package GUI;

import chatting_function.ListeningThread;
import chatting_function.chatting_client;
import function.ImgSetSize;
import public_data.setCoinData;
import function.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class chats extends JFrame{
    private JPanel main;
    private JButton friendButton;
    private JButton roomButton;
    private JButton publicDataButton;
    private JScrollPane roomPanel;
    private JPanel room;
    private JTextField search_room;
    private JButton searchButton;
    private JButton createRoom;
    private JButton more;
    private String user_id;
    private chatting_client client;
    private ListeningThread t1;


    private static ArrayList<String> room_id;
    private static ArrayList<String> member_list;

    public chats(String user_id, chatting_client client, ListeningThread t1){
        this.user_id = user_id;
        this.client = client;
        this.t1 = t1;

        setContentPane(main);
        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);

        ImgSetSize friendIcon = new ImgSetSize("src/IMG/friendButtonIconNoActive.png", 35,35);
        friendButton.setIcon(friendIcon.getImg());
        ImgSetSize roomIcon = new ImgSetSize("src/IMG/chattingButtonIconActive.png", 35,35);
        roomButton.setIcon(roomIcon.getImg());
        ImgSetSize dataIcon = new ImgSetSize("src/IMG/btcBlack.png", 35,35);
        publicDataButton.setIcon(dataIcon.getImg());
        ImgSetSize search = new ImgSetSize("src/IMG/search.png", 35,25);
        searchButton.setIcon(search.getImg());
        ImgSetSize create = new ImgSetSize("src/IMG/roomCreate.png", 35,25);
        createRoom.setIcon(create.getImg());
        ImgSetSize moreicon = new ImgSetSize("src/IMG/moreicon.png", 35,35);
        more.setIcon(moreicon.getImg());

        get_data getData = new get_data();
        getData.setType11(11, user_id);
        getData.start();
        room_id = getData.getMy_room_list();

        for(int i = 0;i<room_id.size();i++){
            System.out.println(room_id.get(i));
            new cache_download(null,room_id.get(i),room_id.get(i),"1",2,client);
        }
        roomPanel.getVerticalScrollBar().setUnitIncrement(15);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridBagLayout Gbag = new GridBagLayout();
        room.setLayout(Gbag);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        for(int i = 0;i<room_id.size();i++){
            getData.setType12(12, user_id, room_id.get(i));
            getData.start();
            member_list = getData.get_users_in_room();
            roomPanel pane = new roomPanel(member_list,room_id.get(i));
            gbc.fill = GridBagConstraints.BOTH;
            gbc.ipadx = 850;
            gbc.ipady = 100;
            gbc.gridx = 0;
            gbc.gridy = i*100;
            Gbag.setConstraints(pane,gbc);
            room.add(pane);
            room.updateUI();
        }

        friendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new friends(user_id, client, t1);
                setVisible(false);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        createRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new chattingRoom(user_id, client, t1);
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
    }
    public class roomPanel extends JPanel{
        private  ArrayList<String> member_list = new ArrayList<>();
        private String room_id;
        private JLabel member;
        private JButton in;
        private JButton out;
        public roomPanel(ArrayList<String> member , String id){
            this.member_list = member;
            this.room_id = id;
            this.member = new JLabel();
            String a = new String();
            for(int i =0;i<member_list.size();i++){
                a = a + " " + member_list.get(i);
                System.out.println(member_list.get(i));
            }
            this.member.setText(a);
            this.in = new JButton("in");
            this.out = new JButton("out");

            setLayout(new FlowLayout(FlowLayout.LEFT));
            setSize(850,100);

            this.in.setBackground(new Color(255,255,255));
            this.out.setBackground(new Color(255,255,255));
            this.in.setSize(100,100);
            this.out.setSize(100,100);
            this.member.setSize(750,100);
            add(this.in);
            add(this.out);
            add(this.member);
            in.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chattingRoom a = new chattingRoom(user_id,client,t1,room_id);
                    a.setVisible(true);

                }
            });
            out.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    client.exit_room(3,user_id,room_id);
                    File b = new File("chatting_data/" + room_id + ".txt");
                    System.out.println("chatting_data/" + room_id + ".txt");
                    if(b.exists()){
                        System.out.println("있다");
                        b.delete();
                    }
                    else{
                        System.out.println("없다");
                    }

                    chats a = new chats(user_id,client,t1);
                    setVisible(false);
                    a.setVisible(true);
                    dispose();
                }
            });
        } // 생성자
    } // roomPanel
}
