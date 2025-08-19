package day02;

public class GradeExample {
    public static void main(String[] args) {
        int score = 60;
        char grade;

        if (score >= 90) {
            grade = 'A';
        } else if (score >= 80) {
            grade = 'B';
        } else if (score >= 70) {
            grade = 'C';
        } else if (score >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("점수: " + score);
        System.out.println("학점: " + grade);
    }
}