package day05;


import java.util.Scanner;


public class Methodtest {
    public String mtest(){
        return "hello";
    }

    public static void main(String[] args) {
        Methodtest m = new Methodtest();
        m.mtest();

        System.out.println( m.mtest().concat(" test").toUpperCase());

       java.util.Scanner scanner;
       io.kr.carami.Scanner caramiScanner = new io.kr.carami.Scanner();
    }
}
