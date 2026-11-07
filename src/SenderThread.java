import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SenderThread extends Thread {
    private DatagramSocket socket;
    private InetAddress serverIp;
    private String username;

    public SenderThread(DatagramSocket socket, InetAddress serverIp, String username) {
        this.socket = socket;
        this.serverIp = serverIp;
        this.username = username;
    }

    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(System.in);

            String hello = username + " joined the chat";
            byte[] h = hello.getBytes();
            socket.send(new DatagramPacket(h, h.length, serverIp, 1234));

            while (true) {
                String text = sc.nextLine();
                if (text.equals("exit")) break;

                String msg = "[" + username + "] : " + text;
                byte[] data = msg.getBytes();
                socket.send(new DatagramPacket(data, data.length, serverIp, 1234));
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}