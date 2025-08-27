package day08;

public class PersonTest {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("knag");
        person.setAge(30);
        person.setAddress("경기도 고양시");

        System.out.println(person);

        Person kim = new Person("kim",20,"강남구 역삼동");
        System.out.println(kim);
        java.lang.String test = "test";
        if("test".equals(test)){
            System.out.println("같아요");
        }
    }
}
