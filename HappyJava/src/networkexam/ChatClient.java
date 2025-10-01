package networkexam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try(Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //키보드로부터 입력받기위해서
            Scanner keyboard = new Scanner(System.in);
        ){

            //서버가 보내준 메시지를 읽어오는 일 담당.
            Thread readerThread = new Thread(()->{
                try{
                    String serverMsg;
                    while((serverMsg = in.readLine()) != null){
                        System.out.println(serverMsg);
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            });
            readerThread.start();


            //사용자의 입력을 받아서 서버에게 전송하는 일 담당.
            String userInput;

            while (!(userInput = keyboard.nextLine()).equalsIgnoreCase("bye")) {
                out.println(userInput);
            }

            out.println("bye");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
