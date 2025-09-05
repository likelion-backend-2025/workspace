package day13;

// OCP 예제
interface PaymentMethod {
    void pay(int amount);
}

class CreditCard implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.println("Processing credit card payment: $" + amount);
        // 신용카드 결제 로직
    }
}

class PayPal implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.println("Processing PayPal payment: $" + amount);
        // PayPal 결제 로직
    }
}

// 새로운 결제 방식 추가 시에도 아래 클래스는 수정 불필요
class PaymentProcessor {
    public void process(PaymentMethod paymentMethod, int amount) {
        paymentMethod.pay(amount);
    }
}

class KakaoPay implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.println("Processing kakaoPay payment: $" + amount);
    }
}

class OCPDemo {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        processor.process(new CreditCard(), 100);
        processor.process(new PayPal(), 200);

        // 새로운 결제 방식(예: KakaoPay)을 추가해도
        // PaymentProcessor 코드는 수정 불필요

        processor.process(new KakaoPay(), 3000);
    }
}