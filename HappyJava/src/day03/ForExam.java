package day03;

public class ForExam {

    public static void main(String[] args) {
//        int i = 1; //초기화
//        while(i < 11){
//            탈출할수있도록..
//        }
        //for(초기화;조건식;증감식){ 실행할 문장 }
//        int i = 1;
        int sum = 0;
        for(int i = 1; i <= 10; i++){

            System.out.println(i);
            sum += i;
        }
        System.out.println(sum);

        //변수의 범위 -- 언어마다 차이가 있을 수 있다.
        //자바는 변수의 스코프가 블럭단위
//        System.out.println(i);

//        for(;;){
//
//        }
//
//        while(true){
//
//        }
        int j = 0;
        for(;j<10;j++){

        }

        for(int i = 0; i < 10; i++){

        }


    }
}
