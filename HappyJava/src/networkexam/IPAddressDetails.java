package networkexam;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPAddressDetails {
    public static void main(String[] args) {
        try {
            InetAddress addr = InetAddress.getLocalHost();

            System.out.println("호스트명: " + addr.getHostName());
            System.out.println("IP 주소: " + addr.getHostAddress());

            // byte 배열로 IP 주소 얻기
            byte[] ipAddr = addr.getAddress();
            System.out.print("IP 주소 (byte): ");
            for (int i = 0; i < ipAddr.length; i++) {
                System.out.print((ipAddr[i] & 0xFF)); // 부호 없는 정수로 변환
                if (i < ipAddr.length - 1) {
                    System.out.print(".");
                }
            }
            System.out.println();

            // 루프백 주소 확인
            System.out.println("루프백 주소?: " + addr.isLoopbackAddress());

            // 사설 IP 확인
            System.out.println("사설 IP?: " + addr.isSiteLocalAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}