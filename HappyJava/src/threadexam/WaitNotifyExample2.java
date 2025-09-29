package threadexam;

public class WaitNotifyExample2 {

    private static int itemsAvailable = 0; //사용 가능한 아이템 수
    private static final Object lock = new Object();

    static  class Producer extends Thread{
        public void run(){
            System.out.println("Producer start!!!");
            synchronized (lock) {
                System.out.println("생산자가 아이템을 생산하는 중!!! ");
                itemsAvailable += 5;
                System.out.println("생상자가 "+itemsAvailable+"개의 아이템을 생산하였습니다.");
                lock.notifyAll(); //생산이 끝났다라고 알려줌..  wait 되어있는 쓰레드를 모두 깨워줌.
                System.out.println("생산자가 모든 소비자에게 알림을 보냄");
            }
        }
    }

    static class Consumer extends Thread{
        private String name;
        public Consumer(String name){
            this.name = name;
        }
        public void run(){
            System.out.println("Consumer start!!!");
            synchronized (lock) {
                while(itemsAvailable <= 0) {
                    System.out.println("아이템 기다림");
                    try {
                        lock.wait();   //아이템이 아직 준비되지 않았으므로,  해당 스레드를 blocking 시킴.
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("아이템을 소비함.");
                itemsAvailable--;
                System.out.println("소비자 "+name+"님이 아이템을 소비했습니다.  남은 아이템 수 : "+itemsAvailable);

            }
        }
    }


    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer("kang");
        Consumer consumer2 = new Consumer("kim");
        Consumer consumer3 = new Consumer("hong");

        consumer.start();
        consumer2.start();
        consumer3.start();

        try {
            Thread.sleep(1000); //생산자 스레드를 일부러 늦게 동작하도록 해본다.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        producer.start();
    }
}