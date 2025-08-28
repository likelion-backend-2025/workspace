package day09;

import java.util.HashSet;
import java.util.Set;

public class SetExam {
    public static void main(String[] args) {
        Set<String> fruitSet = new HashSet<>();
        //데이터추가
        fruitSet.add("사과");
        fruitSet.add("귤");
        fruitSet.add("포도");
        fruitSet.add("사과");
        fruitSet.add("사과");
        fruitSet.add("사과");
        fruitSet.add("사과");


        System.out.println(fruitSet);

        System.out.println();

        Set<Pen> penSet = new HashSet<>();
        penSet.add(new Pen("red"));
        penSet.add(new Pen("black"));
        penSet.add(new Pen("red"));
        penSet.add(new Pen("red"));

        System.out.println(penSet);
    }
}
