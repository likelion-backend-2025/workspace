package day04;

public class PersonExam {
    public static void main(String[] args) {
        //Person 가진 필드를 이용하려면 뭐해야하나요?
//        Person person = new Person();
//        person.name = "강경미";
//        person.age = 20;

        //static 필드는 인스턴스화 하지 않고 사용가능하다.
        Person.count++;

        System.out.println(Person.MAX_AGE);
        System.out.println(Person.count);

        Person kang = new Person();
        kang.name = "강경미";

        Person kim = new Person();
        kim.name = "김주원";

        kang.count++;
        System.out.println(kang.count);

        System.out.println(kim.count);
        System.out.println(kang.name);
        System.out.println(kim.name);



    }
}
