package networkexam;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ModernHttpClientFullResponse {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NEVER) // 리다이렉션 따르지 않음
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.google.com"))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("--- 상태 라인 ---");
            System.out.println(response.version() + " " + response.statusCode());

            System.out.println("\n--- 응답 헤더 ---");
            // 헤더 정보를 가져와서 출력
            HttpHeaders headers = response.headers();
            headers.map().forEach((key, values) ->
                    System.out.println(key + ": " + String.join(", ", values))
            );

            System.out.println(); // 헤더와 본문 사이의 빈 줄

            System.out.println("--- 응답 본문 ---");
            System.out.println(response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}