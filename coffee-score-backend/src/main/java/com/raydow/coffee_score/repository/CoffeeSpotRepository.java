package com.raydow.coffee_score.repository;

import com.raydow.coffee_score.domain.CoffeeSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeSpotRepository extends JpaRepository<CoffeeSpot, Long> {
    // métodos customizados podem ser adicionados aqui
}
