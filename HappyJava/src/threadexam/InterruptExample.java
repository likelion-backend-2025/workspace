package threadexam;
public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("작업 진행: " + i + "/10");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("작업이 중단되었습니다!");
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println("작업 완료");
        });

        worker.start();

        Thread.sleep(3500); // 3.5초 대기
        worker.interrupt(); // 작업 중단 요청
    }
}