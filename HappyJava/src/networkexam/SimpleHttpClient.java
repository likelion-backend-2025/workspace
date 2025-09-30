package networkexam;

import java.io.*;
import java.net.*;

public class SimpleHttpClient {
    public static void main(String[] args) {
        String hostname = "www.google.com";
        int port = 80;

        try (Socket socket = new Socket(hostname, port);
             PrintWriter out = new PrintWriter(
                     socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()))) {

            // HTTP GET 요청 전송
            out.println("GET / HTTP/1.1");
            out.println("Host: " + hostname);
            out.println("Connection: close");
            out.println(); // 빈 줄로 헤더 종료

            // 응답 읽기
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
            }

        } catch (IOException e) {
            System.err.println("HTTP 요청 실패: " + e.getMessage());
        }
    }
}