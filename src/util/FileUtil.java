package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    // Ghi dữ liệu vào tệp
    public static <T> void writeToFile(String path, List<T> data) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(data);
        }
    }

    // Đọc dữ liệu từ tệp
    @SuppressWarnings("unchecked")
    public static <T> List<T> readFromFile(String path) throws IOException, ClassNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            return new ArrayList<>(); // Trả về danh sách rỗng nếu tệp không tồn tại
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (List<T>) ois.readObject();
        }
    }
}
