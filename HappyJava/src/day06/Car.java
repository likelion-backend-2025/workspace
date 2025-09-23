package day06;

// 자식 클래스
public class Car extends Vehicle {
    private int numberOfDoors;

    public Car(String brand, String model, int year, int numberOfDoors) {
        super(brand, model, year);  // 부모 생성자 호출
        this.numberOfDoors = numberOfDoors;
    }

    // Car만의 고유 메소드
    public void openTrunk() {
        System.out.println("트렁크를 엽니다.");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();  // 부모 메소드 호출
        System.out.println("문 개수: " + numberOfDoors);
    }
}
