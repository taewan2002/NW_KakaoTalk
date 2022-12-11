package GUI;

import function.ImgSetSize;
import public_data.setCoinData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public chats(){
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

        friendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new friends();
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
                new chattingRoom();
                setVisible(false);
            }
        });
        publicDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new setCoinData();
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
    }
}
