package org.example.aopexam.beforeaop;

public class AnotherDao {
    LogBean logBean = new LogBean();
    TransactionBean transactionBean = new TransactionBean();
    public void addAnother(){

        logBean.logging();
        transactionBean.startTransaction();

        System.out.println("another 를 추가하는 코드 1");


        transactionBean.endTransaction();


    }
}
