package com.raydow.efit.repository;

import com.raydow.efit.domain.Training;
import com.raydow.efit.domain.TrainingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {
    boolean existsByUserIdAndStatusIn(Integer userId, List<TrainingStatus> statuses);
}
