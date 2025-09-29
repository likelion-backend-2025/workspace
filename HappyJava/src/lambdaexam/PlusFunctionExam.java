package lambdaexam;

public class PlusFunctionExam {
    public static void main(String[] args) {
        PlusFunction plus =(int i, int j)->{
            return i+j;
        };

        PlusFunction plus2 = (i,j)->{   //타입을 생략해도 타입을 추론해서 실행해줌.
            return i+j;
        };

        PlusFunction plus3 = (i,j)-> i+j;

        //plus, plus2, plus3은 똑같다!!
    }
}
