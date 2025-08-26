package day07;

public class ABClass implements AInter,BInter{
//   구현부가 중요하기 때문에..
    @Override
    public void aMethod() {
        System.out.println("test1");
    }

    @Override
    public void bMethod() {
        System.out.println("test2");
    }

    public static void main(String[] args) {
        ABClass abClass = new ABClass();
        abClass.aMethod();
        abClass.bMethod();
    }
}
