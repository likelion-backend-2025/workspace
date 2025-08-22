package day05;

public class Book2 {
    String title;
    String author;
    int price;

    public Book2() {

        this("제목없음", "작자미상", 0);
    }

    // 매개변수 1개
    public Book2(String title) {
        this.title = title;
    }

    // 매개변수 3개 (모든 필드 초기화)
    public Book2(String title, String author, int price) {
        this(title);
        this.author = author;
        this.price = price;
    }
}
