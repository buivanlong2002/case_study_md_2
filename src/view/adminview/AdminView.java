package view.adminview;

import util.InputHelper;

public class AdminView {
    private final ManageProduct manageProduct=new ManageProduct();
    private final ManageOrder manageOrder=new ManageOrder();
    private final ManageCustomerView manageCustomerView = new ManageCustomerView();
    private final ManageCategory manageCategory = new ManageCategory();

    public void display() {
        while (true) {
            System.out.println("\n========= MENU ADMIN =========");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý danh mục");
            System.out.println("3. Quản lý đơn hàng");
            System.out.println("4. Quản lý khách hàng");
            System.out.println("5. Thông tin cá nhân");
            System.out.println("0. Đăng xuất");

            int choice = InputHelper.getInt("Chọn: ");
            try {
                switch (choice) {
                    case 1:
                        manageProduct.manageProduct();
                        break;
                    case 2:
                        manageCategory.manageCategory();
                        break;
                    case 3:
                        manageOrder.manageOrder();
                        break;
                    case 4:
                        manageCustomerView.manageCustomer();
                        break; // Gọi phương thức quản lý khách hàng từ ManageCustomerView
                    case 5:
                        showNews();
                        break;
                    case 0:
                        System.out.println("Đăng xuất khỏi ADMIN.");
                        return;
                    default:
                        System.out.println("Sai lựa chọn.");
                }
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }



    // Hiển thị bảng tin
    private void showNews() {
        System.out.println("\n--- Bảng tin ---");
        // Hiển thị các thông báo cho admin
    }

    // Quản lý đánh giá sản phẩm
    private void manageProductReviews() {
        System.out.println("\n--- Đánh giá sản phẩm ---");
        // Quản lý hoặc xem các đánh giá sản phẩm
    }

    // Xem thông tin cá nhân của admin
    private void viewPersonalInfo() {
        System.out.println("\n--- Thông tin cá nhân ---");
        // Hiển thị thông tin cá nhân của admin
    }
}
