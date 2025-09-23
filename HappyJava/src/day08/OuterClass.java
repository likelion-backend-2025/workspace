package day08;

public class OuterClass {
    private int outerField = 10;
    private static int staticField = 20;

    // 1. 멤버 내부 클래스 (non-static)
    public class InnerClass {
        public void display() {
            System.out.println("외부 필드: " + outerField);  // 외부 클래스 멤버 접근 가능
        }
    }

    // 2. 정적 중첩 클래스 (static nested class)
    public static class StaticNestedClass {
        public void display() {
//             System.out.println(outerField);  // 오류: non-static 필드 접근 불가
            System.out.println("정적 필드: " + staticField);  // static 필드만 접근 가능
        }
    }

    // 3. 지역 내부 클래스 (local inner class)
    public void methodWithLocalClass() {
        final int localVar = 30;

        class LocalClass {
            public void display() {
                System.out.println("외부 필드: " + outerField);
                System.out.println("지역 변수: " + localVar);  // final 또는 effectively final만 접근 가능
            }
        }

        LocalClass local = new LocalClass();
        local.display();
    }


    // 4. 익명 내부 클래스 (anonymous inner class)
    public void createAnonymousClass() {
        // 인터페이스를 구현하는 익명 클래스
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("익명 클래스 실행");
            }
        };

        // Java 8+ 람다 표현식으로 대체 가능
        Runnable lambdaRunnable = () -> System.out.println("람다 실행");
    }
}

