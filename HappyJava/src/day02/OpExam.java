package day02;

public class OpExam {
    public static void main(String[] args) {
        int a = 10;
        int b = 3;  // = 대입연산자

        System.out.println(a+b);
        System.out.println(a / b);
        System.out.println(a % b );  //나눈 나머지 값을 구함

         boolean ba = true, bb = true;

         //and  &&   두 조건이 모두 참 일때만 참!!
        System.out.println(ba && bb);
        System.out.println(ba || bb);
        System.out.println(!ba);
        System.out.println(ba ^ bb);

        System.out.println("=========단락평가============");

        //a = 10 b = 3
        int c = 5, d = 8;

        System.out.println((a<b++) & (c<d++) );

        System.out.println(b);
        System.out.println(d);
        System.out.println("증감연산자");
        //증감연산자
        a++; // a = a+1;   같은 문장

        int incrI = 10;
        System.out.println(++incrI); //incrI = incrI + 1 ;

        System.out.println(incrI);
    }
}
