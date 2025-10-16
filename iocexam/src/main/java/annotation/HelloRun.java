package annotation;

import java.lang.reflect.Method;

//프레임워크가 구현해 놨을 거예요.
public class HelloRun {
    public static void main(String[] args) throws NoSuchMethodException {
        Hello hello = new Hello();

        Method method = hello.getClass().getDeclaredMethod("print");

        if(method.isAnnotationPresent(Count100.class)){
            for(int i = 0; i < 100; i++){
                hello.print();
            }
        }

        hello.print();
    }
}
