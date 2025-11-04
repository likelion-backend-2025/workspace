package org.example.basicsecurity;

public class UserContext {
    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<User>();

    //ThreadLocal에게 값을 맡기기 위한 메서드
    public static void setUser(User user) {
        userThreadLocal.set(user);
    }
    //ThreadLocal에서 값을 얻어오기 위한 메서드
    public static User getUser() {
        return userThreadLocal.get();
    }

    //ThreadLocal 을 초기화 하는 메서드
    public static void clear(){
        userThreadLocal.remove();  //중요!!  왜 중요 할까요?
        //쓰레드 풀을 사용하기 때문에!!  -- 재사용된다라는 이야기!!
    }
}
