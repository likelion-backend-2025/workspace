package day13;

public class LTV implements TV{
    @Override
    public void volumeUp() {
        System.out.println("LTV Sound up");
    }

    @Override
    public void volumeDown() {
        System.out.println("LTV Sound down");
    }

    public void turnOn() {
        System.out.println("LTV Turning on");
    }

    public void turnOff() {
        System.out.println("LTV Turning off");
    }


}
