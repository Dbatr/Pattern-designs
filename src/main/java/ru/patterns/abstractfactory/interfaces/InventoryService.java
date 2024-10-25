package ru.patterns.abstractfactory.interfaces;

public interface InventoryService {
    boolean isInStock(String itemType);
    void reduceStock(String itemType);
}
