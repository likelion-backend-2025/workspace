package day04;

import java.util.Arrays;

public class ArrayCopyExample {
    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5};

        // 1. for문을 이용한 복사
        int[] copy1 = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copy1[i] = original[i];
        }

        // 2. System.arraycopy() 사용
        int[] copy2 = new int[original.length];
        System.arraycopy(original, 0, copy2, 0, original.length);

        // 3. Arrays.copyOf() 사용
        int[] copy3 = Arrays.copyOf(original, original.length);

        // 4. Arrays.copyOfRange() 사용 (부분 복사)
        int[] copy4 = Arrays.copyOfRange(original, 1, 4); // 인덱스 1~3

        // 결과 출력
        System.out.println("원본: " + Arrays.toString(original));
        System.out.println("copy1: " + Arrays.toString(copy1));
        System.out.println("copy2: " + Arrays.toString(copy2));
        System.out.println("copy3: " + Arrays.toString(copy3));
        System.out.println("copy4 (부분): " + Arrays.toString(copy4));

        // 얕은 복사 vs 깊은 복사 확인
        copy1[0] = 100;
        System.out.println("\ncopy1 수정 후:");
        System.out.println("원본: " + Arrays.toString(original));
        System.out.println("copy1: " + Arrays.toString(copy1));
    }
}