package com.raydow.efit.api.v1.controller;

import com.raydow.efit.api.v1.dto.TrainingHistoryResponseDTO;
import com.raydow.efit.api.v1.mapper.TrainingHistoryMapperDTO;
import com.raydow.efit.service.TrainingHistoryService;
import com.raydow.efit.service.vo.TrainingHistoryVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/training-history")
public class TrainingHistoryController {

    private final TrainingHistoryService trainingHistoryService;
    private final TrainingHistoryMapperDTO trainingHistoryMapperDTO;

    public TrainingHistoryController(TrainingHistoryService trainingHistoryService, TrainingHistoryMapperDTO trainingHistoryMapperDTO) {
        this.trainingHistoryService = trainingHistoryService;
        this.trainingHistoryMapperDTO = trainingHistoryMapperDTO;
    }

    @GetMapping
    public ResponseEntity<List<TrainingHistoryResponseDTO>> getAllTrainingHistories() {
        List<TrainingHistoryVO> list = trainingHistoryService.getAllTrainingHistories();
        List<TrainingHistoryResponseDTO> dtoList = list.stream()
                .map(trainingHistoryMapperDTO::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingHistoryResponseDTO> getTrainingHistoryById(@PathVariable Integer id) {
        return trainingHistoryService.getTrainingHistoryById(id)
                .map(trainingHistoryMapperDTO::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
