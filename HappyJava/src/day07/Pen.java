package day07;

/*final*/  class Pen {
    public void 쓰다(){
        System.out.println("쓰다");
    }
    //오버라이딩은 상속에서만 가능

    public static void main(String[] args) {
        BallPen pen = new BallPen();
        pen.쓰다();
        pen.쓰다("msg");
    }

}
class BallPen extends Pen{
    public void 쓰다(String msg){
        System.out.println("쓰다");
    }
}

