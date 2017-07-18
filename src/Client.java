import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.net.ServerSocket;
/**
 * Created by drishti.a on 14/07/17.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket server = new Socket("localhost", 8011);
        System.out.println("Just connected to " + server.getRemoteSocketAddress());
        DataInputStream in = new DataInputStream(server.getInputStream());
        OutputStream outToServer = server.getOutputStream();
        DataOutputStream playerOut = new DataOutputStream(outToServer);

        String response = in.readUTF();
        if(response.equals("GAME_STARTED")){
            System.out.println("we can play now");
        }
        Scanner inp=new Scanner(System.in);
        System.out.println("Enter Player Name And VAlue(X or O");
        String n1= inp.next();
        String v1=inp.next();
        playerOut.writeUTF(n1);
        playerOut.writeUTF(v1);
        int re=0;
        while(true)
        {
            //System.out.print(5);
            re=in.readInt();
            if(re==3) {
                System.out.println(in.readUTF());
                System.out.println();
                System.out.println();

            }
            else if(re==2)
            {
                System.out.println("enter integer");

               int v=inp.nextInt();
                playerOut.writeInt(v);
            }
            else if(re==4)
            {
                System.out.println("Game over");
                server.close();
                return;
            }
        }
        //server.close();
    }
}
