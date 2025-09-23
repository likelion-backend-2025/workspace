package day12;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class DataOutExam {
    public static void main(String[] args) {
        try(
                //원시데이터타입으로 파일에 쓰는 기능을 제공함.
                DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/day12/data.txt"));
                ){

            dos.writeBoolean(true);
            dos.writeChar('a');
            dos.writeInt(10);
            dos.writeDouble(Double.MAX_VALUE);


        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
