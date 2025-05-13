package service;

import model.Order;
import util.AbstractPersistenceService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderService extends AbstractPersistenceService<Order> {

    public OrderService() {
        super("orders.dat");
    }

    public void saveOrder(Order order) {
        dataList.add(order);
        saveToFile();
    }

    public List<Order> getAllOrders() {
        return dataList;
    }

    public List<Order> getOrdersByUsername(String username) {
        List<Order> result = new ArrayList<>();
        for (Order order : dataList) {
            if (order.getUsername().equalsIgnoreCase(username)) {
                result.add(order);
            }
        }
        return result;
    }

    public boolean removeOrderByIdAndUsername(int orderId, String username) {
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
