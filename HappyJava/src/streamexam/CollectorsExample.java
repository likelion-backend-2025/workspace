package streamexam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectorsExample {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Alice", 85, "CS"),
                new Student("Bob", 92, "Math"),
                new Student("Charlie", 78, "CS"),
                new Student("David", 88, "Physics"),
                new Student("Eve", 95, "Math")
        );

        // toList로 수집
        List<String> names = students.stream()
                .map(Student::getName)
                .collect(Collectors.toList());

        // toSet으로 수집
        Set<String> departments = students.stream()
                .map(Student::getDepartment)
                .collect(Collectors.toSet());

        // toMap으로 수집
        Map<String, Integer> nameToScore = students.stream()
                .collect(Collectors.toMap(
                        Student::getName,
                        Student::getScore
                ));

        // joining으로 문자열 결합
        String allNames = students.stream()
                .map(Student::getName)
                .collect(Collectors.joining(", "));
        System.out.println("모든 학생: " + allNames);
        // 모든 학생: Alice, Bob, Charlie, David, Eve
    }

    static class Student {
        private String name;
        private int score;
        private String department;

        public Student(String name, int score, String department) {
            this.name = name;
            this.score = score;
            this.department = department;
        }

        // getter 메서드들
        public String getName() { return name; }
        public int getScore() { return score; }
        public String getDepartment() { return department; }
    }
}