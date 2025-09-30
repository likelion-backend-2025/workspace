package networkexam;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExample {
    public static void main(String[] args) {
        try {
            // 특정 호스트의 IP 주소 조회
            InetAddress google = InetAddress.getByName("www.google.com");
            System.out.println("Google IP: " + google.getHostAddress());
            System.out.println("Google Host: " + google.getHostName());

            // 로컬 호스트 정보
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("Local Host: " + localhost.getHostName());
            System.out.println("Local IP: " + localhost.getHostAddress());

            // 모든 IP 주소 조회 (여러 IP를 가진 경우)
            InetAddress[] allAddresses = InetAddress.getAllByName("www.naver.com");
            for (InetAddress addr : allAddresses) {
                System.out.println("Naver IP: " + addr.getHostAddress());
            }

            // 도달 가능성 확인
            boolean reachable = google.isReachable(3000); // 3초 타임아웃
            System.out.println("Google 도달 가능: " + reachable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}