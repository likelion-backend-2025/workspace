package day12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOExam1 {
    public static void main(String[] args) throws IOException {
//        IO  가 의미하는게 무엇일까요?
        FileInputStream fis = new FileInputStream("src/day12/IOExam1.java");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

//        복잡해보이나요?
//        샤워꼭지 - 생각해볼까요?
    }
}
