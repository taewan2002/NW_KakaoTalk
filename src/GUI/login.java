package GUI;

import javax.swing.*;
import function.*;

import java.awt.event.*;
import java.io.File;

public class login extends JFrame{
    private JPanel main;
    private JButton searchId;
    private JButton repassword;
    private JTextField id;
    private JButton login;
    private JLabel kakaoicon;
    private JButton signup;
    private JPasswordField password;
    public String makedir(){
        // 캐싱된 체팅 파일들 모두 지우고 다시 만들기

        String path = "chatting_data";
        File Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try{
                Folder.mkdir(); //폴더 생성합니다.
                System.out.println("폴더가 생성되었습니다.");
                return Folder.getAbsolutePath();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }else {
            System.out.println("이미 폴더가 생성되어 있습니다.");
            return null;
        }
        return null;
    }

    public login(){
        makedir(); // 캐싱된 체팅 파일들 모두 지우고 다시 만들기
        ImgSetSize kakaolabel = new ImgSetSize("src/IMG/kakaoIcon.png", 200,100);
        kakaoicon.setIcon(kakaolabel.getImg());

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
                if(id.getText().equals("")){
                    id.setText("전화번호, 사용자이름 또는 이메일");
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
                    password.setText("비밀번호486");
                }
            }
        });

        // 엔터키 눌렀을 때 로그인, 탭키를 눌렀을 때 다음칸으로 이동하고 택스트 지우기
        id.setFocusTraversalKeysEnabled(false);
        id.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login.doClick();
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
                    login.doClick();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });



        setContentPane(main);
        setSize(480,650);
        setBounds(0,0,480,650);
        setVisible(true);

        //로그인 버튼
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                로그인 기능 구현
//                String getId = id.getText();
//                char[] temp = password.getPassword();
//                String getPw = new String(temp);
//                System.out.println("ID: " + getId + "\tPWD: " + getPw + "\n");
//                loginregister manager = new loginregister();
//                int session_id = manager.login(getId,getPw);
//                if(session_id!=-1){
//                    chatting_client client = new chatting_client(getId);
//                    client.run();
//                    ListeningThread t1 = client.get_listening();
//                    friends a = new friends(session_id,getId,client,t1);
//                    setVisible(false);
//                    a.setVisible(true);
//                }
//                else{
//                    System.out.println("x");
//                }
                new friends();
                setVisible(false);
            }
        });

        //아이디 찾기
        searchId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //회원가입
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //비밀번호 찾기
        repassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
