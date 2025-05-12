package controller;

import model.Category;
import model.Product;
import service.CategoryService;
import service.ProductService;
import util.InputHelper;

import java.util.Date;
import java.util.List;

public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController() {
        this.productService = new ProductService();
        this.categoryService = new CategoryService();
    }

    public void addProduct() {
        try {
            String id = InputHelper.getString("Nhập ID: ");
            String name = InputHelper.getString("Nhập tên sản phẩm: ");
            double price = InputHelper.getDouble("Nhập giá: ");
            int quantity = InputHelper.getInt("Nhập số lượng: ");
            Date importDate = InputHelper.getDate("Nhập ngày nhập (dd/MM/yyyy): ");

            List<Category> categories = categoryService.getAll();
            if (categories.isEmpty()) {
                System.out.println("Chưa có danh mục nào. Vui lòng thêm danh mục trước.");
                return;
            }

            System.out.println("Danh mục có sẵn:");
            for (int i = 0; i < categories.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, categories.get(i).getName());
            }

            int choice = InputHelper.getInt("Chọn danh mục (số): ");
            if (choice < 1 || choice > categories.size()) {
                System.out.println("Lựa chọn không hợp lệ.");
                return;
            }

            Category selectedCategory = categories.get(choice - 1);

            // Tạo sản phẩm
            Product product = new Product(id, name, price, quantity, importDate, selectedCategory);

            productService.addProduct(product);
            System.out.println(">> Đã thêm sản phẩm thành công.");
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
        }
    }

    public void viewAllProducts() {
        List<Product> list = productService.getAll();
        if (list.isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
        } else {
            System.out.println("\n--- Danh sách sản phẩm ---");
            list.forEach(System.out::println);
        }
    }

    public void searchProductByName() {
        String keyword = InputHelper.getString("Nhập từ khóa cần tìm: ");
        List<Product> result = productService.searchByName(keyword);
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm phù hợp.");
        } else {
            System.out.println("\n--- Kết quả tìm kiếm ---");
            result.forEach(System.out::println);
        }
    }

    public void viewSortedProductsByPrice() {
        productService.sortByPriceAsc();
        System.out.println(">> Đã sắp xếp theo giá tăng dần.");
        viewAllProducts();
    }

    public void viewSortedProductsByDate() {
        productService.sortByDateDesc();
        System.out.println(">> Đã sắp xếp theo ngày nhập giảm dần.");
        viewAllProducts();
    }
}
