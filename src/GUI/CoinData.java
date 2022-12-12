package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import chatting_function.ListeningThread;
import chatting_function.chatting_client;
import function.ImgSetSize;
import public_data.*;

public class CoinData extends JFrame {
    JPanel Scorll;
    JPanel mainPanel;
    private JButton friendButton;
    private JButton roomButton;
    private JButton publicDataButton;
    private JButton refresh;
    private JTextField timeNow;
    private JButton more;
    private ArrayList<String> market = new ArrayList<>();
    private String user_id;
    private chatting_client client;
    private ListeningThread t1;

    public CoinData(String user_id, chatting_client client, ListeningThread t1) {
        this.user_id = user_id;
        this.client = client;
        this.t1 = t1;

        String time = new getCoinData("BTC").getTime();
        timeNow.setText(time);
        timeNow.setEnabled(false);
        timeNow.setHorizontalAlignment(JTextField.CENTER);
        ImgSetSize friendIcon = new ImgSetSize("src/IMG/friendButtonIconNoActive.png", 35, 35);
        friendButton.setIcon(friendIcon.getImg());
        ImgSetSize roomIcon = new ImgSetSize("src/IMG/chattingButtonIconActive.png", 35, 35);
        roomButton.setIcon(roomIcon.getImg());
        ImgSetSize dataIcon = new ImgSetSize("src/IMG/BTC.png", 35, 35);
        publicDataButton.setIcon(dataIcon.getImg());
        ImgSetSize refreshButton = new ImgSetSize("src/IMG/refresh.png", 35,25);
        refresh.setIcon(refreshButton.getImg());
        ImgSetSize moreicon = new ImgSetSize("src/IMG/moreicon.png", 35,35);
        more.setIcon(moreicon.getImg());

        market.add("BTC");
        market.add("ETH");
        market.add("XRP");
        market.add("BCH");
        market.add("EOS"); // 이미지 오류(찾기힘듬);;
        market.add("TRX");
        market.add("QTUM");
        market.add("BTG");
        market.add("ICX");
        market.add("XEM");


        add(mainPanel);
        setTitle("CoinData");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout Gbag = new GridBagLayout();
        Scorll.setLayout(Gbag);
        GridBagConstraints gbc = new GridBagConstraints();


        for (int k = 0; k < market.size(); k++) {
            getCoinData pane = new getCoinData(market.get(k));
            gbc.fill = GridBagConstraints.BOTH;
            gbc.ipadx = 0;
            gbc.ipady = 20;
            gbc.gridx = 0;
            gbc.gridy = k;
            JLabel label = new JLabel();
            String imgPath = "src/img/" + market.get(k) + ".png";
            ImgSetSize img = new ImgSetSize(imgPath, 50, 50);
            label.setIcon(img.getImg());
            label.setText("  이름 : " + pane.getMarket() + " 가격 : " + pane.getTrade_price() + " 변동률 : " + pane.getChangeRate());
            Scorll.add(label, gbc);
            Scorll.updateUI();
            Scorll.setVisible(true);
        }
        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);
        friendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new friends(user_id, client, t1);
                setVisible(false);
            }
        });
        roomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new chats(user_id, client, t1);
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
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new setCoinData(user_id, client, t1);
                setVisible(false);
            }
        });
    }
}
