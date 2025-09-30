package streamexam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReduceExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);


        // 합계 구하기 (초기값 있음)
        int sum = numbers.stream()
                .reduce(0, (a, b) -> {
                    System.out.println(a +"::"+ b);
                    return a + b;});
        System.out.println("합계: " + sum); // 15



        // 합계 구하기 (메서드 참조)
        int sum2 = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("합계2: " + sum2); // 15

        // 최댓값 구하기 (초기값 없음)
        Optional<Integer> max = numbers.stream()
                .reduce(Integer::max);  //(a,b)->Integer.max(a,b)
        max.ifPresent(n -> System.out.println("최댓값: " + n)); // 5

        // 문자열 연결
        List<String> words = Arrays.asList("Hello", " ", "World", "!");
        String sentence = words.stream()
                .reduce("", String::concat);
        System.out.println(sentence); // Hello World!

    }
}
