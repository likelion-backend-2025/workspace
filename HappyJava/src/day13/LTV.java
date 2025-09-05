package day13;

public class LTV implements TV{
    @Override
    public void volumeUp() {
        System.out.println("Sound up");
    }

    @Override
    public void volumeDown() {
        System.out.println("Sound down");
    }

    public void turnOn() {
        System.out.println("Turning on");
    }

    public void turnOff() {
        System.out.println("Turning off");
    }


}
