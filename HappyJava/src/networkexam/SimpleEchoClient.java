package networkexam;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SimpleEchoClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("서버에 연결되었습니다.");
            System.out.println("메시지를 입력하세요 (bye 입력시 종료):");

            while (true) {
                // 1. 사용자 입력을 먼저 받습니다.
                String userInput = scanner.nextLine();

                // 2. 입력된 메시지를 서버로 보냅니다.
                out.println(userInput);

                // 3. 서버로부터 응답을 받습니다.
                String response = in.readLine();

                // [안정성] 서버가 연결을 먼저 끊었을 경우를 확인합니다.
                if (response == null) {
                    System.out.println("서버와의 연결이 끊어졌습니다.");
                    break;
                }

                System.out.println("서버 응답: " + response);

                // [가독성] "bye"를 보낸 후에 루프를 종료합니다.
                if ("bye".equalsIgnoreCase(userInput)) {
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("오류 발생: " + e.getMessage());
        }

        System.out.println("클라이언트를 종료합니다.");
    }
}