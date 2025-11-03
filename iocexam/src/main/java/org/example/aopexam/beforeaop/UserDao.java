package org.example.aopexam.beforeaop;

public class UserDao {
//    LogBean logBean = new LogBean();
    TransactionBean transactionBean = new TransactionBean();
    SecurityBean securityBean = new SecurityBean();
    //User를 하나 추가!!
    public void addUseer(){

        //로그남기기
//        System.out.println("로그를 남깁니다. ");

//        logBean.logging();
        securityBean.doSomething();

        //트랜잭션 처리
//        System.out.println("트랜잭션을 처리합니다.");
        TransactionBean transactionBean = new TransactionBean();
        transactionBean.startTransaction();


        System.out.println("User를 추가하는 코드1 ");
        System.out.println("User를 추가하는 코드2 ");
        System.out.println("User를 추가하는 코드3 ");
        System.out.println("User를 추가하는 코드4 ");


        //트랜잭션 처리
//        System.out.println("트랜잭션을 처리합니다. ");
        transactionBean.endTransaction();

    }

    public void updateUser(){

        //로그남기기
        System.out.println("로그를 남깁니다. ");
        //트랜잭션 처리
        System.out.println("트랜잭션을 처리합니다.");

        System.out.println("User를 수정하는 코드1 ");
        System.out.println("User를 수정하는 코드2 ");
        System.out.println("User를 수정하는 코드3 ");
        System.out.println("User를 수정하는 코드4 ");


        //트랜잭션 처리
        System.out.println("트랜잭션을 처리합니다. ");


    }

    public void deleteUser(){
//        logBean.logging(); //횡단관심  advice

        transactionBean.startTransaction();   //횡단관심  advice

        System.out.println("User를 삭제하는 코드1");  //핵심관심  target  

        transactionBean.endTransaction(); //횡단관심  advice
    }
}
