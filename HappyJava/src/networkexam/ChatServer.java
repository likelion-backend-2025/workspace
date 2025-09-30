package networkexam;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<ClientHandler> clients = ConcurrentHashMap.newKeySet();

    public static void main(String[] args) {
        System.out.println("채팅 서버가 포트 " + PORT + "에서 시작되었습니다.");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("서버 오류: " + e.getMessage());
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private String nickname;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()))) {

                out = new PrintWriter(socket.getOutputStream(), true);

                // 닉네임 설정
                out.println("닉네임을 입력하세요:");
                nickname = in.readLine();

                if (nickname == null || nickname.trim().isEmpty()) {
                    return;
                }

                System.out.println(nickname + "님이 채팅방에 입장했습니다.");
                broadcast(nickname + "님이 채팅방에 입장했습니다.", this);

                String message;
                while ((message = in.readLine()) != null) {
                    if ("bye".equalsIgnoreCase(message)) {
                        break;
                    }
                    broadcast(nickname + ": " + message, this);
                }

            } catch (IOException e) {
                System.err.println("클라이언트 처리 오류: " + e.getMessage());
            } finally {
                if (nickname != null) {
                    System.out.println(nickname + "님이 채팅방을 나갔습니다.");
                    broadcast(nickname + "님이 채팅방을 나갔습니다.", this);
                }
                clients.remove(this);
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("소켓 종료 오류: " + e.getMessage());
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
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }
}