package day09;

import java.util.HashSet;
import java.util.Iterator;
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
//set은 index를 갖고있지 않다.  순서가 중요하지 않은 자료구조.
        System.out.println();

        Iterator<String> iterator = fruitSet.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("================");
        for(String str : fruitSet){
            System.out.println(str);
        }

        System.out.println(fruitSet.contains("사과"));
        System.out.println(fruitSet.remove("사과"));
        System.out.println(fruitSet);
        System.out.println(fruitSet.size());
    }
}
