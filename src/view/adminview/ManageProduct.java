package view.adminview;

import controller.ProductController;
import util.InputHelper;

public class ManageProduct {
    ProductController productController = new ProductController();
    public void manageProduct() {
        while (true) {
            System.out.println("\n--- Quản lý sản phẩm ---");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Hiển thị sản phẩm");
            System.out.println("3. Tìm kiếm sản phẩm");
            System.out.println("4. Sắp xếp sản phẩm theo giá");
            System.out.println("5. Sắp xếp sản phẩm theo ngày nhập");
            System.out.println("6. Quay lại");

            int choice = InputHelper.getInt("Chọn: ");
            switch (choice) {
                case 1:
                    productController.addProduct();
                    break;
                case 2:
                    productController.viewAllProducts();
                    break;
                case 3:
                    productController.searchProductByName();
                    break;
                case 4:
                    productController.viewSortedProductsByPrice();
                    break;
                case 5:
                    productController.viewSortedProductsByDate();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Sai lựa chọn.");
            }
        }
    }
}
