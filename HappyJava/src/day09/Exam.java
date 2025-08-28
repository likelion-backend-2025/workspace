package day09;


import java.util.Scanner;

public class Exam {

    public void 파티장입장(Object obj){
        //이 안에는 누구만 들어갈 수 있나요?
        //객체만 입장 가능!!
        //8개의 객체가 아닌 데이터타입이 존재!! - 입장불가!!
        //8개도 입장가능하게 하고 싶었다!!
    }
    //String - 어떤면이 좀 달랐나요
    //불변 클래스..
    //문자열을 저장하는 특별한 공간을 사용함.

    //String str = new String();
    //String str2 = "abc";
    //str2 = str2 + "def";

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int value = keyboard.nextInt();

        System.out.println(value);

        if(value == 0){
            System.exit(0);
        }

        //어딘가에 열려있는애들!!  연결된것들
        //다 이용하면 닫아줘야함!!
        keyboard.close();

        System.out.println(System.currentTimeMillis());

        System.out.println(System.nanoTime());

        System.out.println(System.getenv("JAVA_HOME"));

        System.out.println("Java 버전: " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println("사용자 홈: " + System.getProperty("user.home"));
        System.out.println("현재 디렉토리: " + System.getProperty("user.dir"));
    }


}
