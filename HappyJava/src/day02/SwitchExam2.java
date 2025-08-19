package day02;

public class SwitchExam2 {
    public static void main(String[] args) {
        int day = 3;
        
        String result = switch (day){
            case 1 -> "월요일";
            default -> throw new IllegalStateException("Unexpected value: " + day);
        };
    }
}
