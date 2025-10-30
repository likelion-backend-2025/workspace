package builderexam.builderuser;

public class UserMain {
    public static void main(String[] args) {
        User kang = new User.Builder()
                .name("강경미")
                .age(3)
                .address("경기도 고양시")
                .email("carami@gmail.com")
                .build();

        System.out.println(kang);

        //User를 생성하는데 이름과, 나이만 가지고 생성하고 싶다면??
        User kim = new User.Builder()
                .name("kim")
                .age(20)
                .build();
        System.out.println(kim);
        //User를 생성하는데 이름과, 이메일만 가지고 생성하고 싶다면?
        User hong = new User.Builder()
                .name("홍길동")
                .email("hong@exam.com")
                .build();
        System.out.println(hong);

        //User를 생성하는데 이름과 주소만 가지고 생성하고 싶다면?
        User user2 = new User.Builder()
                .name("user2")
                .address("서울시 마포구")
                .build();
        //User를 생성하는데 이름, 주소, 이메일만 가지고 생성하고 싶다면???
        User user3 = new User.Builder()
                .name("user3")
                .address("부산")
                .email("user3@exam.com")
                .build();
        System.out.println(user2);
        System.out.println(user3);

        User user4 = new User.Builder().build();
        System.out.println(user4);
    }
}
