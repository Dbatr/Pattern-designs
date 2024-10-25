package ru.patterns.abstractfactory.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.patterns.abstractfactory.services.StationeryShopFacade;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final StationeryShopFacade shopFacade;

    public ShopController(StationeryShopFacade shopFacade) {
        this.shopFacade = shopFacade;
    }

    @GetMapping("/notebook")
    public String buyNotebook() {
        return shopFacade.buyItem("notebook");
    }

    @GetMapping("/notepad")
    public String buyNotepad() {
        return shopFacade.buyItem("notepad");
    }
}
