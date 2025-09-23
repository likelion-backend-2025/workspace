package day08;

public enum OrderStatus {
    PENDING("P", "대기중"),
    PROCESSING("R", "처리중"),
    SHIPPED("S", "배송중"),
    DELIVERED("D", "배송완료"),
    CANCELLED("C", "취소");

    private final String code;
    private final String description;

    // 생성자
    OrderStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    // getter 메소드
    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // 정적 메소드
    public static OrderStatus fromCode(String code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
