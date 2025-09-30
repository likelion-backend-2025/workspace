package networkexam;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            // 서버 메시지를 읽는 스레드
            Thread readerThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.err.println("서버 연결이 끊어졌습니다.");
                }
            });

            readerThread.start();

            // 사용자 입력 처리
            String userInput;
            while (!(userInput = scanner.nextLine()).equalsIgnoreCase("bye")) {
                out.println(userInput);
            }

            out.println("bye");

        } catch (IOException e) {
            System.err.println("서버 연결 실패: " + e.getMessage());
        }
    }
}