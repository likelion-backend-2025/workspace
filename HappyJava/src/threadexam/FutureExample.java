package threadexam;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Callable은 결과를 반환할 수 있음
        Callable<Integer> task = () -> {
            System.out.println("계산 시작...");
            Thread.sleep(2000);
            return 42;
        };

        Future<Integer> future = executor.submit(task);

        System.out.println("다른 작업 수행...");

        // 결과 대기 및 획득
        Integer result = future.get(); // 블로킹
        System.out.println("계산 결과: " + result);

        executor.shutdown();
    }
}