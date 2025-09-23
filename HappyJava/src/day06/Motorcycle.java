package day06;

// 또 다른 자식 클래스
public class Motorcycle extends Vehicle {
    private String type;

    public Motorcycle(String brand, String model, int year, String type) {
        super(brand, model, year);
        this.type = type;
    }

    public void wheelie() {
        System.out.println("휠리를 합니다!");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("오토바이 타입: " + type);
    }
}