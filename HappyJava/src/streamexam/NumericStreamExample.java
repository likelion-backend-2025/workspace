package streamexam;

import java.util.Arrays;
import java.util.List;

public class NumericStreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // IntStream으로 변환하여 집계
        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("합계: " + sum); // 15

    }
}
