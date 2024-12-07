package ru.patterns.templateMethod;

import org.springframework.stereotype.Service;
import ru.patterns.dto.ThemeRequestDTO;
import ru.patterns.models.Theme;
import ru.patterns.repositories.ThemeRepository;

import java.util.List;

@Service
public class ThemeService {

    private final ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    private void processTheme(Theme theme, ThemeProcessor processor) {
        processor.processTheme(theme);
    }

    public Theme processDarkTheme(ThemeRequestDTO themeRequestDTO) {
        Theme theme = new Theme();
        theme.setName(themeRequestDTO.getName());
        processTheme(theme, new DarkThemeProcessor(themeRepository));

        return theme;
    }

    public Theme processLightTheme(ThemeRequestDTO themeRequestDTO) {
        Theme theme = new Theme();
        theme.setName(themeRequestDTO.getName());
        processTheme(theme, new LightThemeProcessor(themeRepository));

        return theme;
    }

    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }
}
