package networkexam;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPBroadcastReceiver {
    public static void main(String[] args) {
        // 보낸 사람이 사용한 포트와 동일한 포트에서 수신 대기
        final int PORT = 9876;

        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("브로드캐스트 수신 대기 중 (포트: " + PORT + ")...");

            byte[] buffer = new byte[1024];

            while (true) { // 계속해서 메시지 수신
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                // 메시지가 도착할 때까지 대기
                socket.receive(packet);

                String receivedMessage = new String(
                        packet.getData(), 0, packet.getLength()
                );

                System.out.println("받은 메시지: " + receivedMessage);
                System.out.println("보낸 사람: " + packet.getAddress() + ":" + packet.getPort());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}