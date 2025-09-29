package threadexam;

import java.util.concurrent.*;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        // 고정 크기 스레드 풀 생성
        ExecutorService executor = Executors.newFixedThreadPool(7);

        // 작업 제출
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("작업 " + taskId + " 실행 중 - " +
                        Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 스레드 풀 종료
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}