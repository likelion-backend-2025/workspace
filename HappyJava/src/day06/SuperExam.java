package day06;

class Parent3{
    int i = 10;
    public void print(){
        System.out.println("parent : "+i);
    }
    public Parent3(){
        System.out.println("Parent 생성");
    }
    public Parent3(int i){
        System.out.println("Parent int 생성");
    }
}
class Child3 extends Parent3{
    int i = 20;

    public void print(){
        super.print();
        System.out.println("Child : "+i);
    }
    public int getI(){
        return super.i;
//        return i;
    }
//    super - 부모의 인스턴스를 가리킴.
    public Child3(){
//        super();   생략가능
        System.out.println("Child 생성");
    }
    public Child3(int i ){
        super(i);  //명시적으로 호출 해야함
        System.out.println("Child int 생성");
    }
}

public class SuperExam {
    public static void main(String[] args) {
        Child3 c = new Child3(10);

        System.out.println(c.i);
        System.out.println(c.getI());
        c.print();
    }
}
