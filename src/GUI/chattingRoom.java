package GUI;

import chatting.protocol;
import chatting_function.ListeningThread;
import chatting_function.chatting_client;
import function.ImgSetSize;
import public_data.getCoinData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class chattingRoom extends JFrame {
    private String user_id;
    private chatting_client client;
    private ListeningThread t1;
    private JTextField message;
    private JButton send;
    private JButton fileOption;
    private JScrollPane chatPanel;
    private JPanel chat;
    private JPanel main;

    boolean running = true;
    BufferedInputStream reader = null;

    private class read extends Thread{
        private GridBagConstraints gbc;
        private JScrollPane chatPanel_thread;

        public read(GridBagConstraints gbc, JScrollPane chatPanel){
            this.gbc = gbc;
            this.chatPanel_thread = chatPanel;
        }
        public void run() {
            int i=0;
            int k=0;
            boolean t = false;
            byte[] b = new byte[100000];
            while (running) {
                try {
                    if (reader.available() > 0) {
                        byte tmp = (byte)reader.read();
                        if(tmp==13 || tmp==10){
                            String s = new String(b, StandardCharsets.UTF_8);
                            s=s.substring(0,i);
                            System.out.println(s);
                            String w[] = s.split(":");

                            //w[0] (8,10): hours
                            //w[0] (10,12): minutes
                            //w[1]: user
                            //w[2]: message
                            //w[3]: file boolean
                            //w[4]: file name
                            // System.out.println(k);
                            chatSchema pane = new chatSchema(w[0].substring(8,10),w[0].substring(10,12),w[1],w[2],w[3],w[4]);
                            gbc.fill = GridBagConstraints.BOTH;
                            gbc.gridx = 0;
                            gbc.gridy = k;
                            gbc.gridwidth = 1;
                            gbc.gridheight = 1;

                            chat.add(pane, gbc);
                            chat.updateUI();
                            chat.setVisible(true);
                            k++;


                            b = new byte[100000];
                            i = 0;
                            if (t == true){
                                try {
                                    Thread.sleep(50);
                                    chatPanel_thread.getVerticalScrollBar().setValue(chatPanel_thread.getVerticalScrollBar().getMaximum());
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            chatPanel_thread.getVerticalScrollBar().setValue(chatPanel_thread.getVerticalScrollBar().getMaximum());
                        }else{
                            b[i] = tmp;
                            i++;
                        }
                    } else {
                        try {
                            if (t == false) {
                                t = true;
                                Thread.sleep(50);
                                chatPanel_thread.getVerticalScrollBar().setValue(chatPanel_thread.getVerticalScrollBar().getMaximum());
                            }
                            sleep(100);
                        } catch (InterruptedException ex) {
                            running = false;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public chattingRoom(String user_id, chatting_client client, ListeningThread t1, String room_id){
        // 스크롤 패널 행스크롤 금지
        chatPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        // 스크롤 속도 조절
        chatPanel.getVerticalScrollBar().setUnitIncrement(16);

        this.user_id = user_id;
        this.client = client;
        this.t1 = t1;

        setContentPane(main);
        chat.setBackground(new Color(186,206,224));
        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);

        try {
            reader = new BufferedInputStream(new FileInputStream("chatting_data/"+room_id+".txt"));
        }catch (Exception e){
            try {
                reader = new BufferedInputStream(new FileInputStream("chatting_data/" + room_id + ".txt"));
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }

        GridBagLayout Gbag = new GridBagLayout();
        chat.setLayout(Gbag);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        new read(gbc, chatPanel).start();

        message.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    send.doClick();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String messageSend = message.getText();

                protocol time = new protocol();
                //client에 message와 room_id보내기
                client.send_messege(4,room_id, user_id, messageSend,false,null);
                message.setText("");
            }
        });
    }

    public class chatSchema extends JPanel{

        private String hours;
        private String minutes;
        private String send_user_id;
        private String message;
        private String file_bool;
        private String file_name;

        private JLabel user;
        private JTextArea text;
        private JLabel time;
        public chatSchema(String hours, String minutes, String send_user_id ,String message, String file_bool, String file_name){
            this.hours = hours;
            this.minutes = minutes;
            this.send_user_id = send_user_id;
            this.message = message;
            this.file_bool = file_bool;
            this.file_name = file_name;

            setSize(480,50);
            setVisible(true);

            text = new JTextArea();

            text.append(message);
            user = new JLabel();
            time = new JLabel();
            String time_message = hours + ":" + minutes;
            time.setText(time_message);
            setBackground(new Color(186,206,224));

            JPanel bullon = new JPanel();
            GridBagLayout Gbag = new GridBagLayout();
            bullon.setLayout(Gbag);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;

            if(user_id.equals(send_user_id)){
                setLayout(new FlowLayout(FlowLayout.RIGHT));
                text.setForeground(new Color(0,0,0));
                text.setBackground(new Color(255,230,0));

                user.setText("나");
                user.setHorizontalTextPosition(SwingConstants.RIGHT);

                gbc.gridx = 3;
                gbc.gridy = 0;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.weightx = 0.25;
                gbc.weighty = 0.5;
                bullon.add(user,gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                gbc.gridwidth = 3;
                gbc.gridheight = 1;
                gbc.weightx = 0.75;
                gbc.weighty = 0.5;
                bullon.add(text,gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.weightx = 0.25;
                gbc.weighty = 0.5;
                bullon.add(time,gbc);
            }
            else{
                setLayout(new FlowLayout(FlowLayout.LEFT));
                text.setForeground(new Color(0,0,0));
                text.setBackground(new Color(255, 255, 255));

                user.setText(send_user_id);

                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.weightx = 0.25;
                gbc.weighty = 0.5;
                bullon.add(user,gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 3;
                gbc.gridheight = 1;
                gbc.weightx = 0.75;
                gbc.weighty = 0.5;
                bullon.add(text,gbc);

                gbc.gridx = 3;
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.weightx = 0.25;
                gbc.weighty = 0.5;
                bullon.add(time,gbc);
            }

            add(bullon);

        }
    }
}
