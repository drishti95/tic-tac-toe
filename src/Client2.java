/**
 * Created by drishti.a on 14/07/17.
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.net.ServerSocket;

public class Client2 {
    public static void main(String[] args) throws IOException {
        Socket server = new Socket("localhost", 8080);
        System.out.println("Just connected to " + server.getRemoteSocketAddress());
        DataInputStream in = new DataInputStream(server.getInputStream());
        OutputStream outToServer = server.getOutputStream();
        DataOutputStream playerTwoOut = new DataOutputStream(outToServer);
        String response = in.readUTF();
        if(response.equals("GAME_STARTED")){
            System.out.println("we can play now");
        }
        Scanner inp=new Scanner(System.in);
        System.out.println("Enter Second Player Name And Value(X or O)");
        String n2= inp.next();
        String v2=inp.next();
        playerTwoOut.writeUTF(n2);
        playerTwoOut.writeUTF(v2);
        server.close();
    }
}
