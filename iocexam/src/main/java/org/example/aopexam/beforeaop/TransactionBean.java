package org.example.aopexam.beforeaop;

public class TransactionBean {
    public void startTransaction(){
        System.out.println("트랜잭션 처리를 합니다.  ");
    }

    public void endTransaction(){
        System.out.println("트랜잭션 처리를 합니다.");
    }
}
