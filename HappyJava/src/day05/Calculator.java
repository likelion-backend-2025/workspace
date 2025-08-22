package day05;

public class Calculator {
    //계산기. - 추상화 해볼까요?

    //메서드가 5줄을 넘지마라.
    //무슨말일까요??
    //어떤 하나의 기능, 객체...  단일책임의 원칙.

    public int plus(int i, int j){
        return i+j;
    }
    public int plus(int x, int y,String name){
        return x+y;
    }
    public int plus(int i, int j, int x){
        return i + j + x;
    }
    public void plus (String str, int i){

    }
    public void plus(int i, String str){

    }


    public static void main(String[] args) {
        Calculator cal = new Calculator();
        cal.plus(10,20);
        cal.plus("test",30);

        System.out.println(10);
        System.out.println('c');

        System.out.println("test");
    }


}
