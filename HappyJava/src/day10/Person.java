package day10;

public class Person implements Comparable<Person>{
    @Override
    public int compareTo(Person o) {
        //우리가 정렬할 기준점
        return this.age - o.age;
    }

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name ;
    }
}
