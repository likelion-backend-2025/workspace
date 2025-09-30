package networkexam;

import java.io.*;
import java.net.*;

public class SimpleEchoServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("에코 서버가 포트 " + PORT + "에서 시작되었습니다.");

            while (true) {
                // 클라이언트 연결 대기
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(
                             clientSocket.getOutputStream(), true)) {

                    System.out.println("클라이언트가 연결되었습니다: " +
                            clientSocket.getRemoteSocketAddress());

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("받은 메시지: " + inputLine);
                        out.println("Echo: " + inputLine);

                        if ("bye".equalsIgnoreCase(inputLine)) {
                            break;
                        }
                    }

                } catch (IOException e) {
                    System.err.println("클라이언트 처리 중 오류: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("서버 시작 실패: " + e.getMessage());
        }
    }
}