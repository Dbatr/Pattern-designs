package ru.patterns.controllers;

import org.springframework.web.bind.annotation.*;
import ru.patterns.dto.ThemeRequestDTO;
import ru.patterns.models.Theme;
import ru.patterns.templateMethod.ThemeService;
import ru.patterns.visitor.GenerateImageVisitor;
import ru.patterns.visitor.ReportVisitor;
import ru.patterns.visitor.ThemeVisitor;

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

    @GetMapping("/report")
    public String getThemeReport(@RequestParam Long themeId) {
        Theme theme = themeService.findById(themeId);
        ThemeVisitor reportVisitor = new ReportVisitor();
        theme.accept(reportVisitor);
        return "Report generated.";
    }

    @GetMapping("/generateImage")
    public String getColorUsage(@RequestParam Long themeId) {
        Theme theme = themeService.findById(themeId);
        ThemeVisitor colorUsageVisitor = new GenerateImageVisitor();
        theme.accept(colorUsageVisitor);
        return "Image generated.";
    }
}
