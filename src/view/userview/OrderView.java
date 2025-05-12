package view.userview;

import controller.OrderController;
import model.Order;
import util.InputHelper;
import util.Session;

import java.util.List;
import java.util.stream.Collectors;

public class OrderView {
    private final OrderController orderController = new OrderController();

    public void viewOrderHistory() {
        while (true) {
            System.out.println("\n--- LỊCH SỬ ĐƠN HÀNG ---");

            String username = Session.getCurrentUsername();
            if (username == null) {
                System.out.println("Bạn chưa đăng nhập.");
                return;
            }

            List<Order> userOrders = orderController.getAllOrders().stream()
                    .filter(order -> username.equals(order.getUsername()))
                    .collect(Collectors.toList());

            if (userOrders.isEmpty()) {
                System.out.println("Bạn chưa có đơn hàng nào.");
            } else {
                for (Order order : userOrders) {
                    System.out.println(order);
                    System.out.println("--------------------------");
                }
            }

            System.out.println("1. Hủy đơn hàng theo ID");
            System.out.println("0. Quay lại");

            int choice = InputHelper.getInt("Chọn: ");
            switch (choice) {
                case 1 -> {return;}
                case 0 -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

}
