package com.mycompany.model;

// 파일: com/mycompany/model/Product.java

public class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void display() {
        System.out.println(name + ": " + price + "원");
    }
}