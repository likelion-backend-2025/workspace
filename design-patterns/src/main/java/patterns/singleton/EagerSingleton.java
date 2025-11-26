package patterns.singleton;

public class EagerSingleton {
    // 클래스 로딩 시점에 인스턴스 생성
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    // private 생성자로 외부 생성 차단
    private EagerSingleton() {
        System.out.println("EagerSingleton 인스턴스 생성");
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;  // 항상 같은 인스턴스 반환
    }

    public void showMessage() {
        System.out.println("Eager Singleton 작동 중");
    }
}