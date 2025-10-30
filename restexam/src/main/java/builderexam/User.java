package builderexam;

public class User {
    private String name;
    private int age;
    private String email;
    private String address;

    public User(String name, int age, String email, String address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
