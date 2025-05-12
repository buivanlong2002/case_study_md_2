package view.adminview;

import controller.CartController;
import controller.CategoryController;
import util.InputHelper;

public class ManageCategory {
    CategoryController categoryController = new CategoryController();
    public void manageCategory() {
        while (true) {
            System.out.println("\n========= MENU QUẢN LÝ DANH MỤC =========");
            System.out.println("1. Thêm danh mục");
            System.out.println("2. Hiển thị tất cả danh mục");
            System.out.println("0. Quay lại");

            int choice = InputHelper.getInt("Chọn: ");
            try {
                switch (choice) {
                    case 1:
                        categoryController.addCategory();
                        break;
                    case 2:
                        categoryController.viewAllCategories();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Sai lựa chọn.");
                }
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
}
