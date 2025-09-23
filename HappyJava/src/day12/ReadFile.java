package day12;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ReadFile {
    public static void main(String[] args) throws Exception{
        //파일에서 읽어서
        //파일에 쓰고 싶다.

        //스트림은 단방향으로 데이터가 흐른다.
        FileInputStream fis = new FileInputStream("src/day12/IOExam1.java");
        FileOutputStream fos = new FileOutputStream("ioexam.txt");

//        System.out.println(fis.read()); //한번 읽었으면 끝!
//
//        System.out.println((char)fis.read());

        //fis.read() 가 반복되어야겠다.  여기까지 괜찮쵸?
        //fis.read() 했을때 파일의 끝이면 -1 을 리턴.  EOF
        int n = 0;
        int count = 0;
//        while ((n = fis.read()) != -1) { //파일의 끝이 아닐때 까지 계속 읽어주세요.
////            System.out.print((char)n);  //콘솔에 출력해줘.
//            count++;
//            fos.write(n);
//        }
//

        byte[] bytes = new byte[1024];

        while ((n = fis.read(bytes)) != -1) {
            String str = new String(bytes, 0, n);
            System.out.println(str);
            count++;
            fos.write(bytes, 0, n);
        }

        System.out.println(count);



        //실제 파일에 쓰는일은 누가 할까요?

        fos.close();
        fis.close();

    }
}
