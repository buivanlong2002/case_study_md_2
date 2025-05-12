package controller;

import model.Category;
import service.CategoryService;
import util.InputHelper;

import java.util.List;

public class CategoryController {
    private final CategoryService categoryService = new CategoryService();

    public void addCategory() {
        String name = InputHelper.getString("Nhập tên danh mục mới: ");
        categoryService.addCategory(name);
    }

    public void viewAllCategories() {
        List<Category> list = categoryService.getAll();
        if (list.isEmpty()) {
            System.out.println("Không có danh mục nào.");
        } else {
            list.forEach(System.out::println);
        }
    }
}
