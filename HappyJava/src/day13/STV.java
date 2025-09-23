package day13;

//전을을 켜다,  전원을 끄다, 소리를 줄이다, 소리를 키우다.
public class STV implements TV{

    public void turnOn(){
        System.out.println("STV TV 켜짐");
    }

    public void turnOff(){
        System.out.println("STV TV 꺼짐");
    }

    public void volumeUp(){
        System.out.println("STV  TV 볼륨이 올라감");
    }

    public void volumeDown(){
        System.out.println("STV TV 볼륨이 내려감");
    }

}