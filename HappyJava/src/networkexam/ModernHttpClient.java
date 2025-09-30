package networkexam;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ModernHttpClient {
    public static void main(String[] args) {
        // 1. HttpClient 생성 (재사용 가능)
        HttpClient client = HttpClient.newHttpClient();

        // 2. HttpRequest 생성 (빌더 패턴 사용)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.google.com"))
                .header("User-Agent", "Java 11 HttpClient")
                .GET() // GET 메소드를 명시적으로 지정
                .build();

        try {
            // 3. 동기 방식으로 요청 전송 및 응답 수신
            HttpResponse<String> response = client.send(
                    request, HttpResponse.BodyHandlers.ofString()
            );

            // 4. 응답 객체에서 정보 추출
            System.out.println("응답 코드: " + response.statusCode());
            System.out.println("응답 본문 길이: " + response.body().length());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}