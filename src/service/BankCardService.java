package service;

import model.BankCard;
import util.AbstractPersistenceService;

import java.util.ArrayList;
import java.util.List;

public class BankCardService extends AbstractPersistenceService<BankCard> {

    public BankCardService() {
        super("bankcards.dat");
        if (dataList == null) dataList = new ArrayList<>();
    }

    public void addCard(BankCard card) {
        for (BankCard existing : dataList) {
            if (existing.getUsername().equals(card.getUsername()) &&
                    existing.getCardNumber().equals(card.getCardNumber())) {
                System.out.println("Thẻ này đã được liên kết với tài khoản.");
                return;
            }
        }
        dataList.add(card);
        saveToFile();
    }

    public void showCards(String username) {
        boolean found = false;
        for (BankCard card : dataList) {
            if (card.getUsername().equals(username)) {
                System.out.println(card);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Chưa có thẻ ngân hàng nào được liên kết.");
        }
    }

    public void removeCard(String username, int index) {
        if (index >= 0 && index < dataList.size()) {
            BankCard card = dataList.get(index);
            if (card.getUsername().equals(username)) {
                dataList.remove(index);
                saveToFile();
                System.out.println("🗑 Đã xóa thẻ.");
            } else {
                System.out.println("Không thể xóa thẻ của người dùng khác.");
            }
        } else {
            System.out.println("Vị trí không hợp lệ.");
        }
    }
    public List<BankCard> getCardsByUsername(String username) {
        List<BankCard> userCards = new ArrayList<>();
        for (BankCard card : dataList) {
            if (card.getUsername().equals(username)) {
                userCards.add(card);
            }
        }
        return userCards;
    }

    public List<BankCard> getCardsForUser(String username) {
        List<BankCard> result = new ArrayList<>();
        for (BankCard card : dataList) {
            if (card.getUsername().equals(username)) {
                result.add(card);
            }
        }
        return result;
    }
    public void delete(BankCard cardToRemove) {
        if (dataList.remove(cardToRemove)) {
            saveToFile();
            System.out.println(" Đã xóa thẻ thành công.");
        } else {
            System.out.println(" Không thể xóa thẻ.");
        }
    }
}
