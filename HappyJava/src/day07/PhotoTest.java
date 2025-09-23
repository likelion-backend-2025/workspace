package day07;

public class PhotoTest {
    public static void main(String[] args) {
        //추상클래스도 타입으로 사용할 수 있었던거 기억나시나요?
        //인터페이스도 타입으로 사용이 가능 합니다.

        Drawable d = new Photo("a.png", 200,500);

        //d.  어떤 것 까지 사용할 수 있을까요?
        d.draw();
        d.erase();

        //Drawable d  타입을 이렇게 사용하게 할 때 장점은??

        //??  그냥 Circle c = new Circle();


    }
}
