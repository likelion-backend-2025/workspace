package threadexam;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int workerCount = 3;
        CountDownLatch latch = new CountDownLatch(workerCount);

        for (int i = 1; i <= workerCount; i++) {
            final int workerId = i;
            new Thread(() -> {
                try {
                    System.out.println("Worker " + workerId + " 작업 시작");
                    Thread.sleep(workerId * 1000);
                    System.out.println("Worker " + workerId + " 작업 완료");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown(); // 카운트 감소
                }
            }).start();
        }

        System.out.println("모든 작업자 대기 중...");
        latch.await(); // 카운트가 0이 될 때까지 대기
        System.out.println("모든 작업 완료!");
    }
}