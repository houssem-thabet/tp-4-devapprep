import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Step2Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(1234));
        System.out.println("Server waiting on port 1234...");

        byte[] buf = new byte[1024];
        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String msg = new String(packet.getData(), 0, packet.getLength());
            System.out.println("From " + packet.getAddress().getHostAddress() + ":" + packet.getPort() + " -> " + msg);
        }
    }
}