package net.likelion.demo.service;

public class UserService {

    public void registerUser(){
        //회원가입이 가능한지..  확인
        //비지니스적으로 회원가입이 가능 한 회원인지 확인하는
        //로직이 들어올것임.

        //UserDAO - addUser()  호출하고,  addUser 가 리턴해준 id
//        를 이용해서  UserRoleDAO 의 addRole 같은 메서드를 호출하게 함.

        //트랜잭션 처리는 서비스레이어에서 처리함!!!
//        하나의 트랜잭션으로 묶인다 라는 것은??  Connection 을 같이 써야하는거!!
    }
}
