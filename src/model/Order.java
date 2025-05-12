package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int nextId = 1;

    private final int id;
    private final String username; // Thêm username
    private final List<CartItem> items;
    private final double totalAmount;
    private final LocalDateTime createdAt;

    private final String address;
    private final String phone;
    private final PaymentStatus paymentStatus;
    private final OrderStatus orderStatus;
    private final String notes;

    public Order(String username, List<CartItem> items, double totalAmount, String address, String phone,
                 PaymentStatus paymentStatus, OrderStatus orderStatus, String notes) {
        this.id = nextId++;
        this.username = username;
        this.items = items;
        this.totalAmount = totalAmount;
        this.createdAt = LocalDateTime.now();
        this.address = address;
        this.phone = phone;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===========================================\n");
        sb.append(String.format("🧾 HÓA ĐƠN #%d\t\tNgày: %s\n", id, createdAt));
        sb.append("-------------------------------------------\n");
        sb.append("👤 Người đặt     : ").append(username).append("\n");
        sb.append("📍 Địa chỉ       : ").append(address).append("\n");
        sb.append("📞 Số điện thoại : ").append(phone).append("\n");
        sb.append("💬 Ghi chú       : ").append(notes).append("\n");
        sb.append("-------------------------------------------\n");
        sb.append("📦 Danh sách sản phẩm:\n");

        double totalAmountAfterTax = 0; // Tổng tiền sau thuế cho tất cả sản phẩm

        for (CartItem item : items) {
            double price = item.getProduct().getPrice();
            double tax = item.getProduct().calculateTax();
            double priceWithTax = price + tax;
            double totalWithTax = priceWithTax * item.getQuantity();

            sb.append(String.format("- %s x%d | Giá: %.2f đ | Thuế: %.2f đ | Thành tiền (sau thuế): %.2f đ\n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    price,
                    tax,
                    totalWithTax));

            totalAmountAfterTax += totalWithTax; // Cộng dồn tổng tiền sau thuế
        }

        sb.append("-------------------------------------------\n");
        sb.append(String.format("💰 Tổng tiền (sau thuế) : %.2f đ\n", totalAmountAfterTax)); // Hiển thị tổng tiền sau thuế
        sb.append("💳 Thanh toán    : ").append(paymentStatus).append("\n");
        sb.append("🚚 Trạng thái    : ").append(orderStatus).append("\n");
        sb.append("===========================================\n");

        return sb.toString();
    }


}
