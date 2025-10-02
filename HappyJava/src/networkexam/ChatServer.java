package networkexam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<ClientHandler> clients = ConcurrentHashMap.newKeySet();

    private static void broadcast(String message, ClientHandler sender){
        for(ClientHandler client: clients){
            if(!client.equals(sender)){
                client.sendMessage(sender.nickname+":::"+message);
            }else{
                client.sendMessage("내가 보낸메시지 ::"+message);
            }
//            if(client != sender){
//                client.sendMessage(message);
//            }
        }
    }

    public static void main(String[] args) {
        System.out.println("채팅 서버 시작!!!");

        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            while(true){
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clients.add(clientHandler);
                System.out.println(clients.size());

                new Thread(clientHandler).start();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    static class ClientHandler implements Runnable{
        private Socket socket;
        private PrintWriter out;
        private String nickname;

        ClientHandler(Socket socket){
            this.socket = socket;
        }

        public void sendMessage(String message) {
            if (out != null) {
                out.println(message);
            }
        }


        @Override
        public void run() {
            try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    ) {
                out = new  PrintWriter(socket.getOutputStream(), true);

                //닉네임을 입력받고 싶다.
                out.println("닉네임을 입력하세요.");
                nickname = in.readLine();

                if(nickname == null){
                    return;
                }
                System.out.println(nickname + "님 입장");

                //채팅방에 있는 전체 사용자에게 알리고 싶다.
                broadcast(nickname+" 님 입장",this);

                String message = null;
                while((message = in.readLine()) != null){
                    System.out.println(nickname+":::"+message);
                    if("bye".equalsIgnoreCase(message)){
                        break;
                    }
                    broadcast(message,this);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }finally {
                if(nickname != null){
                    System.out.println(nickname+"님 퇴장");
                    broadcast(nickname+ "님 퇴장", this);
                }
                try{
                    socket.close();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

        }
    }

}
