package ru.patterns.abstractfactory.interfaces;

public interface AuditService {
    void logPurchase(String itemType, String description);
    void outOfStock(String itemType);
}