package lambdaexam;

// 매개변수가 없는 함수적 인터페이스
@FunctionalInterface
interface Greeting {
    void sayHello();
}

// 매개변수가 하나인 함수적 인터페이스
@FunctionalInterface
interface StringProcessor {
    String process(String str);
}

// 매개변수가 두 개인 함수적 인터페이스
@FunctionalInterface
interface Calculator {
    double calculate(double a, double b);
}

public class LambdaExamples {
    public static void main(String[] args) {
        // 매개변수 없는 람다식
        Greeting greeting = () -> System.out.println("안녕하세요!");
        greeting.sayHello();

        // 매개변수 하나인 람다식 (괄호 생략 가능)
        StringProcessor upperCase = str -> str.toUpperCase();
        System.out.println(upperCase.process("hello")); // HELLO

        // 매개변수 두 개인 람다식
        Calculator multiply = (a, b) -> a * b;
        System.out.println(multiply.calculate(5.0, 3.0)); // 15.0

        // 복잡한 로직을 가진 람다식
        Calculator complexCalc = (a, b) -> {
            double result = a + b;
            System.out.println("계산 중: " + a + " + " + b);
            return result;
        };
        System.out.println(complexCalc.calculate(10.0, 20.0)); // 30.0
    }
}