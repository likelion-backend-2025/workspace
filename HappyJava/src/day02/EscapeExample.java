package day02;

public class EscapeExample {
    public static void main(String[] args) {
        // 줄바꿈
        System.out.println("첫 번째 줄\n두 \n번째 줄");

        // 탭
        System.out.println("이름\t나이\t성별");
        System.out.println("홍길동\t20\t남");

        // 따옴표
        System.out.println("그가 말했다: \"안녕하세요!\"");
        System.out.println('\'');

        // 백슬래시
        System.out.println("파일 경로: C:\\Users\\Documents");
    }
}