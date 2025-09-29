package threadexam;

public class WaitNotifyExample {

    private static boolean itemsAvailable = false;
    private static final Object lock = new Object();

    static  class Producer extends Thread{
        public void run(){
            System.out.println("Producer start!!!");
            synchronized (lock) {
                System.out.println("생산자가 아이템을 생산하는 중!!! ");
                itemsAvailable = true;
                lock.notify(); //생산이 끝났다라고 알려줌..  wait 되어있는 쓰레드를 깨워줌.
                System.out.println("생산자가 알림을 보냄");
            }
        }
    }

    static class Consumer extends Thread{
        public void run(){
            System.out.println("Consumer start!!!");
            synchronized (lock) {
                while(!itemsAvailable) {
                    System.out.println("아이템 기다림");
                    try {
                        lock.wait();   //아이템이 아직 준비되지 않았으므로,  해당 스레드를 blocking 시킴.
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("아이템을 소비함.");
                itemsAvailable = false;

            }
        }
    }


    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        consumer.start();

        try {
            Thread.sleep(1000); //생산자 스레드를 일부러 늦게 동작하도록 해본다.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        producer.start();
    }
}