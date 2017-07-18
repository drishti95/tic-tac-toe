import java.util.Scanner;

/**
 * Created by drishti.a on 14/07/17.
 */
public class Board  {

    static String[][] board = new String[3][3];
    Player p1,p2;
    int x=0;
    public Board(Player P1,Player P2)
    {
        int i,j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++)
                board[i][j]= "_";
        }
        p1=P1;
        p2=P2;
    }
    public int bs=0;
    public boolean isBoardFull() {
        for (int row=0 ;row<3;row++) {
            for (int col=0;col<3;col++) {
                if (board[row][col]=="_" ) {
                    return false;
                }
            }
        }
        bs=2;
        return true;
    }

    public void printBoard() {
        for (String[] row : board) {
            for (String col : row) {
                System.out.print("|" + (col == null? "_" : col) + "|");
            }

            System.out.println("\n---------");
        }
    }
    public String show() {
        String temp="";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp=temp+board[i][j] + " ";
            }
            temp=temp+"\n";
        }
        return temp;
    }
    public Player getNextPlayer()
    {
        if(x==0)
        {
            x=1;return p1;
        }
        else
        {
            x=0;return p2;
        }
    }
    public boolean gameIsWon(String c)
    {
        boolean p=true;int i,j,cnt;
        for(i=0;i<3;i++)
        {
            cnt=0;
            for(j=0;j<3;j++)
            {
                if(board[i][j].equals(c))
                    cnt++;
            }
            if(cnt==3)
            {
                bs=1;return true;}
        }
        for(i=0;i<3;i++)
        {
            cnt=0;
            for(j=0;j<3;j++)
            {
                if(board[j][i].equals(c))
                    cnt++;
            }
            if(cnt==3)
            {
                bs=1;return true;}
        }

        if(board[0][0].equals(c) && board[1][1].equals(c) && board[2][2].equals(c))
        {
            bs=1;return true;}
        if(board[0][2].equals(c) && board[1][1].equals(c) && board[2][0].equals(c))
        {
            bs=1;return true;}
        return false;

    }


}






