package ru.patterns.abstractfactory.services;

import org.springframework.stereotype.Service;
import ru.patterns.abstractfactory.interfaces.AuditService;
import ru.patterns.abstractfactory.interfaces.InventoryService;

/**
 * Паттерн Facade.
 */
@Service
public class StationeryShopFacade {

    private final ShopService shopService;
    private final AuditService auditService;
    private final InventoryService inventoryService;

    public StationeryShopFacade(ShopService shopService, AuditService auditService, InventoryService inventoryService) {
        this.shopService = shopService;
        this.auditService = auditService;
        this.inventoryService = inventoryService;
    }

    public String buyItem(String itemType) {
        if (!inventoryService.isInStock(itemType)) {
            auditService.outOfStock(itemType);
            return "Sorry, " + itemType + " is out of stock.";
        }

        String purchaseMessage;
        if ("notebook".equalsIgnoreCase(itemType)) {
            purchaseMessage = shopService.buyNotebook();
        } else if ("notepad".equalsIgnoreCase(itemType)) {
            purchaseMessage = shopService.buyNotepad();
        } else {
            return "Item not available.";
        }

        inventoryService.reduceStock(itemType);
        auditService.logPurchase(itemType, purchaseMessage);

        return purchaseMessage;
    }
}
