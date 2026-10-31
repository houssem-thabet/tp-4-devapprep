import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Step2Client {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Username: ");
        String username = sc.nextLine();

        DatagramSocket socket = new DatagramSocket();
        InetAddress serverIp = InetAddress.getByName("localhost");

        while (true) {
            String text = sc.nextLine();
            if (text.equals("exit")) break;
            String msg = "[" + username + "] : " + text;

            byte[] data = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, serverIp, 1234);
            socket.send(packet);
        }
        socket.close();
    }
}