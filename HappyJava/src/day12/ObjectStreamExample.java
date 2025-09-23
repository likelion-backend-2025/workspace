package day12;

import java.io.*;

// 직렬화 가능한 User 클래스
class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private String email;

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // getter, setter, toString 메소드들...
    public String toString() {
        return "User{name='" + name + "', age=" + age + ", email='" + email + "'}";
    }
}

public class ObjectStreamExample {
    public static void main(String[] args) {
        try {
            // 객체 쓰기
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("user.dat"));

            User user = new User("홍길동", 25, "hong@example.com");
            oos.writeObject(user);
            oos.close();

            // 객체 읽기
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream("user.dat"));

            User readUser = (User) ois.readObject();
            System.out.println("읽어온 객체: " + readUser);
            ois.close();

        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}