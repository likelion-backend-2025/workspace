package day05;

public class Student {
    // 인스턴스 필드
    String name;
    int score;

    // 클래스 필드 (모든 인스턴스가 공유)
    static int totalStudents = 0;
    static int totalScore = 0;

    // 생성자
    public Student(String name, int score) {
        this.name = name;
        this.score = score;
        totalStudents++;
        totalScore += score;
    }

    // 인스턴스 메소드
    public void displayInfo() {
        System.out.println(name + ": " + score + "점");
    }

    // 클래스 메소드
    public static double getAverage() {
        if(totalStudents == 0) return 0;
        return (double)totalScore / totalStudents;
    }

    public static void displayStatistics() {
        System.out.println("전체 학생 수: " + totalStudents);
        System.out.println("전체 점수 합: " + totalScore);
        System.out.println("평균 점수: " + getAverage());
    }
}