package service;

import util.InputHelper;
import model.Account;
import model.Admin;
import model.User;
import util.FileUtil;
import util.PasswordUtil;
import util.Session;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private List<Account> accounts = new ArrayList<>();
    private final String ACCOUNT_FILE = "accounts.dat";

    public AccountService() {
        loadAccounts();  // Tự động đọc dữ liệu từ file khi khởi tạo đối tượng
        createDefaultAdmin();  // Đảm bảo có tài khoản admin mặc định
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

            // Không nhập thẻ ngân hàng lúc đầu
            accounts.add(new User(username, password, email, address, phone, null));
            saveAccounts();

            System.out.println(">> Đăng ký tài khoản thành công.");
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi không xác định: " + e.getMessage());
        }
    }


    // Đăng nhập
    public Account login(String username, String password) {
        for (Account acc : accounts) {
            if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                // Lưu username vào session sau khi đăng nhập thành công
                Session.setCurrentUsername(username);
                return acc;
            }
        }
        return null;
    }

    // Lưu tất cả tài khoản vào tệp
    private void saveAccounts() {
        try {
            FileUtil.writeToFile(ACCOUNT_FILE, accounts);
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu tài khoản: " + e.getMessage());
        }
    }

    // Tải danh sách tài khoản từ tệp
    private void loadAccounts() {
        try {
            List<Account> data = FileUtil.readFromFile(ACCOUNT_FILE);
            if (data != null) {
                accounts = data;
            }
        } catch (Exception e) {
            System.out.println("Chưa có dữ liệu tài khoản.");
        }
    }

    // Tạo tài khoản admin mặc định nếu chưa có
    private void createDefaultAdmin() {
        boolean exists = accounts.stream().anyMatch(a -> a instanceof Admin);
        if (!exists) {
            accounts.add(new Admin("admin", "admin123", "admin@example.com", "Hà Nội", "0123456789" , null));
            saveAccounts();  // Lưu tự động sau khi tạo tài khoản admin mặc định
            System.out.println("Tạo tài khoản Admin mặc định thành công.");
        }
    }

    // Cập nhật tài khoản
    public boolean updateAccount(String oldUsername, String newUsername, String newPassword) {
        for (Account acc : accounts) {
            if (acc.getUsername().equals(oldUsername)) {
                acc.setUsername(newUsername);
                acc.setPassword(newPassword);
                saveAccounts();  // Lưu tự động sau khi cập nhật tài khoản
                return true;
            }
        }
        System.out.println("Lỗi: Tài khoản không tồn tại.");
        return false;
    }

    // Xóa tài khoản
    public boolean deleteAccount(String username) {
        Account accountToDelete = null;
        for (Account acc : accounts) {
            if (acc.getUsername().equals(username)) {
                accountToDelete = acc;
                break;
            }
        }

        if (accountToDelete != null) {
            accounts.remove(accountToDelete);
            saveAccounts();  // Lưu tự động sau khi xóa tài khoản
            System.out.println("Tài khoản đã được xóa.");
            return true;
        }
        System.out.println("Lỗi: Tài khoản không tồn tại.");
        return false;
    }

    // Lấy danh sách tất cả người dùng
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (Account acc : accounts) {
            if (acc instanceof User && !(acc instanceof Admin)) {
                users.add((User) acc);
            }
        }
        return users;
    }

    // Lấy danh sách tất cả admin
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        for (Account acc : accounts) {
            if (acc instanceof Admin) {
                admins.add((Admin) acc);
            }
        }
        return admins;
    }

    // Kiểm tra tên đăng nhập đã tồn tại chưa
    public boolean exists(String username) {
        return accounts.stream().anyMatch(acc -> acc.getUsername().equalsIgnoreCase(username));
    }
}
