package streamexam;

import java.util.ArrayList;
import java.util.List;

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 1000000; i++) {
            numbers.add(i);
        }


        // 순차 스트림 처리 시간 측정
        long startTime = System.currentTimeMillis();
        long sum1 = numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long sequentialTime = System.currentTimeMillis() - startTime;

        // 병렬 스트림 처리 시간 측정
        startTime = System.currentTimeMillis();
        long sum2 = numbers.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        long parallelTime = System.currentTimeMillis() - startTime;

        System.out.println("순차 처리 시간: " + sequentialTime + "ms");
        System.out.println("병렬 처리 시간: " + parallelTime + "ms");
        System.out.println("결과 동일: " + (sum1 == sum2));

    }
}
