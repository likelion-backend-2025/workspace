package day09;

import java.util.ArrayList;
import java.util.List;

public class ListExam {
    public static void main(String[] args) {
        ArrayList<String> aList = new ArrayList<>();

        //이렇게 선언된 코드를 우리 많이 만나게 됩니다.
        List<String> fruits = new ArrayList<>();

        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Apple22");
        fruits.add("Apple");
        fruits.add("Apple");
        fruits.add("Apple");
        fruits.add("Apple");

        System.out.println(fruits.get(0));

        fruits.set(0,"Cherry");

        System.out.println(fruits.get(0));

        System.out.println(fruits);

        //정리 잘하시는 분들!!
        //포스트잇!! - 안에 들어있는 내용물리스트

        fruits.remove("Cherry");
        System.out.println(fruits);
        System.out.println(fruits.remove(0));
        System.out.println(fruits);



    }
}
