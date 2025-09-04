package day12;

import java.io.File;
import java.io.IOException;

public class FileInfo {
    public static void main(String[] args) {

        File f = new File("../happyjava/src/./day12/IOExam1.java");
        if(f.exists()) { // 파일이 존재할 경우
            System.out.println("length : " + f.length());
            System.out.println("canRead : " + f.canRead());
            System.out.println("canWrite : " + f.canWrite());
            System.out.println("getAbsolutePath : " + f.getAbsolutePath());
            try {
                System.out.println("getCanonicalPath : " + f.getCanonicalPath());
            } catch(IOException e) {
                System.out.println(e);
            }
            System.out.println("getName : " + f.getName());
            System.out.println("getParent : " + f.getParent());
            System.out.println("getPath : " + f.getPath());
        } else { // 파일이 존재하지 않을 경우
            System.out.println("파일이 존재하지 않습니다.");
        }
    }
}
