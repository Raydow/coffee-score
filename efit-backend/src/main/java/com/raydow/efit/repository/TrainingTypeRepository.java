package com.raydow.efit.repository;

import com.raydow.efit.domain.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingType, Integer> {
    boolean existsByName(String name);
    Optional<TrainingType> findByName(String name);
}
