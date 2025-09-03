package day11;

//import io.kr.carami.Scanner;

import java.util.Scanner;

public class ExceptionExam6 {
    public static void inputScore(int score) throws Yewei{

        //score는 점수 값을 나타냄.  0 - 100 까지만 입력 받고 싶어요.
        //만약 0-100 사이 이외의 값이 들어왔다면???   예외라고 정의하고 싶다~!!!
        //지금까지 여러분들이 만난 예외는 누가 발생시켰을까요??
        if(score < 1 || score > 5){
//            System.out.println("점수는 0-100 사이의 값만 입력 가능합니다. ");
//            return;
            throw new Yewei("점수는 1-5 사이 값만 입력 가능 합니다. 사용자 입력값:"+score);
        }
        System.out.println("학생의 점수 : "+score );

        //score값으로 정해진 일을 수행함.
    }

    public static void main(String[] args) {
        //예외가 보편적인 상황에 적용된것 같지 않나요??
        Scanner input = new Scanner(System.in);
        System.out.println("점수를 입력하세요.");

        try {
            inputScore(input.nextInt());
        } catch (Yewei e) {
            System.out.println(e.getMessage());
        }

        System.out.println("main 종료");

    }
}
