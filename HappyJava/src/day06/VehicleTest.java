package day06;

public class VehicleTest {
    public static void main(String[] args) {
        Car car = new Car("현대", "소나타", 2023, 4);
        Motorcycle bike = new Motorcycle("할리데이비슨", "스포츠스터", 2022, "크루저");

        System.out.println("=== 자동차 정보 ===");
        car.displayInfo();
        car.start();
        car.openTrunk();
        car.stop();

        System.out.println("\n=== 오토바이 정보 ===");
        bike.displayInfo();
        bike.start();
        bike.wheelie();
        bike.stop();
    }
}