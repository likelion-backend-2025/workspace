package networkexam;

import java.net.*;
import java.io.*;

public class HttpURLConnectionClient {
    public static void main(String[] args) {
        String urlString = "https://www.google.com"; // HTTPS도 쉽게 처리

        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();

            // 요청 방식 설정 (헤더를 직접 쓸 필요 없음)
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000); // 5초 타임아웃
            conn.setReadTimeout(5000);

            // 응답 코드 먼저 확인 (매우 중요한 개선점)
            int responseCode = conn.getResponseCode();
            System.out.println("응답 코드: " + responseCode);

            // 성공적인 응답(200 OK)일 때만 본문 읽기
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                        System.out.println(inputLine);
                    }
                    System.out.println("응답 본문 길이: " + response.length());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect(); // 연결 종료
            }
        }
    }
}