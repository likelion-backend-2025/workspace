package day02;

public class OverflowTest {
    public static void main(String[] args) {
        // int 오버플로우
        int max = Integer.MAX_VALUE;
        System.out.println("int 최댓값: " + max);
        System.out.println("최댓값 + 1: " + (max + 1));

        // byte 오버플로우
        byte b = 127;
        System.out.println("byte 최댓값: " + b);
        b = (byte)(b + 1);
        System.out.println("최댓값 + 1: " + b);

        // 언더플로우
        int min = Integer.MIN_VALUE;
        System.out.println("int 최솟값: " + min);
        System.out.println("최솟값 - 1: " + (min - 1));
    }
}