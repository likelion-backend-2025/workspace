package networkexam;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPBroadcastReceiver {
    public static void main(String[] args) {
        final int PORT = 9876;

        try(DatagramSocket socket = new DatagramSocket(PORT)){
            System.out.println("브로드캐스트 수신 대기 중 :::  포트번호 :"+ PORT);

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer,buffer.length);

            //메시지가 도착 할때까지 대기!!!
            socket.receive(packet);

            String receiveMessage = new String(packet.getData(),0,packet.getLength());

            System.out.println("받은 메시지 :: "+ receiveMessage);
            System.out.println("보낸 사람 :: "+ packet.getAddress() + ":" + packet.getPort() );


        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
