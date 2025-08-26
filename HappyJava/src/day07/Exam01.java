package day07;
class Parent {
    Parent(){
        System.out.println("Parent 생성자 실행");
    }
    int i = 5;

    public int getI() {
        return i;
    }
}
class Child extends Parent {
    Child(){
        System.out.println("Child 생성자 실행!!");
    }
    int i = 10;   //i가 오버라이딩 된 상태.

    public int getI(){
        return i;
    }

    public void print(){
        System.out.println(i);
    }
}

public class Exam01 {
    public static void main(String[] args) {
        Parent p = new Parent();
        System.out.println(p.i);
        System.out.println(p.getI());

        Child c = new Child();
        System.out.println(c.i);
        System.out.println(c.getI());

        Parent pc = new Child();
        System.out.println(pc.i);
        System.out.println(pc.getI());

        System.out.println("=========================");
        test(p);

        System.out.println("==========================");
        test(c);
    }

    public static void test(Parent p){
        System.out.println(p.i);
        System.out.println(p.getI());
    }
}
