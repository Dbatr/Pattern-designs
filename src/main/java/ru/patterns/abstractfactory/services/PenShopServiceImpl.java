package ru.patterns.abstractfactory.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.patterns.abstractfactory.interfaces.PenShopService;
import ru.patterns.abstractfactory.models.Pen;

import java.util.HashMap;
import java.util.Map;

/**
 * Паттерн Flyweight.
 */
@Service
public class PenShopServiceImpl implements PenShopService {

    private final Map<String, Pen> penCache = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(PenShopServiceImpl.class);

    @Override
    public String buyPen(String color, double tipSize) {

        String key = color + tipSize;

        Pen pen = penCache.computeIfAbsent(key, k -> {
            Pen newPen = new Pen(color, tipSize);
            logger.info("Created new Pen: {}", newPen.getDescription());
            return newPen;
        });

        logger.info("Using existing Pen: {}", pen.getDescription());

        return "You bought a " + pen.getDescription();
    }
}
