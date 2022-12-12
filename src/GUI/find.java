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
    private JTextField idpassword;
    private JTextField emailpassword;
    private JTextField phonepassword;
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
                String id_password = idpassword.getText();
                String Email_password = emailpassword.getText();
                String phone_password = phonepassword.getText();

                new repassword();
                setVisible(false);

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
