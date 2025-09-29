package lambdaexam;


//1. 인터페이스를 구현해서 class로 만듦
class FuncImpl implements FuncTest {
    @Override
    public void run() {
        System.out.println("클래스를 독립적으로 만들어서 사용");
    }
}

public class FuncImplTest {
    public static void main(String[] args) {
        //2.인터페이스를 구현한 익명객체 생성!!
        FuncTest funcTest = new FuncTest() {
            @Override
            public void run() {
                System.out.println("함수적 인터페이스 구현!!@@");
            }
        };

        //3. 람다식을 이용해서 구현!!
        FuncTest funcTest2 = ()->{
            System.out.println("람다식을 이용해서 인터페이스 구현!!");
        };


        FuncImpl funcImpl = new FuncImpl();
        funcImpl.run();  //이 코드가 어렵지는 않으시죠?


        funcTest.run();

        funcTest2.run();

// DoubleInter 인터페이스는 메서드가 2개 정의되어 있으므로, 람다식으로 표현 불가!!!
//        DoubleInter doubleInter = ()->{
//            System.out.println("오류@@@  ");
//        };
    }
}
