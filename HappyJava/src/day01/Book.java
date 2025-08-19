package day01;

public class Book {
    private String title;
    private int price;

    public int getPrice() {
        return this.price * 2;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}