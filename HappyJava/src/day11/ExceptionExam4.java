package day11;

public class ExceptionExam4 {
    public static void main(String[] args) {
        System.out.println("프로그램 시작!!!");
//try-catch
        //어디까지 실행되나요???
        //예외가 발생되지 않으면 catch 블럭은 실행되지 않는다.!!
        //예외가 발생되었을때!!
        //반드시 실행되어야 할 문장이 있다면???  - finally
        String str = null;
        try {
//            System.out.println(str.charAt(0));
            int i = Integer.parseInt(args[0]);   //항상 예외가 일어나는 것은 아님.

            System.out.println(10/i);
            //try 블럭에서도 예외가 발생되면,  발생된 지점부터 나머지 try 블럭안의 문장은 실행되지 않음!!



            System.out.println("try 블럭에서 하는 다음 일!!! ");
        }catch (ArithmeticException e){  //catch 블럭이 있다고 해서 모든 예외가 처리되는 것은 아니다!!
            System.out.println("ArithmeticException catch 블럭 실행!!");
            System.out.println(e);
        }catch (NumberFormatException e){
            System.out.println("NumberFormatException catch 블럭 실행!!");
            System.out.println(e);
        }finally {
            //예외가 발생했든, 발생하지 않았든..  예외를 처리했든, 처리하지 못했든 항상 실행함!!
            //연결- 접속 반드시 접속을 종료해주는것이 좋다!!
            System.out.println("finally 실행!!!");
        }

        //예외가 발생 되었다면, 적절히 처리해야지만 실행됨!!
        System.out.println("반드시 실행되어야 함!!!");
        System.out.println("프로그램 종료!!");
    }

}
