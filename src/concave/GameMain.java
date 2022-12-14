package concave;

import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

import chatting.protocol;
import chatting_function.ListeningThread;
import chatting_function.chatting_client;


public class GameMain {
    private String user_id;
    private chatting_client client;
    private ListeningThread t1;
    private String room_id;


    private static int black = 1;
    private static int white = 2;

    public static int temp_x = -1;
    public static int temp_y = -1;

    public static int getX()
    {
        int t_x;
        while(true)
        {
            if (temp_x != -1)
            {
                t_x = temp_x;
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        initialX();
        return t_x;
    }
    public static int getY()
    {
        int t_y = temp_y;
        initialY();
        return t_y;
    }

    private static void initialX()
    {
        temp_x = -1;
    }
    private static void initialY()
    {
        temp_y = -1;
    }

    private GameMain ad;



    // 메인 함수
    public GameMain(chatting_client client, String room_id, String user_id, ListeningThread t1) {
        this.client = client;
        this.room_id = room_id;
        this.user_id = user_id;
        this.t1 = t1;

        GUI_board guiB = new concave.GUI_board(ad);

        Player playerOne = new Player("p1", "p1입니다.");
        Player playerTwo = new Player("p2", "p2입니다.");
        Board board = new concave.Board();
        playerSetting(playerOne, playerTwo);

        Scanner scanner = new Scanner(System.in);
        int x, y;
        String message;

        while(true)
        {
            if (playerOne.getPlayerColor() == black) {
                //board.show();
                //System.out.println(playerOne.getPlayerName() + "이 둘 차례입니다.");
                //System.out.print("둘 위치를 입력하세요 : ");
                x = getX();
                y = getY();
                //x = scanner.nextInt();
                //y = scanner.nextInt();
                while (!board.put(x, y, playerOne, guiB)) {
                    //System.out.println("놓을 수 없는 자리입니다.");
                    //System.out.println("둘 위치를 다시 선택하세요 : ");
                    x = getX();
                    y = getY();
                }
                System.out.println("!오목!" + Integer.toString(x) + "!" + Integer.toString(y) + "!" + playerOne.getPlayerName());
                message = "!오목!" + Integer.toString(x) + "!" + Integer.toString(y) + "!" + playerOne.getPlayerName();
                client.send_messege(4,room_id, user_id, message,false,null);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //board.show();
                if (board.win(x, y, playerOne))
                {
                    JOptionPane aa=new JOptionPane();
//                    aa.showMessageDialog(null, "승리자는 " + playerOne.getPlayerName() + "입니다.");
                    aa.showMessageDialog(null, "승리자는 " + user_id + "입니다.");
                    break;
                }
                //System.out.println(playerTwo.getPlayerName() + "이 둘 차례입니다.");
                //System.out.print("둘 위치를 입력하세요 : ");
                x = getX();
                y = getY();
                while (!board.put(x, y, playerTwo, guiB)) {
                    //System.out.println("놓을 수 없는 자리입니다.");
                    //System.out.println("둘 위치를 다시 선택하세요 : ");
                    x = getX();
                    y = getY();
                }
                System.out.println("!오목!" + Integer.toString(x) + "!" + Integer.toString(y) + "!" + playerTwo.getPlayerName());
                message = "!오목!" + Integer.toString(x) + "!" + Integer.toString(y) + "!" + playerTwo.getPlayerName();
                client.send_messege(4,room_id, user_id, message,false,null);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (board.win(x, y, playerTwo))
                {
                    JOptionPane aa=new JOptionPane();
//                    aa.showMessageDialog(null, "승리자는 " + playerOne.getPlayerName() + "입니다.");
                    aa.showMessageDialog(null, "승리자는 " + user_id + "입니다.");
                    break;
                }
            }
            else {
                //board.show();
                //System.out.println(playerTwo.getPlayerName() + "이 둘 차례입니다.");
                //System.out.print("둘 위치를 입력하세요 : ");
                x = getX();
                y = getY();
                while (!board.put(x, y, playerTwo, guiB)) {
                    //System.out.println("놓을 수 없는 자리입니다.");
                    //System.out.println("둘 위치를 다시 선택하세요 : ");
                    x = getX();
                    y = getY();
                }
                System.out.println("!오목!" + Integer.toString(x) + "!" + Integer.toString(y) + "!" + playerTwo.getPlayerName());
                message = "!오목!" + Integer.toString(x) + "!" + Integer.toString(y) + "!" + playerTwo.getPlayerName();
                client.send_messege(4,room_id, user_id, message,false,null);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //board.show();
                if (board.win(x, y, playerTwo))
                {
                    JOptionPane aa=new JOptionPane();
//                    aa.showMessageDialog(null, "승리자는 " + playerOne.getPlayerName() + "입니다.");
                    aa.showMessageDialog(null, "승리자는 " + user_id + "입니다.");
                    break;
                }
                //System.out.println(playerOne.getPlayerName() + "이 둘 차례입니다.");
                //System.out.print("둘 위치를 입력하세요 : ");
                x = getX();
                y = getY();
                while (!board.put(x, y, playerOne, guiB)) {
                    //System.out.println("놓을 수 없는 자리입니다.");
                    //System.out.println("둘 위치를 다시 선택하세요 : ");
                    x = getX();
                    y = getY();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("!오목!" + Integer.toString(x) + "!" + Integer.toString(y) + "!" + playerOne.getPlayerName());
                message = "!오목!" + Integer.toString(x) + "!" + Integer.toString(y) + "!" + playerOne.getPlayerName();
                client.send_messege(4,room_id, user_id, message,false,null);
                if (board.win(x, y, playerOne))
                {
                    JOptionPane aa=new JOptionPane();
//                    aa.showMessageDialog(null, "승리자는 " + playerOne.getPlayerName() + "입니다.");
                    aa.showMessageDialog(null, "승리자는 " + user_id + "입니다.");
                    break;
                }
            }
        }
        System.out.println("승리자는 " + board.getWinner().getPlayerName() + "입니다.");
    }

    // 플레이어 순서 결정
    public static void playerSetting(Player playerOne, Player playerTwo)
    {
        Random random = new Random();
        if(random.nextBoolean() == true)
        {
            playerOne.setPlayerColor(black);
            playerTwo.setPlayerColor(white);
        }
        else
        {
            playerOne.setPlayerColor(white);
            playerTwo.setPlayerColor(black);
        }
    }
}

