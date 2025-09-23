package day11;

public class ExceptionExam2 {
    public static void main(String[] args) {
        //예외!!
        //에러와 예외는 차이가 있을까요?
        //두부 좀 사와!!
        // a 라는 슈퍼에 오늘 두부가 판매종료!! -- 예외발생!!
        //전진 - 다른슈퍼를 간다. --내가 직접 예외처리함
        //보광 - 그냥 안산다.  (엄마한테도 말 안해???) -- 예외처리 안함.
        //민혁 - 엄마한테 전화해서 다 떨어졌대요.  -- 엄마한테 예외를 던짐

        //예외 - 프로그램이 동작하던 중..  예기치 못한 일이 발생.

        int[] iarr = {1,2,3,4};

        //프로그래머는 경험에 의해서 예외가 발생 될것 같은 부분을 알아요.
        //미리 예외 처리를 해요.
        //일상에서에 우리는 어떤가요???  -예외처리 하시나요? 안하시나요?
        int value = 0;
        try {
         //예외가 발생 될 것 같은 부분에 try{}
            value = iarr[4];
            System.out.println(iarr[4]); //여기서 예외가 발생!!!

        } catch (Exception e) {
            //예외 처리 로직을 가지는 블럭!!
            //catch블럭을 비워두는거 --  가장 나쁜 예외처리!!
            System.out.println(e);
            //최소한..  예외를 출력이라도 해줘야함.
        }
        System.out.println(value);
        System.out.println("또 해야하는 다음 일을 한다.1");
        System.out.println("또 해야하는 다음 일을 한다.2");
        System.out.println("또 해야하는 다음 일을 한다.3");
        System.out.println("종료!!");

        //예외가 발생했고, 처리하지 않으면..  프로그램이 종료되어버림!!
        //끝나면 안되기때문에 필요한 것이...  예외 처리다.. 
    }
}
