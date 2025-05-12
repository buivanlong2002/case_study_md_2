package model;

public class User extends Account {

    public User(String username, String password, String email, String address, String phone, BankCard bankCard) {
        super(username, password, email, address, phone, bankCard);
    }



    @Override
    public Role getRole() {
        return Role.USER;
    }
}
