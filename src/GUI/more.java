package GUI;

import chatting_function.ListeningThread;
import chatting_function.chatting_client;
import function.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

public class more extends JFrame {
    private JPanel main;
    private String user_id;
    private chatting_client client;
    private ListeningThread t1;
    public more(String user_id, chatting_client client, ListeningThread t1){
        this.user_id = user_id;
        this.client = client;
        this.t1 = t1;

        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);



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
