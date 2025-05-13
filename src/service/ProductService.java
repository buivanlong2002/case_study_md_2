package service;

import model.Product;
import util.AbstractPersistenceService;

import java.util.*;
import java.util.stream.Collectors;

public class ProductService extends AbstractPersistenceService<Product> {

    public ProductService() {
        super("products.dat");
    }

    public void addProduct(Product p) {
        dataList.add(p);
        saveToFile();
    }

    public List<Product> getAll() {
        return new ArrayList<>(dataList);
    }

    public List<Product> searchByName(String keyword) {
        return dataList.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void sortByPriceAsc() {
        dataList.sort(Comparator.comparingDouble(Product::getPrice));
        saveToFile();
    }

    public void sortByDateDesc() {
        dataList.sort((a, b) -> b.getImportDate().compareTo(a.getImportDate()));
        saveToFile();
    }
}
