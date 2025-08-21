package day03;

public class ArrayExam1 {
    public static void main(String[] args) {
        String name = "강경미";
        String name2 = "정미애";
        String name3 = "김영진";

        System.out.println(name);
        System.out.println(name2);
        System.out.println(name3);
        //선언!!
        int[] iarr;
        int iarr2[];
        int[][] iarr3;
        int[] iarr4[];
        //배열의 생성
        iarr = new int[3];

        int[] iarr5 = new int[4]; //배열의 크기는 생성할 때 반드시 정해야함.

        //index 를 사용해서 배열의 요소에 접근
        iarr[0] = 10;

        System.out.println(iarr[0]);
        System.out.println(iarr[1]);
        System.out.println(iarr[2]);
//        System.out.println(iarr[3]);
        int i = iarr[0];

        System.out.println(i);
        System.out.println("iarr의 길이::"+iarr.length);

        for(int ii = 0; ii < iarr.length; ii++){
            System.out.println(iarr[ii]);
        }

        System.out.println("foreach=============");
        //foreach  ==  1.5 추가된 문법.
        for(int val :iarr){
            System.out.println(val);
        }

        String[] strArr = new String[3];
        strArr[0] = "kkkk";
        strArr[1] = "qqqq";
        strArr[2] = "aaaa";

        for(int ii = 0; ii < strArr.length; ii++){
            System.out.println(strArr[ii]);
        }

        for(String str:strArr){
            System.out.println(str);
        }
    }
}
