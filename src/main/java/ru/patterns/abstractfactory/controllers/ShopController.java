package ru.patterns.abstractfactory.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.patterns.abstractfactory.services.ShopService;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/notebook")
    public String buyNotebook() {
        return shopService.buyNotebook();
    }

    @GetMapping("/notepad")
    public String buyNotepad() {
        return shopService.buyNotepad();
    }
}
