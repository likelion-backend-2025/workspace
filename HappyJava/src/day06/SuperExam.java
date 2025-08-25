package day06;

class Parent3{
    int i = 10;
    public void print(){
        System.out.println("parent : "+i);
    }
//    public Parent3(){
//        System.out.println("Parent 생성");
//    }
    public Parent3(int i){
        System.out.println("Parent int 생성");
    }
}
class Child3 extends Parent3{
    int i = 20;

    public void childMethod(){
        System.out.println("test");
    }
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
        super(10);   // 만약 부모가 디폴트생성자를 가지지 않는다면
        // 반드시 명시적으로 부모의 다른 생성자를 호출해줘야만함.
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

        System.out.println("++++++++++++++++++++++++++++++++++");
        //필드는 타입을 따른다!!    이런 규칙 기억나시죠?

        Parent3 p = new Child3();
        System.out.println(p.i);


        ((Child3)p).childMethod();
    }
}
