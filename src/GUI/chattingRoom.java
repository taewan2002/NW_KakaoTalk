package GUI;

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

    boolean running = true;
    BufferedInputStream reader = null;

    private class read extends Thread{

        public read(){
        }
        public void run() {
            int i=0;
            int k=0;
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
                            chatSchema pane = new chatSchema(w[0].substring(8,10),w[0].substring(10,12),w[1],w[2],w[3],w[4]);
                            chat.add(pane);
                            k++;

                            b= new byte[100000];
                            i=0;
                        }else{
                            b[i]=tmp;
                            i++;
                        }
                    } else {
                        try {
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
        this.user_id = user_id;
        this.client = client;
        this.t1 = t1;

        try {
            reader = new BufferedInputStream(new FileInputStream("chatting_data/"+room_id+".txt"));
        }catch (Exception e){
            try {
                reader = new BufferedInputStream(new FileInputStream("chatting_data/" + room_id + ".txt"));
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }

        GridLayout Gbag = new GridLayout(0,1);
        chat.setLayout(Gbag);

        new read().start();


        setBackground(new Color(186,206,224));
        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);

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

            text = new JTextArea();

            if(user_id.equals(send_user_id)){
                setLayout(new FlowLayout(FlowLayout.RIGHT));
                text.setForeground(new Color(0,0,0));
                text.setBackground(new Color(255,230,0));
            }
            else{
                setLayout(new FlowLayout(FlowLayout.LEFT));
                text.setForeground(new Color(0,0,0));
                text.setBackground(new Color(255,255,255));
            }
            text.append(message);
            add(text);

            String time_message = hours + ":" + minutes;
            time.setText(time_message);
            add(time);

        }
    }
}
