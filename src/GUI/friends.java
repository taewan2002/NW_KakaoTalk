package GUI;

import function.ImgSetSize;

import javax.swing.*;
import java.awt.*;
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
    private JButton more;

    public friends(){

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
        more.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new more();
                setVisible(false);
            }
        });
        inviteFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new invite();
                setVisible(false);
            }
        });
    }
}
