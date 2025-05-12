package controller;

import service.AccountService;
import model.Account;
import model.User;
import model.Admin;

import java.util.List;
import java.util.Scanner;

public class AccountController {
    private final AccountService accountService;
    private final Scanner scanner;

    public AccountController() {
        this.accountService = new AccountService();
        this.scanner = new Scanner(System.in);
    }

    // Phương thức đăng ký tài khoản
    public void register() {
        System.out.print("Nhập tên đăng nhập: ");
        String username = scanner.nextLine();

        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();

        accountService.register(username, password);
    }

    // Phương thức đăng nhập
    public void login() {
        System.out.print("Nhập tên đăng nhập: ");
        String username = scanner.nextLine();

        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();

        Account account = accountService.login(username, password);

        if (account != null) {
            System.out.println("Đăng nhập thành công!");
            if (account instanceof Admin) {
                // Xử lý khi đăng nhập với tài khoản Admin
                System.out.println("Chào mừng Admin: " + username);
            } else {
                // Xử lý khi đăng nhập với tài khoản User
                System.out.println("Chào mừng người dùng: " + username);
            }
        } else {
            System.out.println("Tên đăng nhập hoặc mật khẩu sai.");
        }
    }

    // Phương thức cập nhật tài khoản
    public void updateAccount() {
        System.out.print("Nhập tên đăng nhập cũ: ");
        String oldUsername = scanner.nextLine();

        System.out.print("Nhập tên đăng nhập mới: ");
        String newUsername = scanner.nextLine();

        System.out.print("Nhập mật khẩu mới: ");
        String newPassword = scanner.nextLine();

        boolean success = accountService.updateAccount(oldUsername, newUsername, newPassword);

        if (success) {
            System.out.println("Cập nhật tài khoản thành công.");
        } else {
            System.out.println("Cập nhật tài khoản thất bại.");
        }
    }

    // Phương thức xóa tài khoản
    public void deleteAccount() {
        System.out.print("Nhập tên đăng nhập cần xóa: ");
        String username = scanner.nextLine();

        boolean success = accountService.deleteAccount(username);

        if (success) {
            System.out.println("Tài khoản đã được xóa.");
        } else {
            System.out.println("Xóa tài khoản thất bại.");
        }
    }

    // Phương thức hiển thị tất cả người dùng
    public void listUsers() {
        List<User> users = accountService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Không có người dùng nào.");
        } else {
            System.out.println("Danh sách người dùng:");
            for (User user : users) {
                System.out.println(user.getUsername() + " - " + user.getEmail());
            }
        }
    }

    // Phương thức hiển thị tất cả admin
    public void listAdmins() {
        List<Admin> admins = accountService.getAllAdmins();
        if (admins.isEmpty()) {
            System.out.println("Không có admin nào.");
        } else {
            System.out.println("Danh sách admin:");
            for (Admin admin : admins) {
                System.out.println(admin.getUsername() + " - " + admin.getEmail());
            }
        }
    }

    // Hiển thị menu cho người dùng
    public void showMenu() {
        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Đăng ký tài khoản");
            System.out.println("2. Đăng nhập");
            System.out.println("3. Cập nhật tài khoản");
            System.out.println("4. Xóa tài khoản");
            System.out.println("5. Danh sách người dùng");
            System.out.println("6. Danh sách admin");
            System.out.println("7. Thoát");

            System.out.print("Chọn một lựa chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    updateAccount();
                    break;
                case 4:
                    deleteAccount();
                    break;
                case 5:
                    listUsers();
                    break;
                case 6:
                    listAdmins();
                    break;
                case 7:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    public static void main(String[] args) {
        AccountController controller = new AccountController();
        controller.showMenu();
    }
}
