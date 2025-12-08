package patterns.factory.ex3;

// Product
interface Notification {
    void send(String message);
}

// ConcreteProduct 1
class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("이메일 발송: " + message);
    }
}
// ConcreteProduct 2
class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("SMS 발송: " + message);
    }
}
// ConcreteProduct 3
class PushNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("🔔 [앱 푸시] 전송: " + message);
    }
}

// ConcreteProduct 4
class VIPNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("vip 처리: "+message);
    }
}

class VipNotificationService extends FlexibleNotificationService {

    @Override
    protected Notification createNotification(String type) {
        // 1. VIP 전용 로직 처리
        if ("VIP".equals(type)) {
            return new VIPNotification();
        }

        // 2. 나머지는 부모의 로직(기존 switch문)을 그대로 사용
        return super.createNotification(type);
    }
}

// Creator (추상 클래스)
abstract class NotificationService {
    protected abstract Notification createNotification(String type);

    public void notifyUser(String type, String message) {
        Notification notification = createNotification(type);
        notification.send(message);
    }
}


class FlexibleNotificationService extends NotificationService {
    @Override
    protected Notification createNotification(String type) {
        switch (type) {
            case "EMAIL":
                return new EmailNotification();
            case "SMS":
                return new SMSNotification();
            case "PUSH":
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}


public class NotificationApp {
    public static void main(String[] args) {
//        NotificationService service = new FlexibleNotificationService();
        VipNotificationService service = new VipNotificationService();

        service.notifyUser("EMAIL", "가입환영인사!!");
        service.notifyUser("SMS","인증번호[1234]");
        service.notifyUser("PUSH", "배송이 시작됩니다.");

        service.notifyUser("VIP","VIP 인사");
    }
}
