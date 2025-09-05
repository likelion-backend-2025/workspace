package day13;

public class TVUser {
    public static void main(String[] args) {
//        STV tv = new STV();
//        LTV tv = new LTV();

        TV tv = null;/*new LTV();*/ // 이 부분도 수정되지 않을 수 없을까???

        tv.turnOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.turnOff();

    }
}
