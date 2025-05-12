package service;

import model.Category;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private final String CATEGORY_FILE = "categories.dat";
    private List<Category> categories = new ArrayList<>();

    public CategoryService() {
        loadFromFile();
    }

    public void addCategory(String name) {
        categories.add(new Category(name));
        saveToFile();
        System.out.println(">> Thêm danh mục thành công.");
    }

    public List<Category> getAll() {
        loadFromFile();
        return categories;
    }

    private void saveToFile() {
        try {
            FileUtil.writeToFile(CATEGORY_FILE, categories);
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu danh mục: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try {
            List<Category> data = FileUtil.readFromFile(CATEGORY_FILE);
            if (data != null) {
                categories = data;
            } else {
                categories = new ArrayList<>();
            }
        } catch (Exception e) {
            categories = new ArrayList<>();
            System.out.println("Lỗi khi tải danh mục: " + e.getMessage());
        }
    }
}
