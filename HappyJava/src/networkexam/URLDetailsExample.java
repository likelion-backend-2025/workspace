package networkexam;

import java.net.*;
import java.io.*;

public class URLDetailsExample {
    public static void main(String[] args) {
        try {
            // URL 객체 생성
            URL url = new URL("https://likelion.net/");

            // URL 정보 출력
            System.out.println("프로토콜: " + url.getProtocol());
            System.out.println("호스트: " + url.getHost());
            System.out.println("포트: " + url.getPort());
            System.out.println("경로: " + url.getPath());

            // URLConnection으로 연결
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // 헤더 정보 읽기
            System.out.println("\n=== 헤더 정보 ===");
            System.out.println("Content-Type: " +
                    connection.getContentType());
            System.out.println("Content-Length: " +
                    connection.getContentLength());

            // 내용 읽기
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            )) {
                String line;
                System.out.println("\n=== 내용 (처음 5줄) ===");
                int count = 0;
                while ((line = reader.readLine()) != null && count < 5) {
                    System.out.println(line);
                    count++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}