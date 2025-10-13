package sample.bean;

public class Book {
    private String title;
    private String author;
    private String isbn;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    // getter, setter, toString 메서드
    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author +
                "', isbn='" + isbn + "'}";
    }
}
