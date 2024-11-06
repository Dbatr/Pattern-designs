package ru.patterns.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.patterns.interfaces.CanvasValidationHandler;

@Component
public class CanvasValidationChainBuilder {

    private final CanvasNameValidationHandler nameValidationHandler;
    private final CanvasUniquenessValidationHandler uniquenessValidationHandler;

    @Autowired
    public CanvasValidationChainBuilder(CanvasNameValidationHandler nameValidationHandler,
                                        CanvasUniquenessValidationHandler uniquenessValidationHandler) {
        this.nameValidationHandler = nameValidationHandler;
        this.uniquenessValidationHandler = uniquenessValidationHandler;
    }

    @PostConstruct
    public void buildChain() {
        nameValidationHandler.setNext(uniquenessValidationHandler);
    }

    public CanvasValidationHandler getValidationHandler() {
        return nameValidationHandler;  // Возвращаем начало цепочки
    }
}
