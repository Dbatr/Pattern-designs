package ru.patterns.controllers;

import org.springframework.web.bind.annotation.*;
import ru.patterns.dto.ThemeRequestDTO;
import ru.patterns.models.Theme;
import ru.patterns.templateMethod.ThemeService;

import java.util.List;

@RestController
@RequestMapping("/themes")
public class ThemeController {

    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @PostMapping("/process/light")
    public Theme processLightTheme(@RequestBody ThemeRequestDTO themeRequestDTO) {
        return themeService.processLightTheme(themeRequestDTO);
    }

    @PostMapping("/process/dark")
    public Theme processDarkTheme(@RequestBody ThemeRequestDTO themeRequestDTO) {
        return themeService.processDarkTheme(themeRequestDTO);
    }

    @GetMapping("/")
    public List<Theme> getAllThemes() {
        return themeService.getAllThemes();
    }
}
