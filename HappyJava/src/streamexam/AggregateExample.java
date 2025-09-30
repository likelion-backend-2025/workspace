package streamexam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AggregateExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 7, 2, 9, 1, 5, 8, 4, 6);
        // count - 요소 개수
        long count = numbers.stream().count();
        System.out.println("개수: " + count); // 9


        // min - 최솟값
        Optional<Integer> min = numbers.stream()
                .min(Integer::compareTo);
        min.ifPresent(n -> System.out.println("최솟값: " + n)); // 1

        // max - 최댓값
        Optional<Integer> max = numbers.stream()
                .max(Integer::compareTo);
        max.ifPresent(n -> System.out.println("최댓값: " + n)); // 9

    }
}
