package model;

public enum PaymentStatus {
    COD("Thanh toán khi nhận hàng"),
    ATM("Chuyển khoản ngân hàng");

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
