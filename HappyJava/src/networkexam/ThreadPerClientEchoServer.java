package networkexam;

import java.io.*;
import java.net.*;

public class ThreadPerClientEchoServer {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("스레드-퍼-클라이언트 에코 서버가 포트 " + PORT + "에서 시작되었습니다.");

            while (true) {
                // 클라이언트 연결을 기다립니다.
                Socket clientSocket = serverSocket.accept();

                // [변경점] 클라이언트를 처리할 새 스레드를 생성하고 시작합니다.
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println("서버 오류: " + e.getMessage());
        }
    }

    // ClientHandler 로직은 스레드 풀을 사용할 때와 동일합니다.
    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(
                         clientSocket.getOutputStream(), true)) {

                String clientAddress = clientSocket.getRemoteSocketAddress().toString();
                System.out.println("클라이언트 연결됨: " + clientAddress);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(clientAddress + "로부터: " + inputLine);
                    out.println("Echo: " + inputLine);
                    if ("bye".equalsIgnoreCase(inputLine)) {
                        break;
                    }
                }
                System.out.println("클라이언트 연결 종료: " + clientAddress);

            } catch (IOException e) {
                System.err.println("클라이언트 처리 오류: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    // 로그를 남기거나 무시할 수 있습니다.
                }
            }
        }
    }
}