package controller;

import model.BankCard;
import service.BankCardService;
import util.InputHelper;
import util.Session;

import java.util.List;

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

        // Hiển thị danh sách thẻ của user hiện tại
        List<BankCard> userCards = bankCardService.getCardsByUsername(username);
        if (userCards.isEmpty()) {
            System.out.println(">> Bạn chưa có thẻ ngân hàng nào.");
            return;
        }

        // In danh sách thẻ
        for (int i = 0; i < userCards.size(); i++) {
            System.out.println((i + 1) + ". " + userCards.get(i));
        }

        int index = InputHelper.getInt("Nhập vị trí thẻ cần xóa: ") - 1;

        if (index >= 0 && index < userCards.size()) {
            BankCard cardToRemove = userCards.get(index);
            // Xóa khỏi danh sách tổng nếu tìm thấy
            bankCardService.delete(cardToRemove);
            System.out.println(">> Xóa thẻ thành công.");
        } else {
            System.out.println("Vị trí không hợp lệ.");
        }
    }

}
