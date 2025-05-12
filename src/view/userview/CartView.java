package view.userview;

import controller.CartController;
import controller.CheckOut;
import util.InputHelper;

public class CartView {
    CartController cartController = new CartController();
    CheckOut checkOut = new CheckOut();
    public void viewCart() {
        while (true) {
            System.out.println("\n--- GIỎ HÀNG ---");
            cartController.viewCart();

            System.out.println("\n1. Thêm sản phẩm vào giỏ");
            System.out.println("2. Xóa sản phẩm khỏi giỏ");
            System.out.println("3. Xem lại giỏ hàng");
            System.out.println("4. Thanh toán");
            System.out.println("5. Quay lại menu chính");

            int choice = InputHelper.getInt("Chọn: ");
            switch (choice) {
                case 1 -> cartController.addProductToCart();
                case 2 -> cartController.removeProductFromCart();
                case 3 -> cartController.viewCart();
                case 4 -> checkOut.checkout();
                case 5 -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
