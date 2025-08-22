package day05;

public class StaticExam {
    int i;
    static int si;

    public void method(){
        System.out.println(i);
        System.out.println(si);
    }

    public void method2(){
        method();
        smethod();
        smethod2();
    }
    public static void smethod(){
//        static한 메소드에서는 static한 필드만 접근 가능!!
//        System.out.println(i);
        System.out.println(si);
    }
    public static void smethod2(){
        smethod();
//        method();
    }


    public static void main(String[] args) {
        System.out.println(si);
        smethod();

        //인스턴스 필드, 메서드는 객체가 반드시 인스턴스화 된 후에 쓸수있다.
//        System.out.println(i);
//        method();

    }

}
