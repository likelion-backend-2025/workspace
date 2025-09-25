package threadexam;

public class MyRunnableTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable("영희"));
        Thread thread2 = new Thread(new MyRunnable("순희"));
        Thread thread3 = new Thread(new MyRunnable("철수"));
        thread.start();
        thread2.start();
        thread3.start();

        System.out.println("main thread end!!");
    }
}
