package day11;

public class Exception1 {
    public static void main(String[] args) {
        ExceptionObj1 exobj = new ExceptionObj1();
        int value = exobj.divide(10, 0);
        System.out.println(value);



        int value2 = exobj.divide2(10, 0);
        System.out.println(value);
    }
}

class ExceptionObj1 {
    public int divide(int i, int k) {
        int value = 0;
        try {
            value = i / k; // 0으로 나누기 - ArithmeticException 발생!
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
            value = i / 1;
        }

        return value;
    }


    public int divide2(int i, int k) throws Exception {
        int value = 0;
        value = i / k; // 0으로 나누기 - ArithmeticException 발생!
        return value;
    }
}