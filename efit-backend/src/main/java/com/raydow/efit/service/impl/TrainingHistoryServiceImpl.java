package com.raydow.efit.service.impl;

import com.raydow.efit.domain.TrainingHistory;
import com.raydow.efit.repository.TrainingHistoryRepository;
import com.raydow.efit.service.TrainingHistoryService;
import com.raydow.efit.service.mapper.TrainingHistoryMapperVO;
import com.raydow.efit.service.vo.TrainingHistoryVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrainingHistoryServiceImpl implements TrainingHistoryService {

    private final TrainingHistoryRepository trainingHistoryRepository;
    private final TrainingHistoryMapperVO trainingHistoryMapperVO;

    public TrainingHistoryServiceImpl(TrainingHistoryRepository trainingHistoryRepository,
                                      TrainingHistoryMapperVO trainingHistoryMapperVO) {
        this.trainingHistoryRepository = trainingHistoryRepository;
        this.trainingHistoryMapperVO = trainingHistoryMapperVO;
    }

    @Override
    public TrainingHistoryVO createTrainingHistory(TrainingHistoryVO trainingHistoryVO) {
        TrainingHistory history = trainingHistoryMapperVO.toEntity(trainingHistoryVO);
        history = trainingHistoryRepository.save(history);
        return trainingHistoryMapperVO.toVO(history);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TrainingHistoryVO> getTrainingHistoryById(Integer id) {
        return trainingHistoryRepository.findById(id)
                .map(trainingHistoryMapperVO::toVO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainingHistoryVO> getAllTrainingHistories() {
        return trainingHistoryRepository.findAll()
                .stream()
                .map(trainingHistoryMapperVO::toVO)
                .collect(Collectors.toList());
    }

    @Override
    public TrainingHistoryVO updateTrainingHistory(Integer id, TrainingHistoryVO trainingHistoryVO) {
        TrainingHistory history = trainingHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TrainingHistory not found with id " + id));

        trainingHistoryMapperVO.updateEntity(trainingHistoryVO, history);

        history = trainingHistoryRepository.save(history);
        return trainingHistoryMapperVO.toVO(history);
    }

    @Override
    public void deleteTrainingHistory(Integer id) {
        if (!trainingHistoryRepository.existsById(id)) {
            throw new RuntimeException("TrainingHistory not found with id " + id);
        }
        trainingHistoryRepository.deleteById(id);
    }
}
