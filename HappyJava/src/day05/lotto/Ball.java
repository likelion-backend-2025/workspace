package day05.lotto;

public class Ball {
    //어떤 값을 가지면 좋을까??

    //어떤 기능이 필요할까??
    private int number;

//    public Ball(){}
    //볼이 처음 만들어 질때부터 번호가 중요하다고  생각.
    public Ball(int number){
        this.number = number;
    }

    public int getNumber(){
        return number;
    }

}
