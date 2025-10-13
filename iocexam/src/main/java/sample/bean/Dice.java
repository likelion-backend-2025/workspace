package sample.bean;

public class Dice {
    private int face;
    public Dice(){
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
