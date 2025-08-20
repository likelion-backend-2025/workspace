package day03;

import java.util.Scanner;

public class Gugudan2 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("중단하고 싶은 단을 입력하세요. ");
        int dan = scanner.nextInt();

        outter: for(int k = 2; k <= 9; k++) {

            for (int i = 1; i <= 9; i++) {
                if(dan == i) continue outter;
                System.out.println(k + " * " + i + " = " + (k * i));
            }
            System.out.println(); // 단 구분을 위한 줄바꿈
        }
    }
}