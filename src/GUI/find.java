package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        emailid.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                emailid.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                if(emailid.getText().equals("")){
                    emailid.setText("Name");
                }
            }
        });

        phoneid.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                phoneid.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                if(emailid.getText().equals("")){
                    emailid.setText("phone");
                }
            }
        });

        idpassword.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                idpassword.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                if(idpassword.getText().equals("")){
                    idpassword.setText("id");
                }
            }
        });

        emailpassword.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                emailpassword.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                if(emailpassword.getText().equals("")){
                    emailpassword.setText("Name");
                }
            }
        });

        phonepassword.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                phonepassword.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                if(phonepassword.getText().equals("")){
                    phonepassword.setText("phone");
                }
            }
        });
        idpassword.setFocusTraversalKeysEnabled(false);
        idpassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    findpassword.doClick();
                }
                else if(e.getKeyCode() == KeyEvent.VK_TAB){
                    emailpassword.setText("");
                    emailpassword.requestFocus();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        emailpassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    findpassword.doClick();
                }
                else if(e.getKeyCode() == KeyEvent.VK_TAB){
                    phonepassword.setText("");
                    phonepassword.requestFocus();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        phonepassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    findpassword.doClick();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        findpassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id_password = idpassword.getText();
                String Email_password = emailpassword.getText();
                String phone_password = phonepassword.getText();

                //type 9
                new repassword();
                setVisible(false);

            }
        });

        emailid.setFocusTraversalKeysEnabled(false);
        emailid.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    findid.doClick();
                }
                else if(e.getKeyCode() == KeyEvent.VK_TAB){
                    phoneid.setText("");
                    phoneid.requestFocus();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        phoneid.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    findid.doClick();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
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
