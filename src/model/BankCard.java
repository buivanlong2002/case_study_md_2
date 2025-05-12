package model;

import java.io.Serializable;

public class BankCard implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String bankName;
    private String username;  // Thêm trường username
    private double balance ;   // Thêm trường balance để quản lý số dư thẻ

    // Constructor với username và balance
    public BankCard(String cardNumber, String cardHolderName, String expiryDate, String bankName, String username, double balance) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.bankName = bankName;
        this.username = username;  // Gán username
        this.balance = balance;
    // Gán số dư thẻ
    }

    @Override
    public String toString() {
        return cardHolderName + " - " + cardNumber + " - " + bankName + " - " + expiryDate;
    }

    // Getter và Setter
    public String getCardNumber() {
        return cardNumber;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Phương thức thanh toán qua ATM
    public boolean processTransaction(double amount) {
        if (amount > balance) {
            System.out.println("Số dư không đủ để thanh toán.Số dư hiện tại của bạn :" + balance);
            return false;
        } else {
            balance -= amount;  // Trừ tiền khỏi số dư
            System.out.println("Thanh toán thành công! Số dư còn lại: " + balance);
            return true;
        }
    }
}
