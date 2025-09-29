package threadexam;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // 여러 스레드가 동시에 맵 수정
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    map.merge("count", 1, Integer::sum);
                    map.put("thread-" + threadId, j);
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("총 카운트: " + map.get("count")); // 1000
    }
}