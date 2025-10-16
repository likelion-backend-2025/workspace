package annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PrintServiceRun {
    public static void main(String[] args) {
        PrintService service = new PrintService();
        Method[] declaredMethods = service.getClass().getDeclaredMethods();

        for (Method method : declaredMethods) {
            if(method.isAnnotationPresent(PrintAnnotation.class)) {
                //해당 메서드에 PrintAnnotation 애노테이션이 붙어있다면 이렇게 실행해줘..
                System.out.println("============"+method.getName()+"===================");
                PrintAnnotation annotation = method.getAnnotation(PrintAnnotation.class);

                for(int i = 0; i < annotation.number(); i++) {
                    System.out.print(annotation.value());
                }

                System.out.println();
            }

            try {
                //사용자가 구현한 메서드 구현부!!!
                method.invoke(service);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
