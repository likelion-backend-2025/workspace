package day09;

import day07.새;

public class WrapperExample {
    //1. 객체가 아닌 기본타입에 대해서 객체로 변환!!
    //2. 문자열을 각 데이터타입으로 형변환!!
    //자바프로그램 외부부터 입력 받는 값들은 모두 문자타입이다!!!
    //자바프로그램내에서는 각 알맞는 데이터 타입으로 사용될 필요가 있다.

    public static void test(Object obj){

    }
    public static void main(String[] args) {
        //기본타입을 객체인 타입으로 바꾸는 작업을 박싱
        //객체타입을 기본타입으로 바꾸는 작업을 언박싱

        //자바버전 1.5 이전에는 박싱 언박싱을 프로그래머가 직접했다.

        //지금은 컴파일러가 자동으로 해줌..
        Integer integerValue = 10;
        int intValue = 10;
        integerValue = intValue;  //자동박싱 기본 -> 객체로 변환
        intValue = integerValue; //자동 언박싱 객체 -> 기본타입으로 변환

        Integer num1 = Integer.valueOf(10);

        test(10); //오토박싱이 일어난 상태!!

//        새 b ;  //뻐꾸기, 참새, 오리.
//        b.노래하다();

        //순서가 중요!!
        //순서는 중요하지 않는 것..
        // key, value - 쌍으로 저장  - 어떤 데이터가?

        //학생 50명 데이터를 저장 -
        //학번 - 학생

        //foreach - 기억나시나요? 있니? 꺼내줘.  반복..
        //for - 인덱스에 따라서 1번, 2번 ... 전체크기먼저 구해서..

        //set - 순서가 중요하지 않다!!  - index 가 없다.
        //set에서 모든 데이터를 꺼내는 방법??

    }

}
