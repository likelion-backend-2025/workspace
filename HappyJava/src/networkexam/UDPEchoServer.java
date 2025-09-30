package networkexam;

import java.net.*;

public class UDPEchoServer {
    private static final int PORT = 9876;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("UDP Echo 서버 시작 (포트: " + PORT + ")");

            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {
                // 데이터 수신
                DatagramPacket receivePacket =
                        new DatagramPacket(buffer, buffer.length);
                socket.receive(receivePacket);

                String received = new String(
                        receivePacket.getData(),
                        0,
                        receivePacket.getLength()
                );
                System.out.println("받은 메시지: " + received);

                // 클라이언트 정보
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Echo 응답
                String response = "Echo: " + received;
                byte[] responseData = response.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(
                        responseData,
                        responseData.length,
                        clientAddress,
                        clientPort
                );

                socket.send(sendPacket);
                System.out.println("응답 전송: " + response);
            }

        } catch (Exception e) {
            System.err.println("서버 오류: " + e.getMessage());
        }
    }
}