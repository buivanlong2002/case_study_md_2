package view;

import controller.AuthController;
import util.InputHelper;
import model.Account;
import model.Admin;
import model.User;
import view.adminview.AdminView;
import view.userview.HomeUserView;
import view.userview.UserView;


public class AuthView {
    private final AuthController authController = new AuthController();
    private final AdminView adminView = new AdminView();
    private final HomeUserView homeUserView = new HomeUserView();

    public void start() {
        while (true) {
            System.out.println("\n========= ĐĂNG NHẬP =========");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký tài khoản khách hàng");
            System.out.println("0. Thoát");

            int choice = InputHelper.getInt("Chọn: ");
            switch (choice) {
                case 1 -> login();
                case 2 -> register();
                case 0 -> {
                    System.out.println("Thoát chương trình.");
                    return;
                }
                default -> System.out.println("Sai lựa chọn.");
            }
        }
    }

    private void login() {
        String username = InputHelper.getString("Tên đăng nhập: ");
        String password = InputHelper.getString("Mật khẩu: ");

        Account account = authController.login(username, password);
        if (account instanceof Admin) {
            System.out.println("\n>> Đăng nhập với quyền ADMIN thành công!");
            adminView.display();
        } else if (account instanceof User) {
            System.out.println("\n>> Đăng nhập với quyền KHÁCH HÀNG thành công!");
            homeUserView.display();
        } else {
            System.out.println("Sai tên đăng nhập hoặc mật khẩu.");
        }
    }

    private void register() {
        String username = InputHelper.getString("Tạo tên đăng nhập: ");
        String password = InputHelper.getString("Tạo mật khẩu: ");
        authController.register(username, password);
        System.out.println("Đăng ký tài khoản thành công.");
    }
}
