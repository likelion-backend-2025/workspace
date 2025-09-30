package streamexam;

import java.util.Arrays;
import java.util.List;

public class MatchingExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// allMatch - 모든 요소가 조건 만족
        boolean allPositive = numbers.stream()
                .allMatch(n ->{
                    System.out.println(n);
                    return n > 0;});
        System.out.println("모두 양수? " + allPositive); // true

        System.out.println("=========anyMatch==============");
        // anyMatch - 하나라도 조건 만족
        boolean hasEven = numbers.stream()
                .anyMatch(n -> n % 2 == 0);
        System.out.println("짝수 존재? " + hasEven); // true

        System.out.println("=========noneMatch==============");

        // noneMatch - 모든 요소가 조건 불만족
        boolean noNegative = numbers.stream()
                .noneMatch(n -> n < 0);
        System.out.println("음수 없음? " + noNegative); // true
    }
}
