package concave;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import javax.swing.JInternalFrame;
import java.awt.Graphics;

public class Board {
    private int boardXSize = 15, boardYSize = 15;
    private int[][] boardMap = new int[boardXSize][boardYSize];
    private Player playerOne, playerTwo;
    private Player Winner;


    public Board()
    {
        for(int i=0;i<boardXSize;i++)
        {
            for(int j=0;j<boardYSize;j++)
            {
                boardMap[i][j] = 0;
            }
        }
    }

    private static int x;
    private static int y;
    private static GUI_board guiB;
    public boolean put(int x, int y, Player player, GUI_board guiB)
    {
        this.x = x;
        this.y = y;
        this.guiB = guiB;

        if(boardMap[x][y] != 0)
            return false;
        boardMap[x][y] = player.getPlayerColor();



        JButton btn2 = new JButton();
        btn2.setBounds(31*y+4, 31*x+44, 26, 26);

        //Container contentPane = guiB.getContentPane();
        //contentPane.setBounds(0, 590, 50, 60);
        //guiB.layeredPane.add(contentPane);


        if (player.getPlayerColor() == 1)
        {
            btn2.setBackground(new Color(0,0,0));
            //bt_img = new JButton(img);
        }
        else
        {
            btn2.setBackground(new Color(255,255,255));

        }
        guiB.panel.add(btn2);

        guiB.panel.setVisible(true);

        return true;
    }





    public void show()
    {
        for (int i=0;i<boardXSize;i++)
        {
            for(int j=0;j<boardYSize;j++)
            {
                System.out.print(boardMap[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public Player getWinner()
    {
        return Winner;
    }

    public boolean win(int x, int y, Player player)
    {
        if (win1(x,y)||win2(x,y)||win3(x,y)||win4(x,y)||win5(x,y)||win6(x,y)||win7(x,y)||win8(x,y)||
                win9(x,y)||win10(x,y)||win11(x,y)||win12(x,y)||win13(x,y)||win14(x,y)||win15(x,y)||win16(x,y)||
                win17(x,y)||win18(x,y)||win19(x,y)||win20(x,y))
        {
            Winner = player;
            return true;
        }
        return false;
    }

    //한번 돌을 놓을 때마다 총 20가지 승리 가능 경우의 수가 존재

    public boolean win1(int x, int y)
    {
        try
        {
            for(int i=y;i<y+5;i++)
            {
                if(boardMap[x][i]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win2(int x, int y)
    {
        try
        {
            for(int i=x,j=y;i<x+5;i++,j--)
            {
                if(boardMap[i][j]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win3(int x, int y)
    {
        try
        {
            for(int i=x;i<x+5;i++)
            {
                if(boardMap[i][y]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win4(int x, int y)
    {
        try
        {
            for(int i=x,j=y;i<x+5;i++,j++)
            {
                if(boardMap[i][j]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win5(int x, int y)
    {
        try
        {
            for(int i=y;i>y-5;i--)
            {
                if(boardMap[x][i]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win6(int x, int y)
    {
        try
        {
            for(int i=x,j=y;i>x-5;i--,j++)
            {
                if(boardMap[i][j]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win7(int x, int y)
    {
        try
        {
            for(int i=x;i>x-5;i--)
            {
                if(boardMap[i][y]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win8(int x, int y)
    {
        try
        {
            for(int i=x,j=y;i>x-5;i--,j--)
            {
                if(boardMap[i][j]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win9(int x, int y)
    {
        try
        {
            for(int i=y-1;i<y+4;i++)
            {
                if(boardMap[x][i]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win10(int x, int y)
    {
        try
        {
            for(int i=x-1,j=y+1;i<x+4;i++,j--)
            {
                if(boardMap[i][j]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win11(int x, int y)
    {
        try
        {
            for(int i=x-1;i<x+4;i++)
            {
                if(boardMap[i][y]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win12(int x, int y)
    {
        try
        {
            for(int i=x-1,j=y-1;i<x+4;i++,j++)
            {
                if(boardMap[i][j]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win13(int x, int y)
    {
        try
        {
            for(int i=y+1;i>y-4;i--)
            {
                if(boardMap[x][i]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win14(int x, int y)
    {
        try
        {
            for(int i=x+1,j=y-1;i>x-4;i--,j++)
            {
                if(boardMap[i][j]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win15(int x, int y)
    {
        try
        {
            for(int i=x+1;i>x-4;i--)
            {
                if(boardMap[i][y]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win16(int x, int y)
    {
        try
        {
            for(int i=x+1,j=y+1;i>x-4;i--,j--)
            {
                if(boardMap[i][j]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win17(int x, int y)
    {
        try
        {
            for(int i=y-2;i<y+3;i++)
            {
                if(boardMap[x][i]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win18(int x, int y)
    {
        try
        {
            for(int i=x-2;i<x+3;i++)
            {
                if(boardMap[i][y]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win19(int x, int y)
    {
        try
        {
            for(int i=y+2;i>y-3;i--)
            {
                if(boardMap[x][i]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

    public boolean win20(int x, int y)
    {
        try
        {
            for(int i=x+2;i>x-3;i--)
            {
                if(boardMap[i][y]!=boardMap[x][y])
                {
                    return false;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }

}
