package day03;

import day04.Person;

public class PersonExam2 extends Person{
    public void test(){
        System.out.println(name);
//        System.out.println(age);  //패키지 영역이므로 다른 패키지라안됨.
        System.out.println(address);
//        System.out.println(phoneNumber); private 영역이라 비공개
    }
    public static void main(String[] args) {
        Person person = new Person();

        person.name = "knag";
//        person.age = 20;  패키지가 달라서 접근 불가.
//        person.address = "경기도";

//        person.phoneNumber = "0101112222";  private 하므로 접근불가


        PersonExam2 personExam2 = new PersonExam2();
        System.out.println(personExam2.address);
    }
}
