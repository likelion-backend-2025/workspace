package day12;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DataInExam {
    public static void main(String[] args) {
        try(DataInputStream dis = new DataInputStream(new FileInputStream("src/day12/data.txt"))){
            System.out.println(dis.readBoolean());
            System.out.println(dis.readChar());
            System.out.println(dis.readInt());
            System.out.println(dis.readDouble());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
