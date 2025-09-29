package threadexam;

import java.util.LinkedList;
import java.util.Queue;

public class PrinterSpooler {
    private Queue<String> printQueue = new LinkedList<>();
    private boolean isPrinting = false;

    // 문서 추가
    public synchronized void addDocument(String doc) {
        printQueue.offer(doc);
        System.out.println("문서 추가: " + doc);

        if (!isPrinting) {
            notify(); // 프린터 깨우기
        }
    }

    // 인쇄 작업
    public synchronized void print() throws InterruptedException {
        while (true) {
            while (printQueue.isEmpty()) {
                System.out.println("프린터 대기 중...");
                wait(); // 문서를 기다림
            }

            isPrinting = true;
            String doc = printQueue.poll();
            System.out.println("인쇄 시작: " + doc);

            Thread.sleep(2000); // 인쇄 시뮬레이션

            System.out.println("인쇄 완료: " + doc);
            isPrinting = false;
        }
    }

    public static void main(String[] args) {
        PrinterSpooler spooler = new PrinterSpooler();

        Thread printThread = new Thread(()->{
            try{
                spooler.print();
            }catch (Exception e){
                System.out.println("프린터 스레드 종료");
                Thread.currentThread().interrupt();
            }
        }, "printThread");

        System.out.println("=========== 프린트 스풀러 시작 ==========");
        printThread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Thread userThread = new Thread(()->{
           try{
               spooler.addDocument("lion document.pdf");
               Thread.sleep(500);
               spooler.addDocument("lion thread.pdf");
               Thread.sleep(500);
           }catch (Exception e){
               Thread.currentThread().interrupt();
//               e.printStackTrace();
           }
        },"userThread");


        Thread userThread2 = new Thread(()->{
            try{
                spooler.addDocument("lion document2.pdf");
                Thread.sleep(500);
                spooler.addDocument("lion thread2.pdf");
                Thread.sleep(500);
            }catch (Exception e){
                Thread.currentThread().interrupt();
//               e.printStackTrace();
            }
        },"userThread2");

        userThread.start();
        userThread2.start();

        try {
            userThread.join();
            userThread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        printThread.interrupt();

        System.out.println("=======프린트 스플러 시스템 종료 ==============");
    }
}