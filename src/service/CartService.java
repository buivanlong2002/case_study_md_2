package service;

import model.Cart;
import model.Product;

public class CartService {
    private static CartService instance;  // Singleton instance

    private Cart cart;

    private CartService() {
        this.cart = new Cart();
    }

    // Lấy instance duy nhất
    public static CartService getInstance() {
        if (instance == null) {
            instance = new CartService();
        }
        return instance;
    }

    // Thêm sản phẩm vào giỏ hàng
    public void addProductToCart(Product product, int quantity) {
        if (product != null && quantity > 0) {
            cart.addProduct(product, quantity);
        }
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void removeProductFromCart(Product product) {
        if (product != null) {
            int removedQuantity = cart.removeProduct(product);
            if (removedQuantity > 0) {
                product.setQuantity(product.getQuantity() + removedQuantity); // Cộng lại vào kho
            }
        }
    }

    // Lấy giỏ hàng hiện tại
    public Cart getCart() {
        return cart;
    }

    // Xóa tất cả sản phẩm trong giỏ
    public void clearCart() {
        cart = new Cart(); // Reset giỏ hàng
    }

    // Kiểm tra thanh toán
    public boolean checkout() {
        if (cart.getCartItems().isEmpty()) {
            return false; // Không thể thanh toán khi giỏ hàng trống
        }
        clearCart();
        return true;
    }

    // Tính tổng giá trị giỏ hàng (bao gồm thuế)
    public double getTotal() {
        return cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPriceWithTax() * item.getQuantity())
                .sum();
    }
}
