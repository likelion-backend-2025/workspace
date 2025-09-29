package threadexam;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

        // 생산자
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    String item = "Item-" + i;
                    queue.put(item); // 큐가 가득 차면 대기
                    System.out.println("생산: " + item);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 소비자
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    String item = queue.take(); // 큐가 비면 대기
                    System.out.println("소비: " + item);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}