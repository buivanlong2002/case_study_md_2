package service;

import model.BankCard;
import util.AbstractPersistenceService;

import java.util.ArrayList;
import java.util.List;

public class BankCardService extends AbstractPersistenceService<BankCard> {

    public BankCardService() {
        super("bankcards.dat");
        ensureDataListInitialized();
    }

    // Đảm bảo danh sách luôn khởi tạo
    private void ensureDataListInitialized() {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
    }

    // Thêm thẻ vào hệ thống
    public void addCard(BankCard card) {
        loadFromFile();
        ensureDataListInitialized();

        if (card == null) {
            System.out.println(" Thẻ không hợp lệ.");
            return;
        }

        for (BankCard existing : dataList) {
            if (existing.getUsername().equals(card.getUsername()) &&
                    existing.getCardNumber().equals(card.getCardNumber())) {
                System.out.println("⚠ Thẻ này đã được liên kết với tài khoản.");
                return;
            }
        }

        dataList.add(card);
        saveToFile();
        System.out.println(" Thêm thẻ thành công.");
    }

    // Hiển thị thẻ theo người dùng
    public void showCards(String username) {
        List<BankCard> userCards = getCardsByUsername(username);
        if (userCards.isEmpty()) {
            System.out.println(" Chưa có thẻ ngân hàng nào được liên kết.");
        } else {
            userCards.forEach(System.out::println);
        }
    }

    // Xóa thẻ theo vị trí trong danh sách người dùng
    public void removeCard(String username, int indexInUserList) {
        List<BankCard> userCards = getCardsByUsername(username);
        if (indexInUserList >= 0 && indexInUserList < userCards.size()) {
            BankCard cardToRemove = userCards.get(indexInUserList);
            dataList.remove(cardToRemove);
            saveToFile();
            System.out.println(" Đã xóa thẻ.");
        } else {
            System.out.println(" Vị trí không hợp lệ.");
        }
    }

    // Lấy thẻ theo username
    public List<BankCard> getCardsByUsername(String username) {
        loadFromFile();
        ensureDataListInitialized();
        List<BankCard> result = new ArrayList<>();
        for (BankCard card : dataList) {
            if (card.getUsername().equals(username)) {
                result.add(card);
            }
        }
        return result;
    }

    // Xóa thẻ theo đối tượng
    public void removeCardByObject(BankCard cardToRemove) {
        loadFromFile();
        ensureDataListInitialized();
        if (dataList.remove(cardToRemove)) {
            saveToFile();
            System.out.println(" Đã xóa thẻ thành công.");
        } else {
            System.out.println(" Không thể xóa thẻ.");
        }
    }

    // Alias cho removeCardByObject
    public void delete(BankCard cardToRemove) {
        removeCardByObject(cardToRemove);
    }
}
