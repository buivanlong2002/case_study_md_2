package service;

import model.Category;
import util.AbstractPersistenceService;

import java.util.ArrayList;
import java.util.List;

public class CategoryService extends AbstractPersistenceService<Category> {

    public CategoryService() {
        super("categories.dat");
        if (dataList == null) dataList = new ArrayList<>();
    }


    public void addCategory(String name) {
        dataList.add(new Category(name));
        saveToFile();
        System.out.println("✅ Thêm danh mục thành công.");
    }


    public List<Category> getAll() {
        loadFromFile();
        return dataList;
    }


    public void removeCategory(int index) {
        if (index >= 0 && index < dataList.size()) {
            Category removed = dataList.remove(index);
            saveToFile();
            System.out.println(" Đã xoá danh mục: " + removed.getName());
        } else {
            System.out.println( " Vị trí không hợp lệ.");
        }
    }


    public void updateCategory(int index, String newName) {
        if (index >= 0 && index < dataList.size()) {
            dataList.get(index).setName(newName);
            saveToFile();
            System.out.println(" Đã cập nhật danh mục.");
        } else {
            System.out.println(" Vị trí không hợp lệ.");
        }
    }
}
