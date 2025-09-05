package day13;

// DIP 예제
interface MessageService {
    void sendMessage(String msg);
}

class EmailService implements MessageService {
    @Override
    public void sendMessage(String msg) {
        System.out.println("Email: " + msg);
    }
}

class SmsService implements MessageService {
    @Override
    public void sendMessage(String msg) {
        System.out.println("SMS: " + msg);
    }
}

class MessageSender {
    private final MessageService messageService;

    public MessageSender(MessageService messageService) {
        this.messageService = messageService;
    }

    public void send(String msg) {
        messageService.sendMessage(msg);
    }

    //또 다른 형태의 서비스가 추가된다면???    여기 코드가 계속 추가되어야 할거다.
//    private EmailService emailService = new EmailService();
//    private SmsService smsService = new SmsService();
//
//    public void send(String msg) {
//        emailService.sendMessage(msg);
//        smsService.sendMessage(msg);
//    }
}

class DIPDemo {
    public static void main(String[] args) {
        MessageSender emailSender = new MessageSender(new EmailService());
        emailSender.send("Hello via Email!");



        MessageSender smsSender = new MessageSender(new SmsService());
        smsSender.send("Hello via SMS!");
    }
}