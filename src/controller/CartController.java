package controller;

import model.Product;
import service.CartService;
import service.ProductService;
import util.InputHelper;

import java.util.List;

public class CartController {
    private final CartService cartService;
    private final ProductService productService;

    public CartController() {
        this.cartService = CartService.getInstance(); // dùng singleton
        this.productService = new ProductService();
    }

    public void addProductToCart() {
        List<Product> products = productService.getAll();
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào để hiển thị.");
            return;
        }

        System.out.println("\n--- Danh sách sản phẩm ---");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("%d. %s | Giá: %.2f | Số lượng: %d | Thuế: %.2f\n",
                    i + 1, p.getName(), p.getPrice(), p.getQuantity(), p.calculateTax());
        }

        int productId = InputHelper.getInt("Chọn sản phẩm (số): ");
        if (productId < 1 || productId > products.size()) {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }

        Product selectedProduct = products.get(productId - 1);
        int quantity = InputHelper.getInt("Nhập số lượng muốn mua: ");

        if (quantity < 1) {
            System.out.println("Số lượng phải lớn hơn 0.");
            return;
        }

        if (quantity > selectedProduct.getQuantity()) {
            System.out.println("Số lượng vượt quá tồn kho. Vui lòng chọn lại.");
            return;
        }

        // Trừ số lượng tồn kho
        selectedProduct.setQuantity(selectedProduct.getQuantity() - quantity);

        // Thêm vào giỏ hàng
        cartService.addProductToCart(selectedProduct, quantity);
        System.out.println("Sản phẩm đã được thêm vào giỏ hàng.");
    }


    public void removeProductFromCart() {
        if (cartService.getCart().getCartItems().isEmpty()) {
            System.out.println("Giỏ hàng trống.");
            return;
        }

        System.out.println("\n--- Sản phẩm trong giỏ hàng ---");
        for (int i = 0; i < cartService.getCart().getCartItems().size(); i++) {
            System.out.printf("%d. %s\n", i + 1, cartService.getCart().getCartItems().get(i));
        }

        int index = InputHelper.getInt("Chọn sản phẩm muốn xóa (số): ");
        if (index < 1 || index > cartService.getCart().getCartItems().size()) {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }

        Product product = cartService.getCart().getCartItems().get(index - 1).getProduct();
        cartService.removeProductFromCart(product); // Cộng lại số lượng tại đây
        System.out.println("Sản phẩm đã được xóa khỏi giỏ hàng.");
    }


    public void viewCart() {
        System.out.println("\n--- Giỏ hàng ---");
        if (cartService.getCart().getCartItems().isEmpty()) {
            System.out.println("Giỏ hàng của bạn hiện đang trống.");
        } else {
            System.out.println(cartService.getCart());
        }
    }

    public void clearCart() {
        cartService.clearCart();
        System.out.println("Giỏ hàng đã được làm trống.");
    }
}
