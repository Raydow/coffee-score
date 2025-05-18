package com.raydow.efit.service.impl;

import com.raydow.efit.domain.Training;
import com.raydow.efit.domain.TrainingHistory;
import com.raydow.efit.domain.TrainingStatus;
import com.raydow.efit.repository.TrainingRepository;
import com.raydow.efit.repository.TrainingTypeRepository;
import com.raydow.efit.service.TrainingHistoryService;
import com.raydow.efit.service.TrainingService;
import com.raydow.efit.service.mapper.TrainingMapperVO;
import com.raydow.efit.service.vo.TrainingHistoryVO;
import com.raydow.efit.service.vo.TrainingVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.raydow.efit.domain.TrainingStatus.FINISHED;
import static com.raydow.efit.domain.TrainingStatus.IN_PROGRESS;

@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapperVO trainingMapperVO;
    private final TrainingTypeRepository trainingTypeRepository;
    private final TrainingHistoryService trainingHistoryService;

    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingMapperVO trainingMapperVO, TrainingTypeRepository trainingTypeRepository, TrainingHistoryService trainingHistoryService) {
        this.trainingRepository = trainingRepository;
        this.trainingMapperVO = trainingMapperVO;
        this.trainingTypeRepository = trainingTypeRepository;
        this.trainingHistoryService = trainingHistoryService;
    }

    @Override
    public TrainingVO createTraining(TrainingVO trainingVO) {
        List<TrainingStatus> invalidStatuses = List.of(TrainingStatus.IN_PROGRESS, TrainingStatus.PAUSED);

        boolean hasActiveTraining = trainingRepository.existsByUserIdAndStatusIn(trainingVO.getUserId(), invalidStatuses);
        if (hasActiveTraining) {
            throw new IllegalStateException("O usuário já possui um treino em andamento ou pausado.");
        }

        Training training = trainingMapperVO.toEntity(trainingVO);

        trainingTypeRepository.findByName(trainingVO.getTrainingTypeName())
                .ifPresent(training::setTrainingType);

        training.setStartedAt(LocalDateTime.now());
        training.setStatus(IN_PROGRESS);
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

        if (training.getStatus().equals(TrainingStatus.FINISHED)) {
            var trainingHistory = TrainingHistoryVO.builder()
                    .startedAt(training.getStartedAt())
                    .endedAt(LocalDateTime.now()) // ou outra lógica para data de término
                    .userId(training.getUser().getId())
                    .trainingTypeId(training.getTrainingType().getId())
                    .build();
            trainingHistoryService.createTrainingHistory(trainingHistory);
        }

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

    @Override
    public void finishTraining(Integer id) {
        Optional<TrainingVO> trainingVOOp = getTrainingById(id);

        if (trainingVOOp.isPresent()) {
            var trainingVO = trainingVOOp.get();
            trainingVO.setStatus(FINISHED);
            updateTraining(id, trainingVO);
        }
    }
}
