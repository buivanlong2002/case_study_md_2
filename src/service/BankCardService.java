package service;

import model.BankCard;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class BankCardService {
    private List<BankCard> cardList = new ArrayList<>();
    private final String FILE_PATH = "bankcards.dat";

    public BankCardService() {
        loadFromFile();
    }

    // Thêm thẻ ngân hàng cho người dùng hiện tại
    public void addCard(BankCard card) {
        // Kiểm tra nếu thẻ đã tồn tại với cùng username
        for (BankCard existingCard : cardList) {
            if (existingCard.getUsername().equals(card.getUsername()) && existingCard.getCardNumber().equals(card.getCardNumber())) {
                System.out.println("Thẻ này đã được liên kết với tài khoản.");
                return;
            }
        }
        cardList.add(card);
        saveToFile();

    }

    // Hiển thị danh sách thẻ của người dùng hiện tại (theo username)
    public void showCards(String username) {
        boolean found = false;
        for (BankCard card : cardList) {
            if (card.getUsername().equals(username)) {
                System.out.println(card);  // In ra thông tin thẻ
                found = true;
            }
        }
        if (!found) {
            System.out.println("Chưa có thẻ ngân hàng nào được liên kết.");
        }
    }

    // Xóa thẻ ngân hàng theo vị trí trong danh sách
    public void removeCard(String username, int index) {
        if (index >= 0 && index < cardList.size()) {
            BankCard cardToRemove = cardList.get(index);
            if (cardToRemove.getUsername().equals(username)) {
                cardList.remove(index);
                saveToFile();
                System.out.println("🗑 Đã xóa thẻ.");
            } else {
                System.out.println("Bạn không thể xóa thẻ của người dùng khác.");
            }
        } else {
            System.out.println("Vị trí không hợp lệ.");
        }
    }
    public List<BankCard> getCardList() {
        return cardList;
    }
    // Lấy danh sách thẻ ngân hàng của người dùng
    public List<BankCard> getCardsForUser(String username) {
        List<BankCard> userCards = new ArrayList<>();
        for (BankCard card : cardList) {
            if (card.getUsername().equals(username)) {
                userCards.add(card);
            }
        }
        return userCards;
    }

    // Lưu dữ liệu thẻ vào file
    private void saveToFile() {
        try {
            FileUtil.writeToFile(FILE_PATH, cardList);
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu file thẻ ngân hàng: " + e.getMessage());
        }
    }

    // Tải dữ liệu thẻ từ file
    private void loadFromFile() {
        try {
            List<BankCard> loaded = FileUtil.readFromFile(FILE_PATH);
            if (loaded != null) {
                cardList = loaded;
            }
        } catch (Exception e) {
            System.out.println("Không thể đọc file thẻ ngân hàng: " + e.getMessage());
        }
    }
}
