package networkexam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleEchoClient {
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //키보드로 입력 받아서.  받은 내용을 서버에 전송 해야함.
            Scanner keyboard = new Scanner(System.in);
        ){
            System.out.println("서버 접속 성공!!");
            System.out.println("서버에 전달할 메시지를 입력하세요. ");
            System.out.println("bye를 입력하면 종료됩니다. ");

            while(true){
                //사용자가 키보드로 부터 입력한 내용.
                String message = keyboard.nextLine();
                //사용자가 입력한 내용을 서버에 전송!!
                out.println(message);  //out - 소켓

                //서버가 응답한 내용을 읽어옴.
                String response = in.readLine();

                // [안정성] 서버가 연결을 먼저 끊었을 경우를 확인합니다.
                if (response == null) {
                    System.out.println("서버와의 연결이 끊어졌습니다.");
                    break;
                }
                //서버가 보낸 메시지를 내 콘솔에 출력!!
                System.out.println(response);

                // [가독성] "bye"를 보낸 후에 루프를 종료합니다.
                if ("bye".equalsIgnoreCase(message)) {
                    break;
                }


            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
