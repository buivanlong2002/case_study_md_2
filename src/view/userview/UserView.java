package view.userview;

import controller.BankCardController;
import util.InputHelper;

public class UserView {
   BankCardController bankCardController = new BankCardController();

    public void viewPersonalInfo() {
        while (true) {
            System.out.println("\n--- THÔNG TIN CÁ NHÂN ---");
            System.out.println("1. Thông tin tài khoản");
            System.out.println("2. Tài khoản ngân hàng đã liên kết");
            System.out.println("3. Quay lại");

            int choice = InputHelper.getInt("Chọn: ");
            switch (choice) {
                case 1 -> viewAccountInfo();
                case 2 -> viewBankMenu();
                case 3 -> { return; }
                default -> System.out.println("⚠ Lựa chọn không hợp lệ.");
            }
        }
    }

    private void viewAccountInfo() {
        System.out.println(">> Hiển thị thông tin tài khoản ở đây (chưa tích hợp Account cụ thể).");
        // Nếu bạn có Account currentUser, thì có thể in như sau:
        // System.out.println("Tên đăng nhập: " + currentUser.getUsername());
        // System.out.println("Email: " + currentUser.getEmail());
        // ...
    }

    private void viewBankMenu() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ THẺ NGÂN HÀNG ---");
            System.out.println("1. Xem danh sách thẻ");
            System.out.println("2. Thêm thẻ");
            System.out.println("3. Xóa thẻ");
            System.out.println("4. Quay lại");

            int choice = InputHelper.getInt("Chọn: ");
            switch (choice) {
                case 1 -> bankCardController.showCard();
                case 2 -> bankCardController.addCard();
                case 3 -> bankCardController.removeCard();
                case 4 -> { return; }
                default -> System.out.println("⚠ Lựa chọn không hợp lệ.");
            }
        }
    }
}
