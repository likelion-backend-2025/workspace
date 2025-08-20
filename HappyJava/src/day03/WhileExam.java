package day03;

public class WhileExam {
    public static void main(String[] args) {
        int i = 0;
        while(i++ < 10){
//            i++;
            System.out.println("안녕"+i);

        }
        i = 1;
        while (true){
            if(i == 11) {
                break;
            }
            System.out.println(i++);
        }
        System.out.println("=======================");
        i = 0;
        while(i++ < 10){
            if(i % 2 != 0)
                continue;
            System.out.println(i);
        }

        System.out.println("=================");
        i = 0;
        while (i++ < 10){
            if(i==5) continue;
            System.out.println(i);
        }
    }
}
