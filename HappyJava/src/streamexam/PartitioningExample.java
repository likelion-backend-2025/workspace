package streamexam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<Boolean, List<Integer>> evenOddPartition = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        evenOddPartition.get(true).forEach(System.out::println);
        evenOddPartition.get(false).forEach(System.out::println);

        // 학생을 합격/불합격으로 분할
        List<Student> students = Arrays.asList(
                new Student("Alice", 85),
                new Student("Bob", 65),
                new Student("Charlie", 75),
                new Student("David", 55)
        );

        Map<Boolean, List<Student>> passFail = students.stream()
                .collect(Collectors.partitioningBy(s -> s.getScore() >= 70));

        System.out.println("\n합격자:");
        passFail.get(true).forEach(s ->
                System.out.println(s.getName() + ": " + s.getScore())
        );

        System.out.println("\n불합격자:");
        passFail.get(false).forEach(s ->
                System.out.println(s.getName() + ": " + s.getScore())
        );

    }

    static class Student {
        private String name;
        private int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() { return name; }
        public int getScore() { return score; }
    }
}
