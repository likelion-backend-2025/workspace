package patterns.singleton;

public class EagerSingletonMain {
    public static void main(String[] args) {
        EagerSingleton s1 = EagerSingleton.getInstance();
        EagerSingleton s2 = EagerSingleton.getInstance();


        if (s1 == s2) {
            System.out.println("같은 인스턴스??");
        }else {
            System.out.println("다른 인스턴스 ");
        }

        s1.showMessage();
    }
}
