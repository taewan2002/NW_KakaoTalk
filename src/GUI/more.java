package GUI;

import javax.swing.*;
import java.awt.*;

public class more extends JFrame {

    private JButton 친구Button;
    private JButton 채팅방Button;
    private JButton 공공데이터Button;
    private JButton 톱니바퀴Button;
    private JButton 친구차단Button;
    private JButton 비밀번호변경Button;
    private JButton 탈퇴Button;
    private JButton 내정보관리Button;

    public more(){
        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
