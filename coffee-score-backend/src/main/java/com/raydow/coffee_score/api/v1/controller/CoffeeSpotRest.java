package com.raydow.coffee_score.api.v1.controller;

import com.raydow.coffee_score.domain.CoffeeSpot;
import com.raydow.coffee_score.service.CoffeeSpotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coffee-spots")
public class CoffeeSpotRest {

    private final CoffeeSpotService coffeeSpotService;

    public CoffeeSpotRest(CoffeeSpotService coffeeSpotService) {
        this.coffeeSpotService = coffeeSpotService;
    }

    @GetMapping
    public List<CoffeeSpot> getAllCoffeeSpots() {
        return coffeeSpotService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoffeeSpot> getCoffeeSpotById(@PathVariable Long id) {
        return coffeeSpotService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CoffeeSpot createCoffeeSpot(@RequestBody CoffeeSpot coffeeSpot) {
        return coffeeSpotService.save(coffeeSpot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoffeeSpot> updateCoffeeSpot(@PathVariable Long id, @RequestBody CoffeeSpot coffeeSpot) {
        return coffeeSpotService.findById(id)
                .map(existing -> {
                    existing.setName(coffeeSpot.getName());
                    CoffeeSpot updated = coffeeSpotService.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffeeSpot(@PathVariable Long id) {
        if (coffeeSpotService.findById(id).isPresent()) {
            coffeeSpotService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
