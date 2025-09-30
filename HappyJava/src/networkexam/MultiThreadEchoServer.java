package networkexam;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class MultiThreadEchoServer {
    private static final int PORT = 8080;
    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("멀티스레드 에코 서버가 포트 " + PORT + "에서 시작되었습니다.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("서버 오류: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
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
                    System.err.println("소켓 종료 오류: " + e.getMessage());
                }
            }
        }
    }
}