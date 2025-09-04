package day12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadFile2 {
    public static void main(String[] args) {
        //파일에서 읽어서
        //파일에 쓰고 싶다.

        //스트림은 단방향으로 데이터가 흐른다.
        FileInputStream fis =  null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("src/day12/IOEx22am1.java");
            fos = new FileOutputStream("ioexam.txt");
            int n = 0;
            int count = 0;
//        while ((n = fis.read()) != -1) { //파일의 끝이 아닐때 까지 계속 읽어주세요.
////            System.out.print((char)n);  //콘솔에 출력해줘.
//            count++;
//            fos.write(n);
//        }
            byte[] bytes = new byte[1024];

            while ((n = fis.read(bytes)) != -1) {
                String str = new String(bytes, 0, n);
                System.out.println(str);
                count++;
                fos.write(bytes, 0, n);
            }

            System.out.println(count);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }


        //실제 파일에 쓰는일은 누가 할까요?

    }
}
