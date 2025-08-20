package day03;

public class DoWhileExam1 {
    public static void main(String[] args) {
        int i = 10;

        do{
            //조건비교없이 들어오죠??  무조건 해당블럭을 한 번은 실행함.
            System.out.println(i);
        }while (i++ < 5);



        //    누적!!
//    1+2+3+4+5   --  변수..  1, 2, 3, 4
        int sum = 0;  //더한 값을 저장할 변수 선언
        sum = sum + i;   //sum += i;
    }


}
