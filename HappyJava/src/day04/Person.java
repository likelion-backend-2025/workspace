package day04;

public class Person {
    public String name; //--전체공개. 
    int age; //패키지..  같은 패키지에서만 공개
    protected String address = "서울시"; //같은패키지와 상속관계에 있다면 공개
    private String phoneNumber; //비공개 객체내에서 사용가능

    static int count = 0;

    static final int MAX_AGE = 150;


    public void printPhoneNumber(){
        System.out.println(phoneNumber);
    }
}
