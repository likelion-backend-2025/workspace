package day07;

public abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    // 추상 메소드 (구현부가 없음)
    public abstract double getArea();
    public abstract double getPerimeter();

    // 일반 메소드
    public void displayColor() {
        System.out.println("색상: " + color);
    }
}