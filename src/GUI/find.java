package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                String Email_password = emailid.getText();
                String phone_password = phoneid.getText();

                JOptionPane id_option = new JOptionPane();
                if(/**아이디 존재**/false){
                    String id = new String("asd");//아이디 받아오기
                    id_option.showMessageDialog(null, id);

                    new login();
                    setVisible(false);
                }
                else{
                    id_option.showMessageDialog(null, "아이디가 없음");
                }
            }
        });
    }

}
