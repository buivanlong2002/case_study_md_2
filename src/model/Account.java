package model;

import java.io.Serializable;

public abstract class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String username;
    protected String password;
    protected String email;
    protected String address;
    protected String phone;
    protected BankCard bankCard;

    public Account(String username, String password, String email, String address, String phone, BankCard bankCard) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.bankCard = bankCard;
    }

    // Getters & Setters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public BankCard getBankCard() { return bankCard; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setBankCard(BankCard bankCard) { this.bankCard = bankCard; }

    public abstract Role getRole();
}
