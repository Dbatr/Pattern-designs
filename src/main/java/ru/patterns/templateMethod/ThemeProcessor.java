package ru.patterns.templateMethod;

import ru.patterns.models.Theme;
import ru.patterns.repositories.ThemeRepository;

import java.util.logging.Logger;

/**
 * Паттерн Template Method
 */
public abstract class ThemeProcessor {

    private final Logger log = Logger.getLogger(ThemeProcessor.class.getName());
    private final ThemeRepository themeRepository;

    protected ThemeProcessor(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public final void processTheme(Theme theme) {
        validateTheme(theme);
        applyCustomLogic(theme);
        saveTheme(theme);
    }

    private void validateTheme(Theme theme) {
        if (theme.getName() == null || theme.getName().isEmpty()) {
            throw new IllegalArgumentException("Theme must have a name");
        }
        log.info("Validation passed for theme: " + theme.getName());
    }

    protected abstract void applyCustomLogic(Theme theme);

    private void saveTheme(Theme theme) {
        log.info("Saving theme: " + theme.getName());

        themeRepository.save(theme);
    }
}
