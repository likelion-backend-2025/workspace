package threadexam;

class caramiRunnable implements Runnable{
    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            System.out.println("람다 스레드: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class LambdaThreadExample {
    public static void main(String[] args) {
//        Thread thread = new Thread(()->{
//            for (int i = 0; i < 5; i++) {
//                System.out.println("람다 스레드: " + i);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        Thread thread = new Thread(new caramiRunnable());

        thread.start();
    }
}
