package day07;

//추상클래스는 - 클래스예요. 미완성 메서드를 가질 수 있는 클래스
//인터페이스는 껍데기만 갖는다.  - 구현체가 없는것이 기본@@
public interface Drawable {
    void draw(); //추상메서드(구현부가 X)
    void erase();

    //자바의 interface는 java version 7 까지는 구현체를 가질 수 없었다.

    //java version 8 에서 추가된 메서드.
    default void defaTest() {
        System.out.println("필요하면 자식클래스가 오버라이드해서 사용하는것이 목적!");
    }

    //java version 8 에서 추가된 메서드
    static void showInfo(){
        System.out.println("독립적인 기능을 구현함.!!");
    }
}
