package concave;

import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

import chatting.protocol;


public class GameMain {
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
    public GameMain() {

        GUI_board guiB = new concave.GUI_board(ad);

        Player playerOne = new Player("p1", "p1입니다.");
        Player playerTwo = new Player("p2", "p2입니다.");
        Board board = new concave.Board();
        playerSetting(playerOne, playerTwo);

        Scanner scanner = new Scanner(System.in);
        int x, y;

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
                //board.show();
                if (board.win(x, y, playerOne))
                {
                    JOptionPane aa=new JOptionPane();
                    aa.showMessageDialog(null, "승리자는 " + playerOne.getPlayerName() + "입니다.");
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
                if (board.win(x, y, playerTwo))
                {
                    JOptionPane aa=new JOptionPane();
                    aa.showMessageDialog(null, "승리자는 " + playerTwo.getPlayerName() + "입니다.");
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
                //board.show();
                if (board.win(x, y, playerTwo))
                {
                    JOptionPane aa=new JOptionPane();
                    aa.showMessageDialog(null, "승리자는 " + playerTwo.getPlayerName() + "입니다.");
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
                if (board.win(x, y, playerOne))
                {
                    JOptionPane aa=new JOptionPane();
                    aa.showMessageDialog(null, "승리자는 " + playerOne.getPlayerName() + "입니다.");
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

