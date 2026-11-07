import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiverThread extends Thread {
    private DatagramSocket socket;

    public ReceiverThread(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        byte[] buf = new byte[1024];
        try {
            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println(msg);
            }
        } catch (Exception e) {

            System.out.println("Disconnected.");
        }
    }
}