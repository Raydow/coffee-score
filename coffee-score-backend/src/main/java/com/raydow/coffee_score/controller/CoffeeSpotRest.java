package com.raydow.coffee_score.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/coffee-spots")
public class CoffeeSpotRest {

    @GetMapping
    public String getCoffeeSpots() {
        return "asdas";
    }
}
