package com.raydow.efit.service;

import com.raydow.efit.service.vo.TrainingHistoryVO;

import java.util.List;
import java.util.Optional;

public interface TrainingHistoryService {
    TrainingHistoryVO createTrainingHistory(TrainingHistoryVO trainingHistoryVO);
    Optional<TrainingHistoryVO> getTrainingHistoryById(Integer id);
    List<TrainingHistoryVO> getAllTrainingHistories();
    TrainingHistoryVO updateTrainingHistory(Integer id, TrainingHistoryVO trainingHistoryVO);
    void deleteTrainingHistory(Integer id);
}
