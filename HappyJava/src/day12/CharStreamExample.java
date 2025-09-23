package day12;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharStreamExample {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("input.txt");
             FileWriter writer = new FileWriter("output.txt")) {

            int character;
            // 파일 끝(-1)까지 한 문자씩 읽기
            while ((character = reader.read()) != -1) {
                System.out.println((char)character);
                writer.write(character);
            }
            System.out.println("텍스트 파일 복사 완료!");

        } catch (IOException e) {
            System.err.println("파일 처리 중 오류: " + e.getMessage());
        }
    }
}