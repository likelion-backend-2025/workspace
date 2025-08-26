package day07;

public class Cat extends Animal{

//    private String name;
//
//    public void 먹다(){
//        System.out.println("먹는다.");
//    }
//    public void 걷는다(){
//        System.out.println("걷다.");
//    }
//
//    public void 소리를내다(){
//        System.out.println("야옹");
//    }

    @Override
    public void makeSound() {
        System.out.println("야옹");
    }
}
