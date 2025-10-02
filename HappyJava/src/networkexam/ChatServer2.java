package networkexam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer2 {

    // 닉네임을 키로 ClientHandler를 저장하는 스레드 안전한 맵
    private static ConcurrentHashMap<String, ClientHandler> clientsMap = new ConcurrentHashMap<>();

    // 닉네임을 기반으로 특정 ClientHandler를 찾는 메서드
    private static ClientHandler findClient(String nickname) {
        return clientsMap.get(nickname);
    }

    // 모든 클라이언트에게 메시지를 브로드캐스트하는 메서드
    private static void broadcast(String message, ClientHandler sender) {
        // clientsMap.values()를 직접 순회하여 모든 클라이언트에게 메시지를 전송
        for (ClientHandler client : clientsMap.values()) {
            if (client.equals(sender)) {
                // 발신자 본인에게는 별도 메시지 전송
                client.sendMessage("내가 보낸메시지 ::" + message);
            } else {
                // 다른 모든 사용자에게는 "닉네임(IP):::메시지" 형식으로 전송
                client.sendMessage(sender.nickname + "(" + sender.clientIp + "):::" + message);
            }
        }
    }

    public static void main(String[] args) {
        int port = 0;

        // 1. 실행 시 포트 번호 입력받기
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("채팅 서버가 사용할 포트 번호를 입력하세요 (예: 12345): ");
            if (scanner.hasNextInt()) {
                port = scanner.nextInt();
            } else {
                System.out.println("유효한 포트 번호를 입력해야 합니다. 서버를 종료합니다.");
                return;
            }
        } catch (Exception e) {
            System.out.println("포트 번호 입력 중 오류 발생: " + e.getMessage());
            return;
        }

        System.out.println("채팅 서버 시작! 포트: " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) { // 입력받은 port 사용
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                System.out.println("새로운 연결 수락: " + socket.getInetAddress().getHostAddress());

                new Thread(clientHandler).start();
            }
        } catch (Exception e) {
            System.out.println("서버 오류: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------------
    // 클라이언트와의 통신을 전담하는 스레드 클래스
    // ----------------------------------------------------------------------
    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private String nickname;
        private String clientIp; // 클라이언트 IP 주소 저장을 위한 필드

        ClientHandler(Socket socket) {
            this.socket = socket;
            // 클라이언트 IP 주소 추출 및 저장
            this.clientIp = socket.getInetAddress().getHostAddress();
        }

        // 클라이언트에게 메시지를 전송하는 메서드
        public void sendMessage(String message) {
            if (out != null) {
                out.println(message);
            }
        }

        // 귓속말을 처리하고 전송하는 메서드
        private boolean sendPrivateMessage(String targetNickname, String content) {
            ClientHandler targetClient = findClient(targetNickname);

            if (targetClient != null) {
                // 수신자에게 IP 포함 메시지 전송
                targetClient.sendMessage("[귓속말 from " + this.nickname + "(" + this.clientIp + ")] " + content);
                // 발신자 본인에게 성공 알림
                this.sendMessage("[귓속말 to " + targetNickname + "] " + content);
                return true;
            } else {
                // 수신자를 찾지 못했을 경우 알림
                this.sendMessage("[시스템] 사용자 (" + targetNickname + ") 님을 찾을 수 없습니다.");
                return false;
            }
        }

        // 사용법 안내 메시지 출력 메서드
        private void sendHelpMessage() {
            this.sendMessage("==================== 채팅 사용법 ====================");
            this.sendMessage("1. 일반 메시지: 내용을 입력 후 엔터");
            this.sendMessage("2. 귓속말: /to [대상닉네임] [내용] 형식으로 입력");
            this.sendMessage("   예) /to userA 안녕하세요.");
            this.sendMessage("3. 종료: bye 를 입력 후 엔터");
            this.sendMessage("4. 도움말: /help 를 입력 후 엔터");
            this.sendMessage("====================================================");
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                out = new PrintWriter(socket.getOutputStream(), true);

                // 닉네임 입력 요청 및 처리
                String tempNickname = null;

                while (tempNickname == null) {
                    out.println("닉네임을 입력하세요.");
                    String inputNickname = in.readLine();

                    if (inputNickname == null) {
                        return;
                    }

                    // 닉네임 중복 처리
                    if (clientsMap.containsKey(inputNickname)) {
                        out.println("[시스템] 이미 사용 중인 닉네임입니다. 다시 입력해주세요.");
                    } else {
                        tempNickname = inputNickname;
                    }
                }

                this.nickname = tempNickname;
                clientsMap.put(this.nickname, this); // 닉네임으로 맵에 추가

                // 서버 콘솔에 닉네임 + IP 표시
                System.out.println(nickname + "(" + clientIp + ")님 입장. 현재 접속자 수: " + clientsMap.size());

                sendHelpMessage(); // 입장 시 사용법 안내

                // 입장 메시지 브로드캐스트 (IP 포함)
                broadcast(nickname + "(" + clientIp + ") 님 입장", this);

                String message = null;
                while ((message = in.readLine()) != null) {
                    // 서버 콘솔에 수신 메시지 출력 (IP 포함)
                    System.out.println(nickname + "(" + clientIp + "):::" + message);

                    // 명령어 처리 (귓속말, 도움말, 종료)
                    if (message.startsWith("/to ")) {
                        String[] parts = message.split(" ", 3);
                        if (parts.length < 3) {
                            this.sendMessage("[시스템] 귓속말 사용법: /to [대상닉네임] [내용]");
                        } else {
                            String targetNickname = parts[1];
                            String content = parts[2];
                            sendPrivateMessage(targetNickname, content);
                        }
                    } else if ("/help".equalsIgnoreCase(message)) {
                        sendHelpMessage();
                    } else if ("bye".equalsIgnoreCase(message)) {
                        break;
                    } else {
                        // 일반 메시지 브로드캐스트
                        broadcast(message, this);
                    }
                }
            } catch (Exception e) {
                // 연결 오류 발생 시
                System.out.println(this.nickname + "(" + this.clientIp + ") 연결 오류: " + e.getMessage());
            } finally {
                // 퇴장 및 자원 정리
                if (nickname != null) {
                    clientsMap.remove(nickname); // 맵에서 제거
                    System.out.println(nickname + "(" + this.clientIp + ")님 퇴장. 현재 접속자 수: " + clientsMap.size());
                    // 퇴장 메시지 브로드캐스트 (IP 포함)
                    broadcast(nickname + "(" + this.clientIp + ") 님 퇴장", this);
                }
                try {
                    socket.close();
                } catch (Exception ignored) {}
            }
        }
    }
}