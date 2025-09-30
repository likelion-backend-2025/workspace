package networkexam;

import java.io.*;
import java.net.*;

public class URLConnectionExample {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://httpbin.org/post");
            URLConnection connection = url.openConnection();

            // POST 요청 설정
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // 데이터 전송
            String jsonData = "{\"message\": \"Hello from Java!\"}";
            try (OutputStreamWriter writer = new OutputStreamWriter(
                    connection.getOutputStream())) {
                writer.write(jsonData);
            }

            // 응답 읽기
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.err.println("네트워크 오류: " + e.getMessage());
        }
    }
}