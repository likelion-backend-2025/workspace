package builderexam.lombokuser;

public class UserMain {
    public static void main(String[] args) {
        User kang = User.builder()
                .name("kang")
                .email("kang@exam.com")
                .build();

        System.out.println(kang);

        User user = User.builder().build();
        System.out.println(user);
    }
}
