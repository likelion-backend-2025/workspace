package day02;

import java.util.Scanner;

public class Exam {
    public static void main(String[] args) {
        System.out.println(args[0]);
        System.out.println(args[1]);
        String str1 = args[0];

        //기본타입을 객체로 바꿔주는 8개의 객체들은 형변환 하는데도 사용됨.
        int i = Integer.parseInt(args[0]);
        int j = Integer.parseInt(args[1]);

        System.out.println(i + j);

        Scanner scanner = new Scanner(System.in);
        System.out.println("숫자를 입력하세요.");
        int val1 = scanner.nextInt();  //입력받아서 int로 형변환 함.

        System.out.println("입력받은 값은 : "+ val1);

    }
}
