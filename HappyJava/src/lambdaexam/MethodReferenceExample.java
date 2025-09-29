package lambdaexam;

import java.util.Arrays;
import java.util.List;

public class MethodReferenceExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("alice", "bob", "charlie", "david");

        System.out.println(names);

        names.forEach((name) -> System.out.println(name));
        System.out.println("=".repeat(50));

        names.forEach(System.out::println);


        names.stream()
                .map(String::toUpperCase);   //(name) -> name.toUpperCase()
    }
}
