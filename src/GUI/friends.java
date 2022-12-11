package GUI;

import function.ImgSetSize;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public friends(){

        ImgSetSize friendIcon = new ImgSetSize("src/IMG/friendButtonIconActive.png", 35,35);
        friendButton.setIcon(friendIcon.getImg());
        ImgSetSize roomIcon = new ImgSetSize("src/IMG/chattingButtonIconNoActive.png", 35,35);
        roomButton.setIcon(roomIcon.getImg());
        ImgSetSize dataIcon = new ImgSetSize("src/IMG/btcBlack.png", 35,35);
        publicDataButton.setIcon(dataIcon.getImg());

        setContentPane(main);
        setSize(480,650);
        setBounds(0,0,480,650);
        setVisible(true);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = search_friend.getText();

            }
        });
        roomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new chats();
                setVisible(false);
            }
        });
        publicDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CoinData();
                setVisible(false);
            }
        });
    }
}
