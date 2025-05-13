package service;

import model.Account;
import model.Admin;
import model.User;
import util.AbstractPersistenceService;
import util.InputHelper;
import util.PasswordUtil;
import util.Session;

import java.util.ArrayList;
import java.util.List;

public class AccountService extends AbstractPersistenceService<Account> {

    public AccountService() {
        super("accounts.dat");
        createDefaultAdmin();  // Đảm bảo có admin mặc định
    }

    public void register(String username, String password) {
        try {
            if (exists(username)) {
                System.out.println("Lỗi: Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
                return;
            }

            PasswordUtil.validatePassword(password);

            String email = InputHelper.getString("Nhập email: ");
            String address = InputHelper.getString("Nhập địa chỉ: ");
            String phone = InputHelper.getString("Nhập số điện thoại: ");

            dataList.add(new User(username, password, email, address, phone, null));
            saveToFile();

            System.out.println(">> Đăng ký tài khoản thành công.");
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi không xác định: " + e.getMessage());
        }
    }

    public Account login(String username, String password) {
        for (Account acc : dataList) {
            if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                Session.setCurrentUsername(username);
                return acc;
            }
        }
        return null;
    }

    public boolean updateAccount(String oldUsername, String newUsername, String newPassword) {
        for (Account acc : dataList) {
            if (acc.getUsername().equals(oldUsername)) {
                acc.setUsername(newUsername);
                acc.setPassword(newPassword);
                saveToFile();
                return true;
            }
        }
        System.out.println("Lỗi: Tài khoản không tồn tại.");
        return false;
    }

    public boolean deleteAccount(String username) {
        Account accountToDelete = null;
        for (Account acc : dataList) {
            if (acc.getUsername().equals(username)) {
                accountToDelete = acc;
                break;
            }
        }

        if (accountToDelete != null) {
            dataList.remove(accountToDelete);
            saveToFile();
            System.out.println("Tài khoản đã được xóa.");
            return true;
        }
        System.out.println("Lỗi: Tài khoản không tồn tại.");
        return false;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (Account acc : dataList) {
            if (acc instanceof User && !(acc instanceof Admin)) {
                users.add((User) acc);
            }
        }
        return users;
    }

    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        for (Account acc : dataList) {
            if (acc instanceof Admin) {
                admins.add((Admin) acc);
            }
        }
        return admins;
    }

    public boolean exists(String username) {
        return dataList.stream().anyMatch(acc -> acc.getUsername().equalsIgnoreCase(username));
    }

    private void createDefaultAdmin() {
        boolean exists = dataList.stream().anyMatch(a -> a instanceof Admin);
        if (!exists) {
            dataList.add(new Admin("admin", "admin123", "admin@example.com", "Hà Nội", "0123456789", null));
            saveToFile();
            System.out.println("Tạo tài khoản Admin mặc định thành công.");
        }
    }
}
