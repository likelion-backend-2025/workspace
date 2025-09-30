package networkexam;

import java.net.*;

public class UDPBroadcast {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);

            String message = "브로드캐스트 메시지입니다!";
            byte[] buffer = message.getBytes();

            // 브로드캐스트 주소 (255.255.255.255)
            InetAddress broadcastAddress =
                    InetAddress.getByName("255.255.255.255");

            DatagramPacket packet = new DatagramPacket(
                    buffer,
                    buffer.length,
                    broadcastAddress,
                    9876
            );

            socket.send(packet);
            System.out.println("브로드캐스트 메시지 전송 완료");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}