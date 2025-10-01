package networkexam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleEchoServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(12346);) {
            System.out.println("에코 서버가 12345 번 포트에서 시작되었습니다.");

            while (true) {
                //서버가 클라이언트를 기다리다가 (accept())  클라이언트가 해당 서버에 접속하면
                //Client의 소켓이 리턴됨.
                try(Socket socket = serverSocket.accept();
                    //클라이언트 소켓을 이용해서 클라이언트와의 연결 통로를 얻을 수 있어요.
                    //클라이언트와 서버사이에 입출력이 일어날텐데..
                    //항상 입출력은 하나의 통로에서 동작할 수없다.  각각 통로가 필요해다!!!
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    System.out.println("클라이언트 연결 ::"+socket.getRemoteSocketAddress());

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("클라이언트로부터 받은 메시지 ::" + inputLine);
                        out.println("Echo::"+inputLine);

                        if("bye".equalsIgnoreCase(inputLine)) {
                            break;
                        }

                    }

                }

            }




        }catch (Exception e) {
            System.out.println("서버시작실패::"+e.getMessage());
        }
    }
}
