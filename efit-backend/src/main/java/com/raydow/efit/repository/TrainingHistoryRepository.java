package com.raydow.efit.repository;

import com.raydow.efit.domain.TrainingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingHistoryRepository extends JpaRepository<TrainingHistory, Integer> {
}
