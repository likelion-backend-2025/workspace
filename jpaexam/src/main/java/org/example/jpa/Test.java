package org.example.jpa;

import lombok.Getter;
import lombok.Setter;


public class Test {
    private String name;

    public Test(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.setName("test");
        test.getName();

        System.out.println(test.getName());

    }
}
