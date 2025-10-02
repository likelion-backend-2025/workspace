package networkexam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadEchoServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("에코서버 시작");

            while(true){
                Socket socket = serverSocket.accept();

                //클라이언트별로 각각 통신 할 수 있는 쓰레드가 필요할 것 같아요.
                //클라이언트가 접속 할 때 쓰레드를 생성한다.
//                Thread clientThread = new Thread(new ClientHandler(socket));
//                clientThread.start();

                //쓰레드 풀을 이용하는 방법으로  발전시켜봅시다.
                executorService.submit(new ClientHandler(socket));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            executorService.shutdown();
        }
    }

    //각 클라이언트 별로 응대할 스레드!!
    static class ClientHandler implements Runnable{
        private final Socket socket;

        ClientHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    ){
                SocketAddress clientaddress = socket.getRemoteSocketAddress();
                System.out.println(clientaddress + " 사용자 접속함");

                String inputLine;
                while((inputLine = in.readLine()) != null){
                    System.out.println(clientaddress +"로 부터 받은 메시지 :: "+inputLine);
                    out.println("Echo::"+inputLine);
//                    out.flush();

                    if("bye".equals(inputLine)){
                        break;
                    }
                }

                System.out.println(clientaddress+" 연결 종료!!");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage()+"소켓 종료 오류!!");
                }
            }
        }
    }


}
