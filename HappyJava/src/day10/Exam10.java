package day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Exam10 {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i <= 10; i++){
            numbers.add(i);
        }
        System.out.println(numbers);
        Collections.shuffle(numbers);
        System.out.println(numbers);

        Collections.sort(numbers);
        System.out.println(numbers);

        Collections.sort(numbers, Collections.reverseOrder());
        System.out.println(numbers);

        //여기서 질문!!
        //sort라는 메서드가 수행될때..  크다 작다는 어떻게 판단할까요?
        //지금 보여드린 예제는...  숫자

        //만약  ArrayList에 Person 이 들어있다면??

        ArrayList<Person>  persons = new ArrayList<>();

        //이때...  psersons 를 정렬하라고 한다면???
        //Collectios 의 sort 메서드는 어떻게 할까요???
        persons.add(new Person("kang",30));
        persons.add(new Person("kim",20));
        persons.add(new Person("hong",25));
        persons.add(new Person("lee",37));
        persons.add(new Person("bbb", 22));

        System.out.println(persons);
        Collections.sort(persons);

        System.out.println(persons);

        //PersonComparator -  Pserson 의 정렬 기준을 가진 객체.
        //PersonComparator 이런 객체가 독자적으로 자주 사용될지???
        Collections.sort(persons, new PersonComparator());

        Comparator<Person> personCom = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        };

        Collections.sort(persons,personCom);

        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        //람다식.
        //자바는 매개변수에 객체 안닌것들이 못들어와요.
        //함수 - 일급객체로 취급하지 않는다.
        Collections.sort(persons, (o1,o2)->o1.getAge() - o2.getAge());

        System.out.println("나이기준");
        System.out.println(persons);

        //나이를 기준점으로 삼고있었다면,
        //이번에는 이름으로 정렬되도록 구현해 보세요.
        System.out.println("이름기준");
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println(persons);

        System.out.println("kang".compareTo("kim"));
        System.out.println("kang".compareTo("kang"));
        System.out.println("kang".compareTo("aaa"));



Comparable c = "abc";





//        ArrayList<String> strList = new ArrayList<>();
//        strList.add("kang");
//        strList.add("kim");
//        strList.add("hong");
//
//        System.out.println(strList);
//        Collections.sort(strList);
//        System.out.println(strList);

    }

}
