package annotation;

public class PrintService {
    @PrintAnnotation
    public void methodA(){
        System.out.println("PrintService methodA");
    }

    @PrintAnnotation("&")
    public void methodB(){
        System.out.println("PrintService methodB");
    }

    @PrintAnnotation(number = 10)
    public void methodC(){
        System.out.println("PrintService methodC");
    }

    @PrintAnnotation(value = "#", number = 10)
    public void methodD(){
        System.out.println("PrintService methodD");
    }
}
