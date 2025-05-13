package util;

import java.util.List;
import java.util.ArrayList;

//Template Method
public abstract class AbstractPersistenceService<T> {
    protected List<T> dataList;
    private final String filePath;

    public AbstractPersistenceService(String filePath) {
        this.filePath = filePath;
        dataList = new ArrayList<>();  // Khởi tạo mặc định dataList
        loadFromFile();
    }

    @SuppressWarnings("unchecked")
    protected void loadFromFile() {
        try {
            List<T> loaded = FileUtil.readFromFile(filePath);
            if (loaded != null) {
                dataList = loaded;
            }
        } catch (Exception e) {
            System.out.println("Không thể đọc file: " + e.getMessage());
            // dataList đã được khởi tạo từ constructor, nên không cần khởi tạo lại ở đây
        }
    }

    protected void saveToFile() {
        try {
            FileUtil.writeToFile(filePath, dataList);
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    public List<T> getDataList() {
        return dataList;
    }
}
