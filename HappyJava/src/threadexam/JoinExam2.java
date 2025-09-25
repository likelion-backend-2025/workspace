package threadexam;

class TaskThread extends Thread {
    public void run() {
        System.out.println("작업시작!!");
        try{
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class DemonThread extends Thread {
    public void run() {
        while(true){
            System.out.println("배경음악 재생중!!");
            try{
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class JoinExam2 {
    public static void main(String[] args) {
        TaskThread t1 = new TaskThread();
        t1.start();

        DemonThread demonThread = new DemonThread();
        demonThread.setDaemon(true);
        demonThread.start();

        try{
            t1.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("모든 작업완료!!!");
    }
}
