package GUI;

import function.ImgSetSize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import function.loginregister;

public class signUp extends JFrame{
    private JPanel main;
    private JTextField id;
    private JButton signup;
    private JTextField Email;
    private JTextField phone;
    private JPasswordField password;
    private JLabel kakaoIcon;

    public signUp(){
        id.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                id.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                String pwd = new String(id.getText());
                if (pwd.equals("")) {
                    id.setText("id");
                }
            }
        });
        password.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                password.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                String pwd = new String(password.getPassword());
                if (pwd.equals("")) {
                    password.setText("password");
                }
            }
        });
        Email.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                Email.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                String pwd = new String(Email.getText());
                if (pwd.equals("")) {
                    Email.setText("Email");
                }
            }
        });
        phone.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                phone.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                String pwd = new String(phone.getText());
                if (pwd.equals("")) {
                    phone.setText("phone");
                }
            }
        });
        id.setFocusTraversalKeysEnabled(false);
        id.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    signup.doClick();
                }
                else if(e.getKeyCode() == KeyEvent.VK_TAB){
                    password.setText("");
                    password.requestFocus();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        password.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    signup.doClick();
                }
                else if(e.getKeyCode() == KeyEvent.VK_TAB){
                    Email.setText("");
                    Email.requestFocus();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        Email.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    signup.doClick();
                }
                else if(e.getKeyCode() == KeyEvent.VK_TAB){
                    phone.setText("");
                    phone.requestFocus();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        phone.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    signup.doClick();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        ImgSetSize kakaolabel = new ImgSetSize("src/IMG/kakaoIcon.png", 200,100);
        kakaoIcon.setIcon(kakaolabel.getImg());

        setContentPane(main);
        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id_data = id.getText();
                String password_data = new String(password.getPassword());
                String Email_data = Email.getText();
                String phone_data = phone.getText();
                System.out.println("ID: " + id_data + "\tPWD: " + password_data + "\tEmail: " + Email_data + "\tPhone: " + phone_data);
                loginregister registermanager = new loginregister();
                int status = registermanager.register(id_data,password_data);
                if(status!= -1 && status != 2){
                    JOptionPane.showMessageDialog(null, "회원가입 성공");
                    login a = new login();
                    setVisible(false);
                    a.setVisible(true);
                } else if (status==2) {
                    JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
                } else {
                    JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다.");
                }
            }
        });
    }
}
