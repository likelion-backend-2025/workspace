package threadexam;

class SumThread extends Thread {
    @Override
    public void run() {
        //복잡한 계산을 해주는 쓰레드다.
        try{
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("계산종료!!!");
    }
}

public class JoinExam {
    public static void main(String[] args) {
        System.out.println("main이 할일 시작!!");
        System.out.println("복잡한 계산을 해야하는 일 을 만났다.");

        SumThread sum = new SumThread();
        sum.start();

        System.out.println("sumThread가 계산하는 동안에 main 이 할일을 진행중");

//        sumThread 일을 끝낼때까지 기다릴 필요가 있다.

        try {
            sum.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("sumThread에서 계산한 값을 이용해서 일을 마무리함.");
        

    }
}
