package org.example.junitexam;

public class Calculator {

    public int add(int a, int b) {
        return a + b ;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("0으로 나눌 수 없습니다!");
        }
        return a / b;
    }

    public static void main(String[] args) {

    }
}