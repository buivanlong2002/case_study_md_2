package controller;

import model.BankCard;
import model.Order;
import model.OrderStatus;
import model.PaymentStatus;
import service.BankCardService;
import service.CartService;
import util.InputHelper;
import util.Session;
import java.util.ArrayList;
import java.util.List;

public class CheckOut {
    private final CartController cartController = new CartController();
    private final CartService cartService = CartService.getInstance();  // Sử dụng Singleton
    private final OrderController orderController = new OrderController();
    private final BankCardService bankCardService = new BankCardService();

    public void checkout() {
        System.out.println("\n--- Thanh toán ---");

        // Kiểm tra giỏ hàng có trống không
        if (cartService.getCart().getCartItems().isEmpty()) {
            System.out.println("Giỏ hàng trống. Không thể thanh toán.");
            return;
        }

        // Hiển thị giỏ hàng và tổng tiền cần thanh toán
        System.out.println(cartService.getCart());
        double totalAmount = cartService.getTotal();
        System.out.printf("Tổng tiền cần thanh toán: %.2f\n", totalAmount);

        // Xác nhận thanh toán
        String confirm = InputHelper.getString("Bạn có chắc chắn muốn thanh toán? (y/n): ");
        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("Thanh toán đã bị hủy.");
            return;
        }

        String address = InputHelper.getString("Nhập địa chỉ giao hàng: ");
        String phone = String.valueOf(InputHelper.getInt("Nhập số điện thoại: "));
        String notes = InputHelper.getString("Ghi chú (nếu có): ");

        String choice = InputHelper.getString("Phương thức thanh toán (COD/ATM): ");
        PaymentStatus paymentStatus;

        if ("COD".equalsIgnoreCase(choice)) {
            paymentStatus = PaymentStatus.COD;
        } else if ("ATM".equalsIgnoreCase(choice)) {
            paymentStatus = PaymentStatus.ATM;

            String username = Session.getCurrentUsername();
            if (username == null) {
                System.out.println("Người dùng chưa đăng nhập.");
                return;
            }

            List<BankCard> userCards = bankCardService.getCardsByUsername(username);
            if (userCards.isEmpty()) {
                System.out.println(" Bạn chưa liên kết thẻ ngân hàng. Vui lòng liên kết thẻ trước khi thanh toán.");
                return;
            }

            // Hiển thị danh sách thẻ
            System.out.println("Danh sách thẻ ngân hàng của bạn:");
            for (int i = 0; i < userCards.size(); i++) {
                System.out.println((i + 1) + ". " + userCards.get(i));
            }

            int cardChoice = InputHelper.getInt("Chọn thẻ thanh toán (Nhập số thứ tự): ");
            if (cardChoice < 1 || cardChoice > userCards.size()) {
                System.out.println("Lựa chọn không hợp lệ.");
                return;
            }

            BankCard selectedCard = userCards.get(cardChoice - 1);
            boolean success = selectedCard.processTransaction(totalAmount);
            if (!success) {
                System.out.println("Thanh toán không thành công.");
                return;
            }
        } else {
            System.out.println("Phương thức thanh toán không hợp lệ.");
            return;
        }

        String username = Session.getCurrentUsername(); // lấy username hiện tại
        if (username == null) {
            System.out.println("Người dùng chưa đăng nhập.");
            return;
        }

// Tạo đơn hàng
        Order order = new Order(
                username,
                new ArrayList<>(cartService.getCart().getCartItems()),
                totalAmount,
                address,
                phone,
                paymentStatus,
                OrderStatus.PROCESSING,
                notes
        );

        orderController.addOrder(order);

        cartController.clearCart();

        System.out.println("Thanh toán thành công. Hóa đơn của bạn:");
        System.out.println(order);
    }
}
