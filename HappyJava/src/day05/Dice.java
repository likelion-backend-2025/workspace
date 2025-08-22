package day05;

import java.util.Random;

public class Dice {
    //어떤 속성이 필요할까요??
    //속성 - 면, 눈
    int face=6;
    int eye;

    //기능 - 던지기, 굴리기.
    public void roll(){
        //주사위의 면의 따라서 랜덤한 값을 하나 발생하게함.
//        Random random = new Random();

        //0.0 ~  1.0 사이의 값이 리턴된. 1.0 은 포함되지 않는다.
        eye = (int)(Math.random()*face)+1;
    }

    public static void main(String[] args) {
        //Math.random()*6   0-5까지 출력됨.  1-6까지 얻고싶음.
        System.out.println((int)(Math.random()*6)+1);
    }
}
