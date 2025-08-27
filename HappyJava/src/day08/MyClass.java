package day08;

import java.util.Objects;

public class MyClass /*extends ImmutableClass*/ {
    String name;
//    int value;

    public String toString(){
        return "나는 "+name+"입니다.";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MyClass myClass = (MyClass) o;
        return Objects.equals(name, myClass.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();

        myClass.name = "강경미";

        System.out.println(myClass);
        System.out.println(myClass.toString());

        MyClass myClass2 = new MyClass();
        myClass2.name = "강경미";

        //myClass 와 myClass2 는 같은객체일까요??  다른 객체일까요?
        System.out.println(myClass == myClass2);
        //위와같이 비교하면 주소만 비교하므로 다르다!!라고 판단한다.

        //그러나 우리는 객체타입과 필드의 값이 같다면 같아요.. 라고 하고 싶다.
        //그것을 비교하는 기능 equals
        System.out.println("===========equals================");
        System.out.println(myClass.equals(myClass2));


        System.out.println(myClass.equals(myClass));

        System.out.println(myClass.getClass());

        System.out.println(myClass.hashCode());
        myClass2.name="kim";
        System.out.println(myClass2.hashCode());
    }

}
