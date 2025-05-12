package service;

import model.Product;
import util.FileUtil;

import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    private List<Product> products;
    private static final String FILE_PATH = "products.dat";

    public ProductService() {
        this.products = loadFromFile();
    }

    public void addProduct(Product p) {
        products.add(p);
        saveToFile();
    }

    public List<Product> getAll() {
        return new ArrayList<>(products); // Trả về bản sao để tránh thay đổi ngoài ý muốn
    }

    public List<Product> searchByName(String keyword) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void sortByPriceAsc() {
        products.sort(Comparator.comparingDouble(Product::getPrice));
        saveToFile();
    }

    public void sortByDateDesc() {
        products.sort((a, b) -> b.getImportDate().compareTo(a.getImportDate()));
        saveToFile();
    }

    private void saveToFile() {
        try {
            FileUtil.writeToFile(FILE_PATH, products);
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu file sản phẩm: " + e.getMessage());
        }
    }

    private List<Product> loadFromFile() {
        try {
            return FileUtil.readFromFile(FILE_PATH);
        } catch (Exception e) {
            System.out.println("Không thể đọc file sản phẩm: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
