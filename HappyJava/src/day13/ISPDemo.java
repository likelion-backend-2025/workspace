package day13;

// ISP 예제
interface OrderOperations {
    void placeOrder(String item);

}

interface CancelOperations {
    void cancelOrder(String orderId);
}

class OnlineOrderService implements OrderOperations, CancelOperations {
    @Override
    public void placeOrder(String item) {
        System.out.println("Order placed: " + item);
    }

    @Override
    public void cancelOrder(String orderId) {
        System.out.println("Order cancelled: " + orderId);
    }
}

// 주문 생성만 필요한 클라이언트
class OrderClient {
    private final OrderOperations orderOps;

    public OrderClient(OrderOperations orderOps) {
        this.orderOps = orderOps;
    }

    public void createNewOrder() {
        orderOps.placeOrder("Book");
    }
}

// 주문 취소만 필요한 클라이언트
class CancelClient {
    private final CancelOperations cancelOps;

    public CancelClient(CancelOperations cancelOps) {
        this.cancelOps = cancelOps;
    }

    public void cancelOrder(String orderId) {
        cancelOps.cancelOrder(orderId);
    }
}

class ISPDemo {
    public static void main(String[] args) {
        OnlineOrderService service = new OnlineOrderService();
        OrderClient client = new OrderClient(service);

        client.createNewOrder();
        service.cancelOrder("ORD1234");
    }
}