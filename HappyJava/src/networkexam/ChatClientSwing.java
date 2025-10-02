package networkexam;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClientSwing extends JFrame {
    private JTextArea textArea;
    private JTextField inputField;
    private JButton sendButton;
    private PrintWriter out;

    public ChatClientSwing(String host, int port) {
        setTitle("채팅 클라이언트");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // UI 구성
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        inputField = new JTextField();
        sendButton = new JButton("전송");

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // 버튼/엔터키 이벤트
        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());

        // 서버 연결 스레드
        new Thread(() -> connect(host, port)).start();
    }

    private void connect(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            out = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

            // 서버 메시지 읽는 스레드
            new Thread(() -> {
                try {
                    String line;
                    while ((line = in.readLine()) != null) {
                        appendMessage(line);
                    }
                } catch (IOException e) {
                    appendMessage("서버 연결이 종료되었습니다.");
                }
            }).start();

        } catch (IOException e) {
            appendMessage("서버 연결 실패: " + e.getMessage());
        }
    }

    private void sendMessage() {
        String msg = inputField.getText().trim();
        if (!msg.isEmpty() && out != null) {
            out.println(msg);
            inputField.setText("");
        }
    }

    private void appendMessage(String msg) {
        SwingUtilities.invokeLater(() -> {
            textArea.append(msg + "\n");
            textArea.setCaretPosition(textArea.getDocument().getLength());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatClientSwing client = new ChatClientSwing("15.164.228.241", 8888);
            client.setVisible(true);
        });
    }
}
