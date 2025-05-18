package com.raydow.efit.api.v1.controller;

import com.raydow.efit.api.v1.dto.training.TrainingCreateDTO;
import com.raydow.efit.api.v1.dto.training.TrainingResponseDTO;
import com.raydow.efit.api.v1.mapper.TrainingMapperDTO;
import com.raydow.efit.service.TrainingService;
import com.raydow.efit.service.vo.TrainingVO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/trainings")
public class TrainingController {

    private final TrainingService trainingService;
    private final TrainingMapperDTO trainingMapperDTO;

    public TrainingController(TrainingService trainingService, TrainingMapperDTO trainingMapperDTO) {
        this.trainingService = trainingService;
        this.trainingMapperDTO = trainingMapperDTO;
    }

    @PostMapping
    public ResponseEntity<TrainingResponseDTO> createTraining(@Valid @RequestBody TrainingCreateDTO createDTO) {
        TrainingVO vo = trainingMapperDTO.fromCreateDtoToVO(createDTO);
        TrainingVO created = trainingService.createTraining(vo);
        TrainingResponseDTO responseDTO = trainingMapperDTO.toResponseDTO(created);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingResponseDTO> getTrainingById(@PathVariable Integer id) {
        return trainingService.getTrainingById(id)
                .map(trainingMapperDTO::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TrainingResponseDTO>> getAllTrainings() {
        List<TrainingResponseDTO> list = trainingService.getAllTrainings()
                .stream()
                .map(trainingMapperDTO::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingResponseDTO> updateTraining(@PathVariable Integer id,
                                                              @Valid @RequestBody TrainingCreateDTO updateDTO) {
        TrainingVO vo = trainingMapperDTO.fromCreateDtoToVO(updateDTO);
        TrainingVO updated = trainingService.updateTraining(id, vo);
        TrainingResponseDTO responseDTO = trainingMapperDTO.toResponseDTO(updated);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Integer id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/finish")
    public ResponseEntity<TrainingResponseDTO> finishTraining(@PathVariable Integer id) {
        trainingService.finishTraining(id);
        var training = trainingService.getTrainingById(id);
        return ResponseEntity.ok(trainingMapperDTO.toResponseDTO(training.get()));
    }
}
