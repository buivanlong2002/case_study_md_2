package model;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPriceWithTax() * quantity;
    }

    @Override
    public String toString() {
        return String.format("Product: %s | Quantity: %d | Total: %.2f", product.getName(), quantity, getTotalPrice());
    }
}
