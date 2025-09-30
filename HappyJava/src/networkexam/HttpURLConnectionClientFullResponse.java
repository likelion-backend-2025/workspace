package networkexam;
import java.net.*;
import java.io.*;
import java.util.List;
import java.util.Map;

public class HttpURLConnectionClientFullResponse {
    public static void main(String[] args) {
        String urlString = "http://www.google.com"; // http로 접속해야 301 응답을 볼 수 있습니다.

        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false); // 리다이렉션을 자동으로 따르지 않도록 설정

            System.out.println("--- 상태 라인 ---");
            System.out.println("HTTP " + conn.getResponseCode() + " " + conn.getResponseMessage());

            System.out.println("\n--- 응답 헤더 ---");
            // 헤더 정보를 Map 형태로 가져와서 출력
            Map<String, List<String>> headers = conn.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                if (entry.getKey() != null) {
                    System.out.println(entry.getKey() + ": " + String.join(", ", entry.getValue()));
                }
            }

            System.out.println(); // 헤더와 본문 사이의 빈 줄

            System.out.println("--- 응답 본문 ---");
            // 응답 코드가 200번대(성공) 또는 300번대(리다이렉션) 등이면 본문을 읽음
            InputStream inputStream = (conn.getResponseCode() >= 400) ?
                    conn.getErrorStream() : conn.getInputStream();

            if (inputStream != null) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println(inputLine);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
