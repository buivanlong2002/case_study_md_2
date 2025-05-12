package model;

public class Admin extends Account {

    public Admin(String username, String password, String email, String address, String phone, BankCard bankCard) {
        super(username, password, email, address, phone, bankCard);
    }


    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}

