package day06;
class Parent1{
    int i = 10;
    public int getI(){
        return i;
    }
}
class Child1 extends Parent1{
    //부모가 가진것을 자식이 똑같이 다시 정의한 것!! (오버라이딩)
    int i = 20;
    int x = 40;
    public int getI(){
        return i;
    }

    public void print(){
        System.out.println(i);
    }
}
public class Exam02 {
    public static void main(String[] args) {
        //1. 부모(조상)는 자식(자손)을 가리킬 수 있다.
        Parent1 p = new Child1();
//    Child1 c = new Parent1(); // 오류 발생..  이유는?
        Object obj = new Parent1();
        Object obj2 = new Child1();

//    새 b = new 참새();
//    새 b2 = new 비둘기();
//
//    비둘기 b3 = new 새();
        //2. 필드는 타입을 따른다.
        Parent1 pp = new Parent1();
        System.out.println(pp.i);
        Child1 cc = new Child1();
        System.out.println(cc.i);
        Parent1 pc = new Child1();
        System.out.println(pc.i);

        //3. 메소드는 오버라이드되면 무조건!!! 자식의 것이 사용됨 **
        System.out.println(pc.getI()); //왜 20이 출력될까요??


        //자바 상속 - 단일상속
    }


}
