package day05;

public class Printer {
    // 다양한 타입을 출력하는 print 메소드들
    public void print(int value) {
        System.out.println("정수: " + value);
    }

    public void print(double value) {
        System.out.println("실수: " + value);
    }

    public void print(String value) {
        System.out.println("문자열: " + value);
    }

    public void print(int x, int y) {
        System.out.println("좌표: (" + x + ", " + y + ")");
    }

    // 가변 매개변수
    public void print(String... values) {
        System.out.print("여러 문자열: ");
        for(String value : values) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.print("a","b");

    }
}