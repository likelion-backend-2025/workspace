package day02;

public class IfExample {
    public static void main(String[] args) {
        int score = 90;

        if (score >= 80) {
            System.out.println("합격입니다!");
            System.out.println("축하합니다.");
        }else {
            System.out.println("불합격입니다.");
            System.out.println("아쉽네요.");
        }

        int age = 17;
        if(age >= 18)
            System.out.println("성인입니다.");
        else
            System.out.println("미성년자입니다.");

        System.out.println("프로그램 종료");
    }
}