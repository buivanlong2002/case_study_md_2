package service;

import model.Order;
import util.AbstractPersistenceService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderService extends AbstractPersistenceService<Order> {

    public OrderService() {
        super("orders.dat");
        ensureDataListInitialized();
    }

    // Đảm bảo dataList luôn được khởi tạo
    private void ensureDataListInitialized() {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
    }

    // Lưu đơn hàng mới
    public void saveOrder(Order order) {
        loadFromFile();
        ensureDataListInitialized();
        dataList.add(order);
        saveToFile();
    }

    // Lấy toàn bộ đơn hàng
    public List<Order> getAllOrders() {
        loadFromFile();
        ensureDataListInitialized();
        return new ArrayList<>(dataList);
    }

    // Lấy danh sách đơn hàng của 1 người dùng cụ thể
    public List<Order> getOrdersByUsername(String username) {
        loadFromFile();
        ensureDataListInitialized();
        List<Order> result = new ArrayList<>();
        for (Order order : dataList) {
            if (order.getUsername().equalsIgnoreCase(username)) {
                result.add(order);
            }
        }
        return result;
    }

    // Xoá đơn hàng theo ID và người dùng
    public boolean removeOrderByIdAndUsername(int orderId, String username) {
        loadFromFile();
        ensureDataListInitialized();
        Iterator<Order> iterator = dataList.iterator();
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
}
