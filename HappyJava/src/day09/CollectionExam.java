package day09;

import day08.Pen;

import java.util.ArrayList;
import java.util.Iterator;

public class CollectionExam {
    public static void main(String[] args) {
        //가장 많이 사용되는 자료구조!!
        //저장될때--Object가 저장됨.

        ArrayList list = new ArrayList();
        list.add("123");
        list.add(11);
        list.add(new Pen());

        //어때요??
        //이렇게 모든 것이 다 들어갈때 단점은??

        list.get(0);

        ArrayList list2 = new ArrayList();
        list2.add("abc");
        list2.add("def");

        //왜 String 이 제공하는 메서드를 바로 사용할 수 없나요?
        //부모타입일때 자식의 메서드에 접근이 안됨.
        ((String)list2.get(0)).trim();

        //< > 제네릭이 추가된 이유!!!
        ArrayList<String> sList = new ArrayList<>();

        sList.add("abc");
//        sList.add(11);
//        sList.add(new Pen());  제네릭으로 제한되면 다른타입은 입장불가

        sList.add("kang");
        sList.add("kim");

        System.out.println(sList.size());
//ArrayList는 순서가 있는 자료구조이므로 인덱스가 존재한다.
        for(int i = 0; i < sList.size(); i++){
            System.out.println(sList.get(i));
        }

        System.out.println("======================");
        Iterator<String> iter =  sList.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
        System.out.println("======================");
        for(String str : sList){
            System.out.println(str);
        }





    }
}
