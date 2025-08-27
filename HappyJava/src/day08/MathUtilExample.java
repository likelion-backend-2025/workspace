package day08;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MathUtilExample {
    // 두 점 사이의 거리 계산
    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    // 로또 번호 생성 (1~45 중 6개)
    public static int[] generateLotto() {
        int[] resultArr = new int[6];
        for(int i = 0; i < resultArr.length; i++){
            resultArr[i] = ((int)(Math.random() * 45) + 1);
        }
        return resultArr;
    }

    public static void main(String[] args) {
        System.out.println("거리: " + distance(0, 0, 3, 4)); // 5.0
        System.out.println("로또: " + Arrays.toString(generateLotto()));
    }
}