package day11;

public class Yewei extends Exception{
    public Yewei(){
        super("점수는 0 - 100 사이의 값을 입력해야 합니다." );
    }

    public Yewei(String message){
        super(message);
    }
}
