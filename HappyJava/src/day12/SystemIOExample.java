package day12;

import java.io.*;

public class SystemIOExample {
    public static void main(String[] args) {

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter writer = new PrintWriter(new FileWriter("src/day12/myInfo.txt"))
        ) {
            System.out.print("이름을 입력하세요: ");
            String name = reader.readLine();

            System.out.print("나이를 입력하세요: ");
            int age = Integer.parseInt(reader.readLine());

            System.out.println("안녕하세요, " + name + "님!");
            System.out.println("당신은 " + age + "살입니다.");

            writer.println("안녕하세요 " + name + "님!");
            writer.println("당신은 " + age + "살입니다.");

            if (age < 0) {
                System.err.println("오류: 나이는 음수일 수 없습니다.");
            }

        } catch (IOException e) {
            System.err.println("입력 오류: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("숫자 형식 오류: " + e.getMessage());
        }
    }

}
