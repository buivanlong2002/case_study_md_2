package view.adminview;

import util.InputHelper;

public class ManageCustomerView {
    public void manageCustomer() {
        while (true) {
            System.out.println("\n========= MENU QUẢN LÝ KHÁCH HÀNG =========");
            System.out.println("1. Hiển thị tất cả khách hàng");
            System.out.println("2. Tìm kiếm khách hàng");
            System.out.println("3. Cập nhật thông tin khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("0. Quay lại");

            int choice = InputHelper.getInt("Chọn: ");
            try {
                switch (choice) {
                    case 1:
                        viewAllCustomers();
                        break;
                    case 2:
                        searchCustomer();
                        break;
                    case 3:
                        updateCustomer();
                        break;
                    case 4:
                        deleteCustomer();
                        break;
                    case 0:
                        return;  // Quay lại
                    default:
                        System.out.println("Sai lựa chọn.");
                }
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }

    private void viewAllCustomers() {

        System.out.println("\nDanh sách khách hàng:");
    }

    private void searchCustomer() {
        String searchTerm = InputHelper.getString("Nhập tên hoặc ID khách hàng để tìm kiếm: ");
        System.out.println("Đang tìm kiếm khách hàng có tên hoặc ID là: " + searchTerm);
    }

    private void updateCustomer() {
        String customerId = InputHelper.getString("Nhập ID khách hàng cần cập nhật: ");
        System.out.println("Đang cập nhật thông tin khách hàng với ID: " + customerId);
    }

    private void deleteCustomer() {
        String customerId = InputHelper.getString("Nhập ID khách hàng cần xóa: ");
        System.out.println("Đang xóa khách hàng với ID: " + customerId);
    }

}
