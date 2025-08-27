package day08;

public class EnumTest {
    public static void main(String[] args) {
        //요일 값을 저장하고 싶다.
        String day;
        day = "MONDAY";
        //나는 요일값만 저장하고 싶은데...
        day = "test";
        day = "kang";

        Day today = Day.TUESDAY;
        //열거형 타입을 선언해 줌으로서 잘못된 값이 들어가지 않는다.
//        today = "kang";

        OrderStatus status = OrderStatus.CANCELLED;
        System.out.println(status.getDescription());
    }
}
