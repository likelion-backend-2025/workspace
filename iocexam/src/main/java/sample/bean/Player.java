package sample.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Player {
    private String name;
    private Dice dice; // 실행될 때 주사위를 주입받을것!!  (의존성 주입: DI)
    //--필드를통한 주입

    //DI 컨테이너를 통해 주입 받는 방법
    //1. 생성자를 통한 주입
    //2. 설정자(setter 메서드)를 통해서 주입
    //3. 필드에 직접 주입 -- 권장하지 않음!!  애노테이션을이용!! == 나중에 살펴볼께요.

//    우리가 할일이 무엇일까요???
    //생성자를 통해서 주입을 받는다라고 하면 우리는 무엇을 해야할까요???
    public Player(){
        System.out.println("player() 생성");
    }

    public Player(Dice dice){
        this.dice = dice;
        System.out.println("player(dice) 생성자 생성");
    }


    //설정자를 통해서 주입해줄께요!!  라고 한다면 뭘 마련해야할까요?


    public void setDice(Dice dice) {
        this.dice = dice;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void play(){
        System.out.println(name + "님은 주사위를 던져서 "+dice.getNumber()+" 가 나왔습니다.");
    }
}
