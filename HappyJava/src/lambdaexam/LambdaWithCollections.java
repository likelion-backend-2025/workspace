package lambdaexam;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaWithCollections {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("김철수", "이영희", "박민수", "정수진", "최영수");

        // 1. forEach로 출력
        System.out.println("=== 전체 이름 출력 ===");
        names.forEach(name -> System.out.println(name));

        // 2. 필터링 - '김'으로 시작하는 이름
        System.out.println("\n=== '김'으로 시작하는 이름 ===");
        names.stream()
                .filter(name -> name.startsWith("김"))
                .forEach(System.out::println);

        // 3. 변환 - 이름을 대문자로 변환
        System.out.println("\n=== 대문자로 변환된 이름 ===");
        List<String> upperNames = names.stream()
                .map(name -> name.toUpperCase())
                .collect(Collectors.toList());
        upperNames.forEach(System.out::println);

        // 4. 정렬
        System.out.println("\n=== 이름 정렬 ===");
        names.stream()
                .sorted((name1, name2) -> name1.compareTo(name2))
                .forEach(System.out::println);

        // 5. 숫자 리스트 처리
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 짝수만 필터링하고 제곱한 후 합계 구하기
        int sumOfSquaredEvens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .reduce(0, (a, b) -> a + b);

        System.out.println("\n짝수 제곱의 합: " + sumOfSquaredEvens); // 2^2 + 4^2 + 6^2 + 8^2 + 10^2 = 220
    }
}