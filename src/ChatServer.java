import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;

public class ChatServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(1234));
        System.out.println("Chat server waiting on port 1234...");

        ArrayList<SocketAddress> clients = new ArrayList<>();

        byte[] buf = new byte[1024];
        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String msg = new String(packet.getData(), 0, packet.getLength());
            SocketAddress sender = packet.getSocketAddress();
            System.out.println("From " + sender + " -> " + msg);

            if (!clients.contains(sender)) {
                clients.add(sender);
            }

            byte[] data = msg.getBytes();
            for (SocketAddress c : clients) {
                if (!c.equals(sender)) {
                    socket.send(new DatagramPacket(data, data.length, c));
                }
            }
        }
    }
}