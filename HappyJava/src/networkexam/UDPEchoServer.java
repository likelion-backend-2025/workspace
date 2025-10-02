package networkexam;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer {
    private static final int PORT = 9876;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try(DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("UDPEchoServer starting...");
            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {
                //클라이언트가 보낸 메시지를 받아옴.
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("받은 메시지 :: "+ message);



                //클라이언트가 보낸 메시지를 다시 클라이언트에게 보내야 함.
                //데이터를 전송하기 위해서는..  데이터, 데이터크기, 클라이언트 ip. 클라이언트 port 가 필요함.
                String responseMessage = "Echo : "+message;
                byte[] responseBuffer = responseMessage.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(
                        responseBuffer,
                        responseBuffer.length,
                        packet.getAddress(),
                        packet.getPort()
                );

                socket.send(sendPacket);
                System.out.println("전송 : "+responseMessage);
            }


        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
