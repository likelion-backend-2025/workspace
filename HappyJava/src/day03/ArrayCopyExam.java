package day03;

public class ArrayCopyExam {
    public static void main(String[] args) {
        int ii = 10;
        int copyI = ii;

        copyI = 20;

        System.out.println(ii);

        int[] iarr = {1,2,3};
        int[] copyIarr2 = iarr;  //주소만 복사!!  얕은복사
        int[] copyIarr = new int[iarr.length];

        for (int i =0; i< iarr.length; i++){
            copyIarr[i] = iarr[i];   //값을 복사  깊은복사 .

        }
        System.out.println("복사된배열");
        for(int val : copyIarr){
            System.out.println(val);
        }

        copyIarr[0] = 100;

        System.out.println(iarr[0]);







    }
}
