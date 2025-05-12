package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(product, quantity));
    }

    public int removeProduct(Product product) {
        Iterator<CartItem> iterator = cartItems.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getProduct().getId().equals(product.getId())) {
                int removedQuantity = item.getQuantity();
                iterator.remove();
                return removedQuantity;
            }
        }
        return 0;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotalPrice() {
        return cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CartItem item : cartItems) {
            sb.append(item).append("\n");
        }
        sb.append("Total Price: ").append(getTotalPrice());
        return sb.toString();
    }
}
