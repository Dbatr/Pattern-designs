package ru.patterns.abstractfactory.services;

import org.springframework.stereotype.Service;
import ru.patterns.abstractfactory.interfaces.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuditServiceImpl implements AuditService {

    private static final Logger logger = LoggerFactory.getLogger(AuditServiceImpl.class);

    @Override
    public void logPurchase(String itemType, String description) {
        logger.info("Audit Log: Purchased {} - {}", itemType, description);
    }

    @Override
    public void outOfStock(String itemType) {
        logger.info("Audit Log: Out of stock {}", itemType);
    }
}
