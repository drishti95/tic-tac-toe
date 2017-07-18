import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {



    public static void main(String[] args) {



        Scanner in=new Scanner(System.in);
        System.out.println("Enter First Player Name And VAlue(X or O)");
        String n1= in.next();
        String v1=in.next();
        Player P1=new Player(n1,v1);
        System.out.println("Enter Second Player Name And VAlue(X or O");
        String n2= in.next();
        String v2=in.next();
        Player P2=new Player(n2,v2);
        Board b = new Board(P1,P2);
        b.bs=0;

        while(b.bs!=1 && b.bs!=2)
        {

           Player p=b.getNextPlayer();
            int row=0,col=0;
            if(!b.isBoardFull()) {

                boolean valid = false;

                // Loop until a certain player makes a valid move
                while (!valid) {
                    System.out.print(p.getName() + ", choose your row: \n> ");
                    row =in.nextInt() - 1;

                    System.out.print(p.getName()+ ", you have chosen row " + (row + 1) + ". Choose your column: \n> ");
                    col = in.nextInt() - 1;

                    if (b.board[row][col] == "") {
                        b.board[row][col] = p.getValue();

                        b.printBoard();
                        valid = true; // This will allow players to switch turns
                    } else {
                        System.out.println("This slot is taken, try again!");
                    }
                }
            }
            if (b.gameIsWon(p.getValue())) {
                System.out.println(p.getName() + " wins!");
                return;
            }
            else if(b.isBoardFull()) {
                System.out.println("Draw!");
            }

        }
    }

}
