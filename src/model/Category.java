package model;

import java.io.Serializable;
import java.util.UUID;

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;

    public Category(String name) {
        this.id = UUID.randomUUID().toString(); // Tạo ID ngẫu nhiên
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Tên danh mục: %s", id, name);
    }
}
