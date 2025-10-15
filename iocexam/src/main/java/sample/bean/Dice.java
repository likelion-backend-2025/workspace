package sample.bean;

import org.springframework.stereotype.Component;

//@Component
public class Dice {
    private int face;
    public Dice(){
//        this.face = 6;
        System.out.println("Dice() 실행!!");
    }
    public Dice(int face){
        this.face = face;
        System.out.println("Dice(int) 실행!!");
    }

    public void setFace(int face){
        this.face = face;
    }
    public int getNumber(){
        return (int)(Math.random()*face)+1;
    }
}
