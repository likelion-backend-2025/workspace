package day04;

import java.util.Arrays;

public class MultiArrayExample {
    public static void main(String[] args) {
        //선언 - [][] 몇개냐가 차원을 결정함.
        int[][] iarr;
        iarr = new int[3][2];

        iarr[0][0] = 100;
        iarr[0][1] = 200;
        iarr[1][0] = 300;



        int[][] iarr2 = new int[2][];
        iarr2[0] = new int[2];
        iarr2[0][0] = 10;
        iarr2[1] = new int[10];
        iarr2[1][0] = 20;


        System.out.println(iarr2[0][0]);
        int[][][][] iarr4 = new int[2][][][];

        int[] iarr5 = {1,2,3};


        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println(matrix[2][1]);


        int[][] iarr7 = {{1,2},{3,4,5,6,7},{8}};


        System.out.println(iarr7[1][2]);

        // 2차원 배열 순회
        System.out.println("=== 2차원 배열 출력 ===");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // 향상된 for문으로 순회
        System.out.println("\n=== 향상된 for문 ===");
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

        // 가변 길이 2차원 배열
        int[][] jagged = new int[3][];
        jagged[0] = new int[]{1, 2};
        jagged[1] = new int[]{3, 4, 5};
        jagged[2] = new int[]{6, 7, 8, 9};

        System.out.println("\n=== 가변 길이 배열 ===");
        for (int[] row : jagged) {
            System.out.println(Arrays.toString(row));
        }

        int[] iarr9 = {1,2,3};
        System.out.println(Arrays.toString(iarr9));

//        [1,2,3]  타입이 뭘까요?
//     밥   밥을하다 (씻은쌀, 물)  {}  -- 리턴타입 중요!!!











    }
}
