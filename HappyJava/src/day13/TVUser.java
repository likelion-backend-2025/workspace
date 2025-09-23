package day13;

public class TVUser {
    public static void main(String[] args) {
//        STV tv = new STV();
//        LTV tv = new LTV();

//        TV tv = new LTV(); // 이 부분도 수정되지 않을 수 없을까???
//        TV tv = new STV();   //이렇게 TV 인터페이스를 통해서 결합도가 떨어졌다. 근데..  아쉬운 부분.
//        LTV(); new STV()  이렇게 바뀌어야 하더라!!!

        TV tv = TVFactory.getTV(args[0]);

//        TV tv1;  지금 우리 코드에서는 안되요~~

        tv.turnOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.turnOff();

    }
}
