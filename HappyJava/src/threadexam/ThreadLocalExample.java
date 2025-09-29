package threadexam;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalExample {
    private static final ThreadLocal<SimpleDateFormat> dateFormatter =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public String formatDate(Date date) {
        // 각 스레드가 자신만의 SimpleDateFormat 인스턴스 사용
        return dateFormatter.get().format(date);
    }

    public static void main(String[] args) {
        ThreadLocalExample example = new ThreadLocalExample();

        // 여러 스레드에서 안전하게 사용
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Date now = new Date();
                String formatted = example.formatDate(now);
                System.out.println(Thread.currentThread().getName() +
                        ": " + formatted);
            }).start();
        }
    }
}