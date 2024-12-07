package ru.patterns.templateMethod;

import org.springframework.stereotype.Component;
import ru.patterns.models.Theme;
import ru.patterns.repositories.ThemeRepository;

/**
 * Паттерн Template Method
 */
@Component
public class DarkThemeProcessor extends ThemeProcessor {

    protected DarkThemeProcessor(ThemeRepository themeRepository) {
        super(themeRepository);
    }

    @Override
    protected void applyCustomLogic(Theme theme) {
        theme.setPrimaryColor("#000000");
        theme.setSecondaryColor("#333333");
        theme.setFont("Roboto");
    }
}
