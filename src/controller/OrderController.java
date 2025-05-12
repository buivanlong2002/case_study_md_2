package controller;

import model.Order;
import service.OrderService;

import java.util.List;

public class OrderController {
    private final OrderService orderService;

    public OrderController() {
        this.orderService = new OrderService();
    }

    // Thêm đơn hàng mới
    public void addOrder(Order order) {
        if (order != null) {
            orderService.saveOrder(order);
            System.out.println("Đơn hàng đã được lưu.");
        } else {
            System.out.println("Đơn hàng không hợp lệ.");
        }
    }

    // Lấy tất cả đơn hàng
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Lấy đơn hàng theo username
    public List<Order> getOrdersByUsername(String username) {
        return orderService.getOrdersByUsername(username);
    }

    // Xóa đơn hàng theo id và username
    public boolean removeOrderByIdAndUsername(int orderId, String username) {
        return orderService.removeOrderByIdAndUsername(orderId, username);
    }
}
