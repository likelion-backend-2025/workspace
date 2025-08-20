package day03;

import java.util.Arrays;

public class ArrayCopyExam2 {
    public static void main(String[] args) {
        int[] nums = {10, 20, 30};
        int[] shallow = nums;                     // 얕은 복사
        int[] deep = Arrays.copyOf(nums, 3);      // 깊은 복사

        shallow[1] = 200;
        deep[2] = 300;

        System.out.println("nums   : " + Arrays.toString(nums));
        System.out.println("shallow: " + Arrays.toString(shallow));
        System.out.println("deep   : " + Arrays.toString(deep));

        //각 출력값을 예측해 보세요.


        int[] ori1 = {1,2,3};
        int[] ori2 = {4,5,6,7};

        //카피할 배열..
        int[] copy = new int[ori1.length+ori2.length];

        System.arraycopy(ori1,0,copy,0,ori1.length);
        System.arraycopy(ori2,0,copy,ori1.length,ori2.length);
        System.out.println("=====================");
        for (int val : copy){
            System.out.println(val);
        }

    }
}
