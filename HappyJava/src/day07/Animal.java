package day07;
//나 추상클래스야!!
public abstract  class Animal {
    private String name;

    public /*final*/  void 먹다(){
        System.out.println("먹는다.");
    }
    public void 걷는다(){
        System.out.println("걷다.");
    }
    //이 클래스를 상속받아 사용할 클래스들에게 강제성을 부여함.
    //해당 기능을 일관성있게 구현하도록 제시함.
    public abstract  void makeSound();
}
