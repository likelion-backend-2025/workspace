package day13.back;

import java.util.Scanner;

interface IPayAble {
    public void pay(double amount);
}

class CreditCard implements IPayAble {
    @Override
    public void pay(double amount) {
        System.out.println("카드결제: " + amount);
    }
}

class PayPal implements IPayAble {
    @Override
    public void pay(double amount) {
        System.out.println("PayPal 결제: " + amount);
    }
}

class Cash implements IPayAble {
    @Override
    public void pay(double amount) {
        System.out.println("현금결제: " + amount);
    }
}

class PayAbleFactory {
    static public IPayAble getPayAble(String type) {
        return switch (type) {
            case "카드" -> new CreditCard();
            case "페이팔" -> new PayPal();
            case "현금" -> new Cash();
            default -> null;
        };
    }
}




public class PaymentProcessor {
    public static void processPayment(String type, double amount) throws Exception {
        PayAbleFactory.getPayAble(type).pay(amount);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String type = sc.nextLine();
        double amount = sc.nextDouble();

        try {
            PaymentProcessor.processPayment(type, amount);
        } catch (Exception e) {
            System.out.println("결제 불가");
            System.err.println(e.getMessage());
        }
    }
}
