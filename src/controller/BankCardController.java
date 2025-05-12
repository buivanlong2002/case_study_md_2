package controller;

import model.BankCard;
import service.BankCardService;
import util.InputHelper;
import util.Session;

public class BankCardController {
    private final BankCardService bankCardService = new BankCardService();

    // Hiển thị thẻ ngân hàng của người dùng hiện tại
    public void showCard() {
        String username = Session.getCurrentUsername();  // Lấy username từ session
        if (username == null) {
            System.out.println("Lỗi: Người dùng chưa đăng nhập.");
            return;
        }
        bankCardService.showCards(username);  // Chỉ hiển thị thẻ của user hiện tại
    }

    // Thêm thẻ ngân hàng cho người dùng hiện tại
    public void addCard() {
        String username = Session.getCurrentUsername();  // Lấy username từ session

        // Kiểm tra nếu username null, cần xử lý
        if (username == null) {
            System.out.println("Lỗi: Người dùng chưa đăng nhập.");
            return;
        }

        String cardNum = InputHelper.getString("Nhập số thẻ: ");
        String name = InputHelper.getString("Tên chủ thẻ: ");
        String expiry = InputHelper.getString("Ngày hết hạn (MM/YY): ");
        String bank = InputHelper.getString("Tên ngân hàng: ");
        double balance = InputHelper.getDouble("Giả sử số dư trong thẻ: ");

        // Thêm thẻ với username đã được gán từ Session
        BankCard card = new BankCard(cardNum, name, expiry, bank, username,balance);
        bankCardService.addCard(card);
        System.out.println("Đã thêm thẻ ngân hàng.");
    }

    // Xóa thẻ ngân hàng của người dùng hiện tại
    public void removeCard() {
        String username = Session.getCurrentUsername();  // Lấy username từ session
        if (username == null) {
            System.out.println("Lỗi: Người dùng chưa đăng nhập.");
            return;
        }

        bankCardService.showCards(username);  // Hiển thị danh sách thẻ của user hiện tại

        int index = InputHelper.getInt("Nhập vị trí thẻ cần xóa: ") - 1;

        // Kiểm tra lại nếu thẻ thuộc về người dùng hiện tại
        if (index >= 0 && index < bankCardService.getCardList().size()) {
            BankCard cardToRemove = bankCardService.getCardList().get(index);
            if (cardToRemove.getUsername().equals(username)) {
                bankCardService.removeCard(username ,index);  // Xóa thẻ nếu đúng username
            } else {
                System.out.println("Bạn không thể xóa thẻ của người dùng khác.");
            }
        } else {
            System.out.println("Vị trí không hợp lệ.");
        }
    }
}
