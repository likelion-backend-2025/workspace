package networkexam;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class SynchronizedChatServer {
    private static final int PORT = 12345;
    private static Set<ClientHandler> clients = new HashSet<>();
    private static final Object clientsLock = new Object(); // 잠금 객체

    public static void main(String[] args) {
        System.out.println("채팅 서버가 포트 " + PORT + "에서 시작되었습니다.");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                synchronized (clientsLock) {
                    clients.add(clientHandler); // 동기화 처리
                }
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("서버 오류: " + e.getMessage());
        }
    }

    // ClientHandler 클래스는 sendMessage 메소드를 제외하고 동일 (내부 finally 블록 수정)
    static class ClientHandler implements Runnable {
        // ... (필드 선언은 동일)
        private Socket socket;
        private PrintWriter out;
        private String nickname;

        public ClientHandler(Socket socket) { this.socket = socket; }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                out = new PrintWriter(socket.getOutputStream(), true);

                // ... (닉네임 설정 및 채팅 로직은 동일)
                out.println("닉네임을 입력하세요:");
                nickname = in.readLine();
                if (nickname == null || nickname.trim().isEmpty()) return;

                System.out.println(nickname + "님이 채팅방에 입장했습니다.");
                broadcast(nickname + "님이 채팅방에 입장했습니다.", this);

                String message;
                while ((message = in.readLine()) != null) {
                    if ("bye".equalsIgnoreCase(message)) break;
                    broadcast(nickname + ": " + message, this);
                }

            } catch (IOException e) {
                // ...
            } finally {
                if (nickname != null) {
                    System.out.println(nickname + "님이 채팅방을 나갔습니다.");
                    broadcast(nickname + "님이 채팅방을 나갔습니다.", this);
                }
                synchronized (clientsLock) {
                    clients.remove(this); // 동기화 처리
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    // ...
                }
            }
        }

        public void sendMessage(String message) {
            if (out != null) {
                out.println(message);
            }
        }
    }

    private static void broadcast(String message, ClientHandler sender) {
        synchronized (clientsLock) { // 동기화 처리
            for (ClientHandler client : clients) {
                if (client != sender) {
                    client.sendMessage(message);
                }
            }
        }
    }
}