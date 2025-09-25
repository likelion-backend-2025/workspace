package threadexam;

class Counter {
    private int count = 0;

    // 동기화하지 않은 메서드 (문제 발생!)
    public void increment() {
        count++;  // 실제로는 3단계: 읽기 → 증가 → 쓰기
    }

    public int getCount() {
        return count;
    }
}

public class SynchronizedCounter {
    private int count = 0;

    // 동기화된 메서드
    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedCounter counter = new SynchronizedCounter();

        // 10개 스레드가 각각 1000번씩 증가
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        // 모든 스레드 종료 대기
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("최종 카운트: " + counter.getCount()); // 10000
    }
}
