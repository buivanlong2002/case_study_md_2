package view.userview;

import controller.ProductController;

public class ProductView {
     ProductController productController = new ProductController();
    public   void viewAllProducts() {
        System.out.println("\n--- DANH SÁCH SẢN PHẨM ---");
        productController.viewAllProducts();
    }
    public void searchProducts() {
        System.out.println("\nTìm kiếm sản phẩm theo tên (tính năng đang phát triển).");

    }
    public void viewBestSellingProducts() {
        System.out.println("\nSản phẩm bán chạy (tính năng đang phát triển).");

    }
}
