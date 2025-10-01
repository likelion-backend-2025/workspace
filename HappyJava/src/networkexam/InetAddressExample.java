package networkexam;

import java.net.InetAddress;

public class InetAddressExample {
    public static void main(String[] args) throws Exception {
        InetAddress address = InetAddress.getByName("www.google.com");
        System.out.println("ip::"+address.getHostAddress());
        System.out.println("host::"+address.getHostName());

        boolean reachable = address.isReachable(3000);
        System.out.println("도달가능 :: "+ reachable);


        InetAddress[] allAddress = InetAddress.getAllByName("www.google.com");
        for (InetAddress addr : allAddress) {
            System.out.println("ip::"+addr.getHostAddress());
        }



        // 로컬 호스트의 IP 주소와 이름 조회
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("Local Host Name: " + localHost.getHostName());
        System.out.println("Local IP Address: " + localHost.getHostAddress());


        // 바이트 배열로 IP 주소 확인
        byte[] ip = localHost.getAddress();
        System.out.print("Byte array format: ");
        for (int i = 0; i < ip.length; i++) {
            System.out.print((ip[i] & 0xFF)); // 부호 없는 바이트로 출력
            if (i != ip.length - 1) {
                System.out.print(".");
            }
        }



    }

}
