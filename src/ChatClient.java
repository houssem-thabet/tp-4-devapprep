import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Username: ");
        String username = sc.nextLine();

        DatagramSocket socket = new DatagramSocket();
        InetAddress serverIp = InetAddress.getByName("localhost");

        ReceiverThread receiver = new ReceiverThread(socket);
        SenderThread sender = new SenderThread(socket, serverIp, username);

        receiver.start();
        sender.start();
    }
}