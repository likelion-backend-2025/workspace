package networkexam;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPEchoClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9876;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try(DatagramSocket socket = new DatagramSocket();
            Scanner keyboard = new Scanner(System.in);
        ) {
            //키보드로부터 서버에 전송할 문자를 입력 받는다.
            //입력받은 문자를 서버에게 보낸다.

            //서버의 ip를 추상화한 InetAddress 를 생성한다.
            InetAddress serverAddress = InetAddress.getByName(SERVER_HOST);
            byte[] buffer = new byte[BUFFER_SIZE];
            System.out.println("서버에게 보낼 메시지를 입력하세요.  quit 를 입력하면 종료됩니다.");

            while (true) {
                //서버에게 보낼 데이터를 입력
                String message = keyboard.nextLine();

                if("quit".equalsIgnoreCase(message)){
                    break;
                }
                //서버에게 데이터 전송하기위해서 DatagramPacket 생성 .
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                        sendData,
                        sendData.length,
                        serverAddress,
                        SERVER_PORT
                );

                //보내주세요.
                socket.send(sendPacket);


                //서버가 보낸 패킷을 받는코드
                DatagramPacket receivePacket =  new DatagramPacket(buffer, buffer.length);
                socket.receive(receivePacket);

                String responseMessage =
                        new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println("서버 응답::"+responseMessage);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
