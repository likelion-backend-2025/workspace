package day08;

public class InnerClassTest {
    public static void main(String[] args) {
        OuterClass outer = new OuterClass();

        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.display();

        OuterClass.StaticNestedClass nested = new OuterClass.StaticNestedClass();
        nested.display();

        outer.methodWithLocalClass();

        outer.createAnonymousClass();

    }
}
