package threadexam;

public class ThreadTest {
    public static void main(String[] args) {
        System.out.println("main start@@");

        MyThread myThread = new MyThread("철수");
        myThread.start();

        MyThread myThread2 = new MyThread("영희");
        myThread2.start();

        MyThread myThread3 = new MyThread("순이");
        myThread3.start();

        for(int i=0;i<10;i++){
            System.out.println("main"+i);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }

        System.out.println("main end@@");
    }
}
