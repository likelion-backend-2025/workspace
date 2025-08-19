package day02;

public class VariableTest {
    int intValue; //클래스변수
    public static void main(String[] args) {

        //변수 선언
        //타입 변수명 ;
        int i = 1;  // 지역변수

        //자바 -  강형 언어.
        //변수의 타입이 정해지는 언어..
        //밥상 -  어떤 똑같은 그릇에다가 음식을 담나요?
        //어떤 언어는 그릇의 종류에 상관없이 아무거나 담는 언어 - 약형언어

        //자바의 기본 타입

        int x ;

        x = 0;
        x = 10;  //컴파일오류

        System.out.println(x);

        //자바의 타입 - 기본타입, 레퍼런스 타입
        //기본타입 - 값이 들어감
        //레퍼런스타입 - 주소값이 들어감
        //자바프로그램의 대다수는 레퍼런스 타입임.
        //기본타입 -  딱 8개만 존재.  - 숫자 (정수, 실수 ), 문자 - 불린
        byte b = 'A';  // 형변환 - 묵시적 형변환
        System.out.println((char) b);

        boolean booleanFlag = true;

//        System.out.println((byte)booleanFlag);

        //형변환 -- 연관있는것들만 바뀔 수 있음.  char, byte  -- 숫자.
        byte 바이트값 = 10;

    }
}
