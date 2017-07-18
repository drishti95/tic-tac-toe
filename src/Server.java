import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by drishti.a on 7/14/2017.
 */
public class Server {

    public static void main(String args[]) throws IOException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        ServerSocket serverSocket = new ServerSocket(8011);
        int i=0;
        while(i<10){
        System.out.println("Waiting..");
        Socket playerOneSocket = serverSocket.accept();
        Socket playerTwoSocket = serverSocket.accept();
        executorService.submit(new Abc(playerOneSocket,playerTwoSocket));
        i++;
        }
        serverSocket.close();
    }
}
class Abc implements Runnable{
    Socket playerOneSocket;
    Socket playerTwoSocket;
    Abc(Socket one,Socket two)
    {
        playerOneSocket=one;
        playerTwoSocket=two;
    }

    public void run()
    {
        try {
            OutputStream outToServer = playerOneSocket.getOutputStream();
            DataOutputStream playerOneOut = new DataOutputStream(outToServer);

            OutputStream playerTwoSocketOutputStream = playerTwoSocket.getOutputStream();
            DataOutputStream playerTwoOut = new DataOutputStream(playerTwoSocketOutputStream);
            playerOneOut.writeUTF("GAME_STARTED");
            playerTwoOut.writeUTF("GAME_STARTED");
            DataInputStream in = new DataInputStream(playerOneSocket.getInputStream());
            DataInputStream in1 = new DataInputStream(playerTwoSocket.getInputStream());
            String name1 = in.readUTF();
            String value1 = in.readUTF();
            String name2 = in1.readUTF();
            String value2 = in1.readUTF();
            System.out.println(name1 + " is the first player with value : " + value1);
            System.out.println(name2 + " is the second player with value : " + value2);
            Player p1 = new Player(name1, value1);
            Player p2 = new Player(name2, value2);
            Board b = new Board(p1, p2);
            int cnt = 0;
            while (!(b.gameIsWon(value1) || b.gameIsWon(value2) || b.isBoardFull())) {
                if (cnt % 2 == 0) {
                    playerOneOut.writeInt(2);
                    int movex = in.readInt();
                    playerOneOut.writeInt(2);
                    int movey = in.readInt();
                    while (b.board[movex][movey] != "_") {

                        playerOneOut.writeInt(3);
                        playerOneOut.writeUTF("Error, invalid move");
                        playerOneOut.writeInt(2);
                        movex = in.readInt();
                        playerOneOut.writeInt(2);
                        movey = in.readInt();

                    }

                    b.board[movex][movey] = p1.value;
                    b.printBoard();
                    String bstate = b.show();
                    playerOneOut.writeInt(3);
                    playerOneOut.writeUTF(bstate);
                    if (b.gameIsWon(p1.getValue())) {
                        System.out.println(p1.getName() + " wins!");
                        playerOneOut.writeInt(4);
                        return;
                    } else if (b.isBoardFull()) {
                        playerOneOut.writeInt(4);
                        System.out.println("Draw!");
                    }

                } else {
                    playerTwoOut.writeInt(2);
                    int movex = in1.readInt();
                    playerTwoOut.writeInt(2);
                    int movey = in1.readInt();
                    while (b.board[movex][movey] != "_") {

                        playerTwoOut.writeInt(3);
                        playerTwoOut.writeUTF("Error, invalid move");
                        playerTwoOut.writeInt(2);
                        movex = in1.readInt();
                        playerTwoOut.writeInt(2);
                        movey = in1.readInt();

                    }

                    b.board[movex][movey] = p2.value;
                    b.printBoard();
                    String bstate = b.show();
                    playerTwoOut.writeInt(3);
                    playerTwoOut.writeUTF(bstate);
                    if (b.gameIsWon(p2.getValue())) {
                        playerTwoOut.writeInt(4);
                        System.out.println(p2.getName() + " wins!");
                        return;
                    } else if (b.isBoardFull()) {
                        playerTwoOut.writeInt(4);
                        System.out.println("Draw!");
                    }

                }
                cnt++;
            }
        }
        catch(Exception e){
            e.getMessage();
    }
    }
}