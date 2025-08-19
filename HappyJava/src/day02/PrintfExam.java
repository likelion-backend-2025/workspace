package day02;

public class PrintfExam {
    public static void main(String[] args) {
        String name = "kang";
        int age = 20;
        System.out.println("나의 이름은 "+name+"이고, 나이는 "+age+"입니다.");
        System.out.printf("나의 이름은 %s이고, 나이는 %d입니다.\n",name,age);


        System.out.printf("이름 : %s\n", name);


        boolean flag = true;

        System.out.printf("강경미님의 test 결과는 %b 입니다.",flag);
    }
}
