package com.raydow.coffee_score.service;

import com.raydow.coffee_score.domain.CoffeeSpot;
import com.raydow.coffee_score.repository.CoffeeSpotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeSpotService {

    private final CoffeeSpotRepository repository;

    public CoffeeSpotService(CoffeeSpotRepository repository) {
        this.repository = repository;
    }

    public List<CoffeeSpot> findAll() {
        return repository.findAll();
    }

    public Optional<CoffeeSpot> findById(Long id) {
        return repository.findById(id);
    }

    public CoffeeSpot save(CoffeeSpot coffeeSpot) {
        return repository.save(coffeeSpot);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
