package day05;

// 사용 예제
public class PersonTest {
    public static void main(String[] args) {
        Person person = new Person("김철수", 25);

        // 메소드 체이닝
        person.setAddress("서울시 강남구")
                .setAge(26).displayAll();

        person.displayAll();
    }
}
