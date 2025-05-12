package service;

import model.Order;
import util.FileUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderService {
    private final String FILE_PATH = "orders.dat";
    private List<Order> orders;

    public OrderService() {
        orders = loadFromFile();
    }

    public void saveOrder(Order order) {
        orders.add(order);
        saveToFile();
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public List<Order> getOrdersByUsername(String username) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUsername().equalsIgnoreCase(username)) {
                result.add(order);
            }
        }
        return result;
    }

    public boolean removeOrderByIdAndUsername(int orderId, String username) {
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getId() == orderId && order.getUsername().equalsIgnoreCase(username)) {
                iterator.remove();
                saveToFile();
                return true;
            }
        }
        return false;
    }

    private void saveToFile() {
        try {
            FileUtil.writeToFile(FILE_PATH, orders);
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu file đơn hàng: " + e.getMessage());
        }
    }

    private List<Order> loadFromFile() {
        try {
            return FileUtil.readFromFile(FILE_PATH);
        } catch (Exception e) {
            System.out.println("Không thể đọc file đơn hàng: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
