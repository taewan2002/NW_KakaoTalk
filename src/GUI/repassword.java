package GUI;

import function.ImgSetSize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class repassword extends JFrame{
    private JPanel main;
    private JPasswordField newPassword;
    private JButton commit;
    private JLabel kakaoicon;
    private JPasswordField duplicate;

    public repassword(){
        newPassword.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                newPassword.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                if(newPassword.getText().equals("")){
                    newPassword.setText("비밀번호");
                }
            }
        });

        duplicate.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                duplicate.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                String pwd = new String(duplicate.getPassword());
                if (pwd.equals("")) {
                    duplicate.setText("비밀번호");
                }
            }
        });

        newPassword.setFocusTraversalKeysEnabled(false);
        newPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    commit.doClick();
                }
                else if(e.getKeyCode() == KeyEvent.VK_TAB){
                    newPassword.setText("");
                    newPassword.requestFocus();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        duplicate.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    commit.doClick();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        ImgSetSize kakaolabel = new ImgSetSize("src/IMG/kakaoIcon.png", 200,100);
        kakaoicon.setIcon(kakaolabel.getImg());

        setContentPane(main);
        setSize(480,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);

        commit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String new_password = new String(newPassword.getPassword());
                String duplicate_password = new String(duplicate.getPassword());
                //비번 변경
                JOptionPane id_option = new JOptionPane();
                if(new_password.equals(duplicate_password)){
                    id_option.showMessageDialog(null, "비밀번호가 성공적으로 바뀌었습니다");
                    new login();
                    setVisible(false);
                }
                else{
                    id_option.showMessageDialog(null, "비밀번호 중복 확인이 실패했습니다");
                }
            }
        });
    }
}
