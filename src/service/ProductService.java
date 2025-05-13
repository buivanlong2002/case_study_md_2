package service;

import model.Product;
import util.AbstractPersistenceService;

import java.util.*;
import java.util.stream.Collectors;

public class ProductService extends AbstractPersistenceService<Product> {

    public ProductService() {
        super("products.dat");
        ensureDataListInitialized();
    }

    private void ensureDataListInitialized() {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
    }

    // Thêm sản phẩm mới
    public void addProduct(Product p) {
        ensureDataListInitialized();
        dataList.add(p);
        saveToFile();
    }

    // Lấy tất cả sản phẩm
    public List<Product> getAll() {
        loadFromFile(); // luôn load lại từ file để đảm bảo tính đồng bộ
        ensureDataListInitialized();
        return new ArrayList<>(dataList);
    }

    // Tìm kiếm sản phẩm theo tên
    public List<Product> searchByName(String keyword) {
        loadFromFile(); // để đảm bảo tìm trên dữ liệu mới nhất
        ensureDataListInitialized();
        return dataList.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Sắp xếp theo giá tăng dần
    public void sortByPriceAsc() {
        loadFromFile();
        ensureDataListInitialized();
        dataList.sort(Comparator.comparingDouble(Product::getPrice));
        saveToFile();
    }

    // Sắp xếp theo ngày nhập giảm dần
    public void sortByDateDesc() {
        loadFromFile();
        ensureDataListInitialized();
        dataList.sort(Comparator.comparing(Product::getImportDate).reversed());
        saveToFile();
    }
}
