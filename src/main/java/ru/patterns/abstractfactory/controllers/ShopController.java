package ru.patterns.abstractfactory.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.patterns.abstractfactory.interfaces.PenShopService;
import ru.patterns.abstractfactory.services.StationeryShopFacade;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final StationeryShopFacade shopFacade;
    private final PenShopService penShopService;

    public ShopController(StationeryShopFacade shopFacade, PenShopService penShopService) {
        this.shopFacade = shopFacade;
        this.penShopService = penShopService;
    }

    @GetMapping("/notebook")
    public String buyNotebook() {
        return shopFacade.buyItem("notebook");
    }

    @GetMapping("/notepad")
    public String buyNotepad() {
        return shopFacade.buyItem("notepad");
    }

    @GetMapping("/pen")
    public String buyPen(@RequestParam String color, @RequestParam double tipSize) {
        return penShopService.buyPen(color, tipSize);
    }
}
