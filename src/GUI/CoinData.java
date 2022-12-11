package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import function.ImgSetSize;
import public_data.getCoinData;
public class CoinData extends JFrame {
    JPanel Scorll;
    JPanel mainPanel;
    private JButton button1;
    private JButton button2;
    private ArrayList<String> market = new ArrayList<>();

    public CoinData(){
        market.add("BTC");
        market.add("ETH");
        market.add("XRP");
        market.add("BCH"); // 이미지 오류
        // market.add("EOS"); // 이미지 오류
        market.add("TRX");
        market.add("QTUM"); // 이미지 오류
        market.add("BTG");
        market.add("ICX");
        market.add("XEM");


        add(mainPanel);
        setTitle("CoinData");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout Gbag = new GridBagLayout();
        Scorll.setLayout(Gbag);
        GridBagConstraints gbc = new GridBagConstraints();


        for(int k=0; k<market.size(); k++){
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

        setSize(480, 650);
        setVisible(true);
    }
}
