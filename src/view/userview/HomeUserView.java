package view.userview;

import util.InputHelper;
import util.Session;

public class HomeUserView {
    ProductView productView=new ProductView();
    CartView cartView=new CartView();
    OrderView orderView=new OrderView();
    UserView userView=new UserView();

    public void display() {
        while (true) {
            System.out.println("\n========= MENU KHÁCH HÀNG =========");
            System.out.println("1. Hiển thị tất cả sản phẩm");
            System.out.println("2. Giỏ hàng");
            System.out.println("3. Tìm kiếm sản phẩm theo tên");
            System.out.println("4. Lịch sử đơn hàng");
            System.out.println("5. Thông tin cá nhân");
            System.out.println("0. Đăng xuất");

            int choice = InputHelper.getInt("Chọn: ");
            try {
                switch (choice) {
                    case 1 -> productView.viewAllProducts();
                    case 2 -> cartView.viewCart();
                    case 3 -> productView.searchProducts();
                    case 4 -> orderView.viewOrderHistory();
                    case 5 -> userView.viewPersonalInfo();
                    case 0 -> {
                        Session.clear();
                        System.out.println("Đăng xuất khỏi KHÁCH HÀNG.");
                        return;
                    }
                    default -> System.out.println("Sai lựa chọn.");
                }
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }

}
