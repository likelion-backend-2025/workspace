package day11;

import java.io.FileReader;

public class ExceptionExam5 {
    //예외를 처리하는 2가지 방법.
    public static void 심부름(){
//        예외를 직접 처리하는 방법
        int i = 0;  //i의 값이 무엇이 들어올지 모름.


        try {
            System.out.println(10 / i); //두부를 삽니다.
        } catch (Exception e) {
            System.out.println("다른 마트로 감!!");
        }
    }

    public static void 심부름2() throws Exception{
//        throws 넘기고 싶은 예외!!
        //예외를 심부름 시킨쪽으로 알려줌.
        int i = 0;  //i의 값이 무엇이 들어올지 모름.

        System.out.println(10/i);
    }

    public static void main(String[] args) {
         심부름();

         //Unhandled exception: java.lang.Exception
//        예외의 종류 - 반드시 처리해야만 하는 예외. checked Exception - 처리하지 않으면 컴파일 에러 발생!!!
//                  - 처리하지 않아도 컴파일 에러는 발생 안됨.  unChecked Exception
        try {
            심부름2();
        } catch (Exception e) {
            System.out.println("동생한테 시킴!!");
        }


        String str = null;

         str.charAt(0);

//        FileReader fr = new FileReader("a");
    }
}
