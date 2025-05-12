package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product implements Taxable, Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private double price;
    private int quantity;
    private Date importDate;
    private Category category;

    public Product(String id, String name, double price, int quantity, Date importDate, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.importDate = importDate;
        this.category = category;
    }

    // Tính thuế 5% theo interface Taxable
    @Override
    public double calculateTax() {
        return price * 0.05;
    }

    // Giá sau thuế (nếu cần)
    public double getPriceWithTax() {
        return price + calculateTax();
    }

    // toString chi tiết
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format(
                "ID: %s | Tên: %s | Giá: %.2f | SL: %d | Ngày nhập: %s | Danh mục: %s | Thuế: %.2f",
                id, name, price, quantity,
                sdf.format(importDate),
                category != null ? category.getName() : "Chưa phân loại",
                calculateTax()
        );
    }

    // Getter & Setter
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price; // ✅ trả về giá gốc
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getImportDate() {
        return importDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
