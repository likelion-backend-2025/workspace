package day04;

public class VarArgsExample {
    //객체 - 속성(값,필드), 행위(기능, 메소드)
    //접근제한자 리턴타입 메소드명(매개변수들...)
    //정수 2개를 받아들여서 정수 두개를 더해서 더한값을
    // 리턴하는 add라는 이름의 메소드 정의해봅시다.
    //누구든지 사용할 수 있도록 접근제한자를 정한다.
//    public static int add(int a, int b){
//        System.out.println("add a,b");
//        return a + b;
//    }

    //위와 다른 부분은 동일하고, 두개의 정수를 받아서 첫번째 매개변수에서
//    두번째 매개변수를 뺀 값을 리턴하는 minus 라는 메소드를 정의해 보세요.
    public static int minus(int a, int b){
        System.out.println("minus a,b");
        return a - b;
    }
    public static int add(int a, int b, int c){
        System.out.println("add a,b,c");
        return a+ b + c;
    }
//    public static int add(int x, int y, int z,int a){
//        System.out.println("add a,b,c,d");
//        return x+ y + z;
//    }
    public static int add(int... numbers){
        System.out.println("... add 실행");
        int total = 0;
        for(int num : numbers){
            total += num;
        }
        return total;
    }

    //main - 프로그램의 시작점.
    public static void main(String[] args) {
       int result =  add(10,32);
        System.out.println(result);

        add(10,20,30);
        add(10,20,30,40);

        add(10);
        add(10,20,30,40,50,60);
        add(10,20,30,40,50,60,70);
        add(10,30);
    }
}
