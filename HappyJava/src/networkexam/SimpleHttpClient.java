package networkexam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleHttpClient {
    public static void main(String[] args) {

        String hostname = "www.naver.com";
        int port = 80;

        try(Socket socket = new Socket(hostname, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {

            out.println("GET / HTTP/1.1");
            out.println("Host: " + hostname);
            out.println("Connection: close");
            out.println();

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }
}
