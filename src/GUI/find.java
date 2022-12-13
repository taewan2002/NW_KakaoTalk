package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import function.*;

public class find extends JFrame{
    private JTextField emailid;
    private JTextField phoneid;
    private JButton findid;
    private JTextField getID;
    private JTextField getName;
    private JTextField getPhoneNum;
    private JButton findpassword;
    private JPanel main;

    public find(){
        setContentPane(main);
        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);
        findpassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id_password = getID.getText();
                String name_password = getName.getText();
                String phone_password = getPhoneNum.getText();
                // 비밀번호 찾기
                get_data data = new get_data();
                data.setType9(id_password, name_password, phone_password);
                data.start();
                if(data.getTf()){
                    // db에 회원 정보가 있으면 비밀번호 변경 창으로 넘어감
                    new repassword(id_password);
                    setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null, "존재하지 않는 정보입니다.");
                }
            }
        });
        findid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = emailid.getText();
                String phone = phoneid.getText();

                JOptionPane id_option = new JOptionPane();
                get_data data = new get_data();
                data.setType8(name, phone);
                data.start();
                if (data.getEmail() == null) {
                    id_option.showMessageDialog(null, "일치하는 정보가 없습니다.", "ID 찾기", JOptionPane.ERROR_MESSAGE);
                } else {
                    id_option.showMessageDialog(null, "회원님의 ID는 " + data.getEmail() + " 입니다.", "ID 찾기", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

}
