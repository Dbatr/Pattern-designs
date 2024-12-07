package ru.patterns.templateMethod;

import org.springframework.stereotype.Component;
import ru.patterns.models.Theme;
import ru.patterns.repositories.ThemeRepository;

/**
 * Паттерн Template Method
 */
@Component
public class LightThemeProcessor extends ThemeProcessor {

    protected LightThemeProcessor(ThemeRepository themeRepository) {
        super(themeRepository);
    }

    @Override
    protected void applyCustomLogic(Theme theme) {
        theme.setPrimaryColor("#FFFFFF");
        theme.setSecondaryColor("#DDDDDD");
        theme.setFont("Arial");
    }
}
