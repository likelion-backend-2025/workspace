package patterns.singleton;

public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton() {
        System.out.println("StaticInnerClass 인스턴스 생성");
    }

    // 정적 내부 클래스 - getInstance() 호출 시 로딩됨
    private static class Holder {
        private static final StaticInnerClassSingleton INSTANCE
                = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return Holder.INSTANCE;
    }

    public void showMessage() {
        System.out.println("Static Inner Class Singleton 작동 중");
    }
}