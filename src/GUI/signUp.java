package GUI;

import function.ImgSetSize;

import javax.swing.*;
import java.awt.event.*;

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
        setBounds(0,0,480,650);
        setVisible(true);
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id_data = id.getText();
                String password_data = new String(password.getPassword());
                String Email_data = Email.getText();
                String phone_data = phone.getText();

                new login();
                setVisible(false);
            }
        });
    }

    /**
     * -- roadmap
     * -- 회원가입 화면 실행
     * -- 이메일
     * -- 이름
     * -- 생년월일
     * -- 아이디 입력 + (중복확인버튼) --> primary key
     * -- 비밀번호 입력 + 비밀번호 한번 더 입력
     *
     * 회원가입 성공시
     * id = id.getText();
     * pw = pw.getPassword();
     * pw = md5(pw); // 비밀번호 암호화
     * new login();
     *
     * 중복시
     * System.out.println("이미 존재하는 아이디 입니다.");
     * System.out.println("비밀번호가 다릅니다..");
     */
}
