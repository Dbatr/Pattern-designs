package ru.patterns.abstractfactory.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.patterns.abstractfactory.interfaces.InventoryService;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final Map<String, Integer> stock = new HashMap<>();

    @Value("${inventory.notebook.stock}")
    private int notebookStock;

    @Value("${inventory.notepad.stock}")
    private int notepadStock;

    @PostConstruct
    public void init() {
        stock.put("notebook", notebookStock);
        stock.put("notepad", notepadStock);
    }

    @Override
    public boolean isInStock(String itemType) {
        return stock.getOrDefault(itemType, 0) > 0;
    }

    @Override
    public void reduceStock(String itemType) {
        if (isInStock(itemType)) {
            stock.put(itemType, stock.get(itemType) - 1);
        } else {
            throw new IllegalArgumentException("Item not in stock: " + itemType);
        }
    }
}
