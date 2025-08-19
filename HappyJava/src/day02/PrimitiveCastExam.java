package day02;

public class PrimitiveCastExam {
    public static void main(String[] args) {
        //형변환 - 관련있는것들끼리만 변환할 수 있다.
        //기본 - 숫자, boolean

        byte b = 10;
        int i = b;

        //형변환이 일어날때 값의 크기는 중요하지 않다. 그릇(타입)의 크키가 중요.
        b = (byte)i;  //명시적 형변환

        byte b2 = (byte)128;
        System.out.println(b2);
    }
}
