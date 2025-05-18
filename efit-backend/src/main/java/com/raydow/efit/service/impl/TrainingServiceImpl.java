package com.raydow.efit.service.impl;

import com.raydow.efit.domain.Training;
import com.raydow.efit.repository.TrainingRepository;
import com.raydow.efit.service.TrainingService;
import com.raydow.efit.service.mapper.TrainingMapperVO;
import com.raydow.efit.service.vo.TrainingVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapperVO trainingMapperVO;

    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingMapperVO trainingMapperVO) {
        this.trainingRepository = trainingRepository;
        this.trainingMapperVO = trainingMapperVO;
    }

    @Override
    public TrainingVO createTraining(TrainingVO trainingVO) {
        Training training = trainingMapperVO.toEntity(trainingVO);
        training = trainingRepository.save(training);
        return trainingMapperVO.toVO(training);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TrainingVO> getTrainingById(Integer id) {
        return trainingRepository.findById(id)
                .map(trainingMapperVO::toVO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainingVO> getAllTrainings() {
        return trainingRepository.findAll()
                .stream()
                .map(trainingMapperVO::toVO)
                .collect(Collectors.toList());
    }

    @Override
    public TrainingVO updateTraining(Integer id, TrainingVO trainingVO) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found with id " + id));

        trainingMapperVO.updateEntity(trainingVO, training);

        training = trainingRepository.save(training);
        return trainingMapperVO.toVO(training);
    }

    @Override
    public void deleteTraining(Integer id) {
        if (!trainingRepository.existsById(id)) {
            throw new RuntimeException("Training not found with id " + id);
        }
        trainingRepository.deleteById(id);
    }
}
