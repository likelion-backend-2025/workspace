package day07;

public class CaramiTest {
    public static void main(String[] args) {
        Carami carami = new Carami();
        //모든 클래스들의 레퍼런스 변수를 이용해서 사용될 수 있는
        //우리가 만들지 않은 메서드들을 볼 수 있어요.
        //Object 가 가지고 있는 메소드들!!!
        carami.setName("강경미");
        System.out.println(carami.toString());
        System.out.println(carami);

        String str = "hello";

        System.out.println(str.toString());
        System.out.println(str);

        //Object 가 가진 toString() 이라는 메서드...
        //

    }
}
