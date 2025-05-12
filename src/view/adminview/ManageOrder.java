package view.adminview;

import controller.OrderController;
import util.InputHelper;

public class ManageOrder {
    OrderController orderController = new OrderController();
    // Quản lý đơn hàng
    public void manageOrder() {
        while (true) {
            System.out.println("\n========= MENU QUẢN LÝ ĐƠN HÀNG =========");
            System.out.println("1. Hiển thị tất cả đơn hàng");
            System.out.println("2. Cập nhật trạng thái đơn hàng");
            System.out.println("3. Xóa đơn hàng");
            System.out.println("0. Quay lại");

            int choice = InputHelper.getInt("Chọn: ");
            try {
                switch (choice) {
                    case 1:
                        orderController.getAllOrders();
                        break;
                    case 2:
//                        orderController.updateOrderStatus();
                        break;
                    case 3:
//                        orderController.deleteOrderById();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Sai lựa chọn.");
                }
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
}
