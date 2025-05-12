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

    public void addProductToCart(Product product, int quantity) {
        cart.addProduct(product, quantity);
    }

    public void removeProductFromCart(Product product) {
        int removedQuantity = cart.removeProduct(product);
        product.setQuantity(product.getQuantity() + removedQuantity); // Cộng lại vào kho
    }

    public Cart getCart() {
        return cart;
    }

    public void clearCart() {
        cart = new Cart(); // Reset giỏ hàng
    }

    public boolean checkout() {
        if (cart.getCartItems().isEmpty()) {
            return false;
        }
        clearCart();
        return true;
    }

    public double TONG() {
        return cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPriceWithTax() * item.getQuantity())
                .sum();
    }
}
