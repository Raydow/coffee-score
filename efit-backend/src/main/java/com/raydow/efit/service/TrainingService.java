package com.raydow.efit.service;

import com.raydow.efit.service.vo.TrainingVO;

import java.util.List;
import java.util.Optional;

public interface TrainingService {
    TrainingVO createTraining(TrainingVO trainingVO);
    Optional<TrainingVO> getTrainingById(Integer id);
    List<TrainingVO> getAllTrainings();
    TrainingVO updateTraining(Integer id, TrainingVO trainingVO);
    void deleteTraining(Integer id);
    void finishTraining(Integer id);
}
